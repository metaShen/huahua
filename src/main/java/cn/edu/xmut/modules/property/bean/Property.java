package cn.edu.xmut.modules.property.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import cn.edu.xmut.core.entity.IdEntity;
@Entity
@Table(name="tb_property")
//物业表(缴费信息表)
public class Property extends IdEntity{

// 自动生成区域开始
	public static enum FieldOfProperty {
		ID, 
		TYPE, 
		USERID, 
		DAY, 
		TIME, 
		SUM, 
		PRICE, 
	}
	// 自动生成区域结束
	
	private static final long serialVersionUID = 1L;
	@Size(max=32)
	String type; 		//缴费类型
	@Size(max=32)
	String userid; 		//缴费用户
	@Size(max=32)
	String day; 		//截止缴费日期
	@Size(max=32)
	String time; 		//提醒缴费时间
	
	float sum; 	   //总费用
	float price; 	//需缴费金额

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
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}

	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
}