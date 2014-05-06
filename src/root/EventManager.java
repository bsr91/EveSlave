package root;


import interfaces.Listenable;

import java.util.ArrayList;

import listeners.ChatListener;
import listeners.InternalEvent;

public class EventManager {
	private static ArrayList<ChatListener> allChatListeners;
	private static ArrayList<Listenable> allInternalListeners;
	
	public static void acceptListeners(){
		allChatListeners=new ArrayList<ChatListener>();
		allInternalListeners=new ArrayList<Listenable>();
	}
	
	/*
	 * fires respond() in the listeners registered for the command from chat
	 */
	public static void fireChatEvent(String msg){
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
					l.respond(relevant,ac);
					break;
				}
			}
			
		}
	}
	
	/*
	 * fires respond() in internal listeners
	 */
	public static void fireInternalEvent(InternalEvent x){
		for(Listenable i:allInternalListeners){
			i.respond(x);
		}
	}
	
	/*
	 * registers an extension of ChatListener to the ArrayList
	 */
	public static void addChatListener(ChatListener cl){
		allChatListeners.add(cl);
	}
	/*
	 * removes a listener from this manager
	 */
	public static void removeChatListener(ChatListener cl){
		for(ChatListener x:allChatListeners){
			if(x.equals(cl)){
				allChatListeners.remove(x);
			}
		}
	}
	
	/*
	 * registers and extension of InternalListener to the ArrayList
	 */
	public static void addInternalListener(Listenable i){
		allInternalListeners.add(i);
	}
	/*
	 * removes a listener from this manager
	 */
	public static void removeInternalListener(Listenable i){
		for(Listenable i_:allInternalListeners){
			if(i_.equals(i)){
				allInternalListeners.remove(i_);
			}
		}
	}
}
