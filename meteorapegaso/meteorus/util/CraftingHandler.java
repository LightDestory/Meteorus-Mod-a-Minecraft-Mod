package meteorapegaso.meteorus.util;

import meteorapegaso.meteorus.achievements.AchievementManager;
import meteorapegaso.meteorus.blocks.MeteorusBlockHandler;
import meteorapegaso.meteorus.items.MeteorusItemHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public class CraftingHandler implements ICraftingHandler{

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item,
			IInventory craftMatrix) {
		//ProjectCarved
		for(int i=0; i < craftMatrix.getSizeInventory(); i++)
		{               
		if(craftMatrix.getStackInSlot(i) != null)
		{
		ItemStack j = craftMatrix.getStackInSlot(i);
		if(j.getItem() != null && j.getItem() == MeteorusItemHandler.ProjectCarvedStone)
		{
		         ItemStack k = new ItemStack(MeteorusItemHandler.ProjectCarvedStone.itemID, 2, (j.getItemDamage() + 2));
		         if(k.getItemDamage() >= k.getMaxDamage()){
		         k.stackSize--;
		         }
		         craftMatrix.setInventorySlotContents(i, k);
		}
		}
		}
		//Chisel
		for(int i=0; i < craftMatrix.getSizeInventory(); i++)
		{               
		if(craftMatrix.getStackInSlot(i) != null)
		{
		ItemStack j = craftMatrix.getStackInSlot(i);
		if(j.getItem() != null && j.getItem() == MeteorusItemHandler.Chisel)
		{
		         ItemStack k = new ItemStack(MeteorusItemHandler.Chisel.itemID, 2, (j.getItemDamage() + 2));
		         if(k.getItemDamage() >= k.getMaxDamage()){
		         k.stackSize--;
		         }
		         craftMatrix.setInventorySlotContents(i, k);
		}
		}
		}
		//Hammer
		for(int i=0; i < craftMatrix.getSizeInventory(); i++)
		{               
		if(craftMatrix.getStackInSlot(i) != null)
		{
		ItemStack j = craftMatrix.getStackInSlot(i);
		if(j.getItem() != null && j.getItem() == MeteorusItemHandler.Hammer)
		{
		         ItemStack k = new ItemStack(MeteorusItemHandler.Hammer.itemID, 2, (j.getItemDamage() + 1));
		         if(k.getItemDamage() >= k.getMaxDamage()){
		         k.stackSize--;
		         }
		         craftMatrix.setInventorySlotContents(i, k);
		}
		}
		}
		//Achievement
		 if (item.itemID == MeteorusBlockHandler.ColoredStones.blockID)
         {
                 player.addStat(AchievementManager.TIME_OF_DECORATIONS, 1);
         }
		 
		 if (item.itemID == MeteorusBlockHandler.ColoredStoneBricks.blockID)
         {
                 player.addStat(AchievementManager.BRICKS_COLORS, 1);
         }
		 
		 if (item.itemID == MeteorusBlockHandler.ColoredStoneCarvedBricks.blockID)
         {
                 player.addStat(AchievementManager.BRICKS_CHISELED, 1);
         }
		 
		 if (item.itemID == MeteorusBlockHandler.ProjectTable.blockID)
         {
                 player.addStat(AchievementManager.PROJECT_TABLE, 1);
         }
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
		 if (item.itemID == MeteorusBlockHandler.ColoredStones.blockID)
         {
                 player.addStat(AchievementManager.TIME_OF_DECORATIONS, 1);
         }
	}

}
