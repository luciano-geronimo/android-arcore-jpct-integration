package arcorejpct.geronimo.don.arcorewithjpct_ae.model

import android.content.Context
import arcorejpct.geronimo.don.arcorewithjpct_ae.R
import com.threed.jpct.Object3D
import com.threed.jpct.SimpleVector
import com.threed.jpct.Texture
import com.threed.jpct.TextureManager
import com.threed.jpct.util.BitmapHelper

class RuntimeGeometry (ctx : Context) {
    private val context = ctx
    val geometry : Object3D

    private val coordinates : FloatArray
    private val uvs : FloatArray
    private val indices : IntArray
    private val normals : FloatArray

    init{
        coordinates = FloatArray(9)
        uvs = FloatArray(6)
        indices = IntArray(3)
        normals = FloatArray(9)

        coordinates[0] = 0.0f
        coordinates[1] = 0.0f
        coordinates[2] = 0.0f
        uvs[0] = 0.0f
        uvs[1] = 0.0f
        indices[0] = 0
        normals[0] = 0.0f
        normals[1] = 0.0f
        normals[2] = -1.0f

        coordinates[3] = 0.5f
        coordinates[4] = 1.0f
        coordinates[5] = 0.0f
        uvs[2] = 0.5f
        uvs[3] = 1.0f
        indices[1] = 1
        normals[3] = 0.0f
        normals[4] = 0.0f
        normals[5] = -1.0f

        coordinates[6] = 1.0f
        coordinates[7] = 0.0f
        coordinates[8] = 0.0f
        uvs[4] = 1.0f
        uvs[5] = 0.0f
        indices[2] = 2
        normals[6] = 0.0f
        normals[7] = 0.0f
        normals[8] = -1.0f
        val texture = Texture(BitmapHelper.rescale(BitmapHelper.convert( context.resources.getDrawable(R.mipmap.ic_launcher)),64,64));
        TextureManager.getInstance().addTexture("xxx", texture)
        val textureId = TextureManager.getInstance().getTextureID("xxx")

        geometry = Object3D(coordinates,normals,uvs,indices,textureId)
        geometry.strip()
        geometry.build()
        geometry.scale = 10.0f
        geometry.translate(SimpleVector(1.0f, 0.0f, 0.0f))
    }

}