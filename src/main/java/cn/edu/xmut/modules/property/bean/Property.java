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
		ADMIN, 
		USERID, 
		TIME, 
		SUM, 
		WATER, 
		INTEL, 
		DAY, 
	}
	// 自动生成区域结束
	
	private static final long serialVersionUID = 1L;

	@Size(max=32)
	String admin; 		//物业管理员
	@Size(max=32)
	String userid; 		//用户id--外键
	Date time; 		//提醒缴费时间
	float sum; 	   //总费用
	float water; 		//水费
	float intel; 		//网费
	Date day; 		//需缴费日期
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
	public float getWater() {
		return water;
	}
	public void setWater(float water) {
		this.water = water;
	}
	public float getIntel() {
		return intel;
	}
	public void setIntel(float intel) {
		this.intel = intel;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	
	
}