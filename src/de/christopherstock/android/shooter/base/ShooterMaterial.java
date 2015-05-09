/*  $Id: ShooterMaterial.java 1176 2012-03-28 22:54:10Z jenetic.bytemare@gmail.com $
 *  =======================================================================================
 */
    package de.christopherstock.android.shooter.base;

    import     de.christopherstock.android.lib.ui.*;
import de.christopherstock.android.shooter.ShooterSettings.Colors;

    /**************************************************************************************
    *   Different materials that can be assigned for texture surfaces.
    *
    *   @author     Christopher Stock
    *   @version    0.1
    **************************************************************************************/
    public enum ShooterMaterial
    {
        EBrownBricks(       false,    Colors.SLIVER_COLOR_BROWN_BRICKS /* ,    ShooterSound.EWallSolid1    */ ),
        EConcrete(          false,    Colors.SLIVER_COLOR_WALL         /* ,    ShooterSound.EWallSolid1    */ ),
        EElectricDevice(    true ,    Colors.SLIVER_COLOR_GLASS        /* ,    ShooterSound.EWallElectric1 */ ),
        EGlass(             true ,    Colors.SLIVER_COLOR_GLASS        /* ,    ShooterSound.EWallGlass1    */ ),
        EHumanFlesh(        false,    Colors.SLIVER_COLOR_BLOOD        /* ,    ShooterSound.EWallFlesh1    */ ),
        EPlastic1(          false,    Colors.SLIVER_COLOR_GLASS        /* ,    ShooterSound.EWallSolid1    */ ),
        ERedBricks(         false,    Colors.SLIVER_COLOR_RED_BRICKS   /* ,    ShooterSound.EWallSolid1    */ ),
        ESteel1(            false,    Colors.SLIVER_COLOR_STEEL        /* ,    ShooterSound.EWallSolid1    */ ),
        ESteel2(            false,    Colors.SLIVER_COLOR_STEEL        /* ,    ShooterSound.EWallSolid1    */ ),
        EUndefined(         false,    Colors.SLIVER_COLOR_WALL         /* ,    ShooterSound.EWallSolid1    */ ),
        EWood(              false,    Colors.SLIVER_COLOR_WALL         /* ,    ShooterSound.EWallWood1     */ ),
        ;

        public          boolean         iPenetrable     = false;
        public          LibColors[]     iSliverColors   = null;
      //public          ShooterSound    iBulletImpactSound    = null;

        private ShooterMaterial( boolean aPenetrable, LibColors[] aSliverColors /* , ShooterSound aBulletSound */ )
        {
            iPenetrable             = aPenetrable;
            iSliverColors           = aSliverColors;
            //iBulletImpactSound      = aBulletSound;
        }

        /**************************************************************************************
        *   Don't move to the constructor - Circle reference in {@link BulletHole}.
        **************************************************************************************/
/*
        public BulletHole getBulletHoleTexture()
        {
            switch ( this )
            {
                case EElectricDevice:   return BulletHole.EBulletHoleGlass1;
                case EBrownBricks:      return BulletHole.EBulletHoleBrownBricks1;
                case EConcrete:         return BulletHole.EBulletHoleConcrete1;
                case EGlass:            return BulletHole.EBulletHoleGlass1;
                case EHumanFlesh:       return BulletHole.EBulletHoleSteel1;
                case EPlastic1:         return BulletHole.EBulletHolePlastic1;
                case ERedBricks:        return BulletHole.EBulletHoleConcrete1;
                case ESteel1:           return BulletHole.EBulletHoleSteel1;
                case ESteel2:           return BulletHole.EBulletHoleSteel2;
                case EUndefined:        return BulletHole.EBulletHoleSteel1;
                case EWood:             return BulletHole.EBulletHoleWood1;
            }

            return null;
        }
*/
    }
