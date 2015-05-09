/*  $Id: Face.java 1176 2012-03-28 22:54:10Z jenetic.bytemare@gmail.com $
 *  =======================================================================================
 */
    package de.christopherstock.android.lib.g3d;

    import  java.io.Serializable;
    import  de.christopherstock.android.lib.Lib.LibTransformationMode;
    import  de.christopherstock.android.lib.math.*;
    import  de.christopherstock.android.lib.ui.*;

    /**************************************************************************************
    *   Represents a face with an anchor and a various number of vertices that define the polygon.
    *
    *   @author     Christopher Stock
    *   @version    0.1
    **************************************************************************************/
    public abstract class LibFace implements LibGLFace, LibGeomObject, Serializable
    {
        private     static  final   long                serialVersionUID                    = 3855014449381203086L;

        public static enum DrawMethod
        {
            /** Do not draw this face if it is too distant. */
            EHideIfTooDistant,

            /** Always draw these faces. */
            EAlwaysDraw,

            /** For hidden level bounds etc. */
            EInvisible,
            ;
        }

        private                     LibVertex           iAnchor                             = null;
        private                     LibGLTexture        iTexture                            = null;
        private                     LibColors           iColor                              = null;

        protected                   LibVertex           iNormal                             = null;

        public                      LibVertex[]         iOriginalVertices                   = null;
        public                      LibVertex[]         iTransformedVertices                = null;

        protected                   float               iFaceAngleHorz                      = 0.0f;
        protected                   float               iFaceAngleVert                      = 0.0f;
        protected                   DrawMethod          iDrawMethod                         = null;
        public                      float               iAlpha                              = 1.0f;
        public                      float               iDarken                             = 1.0f;

        /**************************************************************************************
        *   Constructs a new face.
        *
        *   @param  aAnchor      The anchor for this face.
        *   @param  aTexture   The texture to use. May be <code>null</code>.
        *   @param  aColor   The color for this face. May be <code>null</code>.
        **************************************************************************************/
        public LibFace( LibVertex aAnchor, LibGLTexture aTexture, LibColors aColor, LibVertex aFaceNormal )
        {
            //assign initial values
            iAnchor         = aAnchor;
            iTexture        = aTexture; //( aTexture == null ? Default.EStones1.iTexture : aTexture  );
            iColor          = ( aColor == null ? LibColors.EWhite : aColor );

            iNormal         = aFaceNormal;

            iDrawMethod     = DrawMethod.EHideIfTooDistant;
        }

        @Override
        public final void setNewAnchor( LibVertex newAnchor, boolean performTranslationOnFaces, LibTransformationMode transformationMode )
        {
            iAnchor = newAnchor;
            if ( performTranslationOnFaces )
            {
                translate( iAnchor.x, iAnchor.y, iAnchor.z, transformationMode );
            }
        }

        /***********************************************************************
        *   Translates the ORIGINAL vertices setting the TRANSFORMED vertices.
        *
        *   @param  tX  The x-modification value to translate all original vertices for.
        *   @param  tY  The y-modification value to translate all original vertices for.
        *   @param  tZ  The z-modification value to translate all original vertices for.
        ***********************************************************************/
        @Override
        public void translate( float tX, float tY, float  tZ, LibTransformationMode transformationMode )
        {
            LibVertex[] newTransformedVertices  = new LibVertex[ iOriginalVertices.length ];
            LibVertex[] srcVertices             = null;

            //choose source
            switch ( transformationMode )
            {
                case ETransformedToTransformed:
                {
                    srcVertices = iTransformedVertices;
                    break;
                }

                case EOriginalsToOriginals:
                case EOriginalsToTransformed:
                default:
                {
                    srcVertices = iOriginalVertices;
                    break;
                }
            }

            //translate all original vertices
            for ( int i = 0; i < srcVertices.length; ++i )
            {
                //remember to copy u and v and to make a new object!
                newTransformedVertices[ i ] = new LibVertex
                (
                    srcVertices[ i ].x + tX,
                    srcVertices[ i ].y + tY,
                    srcVertices[ i ].z + tZ,
                    srcVertices[ i ].u,
                    srcVertices[ i ].v
                );
            }

            //choose destination
            switch ( transformationMode )
            {
                case EOriginalsToOriginals:
                {
                    iOriginalVertices       = newTransformedVertices;
                    iTransformedVertices    = newTransformedVertices;
                    break;
                }

                case EOriginalsToTransformed:
                case ETransformedToTransformed:
                {
                    iTransformedVertices    = newTransformedVertices;
                    break;
                }
            }

            //update collision values
            //updateCollisionValues();
        }

        /***********************************************************************
        *   Rotates the TRANSFORMED vertices setting the TRANSFORMED vertices.
        *   Rotation operations shall always be performed after translation operations.
        *
        *   @param  transMatrix     The transformation-matrix to turn all vertices around.
        *   @param  tX              The amount to translate this vertex on the x-axis.
        *   @param  tY              The amount to translate this vertex on the y-axis.
        *   @param  tZ              The amount to translate this vertex on the z-axis.
        ***********************************************************************/
        public void translateAndRotateXYZ( LibMatrix transMatrix, float tX, float tY, float tZ, LibTransformationMode transformationMode, LibVertex alternateAnchor )
        {
            //translate all original vertices
            translate( tX, tY, tZ, transformationMode );

            //rotate all transformed vertices
            transMatrix.transformVertices( iTransformedVertices, ( alternateAnchor == null ? iAnchor : alternateAnchor ) );

            //alter originals?
            if ( transformationMode == LibTransformationMode.EOriginalsToOriginals )
            {
                iOriginalVertices = iTransformedVertices;
            }

            //update collision values
            //updateCollisionValues();
        }

        /***********************************************************************
        *   Translates the ORIGINAL vertices setting the TRANSFORMED vertices.
        *
        *   @param  scaleFactor     1.0 performs scalation to equal.
        ***********************************************************************/
        public void scale( float scaleFactor, boolean performOnOriginals )
        {
            //prune old transformed vertices
            iTransformedVertices = new LibVertex[ iOriginalVertices.length ];

            //translate all original vertices
            for ( int i = 0; i < iOriginalVertices.length; ++i )
            {
                //remember to copy u and v and to make a new object!
                iTransformedVertices[ i ] = new LibVertex
                (
                    iOriginalVertices[ i ].x * scaleFactor,
                    iOriginalVertices[ i ].y * scaleFactor,
                    iOriginalVertices[ i ].z * scaleFactor,
                    iOriginalVertices[ i ].u,
                    iOriginalVertices[ i ].v
                );
            }

            //alter originals?
            if ( performOnOriginals )
            {
                iOriginalVertices   = iTransformedVertices;
            }

            //update collision values
            //updateCollisionValues();
        }

        /***********************************************************************
        *   Translates the ORIGINAL vertices setting the TRANSFORMED vertices.
        *
        *   @param  scaleFactor     1.0 performs scalation to equal.
        ***********************************************************************/
        public void invert()
        {
            boolean performOnOriginals = true;

            //prune old transformed vertices
            iTransformedVertices = new LibVertex[ iOriginalVertices.length ];

            //invert all original vertices
            for ( int i = 0; i < iOriginalVertices.length; ++i )
            {
                //remember to copy u and v and to make a new object!
                iTransformedVertices[ i ] = new LibVertex
                (
                    iOriginalVertices[ i ].x * -1,
                    iOriginalVertices[ i ].y,
                    iOriginalVertices[ i ].z,
                    iOriginalVertices[ i ].u * -1,
                    iOriginalVertices[ i ].v
                );
            }

            //alter originals?
            if ( performOnOriginals )
            {
                iOriginalVertices   = iTransformedVertices;
            }

            //update collision values
            //updateCollisionValues();
        }

        protected final void setFaceAngleHorz( float aFaceAngleHorz )
        {
            iFaceAngleHorz = aFaceAngleHorz;
        }

        protected final void setFaceAngleVert( float aFaceAngleVert )
        {
            iFaceAngleVert = aFaceAngleVert;
        }

        public final void setOriginalVertices( LibVertex[] vertices )
        {
            iOriginalVertices    = vertices;
            iTransformedVertices = vertices;
        }

        public final void mirror( boolean x, boolean y, boolean z )
        {
            //mirror all originals
            for ( int i = 0; i < iOriginalVertices.length; ++i )
            {
                //remember to copy u and v and to make a new object!
                iOriginalVertices[ i ] = new LibVertex
                (
                    ( x ? -1 : 1 ) * iOriginalVertices[ i ].x,
                    ( y ? -1 : 1 ) * iOriginalVertices[ i ].y,
                    ( z ? -1 : 1 ) * iOriginalVertices[ i ].z,
                    iOriginalVertices[ i ].u,
                    iOriginalVertices[ i ].v
                );
            }

            //assign to transformed too !
            iTransformedVertices = iOriginalVertices;
        }

        public final void draw()
        {
            boolean draw = false;
            switch ( iDrawMethod )
            {
                case EAlwaysDraw:
                {
                    draw = true;
                    break;
                }

                case EHideIfTooDistant:
                {
                    //draw = ( getVerticesToDraw()[ 0 ].distance( ShooterGameLevel.currentPlayer().getAnchor() ) <= PlayerSettings.VIEW_DISTANCE );
                    break;
                }

                case EInvisible:
                {
                    draw = false;
                    break;
                }
            }

            //only draw if desired
            if ( draw )
            {
                //LibGL3D.view.enqueueFaceToQueue( this );
            }
        }

        @Override
        public final LibVertex getFaceNormal()
        {
            return iNormal; //iTransformedNormal; inoperative :(
        }

        @Override
        public final LibGLTexture getTexture()
        {
            return iTexture;
        }

        @Override
        public final float[] getColor3f()
        {
            return iColor.f3;
        }

        @Override
        public final int getColor()
        {
            return iColor.argb;
        }

        @Override
        public final LibVertex[] getVerticesToDraw()
        {
            return iTransformedVertices;
        }

        public final LibVertex[] getOriginalVertices()
        {
            return iOriginalVertices;
        }

        @Override
        public final LibVertex getAnchor()
        {
            return iAnchor;
        }

        public void changeTexture( LibGLTexture oldTex, LibGLTexture newTex )
        {
            if ( iTexture == oldTex )
            {
                iTexture = newTex;
            }
        }

        public void setDrawMethod( DrawMethod aDrawMethod )
        {
            iDrawMethod = aDrawMethod;
        }

        public void fadeOut( float delta )
        {
            iAlpha -= delta;
            if ( iAlpha < 0.0f ) iAlpha = 0.0f;
        }

        public void darken( float opacity )
        {
            iDarken = opacity;
            if ( iDarken > 1.0f ) iDarken = 1.0f;
            if ( iDarken < 0.0f ) iDarken = 0.0f;
        }

        @Override
        public final float getAlpha()
        {
            return iAlpha;
        }

        @Override
        public final float getDarken()
        {
            return iDarken;
        }
    }
