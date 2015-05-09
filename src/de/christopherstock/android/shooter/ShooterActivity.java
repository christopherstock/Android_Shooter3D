/*  $Id: Shooter.java 1176 2012-03-28 22:54:10Z jenetic.bytemare@gmail.com $
 *  =======================================================================================
 */
    package de.christopherstock.android.shooter;

    import  de.christopherstock.android.lib.*;
    import  de.christopherstock.android.shooter.g3d.*;
    import  android.app.Activity;
    import  android.content.res.*;
    import  android.os.Bundle;
    import  android.util.*;

    /******************************************************************************************
    *   The project's activity class contains the app's point of entry in {@link #onCreate(Bundle)}.
    *
    *   @version    0.1
    *   @author     Christopher Stock
    *******************************************************************************************/
    public class ShooterActivity extends Activity
    {
        public      static          ShooterGLCanvas         canvasGL            = null;
        public      static          ShooterActivity         singleton           = null;

        /******************************************************************************************
        *   Being invoked when the activity is first created.
        *******************************************************************************************/
        @Override
        public void onCreate( Bundle savedInstanceState )
        {
            //invoke super method
            super.onCreate( savedInstanceState );

            //reference singleton instance
            singleton = this;

            //fullscreen please
            LibUI.requestFullscreen( this, false );

            //hide system overlay shadow
            setTheme( R.style.shooterDroid );

            //create glCanvas and assign renderer
            canvasGL = new ShooterGLCanvas( this );
            canvasGL.setRenderer( new ShooterGLRenderer() );

            //set glCanvas as content view
            setContentView( canvasGL );
        }

        @Override
        protected void onPause()
        {
            super.onPause();
            canvasGL.onPause();
        }

        @Override
        protected void onResume()
        {
            super.onResume();
            canvasGL.onResume();
        }

        @Override
        public void onConfigurationChanged( Configuration newConfig )
        {
            super.onConfigurationChanged( newConfig );
            Log.i( "info", "onConfigurationChanged" );
        }

        @Override
        public void onWindowFocusChanged( boolean hasFocus )
        {
            super.onWindowFocusChanged( hasFocus );
            Log.i( "info", "onWindowFocusChanged [" + hasFocus + "]" );
        }
/*
        @Override
        public boolean onKeyDown( int i, KeyEvent ke )
        {
            //Lib.DEBUG_OUT( "key down: " + ke.getKeyCode() );

            switch ( ke.getKeyCode() )
            {
                case KeyEvent.KEYCODE_DPAD_UP:
                case KeyEvent.KEYCODE_W:
                {
                    ShooterLevel.current.iPlayer.iPos.x = ShooterLevel.current.iPlayer.iPos.x - LibMath.sinDeg( ShooterLevel.current.iPlayer.iRot.z ) * Player.SPEED_WALKING;
                    ShooterLevel.current.iPlayer.iPos.y = ShooterLevel.current.iPlayer.iPos.y - LibMath.cosDeg( ShooterLevel.current.iPlayer.iRot.z ) * Player.SPEED_WALKING;

                    break;
                }

                case KeyEvent.KEYCODE_DPAD_DOWN:
                case KeyEvent.KEYCODE_S:
                {
                    ShooterLevel.current.iPlayer.iPos.x = ShooterLevel.current.iPlayer.iPos.x + LibMath.sinDeg( ShooterLevel.current.iPlayer.iRot.z ) * Player.SPEED_WALKING;
                    ShooterLevel.current.iPlayer.iPos.y = ShooterLevel.current.iPlayer.iPos.y + LibMath.cosDeg( ShooterLevel.current.iPlayer.iRot.z ) * Player.SPEED_WALKING;
                    break;
                }

                case KeyEvent.KEYCODE_A:
                {
                    ShooterLevel.current.iPlayer.iPos.x = ShooterLevel.current.iPlayer.iPos.x + LibMath.sinDeg( ShooterLevel.current.iPlayer.iRot.z - 90.0f ) * Player.SPEED_STRAFING;
                    ShooterLevel.current.iPlayer.iPos.y = ShooterLevel.current.iPlayer.iPos.y + LibMath.cosDeg( ShooterLevel.current.iPlayer.iRot.z - 90.0f ) * Player.SPEED_STRAFING;
                    break;
                }

                case KeyEvent.KEYCODE_D:
                {
                    ShooterLevel.current.iPlayer.iPos.x = ShooterLevel.current.iPlayer.iPos.x + LibMath.sinDeg( ShooterLevel.current.iPlayer.iRot.z + 90.0f ) * Player.SPEED_STRAFING;
                    ShooterLevel.current.iPlayer.iPos.y = ShooterLevel.current.iPlayer.iPos.y + LibMath.cosDeg( ShooterLevel.current.iPlayer.iRot.z + 90.0f ) * Player.SPEED_STRAFING;
                    break;
                }

                case KeyEvent.KEYCODE_DPAD_LEFT:
                {
                    ShooterLevel.current.iPlayer.iRot.z += Player.SPEED_TURNING;
                    break;
                }

                case KeyEvent.KEYCODE_DPAD_RIGHT:
                {
                    ShooterLevel.current.iPlayer.iRot.z -= Player.SPEED_TURNING;
                    break;
                }
            }

            return true;
        }
*/
    }
