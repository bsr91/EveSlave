package listeners;


public class TestListener extends ChatListener{

	public TestListener(String[] cmd) {
		super(cmd);
	}

	@Override
	public void respond(String msg, String cmd) {
		System.out.println(msg);

	}
	

}
