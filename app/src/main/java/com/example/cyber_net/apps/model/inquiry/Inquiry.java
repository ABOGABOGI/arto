package com.example.cyber_net.apps.model.inquiry;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;

import static com.example.cyber_net.apps.helper.ConvertDate.tglDanJam;

public class Inquiry implements Serializable{

	public static HashMap setSendInquiry(String partId, String transId, String prodCode, String merchantCode,
										 String custId){
		HashMap<String, Object> datas = new HashMap<>();
		datas.put("partner_id", partId);
		datas.put("transaction_id", transId);
		datas.put("product_code", prodCode);
		datas.put("merchant_code", merchantCode);
		datas.put("customer_no", custId);
		datas.put("request_time", tglDanJam());

		return datas;
	}

	public Inquiry() {
	}

	@SerializedName("msg")
	private String msg;

	@SerializedName("rc")
	private String rc;

	@SerializedName("tagihan")
	private int tagihan;

	@SerializedName("biller_ref")
	private String billerRef;

	@SerializedName("produk")
	private String produk;

	@SerializedName("nopel")
	private String nopel;

	@SerializedName("nama")
	private String nama;

	@SerializedName("data")
	private Data data;

	@SerializedName("total_tagihan")
	private int totalTagihan;

	@SerializedName("restime")
	private String restime;

	@SerializedName("admin")
	private String admin;

	@SerializedName("reffid")
	private int reffid;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setRc(String rc){
		this.rc = rc;
	}

	public String getRc(){
		return rc;
	}

	public void setTagihan(int tagihan){
		this.tagihan = tagihan;
	}

	public int getTagihan(){
		return tagihan;
	}

	public void setBillerRef(String billerRef){
		this.billerRef = billerRef;
	}

	public String getBillerRef(){
		return billerRef;
	}

	public void setProduk(String produk){
		this.produk = produk;
	}

	public String getProduk(){
		return produk;
	}

	public void setNopel(String nopel){
		this.nopel = nopel;
	}

	public String getNopel(){
		return nopel;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setTotalTagihan(int totalTagihan){
		this.totalTagihan = totalTagihan;
	}

	public int getTotalTagihan(){
		return totalTagihan;
	}

	public void setRestime(String restime){
		this.restime = restime;
	}

	public String getRestime(){
		return restime;
	}

	public void setAdmin(String admin){
		this.admin = admin;
	}

	public String getAdmin(){
		return admin;
	}

	public void setReffid(int reffid){
		this.reffid = reffid;
	}

	public int getReffid(){
		return reffid;
	}

	@Override
 	public String toString(){
		return 
			"Inquiry{" + 
			"msg = '" + msg + '\'' + 
			",rc = '" + rc + '\'' + 
			",tagihan = '" + tagihan + '\'' + 
			",biller_ref = '" + billerRef + '\'' + 
			",produk = '" + produk + '\'' + 
			",nopel = '" + nopel + '\'' + 
			",nama = '" + nama + '\'' + 
			",data = '" + data + '\'' + 
			",total_tagihan = '" + totalTagihan + '\'' + 
			",restime = '" + restime + '\'' + 
			",admin = '" + admin + '\'' + 
			",reffid = '" + reffid + '\'' + 
			"}";
		}
}