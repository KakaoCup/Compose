package io.github.kakaocup.compose.foundation

import androidx.annotation.DrawableRes
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import io.github.kakaocup.compose.semantics.imageContent
import io.github.kakaocup.compose.semantics.tintColor

/**
 * A Material Design icon component that draws [imageVector] using [tint], with a default value
 * of [LocalContentColor]. If [imageVector] has no intrinsic size, this component will use the
 * recommended default size. Icon is an opinionated component designed to be used with single-color
 * icons so that they can be tinted correctly for the component they are placed in. For multicolored
 * icons and icons that should not be tinted, use [Color.Unspecified] for [tint]. For generic images
 * that should not be tinted, and do not follow the recommended icon size, use the generic
 * [androidx.compose.foundation.Image] instead. For a clickable icon, see [IconButton].
 *
 * @param imageVector [ImageVector] to draw inside this Icon
 * @param contentDescription text used by accessibility services to describe what this icon
 * represents. This should always be provided unless this icon is used for decorative purposes,
 * and does not represent a meaningful action that a user can take. This text should be
 * localized, such as by using [androidx.compose.ui.res.stringResource] or similar
 * @param modifier optional [Modifier] for this Icon
 * @param tint tint to be applied to [imageVector]. If [Color.Unspecified] is provided, then no tint
 * is applied
 */
@Composable
@NonRestartableComposable
fun Icon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
) {
    androidx.compose.material.Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = modifier
            .semantics { this.tintColor = tint }
            .semantics { this.imageContent = imageVector },
        tint = tint
    )
}

/**
 * A Material Design icon component that draws [bitmap] using [tint], with a default value
 * of [LocalContentColor]. If [bitmap] has no intrinsic size, this component will use the
 * recommended default size. Icon is an opinionated component designed to be used with single-color
 * icons so that they can be tinted correctly for the component they are placed in. For multicolored
 * icons and icons that should not be tinted, use [Color.Unspecified] for [tint]. For generic images
 * that should not be tinted, and do not follow the recommended icon size, use the generic
 * [androidx.compose.foundation.Image] instead. For a clickable icon, see [IconButton].
 *
 * @param bitmap [ImageBitmap] to draw inside this Icon
 * @param contentDescription text used by accessibility services to describe what this icon
 * represents. This should always be provided unless this icon is used for decorative purposes,
 * and does not represent a meaningful action that a user can take. This text should be
 * localized, such as by using [androidx.compose.ui.res.stringResource] or similar
 * @param modifier optional [Modifier] for this Icon
 * @param tint tint to be applied to [bitmap]. If [Color.Unspecified] is provided, then no tint is
 * applied
 */
@Composable
@NonRestartableComposable
fun Icon(
    bitmap: ImageBitmap,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
) {
    androidx.compose.material.Icon(
        bitmap = bitmap,
        contentDescription = contentDescription,
        modifier = modifier
            .semantics { this.tintColor = tint }
            .semantics { this.imageContent = bitmap },
        tint = tint
    )
}

/**
 * A Material Design icon component that draws [painter] using [tint], with a default value
 * of [LocalContentColor]. If [painter] has no intrinsic size, this component will use the
 * recommended default size. Icon is an opinionated component designed to be used with single-color
 * icons so that they can be tinted correctly for the component they are placed in. For multicolored
 * icons and icons that should not be tinted, use [Color.Unspecified] for [tint]. For generic images
 * that should not be tinted, and do not follow the recommended icon size, use the generic
 * [androidx.compose.foundation.Image] instead. For a clickable icon, see [IconButton].
 *
 * @param painter [Painter] to draw inside this Icon
 * @param contentDescription text used by accessibility services to describe what this icon
 * represents. This should always be provided unless this icon is used for decorative purposes,
 * and does not represent a meaningful action that a user can take. This text should be
 * localized, such as by using [androidx.compose.ui.res.stringResource] or similar
 * @param modifier optional [Modifier] for this Icon
 * @param tint tint to be applied to [painter]. If [Color.Unspecified] is provided, then no tint is
 * applied
 */
@Composable
fun Icon(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
) {
    androidx.compose.material.Icon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .semantics { this.tintColor = tint }
            .semantics { this.imageContent = painter },
        tint = tint
    )
}

/**
 * A Material Design icon component that draws [painter] using [tint], with a default value
 * of [LocalContentColor]. If [painter] has no intrinsic size, this component will use the
 * recommended default size. Icon is an opinionated component designed to be used with single-color
 * icons so that they can be tinted correctly for the component they are placed in. For multicolored
 * icons and icons that should not be tinted, use [Color.Unspecified] for [tint]. For generic images
 * that should not be tinted, and do not follow the recommended icon size, use the generic
 * [androidx.compose.foundation.Image] instead. For a clickable icon, see [IconButton].
 *
 * @param painter [Painter] to draw inside this Icon
 * @param contentDescription text used by accessibility services to describe what this icon
 * represents. This should always be provided unless this icon is used for decorative purposes,
 * and does not represent a meaningful action that a user can take. This text should be
 * localized, such as by using [androidx.compose.ui.res.stringResource] or similar
 * @param modifier optional [Modifier] for this Icon
 * @param tint tint to be applied to [painter]. If [Color.Unspecified] is provided, then no tint is
 * applied
 */
@Composable
fun Icon(
    @DrawableRes
    resId: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
) {
    androidx.compose.material.Icon(
        painter = painterResource(resId),
        contentDescription = contentDescription,
        modifier = modifier
            .semantics { this.tintColor = tint }
            .semantics { this.imageContent = resId },
        tint = tint
    )
}
