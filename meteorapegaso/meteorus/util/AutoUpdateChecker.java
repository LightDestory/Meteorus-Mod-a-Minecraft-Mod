package meteorapegaso.meteorus.util;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;

public class AutoUpdateChecker implements IConnectionHandler
{
	public static String modName;
	public static String currentVersion;
	public static String url;
	public static String connection;

	public AutoUpdateChecker()
	{
		this.modName = Reference.MOD_NAME;
		this.currentVersion = Reference.MOD_VERSION;
		this.url = Reference.UPDATE_URL;
	}

	public static void getUpdate(String modName, String currentVersion, String url)
	{
		String newVersion = getNewVersion(url);
		int currentTick = 1;

		if(currentTick == 1)
		{
			currentTick++;

			if(currentVersion.equals(newVersion) && connection.equals("available"))
			{
				System.out.println(modName + " " + currentVersion + " is up to date");
				InformerPlayer.printText(("\u00A74[\u00A7aMeteorus\u00A74] \u00A7cThere isn't a new version of the mod. \u00A74You can disable auto check into config file."));
			}
			if(!currentVersion.equals(newVersion) && connection.equals("available"))
			{
				System.out.println("A new version " + newVersion + " is available for " + modName);
				InformerPlayer.printText(("\u00A74[\u00A7aMeteorus\u00A74] \u00A7cThere is a new version of the mod( \u00A74" + newVersion + " \u00A7c).Please update it!. \u00A74You can disable auto check into config file."));
			}
			
			if(connection.equals("not available"))
			{
				System.out.println("No connection!");
				InformerPlayer.printText(("\u00A74[\u00A7aMeteorus\u00A74] \u00A7cNo connection avaible!. \u00A74You can disable auto check into config file."));
			}
		}
	}

	public static String getNewVersion(String address)
    {
		String newVersion = null;
    	try 
    	{
	    	URL url = new URL(address);
			Scanner scanner = new Scanner(url.openStream());
			while (scanner.hasNextLine())
			{
                newVersion = scanner.nextLine(); 
            }
			scanner.close();
			connection = "available";
		} 
    	catch (IOException ex)
    	{
			ex.printStackTrace();
			connection = "not available";
		}
    	return newVersion;
    }

	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager)
	{
	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager)
	{
	return "";
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager)
	{
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager)
	{
	}

	@Override
	public void connectionClosed(INetworkManager manager)
	{
	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login)
	{
		getUpdate(modName, currentVersion, url);
	}
}