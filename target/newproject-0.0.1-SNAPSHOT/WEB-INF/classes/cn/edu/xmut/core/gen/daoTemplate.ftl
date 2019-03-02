package  ${defaultTemplate.packagePath};

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import cn.edu.xmut.core.persistence.BaseDao;
import cn.edu.xmut.core.persistence.BaseDaoImpl;
import ${defaultTemplate.modelPath};

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
public interface ${defaultTemplate.daoName} extends ${defaultTemplate.daoName}Custom, CrudRepository<${defaultTemplate.entityName}, String> {
	
	// 用户自定义开始，请不要修改以上内容
	
	
	// 用户自定义结束，请不要修改以下内容
}

interface ${defaultTemplate.daoName}Custom extends BaseDao<${defaultTemplate.entityName}> {

}

@Repository
class ${defaultTemplate.daoName}Impl extends BaseDaoImpl<${defaultTemplate.entityName}> implements ${defaultTemplate.daoName}Custom {

}