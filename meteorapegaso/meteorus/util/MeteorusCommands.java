package meteorapegaso.meteorus.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatMessageComponent;

public class MeteorusCommands implements ICommand {
	
	 String[] main;
	 String[] info;
	 String[] credits;
	 String[] nowChangelog;
	private List aliases;{
		 this.aliases = new ArrayList();
		 this.aliases.add("meteorus");
		 this.aliases.add("mete");
		 this.aliases.add("mt");
		 }
		public MeteorusCommands(){
			 main = new String[7];
			 info = new String[4];
			 credits = new String[3];
			 nowChangelog = new String[3];
			 for(int i = 0; i < main.length; i++){
				 main[i] = "";
			 }
			 for(int i = 0; i < info.length; i++){
				 info[i] = "";
			 }
			 for(int i = 0; i < credits.length; i++){
				 credits[i] = "";
			 }
			 for(int i = 0; i < nowChangelog.length; i++){
				 nowChangelog[i] = "";
			 }
			 main[0] = "\u00A72~~~~~~~~~~~~~ \u00A74Meteorus Mod Commands \u00A7aWORK IN PROGRESS \u00A72~~~~~~~~~~~~~";
			 main[1] = "\u00A72|\u00A74 /meteorus version \u00A7a- \u00A74Show the version of the installed mod \u00A72|";
			 main[2] = "\u00A72|\u00A74 /meteorus changelog \u00A7a- \u00A74Show the changelog of the installed version \u00A72|";
			 main[3] = "\u00A72|\u00A74 /meteorus check \u00A7a- \u00A74Check for new update of the mod and show changelog \u00A72|";
			 main[4] = "\u00A72|\u00A74 /meteorus info \u00A7a- \u00A74Show more info of the mod \u00A72                   |";
			 main[5] = "\u00A72|\u00A74 /meteorus credits \u00A7a- \u00A74Show all credits of the mod \u00A72              |";
			 main[6] = "\u00A72~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
			 
			 info[0] = "\u00A72~~~~~~~~~~~ \u00A74Meteorus Mod Info \u00A72~~~~~~~~~~~";
			 info[1] = "\u00A72|\u00A74 Mod Name: \u00A7a" + Reference.MOD_NAME + "  |   " + "\u00A74 Mod Version: " + Reference.MOD_VERSIONc + "     |";
			 info[2] = "\u00A72|\u00A74 Author: \u00A7a" + Reference.MOD_AUTHORc + "   |  " + "\u00A74 Release Date: " + Reference.MOD_RELEASEDATEc + "   |";
			 info[3] = "\u00A72~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
			 
			 credits[0] = "\u00A72~~~~~~~~~~~ \u00A74Meteorus Mod Credits \u00A72~~~~~~~~~~~";
			 credits[1] = "WORK IN PROGRESS!";
			 credits[2] = "\u00A72~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
			 
			 nowChangelog[0] = "\u00A72~~~~~~~~~~~ \u00A74Meteorus Mod 0.1 ChangeLog \u00A72~~~~~~~~~~~";
			 nowChangelog[1] = "\u00A7a*First Version = No Changelog";
			 nowChangelog[2] = "\u00A72~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
		 } 
	@Override
	public int compareTo(Object arg0) {
		return 0;
	}
	@Override
	public String getCommandName() {
		return "/meteorus";
	}
	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/meteorus <text>";
	}
	@Override
	public List getCommandAliases() {
		return this.aliases;
	}
	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		//Main
		if(astring.length ==0)
	    {
			for(int x = 0; x < this.main.length; x++){
				icommandsender.sendChatToPlayer(ChatMessageComponent.createFromText(this.main[x]));
			}
		      return;
	    }
		// End Main
		// Version
		if(astring[0].equals("version"))
	    {
			      icommandsender.sendChatToPlayer(ChatMessageComponent.createFromText("\u00A74[\u00A7aMeteorus\u00A74] \u00A7aYou have installed the " + (Reference.MOD_VERSIONc) + " version."));
			      return;
	    }
		// End Version
		// ChangeLog
		if(astring[0].equals("changelog"))
	    {
			for(int x = 0; x < this.nowChangelog.length; x++){
				icommandsender.sendChatToPlayer(ChatMessageComponent.createFromText(this.nowChangelog[x]));
			}
			return;
	    }
		// End ChangeLog
	    // Check
		if(astring[0].equals("check"))
		{
			try{
				 URL checkupdate = new URL(Reference.UPDATE_URL);
				 BufferedReader in = new BufferedReader(new InputStreamReader(checkupdate.openStream()));
				 String onlineversion = in.readLine();
				 if(!Reference.MOD_VERSION.equals(onlineversion)) {
					 icommandsender.sendChatToPlayer(ChatMessageComponent.createFromText("\u00A74[\u00A7aMeteorus\u00A74] \u00A7cThere is a new version of the mod (\u00A74 " + onlineversion + " \u00A7c).Please update it!"));
				 } else{
					 icommandsender.sendChatToPlayer(ChatMessageComponent.createFromText("\u00A74[\u00A7aMeteorus\u00A74] \u00A7cThere isn't a new version of the mod."));
				 }
				 onlineversion = "";
			} catch (Exception offline){
				icommandsender.sendChatToPlayer(ChatMessageComponent.createFromText("\u00A74[\u00A7aMeteorus\u00A74] \u00A7cFailed to check, the connection is not available!"));
			}
		}
		//End Check
		//info
		if(astring[0].equals("info"))
	    {
			for(int x = 0; x < this.info.length; x++){
				icommandsender.sendChatToPlayer(ChatMessageComponent.createFromText(this.info[x]));
			}
			return;
	    }
		//End info
		// Credits
		if(astring[0].equals("credits"))
	    {
			for(int x = 0; x < this.credits.length; x++){
				icommandsender.sendChatToPlayer(ChatMessageComponent.createFromText(this.credits[x]));
			}
			return;
	    }
		//End Credits
		if(!astring[0].equals("version") && !astring[0].equals("check") && !astring[0].equals("info") && !astring[0].equals("credits") && !astring[0].equals("changelog")){
	    icommandsender.sendChatToPlayer(ChatMessageComponent.createFromText("\u00A74[\u00A7aMeteorus\u00A74]\u00A7c /meteorus " + astring[0] + " not exist!"));
		}
	}
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
		return true;
	}
	@Override
	public List addTabCompletionOptions(ICommandSender icommandsender,
			String[] astring) {
		return null;
	}
	@Override
	public boolean isUsernameIndex(String[] astring, int i) {
		return false;
	}
}