package meteorapegaso.meteorus.blocks;

import java.util.List;

import meteorapegaso.meteorus.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockColoredStoneBricks extends Block {
	public static final String[] colors = new String[] {"black", "red", "green", "brown", "blue", "purple", "cyan", "pink", "lightgreen", "yellow", "lightblue", "magenta", "orange", "white"};
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	public BlockColoredStoneBricks(int par1, Material par2Material) {
		super(par1, par2Material);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
	       icons = new Icon[14];

	       for(int i = 0; i < icons.length; i++)
	       {
	        icons[i] = par1IconRegister.registerIcon(Reference.MOD_IDFOLDER + ":" + this.colors[(i)] + "stonebrick");
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
	
	 public int damageDropped(int par1)
	    {
	        return par1;
	    }
}