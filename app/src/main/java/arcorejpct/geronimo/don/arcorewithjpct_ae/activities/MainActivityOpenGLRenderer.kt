package arcorejpct.geronimo.don.arcorewithjpct_ae.activities

import android.content.Context
import android.opengl.GLSurfaceView
import arcorejpct.geronimo.don.arcorewithjpct_ae.R
import arcorejpct.geronimo.don.arcorewithjpct_ae.utils.DisplayRotationHelper
import com.threed.jpct.*
import com.threed.jpct.util.BitmapHelper
import com.threed.jpct.util.MemoryHelper
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
/**
 * Implements the rendering contract.
 * */
class MainActivityOpenGLRenderer(_ctx:Context, displayRotHelper:DisplayRotationHelper) : GLSurfaceView.Renderer {
    private val context = _ctx
    private val displayRotationHelper = displayRotHelper
    private var frameBuffer: FrameBuffer? = null

    private var isSceneInitialized : Boolean = false
    private lateinit var world : World
    private lateinit var camera : Camera
    private lateinit var cube : Object3D

    override fun onDrawFrame(gl: GL10?) {
        //displayRotationHelper.updateSessionIfNeeded(session) TODO so vai funcionar quando tiver a session
        val backgroundColor = RGBColor(100,40, 40)
        frameBuffer?.clear(backgroundColor)
        world.renderScene(frameBuffer)
        world.draw(frameBuffer)
        frameBuffer?.display()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        displayRotationHelper.onSurfaceChanged(width,height)

        frameBuffer?.dispose()

        frameBuffer = FrameBuffer(width, height)// FrameBuffer(gl,width,height)
        if (!isSceneInitialized) {
            world = World()
            //TODO ambient light will come from Arcore
            world.setAmbientLight(20,20,20)
            val sun = Light(world)
            sun.setIntensity(250.0f, 250.0f, 250.0f)
            val texture = Texture(BitmapHelper.rescale(BitmapHelper.convert( context.resources.getDrawable(R.mipmap.ic_launcher)),64,64));
            val textureName = "foobar"
            //TODO the cube's position and orientation will come from jpct
            TextureManager.getInstance().addTexture(textureName, texture)
            cube = Primitives.getCube(10.0f)
            cube.calcTextureWrapSpherical()
            cube.setTexture(textureName)
            cube.strip()
            cube.build()
            world.addObject(cube)
            //TODO Will i be able to pass the view and projection matrixes that Arcore will give me to JPCT camera or will i have to implement my own camera?
            camera = world.camera
            camera.moveCamera(Camera.CAMERA_MOVEOUT, 50.0f)
            camera.lookAt(cube.transformedCenter)
            //TODO most probably there won't be a sun in the Arcore scene.
            val sv = SimpleVector()
            sv.set(cube.transformedCenter)
            sv.y -= 100
            sv.z -= 100
            sun.position = sv

            MemoryHelper.compact()
            isSceneInitialized = true
        }

    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        //Not using for now.
    }
}