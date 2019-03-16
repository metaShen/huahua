package cn.edu.xmut.modules.pay.bean;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import cn.edu.xmut.core.entity.IdEntity;
@Entity
@Table(name="tb_pay")
//支出表
public class Pay extends IdEntity{

// 自动生成区域开始
	public static enum FieldOfPay {
		ID, 
		NUMBER, 
		PURPOSE, 
		TIME, 
		USERID, 
		PRICE, 
	}
	// 自动生成区域结束
	
	private static final long serialVersionUID = 1L;
	
	@Size(max=32)
	String number; 		//序号
	
	@Size(max=32)
	String purpose; 	//用途
	
	@Size(max=32)
	String time;			//支出时间
	
	@Size(max=32)
	String userid; 		//用户id--外键
	
	float price; 		//支出金额
	

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}


	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}



	
}