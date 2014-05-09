package listeners;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import root.Clip;
import root.EveSystem;
import root.FileManager;

public class MarketListener extends ChatListener {

	private final String iskUrl="http://api.eve-central.com/api/marketstat?usesystem=";
	private final String i="&typeid=";
	
	private final EveSystem jita=new EveSystem("Jita","30000142","10000002","!jita");
	private final EveSystem dodixie=new EveSystem("Dodixie","30002659","10000032","!dodix");
	private final EveSystem amarr=new EveSystem("Amarr","30002187","10000043","!amarr");
	private final EveSystem rens=new EveSystem("Rens","30002510","10000030","!rens");
	private ArrayList<EveSystem> sysList;
	
	private final File itemfile=new File("itemIDs.txt");
	public MarketListener(String[] cmds) {
		super(cmds);
		sysList=new ArrayList<EveSystem>();
		sysList.add(jita);
		sysList.add(amarr);
		sysList.add(dodixie);
		sysList.add(rens);
	}

	@Override
	public void respond(String msg, String cmd) {

		//price check - works with jita, amarr, dodixie and rens
		EveSystem sys=getSystem(cmd);
		String r="Error: Item not found.";
		FileManager fm=new FileManager();
		ArrayList<String> items=fm.readFile(itemfile);

		for(String s:items){
			String id=s.split("=")[0];
			String name=s.split("=")[1];

			if(name.equalsIgnoreCase(msg)){
				String u=iskUrl+sys.getSysID()+i+id;
				String[] sellNodes={"sell","min"};
				String[] buyNodes={"buy","max"};
				String sell="ERROR";
				String buy="ERROR";

				try{
					Document doc=Jsoup.connect(u).get();
					//get min sell price
					for(Element e:doc.select(sellNodes[0])){
						double sell_=Double.parseDouble(e.select(sellNodes[1]).text());
						sell=NumberFormat.getIntegerInstance().format(sell_);
					}
					//get max buy price
					for(Element e:doc.select(buyNodes[0])){
						double buy_=Double.parseDouble(e.select(buyNodes[1]).text());
						buy=NumberFormat.getIntegerInstance().format(buy_);
					}
				}catch(IOException x){
					x.printStackTrace();
				}finally{
					//build return String
					r="["+name+"] "+sys.getName()+" Sell: "+sell+" ISK |#| "+sys.getName()+" Buy: "+buy+" ISK";
				}

				break;
			}
		}		
		Clip.put(r);

	}
	private EveSystem getSystem(String cmd){
		EveSystem r=null;
		for(EveSystem s:sysList){
			if(s.getCmd().equals(cmd)){
				r=s;
			}
		}
		return r;
	}

	@Override
	public void respond(InternalEvent x) {/*nope*/}
	
	//for determining which system to use for market pull based on command entered
	

}
