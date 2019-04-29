package com.example.cyber_net.apps.model.payment;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import static com.example.cyber_net.apps.helper.ConvertDate.tglDanJam;

public class Payment implements Serializable {

	public static HashMap setSendPayment(String partId, String transId, int reffId, String prodCode, String merchantCode,
										 String custId, int amount){
		HashMap<String, Object> datas = new HashMap<>();
		datas.put("partner_id", partId);
		datas.put("transaction_id", transId);
		datas.put("reff_id", reffId);
		datas.put("product_code", prodCode);
		datas.put("merchant_code", merchantCode);
		datas.put("customer_no", custId);
		datas.put("amount", amount);

		return datas;
	}

	public Payment() {
	}

	@SerializedName("reff_no")
	private String reffNo;

	@SerializedName("msg")
	private String msg;

	@SerializedName("restime")
	private String restime;

	@SerializedName("admin")
	private int admin;

	@SerializedName("reffid")
	private String reffid;

	@SerializedName("struk")
	private List<String> struk;

	@SerializedName("tgl_lunas")
	private String tglLunas;

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

	@SerializedName("total_tagihan")
	private int totalTagihan;

	public void setReffNo(String reffNo){
		this.reffNo = reffNo;
	}

	public String getReffNo(){
		return reffNo;
	}

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setRestime(String restime){
		this.restime = restime;
	}

	public String getRestime(){
		return restime;
	}

	public void setAdmin(int admin){
		this.admin = admin;
	}

	public int getAdmin(){
		return admin;
	}

	public void setReffid(String reffid){
		this.reffid = reffid;
	}

	public String getReffid(){
		return reffid;
	}

	public void setStruk(List<String> struk){
		this.struk = struk;
	}

	public List<String> getStruk(){
		return struk;
	}

	public void setTglLunas(String tglLunas){
		this.tglLunas = tglLunas;
	}

	public String getTglLunas(){
		return tglLunas;
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

	public void setTotalTagihan(int totalTagihan){
		this.totalTagihan = totalTagihan;
	}

	public int getTotalTagihan(){
		return totalTagihan;
	}

	@Override
 	public String toString(){
		return 
			"Payment{" + 
			"reff_no = '" + reffNo + '\'' + 
			",msg = '" + msg + '\'' + 
			",restime = '" + restime + '\'' + 
			",admin = '" + admin + '\'' + 
			",reffid = '" + reffid + '\'' + 
			",struk = '" + struk + '\'' + 
			",tgl_lunas = '" + tglLunas + '\'' + 
			",rc = '" + rc + '\'' + 
			",tagihan = '" + tagihan + '\'' + 
			",biller_ref = '" + billerRef + '\'' + 
			",produk = '" + produk + '\'' + 
			",nopel = '" + nopel + '\'' + 
			",nama = '" + nama + '\'' + 
			",total_tagihan = '" + totalTagihan + '\'' + 
			"}";
		}
}