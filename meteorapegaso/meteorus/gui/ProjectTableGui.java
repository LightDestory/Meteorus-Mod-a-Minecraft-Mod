package meteorapegaso.meteorus.gui;

import meteorapegaso.meteorus.container.ContainerProjectTable;
import meteorapegaso.meteorus.tileentity.TileEntityProjectTable;
import meteorapegaso.meteorus.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class ProjectTableGui extends GuiContainer {
	
	public static final ResourceLocation sa = new ResourceLocation(Reference.MOD_IDFOLDER + ":" + "textures/gui/container/project_table.png");
	 
	public ProjectTableGui(InventoryPlayer player_inventory, TileEntityProjectTable tile){
        super(new ContainerProjectTable(player_inventory, tile));
}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j){
        fontRenderer.drawString("Project Table", 75, 6, 0x404040);
 
	}


	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j){
       
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
       
        this.mc.renderEngine.bindTexture(sa);
       
        int x = (width - xSize) / 2;
       
        int y = (height - ySize) / 2;
       
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}