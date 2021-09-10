package meteorapegaso.meteorus.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import meteorapegaso.meteorus.achievements.AchievementManager;
import meteorapegaso.meteorus.api.jadarstudios.developercapes_modificated_for_meteorusmod_by_MeteoraPegaso.DevCapes;
import meteorapegaso.meteorus.api.jadarstudios.developercapes_modificated_for_meteorusmod_by_MeteoraPegaso.DevCapesUtil;
import meteorapegaso.meteorus.blocks.BlockColoredCobblestones;
import meteorapegaso.meteorus.blocks.BlockColoredGlowstones;
import meteorapegaso.meteorus.blocks.BlockColoredStoneBricks;
import meteorapegaso.meteorus.blocks.BlockColoredStoneCarvedBricks;
import meteorapegaso.meteorus.blocks.BlockColoredStoneCrackedBricks;
import meteorapegaso.meteorus.blocks.BlockColoredStones;
import meteorapegaso.meteorus.blocks.BlockProjectTable;
import meteorapegaso.meteorus.blocks.ColoredCobblestoneStairs;
import meteorapegaso.meteorus.blocks.MeteorusBlockHandler;
import meteorapegaso.meteorus.config.MeteorusConfiguration;
import meteorapegaso.meteorus.creativetabs.CreativeTabsMeteorusBlocks;
import meteorapegaso.meteorus.creativetabs.CreativeTabsMeteorusItems;
import meteorapegaso.meteorus.gui.GuiHandlerProjectTable;
import meteorapegaso.meteorus.itemblock.ItemBlockColoredBricks;
import meteorapegaso.meteorus.itemblock.ItemBlockColoredCarvedBricks;
import meteorapegaso.meteorus.itemblock.ItemBlockColoredCobblestones;
import meteorapegaso.meteorus.itemblock.ItemBlockColoredCrackedBricks;
import meteorapegaso.meteorus.itemblock.ItemBlockColoredGlowstones;
import meteorapegaso.meteorus.itemblock.ItemBlockColoredStones;
import meteorapegaso.meteorus.items.ItemChisel;
import meteorapegaso.meteorus.items.ItemColoredGlowstoneDust;
import meteorapegaso.meteorus.items.ItemHammer;
import meteorapegaso.meteorus.items.ItemProjectCarvedStone;
import meteorapegaso.meteorus.items.MeteorusItemHandler;
import meteorapegaso.meteorus.render.TileEntityProjectTableHandlerRenderer;
import meteorapegaso.meteorus.server.ServerProxy;
import meteorapegaso.meteorus.tileentity.TileEntityProjectTable;
import meteorapegaso.meteorus.util.AutoUpdateChecker;
import meteorapegaso.meteorus.util.CraftingHandler;
import meteorapegaso.meteorus.util.MeteorusCommands;
import meteorapegaso.meteorus.util.MeteorusLogger;
import meteorapegaso.meteorus.util.PacketHandler;
import meteorapegaso.meteorus.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.PotionHelper;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAMEc, version=Reference.MOD_VERSIONc)
@NetworkMod(clientSideRequired=true, serverSideRequired = true, channels = Reference.CHANNEL_NAME, packetHandler = PacketHandler.class)
public class mod_meteorus {
	@SidedProxy(clientSide="meteorapegaso.meteorus.client.ClientProxy", serverSide="meteorapegaso.meteorus.server.ServerProxy")
    public static ServerProxy proxy;
	@Instance(value = Reference.MOD_ID)
    public static mod_meteorus instance;
	//CreativeTabs
	public static CreativeTabs MeteorusModBlocks = new CreativeTabsMeteorusBlocks(CreativeTabs.getNextID(), "BlocksMeteorusTab", "Meteorus Mod Blocks Tab");
	public static CreativeTabs MeteorusModItems = new CreativeTabsMeteorusItems(CreativeTabs.getNextID(), "ItemMeteorusTab", "Meteorus Mod Items Tab");
	//Gui Handler
	private GuiHandlerProjectTable guiHandlerProjectTable = new GuiHandlerProjectTable();
	//CraftingHandler
	public static CraftingHandler craftHandler = new CraftingHandler();
	//Client Post Load
	@EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		MeteorusLogger.init();
		MeteorusLogger.log(Level.INFO, "Meteorus Mod Inizialization!");
		System.out.println("Meteorus Mod Inizialization...");
		MeteorusConfiguration.preConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.CHANNEL_NAME + File.separator + Reference.MOD_ID + ".cfg"));
		MeteorusLogger.log(Level.INFO, "Preparing blocks...");
		System.out.println("Loading Blocks...");
		MeteorusBlocksManager();
		MeteorusBlocksRegistry();
		MeteorusLogger.log(Level.INFO, "Blocks preparated");
		System.out.println("Loading Blocks Complete...");
		MeteorusLogger.log(Level.INFO, "Preparing items...");
		System.out.println("Loading Items...");
		MeteorusItemsManager();
		MeteorusItemsRegistry();
		MeteorusLogger.log(Level.INFO, "Items preparated");
		System.out.println("Loading Items Complete...");
		MeteorusLogger.log(Level.INFO, "Preparing renders...");
		System.out.println("Loading Renders...");
		MeteorusRendersManager();
		MeteorusLogger.log(Level.INFO, "Renders preparated");
		System.out.println("Loading Renders Complete...");
		MeteorusLogger.log(Level.INFO, "Checking of continuated IDs...");
		System.out.println("Checking IDs...");
		MeteorusIDsManager();
		MeteorusLogger.log(Level.INFO, "Checking IDs complete");
		System.out.println("Checking IDs Complete...");
		MeteorusLogger.log(Level.INFO, "Preparing repices & smeltings...");
		System.out.println("Loading Recipe & Smelting...");
		MeteorusCraftingManager();
		MeteorusSmeltingManager();
		MeteorusLogger.log(Level.INFO, "Recipe & Smelting preparated");
		System.out.println("Loading Recipe & Smelting Complete...");
		MeteorusLogger.log(Level.INFO, "Preparing Achievement");
		System.out.println("Loading Achievement...");
		initAchievements();
		MeteorusLogger.log(Level.INFO, "Achievement preparated");
		System.out.println("Loading Achievement Complete...");
		MeteorusLogger.log(Level.INFO, "Meteorus Mod PreLoading Complete!");
		System.out.println("PreLoading Meteorus Mod Completated!");
    }

	//Client Load
	@EventHandler
    public void load(FMLInitializationEvent event) {
		//Capes
		MeteorusLogger.log(Level.INFO, "Preparing Custom Capes");
		System.out.println("Loading Custom Capes...");
		DevCapesUtil.addFileUrl("https://raw.github.com/MeteoraPegaso/CapesSystem/master/Capes.txt");
		MeteorusLogger.log(Level.INFO, "Custom Capes preparated");
		System.out.println("Loading Custom Capes Complete...");
		//Crafting Handler
		GameRegistry.registerCraftingHandler(craftHandler);
		//Render Load
		proxy.registerRenderers();
		//Auto Checking
		if(MeteorusConfiguration.enableAutoCheckkUpdate){
			MeteorusLogger.log(Level.INFO, "Checking for new version");
			System.out.println("Auto Checking Update");
	        NetworkRegistry.instance().registerConnectionHandler(new AutoUpdateChecker());
			MeteorusLogger.log(Level.INFO, "Auto Checking Update completed");
			System.out.println("Auto Checking Update Complete...");
	        }
    }
	
	//Client Post Load
	@EventHandler
    public void postInit(FMLPostInitializationEvent event) {
		
    }
    
	//Server Starting
    @EventHandler
    public void serverStart(FMLServerStartingEvent event){
    	event.registerServerCommand(new MeteorusCommands());
    	
    }
    
    //Block Manager
	private void MeteorusBlocksManager() {
		//SubBlocks
		MeteorusBlockHandler.ColoredStones = new BlockColoredStones(MeteorusBlockHandler.ColoredStonesID, Material.rock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("colored_stones").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.ColoredCobblestones = new BlockColoredCobblestones(MeteorusBlockHandler.ColoredCobblestonesID, Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("colored_stonebrick").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.ColoredStoneBricks = new BlockColoredStoneBricks(MeteorusBlockHandler.ColoredStoneBricksID, Material.rock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("colored_stonebricksmooth").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.ColoredStoneCarvedBricks = new BlockColoredStoneCarvedBricks(MeteorusBlockHandler.ColoredStoneCarvedBricksID, Material.rock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("colored_stonebrickcarved").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.ColoredStoneCrackedBricks = new BlockColoredStoneCrackedBricks(MeteorusBlockHandler.ColoredStoneCrackedBricksID, Material.rock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("colored_stonebrickcracked").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.ColoredGlowstones = new BlockColoredGlowstones(MeteorusBlockHandler.ColoredGlowstonesID, Material.glass).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setLightValue(1.0F).setUnlocalizedName("colored_glowstones").setCreativeTab(MeteorusModBlocks);
		//Blocks
		MeteorusBlockHandler.ProjectTable = new BlockProjectTable(MeteorusBlockHandler.ProjectTableID, TileEntityProjectTable.class, Material.wood).setHardness(4.0F).setResistance(7.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("project_table").setCreativeTab(MeteorusModBlocks).setTextureName(Reference.MOD_IDFOLDER + ":" + "project_table_2D_unused(not delete)");
		//Stairs		
		MeteorusBlockHandler.CobblestoneBlackStairs = new ColoredCobblestoneStairs(MeteorusBlockHandler.ColoredCobblestoneStairsID + 0,MeteorusBlockHandler.ColoredCobblestones, 0).setUnlocalizedName("stonebricksblack_stairs").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.CobblestoneRedStairs = new ColoredCobblestoneStairs(MeteorusBlockHandler.ColoredCobblestoneStairsID + 1,MeteorusBlockHandler.ColoredCobblestones, 1).setUnlocalizedName("stonebricksred_stairs").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.CobblestoneGreenStairs = new ColoredCobblestoneStairs(MeteorusBlockHandler.ColoredCobblestoneStairsID + 2,MeteorusBlockHandler.ColoredCobblestones, 2).setUnlocalizedName("stonebricksgreen_stairs").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.CobblestoneBrownStairs = new ColoredCobblestoneStairs(MeteorusBlockHandler.ColoredCobblestoneStairsID + 3,MeteorusBlockHandler.ColoredCobblestones, 3).setUnlocalizedName("stonebricksbrown_stairs").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.CobblestoneBlueStairs = new ColoredCobblestoneStairs(MeteorusBlockHandler.ColoredCobblestoneStairsID + 4,MeteorusBlockHandler.ColoredCobblestones, 4).setUnlocalizedName("stonebricksblue_stairs").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.CobblestonePurpleStairs = new ColoredCobblestoneStairs(MeteorusBlockHandler.ColoredCobblestoneStairsID + 5,MeteorusBlockHandler.ColoredCobblestones, 5).setUnlocalizedName("stonebrickspurple_stairs").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.CobblestoneCyanStairs = new ColoredCobblestoneStairs(MeteorusBlockHandler.ColoredCobblestoneStairsID + 6,MeteorusBlockHandler.ColoredCobblestones, 6).setUnlocalizedName("stonebrickscyan_stairs").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.CobblestonePinkStairs = new ColoredCobblestoneStairs(MeteorusBlockHandler.ColoredCobblestoneStairsID + 7,MeteorusBlockHandler.ColoredCobblestones, 7).setUnlocalizedName("stonebrickspink_stairs").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.CobblestoneLightGreenStairs = new ColoredCobblestoneStairs(MeteorusBlockHandler.ColoredCobblestoneStairsID + 8,MeteorusBlockHandler.ColoredCobblestones, 8).setUnlocalizedName("stonebrickslightgreen_stairs").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.CobblestoneYellowStairs = new ColoredCobblestoneStairs(MeteorusBlockHandler.ColoredCobblestoneStairsID + 9,MeteorusBlockHandler.ColoredCobblestones, 9).setUnlocalizedName("stonebricksyellow_stairs").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.CobblestoneLightBlueStairs = new ColoredCobblestoneStairs(MeteorusBlockHandler.ColoredCobblestoneStairsID + 10,MeteorusBlockHandler.ColoredCobblestones, 10).setUnlocalizedName("stonebrickslightblue_stairs").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.CobblestoneMagentaStairs = new ColoredCobblestoneStairs(MeteorusBlockHandler.ColoredCobblestoneStairsID + 11,MeteorusBlockHandler.ColoredCobblestones, 11).setUnlocalizedName("stonebricksmagenta_stairs").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.CobblestoneOrangeStairs = new ColoredCobblestoneStairs(MeteorusBlockHandler.ColoredCobblestoneStairsID + 12,MeteorusBlockHandler.ColoredCobblestones, 12).setUnlocalizedName("stonebricksorange_stairs").setCreativeTab(MeteorusModBlocks);
		MeteorusBlockHandler.CobblestoneWhiteStairs = new ColoredCobblestoneStairs(MeteorusBlockHandler.ColoredCobblestoneStairsID + 13,MeteorusBlockHandler.ColoredCobblestones, 13).setUnlocalizedName("stonebrickswhite_stairs").setCreativeTab(MeteorusModBlocks);
		
	}
	
	//Block Registry
	private void MeteorusBlocksRegistry() {
		//GameRegistry
		GameRegistry.registerBlock(MeteorusBlockHandler.ColoredGlowstoneActiveLamps);
		GameRegistry.registerBlock(MeteorusBlockHandler.ColoredStones, ItemBlockColoredStones.class, MeteorusBlockHandler.ColoredStones.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.ColoredCobblestones, ItemBlockColoredCobblestones.class, MeteorusBlockHandler.ColoredCobblestones.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.ColoredStoneBricks, ItemBlockColoredBricks.class, MeteorusBlockHandler.ColoredStoneBricks.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.ColoredStoneCarvedBricks, ItemBlockColoredCarvedBricks.class, MeteorusBlockHandler.ColoredStoneCarvedBricks.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.ColoredStoneCrackedBricks, ItemBlockColoredCrackedBricks.class, MeteorusBlockHandler.ColoredStoneCrackedBricks.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.ProjectTable, MeteorusBlockHandler.ProjectTable.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.CobblestoneBlackStairs, MeteorusBlockHandler.CobblestoneBlackStairs.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.CobblestoneRedStairs, MeteorusBlockHandler.CobblestoneRedStairs.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.CobblestoneGreenStairs, MeteorusBlockHandler.CobblestoneGreenStairs.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.CobblestoneBrownStairs, MeteorusBlockHandler.CobblestoneBrownStairs.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.CobblestoneBlueStairs, MeteorusBlockHandler.CobblestoneBlueStairs.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.CobblestonePurpleStairs, MeteorusBlockHandler.CobblestonePurpleStairs.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.CobblestoneCyanStairs, MeteorusBlockHandler.CobblestoneCyanStairs.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.CobblestonePinkStairs, MeteorusBlockHandler.CobblestonePinkStairs.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.CobblestoneLightGreenStairs, MeteorusBlockHandler.CobblestoneLightGreenStairs.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.CobblestoneYellowStairs, MeteorusBlockHandler.CobblestoneYellowStairs.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.CobblestoneLightBlueStairs, MeteorusBlockHandler.CobblestoneLightBlueStairs.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.CobblestoneMagentaStairs, MeteorusBlockHandler.CobblestoneMagentaStairs.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.CobblestoneOrangeStairs, MeteorusBlockHandler.CobblestoneOrangeStairs.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.CobblestoneWhiteStairs, MeteorusBlockHandler.CobblestoneWhiteStairs.getUnlocalizedName());
		GameRegistry.registerBlock(MeteorusBlockHandler.ColoredGlowstones, ItemBlockColoredGlowstones.class, MeteorusBlockHandler.ColoredGlowstones.getUnlocalizedName());
		//LanguageRegistry
		LanguageRegistry.addName(MeteorusBlockHandler.ProjectTable, "Project Table");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStones,1,0), "Black Stone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStones,1,1), "Red Stone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStones,1,2), "Green Stone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStones,1,3), "Brown Stone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStones,1,4), "Blue Stone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStones,1,5), "Purple Stone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStones,1,6), "Cyan Stone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStones,1,7), "Pink Stone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStones,1,8), "LightGreen Stone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStones,1,9), "Yellow Stone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStones,1,10), "LightBlue Stone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStones,1,11), "Magenta Stone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStones,1,12), "Orange Stone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStones,1,13), "White Stone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredCobblestones,1,0), "Black Cobblestone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredCobblestones,1,1), "Red Cobblestone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredCobblestones,1,2), "Green Cobblestone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredCobblestones,1,3), "Brown Cobblestone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredCobblestones,1,4), "Blue Cobblestone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredCobblestones,1,5), "Purple Cobblestone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredCobblestones,1,6), "Cyan Cobblestone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredCobblestones,1,7), "Pink Cobblestone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredCobblestones,1,8), "LightGreen Cobblestone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredCobblestones,1,9), "Yellow Cobblestone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredCobblestones,1,10), "LightBlue Cobblestone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredCobblestones,1,11), "Magenta Cobblestone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredCobblestones,1,12), "Orange Cobblestone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredCobblestones,1,13), "White Cobblestone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,1,0), "Black Stone Bricks");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,1,1), "Red Stone Bricks");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,1,2), "Green Stone Bricks");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,1,3), "Brown Stone Bricks");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,1,4), "Blue Stone Bricks");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,1,5), "Purple Stone Bricks");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,1,6), "Cyan Stone Bricks");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,1,7), "Pink Stone Bricks");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,1,8), "LightGreen Stone Bricks");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,1,9), "Yellow Stone Bricks");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,1,10), "LightBlue Stone Bricks");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,1,11), "Magenta Stone Bricks");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,1,12), "Orange Stone Bricks");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,1,13), "White Stone Bricks");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks,1,0), "Chiseled Black Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks,1,1), "Chiseled Red Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks,1,2), "Chiseled Green Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks,1,3), "Chiseled Brown Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks,1,4), "Chiseled Blue Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks,1,5), "Chiseled Purple Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks,1,6), "Chiseled Cyan Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks,1,7), "Chiseled Pink Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks,1,8), "Chiseled LightGreen Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks,1,9), "Chiseled Yellow Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks,1,10), "Chiseled LightBlue Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks,1,11), "Chiseled Magenta Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks,1,12), "Chiseled Orange Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks,1,13), "Chiseled White Stone Bricks");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,1,0), "Cracked Black Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,1,1), "Cracked Red Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,1,2), "Cracked Green Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,1,3), "Cracked Brown Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,1,4), "Cracked Blue Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,1,5), "Cracked Purple Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,1,6), "Cracked Cyan Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,1,7), "Cracked Pink Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,1,8), "Cracked LightGreen Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,1,9), "Cracked Yellow Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,1,10), "Cracked LightBlue Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,1,11), "Cracked Magenta Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,1,12), "Cracked Orange Stone Bricks");
    	LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,1,13), "Cracked White Stone Bricks");
		LanguageRegistry.addName(MeteorusBlockHandler.CobblestoneBlackStairs, "Black Cobblestone Stairs");
		LanguageRegistry.addName(MeteorusBlockHandler.CobblestoneRedStairs, "Red Cobblestone Stairs");
		LanguageRegistry.addName(MeteorusBlockHandler.CobblestoneGreenStairs, "Green Cobblestone Stairs");
		LanguageRegistry.addName(MeteorusBlockHandler.CobblestoneBrownStairs, "Brown Cobblestone Stairs");
		LanguageRegistry.addName(MeteorusBlockHandler.CobblestoneBlueStairs, "Blue Cobblestone Stairs");
		LanguageRegistry.addName(MeteorusBlockHandler.CobblestonePurpleStairs, "Purple Cobblestone Stairs");
		LanguageRegistry.addName(MeteorusBlockHandler.CobblestoneCyanStairs, "Cyan Cobblestone Stairs");
		LanguageRegistry.addName(MeteorusBlockHandler.CobblestonePinkStairs, "Pink Cobblestone Stairs");
		LanguageRegistry.addName(MeteorusBlockHandler.CobblestoneLightGreenStairs, "LightGreen Cobblestone Stairs");
		LanguageRegistry.addName(MeteorusBlockHandler.CobblestoneYellowStairs, "Yellow Cobblestone Stairs");
		LanguageRegistry.addName(MeteorusBlockHandler.CobblestoneLightBlueStairs, "LightBlue Cobblestone Stairs");
		LanguageRegistry.addName(MeteorusBlockHandler.CobblestoneMagentaStairs, "Magenta Cobblestone Stairs");
		LanguageRegistry.addName(MeteorusBlockHandler.CobblestoneOrangeStairs, "Orange Cobblestone Stairs");
		LanguageRegistry.addName(MeteorusBlockHandler.CobblestoneWhiteStairs, "White Cobblestone Stairs");
		LanguageRegistry.addName(MeteorusBlockHandler.ProjectTable, "Project Table");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,0), "Black Glowstone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,1), "Red Glowstone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,2), "Green Glowstone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,3), "Brown Glowstone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,4), "Blue Glowstone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,5), "Purple Glowstone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,6), "Cyan Glowstone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,7), "Pink Glowstone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,8), "LightGreen Glowstone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,9), "Gray Glowstone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,10), "LightBlue Glowstone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,11), "Magenta Glowstone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,12), "Orange Glowstone");
		LanguageRegistry.addName(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,13), "White Glowstone");
	}
	
	//Item Manager
	private void MeteorusItemsManager() {
		MeteorusItemHandler.ProjectCarvedStone = new ItemProjectCarvedStone(MeteorusItemHandler.ProjectCarvedStoneID).setUnlocalizedName("project_carvedstone").setCreativeTab(MeteorusModItems).setTextureName(Reference.MOD_IDFOLDER + ":" + "projectcarvedstone");
		MeteorusItemHandler.Chisel = new ItemChisel(MeteorusItemHandler.ChiselID).setUnlocalizedName("chisel").setFull3D().setCreativeTab(MeteorusModItems).setTextureName(Reference.MOD_IDFOLDER + ":" + "chisel");
		MeteorusItemHandler.Hammer = new ItemHammer(MeteorusItemHandler.HammerID).setUnlocalizedName("hammer").setFull3D().setCreativeTab(MeteorusModItems).setTextureName(Reference.MOD_IDFOLDER + ":" + "hammer");
		MeteorusItemHandler.ColoredGlowstoneDusts = new ItemColoredGlowstoneDust(MeteorusItemHandler.ColoredGlowstoneDustsID).setUnlocalizedName("glowstonedust").setPotionEffect(PotionHelper.glowstoneEffect).setCreativeTab(MeteorusModItems);
	}
	
	//Item Registry
	private void MeteorusItemsRegistry() {
		//Registry
		GameRegistry.registerItem(MeteorusItemHandler.ProjectCarvedStone, MeteorusItemHandler.ProjectCarvedStone.getUnlocalizedName());
		GameRegistry.registerItem(MeteorusItemHandler.Chisel, MeteorusItemHandler.Chisel.getUnlocalizedName());
		GameRegistry.registerItem(MeteorusItemHandler.Hammer, MeteorusItemHandler.Hammer.getUnlocalizedName());
		//Language
		LanguageRegistry.addName(MeteorusItemHandler.ProjectCarvedStone, "Project of Carved Stone Bricks");
		LanguageRegistry.addName(MeteorusItemHandler.Chisel, "Chisel");
		LanguageRegistry.addName(MeteorusItemHandler.Hammer, "Hammer");
		LanguageRegistry.addName(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,1,0), "Black Glowstone Dust");
		LanguageRegistry.addName(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,1,1), "Red Glowstone Dust");
		LanguageRegistry.addName(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,1,2), "Green Glowstone Dust");
		LanguageRegistry.addName(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,1,3), "Brown Glowstone Dust");
		LanguageRegistry.addName(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,1,4), "Blue Glowstone Dust");
		LanguageRegistry.addName(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,1,5), "Purple Glowstone Dust");
		LanguageRegistry.addName(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,1,6), "Cyan Glowstone Dust");
		LanguageRegistry.addName(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,1,7), "Pink Glowstone Dust");
		LanguageRegistry.addName(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,1,8), "LightGreen Glowstone Dust");
		LanguageRegistry.addName(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,1,9), "Gray Glowstone Dust");
		LanguageRegistry.addName(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,1,10), "LightBlue Glowstone Dust");
		LanguageRegistry.addName(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,1,11), "Magenta Glowstone Dust");
		LanguageRegistry.addName(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,1,12), "Orange Glowstone Dust");
		LanguageRegistry.addName(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,1,13), "White Glowstone Dust");
	}
	
	//IDs Check
	private void MeteorusIDsManager() {
		
	}
	
	//Render Manager
	private void MeteorusRendersManager() {
		//Project Table
		GameRegistry.registerTileEntity(TileEntityProjectTable.class, "TileEntityProjectTable");
		NetworkRegistry.instance().registerGuiHandler(this, guiHandlerProjectTable);
		MeteorusItemHandler.ItemRendererProjectTable = new TileEntityProjectTableHandlerRenderer();
		
	}
	
	//Recipe Manager
	private void MeteorusCraftingManager() {
		//Shape
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStones, 1, 0), new Object[]{new ItemStack(Item.dyePowder, 1, 0), new ItemStack(Item.itemsList[Block.stone.blockID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStones, 1, 1), new Object[]{new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.itemsList[Block.stone.blockID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStones, 1, 2), new Object[]{new ItemStack(Item.dyePowder, 1, 2), new ItemStack(Item.itemsList[Block.stone.blockID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStones, 1, 3), new Object[]{new ItemStack(Item.dyePowder, 1, 3), new ItemStack(Item.itemsList[Block.stone.blockID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStones, 1, 4), new Object[]{new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.itemsList[Block.stone.blockID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStones, 1, 5), new Object[]{new ItemStack(Item.dyePowder, 1, 5), new ItemStack(Item.itemsList[Block.stone.blockID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStones, 1, 6), new Object[]{new ItemStack(Item.dyePowder, 1, 6), new ItemStack(Item.itemsList[Block.stone.blockID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStones, 1, 7), new Object[]{new ItemStack(Item.dyePowder, 1, 9), new ItemStack(Item.itemsList[Block.stone.blockID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStones, 1, 8), new Object[]{new ItemStack(Item.dyePowder, 1, 10), new ItemStack(Item.itemsList[Block.stone.blockID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStones, 1, 9), new Object[]{new ItemStack(Item.dyePowder, 1, 11), new ItemStack(Item.itemsList[Block.stone.blockID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStones, 1, 10), new Object[]{new ItemStack(Item.dyePowder, 1, 12), new ItemStack(Item.itemsList[Block.stone.blockID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStones, 1, 11), new Object[]{new ItemStack(Item.dyePowder, 1, 13), new ItemStack(Item.itemsList[Block.stone.blockID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStones, 1, 12), new Object[]{new ItemStack(Item.dyePowder, 1, 14), new ItemStack(Item.itemsList[Block.stone.blockID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStones, 1, 13), new Object[]{new ItemStack(Item.dyePowder, 1, 15), new ItemStack(Item.itemsList[Block.stone.blockID], 1)});
        GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,4, 0), new Object[]{new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,0,0), new ItemStack(MeteorusItemHandler.Hammer, 0 ,OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,4, 1), new Object[]{new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,0,1), new ItemStack(MeteorusItemHandler.Hammer, 0 ,OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,4, 2), new Object[]{new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,0,2), new ItemStack(MeteorusItemHandler.Hammer, 0 ,OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,4, 3), new Object[]{new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,0,3), new ItemStack(MeteorusItemHandler.Hammer, 0 ,OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,4, 4), new Object[]{new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,0,4), new ItemStack(MeteorusItemHandler.Hammer, 0 ,OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,4, 5), new Object[]{new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,0,5), new ItemStack(MeteorusItemHandler.Hammer, 0 ,OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,4, 6), new Object[]{new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,0,6), new ItemStack(MeteorusItemHandler.Hammer, 0 ,OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,4, 7), new Object[]{new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,0,7), new ItemStack(MeteorusItemHandler.Hammer, 0 ,OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,4, 8), new Object[]{new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,0,8), new ItemStack(MeteorusItemHandler.Hammer, 0 ,OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,4, 9), new Object[]{new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,0,9), new ItemStack(MeteorusItemHandler.Hammer, 0 ,OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,4, 10), new Object[]{new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,0,10), new ItemStack(MeteorusItemHandler.Hammer, 0 ,OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,4, 11), new Object[]{new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,0,11), new ItemStack(MeteorusItemHandler.Hammer, 0 ,OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,4, 12), new Object[]{new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,0,12), new ItemStack(MeteorusItemHandler.Hammer, 0 ,OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addShapelessRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCrackedBricks,4, 13), new Object[]{new ItemStack(MeteorusBlockHandler.ColoredStoneBricks,0,13), new ItemStack(MeteorusItemHandler.Hammer, 0 ,OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addShapelessRecipe(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts, 1, 0), new Object[]{new ItemStack(Item.dyePowder, 1, 0), new ItemStack(Item.itemsList[Item.glowstone.itemID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts, 1, 1), new Object[]{new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.itemsList[Item.glowstone.itemID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts, 1, 2), new Object[]{new ItemStack(Item.dyePowder, 1, 2), new ItemStack(Item.itemsList[Item.glowstone.itemID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts, 1, 3), new Object[]{new ItemStack(Item.dyePowder, 1, 3), new ItemStack(Item.itemsList[Item.glowstone.itemID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts, 1, 4), new Object[]{new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.itemsList[Item.glowstone.itemID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts, 1, 5), new Object[]{new ItemStack(Item.dyePowder, 1, 5), new ItemStack(Item.itemsList[Item.glowstone.itemID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts, 1, 6), new Object[]{new ItemStack(Item.dyePowder, 1, 6), new ItemStack(Item.itemsList[Item.glowstone.itemID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts, 1, 7), new Object[]{new ItemStack(Item.dyePowder, 1, 9), new ItemStack(Item.itemsList[Item.glowstone.itemID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts, 1, 8), new Object[]{new ItemStack(Item.dyePowder, 1, 10), new ItemStack(Item.itemsList[Item.glowstone.itemID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts, 1, 9), new Object[]{new ItemStack(Item.dyePowder, 1, 8), new ItemStack(Item.itemsList[Item.glowstone.itemID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts, 1, 10), new Object[]{new ItemStack(Item.dyePowder, 1, 12), new ItemStack(Item.itemsList[Item.glowstone.itemID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts, 1, 11), new Object[]{new ItemStack(Item.dyePowder, 1, 13), new ItemStack(Item.itemsList[Item.glowstone.itemID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts, 1, 12), new Object[]{new ItemStack(Item.dyePowder, 1, 14), new ItemStack(Item.itemsList[Item.glowstone.itemID], 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts, 1, 13), new Object[]{new ItemStack(Item.dyePowder, 1, 15), new ItemStack(Item.itemsList[Item.glowstone.itemID], 1)});
        //Normal
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ProjectTable, 1), new Object[]{"XXX", "CAC", "CAC", 'X', Block.stone, 'C', Item.ingotIron, 'A', Block.planks });
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks, 4, 0), new Object[]{"XX", "XX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,0)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks, 4, 1), new Object[]{"XX", "XX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,1)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks, 4, 2), new Object[]{"XX", "XX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,2)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks, 4, 3), new Object[]{"XX", "XX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,3)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks, 4, 4), new Object[]{"XX", "XX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,4)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks, 4, 5), new Object[]{"XX", "XX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,5)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks, 4, 6), new Object[]{"XX", "XX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,6)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks, 4, 7), new Object[]{"XX", "XX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,7)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks, 4, 8), new Object[]{"XX", "XX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,8)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks, 4, 9), new Object[]{"XX", "XX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,9)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks, 4, 10), new Object[]{"XX", "XX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,10)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks, 4, 11), new Object[]{"XX", "XX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,11)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks, 4, 12), new Object[]{"XX", "XX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,12)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneBricks, 4, 13), new Object[]{"XX", "XX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,13)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneWhiteStairs, 4), new Object[]{"X  ", "XX ", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,13)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneWhiteStairs, 4), new Object[]{"  X", " XX", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,13)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneOrangeStairs, 4), new Object[]{"X  ", "XX ", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,12)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneOrangeStairs, 4), new Object[]{"  X", " XX", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,12)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneMagentaStairs, 4), new Object[]{"X  ", "XX ", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,11)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneMagentaStairs, 4), new Object[]{"  X", " XX", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,11)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneLightBlueStairs, 4), new Object[]{"X  ", "XX ", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,10)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneLightBlueStairs, 4), new Object[]{"  X", " XX", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,10)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneYellowStairs, 4), new Object[]{"X  ", "XX ", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,9)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneYellowStairs, 4), new Object[]{"  X", " XX", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,9)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneLightGreenStairs, 4), new Object[]{"X  ", "XX ", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,8)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneLightGreenStairs, 4), new Object[]{"  X", " XX", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,8)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestonePinkStairs, 4), new Object[]{"X  ", "XX ", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,7)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestonePinkStairs, 4), new Object[]{"  X", " XX", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,7)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneCyanStairs, 4), new Object[]{"X  ", "XX ", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,6)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneCyanStairs, 4), new Object[]{"  X", " XX", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,6)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestonePurpleStairs, 4), new Object[]{"X  ", "XX ", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,5)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestonePurpleStairs, 4), new Object[]{"  X", " XX", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,5)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneBlueStairs, 4), new Object[]{"X  ", "XX ", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,4)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneBlueStairs, 4), new Object[]{"  X", " XX", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,4)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneBrownStairs, 4), new Object[]{"X  ", "XX ", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,3)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneBrownStairs, 4), new Object[]{"  X", " XX", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,3)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneGreenStairs, 4), new Object[]{"X  ", "XX ", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,2)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneGreenStairs, 4), new Object[]{"  X", " XX", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,2)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneRedStairs, 4), new Object[]{"X  ", "XX ", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,1)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneRedStairs, 4), new Object[]{"  X", " XX", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,1)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneBlackStairs, 4), new Object[]{"X  ", "XX ", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,0)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.CobblestoneBlackStairs, 4), new Object[]{"  X", " XX", "XXX", 'X', new ItemStack(MeteorusBlockHandler.ColoredCobblestones, 0,0)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks, 4, 0), new Object[]{"XXX", "XCX", "XAX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,0),'C', new ItemStack(MeteorusItemHandler.Chisel, 0 ,OreDictionary.WILDCARD_VALUE),'A', new ItemStack(MeteorusItemHandler.ProjectCarvedStone, 0 , OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks, 4, 1), new Object[]{"XXX", "XCX", "XAX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,1),'C', new ItemStack(MeteorusItemHandler.Chisel, 0 ,OreDictionary.WILDCARD_VALUE),'A', new ItemStack(MeteorusItemHandler.ProjectCarvedStone, 0 , OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks, 4, 2), new Object[]{"XXX", "XCX", "XAX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,2),'C', new ItemStack(MeteorusItemHandler.Chisel, 0 ,OreDictionary.WILDCARD_VALUE),'A', new ItemStack(MeteorusItemHandler.ProjectCarvedStone, 0 , OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks, 4, 3), new Object[]{"XXX", "XCX", "XAX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,3),'C', new ItemStack(MeteorusItemHandler.Chisel, 0 ,OreDictionary.WILDCARD_VALUE),'A', new ItemStack(MeteorusItemHandler.ProjectCarvedStone, 0 , OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks, 4, 4), new Object[]{"XXX", "XCX", "XAX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,4),'C', new ItemStack(MeteorusItemHandler.Chisel, 0 ,OreDictionary.WILDCARD_VALUE),'A', new ItemStack(MeteorusItemHandler.ProjectCarvedStone, 0 , OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks, 4, 5), new Object[]{"XXX", "XCX", "XAX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,5),'C', new ItemStack(MeteorusItemHandler.Chisel, 0 ,OreDictionary.WILDCARD_VALUE),'A', new ItemStack(MeteorusItemHandler.ProjectCarvedStone, 0 , OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks, 4, 6), new Object[]{"XXX", "XCX", "XAX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,6),'C', new ItemStack(MeteorusItemHandler.Chisel, 0 ,OreDictionary.WILDCARD_VALUE),'A', new ItemStack(MeteorusItemHandler.ProjectCarvedStone, 0 , OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks, 4, 7), new Object[]{"XXX", "XCX", "XAX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,7),'C', new ItemStack(MeteorusItemHandler.Chisel, 0 ,OreDictionary.WILDCARD_VALUE),'A', new ItemStack(MeteorusItemHandler.ProjectCarvedStone, 0 , OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks, 4, 8), new Object[]{"XXX", "XCX", "XAX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,8),'C', new ItemStack(MeteorusItemHandler.Chisel, 0 ,OreDictionary.WILDCARD_VALUE),'A', new ItemStack(MeteorusItemHandler.ProjectCarvedStone, 0 , OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks, 4, 9), new Object[]{"XXX", "XCX", "XAX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,9),'C', new ItemStack(MeteorusItemHandler.Chisel, 0 ,OreDictionary.WILDCARD_VALUE),'A', new ItemStack(MeteorusItemHandler.ProjectCarvedStone, 0 , OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks, 4, 10), new Object[]{"XXX", "XCX", "XAX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,10),'C', new ItemStack(MeteorusItemHandler.Chisel, 0 ,OreDictionary.WILDCARD_VALUE),'A', new ItemStack(MeteorusItemHandler.ProjectCarvedStone, 0 , OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks, 4, 11), new Object[]{"XXX", "XCX", "XAX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,11),'C', new ItemStack(MeteorusItemHandler.Chisel, 0 ,OreDictionary.WILDCARD_VALUE),'A', new ItemStack(MeteorusItemHandler.ProjectCarvedStone, 0 , OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks, 4, 12), new Object[]{"XXX", "XCX", "XAX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,12),'C', new ItemStack(MeteorusItemHandler.Chisel, 0 ,OreDictionary.WILDCARD_VALUE),'A', new ItemStack(MeteorusItemHandler.ProjectCarvedStone, 0 , OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks, 4, 13), new Object[]{"XXX", "XCX", "XAX", 'X', new ItemStack(MeteorusBlockHandler.ColoredStones, 0,13),'C', new ItemStack(MeteorusItemHandler.Chisel, 0 ,OreDictionary.WILDCARD_VALUE),'A', new ItemStack(MeteorusItemHandler.ProjectCarvedStone, 0 , OreDictionary.WILDCARD_VALUE)});
        GameRegistry.addRecipe(new ItemStack(MeteorusItemHandler.Hammer, 1), new Object[]{"XXX", "XCX", " C ", 'X', Item.ingotIron, 'C', Item.stick });
        GameRegistry.addRecipe(new ItemStack(MeteorusItemHandler.Chisel, 1), new Object[]{"XX ", "XC ", "  C", 'X', Item.ingotIron, 'C', Item.stick });
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,0), new Object[]{"XX", "XX" , 'X', new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,0,0)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,1), new Object[]{"XX", "XX" , 'X', new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,0,1)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,2), new Object[]{"XX", "XX" , 'X', new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,0,2)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,3), new Object[]{"XX", "XX" , 'X', new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,0,3)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,4), new Object[]{"XX", "XX" , 'X', new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,0,4)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,5), new Object[]{"XX", "XX" , 'X', new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,0,5)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,6), new Object[]{"XX", "XX" , 'X', new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,0,6)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,7), new Object[]{"XX", "XX" , 'X', new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,0,7)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,8), new Object[]{"XX", "XX" , 'X', new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,0,8)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,9), new Object[]{"XX", "XX" , 'X', new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,0,9)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,10), new Object[]{"XX", "XX" , 'X', new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,0,10)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,11), new Object[]{"XX", "XX" , 'X', new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,0,11)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,12), new Object[]{"XX", "XX" , 'X', new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,0,12)});
        GameRegistry.addRecipe(new ItemStack(MeteorusBlockHandler.ColoredGlowstones,1,13), new Object[]{"XX", "XX" , 'X', new ItemStack(MeteorusItemHandler.ColoredGlowstoneDusts,0,13)});
        }
	
	//Smelting Manager
    private void MeteorusSmeltingManager() {
    	FurnaceRecipes.smelting().addSmelting(MeteorusBlockHandler.ColoredCobblestones.blockID, 0, new ItemStack(MeteorusBlockHandler.ColoredStones, 1,0), 0.1F);
    	FurnaceRecipes.smelting().addSmelting(MeteorusBlockHandler.ColoredCobblestones.blockID, 1, new ItemStack(MeteorusBlockHandler.ColoredStones, 1,1), 0.1F);
    	FurnaceRecipes.smelting().addSmelting(MeteorusBlockHandler.ColoredCobblestones.blockID, 2, new ItemStack(MeteorusBlockHandler.ColoredStones, 1,2), 0.1F);
    	FurnaceRecipes.smelting().addSmelting(MeteorusBlockHandler.ColoredCobblestones.blockID, 3, new ItemStack(MeteorusBlockHandler.ColoredStones, 1,3), 0.1F);
    	FurnaceRecipes.smelting().addSmelting(MeteorusBlockHandler.ColoredCobblestones.blockID, 4, new ItemStack(MeteorusBlockHandler.ColoredStones, 1,4), 0.1F);
    	FurnaceRecipes.smelting().addSmelting(MeteorusBlockHandler.ColoredCobblestones.blockID, 5, new ItemStack(MeteorusBlockHandler.ColoredStones, 1,5), 0.1F);
    	FurnaceRecipes.smelting().addSmelting(MeteorusBlockHandler.ColoredCobblestones.blockID, 6, new ItemStack(MeteorusBlockHandler.ColoredStones, 1,6), 0.1F);
    	FurnaceRecipes.smelting().addSmelting(MeteorusBlockHandler.ColoredCobblestones.blockID, 7, new ItemStack(MeteorusBlockHandler.ColoredStones, 1,7), 0.1F);
    	FurnaceRecipes.smelting().addSmelting(MeteorusBlockHandler.ColoredCobblestones.blockID, 8, new ItemStack(MeteorusBlockHandler.ColoredStones, 1,8), 0.1F);
    	FurnaceRecipes.smelting().addSmelting(MeteorusBlockHandler.ColoredCobblestones.blockID, 9, new ItemStack(MeteorusBlockHandler.ColoredStones, 1,9), 0.1F);
    	FurnaceRecipes.smelting().addSmelting(MeteorusBlockHandler.ColoredCobblestones.blockID, 10, new ItemStack(MeteorusBlockHandler.ColoredStones, 1,10), 0.1F);
    	FurnaceRecipes.smelting().addSmelting(MeteorusBlockHandler.ColoredCobblestones.blockID, 11, new ItemStack(MeteorusBlockHandler.ColoredStones, 1,11), 0.1F);
    	FurnaceRecipes.smelting().addSmelting(MeteorusBlockHandler.ColoredCobblestones.blockID, 12, new ItemStack(MeteorusBlockHandler.ColoredStones, 1,12), 0.1F);
    	FurnaceRecipes.smelting().addSmelting(MeteorusBlockHandler.ColoredCobblestones.blockID, 13, new ItemStack(MeteorusBlockHandler.ColoredStones, 1,13), 0.1F);
    }
    
    //Achievements
    public static void initAchievements()
	{
    	//Achievements
		AchievementManager.TIME_OF_DECORATIONS = new Achievement(AchievementManager.TIME_OF_DECORATIONSID, "TIME_OF_DECORATIONS", 0, 0, new ItemStack(MeteorusBlockHandler.ColoredStones, 0,1), null).registerAchievement();
		AchievementManager.BRICKS_COLORS = new Achievement(AchievementManager.BRICKS_COLORSID, "BRICKS_COLORS", 1, 1, new ItemStack(MeteorusBlockHandler.ColoredStoneBricks, 0,0), AchievementManager.TIME_OF_DECORATIONS ).registerAchievement();
		AchievementManager.PROJECT_TABLE = new Achievement(AchievementManager.PROJECT_TABLEID, "PROJECT_TABLE", 0, -2, new ItemStack(MeteorusBlockHandler.ProjectTable), null ).registerAchievement();
		AchievementManager.BRICKS_CHISELED = new Achievement(AchievementManager.BRICKS_CHISELEDID, "BRICKS_CHISELED", 2, -2, new ItemStack(MeteorusBlockHandler.ColoredStoneCarvedBricks, 0,6), AchievementManager.PROJECT_TABLE ).registerAchievement();
		//Achievements Name
		addAchievementName("TIME_OF_DECORATIONS", "Time of Decorations!");
		addAchievementName("BRICKS_COLORS", "More Bricks!");
		addAchievementName("BRICKS_CHISELED", "More Chiseled Bricks!");
		addAchievementName("PROJECT_TABLE", "Time of project!");
		//Achievements Desc
		addAchievementDesc("TIME_OF_DECORATIONS", "Craft or Smelt a colored stone");
		addAchievementDesc("BRICKS_COLORS", "Craft a colored stone bricks");
		addAchievementDesc("BRICKS_CHISELED", "Craft a colored stone chiseled bricks");
		addAchievementDesc("PROJECT_TABLE", "Craft a project table");
		//Achievements Page Manager
		AchievementManager.METEORUS_ACHIEVEMENT_PAGE = new AchievementPage("Meteorus Achievements", AchievementManager.TIME_OF_DECORATIONS, AchievementManager.BRICKS_COLORS, AchievementManager.BRICKS_CHISELED, AchievementManager.PROJECT_TABLE);
		//Achievements Page Register
		AchievementPage.registerAchievementPage(AchievementManager.METEORUS_ACHIEVEMENT_PAGE);
	}

	private static void addAchievementName(String ach, String name)
	{
		LanguageRegistry.instance().addStringLocalization("achievement." + ach, "en_US", name);
	}

	private static void addAchievementDesc(String ach, String desc)
	{
		LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
	}
}
