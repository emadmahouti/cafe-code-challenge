package com.pixy.codebase.common.viewgroup

import android.content.Context
import android.widget.LinearLayout

/**
 * Created by emadmahouti on 5/12/23
 */

open class VLinearLayout(context: Context): LinearLayout(context) {
    init {
        orientation = VERTICAL
    }
}