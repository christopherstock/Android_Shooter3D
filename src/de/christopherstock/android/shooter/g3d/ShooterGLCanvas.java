/*  $Id: Shooter.java 1176 2012-03-28 22:54:10Z jenetic.bytemare@gmail.com $
 *  =======================================================================================
 */
    package de.christopherstock.android.shooter.g3d;

    import  de.christopherstock.android.shooter.hid.*;
    import  android.content.*;
    import  android.opengl.*;
    import  android.view.*;

    /******************************************************************************************
    *   The GL canvas.
    *
    *   @version    0.1
    *   @author     Christopher Stock
    *******************************************************************************************/
    public class ShooterGLCanvas extends GLSurfaceView
    {
        public      static      GestureDetector     gestures            = null;

        public ShooterGLCanvas( Context context )
        {
            super( context );

            //assign gesture listener
            gestures = new GestureDetector( context, new ShooterGestureListener() );
        }

        @Override
        public boolean onTouchEvent( MotionEvent me )
        {
            return gestures.onTouchEvent( me );
        }
    }
