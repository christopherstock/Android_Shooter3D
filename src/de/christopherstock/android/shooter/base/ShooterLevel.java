/*  $Id: ShooterD3ds.java 1179 2012-03-30 19:18:53Z jenetic.bytemare $
 *  =================================================================================
 */
    package de.christopherstock.android.shooter.base;

    import  javax.microedition.khronos.opengles.*;
    import     de.christopherstock.android.lib.g3d.*;
    import  de.christopherstock.android.shooter.base.ShooterD3ds.D3dsFile;
    import  de.christopherstock.android.shooter.g3d.*;

    /********************************************************************************
    *    Specifies the level.
    *
    *   @author     Christopher Stock
    *   @version    0.1
    ********************************************************************************/
    public class ShooterLevel
    {
        private     static      ShooterLevel            current                 = null;

        private                 ShooterMesh[]           iMeshes                 = null;
        private                 ShooterPlayer           iPlayer                 = null;

        public ShooterLevel( ShooterMesh[] aMeshes )
        {
            iMeshes = aMeshes;
            iPlayer = new ShooterPlayer( new LibVertex( 51.0f, 53.0f, 0.3f ), new LibVertex( 0.0f, 0.0f, -90.0f ) );
        }

        public void animate()
        {
            //animate level
            //iMeshes[ 0 ].translate( 0.0f,   0.0f,       0.001f      );
            //iMeshes[ 0 ].rotateXYZ( 0.0f,   0.0f,       1f          );

            //animate player
            iPlayer.animate();



        }

        public static final ShooterLevel current()
        {
            return current;
        }

        public static final ShooterPlayer currentPlayer()
        {
            return current.iPlayer;
        }

        public void draw( GL10 gl )
        {
            for ( ShooterMesh mesh : iMeshes )
            {
                mesh.draw( gl );
            }
        }

        public static final void init()
        {
            current = new ShooterLevel
            (
                new ShooterMesh[]
                {
                    //floor
                    new ShooterMesh( D3dsFile.EFloor100x100, ShooterTexture.EGrass1, new LibVertex(  0.0f,   0.0f,      -0.01f  ), 0.0f ) ,
                    new ShooterMesh( D3dsFile.EFloor100x100, ShooterTexture.EGrass1, new LibVertex(  0.0f,   100.0f,    -0.01f  ), 0.0f ) ,
                    new ShooterMesh( D3dsFile.EFloor100x100, ShooterTexture.EGrass1, new LibVertex(  100.0f, 0.0f,      -0.01f  ), 0.0f ) ,
                    new ShooterMesh( D3dsFile.EFloor100x100, ShooterTexture.EGrass1, new LibVertex(  100.0f, 100.0f,    -0.01f  ), 0.0f ) ,

                    //left
                    new ShooterMesh( D3dsFile.ERoad1,   ShooterTexture.ERoad1,  new LibVertex(  50.0f,  50.0f,  0.0f   ), 0.0f  ),
                    new ShooterMesh( D3dsFile.ERoad1,   ShooterTexture.ERoad1,  new LibVertex(  66.0f,  50.0f,  0.0f   ), 0.0f  ),
                    new ShooterMesh( D3dsFile.ERoad1,   ShooterTexture.ERoad1,  new LibVertex(  82.0f,  50.0f,  0.0f   ), 0.0f  ),
                    new ShooterMesh( D3dsFile.ERoad1,   ShooterTexture.ERoad1,  new LibVertex(  98.0f,  50.0f,  0.0f   ), 0.0f  ),
                    new ShooterMesh( D3dsFile.ERoad1,   ShooterTexture.ERoad1,  new LibVertex(  114.0f, 50.0f,  0.0f   ), 0.0f  ),

                    //top
                    new ShooterMesh( D3dsFile.ERoad1,   ShooterTexture.ERoad1,  new LibVertex(  134.0f, 54.0f,  0.0f   ), 90.0f ),
                    
                    //right
                    new ShooterMesh( D3dsFile.ERoad1,   ShooterTexture.ERoad1,  new LibVertex(  50.0f,  70.0f,  0.0f   ), 0.0f  ),
                    new ShooterMesh( D3dsFile.ERoad1,   ShooterTexture.ERoad1,  new LibVertex(  66.0f,  70.0f,  0.0f   ), 0.0f  ),
                    new ShooterMesh( D3dsFile.ERoad1,   ShooterTexture.ERoad1,  new LibVertex(  82.0f,  70.0f,  0.0f   ), 0.0f  ),
                    new ShooterMesh( D3dsFile.ERoad1,   ShooterTexture.ERoad1,  new LibVertex(  98.0f,  70.0f,  0.0f   ), 0.0f  ),
                    new ShooterMesh( D3dsFile.ERoad1,   ShooterTexture.ERoad1,  new LibVertex(  114.0f, 70.0f,  0.0f   ), 0.0f  ),
                    
                    //bottom
                    new ShooterMesh( D3dsFile.ERoad1,   ShooterTexture.ERoad1,  new LibVertex(  50.0f,  54.0f,  0.0f   ), 90.0f ),

                    
/*
                    new ShooterMesh( D3dsFile.EAmmo,            null,                   new LibVertex(  50.0f,  50.0f,  0.0f   ) ) ,
                    new ShooterMesh( D3dsFile.EAmmo,            null,                   new LibVertex(  52.0f,  51.0f,  0.0f   ) ) ,

                    new ShooterMesh( D3dsFile.ECrackers,      new LibVertex( 0.0f, 0.0f, 1.0f )   ),
                    new ShooterMesh( D3dsFile.ECar1,       new LibVertex( 3.0f, 0.0f, 1.0f )   ),
*/
                }
            );
        }
    }
