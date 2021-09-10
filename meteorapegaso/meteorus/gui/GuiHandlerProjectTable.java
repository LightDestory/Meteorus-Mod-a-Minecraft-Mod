package meteorapegaso.meteorus.gui;

import meteorapegaso.meteorus.container.ContainerProjectTable;
import meteorapegaso.meteorus.tileentity.TileEntityProjectTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandlerProjectTable implements IGuiHandler {

	 @Override
   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
  
		 if (!world.blockExists(x, y, z))
				return null;

			TileEntity tile = world.getBlockTileEntity(x, y, z);

			switch (ID) {

			case 0:
				if (!(tile instanceof TileEntityProjectTable))
					return null;
				return new ProjectTableGui(player.inventory, (TileEntityProjectTable) tile);

			default:
				return null;
			}
   }
	
	
	  @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
   
		  if (!world.blockExists(x, y, z))
				return null;

			TileEntity tile = world.getBlockTileEntity(x, y, z);

			switch (ID) {

			case 0:
				if (!(tile instanceof TileEntityProjectTable))
					return null;
				return new ContainerProjectTable(player.inventory, (TileEntityProjectTable) tile);


			default:
				return null;
			}
   
	  }
	
}