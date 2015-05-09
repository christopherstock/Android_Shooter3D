/*  $Id: Shooter.java 1176 2012-03-28 22:54:10Z jenetic.bytemare@gmail.com $
 *  =======================================================================================
 */
    package de.christopherstock.android.shooter.g3d;

    import  javax.microedition.khronos.opengles.*;
    import  de.christopherstock.android.lib.g3d.*;
    import  de.christopherstock.android.lib.math.*;
    import  de.christopherstock.android.shooter.base.*;
    import  de.christopherstock.android.shooter.base.ShooterD3ds.*;

    /******************************************************************************************
    *   The project's activity class.
    *
    *   @version    0.1
    *   @author     Christopher Stock
    *******************************************************************************************/
    public class ShooterMesh
    {
        public                  ShooterTriangle[]               iTriangles               = null;

        public ShooterMesh( D3dsFile item, ShooterTexture tex1, LibVertex anchor, float initRotZ )
        {
            this ( ShooterD3ds.getFaces( item, anchor ), tex1, initRotZ );
        }

        public ShooterMesh( ShooterTriangle[] aTriangles, ShooterTexture tex1, float initRotZ )
        {
            iTriangles = aTriangles;
            alterTexture( ShooterTexture.ETest1, tex1 );
            rotateXYZ( 0.0f, 0.0f, initRotZ );
        }

        public void draw( GL10 gl )
        {
            for ( ShooterTriangle triangle : iTriangles )
            {
                triangle.draw( gl );
            }
        }

        public void translate( float x, float y, float z )
        {
            for ( ShooterTriangle triangle : iTriangles )
            {
                triangle.translate( x, y, z );
            }
        }

        public void rotateXYZ( float x, float y, float z )
        {
            LibMatrix matrix = new LibMatrix( x, y, z );
            for ( ShooterTriangle triangle : iTriangles )
            {
                triangle.rotateXYZ( matrix );
            }
        }
        
        public void alterTexture( ShooterTexture oldTex, ShooterTexture newTex )
        {
            for ( ShooterTriangle triangle : iTriangles )
            {
                triangle.alterTexture( oldTex, newTex );
            }
        }        
    }
