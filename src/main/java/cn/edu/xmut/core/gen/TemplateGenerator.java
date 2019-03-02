/**
 * 
 */
package cn.edu.xmut.core.gen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author yangzj
 * @version 2014-8-14 下午5:13:14
 */
public class TemplateGenerator {

	private Logger logger = Logger.getLogger(TemplateGenerator.class);

	private String entityPath; // 实体类路径
	private String daoPath; // dao层路径
	private String servicePath; // service层路径
	private String serviceImplPath; // serviceImple层路径

	private String daoTemplatePath; // 生成dao 模版路径
	private String serviceTemplatePath; // 生成service 模版路径
	private String serviceImplTemplatePath; // 生成serviceImpl 模板路径
	private String entityTemplatePath; // 生成entity 模板路径
	
	private String tempFtlPath;

	/**
	 * 初始化参数 从配置文件中得到
	 */
	public void init() {
		
		String propertyPath = "src/main/java/cn/edu/xmut/core/gen/config.properties"; // 使用默认路径
		init(propertyPath);
	}
	
	public void init(String propertyPath) {
		Map<String, String> properties = Property.getProperties(propertyPath);
		entityPath = properties.get("entityPath");
		daoPath = properties.get("daoPath");
		servicePath = properties.get("servicePath");
		serviceImplPath = properties.get("serviceImplPath");
		daoTemplatePath = properties.get("daoTemplatePath");
		serviceTemplatePath = properties.get("serviceTemplatePath");
		serviceImplTemplatePath = properties.get("serviceImplTemplatePath");
		entityTemplatePath = properties.get("entityTemplatePath");
		tempFtlPath = properties.get("tempFtlPath");
	}

	/**
	 * @param path
	 * @return 根据包的路径得到package XXX中的XXX
	 */
	private String getImportPath(String path) {
		StringBuffer result = new StringBuffer();
		String[] strings = path.split("/");
		for (int i = 3, size = strings.length; i < size; i++) {
			result.append(".").append(strings[i]);
		}
		return result.substring(1);
	}

	/**
	 * @param entity
	 * @return 得到实体名称
	 */
	private String getEntityName(File entity) {
		return entity.getName().substring(0, entity.getName().indexOf("."));
	}

	/**
	 * @param s
	 *            String
	 * @return 首字母小写
	 */
	public static String firstCharLowerCase(String s) {
		if (s == null || "".equals(s)) {
			return ("");
		}
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}

	/**
	 * 开始创建文件
	 * @param daoFlag
	 * @param daoImplFlag
	 * @param serviceFlag
	 * @param serviceImplFlag
	 * @param isDelete
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public void buildTemplate(boolean entityFlag, boolean daoFlag, boolean daoImplFlag, boolean serviceFlag,boolean serviceImplFlag, boolean isDelete) throws IOException, ClassNotFoundException {
		
		File entityDir = new File(entityPath);
		entityPath = getImportPath(entityPath);
		//得到实体包下所有的实体java文件
		File[] entities = entityDir.listFiles();
		String daoImport = getImportPath(daoPath);
		String serviceImport = getImportPath(servicePath);
		String serviceImplImport = getImportPath(serviceImplPath);
		// baseDao, baseImplDao 名字和路径
		DefaultTemplate defaultTemplate = new DefaultTemplate();
		String baseDaoName = defaultTemplate.getBaseDaoName();
		String baseDaoPath = daoImport + "." + baseDaoName;
		
		//开始create了
		for (File entity : entities) {
			// 过滤掉配置文件
			if (!entity.getName().endsWith("java")) {
				continue;
			}
			
			
			String entityName = getEntityName(entity);
			String entityViable= firstCharLowerCase(entityName);
			String tableName = getTableName(entityName);
			String daoName = entityName + "Dao";
			String daoEntity = firstCharLowerCase(daoName);
			String serviceName = entityName + "Service";
			String serviceImplName = entityName + "ServiceImpl";
			String modelPath = entityPath + "." + entityName;
			String dao = daoImport + "." + daoName;
			String service =serviceImport + "." + serviceName;

			System.out.println("==================="+entityName+"相关 dao service serviceImpl 模板生成开始========================");
			if(entityFlag) {
				Template template = new Configuration().getTemplate(entityTemplatePath, "UTF-8");
				defaultTemplate = new DefaultTemplate();
				String clazzPath = entity.getPath().replace("\\", ".").replace("src.main.java.", "").replace(".java", "");
				List<String> fieldlist = getFields(clazzPath);
				defaultTemplate.setFieldlist(fieldlist);
				defaultTemplate.setModelPath(modelPath);
				defaultTemplate.setEntityName(entityName);
				defaultTemplate.setEntityViable(entityViable);
				String c = entity.getPath().replace("\\", "/").replace("/"+entity.getName(), "");
				updateEntity(defaultTemplate, c,
						entityName + ".java", template, isDelete, entityTemplatePath);
			}
			
			//~ 生成dao ======================================================================================
			if (daoFlag) {
				Template template = new Configuration().getTemplate(daoTemplatePath, "UTF-8");
				defaultTemplate = new DefaultTemplate();
				defaultTemplate.setBaseDaoPath(baseDaoPath);
				defaultTemplate.setDaoName(daoName);
				defaultTemplate.setPackagePath(daoImport);
				defaultTemplate.setModelPath(modelPath);
				defaultTemplate.setEntityName(entityName);
				defaultTemplate.setEntityViable(entityViable);
				defaultTemplate.setTableName(tableName);
				createFile(defaultTemplate, daoPath,
						entityName + "Dao.java", template, isDelete, daoTemplatePath);
			}
			
			//~ 生成service ======================================================================================
			if (serviceFlag) {
				Template template = new Configuration().getTemplate(serviceTemplatePath, "UTF-8");
				defaultTemplate = new DefaultTemplate();
				defaultTemplate.setPackagePath(serviceImport);
				defaultTemplate.setDaoPath(dao);
				defaultTemplate.setModelPath(modelPath);
				defaultTemplate.setServiceName(serviceName);
				defaultTemplate.setDaoName(daoName);
				defaultTemplate.setDaoEntity(daoEntity);
				defaultTemplate.setEntityName(entityName);
				defaultTemplate.setEntityViable(entityViable);
				defaultTemplate.setTableName(tableName);
				createFile(defaultTemplate, servicePath, entityName
						+ "Service.java", template, isDelete, serviceTemplatePath);
			}
			//~ 生成serviceImpl ======================================================================================
			if(serviceImplFlag){
				Template template = new Configuration().getTemplate(serviceImplTemplatePath, "UTF-8");
				defaultTemplate = new DefaultTemplate();
				defaultTemplate.setPackagePath(serviceImplImport);
				defaultTemplate.setDaoPath(dao);
				defaultTemplate.setServicePath(service);
				defaultTemplate.setModelPath(modelPath);
				defaultTemplate.setServiceImplName(serviceImplName);
				defaultTemplate.setDaoName(daoName);
				defaultTemplate.setServiceName(serviceName);
				defaultTemplate.setDaoEntity(daoEntity);
				defaultTemplate.setEntityName(entityName);
				defaultTemplate.setEntityViable(entityViable);
				defaultTemplate.setTableName(tableName);
				createFile(defaultTemplate, serviceImplPath, entityName
						+ "ServiceImpl.java", template, isDelete, serviceImplTemplatePath);
			}
			System.out.println("==================="+entityName+"相关 dao service serviceImpl 模板生成结束========================");
			System.out.println();
		}
	}

	/**
	 * @param entityName
	 * @return
	 */
	private String getTableName(String entityName) {
		String str = "";
		for(int i=0;i<entityName.length();i++) {
			char c = entityName.charAt(i);
			if(!Character.isLowerCase(c)) {
				str = str + '_';
			}
			str = str + c;
		}
		return ("TB"+str).toUpperCase();
	}

	/**
	 * @param defaultTemplate
	 * @param entityPath2
	 * @param string
	 * @param template
	 * @param isDelete
	 * @param entityTemplatePath2
	 */
	public void updateEntity(Object object, String savePath, String fileName,
			Template template, boolean isDelete, String templatePath) throws IOException {
		
		String realFileName = savePath + "/" + fileName;
		String realSavePath = savePath;
		
		String tempFtlName = tempFtlPath + "/" + "temp.ftl";
		
		File newsDir = new File(realSavePath);
		if (!newsDir.exists()) {
			logger.info("create dir :" + realSavePath);
			newsDir.mkdirs();
		}
		File realFile = new File(realFileName);
		
		// 判断是否删除文件

		if (realFile.exists()) {
			File tempFtl = new File(tempFtlName);
			File templateFile = new File(templatePath);
			System.out.println(fileName + "已经存在，更新文件!");
			
			String sources = FileUtils.readFileToString(realFile, "UTF-8");
			String tempContent = FileUtils.readFileToString(templateFile, "UTF-8");
			String startStr = "// 自动生成区域开始";
			String endStr = "// 自动生成区域结束";
			
			if(tempContent.indexOf(startStr) != -1 && tempContent.indexOf(endStr) != -1) {
				String codeWithoutOutBound = tempContent.substring(tempContent.indexOf(startStr));
				if(sources.indexOf("{") != -1 && sources.indexOf("private") != -1) {
					String t1 = sources.substring(0, sources.indexOf("{")+6);
					String t2 = sources.substring(sources.indexOf("private")-3);
					tempContent = t1 + codeWithoutOutBound + t2;
				}
				FileUtils.writeStringToFile(tempFtl, tempContent, "UTF-8");
			}
			
			Template tempTemplate = new Configuration().getTemplate(tempFtlName, "UTF-8");
			Map<String, Object> rootMap = new HashMap<String, Object>();
			rootMap.put("defaultTemplate", object);
			try {
				Writer out = new OutputStreamWriter(new FileOutputStream(realFileName), "UTF-8");
				tempTemplate.process(rootMap, out);
				System.out.println(fileName+"生成成功");
				
				// 把临时模板删掉
				tempFtl.deleteOnExit();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			Map<String, Object> rootMap = new HashMap<String, Object>();
			rootMap.put("defaultTemplate", object);
			try {
				Writer out = new OutputStreamWriter(new FileOutputStream(realFileName), "UTF-8");
				template.process(rootMap, out);
				System.out.println(fileName+"生成成功");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @param entityPath2
	 * @return
	 * @throws ClassNotFoundException 
	 */
	private List<String> getFields(String clazzPath) throws ClassNotFoundException {
		List<String> fields = new ArrayList<String>();
		Class<?> clazz = Class.forName(clazzPath);
		Field[] f = clazz.getDeclaredFields();
		for(Field s:f) {
			if(s.getName().equals("serialVersionUID")) {
				continue;
			}
			String str = "";
			for(int i=0;i<s.getName().length();i++) {
				char c = s.getName().charAt(i);
				if(!Character.isLowerCase(c) && !Character.isDigit(c)) {
					str = str + '_';
				}
				str = str + c;
			}
			fields.add(str.toUpperCase());
		}
		return fields;
	}

	/**
	 * 生成文件
	 * @param object
	 * @param savePath
	 * @param fileName
	 * @param template
	 * @param isDelete
	 * @throws IOException 
	 */
	public void createFile(Object object, String savePath, String fileName,
			Template template, boolean isDelete, String templatePath) throws IOException {
		String realFileName = savePath + "/" + fileName;
		String realSavePath = savePath;
		
		String tempFtlName = tempFtlPath + "/" + "temp.ftl";
		
		File newsDir = new File(realSavePath);
		if (!newsDir.exists()) {
			logger.info("create dir :" + realSavePath);
			newsDir.mkdirs();
		}
		File realFile = new File(realFileName);
		
		// 判断是否删除文件

		if (realFile.exists()) {
			File tempFtl = new File(tempFtlName);
			File templateFile = new File(templatePath);
			System.out.println(fileName + "已经存在，更新文件!");
			
			String sources = FileUtils.readFileToString(realFile, "UTF-8");
			String tempContent = FileUtils.readFileToString(templateFile, "UTF-8");
			String startStr = "// 用户自定义开始";
			String endStr = "// 用户自定义结束";
			
			if(sources.indexOf(startStr) != -1 && sources.indexOf(endStr) != -1) {
				String codeWithoutOutBound = sources.substring(sources.indexOf(startStr) + startStr.length(), sources.indexOf(endStr));
				if(tempContent.indexOf(startStr) != -1 && tempContent.indexOf(endStr) != -1) {
					tempContent = tempContent.substring(0, tempContent.indexOf(startStr) + startStr.length()) + codeWithoutOutBound + tempContent.substring(tempContent.indexOf(endStr));
				}
				FileUtils.writeStringToFile(tempFtl, tempContent, "UTF-8");
			}
			
			Template tempTemplate = new Configuration().getTemplate(tempFtlName, "UTF-8");
			Map<String, Object> rootMap = new HashMap<String, Object>();
			rootMap.put("defaultTemplate", object);
			try {
				Writer out = new OutputStreamWriter(new FileOutputStream(realFileName), "UTF-8");
				tempTemplate.process(rootMap, out);
				System.out.println(fileName+"生成成功");
				
				// 把临时模板删掉
				tempFtl.deleteOnExit();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			Map<String, Object> rootMap = new HashMap<String, Object>();
			rootMap.put("defaultTemplate", object);
			try {
				Writer out = new OutputStreamWriter(new FileOutputStream(realFileName), "UTF-8");
				template.process(rootMap, out);
				System.out.println(fileName+"生成成功");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) {
		try {
			TemplateGenerator mg = new TemplateGenerator();
			mg.init();
			mg.buildTemplate(true, true, true, true, true, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
