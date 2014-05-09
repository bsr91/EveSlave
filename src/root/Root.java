package root;

import interfaces.Listenable;
import listeners.*;


public class Root implements Listenable{
	private LogParser logParser;
	public static void main(String[] args){
		new Root();
	}
	public Root(){		
		EventManager.acceptListeners();
		EventManager.addInternalListener(this);
		
		//test listener
		TestListener test=new TestListener(new String[]{"!say"});
		EventManager.addInternalListener(test);
		
		//settings modification listener
		EventManager.addChatListener(new SettingsListener(new String[]{"!addUser","!delUser","!setChannel"}));
		//market listener
		EventManager.addChatListener(new MarketListener(new String[]{"!jita","!amarr","!dodix","!rens"}));
		
		logParser=new LogParser();
		logParser.parse();
		
		new EventManager().fireInternalEvent(InternalEvent.TEST_EVENT);
	}
	@Override
	public void respond(String msg, String cmd) {/*not used here*/}
	@Override
	public void respond(InternalEvent x) {
		if(x.getType()==InternalEvent.RELOAD_LOG_FILE){
			logParser.stopParse();
			logParser.parse();
		}
		
	}
	
	
	
	
	
}
