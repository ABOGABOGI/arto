package com.example.cyber_net.apps.model.send;

import com.google.gson.annotations.SerializedName;

public class PostInquiry{

	public PostInquiry(String transactionId, String merchantCode, String customerNo, String partnerId,
					   String requestTime, String productCode) {
		this.transactionId = transactionId;
		this.merchantCode = merchantCode;
		this.customerNo = customerNo;
		this.partnerId = partnerId;
		this.requestTime = requestTime;
		this.productCode = productCode;
	}

	@SerializedName("transaction_id")
	private String transactionId;

	@SerializedName("merchant_code")
	private String merchantCode;

	@SerializedName("customer_no")
	private String customerNo;

	@SerializedName("partner_id")
	private String partnerId;

	@SerializedName("request_time")
	private String requestTime;

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

	public void setPartnerId(String partnerId){
		this.partnerId = partnerId;
	}

	public String getPartnerId(){
		return partnerId;
	}

	public void setRequestTime(String requestTime){
		this.requestTime = requestTime;
	}

	public String getRequestTime(){
		return requestTime;
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
			"PostInquiry{" + 
			"transaction_id = '" + transactionId + '\'' + 
			",merchant_code = '" + merchantCode + '\'' + 
			",customer_no = '" + customerNo + '\'' + 
			",partner_id = '" + partnerId + '\'' + 
			",request_time = '" + requestTime + '\'' + 
			",product_code = '" + productCode + '\'' + 
			"}";
		}
}