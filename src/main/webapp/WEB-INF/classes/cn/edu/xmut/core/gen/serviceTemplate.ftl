package  ${defaultTemplate.packagePath};

import java.util.List;
import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import ${defaultTemplate.modelPath};

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
public interface ${defaultTemplate.serviceName}{

	// 自动生成区域
	
	/**
	 * 计算${defaultTemplate.entityName}所有条目
	 * 
	 * @param ${defaultTemplate.entityViable}
	 *
	 * @return 返回${defaultTemplate.entityName}信息所有条目
	 */
	public int countAll();
	
	/**
	 * 添加${defaultTemplate.entityName}一条信息
	 * 
	 * @param ${defaultTemplate.entityViable}
	 *
	 * @return 返回${defaultTemplate.entityName}信息
	 */
	public ${defaultTemplate.entityName} save(${defaultTemplate.entityName} ${defaultTemplate.entityViable});
 
 	/**
	 * 删除${defaultTemplate.entityName}多条记录
	 * 
	 * @param ids
	 *
	 * @return void
	 */
	public void deleteById(String[] ids);
	
	/**
	 * 删除${defaultTemplate.entityName}一条记录
	 * 
	 * @param ids
	 *
	 * @return void
	 */
	public void deleteById(String id);
	
	/**
	 * 根据${defaultTemplate.entityName}编号查找${defaultTemplate.entityName}
	 * 
	 * @param id
	 *
	 * @return ${defaultTemplate.entityName}，若不存在则返回null
	 */
	${defaultTemplate.entityName} findById(String id);
	
	/**
	 * 翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<${defaultTemplate.entityName}>
	 */
	public Page<${defaultTemplate.entityName}> findPageOrderBy(Pageable pageable, String orderBy);
	
	/**
	 * 根据一个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<${defaultTemplate.entityName}>
	 */
	public Page<${defaultTemplate.entityName}> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy);
	
	/**
	 * 根据两个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<${defaultTemplate.entityName}>
	 */
	public Page<${defaultTemplate.entityName}> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy);
	
	/**
	 * 根据三个参数翻页方式找出所有记录
	 * 
	 * @param pageable
	 *
	 * @return Page<${defaultTemplate.entityName}>
	 */
	public Page<${defaultTemplate.entityName}> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy);
	
	/**
	 * 根据单个字段名称查找一条记录
	 * 
	 * @param fieldName
	 * @param fieldValue
	 *
	 * @return ${defaultTemplate.entityName}
	 */
	public ${defaultTemplate.entityName} getByOneField(String fieldName, Object fieldValue);
	
	/**
	 * 根据两个字段名称查找一条记录
	 * 
	 * @param fieldName1
	 * @param fieldValue1
	 * @param fieldName2
	 * @param fieldValue2
	 *
	 * @return ${defaultTemplate.entityName}
	 */
	public ${defaultTemplate.entityName} getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2);
		
	/**
	 * 查找所有记录
	 * 
	 * @param orderBy
	 *
	 * @return List<${defaultTemplate.entityName}>
	 */
	public List<${defaultTemplate.entityName}> findAllOrderBy(String orderBy);
	
	/**
	 * 根据单个字段名称查找记录
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @param orderBy
	 *
	 * @return List<${defaultTemplate.entityName}>
	 */
	public List<${defaultTemplate.entityName}> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy);
	
	/**
	 * 根据两个字段名称查找记录
	 * 
	 * @param fieldName1
	 * @param fieldValue1
	 * @param fieldName2
	 * @param fieldValue2
	 * @param orderBy
	 *
	 * @return List<${defaultTemplate.entityName}>
	 */
	public List<${defaultTemplate.entityName}> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy);
	
	/**
	 * 根据单个字段名称计算记录数目
	 * 
	 * @param fieldName
	 * @param fieldValue
	 *
	 * @return int
	 */
	public int countByOneField(String fieldName, Object fieldValue);
	
	/**
	 * 根据两个字段名称计算记录数目
	 * 
	 * @param fieldName1
	 * @param fieldValue1
	 * @param fieldName2
	 * @param fieldValue2
	 *
	 * @return int
	 */
	public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2);
	
	// 用户自定义开始，请不要修改以上内容
	
	
	// 用户自定义结束，请不要修改以下内容
}