package io.github.kakaocup.compose.foundation

import androidx.annotation.FloatRange
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.ProgressIndicatorDefaults.IndicatorBackgroundOpacity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import io.github.kakaocup.compose.semantics.backgroundColor
import io.github.kakaocup.compose.semantics.color
import io.github.kakaocup.compose.semantics.progress
import io.github.kakaocup.compose.semantics.strokeCap
import io.github.kakaocup.compose.semantics.strokeWidth

/**
 * Determinate <a href="https://material.io/components/progress-indicators#linear-progress-indicators" class="external" target="_blank">Material Design linear progress indicator</a>.
 *
 * Progress indicators express an unspecified wait time or display the length of a process.
 *
 * ![Linear progress indicator image](https://developer.android.com/images/reference/androidx/compose/material/linear-progress-indicator.png)
 *
 * By default there is no animation between [progress] values. You can use
 * [ProgressIndicatorDefaults.ProgressAnimationSpec] as the default recommended
 * [AnimationSpec] when animating progress, such as in the following example:
 *
 * @sample androidx.compose.material.samples.LinearProgressIndicatorSample
 *
 * @param progress The progress of this progress indicator, where 0.0 represents no progress and 1.0
 * represents full progress. Values outside of this range are coerced into the range.
 * @param modifier the [Modifier] to be applied to this progress indicator
 * @param color The color of the progress indicator.
 * @param backgroundColor The color of the background behind the indicator, visible when the
 * progress has not reached that area of the overall indicator yet.
 * @param strokeCap stroke cap to use for the ends of this progress indicator
 */
@Composable
fun LinearProgressIndicator(
    @FloatRange(from = 0.0, to = 1.0)
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    backgroundColor: Color = color.copy(alpha = IndicatorBackgroundOpacity),
    strokeCap: StrokeCap = StrokeCap.Butt,
) = androidx.compose.material.LinearProgressIndicator(
    progress = progress,
    modifier = modifier
        .semantics {
        this.progress = progress
        this.color = color
        this.backgroundColor = backgroundColor
        this.strokeCap = strokeCap
    },
    color = color,
    backgroundColor = backgroundColor,
    strokeCap = strokeCap
)

/**
 * Indeterminate <a href="https://material.io/components/progress-indicators#linear-progress-indicators" class="external" target="_blank">Material Design linear progress indicator</a>.
 *
 * Progress indicators express an unspecified wait time or display the length of a process.
 *
 * ![Linear progress indicator image](https://developer.android.com/images/reference/androidx/compose/material/linear-progress-indicator.png)
 *
 * @param modifier the [Modifier] to be applied to this progress indicator
 * @param color The color of the progress indicator.
 * @param backgroundColor The color of the background behind the indicator, visible when the
 * progress has not reached that area of the overall indicator yet.
 * @param strokeCap stroke cap to use for the ends of this progress indicator
 */
@Composable
fun LinearProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    backgroundColor: Color = color.copy(alpha = IndicatorBackgroundOpacity),
    strokeCap: StrokeCap = StrokeCap.Butt,
) = androidx.compose.material.LinearProgressIndicator(
    modifier = modifier
        .semantics {
            this.color = color
            this.backgroundColor = backgroundColor
            this.strokeCap = strokeCap
        },
    color = color,
    backgroundColor = backgroundColor,
    strokeCap = strokeCap
)

/**
 * Determinate <a href="https://material.io/components/progress-indicators#circular-progress-indicators" class="external" target="_blank">Material Design circular progress indicator</a>.
 *
 * Progress indicators express an unspecified wait time or display the length of a process.
 *
 * ![Circular progress indicator image](https://developer.android.com/images/reference/androidx/compose/material/circular-progress-indicator.png)
 *
 * By default there is no animation between [progress] values. You can use
 * [ProgressIndicatorDefaults.ProgressAnimationSpec] as the default recommended
 * [AnimationSpec] when animating progress, such as in the following example:
 *
 * @sample androidx.compose.material.samples.CircularProgressIndicatorSample
 *
 * @param progress The progress of this progress indicator, where 0.0 represents no progress and 1.0
 * represents full progress. Values outside of this range are coerced into the range.
 * @param modifier the [Modifier] to be applied to this progress indicator
 * @param color The color of the progress indicator.
 * @param strokeWidth The stroke width for the progress indicator.
 * @param backgroundColor The color of the background behind the indicator, visible when the
 * progress has not reached that area of the overall indicator yet.
 * @param strokeCap stroke cap to use for the ends of this progress indicator
 */
@Composable
fun CircularProgressIndicator(
    @FloatRange(from = 0.0, to = 1.0)
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    strokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth,
    backgroundColor: Color = Color.Transparent,
    strokeCap: StrokeCap = StrokeCap.Butt,
) = androidx.compose.material.CircularProgressIndicator(
    progress = progress,
    modifier = modifier
        .semantics {
            this.progress = progress
            this.color = color
            this.strokeWidth = strokeWidth
            this.backgroundColor = backgroundColor
            this.strokeCap = strokeCap
        },
    color = color,
    strokeWidth = strokeWidth,
    backgroundColor = backgroundColor,
    strokeCap = strokeCap
)

/**
 * Indeterminate <a href="https://material.io/components/progress-indicators#circular-progress-indicators" class="external" target="_blank">Material Design circular progress indicator</a>.
 *
 * Progress indicators express an unspecified wait time or display the length of a process.
 *
 * ![Circular progress indicator image](https://developer.android.com/images/reference/androidx/compose/material/circular-progress-indicator.png)
 *
 * @param modifier the [Modifier] to be applied to this progress indicator
 * @param color The color of the progress indicator.
 * @param strokeWidth The stroke width for the progress indicator.
 * @param backgroundColor The color of the background behind the indicator, visible when the
 * progress has not reached that area of the overall indicator yet.
 * @param strokeCap stroke cap to use for the ends of this progress indicator
 */
@Composable
fun CircularProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    strokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth,
    backgroundColor: Color = Color.Transparent,
    strokeCap: StrokeCap = StrokeCap.Square,
) = androidx.compose.material.CircularProgressIndicator(
    modifier = modifier
        .semantics {
            this.color = color
            this.strokeWidth = strokeWidth
            this.backgroundColor = backgroundColor
            this.strokeCap = strokeCap
        },
    color = color,
    strokeWidth = strokeWidth,
    backgroundColor = backgroundColor,
    strokeCap = strokeCap
)
