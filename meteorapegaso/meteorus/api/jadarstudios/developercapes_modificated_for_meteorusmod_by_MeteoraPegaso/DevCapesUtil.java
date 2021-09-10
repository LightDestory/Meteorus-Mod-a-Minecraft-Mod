package meteorapegaso.meteorus.api.jadarstudios.developercapes_modificated_for_meteorusmod_by_MeteoraPegaso;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class DevCapesUtil {

    @Deprecated
    public static DevCapes getInstance() {
        if(FMLCommonHandler.instance().getSide().equals(Side.CLIENT))
            return DevCapes.getInstance();
        else {
            System.out.println("[SEVERE] [DevCapes] **Someone tried to call DevCapesUtil.getInstance() on a server! If you are attempting to add a file url then use DevCapesUtil.addFileUrl().**");
            return null;
        }
    }
public static void addFileUrl(String parTxtUrl) {
        if(FMLCommonHandler.instance().getSide().equals(Side.CLIENT))
            getInstance().addFileUrl(parTxtUrl);
    }
}
