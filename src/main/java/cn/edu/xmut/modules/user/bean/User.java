package cn.edu.xmut.modules.user.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import cn.edu.xmut.core.entity.IdEntity;
@Entity
@Table(name="tb_user")
public class User extends IdEntity{

	// 自动生成区域开始
	public static enum FieldOfUser {
		ID, 
		NAME, 
		USERNAME, 
		SEX, 
		PASSWORD, 
		FAM, 
		TYPE, 
		AGE, 
	}
	// 自动生成区域结束
	
	private static final long serialVersionUID = 1L;
	@Size(max=32)
	String name; 
	@Size(max=32)
	String username; 
	@Size(max=32)
	String sex; 
	@Size(max=32)
	String password; 
	@Size(max=32)
	String fam; 	//家庭身份
	int type;  //0管理员，1普通用户
	int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getFam() {
		return fam;
	}
	public void setFam(String fam) {
		this.fam = fam;
	}	

	

}