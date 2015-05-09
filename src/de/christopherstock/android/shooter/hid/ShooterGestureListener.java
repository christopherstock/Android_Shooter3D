/*  $Id: Shooter.java 1176 2012-03-28 22:54:10Z jenetic.bytemare@gmail.com $
 *  =======================================================================================
 */
    package de.christopherstock.android.shooter.hid;

    import  de.christopherstock.android.shooter.base.*;
    import  android.util.*;
    import  android.view.*;

    /******************************************************************************************
    *   The GL canvas.
    *
    *   @version    0.1
    *   @author     Christopher Stock
    *******************************************************************************************/
    public class ShooterGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        public boolean onSingleTapUp( MotionEvent ev )
        {
            Log.d( "onSingleTapUp", ev.toString() );
            return true;
        }

        @Override
        public void onShowPress( MotionEvent ev )
        {
            Log.d( "onShowPress", ev.toString() );
        }

        @Override
        public void onLongPress( MotionEvent ev )
        {
            Log.d( "onLongPress", ev.toString() );
        }

        @Override
        public boolean onScroll( MotionEvent e1, MotionEvent e2, float distanceX, float distanceY )
        {
            Log.d( "onScroll", e1.toString() );

            //only if everything is inited
            if ( ShooterInit.allDone() )
            {            
                ShooterLevel.currentPlayer().addScrollEvent( new ShooterScrollEvent( distanceX, distanceY ) );
            }    
                
            return true;
        }

        @Override
        public boolean onDown( MotionEvent ev )
        {
            Log.d( "onDownd", ev.toString() );
            return true;
        }

        @Override
        public boolean onFling( MotionEvent e1, MotionEvent e2, float velocityX, float velocityY )
        {
            Log.d( "d", e1.toString() );
            Log.d( "e2", e2.toString() );
            return true;
        }
    }
