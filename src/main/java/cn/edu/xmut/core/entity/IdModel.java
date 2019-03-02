/**
 * 
 */
package cn.edu.xmut.core.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author yangzj
 * @version 2014-9-19 上午9:27:36
 */
@MappedSuperclass
public class IdModel implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String UUID = "uuid";

	protected String id;
	
	public IdModel() {
	}

	public IdModel(String id) {
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



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdModel other = (IdModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
