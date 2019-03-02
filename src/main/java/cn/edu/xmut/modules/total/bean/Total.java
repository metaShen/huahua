package cn.edu.xmut.modules.total.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import cn.edu.xmut.core.entity.IdEntity;
@Entity
@Table(name="tb_total")
//统计表
public class Total extends IdEntity{


	public static enum FieldOfTotal {
		ID, 
		USERID, 
		PAYID, 
		REVENUEID, 
		PRCIE, 
		TIME, 
	}
	// 自动生成区域结束
	
	private static final long serialVersionUID = 1L;
	@Size(max=32)
	String userid; 		//用户id--外键
	@Size(max=32)
	String payid; 		//支出表id--外键
	@Size(max=32)
	String revenueid; 	//收入表id--外键
	float prcie;		//收入支出差
	Date time;			//统计时间
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPayid() {
		return payid;
	}
	public void setPayid(String payid) {
		this.payid = payid;
	}
	
	public String getRevenueid() {
		return revenueid;
	}
	public void setRevenueid(String revenueid) {
		this.revenueid = revenueid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public float getPrcie() {
		return prcie;
	}
	public void setPrcie(float prcie) {
		this.prcie = prcie;
	}

	
}