package com.xiaojiang.utilProject;

import java.sql.Timestamp;

public class GBCModel {
	private String GBC;
	private Timestamp update_ts;
	private String parent;
	public String getGBC() {
		return GBC;
	}
	public void setGBC(String gBC) {
		GBC = gBC;
	}
	public Timestamp getUpdate_ts() {
		return update_ts;
	}
	public void setUpdate_ts(Timestamp update_ts) {
		this.update_ts = update_ts;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	@Override
	public String toString() {
		return "GBCModel [GBC=" + GBC + ", update_ts=" + update_ts + ", parent=" + parent + "]";
	}
	
	
}
