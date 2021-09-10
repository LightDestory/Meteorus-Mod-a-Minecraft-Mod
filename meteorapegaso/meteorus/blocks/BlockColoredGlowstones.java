package meteorapegaso.meteorus.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import meteorapegaso.meteorus.items.MeteorusItemHandler;
import meteorapegaso.meteorus.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockColoredGlowstones extends Block {
	public static final String[] colors = new String[] {"black", "red", "green", "brown", "blue", "purple", "cyan", "pink", "lightgreen", "gray", "lightblue", "magenta", "orange", "white"};
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	public BlockColoredGlowstones(int par1, Material par2Material) {
		super(par1, par2Material);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
	       icons = new Icon[14];

	       for(int i = 0; i < icons.length; i++)
	       {
	        icons[i] = par1IconRegister.registerIcon(Reference.MOD_IDFOLDER + ":" + this.colors[(i)] + "_glowstone");
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
	public int quantityDropped(Random par1Random)
    {
        return 2 + par1Random.nextInt(3);
    }
	
	public int idDropped(int metadata, Random par2Random, int par3) {
		return MeteorusItemHandler.ColoredGlowstoneDusts.itemID;
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