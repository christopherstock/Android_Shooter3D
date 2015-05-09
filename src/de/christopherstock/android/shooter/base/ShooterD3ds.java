/*  $Id: ShooterD3ds.java 1179 2012-03-30 19:18:53Z jenetic.bytemare $
 *  =================================================================================
 */
    package de.christopherstock.android.shooter.base;

    import  de.christopherstock.android.lib.*;
    import  de.christopherstock.android.lib.g3d.*;
    import  de.christopherstock.android.lib.io.d3ds.*;
    import  de.christopherstock.android.lib.ui.*;
    import  de.christopherstock.android.shooter.*;
    import  de.christopherstock.android.shooter.g3d.*;

    /********************************************************************************
    *   All available Discreet 3D studio max resource files. The ordinal index
    *   of the enum constant is the filename of the ascii scene export file (.ase).
    *   Remember to set the UVW mapping for all ase files being exported
    *   otherwise the texture will not be displayed correctly.
    *
    *   @author     Christopher Stock
    *   @version    0.1
    ********************************************************************************/
    public class ShooterD3ds
    {
        public static enum D3dsFile
        {
            EAmmo,
            EFloor5x5,
            EFloor20x20,
            EFloor100x100,
            ECrackers,
            ECrate,
            ERoad1,
            ;

            public                      LibD3dsImporter            iD3dsfile                    = null;

            public final void initFile( LibD3dsImporter aD3dsfile )
            {
                iD3dsfile = aD3dsfile;
            }

            public final LibD3dsImporter getFile()
            {
                return iD3dsfile;
            }
        }

        /**************************************************************************************
        *   Returns a COPY of an imported 3dsmax mesh.
        **************************************************************************************/
        public static final ShooterTriangle[] getFaces( D3dsFile file, LibVertex anchor )
        {
            //copy original faces
            LibMaxTriangle[]    originalFaces = file.getFile().getFaces();
            ShooterTriangle[]   copiedFaces   = new ShooterTriangle[ originalFaces.length ];
            for ( int i = 0; i < copiedFaces.length; ++i )
            {
                copiedFaces[ i ] = new ShooterTriangle( anchor, originalFaces[ i ].iA, originalFaces[ i ].iB, originalFaces[ i ].iC, LibColors.ERed.argb, ShooterTexture.getFromName( originalFaces[ i ].iTextureName ) );
            }
            return copiedFaces;
        }

        /**************************************************************************************
        *   Init all 3dsmax-files.
        **************************************************************************************/
        public static final void init( LibDebug aDebug )
        {
            String prefix = "d3ds_";

            for ( D3dsFile file : D3dsFile.values() )
            {
                file.initFile( new LibD3dsImporter( prefix + file.toString().toLowerCase(), aDebug, ShooterActivity.singleton ) );
            }
        }
    }
