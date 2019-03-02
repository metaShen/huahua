package cn.edu.xmut.core.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

/**
 * 统一定义id的entity基类.  
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 * 子类可重载getId()函数重定义id的列名映射和生成策略.
 * 时间
 * @author lilin
 */
//JPA 基类的标识，可以对应相应的数据库表
@MappedSuperclass
public abstract class IdEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String UUID = "uuid";

	@Size(max=32)
	protected String id;
	private Object etc;
	
	public IdEntity() {
	}

	public IdEntity(String id) {
		this.id = id;
	}

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Transient
	public Object getEtc() {
		return etc;
	}

	@Transient
	public void setEtc(Object etc) {
		this.etc = etc;
	}

	@SuppressWarnings("unchecked")
	public void put(String key, Object value) {
		if(etc == null) {
			etc = new HashMap<String, Object>();
		}
		((Map<String, Object>) etc).put(key, value);
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdEntity other = (IdEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}