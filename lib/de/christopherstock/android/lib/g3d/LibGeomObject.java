/*  $Id: LibGeomObject.java 1176 2012-03-28 22:54:10Z jenetic.bytemare@gmail.com $
 *  =======================================================================================
 */
    package de.christopherstock.android.lib.g3d;

    import  de.christopherstock.android.lib.Lib.LibTransformationMode;

    /**************************************************************************************
    *   Represents a cylinder.
    *
    *   @author     Christopher Stock
    *   @version    0.1
    **************************************************************************************/
    public interface LibGeomObject
    {
        public abstract LibVertex getAnchor();
        public abstract void translate( float tX, float tY, float tZ, LibTransformationMode transformationMode );
        public abstract void setNewAnchor( LibVertex newAnchor, boolean performTranslationOnFaces, LibTransformationMode transformationMode );
    }
