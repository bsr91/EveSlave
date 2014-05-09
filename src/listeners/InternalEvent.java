package listeners;

public class InternalEvent {
	public final static int RELOAD_LOG_FILE=0;
	public final static int TEST_EVENT=1;
	
	private static int type;
	public InternalEvent(final int type){
		InternalEvent.type=type;
	}
	public final int getType(){
		return type;
	}
}
