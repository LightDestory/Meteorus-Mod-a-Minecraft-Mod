package meteorapegaso.meteorus.config;

import java.io.File;
import java.util.logging.Level;
import cpw.mods.fml.common.FMLLog;
import meteorapegaso.meteorus.achievements.AchievementManager;
import meteorapegaso.meteorus.blocks.MeteorusBlockHandler;
import meteorapegaso.meteorus.common.mod_meteorus;
import meteorapegaso.meteorus.items.MeteorusItemHandler;
import meteorapegaso.meteorus.util.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;

public class MeteorusConfiguration extends mod_meteorus {
	//Booleans
	public static boolean enableAutoCheckkUpdate;
	public static final boolean enableAutoCheckkUpdate_default = true;
	public static final String enableAutoCheckkUpdate_name = "Enable Auto Check Update?";
	//Config Start
	public static Configuration config;
    public static void preConfig(File file){
        config = new Configuration(file);
        
        try{
            config.load();
            //Blocks
            MeteorusBlockHandler.ProjectTableID = config.getBlock("Project Table IDs", 3000).getInt();
            MeteorusBlockHandler.ColoredStonesID = config.getBlock("Colored Stones IDs ***This will the id of the sub blocks of this!***", 3001).getInt();
            MeteorusBlockHandler.ColoredCobblestonesID = config.getBlock("Colored Cobblestones IDs ***This will the id of the sub blocks of this!***", 3002).getInt();
            MeteorusBlockHandler.ColoredStoneBricksID = config.getBlock("Colored Stone Bricks IDs ***This will the id of the sub blocks of this!***", 3003).getInt();
            MeteorusBlockHandler.ColoredStoneCarvedBricksID = config.getBlock("Colored Stone Carved Bricks ID ***This will the id of the sub blocks of this!***", 3004).getInt();
            MeteorusBlockHandler.ColoredStoneCrackedBricksID = config.getBlock("Colored Stone Cracked Bricks ID ***This will the id of the sub blocks of this!***", 3005).getInt();
            MeteorusBlockHandler.ColoredCobblestoneStairsID = config.getBlock("Colored Cobblestone Stairs IDs ***THIS ID IS THE ID FOR THE ONE STAIRS! THIS ID MUST HAVE THE FOLLOWING FREE 13 ID (example if you set 5 the ids from 5 to 18 should be free", 3006).getInt();
            MeteorusBlockHandler.ColoredGlowstonesID = config.getBlock("Colored Glowstones ID ***This will the id of the sub blocks of this!***", 3020).getInt();
            MeteorusBlockHandler.ColoredGlowstoneNotActiveLampsID = config.getBlock("Colored Glowstone Not Active Lamps ID ***This will the id of the sub blocks of this!***", 3021).getInt();
            MeteorusBlockHandler.ColoredGlowstoneActiveLampsID = config.getBlock("Colored Glowstone Active Lamps ID ***This will the id of the sub blocks of this!***", 3022).getInt();
            //Items    
            MeteorusItemHandler.ProjectCarvedStoneID = config.getItem("Project of Carved Stone IDs", 3100).getInt();
            MeteorusItemHandler.ChiselID = config.getItem("Chisel IDs", 3101).getInt();
            MeteorusItemHandler.HammerID = config.getItem("Hammer IDs", 3102).getInt();
            MeteorusItemHandler.ColoredGlowstoneDustsID = config.getItem("Colored Glowstone Dusts IDs", 3103).getInt();
            //Achievements
            AchievementManager.TIME_OF_DECORATIONSID = config.get("Achievements", "Time of Decorations ID", 546).getInt();
            AchievementManager.BRICKS_COLORSID = config.get("Achievements", "More Brick ID", 547).getInt();
            AchievementManager.PROJECT_TABLEID = config.get("Achievements", "ProjectTable ID", 548).getInt();
            AchievementManager.BRICKS_CHISELEDID = config.get("Achievements", "More Chiseled Brick ID", 549).getInt();
            //Booleans Config
            enableAutoCheckkUpdate = config.get("Booleans Configuration", enableAutoCheckkUpdate_name, enableAutoCheckkUpdate_default).getBoolean(enableAutoCheckkUpdate_default);
        }
        catch(Exception e){
            FMLLog.log(Level.SEVERE, e, Reference.MOD_ID + "Has a problem loading the config file!.Please delete it!");
            System.err.println("Has a problem loading the config file!.Please delete it!");
        }
        finally{
            config.save();
        }
    }
  //Config End
}

