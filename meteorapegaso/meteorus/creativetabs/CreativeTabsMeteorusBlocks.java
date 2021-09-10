package meteorapegaso.meteorus.creativetabs;

import meteorapegaso.meteorus.blocks.MeteorusBlockHandler;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabsMeteorusBlocks extends CreativeTabs {
	public String tabName;
	public CreativeTabsMeteorusBlocks(int par1, String par2Str, String par3Str) {
        super(par1, par2Str);
        this.tabName = par3Str;
    }
    
    @SideOnly(Side.CLIENT)
    public int getTabIconItemIndex()
    {
        return MeteorusBlockHandler.ProjectTable.blockID;
    }
    @Override
    public String getTranslatedTabLabel() {
    	return this.tabName;
    }
}