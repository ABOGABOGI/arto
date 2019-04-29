package com.example.cyber_net.apps.model.send;

import com.google.gson.annotations.SerializedName;

public class PostPayment{

	public PostPayment(String transactionId, String merchantCode, String customerNo, String amount,
					   String partnerId, String reffId, String productCode) {
		this.transactionId = transactionId;
		this.merchantCode = merchantCode;
		this.customerNo = customerNo;
		this.amount = amount;
		this.partnerId = partnerId;
		this.reffId = reffId;
		this.productCode = productCode;
	}

	@SerializedName("transaction_id")
	private String transactionId;

	@SerializedName("merchant_code")
	private String merchantCode;

	@SerializedName("customer_no")
	private String customerNo;

	@SerializedName("amount")
	private String amount;

	@SerializedName("partner_id")
	private String partnerId;

	@SerializedName("reff_id")
	private String reffId;

	@SerializedName("product_code")
	private String productCode;

	public void setTransactionId(String transactionId){
		this.transactionId = transactionId;
	}

	public String getTransactionId(){
		return transactionId;
	}

	public void setMerchantCode(String merchantCode){
		this.merchantCode = merchantCode;
	}

	public String getMerchantCode(){
		return merchantCode;
	}

	public void setCustomerNo(String customerNo){
		this.customerNo = customerNo;
	}

	public String getCustomerNo(){
		return customerNo;
	}

	public void setAmount(String amount){
		this.amount = amount;
	}

	public String getAmount(){
		return amount;
	}

	public void setPartnerId(String partnerId){
		this.partnerId = partnerId;
	}

	public String getPartnerId(){
		return partnerId;
	}

	public void setReffId(String reffId){
		this.reffId = reffId;
	}

	public String getReffId(){
		return reffId;
	}

	public void setProductCode(String productCode){
		this.productCode = productCode;
	}

	public String getProductCode(){
		return productCode;
	}

	@Override
 	public String toString(){
		return 
			"PostPayment{" + 
			"transaction_id = '" + transactionId + '\'' + 
			",merchant_code = '" + merchantCode + '\'' + 
			",customer_no = '" + customerNo + '\'' + 
			",amount = '" + amount + '\'' + 
			",partner_id = '" + partnerId + '\'' + 
			",reff_id = '" + reffId + '\'' + 
			",product_code = '" + productCode + '\'' + 
			"}";
		}
}