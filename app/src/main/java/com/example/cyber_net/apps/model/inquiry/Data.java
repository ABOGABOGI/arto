package com.example.cyber_net.apps.model.inquiry;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("pemakaian")
	private String pemakaian;

	@SerializedName("periode")
	private String periode;

	public void setPemakaian(String pemakaian){
		this.pemakaian = pemakaian;
	}

	public String getPemakaian(){
		return pemakaian;
	}

	public void setPeriode(String periode){
		this.periode = periode;
	}

	public String getPeriode(){
		return periode;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"pemakaian = '" + pemakaian + '\'' + 
			",periode = '" + periode + '\'' + 
			"}";
		}
}