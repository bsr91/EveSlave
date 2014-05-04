package root;


import interfaces.Listenable;

import java.util.ArrayList;

import listeners.ChatListener;
import listeners.InternalEvent;

public class EventManager {
	private ArrayList<ChatListener> allChatListeners;
	private ArrayList<Listenable> allInternalListeners;
	
	public EventManager(){
		allChatListeners=new ArrayList<ChatListener>();
		allInternalListeners=new ArrayList<Listenable>();
	}
	
	/*
	 * fires respond() in the listeners registered for the command from chat
	 */
	public void fireChatEvent(String msg){
		String cmd;
		try{
			cmd=msg.split(" ")[0];
		}catch(ArrayIndexOutOfBoundsException x){
			//one word commands
			cmd=msg;
		}
		for(ChatListener l:allChatListeners){
			for(String ac:l.getCommands()){
				if(ac.equalsIgnoreCase(cmd)){
					String relevant=msg.replace(cmd, "").trim();
					l.respond(relevant,ac,this);
					break;
				}
			}
			
		}
	}
	
	/*
	 * fires respond() in internal listeners
	 */
	public void fireInternalEvent(InternalEvent x){
		for(Listenable i:allInternalListeners){
			i.respond(x);
		}
	}
	
	/*
	 * registers an extension of ChatListener to the ArrayList
	 */
	public void addChatListener(ChatListener cl){
		allChatListeners.add(cl);
	}
	/*
	 * removes a listener from this manager
	 */
	public void removeChatListener(ChatListener cl){
		for(ChatListener x:allChatListeners){
			if(x.equals(cl)){
				allChatListeners.remove(x);
			}
		}
	}
	
	/*
	 * registers and extension of InternalListener to the ArrayList
	 */
	public void addInternalListener(Listenable i){
		allInternalListeners.add(i);
	}
	/*
	 * removes a listener from this manager
	 */
	public void removeInternalListener(Listenable i){
		for(Listenable i_:allInternalListeners){
			if(i_.equals(i)){
				allInternalListeners.remove(i_);
			}
		}
	}
}
