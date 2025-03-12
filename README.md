# Kakao Compose
[![Kotlin version badge](https://img.shields.io/badge/kotlin-2.0.0-blue.svg)](https://kotlinlang.org/)
[![Telegram](https://img.shields.io/static/v1?label=Telegram&message=RU&color=0088CC)](https://t.me/kaspresso)
[![Telegram](https://img.shields.io/static/v1?label=Telegram&message=EN&color=0088CC)](https://t.me/kaspresso_en)
[![Telegram](https://img.shields.io/static/v1?label=Documentation&message=EN&color=008000)]([https://t.me/kaspresso_en](https://kakaocup.github.io/Compose/docs/introduction))

Nice and simple DSL for Espresso Compose in Kotlin

![coco](https://user-images.githubusercontent.com/2812510/30947310-3825724c-a433-11e7-8a0d-3c3bfe00d584.png)

#### Benefits
- Readability
- Reusability
- Extensible DSL
- Interceptors

### Concept
The one of the main concepts of Jetpack Compose is a presentation of UI according to UI tree (UI hierarchy) approach with a parent-children relationships support.
It means that the related UI test library has to support a parent-children relationships for Nodes by default.
It will be discovered below how Kakao Compose library supports mentioned parent-children relationships approach.

### How to use it
#### Create Screen
Create your entity `ComposeScreen` where you will add the views involved in the interactions of the tests:
```Kotlin
class MainActivityScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<MainActivityScreen>(
        semanticsProvider = semanticsProvider
    )
```
`ComposeScreen` can represent the whole user interface or a portion of UI.
If you are using [Page Object pattern](https://martinfowler.com/bliki/PageObject.html) you can put the interactions of Kakao inside the Page Objects.

Described way of Screen definition is very similar with the way that Kakao library offers.
But, usually, `Screen` in Jetpack Compose is a UI element (Node) too. That's why there is an additional option to declare `ComposeScreen`:
```Kotlin
class MainActivityScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<MainActivityScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag("MainScreen") }
    )
```
So, `ComposeScreen` is a `BaseNode`'s inheritor in Kakao-Compose library. And, as you've seen above, there is a possibility to describe
`ComposeScreen` without mandatory `viewBuilderAction` in cases when Screen is an abstraction without clear connection with any Node.

#### Create KNode
`ComposeScreen` contains `KNode`, these are the Jetpack Compose nodes where you want to do the interactions:
```Kotlin
class MainActivityScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<MainActivityScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag("MainScreen") }
    ) {

    val myButton: KNode = child {
        hasTestTag("myTestButton")
    }
}
```

`myButton` was declared as a child of `MainActivityScreen`.
It means that `myButton` will be calculated using matchers specified in a lambda explicitly and a parent matcher implicitly (`MainActivityScreen`).
Under the hood, the `SemanticMatcher` of `myButton` is equal to `hasTestTag("myTestButton") + hasParent(MainActivityScreen.matcher)`.

Also, `KNode` can be declared as a child of another `KNode`:
```Kotlin
class MainActivityScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<MainActivityScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag("MainScreen") }
    ) {

    val myButton: KNode = child {
        hasTestTag("myTestButton")
    }

    val myButton2: KNode = myButton.child {
        hasTestTag("myTestButton2")
    }
}
```
`myButton2` will be calculated with the following
`SemanticMatcher = hasTestTag("myTestButton") + hasParent(myButton.matcher) + hasParent(MainActivityScreen.matcher)`.
But, we advise not to abuse inheritance and use only the following chain: "ComposeScreen" - "Element of ComposeScreen".

The last, `KNode` can be declared without `child` function using only explicit matchers:
```Kotlin
class MainActivityScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<MainActivityScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag("MainScreen") }
    ) {

    val myButton = KNode(this) {
        hasTestTag("myTestButton")
    }
}
```

Every `KNode` contains many matches. Some examples of matchers provided by Kakao Compose:

* `hasText`
* `hasTestTag`
* <b>and more</b>

Like in Espresso you can combine different matchers:
```Kotlin
val myButton = KNode(this) {
    hasTestTag("myTestButton")
    hasText("Button 1")
}
```

#### Write the interaction.

The syntax of the test with Kakao is very easy, once you have the `ComposeScreen` and the `KNode` defined, you only have to apply
the actions or assertions like in Espresso:
```Kotlin
class ExampleInstrumentedTest {
    @Rule
    @JvmField
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun simpleTest() {
        onComposeScreen<MainActivityScreen>(composeTestRule) {
            myButton {
                assertIsDisplayed()
                assertTextContains("Button 1")
            }

            onNode {
                hasTestTag("doesNotExist")
            }.invoke {
                assertDoesNotExist()
            }
        }
    }
}
```

#### Lazy lists testing

:warning: This API is experimental and might change in future!

To test lazy lists such as `LazyRow` or `LazyColumn` you should add `KLazyListNode` into your `ComposeScreen`:
```Kotlin
val list = KLazyListNode(
    semanticsProvider = semanticsProvider,
    viewBuilderAction = { hasTestTag("LazyList") },
    itemTypeBuilder = {
        itemType(::LazyListItemNode)
        itemType(::LazyListHeaderNode)
    },
    positionMatcher = { position -> SemanticsMatcher.expectValue(LazyListItemPosition, position) }
)
```

Inside `itemTypeBuilder` function you should register `KLazyListItemNode` types to differentiate elements in lazy list:
```kotlin
class LazyListItemNode(
    semanticsNode: SemanticsNode,
    semanticsProvider: SemanticsNodeInteractionsProvider,
) : KLazyListItemNode<LazyListItemNode>(semanticsNode, semanticsProvider)

class LazyListHeaderNode(
    semanticsNode: SemanticsNode,
    semanticsProvider: SemanticsNodeInteractionsProvider,
) : KLazyListItemNode<LazyListHeaderNode>(semanticsNode, semanticsProvider) {
    val title: KNode = child {
        hasTestTag("LazyListHeaderTitle")
    }
}
```

The element position might be changed during the scroll due to lazy list construction, that’s why we should provide `positionMatcher` to determine the element position correctly. It could be achieved in different ways, for example you can determine item position through `TestTag`:
```kotlin
LazyColumn(
    Modifier
        .fillMaxSize()
        .testTag("LazyList")
) {
    itemsIndexed(items) { index, item ->
        when (item) {
            is LazyListItem.Header -> ListItemHeader(item, Modifier.testTag("position=$index"))
            is LazyListItem.Item -> ListItemCell(item, Modifier.testTag("position=$index"))
        }
    }
}
```

And then check this position inside `positionMatcher` lambda:
```kotlin
positionMatcher = { position -> hasTestTag("position=$position") }
```

But it will be more convenient and less error prone to create custom semantics property and custom modifier:
```kotlin
val LazyListItemPosition = SemanticsPropertyKey<Int>("LazyListItemPosition")
var SemanticsPropertyReceiver.lazyListItemPosition by LazyListItemPosition

fun Modifier.lazyListItemPosition(position: Int): Modifier {
    return semantics { lazyListItemPosition = position }
}
```

And check an item position with `SemanticsMatcher`:
```kotlin
positionMatcher = { position -> SemanticsMatcher.expectValue(LazyListItemPosition, position) }
```

So the typical lazy list test may look like this:
```kotlin
 @Test
fun lazyListTest() {
    onComposeScreen<LazyListScreen>(composeTestRule) {
        list {
            firstChild<LazyListHeaderNode> {
                title.assertTextEquals("Items from 1 to 10")
            }
            childWith<LazyListItemNode> {
                hasText("Item 1")
            } perform {
                assertTextEquals("Item 1")
            }
            childAt<LazyListItemNode>(10) {
                assertTextEquals("Item 10")
            }
        }
    }
}
```

Check the lazy list test [example](sample/src/androidTest/java/io/github/kakaocup/compose/test/LazyListTest.kt) for more information.

#### Intercepting

If you need to add custom logic during the `Kakao-Compose -> Espresso(Compose)` call chain (for example, logging) or
if you need to completely change the events/commands that are being sent to Espresso
during runtime in some cases, you can use the intercepting mechanism.

Interceptors are lambdas that you pass to a configuration DSL that will be invoked before calls happening from inside Kakao-Compose.

You have the ability to provide interceptors at two main different levels: Kakao-Compose runtime and any individual `BaseNode` instance.
Interceptors in Kakao-Compose support a parent-children concept too.
It means that any `BaseNode` inherits interceptors of his parents.

On each invocation of Espresso function that can be intercepted, Kakao-Compose will aggregate all available interceptors
for this particular call and invoke them in descending order: `Active BaseNode interceptor -> Interceptor of the parent Active BaseNode ->
... -> Kakao-Compose interceptor`.

Each of the interceptors in the chain can break the chain call by setting `isOverride` to true during configuration.
In that case Kakao-Compose will not only stop invoking remaining interceptors in the chain, **but will not perform the Espresso
call**. It means that in such case, the responsibility to actually invoke Espresso lies on the shoulders
of the developer.

Here's the examples of intercepting configurations:
```Kotlin
class SomeTest {
    @Before
    fun setup() {
        KakaoCompose { // KakaoCompose runtime
            intercept {
                onComposeInteraction {
                    onAll { list.add("ALL") }
                    onCheck { _, _ -> list.add("CHECK") }
                    onPerform { _, _ -> list.add("PERFORM") }
                }
            }
        }
    }

    @Test
    fun test() {
        onComposeScreen<MyScreen> {
            intercept {
                onCheck { interaction, assertion -> // Intercept check() call
                    Log.d("KAKAO", "$interaction is checking $assertion")
                }
            }

            myView {
                intercept { // Intercepting ComposeInteraction calls on this individual Node
                    onPerform(true) { interaction, action -> // Intercept perform() call and overriding the chain
                        // When performing actions on this view, Kakao level interceptor will not be called
                        // and we have to manually call Espresso now.
                        Log.d("KAKAO_NODE", "$interaction is performing $action")
                        interaction.perform(action)
                    }
                }
            }
        }
    }
}
```
For more detailed info please refer to the documentation.

### `KakaoComposeTestRule`

By default Espresso using `useUnmergedTree = true` and it create a lot of inconveniences with node matching.
However you can override global parameter with `KakaoComposeTestRule`.

Every Compose screen require `composeTestRule` whats creating a lot of boilerplate like `onComposeScreen<LazyListScreen>**(composeTestRule)** {`
But you can provide `composeTestRule` into `KakaoComposeTestRule` and use all screens without injection like `onComposeScreen<LazyListScreen> {`, it possible to mix both 
of implementation, injected `composeTestRule` will override provided via `KakaoComposeTestRule`.

You can find examples of it in [Simple project](https://github.com/KakaoCup/Compose/blob/master/sample/src/androidTest/java/io/github/kakaocup/compose/test/SimpleTestGlobalSemantic.kt)

### Setup
Maven
```xml
<dependency>
    <groupId>io.github.kakaocup</groupId>
    <artifactId>compose</artifactId>
    <version><latest version></version>
    <type>pom</type>
</dependency>
```
or Gradle:
```groovy
dependencies {
    androidTestImplementation 'io.github.kakaocup:compose:<latest version>'
}
```

```kotlin
dependencies {
    androidTestImplementation("io.github.kakaocup:compose:<latest version>")
}
```

### Contribution Policy

**Kakao Compose** is an open source project, and depends on its users to improve it. We are more than happy to find you interested in taking the project forward.

Kindly refer to the [Contribution Guidelines](https://github.com/kakaocup/compose/blob/master/CONTRIBUTING.md) for detailed information.

### Code of Conduct

Please refer to [Code of Conduct](https://github.com/kakaocup/compose/blob/master/CODE_OF_CONDUCT.md) document.

### License

Kakao Compose is open source and available under the [Apache License, Version 2.0](https://github.com/kakaocup/compose/blob/master/LICENSE).

### Thanks for supporting Open Source
<img src="https://resources.jetbrains.com/storage/products/company/brand/logos/jetbrains.png" height="100"> <img src="https://firebase.google.com/static/downloads/brand-guidelines/SVG/logo-logomark.svg" width="100" height="100">
