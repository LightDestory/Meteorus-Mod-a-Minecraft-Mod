package meteorapegaso.meteorus.api.jadarstudios.developercapes_modificated_for_meteorusmod_by_MeteoraPegaso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureObject;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
public class DevCapes {

    private static DevCapes instance;
    private HashMap<String, String> users;
    private HashMap<String, ResourceLocation> capeResources;
    private HashMap<String, ThreadDownloadImageData> downloadThreads;

    private DevCapesTickHandler tickHandler = null;

    private DevCapes() {
        users = new HashMap<String, String>();
        capeResources = new HashMap<String, ResourceLocation>();
        downloadThreads = new HashMap<String, ThreadDownloadImageData>();
    }
    public static DevCapes getInstance() {
        if (instance == null){
            instance = new DevCapes();
        }
        return instance;
    }
    public void addFileUrl(String parTxtUrl) {
        try{
            URL url = new URL(parTxtUrl);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            String username = "";
            String group = "";
            String capeUrl = "";

            while ((line = reader.readLine()) != null){

            	if (!line.startsWith("#")){
                    for (int i = 0; i < line.length(); i++){
                        if (line.charAt(i) == '='){
                            group = line.substring(0, i);
                            String subLine = line.substring(i + 1);

                            if (subLine.startsWith("http")){
                                capeUrl = subLine;

                                ResourceLocation r = new ResourceLocation("MeteorusCape/" + group);
                                ThreadDownloadImageData t = makeDownloadThread(r, capeUrl, null, new DevCapesImageBufferDownload());
                                this.addCapeResource(group, r);
                                this.addDownloadThread(group, t);

                                continue;
                            }else{
                                username = subLine.toLowerCase();
                                addUser(username, group);
                            }
                        }
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        if (tickHandler == null){
            tickHandler = new DevCapesTickHandler();
            TickRegistry.registerTickHandler(tickHandler, Side.CLIENT);
        }

    }

    public void addUser(String parUsername, String parGroup) {
        if (getUserGroup(parUsername) == null){
            users.put(parUsername, parGroup);

        }
    }

    public String getUserGroup(String parUsername) {
        return users.get(parUsername.toLowerCase());
    }

    public void addCapeResource(String parGroup, ResourceLocation parResource) {
        if (getCapeResource(parGroup) == null){
            capeResources.put(parGroup, parResource);
        }
    }

    public ResourceLocation getCapeResource(String parGroup) {
        return capeResources.get(parGroup);
    }

    public void addDownloadThread(String parGroup, ThreadDownloadImageData parResource) {
        if (getDownloadThread(parGroup) == null){
            downloadThreads.put(parGroup, parResource);
        }
    }

    public ThreadDownloadImageData getDownloadThread(String parGroup) {
        return downloadThreads.get(parGroup);
    }

    public static ThreadDownloadImageData makeDownloadThread(ResourceLocation par0ResourceLocation, String par1Str, ResourceLocation par2ResourceLocation, IImageBuffer par3IImageBuffer)
    {
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();

        TextureObject object = new ThreadDownloadImageData(par1Str, par2ResourceLocation, par3IImageBuffer);
        texturemanager.loadTexture(par0ResourceLocation, object);

        return (ThreadDownloadImageData)object;
    }
}