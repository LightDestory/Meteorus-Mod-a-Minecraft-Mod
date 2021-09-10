package meteorapegaso.meteorus.api.jadarstudios.developercapes_modificated_for_meteorusmod_by_MeteoraPegaso;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DevCapesTickHandler implements ITickHandler {

	private static final Minecraft mc = Minecraft.getMinecraft();
	private static final DevCapes instance = DevCapesUtil.getInstance();
	private boolean debug = false;


	private int counter = 0;
	private boolean notified = false;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
if ((mc.theWorld != null) && (mc.theWorld.playerEntities.size() > 0)){
			@SuppressWarnings("unchecked")
			List<AbstractClientPlayer> players = mc.theWorld.playerEntities;
			if(counter >= players.size())
				counter = 0;

			AbstractClientPlayer p = players.get(counter);
			if(p != null) {

				String lowerUsername = p.username.toLowerCase();

				if (instance.getUserGroup(lowerUsername) != null){
					if (!p.downloadImageCape.isTextureUploaded()) {
						String userGroup = instance.getUserGroup(lowerUsername);

						if(debug)
							System.out.println("Changing the cape of: " + p.username);
						p.locationCape = instance.getCapeResource(userGroup);
						p.downloadImageCape = instance.getDownloadThread(userGroup);
					}
				}
			}

			counter++;
		}
	}
	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return "DeveloperCapesTickHandler";
	}
}
