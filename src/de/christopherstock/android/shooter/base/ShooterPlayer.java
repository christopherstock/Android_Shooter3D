/*  $Id: Shooter.java 1176 2012-03-28 22:54:10Z jenetic.bytemare@gmail.com $
 *  =======================================================================================
 */
    package de.christopherstock.android.shooter.base;

    import     java.util.Vector;
    import  de.christopherstock.android.lib.g3d.*;
    import  de.christopherstock.android.lib.math.*;
    import     de.christopherstock.android.shooter.ShooterDebug;
    import     de.christopherstock.android.shooter.ShooterSettings.Player;
    import     de.christopherstock.android.shooter.hid.ShooterScrollEvent;

    /******************************************************************************************
    *   The player.
    *
    *   @version    0.1
    *   @author     Christopher Stock
    *******************************************************************************************/
    public class ShooterPlayer
    {
        public                          Vector<ShooterScrollEvent>         scrollEvents           = new Vector<ShooterScrollEvent>();
        public                      LibVertex                       iPos                  = null;
        public                      LibVertex                       iRot                   = null;

        public ShooterPlayer( LibVertex aPos, LibVertex aRot )
        {
            iPos = aPos;
            iRot = aRot;
        }

        public void animate()
        {
            //accelerate for walking speed
            iPos.x = iPos.x - LibMath.sinDeg( iRot.z ) * Player.SPEED_WALKING;
            iPos.y = iPos.y - LibMath.cosDeg( iRot.z ) * Player.SPEED_WALKING;
        }

        public final void handleGestures()
        {
            //only if everything is inited
            if ( ShooterInit.allDone() )
            {
                //synchronize scroll event queue
                synchronized ( scrollEvents )
                {
                    //browse scroll event queue
                    float distanceThisTick = 0.0f;
                    for ( ShooterScrollEvent se : scrollEvents )
                    {
                        ShooterDebug.gestures.out( "scroll distance: " + se.iDistX );
                        distanceThisTick += se.iDistX;
                    }
                    
                    //calc and clip delta
                    float rotDelta = distanceThisTick;
                    if ( rotDelta >  Player.SPEED_MAX_TURNING_PER_TICK ) rotDelta =  Player.SPEED_MAX_TURNING_PER_TICK;
                    if ( rotDelta < -Player.SPEED_MAX_TURNING_PER_TICK ) rotDelta = -Player.SPEED_MAX_TURNING_PER_TICK;
                    
                    //assign rotation
                    iRot.z += rotDelta;
        
                    //ditch queue
                    scrollEvents.removeAllElements();
                }
            }
        }
        
        public final void addScrollEvent( ShooterScrollEvent se )
        {
            scrollEvents.add( se );
        }
    }
