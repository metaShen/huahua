package cn.edu.xmut.modules.revenue.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import cn.edu.xmut.core.entity.IdEntity;
@Entity
@Table(name="tb_revenue")
//收入表
public class Revenue extends IdEntity{

// 自动生成区域开始
	public static enum FieldOfRevenue {
		ID, 
		NUMBER, 
		SOURCE, 
		USERID, 
		TIME, 
		PRICE, 
	}
	// 自动生成区域结束
	
	private static final long serialVersionUID = 1L;
	@Size(max=32)
	String number; 		//序号
	@Size(max=32)
	String source; 		//收入来源
	@Size(max=32)
	String userid; 		//用户id--外键
	
	String time; 			 //收入时间
	float price;		//收入金额
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}


	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	} 

		
}