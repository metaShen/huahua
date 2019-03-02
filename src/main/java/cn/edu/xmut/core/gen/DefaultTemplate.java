/**
 * 
 */
package cn.edu.xmut.core.gen;

import java.util.List;

/**
 * @author yangzj
 * @version 2014-8-14 下午5:17:30
 */
public class DefaultTemplate {

	private String packagePath; //当前包路径
	private String baseDaoPath; //BaseDao接口路径
	private String baseDaoImplPath; //BaseDao实现类路径
	private String modelPath;   //当前实体类路径
	private String daoPath;		//当前dao接口路径	
	private String servicePath; //当前service接口路径
	
	private String entityName;	//当前实体类名称
	private String tableName;   //表名称
	private String daoName;		//当前dao接口名称
	
	private String serviceName; //service
	private String serviceImplName; //serviceImple名称
	private String daoEntity;   //dao实体
	private String entityViable; //实体变量
	
	private String baseDaoName = "BaseDAO";
	private String baseDaoImplName = "BaseDaoImpl";
	
	private List<String> fieldlist;
	
	//~ Get and Set Methods =================================================================================
	public String getPackagePath() {
		return packagePath;
	}
	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}
	public String getBaseDaoPath() {
		return baseDaoPath;
	}
	public void setBaseDaoPath(String baseDaoPath) {
		this.baseDaoPath = baseDaoPath;
	}
	public String getModelPath() {
		return modelPath;
	}
	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}
	public String getDaoName() {
		return daoName;
	}
	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getBaseDaoImplPath() {
		return baseDaoImplPath;
	}
	public void setBaseDaoImplPath(String baseDaoImplPath) {
		this.baseDaoImplPath = baseDaoImplPath;
	}
	public String getDaoPath() {
		return daoPath;
	}
	public void setDaoPath(String daoPath) {
		this.daoPath = daoPath;
	}
	
	public String getBaseDaoName() {
		return baseDaoName;
	}
	public void setBaseDaoName(String baseDaoName) {
		this.baseDaoName = baseDaoName;
	}
	public String getBaseDaoImplName() {
		return baseDaoImplName;
	}
	public void setBaseDaoImplName(String baseDaoImplName) {
		this.baseDaoImplName = baseDaoImplName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getDaoEntity() {
		return daoEntity;
	}
	public void setDaoEntity(String daoEntity) {
		this.daoEntity = daoEntity;
	}
	public String getEntityViable() {
		return entityViable;
	}
	public void setEntityViable(String entityViable) {
		this.entityViable = entityViable;
	}
	public String getServiceImplName() {
		return serviceImplName;
	}
	public void setServiceImplName(String serviceImplName) {
		this.serviceImplName = serviceImplName;
	}
	public String getServicePath() {
		return servicePath;
	}
	public void setServicePath(String servicePath) {
		this.servicePath = servicePath;
	}
	public List<String> getFieldlist() {
		return fieldlist;
	}
	public void setFieldlist(List<String> fieldlist) {
		this.fieldlist = fieldlist;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
