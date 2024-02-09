package com.cafe.codechallenge.util.providers

import com.pixy.codebase.providers.StyleInterface
import com.pixy.codebase.providers.StyleProviderInterface
import com.pixy.codebase.providers.items.ShapeDrawable
import com.pixy.codebase.utils.Color
import com.pixy.codebase.utils.dpToPx

/**
 * Created by emadmahouti on 2/8/24
 */
sealed class StyleProvider {

    /**
     * application components style
     */
    class RetryButton: Style(
        ShapeDrawable(
            ColorProvider.retryButtonColor,
            ColorProvider.retryButtonColor,
            1.dpToPx,
            24.dpToPx
        ), ColorProvider.white
    )

    /**
     * codebase components default style
     */
    class Default : StyleProviderInterface {
        override fun input(): Style {
            return Style(
                ShapeDrawable(
                    ColorProvider.white,
                    ColorProvider.primary,
                    1.dpToPx,
                    5.dpToPx
                ), ColorProvider.black
            )
        }

        override fun button(): Style {
            return Style(
                ShapeDrawable(
                ColorProvider.primary,
                ColorProvider.primary,
                1.dpToPx,
                5.dpToPx
            ), ColorProvider.white)
        }
    }
}

open class Style(override val shapeDrawable: ShapeDrawable, override val textColor: Color):
    StyleInterface
