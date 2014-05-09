package listeners;

import root.EventManager;
import root.FileManager;

public class SettingsListener extends ChatListener {
	private FileManager fm;
	public SettingsListener(String[] cmd){
		super(cmd);
		fm=new FileManager();
	}

	@Override
	public void respond(String msg, String cmd) {
		//adds user to settings array
		if(cmd.equalsIgnoreCase("!addUser")){
			fm.getSettings().addUser(msg);
			fm.saveSettings();
			return;
		}
		//removes user from settings array if it exists
		if(cmd.equalsIgnoreCase("!delUser")){
			for(String user:fm.getSettings().getUserArray()){
				if(user.equals(msg)){
					fm.getSettings().removeUser(user);
					fm.saveSettings();
					return;
				}
			}
		}
		//sets the channel to parse from
		//dont set this to a channel you arent in 
		if(cmd.equalsIgnoreCase("!setChannel")){
			fm.getSettings().setChannelname(msg);
			fm.saveSettings();
			EventManager em=new EventManager();
			em.fireInternalEvent(InternalEvent.RELOAD_LOG_FILE);
			return;
		}

	}
	
	
	public void respond(InternalEvent x){/*na*/}

}
