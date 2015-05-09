/*  $Id: LibDebug.java 911 2012-01-29 18:51:55Z jenetic.bytemare $
 *  =======================================================================================
 */
    package de.christopherstock.android.lib;

    /*********************************************************************************
    *   This class performs a threaded http-connection.
    *********************************************************************************/
    public class LibDebug
    {
        private         boolean         iDebug              = false;

        public LibDebug( boolean aDebug )
        {
            iDebug = aDebug;
        }

        /*********************************************************************************
        *   An output being logged if this debug group is enabled.
        *********************************************************************************/
        public void out( Object msg )
        {
            if ( iDebug ) System.out.println( msg );
        }

        /*********************************************************************************
        *   An output being logged UNCONDITIONAL.
        *********************************************************************************/
        public void err( Object msg )
        {
            if ( iDebug ) System.err.println( msg );
        }

        /*********************************************************************************
        *   A stacktrace being logged if this debug group is enabled.
        *********************************************************************************/
        public void trace( Throwable msg )
        {
            if ( iDebug ) msg.printStackTrace();
        }

        /*********************************************************************************
        *   Displays memory info.
        *********************************************************************************/
        public void mem()
        {
            //if ( iDebug )
        }
    }
