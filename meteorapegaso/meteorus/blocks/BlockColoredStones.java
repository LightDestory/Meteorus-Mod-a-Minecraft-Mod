package meteorapegaso.meteorus.blocks;

import java.util.List;
import java.util.Random;

import meteorapegaso.meteorus.achievements.AchievementManager;
import meteorapegaso.meteorus.common.mod_meteorus;
import meteorapegaso.meteorus.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockColoredStones extends Block {
	public static final String[] colors = new String[] {"black", "red", "green", "brown", "blue", "purple", "cyan", "pink", "lightgreen", "yellow", "lightblue", "magenta", "orange", "white"};
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	public BlockColoredStones(int par1, Material par2Material) {
		super(par1, par2Material);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
	       icons = new Icon[14];

	       for(int i = 0; i < icons.length; i++)
	       {
	        icons[i] = par1IconRegister.registerIcon(Reference.MOD_IDFOLDER + ":" + this.colors[(i)] + "stone");
	       }
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
	       return icons[par2];
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
	    for (int var4 = 0; var4 < 14; ++var4)
	    {
	        par3List.add(new ItemStack(par1, 1, var4));
	    }
	}
	
	public int idDropped(int metadata, Random par2Random, int par3) {
		return MeteorusBlockHandler.ColoredCobblestones.blockID;
		}
	@Override
	public int damageDropped(int metadata)
	{
		if(metadata == 0) {
			return 0;
		}
		
		if(metadata == 1) {
			return 1;
		}
		
		if(metadata == 2) {
			return 2;
		}
		
		if(metadata == 3) {
			return 3;
		}
		
		if(metadata == 4) {
			return 4;
		}
		
		if(metadata == 5) {
			return 5;
		}
		
		if(metadata == 6) {
			return 6;
		}
		
		if(metadata == 7) {
			return 7;
		}
		
		if(metadata == 8) {
			return 8;
		}
		
		if(metadata == 9) {
			return 9;
		}
		
		if(metadata == 10) {
			return 10;
		}
		
		if(metadata == 11) {
			return 11;
		}
		
		if(metadata == 12) {
			return 12;
		}
		
		if(metadata == 13) {
			return 13;
		}
		
		return 0;
	}
}
