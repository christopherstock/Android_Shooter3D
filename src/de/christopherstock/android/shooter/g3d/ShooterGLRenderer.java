/*  $Id: Shooter.java 1176 2012-03-28 22:54:10Z jenetic.bytemare@gmail.com $
 *  =======================================================================================
 */
    package de.christopherstock.android.shooter.g3d;

    import  javax.microedition.khronos.egl.*;
    import  javax.microedition.khronos.opengles.*;
    import  de.christopherstock.android.lib.g3d.LibGL;
    import  de.christopherstock.android.lib.ui.LibAnchor;
    import  de.christopherstock.android.shooter.*;
    import  de.christopherstock.android.shooter.ShooterSettings.GL;
    import  de.christopherstock.android.shooter.base.*;
    import  android.opengl.*;

    /******************************************************************************************
    *   The GL renderer.
    *
    *   @version    0.1
    *   @author     Christopher Stock
    *******************************************************************************************/
    public class ShooterGLRenderer implements GLSurfaceView.Renderer
    {
        private                     int             width                       = 0;
        private                     int             height                      = 0;

        @Override
        public void onSurfaceCreated( GL10 gl, EGLConfig config )
        {
            //ShooterDebug.bugfix.out( ">>> onSurfaceCreated" );

            gl.glShadeModel( GL10.GL_SMOOTH );                                  //enable Smooth Shading
            gl.glClearDepthf( 1.0f );                                           //depth buffer setup
            gl.glEnable( GL10.GL_DEPTH_TEST );                                  //enable depth testing
            gl.glDepthFunc( GL10.GL_LEQUAL );                                   //type of depth testing
            gl.glHint( GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST );   //really nice perspective corrections
            gl.glDisable( GL10.GL_DITHER );                                     //disable dithering for better performance
        }

        @Override
        public void onDrawFrame( GL10 gl )
        {
            ShooterDebug.init.out( "onDrawFrame" );
            
            //check keys
            if ( ShooterInit.allDone() )
            {
                ShooterLevel.currentPlayer().handleGestures();
            }

            //draw bg


            //draw scene
            onSurfacePerspective( gl );
            drawScene(            gl );

            //draw screen
            onSurfaceOrtho(       gl );
            drawScreen(           gl );
        }

        @Override
        public void onSurfaceChanged( GL10 gl, int newWidth, int newHeight )
        {
            //ShooterDebug.bugfix.out( ">>>>> onSurfaceChanged" );

            width  = newWidth;
            height = newHeight;

            // Sets the current view port to the new size:
            gl.glViewport( 0, 0, width, height );       //set viewport
            
            //init all
            ShooterInit.initAll( gl );
        }

        public void onSurfaceOrtho( GL10 gl )
        {
            gl.glMatrixMode( GL10.GL_PROJECTION );
            gl.glLoadIdentity();
          //gl.glOrthof( 0.0f, 100.0f, 0.0f, 100.0f, -0.1f, 0.1f );

            GLU.gluOrtho2D( gl, 0, width, 0, height );

            gl.glOrthox( 0, width, 0, height, 0, 0 );
            gl.glMatrixMode( GL10.GL_MODELVIEW );
            gl.glLoadIdentity();
        }

        public void onSurfacePerspective( GL10 gl )
        {
            gl.glMatrixMode( GL10.GL_PROJECTION );
            gl.glLoadIdentity();

            GLU.gluPerspective( gl, 45.0f, (float) width / (float) height, GL.VIEW_MIN, GL.VIEW_MAX );  //calc the aspect ratio of the window:

            gl.glMatrixMode( GL10.GL_MODELVIEW );
            gl.glLoadIdentity();
        }
        
        private final void drawScene( GL10 gl )
        {
            //check init state
            if ( ShooterInit.allDone() )
            {
                //clear screen
                gl.glClearColor( 0.0f, 0.0f, 0.0f, 1.0f );                          
                gl.glClear( GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT );    
                gl.glLoadIdentity();                                                
                
                //normalize rotation and translation then rotate and translate
                gl.glNormal3f( 0.0f, 0.0f, 0.0f );                                  
                gl.glRotatef( ShooterLevel.currentPlayer().iRot.y, 0.0f, 0.0f, 1.0f );               
                gl.glRotatef( ShooterLevel.currentPlayer().iRot.x, 1.0f, 0.0f, 0.0f );               
                gl.glRotatef( ( 360.0f - ShooterLevel.currentPlayer().iRot.z ), 0.0f, 1.0f, 0.0f );               
                gl.glTranslatef( -ShooterLevel.currentPlayer().iPos.x, -ShooterLevel.currentPlayer().iPos.z, -ShooterLevel.currentPlayer().iPos.y );

                //draw level
                ShooterLevel.current().draw( gl );

                //animate level
                ShooterLevel.current().animate();
            }
            else
            {
                //clear screen
                gl.glClearColor( 1.0f, 1.0f, 1.0f, 1.0f );                          
                gl.glClear( GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT );    
                gl.glLoadIdentity();                                                



            }
        }

        private final void drawScreen( GL10 gl )
        {
            //check init state
            if ( ShooterInit.allDone() )
            {
/*                
                LibGL.drawBitmap( gl, 20, 40, ShooterTexture.ECrackers, LibAnchor.ELeftBottom );
                LibGL.drawBitmap( gl, 250, 50, ShooterTexture.EAmmo, LibAnchor.ELeftBottom );
*/                
                
                
            }
            else
            {
                //draw 'driving park' logo
                LibGL.drawBitmap( gl, width / 2, height / 2, ShooterTexture.ELogoDrivingPark, LibAnchor.ECenterMiddle );




            }
        }
    }
