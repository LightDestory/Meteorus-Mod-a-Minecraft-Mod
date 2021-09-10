package meteorapegaso.meteorus.client;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import meteorapegaso.meteorus.blocks.MeteorusBlockHandler;
import meteorapegaso.meteorus.items.MeteorusItemHandler;
import meteorapegaso.meteorus.render.ItemRendererChisel;
import meteorapegaso.meteorus.render.ItemRendererHammer;
import meteorapegaso.meteorus.render.TileEntityProjectTableHandlerRenderer;
import meteorapegaso.meteorus.render.TileEntityProjectTableRenderer;
import meteorapegaso.meteorus.server.ServerProxy;
import meteorapegaso.meteorus.tileentity.TileEntityProjectTable;

public class ClientProxy extends ServerProxy {
	
	public static int BlockProjectTableRenderID;
	@Override
    public void registerRenderers() {
		//Render IDs
		BlockProjectTableRenderID = RenderingRegistry.getNextAvailableRenderId();
		//Rendering ProjectTable
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityProjectTable.class, new TileEntityProjectTableRenderer());
		MinecraftForgeClient.registerItemRenderer(MeteorusBlockHandler.ProjectTable.blockID, new TileEntityProjectTableHandlerRenderer());
		//Hammer and Chisel
		MinecraftForgeClient.registerItemRenderer(MeteorusItemHandler.Chisel.itemID,(IItemRenderer) new ItemRendererChisel());
		MinecraftForgeClient.registerItemRenderer(MeteorusItemHandler.Hammer.itemID,(IItemRenderer) new ItemRendererHammer());
}
	@Override
    public int addArmor(String armor) {
		return 0;
    }
}
