package arcorejpct.geronimo.don.arcorewithjpct_ae.activities

import android.app.Activity
import android.opengl.GLSurfaceView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import arcorejpct.geronimo.don.arcorewithjpct_ae.R
import arcorejpct.geronimo.don.arcorewithjpct_ae.utils.FullscreenHelper
import arcorejpct.geronimo.don.arcorewithjpct_ae.utils.GLSurfaceViewConfigurer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    /**The implementator of the rendering contract*/
    private val renderer : MainActivityOpenGLRenderer = MainActivityOpenGLRenderer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GLSurfaceViewConfigurer.openGlSetup(renderer, surfaceView)
    }

    override fun onResume() {
        super.onResume()
        //todo a lot of sorcery
        //resumes the gl suface. Order matters
        surfaceView.onResume() //must be paused after the arcore session
    }

    override fun onPause() {
        super.onPause()
        //todo a lot of sorcery
        //pauses the gl surface. Order matters
        surfaceView.onPause() //must be paused before the arcore Session
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        FullscreenHelper.setFullscreenOnWindowFocusChanged(this, hasFocus)
    }
}
