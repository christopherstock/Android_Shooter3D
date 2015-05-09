/*  $Id: LibGLView.java 1182 2012-03-30 21:49:17Z jenetic.bytemare@gmail.com $
 *  =======================================================================================
 */
    package de.christopherstock.android.lib.g3d;

    /**************************************************************************************
    *
    *
    *   @author         Christopher Stock
    *   @version        0.1
    **************************************************************************************/
    public interface LibGLFace
    {
        public  abstract    LibVertex       getAnchor();
        public  abstract    float           getAlpha();
        public  abstract    float           getDarken();
        public  abstract    int             getColor();
        public  abstract    float[]         getColor3f();
        public  abstract    LibVertex       getFaceNormal();
        public  abstract    LibGLTexture    getTexture();
        public  abstract    LibVertex[]     getVerticesToDraw();
    }
