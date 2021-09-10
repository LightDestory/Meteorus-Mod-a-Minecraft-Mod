package meteorapegaso.meteorus.render;

import meteorapegaso.meteorus.model.ProjectTableModel;
import meteorapegaso.meteorus.tileentity.TileEntityProjectTable;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class TileEntityProjectTableHandlerRenderer implements IItemRenderer {

    private ProjectTableModel projectmodel;

    public TileEntityProjectTableHandlerRenderer() {
    	projectmodel = new ProjectTableModel();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        TileEntityRenderer.instance.renderTileEntityAt(new TileEntityProjectTable(), 0.0D, 0.0D, 0.0D, 0.0f);
    }

}
