/*  $Id: Shooter.java 1176 2012-03-28 22:54:10Z jenetic.bytemare@gmail.com $
 *  =======================================================================================
 */
    package de.christopherstock.android.shooter.hid;

    /******************************************************************************************
    *   The GL canvas.
    *
    *   @version    0.1
    *   @author     Christopher Stock
    *******************************************************************************************/
    public class ShooterScrollEvent
    {
        public              float       iDistX              = 0.0f;
        public              float       iDistY              = 0.0f;

        public ShooterScrollEvent( float aDistX, float aDistY )
        {
            iDistX = aDistX;
            iDistY = aDistY;
        }
    }
