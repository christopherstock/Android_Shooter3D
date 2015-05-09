
    package de.christopherstock.android.shooter.g3d;

    import  java.nio.ByteBuffer;
    import  java.nio.ByteOrder;
    import  java.nio.FloatBuffer;
    import  java.nio.ShortBuffer;
    import  javax.microedition.khronos.opengles.GL10;
    import  de.christopherstock.android.lib.g3d.*;
    import  de.christopherstock.android.lib.math.*;
    import  de.christopherstock.android.lib.ui.*;
import  de.christopherstock.android.shooter.base.*;

    /**************************************************************************************
    *   Represents a face in 3d space.
    *
    *   @author     Christopher Stock
    *   @version    0.1
    **************************************************************************************/
    public class ShooterTriangle
    {
        private     static  final   short[]             CONNECTION_INDICES          = new short[] { 0, 1, 2, 1 };

        private                     LibVertex           iAnchor                     = null;

        private                     LibVertex           iA                          = null;
        private                     LibVertex           iB                          = null;
        private                     LibVertex           iC                          = null;

        private                     ShooterTexture      iTex                        = null;
        private                     FloatBuffer         iVertexBuffer               = null;
        private                     FloatBuffer         iColorBuffer                = null;
        private                     ShortBuffer         iIndexBuffer                = null;
        private                     FloatBuffer         iTextureCoordinatesBuffer   = null;

        public ShooterTriangle( LibVertex aAnchor, LibVertex a, LibVertex b, LibVertex c, int color, ShooterTexture tex )
        {
            //super( null, null,  );

            iAnchor = aAnchor;

            //important to copy here!
            iA   = new LibVertex( a );
            iB   = new LibVertex( b );
            iC   = new LibVertex( c );

            iTex = tex;

            //translate by anchor
            iA.translate( aAnchor );
            iB.translate( aAnchor );
            iC.translate( aAnchor );

            if ( iTex != null )
            {
                color = LibColors.EWhite.argb;
            }

            //update vertices
            updateVertexBuffer();

            //set texture or color vertices
            float[] col4f = LibMath.col2f4( color );
            float   colors[] =
            {
                col4f[ 0 ], col4f[ 1 ], col4f[ 2 ], col4f[ 3 ],
                col4f[ 0 ], col4f[ 1 ], col4f[ 2 ], col4f[ 3 ],
                col4f[ 0 ], col4f[ 1 ], col4f[ 2 ], col4f[ 3 ],
            };

            // Multiply with 4 because a float is 4 bytes
            ByteBuffer colorByteBuf = ByteBuffer.allocateDirect( colors.length * 4 );
            colorByteBuf.order(ByteOrder.nativeOrder());
            iColorBuffer = colorByteBuf.asFloatBuffer();
            iColorBuffer.put(colors);
            iColorBuffer.position( 0 );

            if ( iTex != null )
            {

                float textureCoordinates[] =
                {
                    iA.u, iA.v,
                    iB.u, iB.v,
                    iC.u, iC.v,
                };


                /*
                float textureCoordinates[] =
                {
                    0.0f, 1.0f,
                    1.0f, 1.0f,
                    0.0f, 0.0f,
                    1.0f, 0.0f
                };
                */

                ByteBuffer texBuf = ByteBuffer.allocateDirect( textureCoordinates.length * 4 );
                texBuf.order( ByteOrder.nativeOrder() );
                iTextureCoordinatesBuffer = texBuf.asFloatBuffer();
                iTextureCoordinatesBuffer.put( textureCoordinates );
                iTextureCoordinatesBuffer.position( 0 );
            }

            //Multiply with 2 because a short is 2 bytes
            ByteBuffer ibb = ByteBuffer.allocateDirect( CONNECTION_INDICES.length * 2 );
            ibb.order(ByteOrder.nativeOrder());
            iIndexBuffer = ibb.asShortBuffer();
            iIndexBuffer.put( CONNECTION_INDICES );
            iIndexBuffer.position( 0 );
        }

        public void draw( GL10 gl )
        {
            boolean cullFaces = true;
            if ( cullFaces )
            {
                gl.glFrontFace( GL10.GL_CCW         );      //counter-clockwise winding
                gl.glEnable(    GL10.GL_CULL_FACE   );      //enable face culling
                gl.glCullFace(  GL10.GL_BACK        );      //what faces to remove with the face culling
            }

            // Enabled the vertices buffer for writing and to be used during rendering:
            gl.glEnableClientState( GL10.GL_VERTEX_ARRAY    );

            //set color vertex array
            gl.glEnableClientState( GL10.GL_COLOR_ARRAY     );
            gl.glColorPointer(  4, GL10.GL_FLOAT, 0, iColorBuffer   );

            if ( iTex != null )
            {
                // Telling OpenGL to enable textures.
                gl.glEnable(GL10.GL_TEXTURE_2D);
                // Tell OpenGL where our texture is located.
                gl.glBindTexture(GL10.GL_TEXTURE_2D, iTex.iId );
                // Tell OpenGL to enable the use of UV coordinates.
                gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
                // Telling OpenGL where our UV coordinates are.
                gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, iTextureCoordinatesBuffer);
            }

            // Specifies the location and data format for rendering:
            gl.glVertexPointer( 3, GL10.GL_FLOAT, 0, iVertexBuffer   );

            //draw it!
            gl.glDrawElements( GL10.GL_TRIANGLES, CONNECTION_INDICES.length, GL10.GL_UNSIGNED_SHORT, iIndexBuffer );

            if ( iTex != null )
            {
                // Disable the use of UV coordinates.
                gl.glDisableClientState( GL10.GL_TEXTURE_COORD_ARRAY );
                // Disable the use of textures.
                gl.glDisable( GL10.GL_TEXTURE_2D );
            }

            gl.glDisableClientState( GL10.GL_VERTEX_ARRAY );    //disable the vertices buffer

            if ( cullFaces )
            {
                gl.glDisable( GL10.GL_CULL_FACE );                  //disable the face culling
            }
        }

        /***********************************************************************
        *   Translates the vertices and the anchor.
        *
        *   @param  tX  The x-modification value to translate all original vertices for.
        *   @param  tY  The y-modification value to translate all original vertices for.
        *   @param  tZ  The z-modification value to translate all original vertices for.
        ***********************************************************************/
        public void translate( float tX, float tY, float  tZ )
        {
            //translate anchor
            iAnchor.x += tX; iAnchor.y += tY; iAnchor.z += tZ;

            //translate vertices
            iA.x += tX; iA.y += tY; iA.z += tZ;
            iB.x += tX; iB.y += tY; iB.z += tZ;
            iC.x += tX; iC.y += tY; iC.z += tZ;

            //update vertices buffer
            updateVertexBuffer();
        }

        private final void updateVertexBuffer()
        {
            ByteBuffer vbb = ByteBuffer.allocateDirect( 3 * 3 * 4 );
            vbb.order(ByteOrder.nativeOrder());
            iVertexBuffer = vbb.asFloatBuffer();
            iVertexBuffer.put( new float[] { iA.x, iA.z, iA.y, iB.x, iB.z, iB.y, iC.x, iC.z, iC.y, } );
            iVertexBuffer.position( 0 );
        }

        public void rotateXYZ( LibMatrix transMatrix )
        {
            LibVertex[] vertices = new LibVertex[] { iA, iB, iC, };

            //rotate all transformed vertices
            transMatrix.transformVertices( vertices, iAnchor );

            iA = vertices[ 0 ];
            iB = vertices[ 1 ];
            iC = vertices[ 2 ];

            //update vertex buffer
            updateVertexBuffer();
        }
        
        public void alterTexture( ShooterTexture oldTex, ShooterTexture newTex )
        {
            if ( iTex == oldTex ) iTex = newTex;
        }        
    }
