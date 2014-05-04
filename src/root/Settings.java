package root;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class Settings implements Serializable {
	private static final long serialVersionUID = 3420398591421596560L;
	
	private ArrayList<String> userArray;
	private String channelname;
	
	/*
	 * generates new filename String prefix
	 * based on channelname and datestamp
	 */
	public String getFileName(){
		String result=channelname+"_";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String fDate=sdf.format(new Date());

		result=result+fDate;
		return result;
	}
	
	/*
	 * adds a character to chararray
	 */
	public void addUser(String c){
		if(userArray==null){
			userArray=new ArrayList<String>();
		}
		userArray.add(c);
	}
	/*
	 * removes a char from chararray
	 */
	public void removeUser(String c){
		if(userArray!=null){
			for(String a_char:userArray){
				if(a_char.equals(c)){
					userArray.remove(a_char);
				}
			}
		}
	}
	/*
	 * here be Getters/Setters
	 */
	public ArrayList<String> getUserArray() {
		return userArray;
	}
	public String getChannelname() {
		return channelname;
	}
	public void setCharArray(ArrayList<String> chararray) {
		this.userArray = chararray;
	}
	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}
}
