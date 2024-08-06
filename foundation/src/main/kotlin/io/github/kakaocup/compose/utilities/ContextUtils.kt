package io.github.kakaocup.compose.utilities

import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry

fun getResourceString(@StringRes resId: Int) = InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(resId)
