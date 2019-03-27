package cn.edu.xmut.modules.card.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import cn.edu.xmut.core.entity.IdEntity;
@Entity
@Table(name="tb_card")
//各种卡存放表
public class Card extends IdEntity{

// 自动生成区域开始
	public static enum FieldOfCard {
		ID, 
		USERID, 
		ACCOUNTID, 
		PRICE, 
	}
	// 自动生成区域结束
	
	private static final long serialVersionUID = 1L;
	@Size(max=32)
	String userid; 		//持卡人
	
	long accountid; 		//卡id
	
	float price ;			//余额



	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public long getAccountid() {
		return accountid;
	}

	public void setAccountid(long accountid) {
		this.accountid = accountid;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}


	
}