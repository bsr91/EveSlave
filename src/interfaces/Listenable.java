package interfaces;

import listeners.InternalEvent;

public interface Listenable {
	public void respond(String msg, String cmd);
	public void respond(InternalEvent x);
}
