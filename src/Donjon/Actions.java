package Donjon;

import java.io.Serializable;

public class Actions implements  Serializable{

	private static final long serialVersionUID = 1L;
	private String cmd ;

	public Actions(String cmd) {
		this.cmd=cmd;
		
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	

}
