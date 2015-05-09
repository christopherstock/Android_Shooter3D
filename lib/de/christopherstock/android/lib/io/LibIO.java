/*  $Id: LibIO.java 1176 2012-03-28 22:54:10Z jenetic.bytemare@gmail.com $
 *  =======================================================================================
 */
    package de.christopherstock.android.lib.io;

    import  java.io.*;
    import  java.nio.IntBuffer;
    import  java.util.*;
    import  android.graphics.Bitmap;
    import  android.graphics.Bitmap.Config;

    /**************************************************************************************
    *   The GL-View.
    *
    *   @author     Christopher Stock
    *   @version    0.1
    **************************************************************************************/
    public abstract class LibIO
    {
        public static final Bitmap flipVert( Bitmap bi )
        {
            int[]  pixels      = new int[ bi.getWidth() * bi.getHeight() ];
            bi.getPixels( pixels, 0, bi.getWidth(), 0, 0, bi.getWidth(), bi.getHeight() );

            //flip pixels vert
            IntBuffer out = IntBuffer.allocate( pixels.length );
            for ( int i = pixels.length - bi.getWidth(); i >= 0; i -= bi.getWidth() )
            {
                for ( int j = i; j < i + bi.getWidth(); ++j )
                {
                    out.put( pixels[ j ] );
                }
            }

            //assign return bitmap
            Bitmap ret = Bitmap.createBitmap( bi.getWidth(), bi.getHeight(), Config.ARGB_8888 );
            ret.setPixels( out.array(), 0, bi.getWidth(), 0, 0, bi.getWidth(), bi.getHeight() );

            return ret;
        }

        /*****************************************************************************************//**
        *   Reads the given {@link InputStream} buffered and returns all read bytes as a byte-array.
        *
        *   @param  is      The InputStream to read buffered.
        *   @return         All bytes of the given InputStream as a byte-array.
        *********************************************************************************************/
        public static byte[] readStreamBuffered( InputStream is )
        {
            ByteArrayOutputStream   baos        = new ByteArrayOutputStream();
            int                     byteread    = 0;

            try
            {
                //read one byte after another until the EOF-flag is returned
                while ( ( byteread = is.read() ) != -1 )
                {
                    //write this byte, if it could be read, into the output-stream
                    baos.write( byteread );
                }

                is.close();
                baos.close();

                //return the output-stream as a byte-array
                return baos.toByteArray();
            }
            catch ( Exception e )
            {
                try
                {
                    is.close();
                    baos.close();
                }
                catch ( Exception u )
                {
                    //ignore exceptions
                }
                e.printStackTrace();
                return null;
            }
        }

        public static final void saveObjects( String filename, Vector<Object> objectsToSave ) throws Throwable
        {
            FileOutputStream   fos = new FileOutputStream(   filename   );
            ObjectOutputStream oos = new ObjectOutputStream( fos        );

            for ( Object o : objectsToSave )
            {
                oos.writeObject( o );
            }

            oos.close();
        }

        public static final Object[] loadObjects( String filename ) throws Throwable
        {
            Vector<Object>     ret = new Vector<Object>();
            FileInputStream    fis = new FileInputStream(    filename   );
            ObjectInputStream  ois = new ObjectInputStream(  fis        );

            try
            {
                //isn't there an other way?
                while ( true )
                {
                    Object o = ois.readObject();
                    ret.add( o );
                }
            }
            catch ( EOFException eof )
            {
            }

            return ret.toArray( new Object[] {} );
        }
    }
