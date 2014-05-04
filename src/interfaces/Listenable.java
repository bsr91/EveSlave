package interfaces;

import root.EventManager;
import listeners.InternalEvent;

public interface Listenable {
	public void respond(String msg, String cmd,EventManager em);
	public void respond(InternalEvent x);
}
