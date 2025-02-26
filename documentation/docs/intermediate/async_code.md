---
sidebar_position: 1
---

# Async Code and Idling Resource

When using Kotlin Coroutines (or any other async frameworks) in your Android app, you can still leverage `IdlingResource` to synchronize your UI tests with asynchronous tasks. However, with coroutines, you can use `IdlingResource` in combination with `Dispatchers` to control when your app is considered "idle."

Here’s how you can use `IdlingResource` with coroutines in Android UI tests:

---

### 1. **Add Dependencies**
Ensure you have the necessary dependencies for coroutines and Espresso in your `build.gradle` file:

```koltin
dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.2")
}
```

---

### 2. **Create a CoroutineIdlingResource**
Implement an `IdlingResource` that monitors coroutine execution. You can use a `CoroutineDispatcher` to track whether coroutines are active.

```kotlin
import androidx.test.espresso.IdlingResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

class CoroutineIdlingResource : IdlingResource {

    private val counter = AtomicInteger(0)
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return CoroutineIdlingResource::class.java.name
    }

    override fun isIdleNow(): Boolean {
        return counter.get() == 0
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }

    fun increment() {
        counter.getAndIncrement()
    }

    fun decrement() {
        val counterVal = counter.decrementAndGet()
        if (counterVal == 0) {
            resourceCallback?.onTransitionToIdle()
        }
    }
}
```

---

### 3. **Wrap CoroutineDispatcher with IdlingResource**
Create a custom `CoroutineDispatcher` that updates the `CoroutineIdlingResource` when coroutines start and finish.

```kotlin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

class IdlingDispatcher(private val idlingResource: CoroutineIdlingResource) : CoroutineDispatcher() {

    private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    override fun dispatch(context: kotlin.coroutines.CoroutineContext, block: Runnable) {
        idlingResource.increment()
        dispatcher.dispatch(context, Runnable {
            try {
                block.run()
            } finally {
                idlingResource.decrement()
            }
        })
    }
}
```

---

### 4. **Use the IdlingDispatcher in Your App**
Replace your default `Dispatchers` (e.g., `Dispatchers.IO` or `Dispatchers.Main`) with the `IdlingDispatcher` in your app code during tests.

```kotlin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val idlingResource = CoroutineIdlingResource()
    private val idlingDispatcher = IdlingDispatcher(idlingResource)

    fun fetchData() {
        CoroutineScope(idlingDispatcher).launch {
            // Simulate a network request or long-running task
            kotlinx.coroutines.delay(2000)
            // Update UI or state
        }
    }

    fun getIdlingResource(): CoroutineIdlingResource {
        return idlingResource
    }
}
```

---

### 5. **Register IdlingResource in Your Test**
In your UI test, register the `CoroutineIdlingResource` before running the test and unregister it afterward.

```kotlin
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var idlingResource: CoroutineIdlingResource

    @Before
    fun registerIdlingResource() {
        activityRule.scenario.onActivity { activity ->
            val viewModel = (activity as MainActivity).viewModel
            idlingResource = viewModel.getIdlingResource()
            Espresso.registerIdlingResources(idlingResource)
        }
    }

    @Test
    fun testFetchData() {
        // Perform UI actions that trigger coroutines
        onView(withId(R.id.fetchDataButton)).perform(click())

        // Espresso will wait until the coroutine finishes
        onView(withId(R.id.resultTextView)).check(matches(withText("Data Loaded")))
    }

    @After
    fun unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(idlingResource)
    }
}
```

---

### 6. **Alternative: Use `IdlingRegistry` for Global Coroutine Tracking**
If you want to track all coroutines globally, you can use `IdlingRegistry` to wrap the `Dispatchers.Main` or `Dispatchers.IO`. This approach is more advanced but ensures all coroutines are tracked.

```kotlin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

object IdlingDispatcher {
    private val idlingResource = CoroutineIdlingResource()

    fun register() {
        Dispatchers.Main = IdlingDispatcherWrapper(Dispatchers.Main, idlingResource)
        Espresso.registerIdlingResources(idlingResource)
    }

    fun unregister() {
        Espresso.unregisterIdlingResources(idlingResource)
    }
}
```

---

### Summary
By using `IdlingResource` with coroutines, you can ensure your UI tests wait for asynchronous tasks to complete. The key steps are:
1. Create a custom `IdlingResource` to track coroutine execution.
2. Wrap your `CoroutineDispatcher` to update the `IdlingResource`.
3. Register and unregister the `IdlingResource` in your tests.

This approach ensures your tests are reliable and synchronized with coroutine-based asynchronous operations.

### Using `waitUntil` from Kakao

As an alternative option you can use `waitUntil` function from Kakao Compose library
[Example](https://github.com/KakaoCup/Compose/blob/master/sample/src/androidTest/java/io/github/kakaocup/compose/test/WaitForTest.kt): 

```kotlin
mySimpleText {
    assertIsNotDisplayed()
    waitUntil {
        assertIsDisplayed()
    }
    assertIsDisplayed()
}
```

But we strongly recommending you to setup proper IdleResource for your UI tests