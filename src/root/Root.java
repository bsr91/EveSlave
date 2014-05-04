package root;

import interfaces.Listenable;
import listeners.*;


public class Root implements Listenable{
	private FileManager fileManager;
	private LogParser logParser;
	private Settings settings;
	private EventManager em;
	public static void main(String[] args){
		new Root();
	}
	public Root(){
		
		fileManager=new FileManager();
		settings=fileManager.getSettings();
		
		em=new EventManager();
		em.addInternalListener(this);
		
		
		
		//test listener
		em.addChatListener(new TestListener(new String[]{"!say"}));
		//settings modification listener
		em.addChatListener(new SettingsListener(new String[]{"!addUser","!delUser","!setChannel"},fileManager));
		
		logParser=new LogParser(settings, em);
		
		//start read
		logParser.parse(fileManager.getNewLogFile(settings.getFileName()));
	}
	@Override
	public void respond(String msg, String cmd, EventManager em) {/*not used here*/}
	@Override
	public void respond(InternalEvent x) {
		if(x.getType()==InternalEvent.RELOAD_LOG_FILE){
			logParser.stopParse();
			logParser.parse(fileManager.getNewLogFile(settings.getFileName()));
		}
		
	}
	
	
	
	
	
}
