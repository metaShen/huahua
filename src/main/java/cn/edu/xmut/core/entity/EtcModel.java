/**
 * 
 */
package cn.edu.xmut.core.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * @author yangzj
 * @version 2014年11月12日 上午11:23:02
 */
@MappedSuperclass
public abstract class EtcModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object etc;
	
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
}
