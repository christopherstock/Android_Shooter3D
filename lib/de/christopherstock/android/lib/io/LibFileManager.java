/*  $Id: LibFileManager.java 34142 2011-10-13 15:29:47Z schristopher $
 *  ==============================================================================================================
 */
    package de.christopherstock.android.lib.io;

    import  java.io.*;

    import de.christopherstock.android.lib.*;
    import  android.content.*;

    /************************************************************************
    *   Represents a system to manage files on the device's filesystem.
    *   All filenames MUST NOT contain any path-separator.
    *
    *   @author     $Author: schristopher $
    *   @version    $Rev: 34142 $ $Date: 2011-10-13 17:29:47 +0200 (Do, 13 Okt 2011) $
    *   @see        "$URL: http://svn.synapsy.net/svn/Synapsy/odp/client/android/DevImpl/trunk/lib/src/com/synapsy/odpandroid/lib/io/LibFileManager.java $"
    ************************************************************************/
    public class LibFileManager
    {
        /************************************************************************
        *   Streams all bytes from the specified file.
        *
        *   @param  fileName    The filename of the file to return all bytes from.
        *   @return             A byte-array with all bytes from the specified file.
        *                       The byte-array is empty if an error occured on reading the file.
        ************************************************************************/
        public static final byte[] readFile( String fileName, Context iContext )
        {
            try
            {
              //ShooterDebug.bugfix.out( "read file [" + fileName + "]" );
                int id = Lib.getResourceRawID( iContext, fileName );
              //ShooterDebug.bugfix.out( "id [" + id + "]" );
                InputStream is = iContext.getResources().openRawResource( id );
              //ShooterDebug.bugfix.out( "is [" + is + "]" );

                byte[] ret = LibIO.readStreamBuffered( is );
                is.close();

                return ret;
            }
            catch ( Exception e )
            {
                return new byte[] {};
            }
        }

        /************************************************************************
        *   Writes all bytes to the specified location.
        *
        *   @param  fileName    The filename of the file to write all bytes into.
        *   @param  data        The bytes to write into the file.
        ************************************************************************/
        public final void writeFile( String fileName, byte[] data, Context iContext )
        {
            try
            {
                FileOutputStream fos = iContext.openFileOutput( fileName, Context.MODE_WORLD_READABLE|Context.MODE_WORLD_WRITEABLE );

                fos.write( data );
                fos.flush();
                fos.close();
            }
            catch ( Exception e )
            {
            }
        }

        /************************************************************************
        *   Deletes the file with the specified name.
        *
        *   @param  fileName    The filename of the file to delete.
        ************************************************************************/
        public final void deleteFile( String fileName, Context iContext )
        {
            try
            {
                iContext.deleteFile( fileName );
            }
            catch ( Exception e )
            {
            }
        }
    }
