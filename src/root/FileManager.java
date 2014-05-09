package root;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class FileManager {
	private static final File settingsxml=new File("settings.xml");;
	private static Settings s;
	public FileManager(){	}
	/*
	 * reads URL into AL<String>
	 */
	public ArrayList<String> readURL(URL url){
		ArrayList<String> r=new ArrayList<String>();
		URL u=url;
		try{
			BufferedReader in=new BufferedReader(new InputStreamReader(u.openStream()));
			String line;
			while((line=in.readLine())!=null){
				r.add(line);
			}
			in.close();
		}catch(IOException x){
			x.printStackTrace();
		}
		
		return r;
	}
	/*
	 * reads file into AL<String> for make simple I/O
	 */
	public ArrayList<String> readFile(File file){
		ArrayList<String> r=new ArrayList<String>();
		File f=file;
		try{
			BufferedReader in=new BufferedReader(new FileReader(f));
			String line;
			while((line=in.readLine())!=null){
				r.add(line);
			}
			in.close();
		}catch(IOException x){
			x.printStackTrace();
		}

		return r;
	}
	/*
	 * writes AL<String> to File
	 */
	public void writeToFile(ArrayList<String> content, File file){
		try{
			if(file.exists()){
				File temp=new File("temp.txt");
				temp.createNewFile();
				file.delete();
				temp.renameTo(file);

			}else{
				file.createNewFile();
			}
			
			BufferedWriter out=new BufferedWriter(new FileWriter(file));
			for(String s:content){
				out.write(s);
				out.newLine();
			}
			out.close();
		}catch(IOException x){
			x.printStackTrace();
		}
	}
	/*
	 * determines the file to parse logs from
	 * returns most recent file with correct channel name
	 */
	public File getNewLogFile(){
		String filename=s.getFileName();
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
		if(!(logAList.size()==0)){
			result=logAList.get(logAList.size()-1);
		}
		return result;
	}

	/*
	 * load settings from XML into Settings object
	 * returns settings object
	 */
	private static void loadSettings(){
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
	 * method shortcut
	 */
	public void saveSettings(){
		writeObjectToXML(s,settingsxml);
	}
	/*
	 * Writes object as XML to file
	 */
	public void writeObjectToXML(Object c,File f){
		//wipes old file
		try{
			if(f.exists()){
				File temp=new File("temp.xml");
				temp.createNewFile();
				f.delete();
				temp.renameTo(f);
			}else{
				f.createNewFile();
			}
		}catch(IOException e){
			e.printStackTrace();
		}

		//writes new file
		try{
			XStream xml=new XStream(new StaxDriver());
			BufferedWriter out=new BufferedWriter(new FileWriter(f));
			String x=xml.toXML(c);
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
