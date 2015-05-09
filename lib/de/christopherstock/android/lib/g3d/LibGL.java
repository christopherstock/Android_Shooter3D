/*  $Id: Face.java 1176 2012-03-28 22:54:10Z jenetic.bytemare@gmail.com $
 *  =======================================================================================
 */
    package de.christopherstock.android.lib.g3d;

    import  javax.microedition.khronos.opengles.GL10;
    import  javax.microedition.khronos.opengles.GL11;
    import  javax.microedition.khronos.opengles.GL11Ext;
    import  android.graphics.Bitmap;
    import  de.christopherstock.android.lib.math.*;
    import  de.christopherstock.android.lib.ui.LibAnchor;
    import  de.christopherstock.android.shooter.base.ShooterTexture;

    /**************************************************************************************
    *   Represents a face with an anchor and a various number of vertices that define the polygon.
    *
    *   @author     Christopher Stock
    *   @version    0.1
    **************************************************************************************/
    public abstract class LibGL
    {
        public static void fillRect( GL10 gl, int x, int y, int width, int height, int col )
        {
            float[] c4f = LibMath.col2f4( col );
            gl.glColor4f( c4f[ 0 ], c4f[ 1 ], c4f[ 2 ], c4f[ 3 ] );
            float z = 0.0f; //-8.0f;
            ( (GL11Ext)gl).glDrawTexfOES( x, y, z, width, height );
        }

        public static void drawBitmap( GL10 gl, int x, int y, ShooterTexture txt, LibAnchor anchor )
        {
            Bitmap  bmp             = txt.iBitmap;
            int     bitmapWidth     = bmp.getWidth();
            int     bitmapHeight    = bmp.getHeight();
            int     textureID       = txt.iId;
            int     blendColor      = 0x10000;
            int[]   mCrop           = new int[] { 0, bitmapHeight, bitmapWidth, -bitmapHeight };
            
            //consider anchor
            if ( anchor == LibAnchor.ERightTop      || anchor == LibAnchor.ERightMiddle     || anchor == LibAnchor.ERightBottom   ) x -= bmp.getWidth();
            if ( anchor == LibAnchor.ECenterTop     || anchor == LibAnchor.ECenterMiddle    || anchor == LibAnchor.ECenterBottom  ) x -= bmp.getWidth() / 2;
            if ( anchor == LibAnchor.ERightTop      || anchor == LibAnchor.ECenterTop       || anchor == LibAnchor.ELeftTop       ) y -= bmp.getHeight();
            if ( anchor == LibAnchor.ERightMiddle   || anchor == LibAnchor.ECenterMiddle    || anchor == LibAnchor.ELeftMiddle    ) y -= bmp.getHeight() / 2;
            
            gl.glShadeModel( GL10.GL_FLAT );
            gl.glEnable( GL10.GL_BLEND );
            gl.glBlendFunc( GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA );
            gl.glColor4x( blendColor, blendColor, blendColor, blendColor );
            gl.glEnable( GL10.GL_TEXTURE_2D );

            gl.glBindTexture( GL10.GL_TEXTURE_2D, textureID );
            ( (GL11)gl ).glTexParameteriv( GL10.GL_TEXTURE_2D, GL11Ext.GL_TEXTURE_CROP_RECT_OES, mCrop, 0 );
            ( (GL11Ext)gl ).glDrawTexfOES( x, y, 0, bitmapWidth, bitmapHeight );

            gl.glDisable( GL10.GL_BLEND );
            gl.glMatrixMode( GL10.GL_PROJECTION );
            gl.glPopMatrix();
            gl.glMatrixMode( GL10.GL_MODELVIEW );
            gl.glPopMatrix();                
        }
    }
