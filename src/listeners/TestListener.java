package listeners;

import root.EventManager;


public class TestListener extends ChatListener{

	public TestListener(String[] cmd) {
		super(cmd);
	}

	@Override
	public void respond(String msg, String cmd,EventManager em) {
		System.out.println(msg);

	}	

	public void respond(InternalEvent x){/*na*/}
}
