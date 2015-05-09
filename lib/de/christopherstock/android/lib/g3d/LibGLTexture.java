/*  $Id: LibGLTexture.java 1176 2012-03-28 22:54:10Z jenetic.bytemare@gmail.com $
 *  =======================================================================================
 */
    package de.christopherstock.android.lib.g3d;

    import  java.io.Serializable;
    import  javax.microedition.khronos.opengles.*;
    import  android.graphics.*;
    import  android.opengl.*;
    import  de.christopherstock.android.shooter.base.*;

    /**************************************************************************************
    *   The Texture-System.
    *
    *   @author     Christopher Stock
    *   @version    0.1
    **************************************************************************************/
    public class LibGLTexture implements Serializable
    {
        private     static  final   long                serialVersionUID    = -9176705157906845259L;

        public static enum Translucency
        {
            EGlass,
            EOpaque,
            EHasMask,
            EHasMaskBulletHole,
            ;
        }

        private     static          int                 freeID              = 0;

        private                     int                 id                  = 0;
        private                     Translucency        translucency        = null;
        private                     ShooterMaterial     material            = null;
        private                     Integer             maskId              = null;

        public LibGLTexture( int aId, Translucency aTranslucency, ShooterMaterial aMaterial, Integer aMaskId )
        {
            id              = aId;
            translucency    = aTranslucency;
            material        = aMaterial;
            maskId          = aMaskId;
        }

        public final int getId()
        {
            return id;
        }

        public final ShooterMaterial getMaterial()
        {
            return material;
        }

        public final Translucency getTranslucency()
        {
            return translucency;
        }

        public final int getMaskId()
        {
            return maskId.intValue();
        }

        public static final int getNextFreeID()
        {
            return freeID++;
        }

        public static final void createGLTexture( GL10 gl, Bitmap bitmap, int id )
        {
            gl.glGenTextures( 1, new int[ 1 ], 0 );

            gl.glBindTexture(   GL10.GL_TEXTURE_2D, id );
            //scale up if the texture if smaller.
            gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR  );
            //scale linearly when image smalled than texture
            gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR  );
            gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT      );
            gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT      );
            GLUtils.texImage2D( GL10.GL_TEXTURE_2D, 0, bitmap, 0 );
        }
    }
