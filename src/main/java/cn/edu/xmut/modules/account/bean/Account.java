package cn.edu.xmut.modules.account.bean;

import java.sql.Date;

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
		BANK, 
		TIME, 
		BALANCE, 
		BANKNUMBER, 
		TYPE, 
		REPAYMENT, 
	}
	// 自动生成区域结束
	
	private static final long serialVersionUID = 1L;		

	@Size(max=32)
	String bank; 		//开户银行
	@Size(max=32)
	String time;	//还款日期
	
	float balance;		//账户余额
	long banknumber; 		//银行账户
	int type; 		//卡类型--0贷款--1普通--2公交卡--3会员卡
	
	float repayment;	//还款金额

	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public long getBanknumber() {
		return banknumber;
	}
	public void setBanknumber(long banknumber) {
		this.banknumber = banknumber;
	}
	public float getRepayment() {
		return repayment;
	}
	public void setRepayment(float repayment) {
		this.repayment = repayment;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	
	
}