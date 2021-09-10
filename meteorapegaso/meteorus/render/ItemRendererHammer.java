package meteorapegaso.meteorus.render;

import meteorapegaso.meteorus.model.HammerModel;
import meteorapegaso.meteorus.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class ItemRendererHammer implements IItemRenderer {

	protected HammerModel modelhammer;
	public static final ResourceLocation hammerLoc = new ResourceLocation (Reference.MOD_IDFOLDER + ":" + "textures/entity/item3D/hammer.png");
	public ItemRendererHammer(){
		modelhammer = new HammerModel();
	}
	
	@Override
	public boolean handleRenderType(ItemStack ItemStack, ItemRenderType ItemRenderType) 
	{
		switch(ItemRenderType)
		{
			case EQUIPPED_FIRST_PERSON:
			    return true;
		case EQUIPPED:
 			    return true;
		default: return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType ItemRenderType, ItemStack ItemStack, ItemRendererHelper ItemRendererHelper) 
	{
		return false;
	}


	// Rendering The Item In Your Hand

	@Override
	public void renderItem(ItemRenderType ItemRenderType, ItemStack ItemStack, Object... Data) 
	{
		switch(ItemRenderType)
		{
			case EQUIPPED: 
			{
				Minecraft.getMinecraft().renderEngine.bindTexture(hammerLoc);
				

				GL11.glPushMatrix();
				float Scale = 1.5F;

				GL11.glRotatef(-130F, 0, 0F, 0.0625F);
				GL11.glTranslatef(-0.6F, -0.3F, -0.1F);
				GL11.glScalef(Scale, Scale, Scale);
				modelhammer.render((Entity)Data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                
                		GL11.glPopMatrix();
                	
		}
			break;
            case EQUIPPED_FIRST_PERSON:
            {
				Minecraft.getMinecraft().renderEngine.bindTexture(hammerLoc);
				

				GL11.glPushMatrix();
				float Scale = 1.75F;

				GL11.glRotatef(-150F, 0, 0F, 0.0625F);
				GL11.glTranslatef(-0.9F, -0.3F, 0.F);
				GL11.glScalef(Scale, Scale, Scale);
				modelhammer.render((Entity)Data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                
                		GL11.glPopMatrix();
                	
		}            break;
	default:
	break;
	}
		
}
}
