/*  $Id: ShooterD3ds.java 1179 2012-03-30 19:18:53Z jenetic.bytemare $
 *  =================================================================================
 */
    package de.christopherstock.android.shooter.base;

    import  javax.microedition.khronos.opengles.*;
    import  de.christopherstock.android.lib.*;
    import  de.christopherstock.android.lib.g3d.*;
    import  de.christopherstock.android.lib.io.*;
    import  android.content.*;
    import  android.graphics.*;

    /********************************************************************************
    *
    *
    *   @author     Christopher Stock
    *   @version    0.1
    ********************************************************************************/
    public enum ShooterTexture
    {
        ELogoDrivingPark(   false   ),
        EAmmo(              true    ),
        ECar1(              true    ),
        ECrackers(          true    ),
        EGrass1(            true    ),
        ETest1(             true    ),
        ERoad1(             true    ),
        ERoad2(             true    ),
        ESign1(             true    ),
        ESign2(             true    ),
        EWood1(             true    ),
        ;

        public                  Bitmap          iBitmap         = null;
        public                  int             iId             = 0;
        public                  String          iFilename       = null;
        public                  boolean         iFlipBitmap     = false;

        private ShooterTexture( boolean aFlipBitmap )
        {
            iId         = ordinal();
            iFilename   = "tex_" + toString().toLowerCase();
            iFlipBitmap = aFlipBitmap;
        }

        public static final void init( GL10 gl, Context context )
        {
            for ( int i = 0; i < values().length; ++i )
            {
                if ( values()[ i ].iBitmap == null )
                {
                    values()[ i ].iBitmap = BitmapFactory.decodeResource( context.getResources(), Lib.getResourceRawID( context, values()[ i ].iFilename ) );
                    if ( values()[ i ].iFlipBitmap ) values()[ i ].iBitmap = LibIO.flipVert( values()[ i ].iBitmap );    //flip texture vertical
                }
                LibGLTexture.createGLTexture( gl, values()[ i ].iBitmap, i );
            }
        }

        public static final ShooterTexture getFromName( String aName )
        {
            for ( ShooterTexture texture : values() )
            {
                if ( texture.toString().equalsIgnoreCase( aName ) )
                {
                    return texture;
                }
            }

            return null;
        }
    }
