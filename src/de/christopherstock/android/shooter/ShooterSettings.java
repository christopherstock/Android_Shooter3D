/*  $Id: ShooterD3ds.java 1179 2012-03-30 19:18:53Z jenetic.bytemare $
 *  =================================================================================
 */
    package de.christopherstock.android.shooter;

    import  de.christopherstock.android.lib.ui.*;

    /********************************************************************************
    *   TODO PRUNE!
    *
    *   @author     Christopher Stock
    *   @version    0.1
    ********************************************************************************/
    public class ShooterSettings
    {
        public static class Player
        {
            public      static  final   float           SPEED_MAX_TURNING_PER_TICK          = 10.0f;
            
            public      static  final   float           SPEED_WALKING                       = 0.15f;
            public      static  final   float           SPEED_STRAFING                      = 0.1f;
        }

        public static class GL
        {

            public      static  final   float           VIEW_MIN                    = 0.01f;
            public      static  final   float           VIEW_MAX                    = 100.0f;
        }
        
        public static class Colors
        {
            public      static  final   LibColors       EAvatarMessageText                  = LibColors.EWhite;
            public      static  final   LibColors       EAvatarMessageTextOutline           = LibColors.EGreyDark;
            public      static  final   LibColors       EFpsFg                              = LibColors.EWhite;
            public      static  final   LibColors       EFpsOutline                         = LibColors.EGreyDark;
            public      static  final   LibColors       EAmmoFg                             = LibColors.EWhite;
            public      static  final   LibColors       EAmmoOutline                        = LibColors.EGreyDark;

            public      static  final   LibColors       EHealthFgNormal                     = LibColors.EWhite;
            public      static  final   LibColors       EHealthFgWarning                    = LibColors.ERed;

            public      static  final   LibColors       EHealthOutline                      = LibColors.EGreyDark;
            public      static  final   LibColors       EHudMsgFg                           = LibColors.EWhite;
            public      static  final   LibColors       EHudMsgOutline                      = LibColors.EBlack;

            public      static  final   LibColors       EAvatarMessagePanelBgRed            = LibColors.ERedLight;
            public      static  final   LibColors       EAvatarMessagePanelBgYellow         = LibColors.EYellow;
            public      static  final   LibColors       EAvatarMessagePanelBgGrey           = LibColors.EGreyLight;
            public      static  final   LibColors       EAvatarMessagePanelBgBlack          = LibColors.EBlack;
            public      static  final   LibColors       EAvatarMessagePanelBgGreen          = LibColors.EGreenLight;
            public      static  final   LibColors       EAvatarMessagePanelBgBlue           = LibColors.EBlueLight;

            public      static  final   LibColors[]     SLIVER_COLOR_WALL           = new LibColors[]
            {
                LibColors.EExplosion1,  LibColors.EExplosion2,  LibColors.EExplosion3,
                LibColors.EExplosion4,  LibColors.EExplosion5,  LibColors.EExplosion6,
                LibColors.EExplosion7,  LibColors.EExplosion8,  LibColors.EExplosion9,
                LibColors.EExplosion10, LibColors.EExplosion11, LibColors.EExplosion12,
            };

            public      static  final   LibColors[]     SLIVER_COLOR_RED_BRICKS     = new LibColors[]
            {
                LibColors.ESliverBricks1,  LibColors.ESliverBricks2,  LibColors.ESliverBricks3,
                LibColors.ESliverBricks4,  LibColors.ESliverBricks5,
            };

            public      static  final   LibColors[]     SLIVER_COLOR_BROWN_BRICKS   = new LibColors[]
            {
                LibColors.EBrown, LibColors.EBrownDark, LibColors.EBrownLight,
            };

            public      static  final   LibColors[]     SLIVER_COLOR_BLOOD          = new LibColors[]
            {
                LibColors.ESliverBlood1,  LibColors.ESliverBlood2,  LibColors.ESliverBlood3,
            };

            public      static  final   LibColors[]     SLIVER_COLOR_GLASS          = new LibColors[]
            {
                LibColors.ESliverGlass1,  LibColors.ESliverGlass2,  LibColors.ESliverGlass3,
            };

            public      static  final   LibColors[]     SLIVER_COLOR_STEEL          = new LibColors[]
            {
                LibColors.ESliverSteel1,  LibColors.ESliverSteel2,  LibColors.ESliverSteel3,
            };
        }
    }
