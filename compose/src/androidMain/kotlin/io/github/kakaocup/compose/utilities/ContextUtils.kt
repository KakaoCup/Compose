package io.github.kakaocup.compose.utilities

import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry

actual fun getResourceString(@StringRes resId: Int): String =
    InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(resId)
