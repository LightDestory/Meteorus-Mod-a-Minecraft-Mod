package meteorapegaso.meteorus.util;

import net.minecraft.src.ModLoader;

public class InformerPlayer
{

	public static void printText(String message)
	{
		ModLoader.getMinecraftInstance().thePlayer.addChatMessage(message);	
	}

}