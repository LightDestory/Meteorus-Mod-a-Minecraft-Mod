package meteorapegaso.meteorus.render;

import meteorapegaso.meteorus.model.ProjectTableModel;
import meteorapegaso.meteorus.tileentity.TileEntityProjectTable;
import meteorapegaso.meteorus.util.Reference;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class TileEntityProjectTableRenderer extends TileEntitySpecialRenderer
{
	public static final ResourceLocation projectTableLoc = new ResourceLocation (Reference.MOD_IDFOLDER + ":" + "textures/entity/project_table/project_table.png");
        public TileEntityProjectTableRenderer()
        {
                aModel = new ProjectTableModel();
        }
 
        public void renderAModelAt(TileEntityProjectTable tileentity1, double d, double d1, double d2, float f)
        {  
                GL11.glPushMatrix();
                GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.52F, (float)d2 + 0.5F);
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(projectTableLoc);
                GL11.glRotatef(180F, 0F, 0F, 1F);
                bindTexture(projectTableLoc);
                GL11.glPushMatrix();
                aModel.renderModel(0.0625F);
                GL11.glPopMatrix();     
                GL11.glPopMatrix();                                     
        }
        public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2,
                        float f)
        {
                renderAModelAt((TileEntityProjectTable)tileentity, d, d1, d2, f);
        }
        private ProjectTableModel aModel;
}