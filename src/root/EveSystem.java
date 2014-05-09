package root;

public class EveSystem {
	private String name;
	private String sys;
	private String reg;
	private String cmd;
	public EveSystem(String name,String sys, String reg,String cmd){
		this.name=name;
		this.sys=sys;
		this.reg=reg;
		this.cmd=cmd;
	}

	public String getName() {
		return name;
	}

	public String getSysID() {
		return sys;
	}

	public String getRegID() {
		return reg;
	}
	public String getCmd(){
		return cmd;
	}
	@Override
	public String toString(){
		return name;
	}
}
