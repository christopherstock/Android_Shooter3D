/*  $Id: Shooter.java 1176 2012-03-28 22:54:10Z jenetic.bytemare@gmail.com $
 *  =======================================================================================
 */
    package de.christopherstock.android.lib;

    import  android.app.*;
    import  android.content.*;
    import  android.view.*;
    import  android.view.animation.*;

    /******************************************************************************************
    *   The project's activity class.
    *
    *   @version    0.1
    *   @author     Christopher Stock
    *******************************************************************************************/
    public class LibUI
    {
        /******************************************************************************************
        *   Requests fullscreen canvas.
        *******************************************************************************************/
        public static final void requestFullscreen( Activity act, boolean showStatusBar )
        {
            act.requestWindowFeature( Window.FEATURE_NO_TITLE );
            if ( !showStatusBar ) act.getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        }

        /******************************************************************************************
        *   Being invoked when the activity is first created.
        *******************************************************************************************/
        public static final SurfaceView createSurfaceView( Context context )
        {
            SurfaceView ret = new SurfaceView( context );

            return ret;
        }

        public static final AlphaAnimation getAlphaAnimation()
        {
            AlphaAnimation ret = new AlphaAnimation( 0.0f, 1.0f );
            ret.setDuration( 3000 );
            return ret;
        }
    }
