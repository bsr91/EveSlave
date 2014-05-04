package listeners;

import root.FileManager;

public class SettingsListener extends ChatListener {
	private FileManager fm;
	public SettingsListener(String[] cmd, FileManager fm){
		super(cmd);
		this.fm=fm;
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
					fm.getSettings().getUserArray().remove(user);
					fm.saveSettings();
					return;
				}
			}
		}

	}

}
