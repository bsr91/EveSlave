package root;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class FileManager {
	private File settingsxml;
	private Settings s;
	public FileManager(){
		settingsxml=new File("settings.xml");
	}
	/*
	 * determines the file to parse logs from
	 * returns most recent file with correct channel name
	 */
	public File getNewLogFile(String filename){
		File result=null;
		File folder=new File("C:/Users/BenJ/Documents/EVE/logs/Chatlogs");
		File[] logList=folder.listFiles();
		ArrayList<File> logAList=new ArrayList<File>();
		for(File log:logList){
			String n=log.getName();
			if(n.startsWith(filename)){
				logAList.add(log);
			}
		}
		Collections.sort(logAList);
		if(logAList.size()==0){
			System.out.println("Log file not found");
		}else{
			result=logAList.get(logAList.size()-1);
		}
		return result;
	}
	
	/*
	 * load settings from XML into Settings object
	 * returns settings object
	 */
	private void loadSettings(){
		s=new Settings();
		try{
			XStream xml=new XStream(new StaxDriver());
			BufferedReader in=new BufferedReader(new FileReader(settingsxml));
			String line;
			while((line=in.readLine())!=null){
				s=(Settings)xml.fromXML(line);			
			}
			in.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	/*
	 * save settings to XML
	 */
	public void saveSettings(){
		//wipes old settings
		try{
			if(settingsxml.exists()){
				File temp=new File("settingstemp.xml");
				temp.createNewFile();
				settingsxml.delete();
				temp.renameTo(settingsxml);
			}else{
				settingsxml.createNewFile();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//writes new settings
		try{
			XStream xml=new XStream(new StaxDriver());
			BufferedWriter out=new BufferedWriter(new FileWriter(settingsxml));
			String x=xml.toXML(s);
			out.write(x);
			out.close();
		}catch(IOException e1){
			e1.printStackTrace();
		}
	}
	
	/*
	 * public settings object
	 */
	public Settings getSettings(){
		if(s==null){
			loadSettings();
		}
		return s;
	}
}
