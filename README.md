# Kakao Compose
[![Kotlin version badge](https://img.shields.io/badge/kotlin-1.5.30-blue.svg)](http://kotlinlang.org/)
[![Telegram](https://img.shields.io/static/v1?label=Telegram&message=RU&color=0088CC)](https://t.me/kaspresso)
[![Telegram](https://img.shields.io/static/v1?label=Telegram&message=EN&color=0088CC)](https://t.me/kaspresso_en)

Nice and simple DSL for Espresso Compose in Kotlin

![coco](https://user-images.githubusercontent.com/2812510/30947310-3825724c-a433-11e7-8a0d-3c3bfe00d584.png)

#### Benefits
- Readability
- Reusability
- Extensible DSL

### How to use it
#### Create Screen
Create your entity `ComposeScreen` where you will add the views involved in the interactions of the tests:
```Kotlin
class MainActivityScreen(composeTestRule: AndroidComposeTestRule<*, *>):
      ComposeScreen<MainActivityScreen>(composeTestRule)
```
 `ComposeScreen` can represent the whole user interface or a portion of UI.
If you are using [Page Object pattern](https://martinfowler.com/bliki/PageObject.html) you can put the interactions of Kakao inside the Page Objects.

#### Create KNode
`ComposeScreen` contains `KNode`, these are the Android Framework views where you want to do the interactions:
```Kotlin
class MainActivityScreen(composeTestRule: AndroidComposeTestRule<*, *>) :
    ComposeScreen<MainActivityScreen>(composeTestRule) {
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

### Contribution Policy

**Kakao Compose** is an open source project, and depends on its users to improve it. We are more than happy to find you interested in taking the project forward.

Kindly refer to the [Contribution Guidelines](https://github.com/kakaocup/compose/blob/master/CONTRIBUTING.md) for detailed information.

### Code of Conduct

Please refer to [Code of Conduct](https://github.com/kakaocup/compose/blob/master/CODE_OF_CONDUCT.md) document.

### License

Kakao Compose is open source and available under the [Apache License, Version 2.0](https://github.com/kakaocup/compose/blob/master/LICENSE).


