package arcorejpct.geronimo.don.arcorewithjpct_ae.utils

import android.opengl.GLSurfaceView
/**
 * Utility functions for configuration of the GLSurfaceView.
 * */
class GLSurfaceViewConfigurer {
    companion object {
        /**
         * Sets the properties of the GLSurfaceView and links it to the provided renderer
         * */
        fun openGlSetup(renderer: GLSurfaceView.Renderer, view: GLSurfaceView){
            view.preserveEGLContextOnPause = true
            view.setEGLContextClientVersion(2)
            view.setEGLConfigChooser(8,8,8,8,16,0)
            view.setRenderer(renderer)
            view.renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
        }

    }
}