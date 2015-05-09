/*  $Id: ShooterD3ds.java 1179 2012-03-30 19:18:53Z jenetic.bytemare $
 *  =================================================================================
 */
    package de.christopherstock.android.shooter.base;

    import  javax.microedition.khronos.opengles.GL10;
    import  de.christopherstock.android.shooter.ShooterActivity;
    import  de.christopherstock.android.shooter.ShooterDebug;

    /********************************************************************************
    *
    *
    *   @author     Christopher Stock
    *   @version    0.1
    ********************************************************************************/
    public enum ShooterInit
    {
        EFirstStart,
        EPreloading,
        EDone,
        ;

        private     static      ShooterInit     initState           = EFirstStart;

        public static final void initAll( GL10 gl )
        {
            
            //if ( true ) return;
            
            
            //init gl textures non-threaded
            ShooterDebug.init.out( "init textures" );
            ShooterTexture.init( gl, ShooterActivity.singleton );

            
            //only init if nothing is inited
            if ( initState == EFirstStart )
            {
                initState = EPreloading;
                
                //load textures non-threaded!
                ShooterDebug.init.out( "START inits" );

                //init the rest in a thread
                initThreaded();
            }
        }

        private static final void initThreaded()
        {
            new Thread()
            {
                @Override
                public void run()
                {
//                    if ( true ) return;
                    
                    //setPriority( Thread.MIN_PRIORITY );
                    init();
                }
            }.start();
        }

        protected static final void init()
        {
            ShooterDebug.init.out( "init 3D models" );
            
            //init 3ds models
            ShooterD3ds.init( ShooterDebug.d3ds );

            ShooterDebug.init.out( "init levels" );
            
            //init level
            ShooterLevel.init();

            ShooterDebug.init.out( "END inits" );
            
            //start game by setting the init state to 'done'
            initState = EDone;
        }

        public static final boolean allDone()
        {
            return ( initState == EDone );
        }
    }
