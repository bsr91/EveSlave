package root;

import listeners.*;


public class Root {
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
		//test listener
		em.addListener(new TestListener(new String[]{"!say"}));
		//settings modification listener
		em.addListener(new SettingsListener(new String[]{"!addUser","!delUser"},fileManager));
		
		logParser=new LogParser(settings, em);
		logParser.parse(fileManager.getNewLogFile(settings.getFileName()));
	}
	
	
	
	
	
}
