package com.cafe.codechallenge.util.providers

import com.pixy.codebase.providers.StyleInterface
import com.pixy.codebase.providers.StyleProviderInterface
import com.pixy.codebase.providers.items.ShapeDrawable
import com.pixy.codebase.utils.dpToPx

/**
 * Created by emadmahouti on 2/8/24
 */
sealed class StyleProvider {

    /**
     * application components style
     */
    class SearchInput: Style(
        ShapeDrawable(
            ColorProvider.white.light,
            ColorProvider.primary.light,
            1.dpToPx,
            20.dpToPx
        ), ColorProvider.black.light
    )

    /**
     * codebase components default style
     */
    class Default : StyleProviderInterface {
        override fun input(): Style {
            return Style(
                ShapeDrawable(
                    ColorProvider.white.light,
                    ColorProvider.primary.light,
                    1.dpToPx,
                    5.dpToPx
                ), ColorProvider.black.light
            )
        }

        override fun button(): Style {
            return Style(
                ShapeDrawable(
                ColorProvider.primary.light,
                ColorProvider.primary.light,
                1.dpToPx,
                5.dpToPx
            ), ColorProvider.white.light)
        }
    }
}

open class Style(override val shapeDrawable: ShapeDrawable, override val textColor: Int):
    StyleInterface
