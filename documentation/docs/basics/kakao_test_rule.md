---
sidebar_position: 2
---

# Kakao Compose Test Rule

### **How to reduce boilerplate**

When we writing UI test for compose we need to inject `composeTestRule` in to each `onComposeScreen` call.

```kotlin
    @Test
    fun simpleTest() {
        onComposeScreen<MainActivityScreen>(composeTestRule) {
            myButton {
                assertIsDisplayed()
                assertTextContains("Button 1")
            }
        }
    }
```

That's creating a lot of boilerplate in the code. But you can avoid it with `KakaoComposeTestRule` [Example](https://github.com/KakaoCup/Compose/blob/master/sample/src/androidTest/java/io/github/kakaocup/compose/test/SimpleTestGlobalSemantic.kt)

```kotlin
class SimpleTestGlobalSemantic {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val kakaoComposeTestRule = KakaoComposeTestRule(
        semanticsProvider = composeTestRule
    )

    @Test
    fun simpleTest() {
        onComposeScreen<GlobalSemanticScreen> {
            myText1 {
                assertIsDisplayed()
                assertTextContains("Simple text 1")
            }
        }
    }
}
```

This rule will create a wrapper around `composeTestRule` and provide it into `ComposeScreen` or `KNode` implicitly, what will reduce boilerplate in the tests.

---

### **Global override `useUnmergedTree`**

Compose framework always will try to optimise view tree and squash views if it acceptable. For example two `Text` view will be squashed into one, what can create misleading in tests.
By default in Espresso `useUnmergedTree` set as `false`, but on practice for UI test we want to have it as `true` in many cases. With `KakaoComposeTestRule` we can override it globally.

```kotlin
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val kakaoComposeTestRule = KakaoComposeTestRule(
        semanticsProvider = composeTestRule,
        useUnmergedTree = true
    )
```
