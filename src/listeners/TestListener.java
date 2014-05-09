package listeners;


public class TestListener extends ChatListener{
	
	/**
	 * Nothing in this class is real or an accurate representation of 
	 * any active ChatListener subclass.
	 * 
	 * the inherited respond() methods are for testing code blocks 
	 * and firing artificial chat commands without needing chat commands
	 * 
	 * TEST CLASS, PLEASE IGNORE
	 * 
	 */
	public TestListener(String[] cmd) {
		super(cmd);
	}

	@Override
	public void respond(String msg, String cmd) {
		System.out.println(msg);

	}	

	public void respond(InternalEvent x){
		if(x.getType()==InternalEvent.TEST_EVENT){

		}
	}
}
