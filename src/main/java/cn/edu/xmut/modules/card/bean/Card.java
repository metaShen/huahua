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
		TYPE, 
		USERID, 
		CARDID, 
		TIME, 
	}
	// 自动生成区域结束
	
	private static final long serialVersionUID = 1L;
	@Size(max=32)
	String type; 		//卡类型
	@Size(max=32)
	String userid; 		//持卡人
	@Size(max=32)
	String cardid; 		//卡id
	
	@Size(max=32)
	String time ;			//办卡时间--用于排序 

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	
}