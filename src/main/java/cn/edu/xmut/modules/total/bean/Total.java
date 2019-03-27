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

// 自动生成区域开始
	public static enum FieldOfTotal {
		ID, 
		PAYID, 
		REVENUEID, 
		TIME, 
		PRCIE, 
	}
	// 自动生成区域结束
	
	private static final long serialVersionUID = 1L;
	@Size(max=32)
	String payid; 		//支出表id--外键
	@Size(max=32)
	String revenueid; 	//收入表id--外键
	@Size(max=32)
	String time;			//统计时间
	
	float prcie;		//收入支出差


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

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public float getPrcie() {
		return prcie;
	}
	public void setPrcie(float prcie) {
		this.prcie = prcie;
	}

	
}