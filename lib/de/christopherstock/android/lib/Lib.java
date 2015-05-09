/*  $Id: Lib.java 1178 2012-03-30 17:55:55Z jenetic.bytemare $
 *  =======================================================================================
 */
    package de.christopherstock.android.lib;

    import android.content.*;

    import de.christopherstock.android.lib.math.*;

    /**************************************************************************************
    *   Use final instances of this class to declare different log groups.
    *
    *   @author     Christopher Stock
    *   @version    0.1
    **************************************************************************************/
    public abstract class Lib
    {
        public static final class Offset
        {
            public      float   x   = 0;
            public      float   y   = 0;
            public      float   z   = 0;

            public Offset( float aX, float aY, float aZ )
            {
                x = aX;
                y = aY;
                z = aZ;
            }
        }

        public static enum Direction
        {
            ELeft,
            ETop,
            ERight,
            EBottom,
            ;
        }

        public static final class Rotation
        {
            public      float       x           = 0.0f;
            public      float       y           = 0.0f;
            public      float       z           = 0.0f;
            public      float       iSpeed      = 0.0f;

            public Rotation()
            {
                set( 0.0f, 0.0f, 0.0f, 0.0f );
            }

            public Rotation( float aX, float aY, float aZ )
            {
                set( aX, aY, aZ, 0.0f );
            }

            public void set( float aX, float aY, float aZ, float aSpeed )
            {
                x = aX;
                y = aY;
                z = aZ;
                iSpeed = aSpeed;
            }

            public void reachToAbsolute( Rotation targetPitch, float absoluteDistance )
            {
                x = LibMath.reachToAbsolute( x, targetPitch.x, absoluteDistance );
                y = LibMath.reachToAbsolute( y, targetPitch.y, absoluteDistance );
                z = LibMath.reachToAbsolute( z, targetPitch.z, absoluteDistance );
            }

            public Rotation copy()
            {
                return new Rotation( x, y, z );
            }

            public boolean equal( Rotation r )
            {
                return ( r.x == x && r.y == y && r.z == z );
            }

            public boolean equalRounded( Rotation other )
            {
                return
                (
                        Math.round( x ) == Math.round( other.x )
                    &&  Math.round( y ) == Math.round( other.y )
                    &&  Math.round( z ) == Math.round( other.z )
                );
            }

            @Override
            public String toString()
            {
                return "[" + x + "," + y + "," + z + "]";
            }
        }

        /**************************************************************************************
        *   Represents a camera adjustment in 3d space.
        *
        *   @author     Christopher Stock
        *   @version    0.1
        **************************************************************************************/
        public static final class ViewSet
        {
            public          Offset                      pos                 = null;
            public          Rotation                    rot                 = null;

            public ViewSet( float aPosX, float aPosY, float aPosZ, float aRotX, float aRotY, float aRotZ )
            {
                pos = new Offset(   aPosX, aPosY, aPosZ );
                rot = new Rotation( aRotX, aRotY, aRotZ );
            }
        }

        public static enum Invert
        {
            EYes,
            ENo,
            ;
        }

        public static enum Scalation
        {
            ELowerThreeQuarters(    0.25f   ),
            ELowerTwoThirds(        0.33f   ),
            ELowerHalf(             0.5f    ),
            ELowerThirds(           0.66f   ),
            ENone(                  1.0f    ),
            EAddQuarter(            1.25f   ),
            EAddThird(              1.33f   ),
            EAddHalf(               1.5f    ),
            EAddTwoThirds(          1.66f   ),
            EAddThreeQuarters(      1.75f   ),
            EDouble(                2.0f    ),
            ETriple(                3.0f    ),
            EQuadruple(             4.0f    ),
            EQuintuple(             5.0f    ),
            ESextuple(              6.0f    ),
            ESeptuple(              7.0f    ),
            EOctuple(               8.0f    ),
            ENinefold(              9.0f    ),
            EDecuple(               10.0f   ),
            ;

            private         float           iScaleFactor            = 0.0f;

            private Scalation( float aScaleFactor )
            {
                iScaleFactor = aScaleFactor;
            }

            public final float getScaleFactor()
            {
                return iScaleFactor;
            }
        }

        public enum LibTransformationMode
        {
            EOriginalsToOriginals,
            EOriginalsToTransformed,
            ETransformedToTransformed,
            ;
        }

        public static enum LibAnimation
        {
            EAnimationNone,
            EAnimationShow,
            EAnimationHide,
            ;
        }

        public static enum ParticleQuantity
        {
            ETiny,
            ELow,
            EMedium,
            EHigh,
            EMassive,
            ;
        }

        public          static  final   int             MILLIS_PER_SECOND           = 1000;
        public          static  final   int             MILLIS_PER_MINUTE           = 60 * MILLIS_PER_SECOND;
        public          static  final   int             MILLIS_PER_HOUR             = 60 * MILLIS_PER_MINUTE;

        public static  final   int             JAR_BUFFER_SIZE             = 0xffff;

        public static final void delay( long millis )
        {
            try { Thread.sleep( millis ); } catch ( InterruptedException ie ) {}
        }
/*
        public static final Typeface createFont( String filename, float size ) throws Throwable
        {
            return Typeface.create( Typeface.DEFAULT, LibIO.preStreamJarResource( filename ) ).deriveFont( size );
        }
*/
        /*********************************************************************************
        *   Delivers the Android-resource-id for a raw-resource that has been saved under
        *   res/raw.
        *
        *   @param  context     The current application context.
        *   @param  name        The name of the raw file.
        *   @return             The unique Android-resource-id of the raw-resource.
        *********************************************************************************/
        public static final int getResourceRawID( Context context, String name )
        {
            return getResourceIdentifier( context, "raw", name );
        }

        /*********************************************************************************
        *   Delivers the Android-resource-id for the specified item in the
        *   specified resource group.
        *
        *   @param  context     The current application context.
        *   @param  group       The name of the resource group ( e.g. 'drawable', 'raw', 'layout' ).
        *   @param  name        The name of the resource.
        *                       This usually is the name of the file.
        *   @return             The unique Android-resource-id of the resource.
        *********************************************************************************/
        private static final int getResourceIdentifier( Context context, String group, String name )
        {
            return context.getResources().getIdentifier( name, group, context.getPackageName() );
        }
    }
