package root;

import interfaces.Listenable;
import listeners.*;


public class Root implements Listenable{
	private FileManager fileManager;
	private LogParser logParser;
	private Settings settings;
	public static void main(String[] args){
		new Root();
	}
	public Root(){
		
		fileManager=new FileManager();
		settings=fileManager.getSettings();
		
		EventManager.acceptListeners();
		EventManager.addInternalListener(this);
		
		
		
		//test listener
		EventManager.addChatListener(new TestListener(new String[]{"!say"}));
		//settings modification listener
		EventManager.addChatListener(new SettingsListener(new String[]{"!addUser","!delUser","!setChannel"},fileManager));
		
		logParser=new LogParser(settings);
		
		//start read
		logParser.parse(fileManager.getNewLogFile(settings.getFileName()));
	}
	@Override
	public void respond(String msg, String cmd) {/*not used here*/}
	@Override
	public void respond(InternalEvent x) {
		if(x.getType()==InternalEvent.RELOAD_LOG_FILE){
			logParser.stopParse();
			logParser.parse(fileManager.getNewLogFile(settings.getFileName()));
		}
		
	}
	
	
	
	
	
}
