package arcorejpct.geronimo.don.arcorewithjpct_ae.utils

import android.app.Activity
import android.view.View
import android.view.WindowManager
/**
 * Helps the setup of fullscreen mode.
 * */
class FullscreenHelper {
    companion object {
        fun setFullscreenOnWindowFocusChanged(activity: Activity, hasFocus: Boolean) {
            if (hasFocus) {
                activity.window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                or View.SYSTEM_UI_FLAG_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            }
        }
    }
}