package root;


import java.util.ArrayList;

import listeners.ChatListener;

public class EventManager {
	private ArrayList<ChatListener> allListeners;
	
	public EventManager(){
		allListeners=new ArrayList<ChatListener>();
	}
	
	/*
	 * fires respond() in the listeners registered for the command
	 */
	public void fireEvent(String msg){
		String cmd;
		try{
			cmd=msg.split(" ")[0];
		}catch(ArrayIndexOutOfBoundsException x){
			//one word commands
			cmd=msg;
		}
		for(ChatListener l:allListeners){
			for(String ac:l.getCommands()){
				if(ac.equalsIgnoreCase(cmd)){
					String relevant=msg.replace(cmd, "").trim();
					l.respond(relevant,ac);
					break;
				}
			}
			
		}
	}
	
	/*
	 * registers an extension of ChatListener to the ArrayList
	 */
	public void addListener(ChatListener cl){
		allListeners.add(cl);
	}
	/*
	 * removes a listener from this manager
	 */
	public void removeListener(ChatListener cl){
		for(ChatListener x:allListeners){
			if(x.equals(cl)){
				allListeners.remove(x);
			}
		}
	}
}
