package arcorejpct.geronimo.don.arcorewithjpct_ae.activities

import android.content.Context
import android.opengl.GLSurfaceView
import arcorejpct.geronimo.don.arcorewithjpct_ae.R
import com.threed.jpct.*
import com.threed.jpct.util.BitmapHelper
import com.threed.jpct.util.MemoryHelper
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
/**
 * Implements the rendering contract.
 * */
class MainActivityOpenGLRenderer(_ctx:Context) : GLSurfaceView.Renderer {
    private val context = _ctx
    private lateinit var frameBuffer: FrameBuffer

    private var isSceneInitialized : Boolean = false
    private lateinit var world : World
    private lateinit var camera : Camera
    private lateinit var cube : Object3D

    override fun onDrawFrame(gl: GL10?) {
        val backgroundColor = RGBColor(100,40, 40)
        frameBuffer.clear(backgroundColor)
        world.renderScene(frameBuffer)
        world.draw(frameBuffer)
        frameBuffer.display()
//        TODO Render something
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        frameBuffer?.dispose()

        frameBuffer = FrameBuffer(width, height)// FrameBuffer(gl,width,height)
        if (!isSceneInitialized) {
            world = World()
            world.setAmbientLight(20,20,20)
            val sun = Light(world)
            sun.setIntensity(250.0f, 250.0f, 250.0f)
            val texture = Texture(BitmapHelper.rescale(BitmapHelper.convert( context.resources.getDrawable(R.mipmap.ic_launcher)),64,64));
            val textureName = "foobar"
            TextureManager.getInstance().addTexture(textureName, texture)
             cube = Primitives.getCube(10.0f)
            cube.calcTextureWrapSpherical()
            cube.setTexture(textureName)
            cube.strip()
            cube.build()
            world.addObject(cube)

            camera = world.camera
            camera.moveCamera(Camera.CAMERA_MOVEOUT, 50.0f)
            camera.lookAt(cube.transformedCenter)

            val sv = SimpleVector()
            sv.set(cube.transformedCenter)
            sv.y -= 100
            sv.z -= 100
            sun.position = sv

            MemoryHelper.compact()
            isSceneInitialized = true
        }
//        TODO create something to render
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}