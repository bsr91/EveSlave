package root;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class LogParser{

	private boolean stop;
	private Settings settings;
	public LogParser(Settings settings){
		this.settings=settings;
		stop=false;
		
	}

	/*
	 * parses log file and sends lines to event manager
	 */
	public void parse(File log){
		if(log==null){
			System.out.println("Log file not found");
			
		}else if(settings.getUserArray()!=null){
			stop=false;
			try {
				System.out.println("Reading file: "+log.getName());
				BufferedReader f_in=new BufferedReader(new InputStreamReader(new FileInputStream(log),"UTF-16"));
				while(!stop){
					String line=f_in.readLine();

					//hold Thread if line is null
					if(line==null){
						Thread.sleep(1000);
					}

					//checks line is chat (rather than server/channel message)
					//[or] clause to workaround timestamping
					else if(line.startsWith(getLineStartIndicator())||line.startsWith("?"+getLineStartIndicator())){
						line.trim();					
						String[] split=line.split(">");
						
						//filters for people typing delimit (">") in message
						if(split.length==2){
							String pre=split[0];
							String msg=split[1].trim();
							

							//filters other peoples messages and ensures message is bot command
							//then tells EventManager to fire an event
							if(isValidPrefix(pre)&&msg.startsWith("!")){
								EventManager.fireChatEvent(msg);
							}
						}
					}

				}
				f_in.close();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("No character setting found.");
		}
	}

	/*
	 * gets start of input text regex (ie formatted datestamp)
	 */
	private String getLineStartIndicator(){
		SimpleDateFormat sdf=new SimpleDateFormat("[ yyyy.MM.dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String nowEve=sdf.format(new Date());
		return nowEve;
	}
	
	/*
	 * Checks if premessage is valid for any registered chars in settings
	 */
	private boolean isValidPrefix(String pre){
		for(String c:settings.getUserArray()){
			if(pre.contains(c)){
				return true;
			}
		}
		return false;
	}

	/*
	 * stops loop
	 */
	public void stopParse(){
		stop=true;
	}


}
