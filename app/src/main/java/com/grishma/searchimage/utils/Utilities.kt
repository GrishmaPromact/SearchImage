package com.grishma.searchimage.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Utilities class
 */
class Utilities {

    companion object {
        fun isNetworkConnected(context: Context): Boolean {
            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = cm.activeNetworkInfo
            return info != null && info.isConnected
        }

        const val LAUNCH_SECOND_ACTIVITY : Int = 1
        const val RESULT : String = "result"
        const val POSITION : String = "position"
        const val DATA : String = "data"
    }
}