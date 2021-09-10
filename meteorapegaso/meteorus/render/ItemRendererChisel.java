package meteorapegaso.meteorus.render;

import org.lwjgl.opengl.GL11;

import meteorapegaso.meteorus.model.ChiselModel;
import meteorapegaso.meteorus.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

public class ItemRendererChisel implements IItemRenderer {

	protected ChiselModel modelhammer;
	public static final ResourceLocation hammerLoc = new ResourceLocation (Reference.MOD_IDFOLDER + ":" + "textures/entity/item3D/chisel.png");
	public ItemRendererChisel(){
		modelhammer = new ChiselModel();
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
				float Scale = 1.25F;

				GL11.glRotatef(-100F, 0F, 0F, 0.0625F);
				GL11.glTranslatef(-0.8F, 0.6F, -0.1F);
				GL11.glScalef(Scale, Scale, Scale);
				modelhammer.render((Entity)Data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                
                		GL11.glPopMatrix();
                	
		}
			break;
            case EQUIPPED_FIRST_PERSON:
            {
				Minecraft.getMinecraft().renderEngine.bindTexture(hammerLoc);
				

				GL11.glPushMatrix();
				float Scale = 1.3F;

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
