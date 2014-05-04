package listeners;

import root.EventManager;
import interfaces.Listenable;

public abstract class ChatListener implements Listenable {
	private String[] cmd;
	
	public ChatListener(String[] cmd){
		this.cmd=cmd;
	}
	
	public String[] getCommands(){
		return cmd;
	}
	@Override
	public abstract void respond(String msg, String cmd,EventManager em);
	@Override
	public abstract void respond(InternalEvent x);
	

}
