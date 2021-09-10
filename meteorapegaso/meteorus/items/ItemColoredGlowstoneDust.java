package meteorapegaso.meteorus.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import meteorapegaso.meteorus.util.Reference;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class ItemColoredGlowstoneDust extends Item {
	
	public static final String[] ColoredGlowstoneNames = new String[] {"black", "red", "green", "brown", "blue", "purple", "cyan","pink", "lightgreen", "gray", "lightblue", "magenta", "orange", "white"};
	 @SideOnly(Side.CLIENT)
	    private Icon[] coloredglowstoneicons;
	 
	public ItemColoredGlowstoneDust(int par1) {
		super(par1);
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
	}
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1)
    {
        int j = MathHelper.clamp_int(par1, 0, 13);
        return this.coloredglowstoneicons[j];
    }
    
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 13);
        return super.getUnlocalizedName() + "_" + ColoredGlowstoneNames[i];
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int j = 0; j < 14; ++j)
        {
            par3List.add(new ItemStack(par1, 1, j));
        }
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.coloredglowstoneicons = new Icon[ColoredGlowstoneNames.length];

        for (int i = 0; i < ColoredGlowstoneNames.length; ++i)
        {
            this.coloredglowstoneicons[i] = par1IconRegister.registerIcon(Reference.MOD_IDFOLDER + ":" + this.ColoredGlowstoneNames[(i)] + "_glowstonedust");
        }
    }
}
