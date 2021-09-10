package meteorapegaso.meteorus.creativetabs;

import meteorapegaso.meteorus.items.MeteorusItemHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabsMeteorusItems extends CreativeTabs {
	public String tabName;
	public CreativeTabsMeteorusItems(int par1, String par2Str, String par3Str) {
        super(par1, par2Str);
        this.tabName = par3Str;
    }
    
    @SideOnly(Side.CLIENT)
    public int getTabIconItemIndex()
    {
        return MeteorusItemHandler.Hammer.itemID;
    }
    @Override
    public String getTranslatedTabLabel() {
    	return this.tabName;
    }
}