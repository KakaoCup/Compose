---
sidebar_position: 1
---

# Page Object Pattern

The **Page Object pattern** is a design pattern used in UI testing to improve test maintenance, readability, and reusability. It abstracts the UI structure and interactions into reusable components, making tests more modular and less brittle.

---

### **Key Concepts of the Page Object Pattern**
1. **Page Object**:
    - Represents a single page or component of a application.
    - Encapsulates all the elements (e.g., buttons, input fields) and actions (e.g., clicking, typing) related to that page.
    - Acts as an interface between the test scripts and the UI.

2. **Separation of Concerns**:
    - Test logic (e.g., assertions, test flow) is separated from the UI interaction logic.
    - Tests focus on "what to test," while Page Objects handle "how to interact with the UI."

3. **Reusability**:
    - Page Objects can be reused across multiple tests, reducing code duplication.
    - Changes to the UI only require updates in the Page Object, not in every test.

4. **Readability**:
    - Tests become more readable and expressive, as they use high-level methods provided by Page Objects.

---

### **How It Works**
1. **Create a Page Object for Each Screen/Component**:
    - Define a class for each page or reusable component in the application.
    - Include locators (e.g., test tags, ancestors and etc.) for the elements on the page.

:::warning Don't use content locators

Try to avoid using locators based on view content like

```kotlin 
val button: KNode = child { hasText("Button 1") }
```

That's can create an issue with wrong matching. For example you have fields with First and Last name of user, if you finding it by content. When your codebase will be updated (changing view content) than test will still pass
:::

   [Example](https://github.com/KakaoCup/Compose/blob/master/sample/src/androidTest/java/io/github/kakaocup/compose/screen/MainActivityScreen.kt):
   ```kotlin
    class MainActivityScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
  ComposeScreen<MainActivityScreen>(
      semanticsProvider = semanticsProvider,
      viewBuilderAction = { hasTestTag("MainScreen") }) 
   {
  val myText1: KNode = child {
      hasTestTag("mySimpleText")
  }

  val clickCounter: KNode = child {
      hasTestTag("clickCounter")
  }
   }

   ```

2. **Use Page Objects in Tests**:
    - Tests interact with the application through the methods provided by the Page Objects.
    - Tests are concise and focus on the test scenario.

   Example:
   ```kotlin
   class SimpleTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun simpleTest() {
        onComposeScreen<MainActivityScreen>(composeTestRule) {
            myText1 {
                assertIsDisplayed()
                assertTextContains("Simple text 1")
            }
        } 
   }
   }

   ```

3. **Handle Complex Flows with Page Objects**:
    - For multi-step workflows, chain methods from different Page Objects (Screens or KNodes).
    - Example: Logging in, navigating to a dashboard, and performing an action.

---

### **Benefits of the Page Object Pattern**
1. **Maintainability**:
    - Changes to the UI (e.g., updated element locators) only need to be made in the Page Object, not in every test.
2. **Reusability**:
    - Page Objects can be reused across multiple tests and test suites.
3. **Readability**:
    - Tests are easier to read and understand, as they use descriptive methods.
4. **Reduced Duplication**:
    - Common interactions are centralized in Page Objects, avoiding repetitive code.
5. **Improved Collaboration**:
    - Testers and developers can work independently, with testers focusing on test logic and developers maintaining Page Objects.

---

### **Best Practices**
1. **Single Responsibility**:
    - Each Page Object should represent a single page or component.
2. **Avoid Assertions in Page Objects**:
    - Keep assertions in the test scripts, not in the Page Objects.
3. **Handle Dynamic Elements**:
    - Use techniques like waiting for elements to load before interacting with them.
4. **Layered Architecture**:
    - For complex applications, consider breaking Page Objects into smaller components (e.g., headers, footers).

---

### **Example in Action**
#### Screen Object:
```kotlin
class LoginScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
   ComposeScreen<LoginScreen>(
      semanticsProvider = semanticsProvider,
      viewBuilderAction = { hasTestTag("LoginScreen") }
   ) {

   val loginField: KNode = child {
      hasTestTag("loginField")
   }

   val passwordField: KNode = child {
      hasTestTag("passwordField")
   }

   val loginButton: KNode = child {
      hasTestTag("loginButton")
   }
}
```

#### Test Script:
```kotlin
class LoginTest {

   @get:Rule
   val composeTestRule = createAndroidComposeRule<LoginScreen>()

   @Test
   fun simpleTest() {
      onComposeScreen<LoginScreen>(composeTestRule) {
         loginField {
            performTextInput("test@test.test")
            assertIsDisplayed() 
         }

         passwordField {
            performTextInput("dummypass")
         }

         loginButton {
            performTouchInput {
               click()
            }
         }
      }

      onComposeScreen<MainScreen>(composeTestRule) {
         assertIsDisplayed()
      }
   }
}
```

---

By using the Page Object pattern, UI tests become more robust, maintainable, and scalable, especially in large and complex applications.

