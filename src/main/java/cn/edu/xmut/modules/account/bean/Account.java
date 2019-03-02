package cn.edu.xmut.modules.account.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import cn.edu.xmut.core.entity.IdEntity;
@Entity
@Table(name="tb_account")
//卡务账户表
public class Account extends IdEntity{

// 自动生成区域开始
	public static enum FieldOfAccount {
		ID, 
		USERID, 
		BANK, 
		CARDS, 
		CARDID, 
		TYPE, 
	}
	// 自动生成区域结束
	
	private static final long serialVersionUID = 1L;		
	@Size(max=32)
	String userid; 		//持卡人
	
	@Size(max=32)
	String bank; 		//开户行

	@Size(max=32)
	String cards;  		//
	
	long cardid; 		//卡号
	int type; 		//卡类型--0贷款--1普通
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getCards() {
		return cards;
	}
	public void setCards(String cards) {
		this.cards = cards;
	}
	public long getCardid() {
		return cardid;
	}
	public void setCardid(long cardid) {
		this.cardid = cardid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}