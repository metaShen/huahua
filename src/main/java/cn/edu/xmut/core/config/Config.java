package cn.edu.xmut.core.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
* @ClassName: Config
* @Description: 整个系统的配置文件处理类
* @author lilin
 */
@Component
public class Config {

	private static Logger logger = LoggerFactory.getLogger(Config.class);
	private static TreeMap<String, String> values = new TreeMap<String, String>();

	// Default directories
	public static final String HOME_DIR = getHomeDir();
	public static final String CLASS_DIR = getClassDir();
	//	public static final String APP_DIR = getAppDir();
	public static final String TMP_DIR = getTempDir();
	public static final String NULL_DEVICE = getNullDevice();
	public static final boolean IN_SERVER = inServer();
	public static final String WELLOA_CONFIG = "application.properties";

	/** 日期格式配比 */
	public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd",
			"yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

	/** ecom.xml文件路径 */
	public static final String SHOPXX_XML_PATH = "/ecom.xml";

	/** application.properties文件路径 */
	public static final String SHOPXX_PROPERTIES_PATH = "/application.properties";

	static {
		load();
	}
	//上传文件目录
	public static final String PROPERTY_UPLOAD_DIR = "upload.dir";
	public static String UPLOAD_DIR = "uploads";

	private static String getClassDir() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		//去掉最后一个"/"
		path = path.substring(0, path.length() - 1);
		return path;
	}

	private static String getAppDir() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		path = path.substring(0, path.indexOf("/WEB-INF/classes"));
		return path;
	}

	/**
	 * Guess the application server home directory
	 */
	private static String getHomeDir() {
		// Try JBoss
		String dir = System.getProperty("jboss.home.dir");
		if (dir != null) {
			logger.info("Using JBoss: " + dir);
			return dir;
		}

		// Try Tomcat
		dir = System.getProperty("catalina.home");
		if (dir != null) {
			logger.info("Using Tomcat: " + dir);
			return dir;
		}

		// Otherwise GWT hosted mode
		dir = System.getProperty("user.dir") + "/src/test/resources";
		logger.info("Using default dir: " + dir);
		return dir;
	}

	/**
	 * Guess the system wide temporary directory
	 */
	private static String getTempDir() {
		String dir = System.getProperty("java.io.tmpdir");
		if (dir != null) {
			return dir;
		} else {
			return "";
		}
	}

	/**
	 * Guess the system null device
	 */
	private static String getNullDevice() {
		String os = System.getProperty("os.name").toLowerCase();

		if (os.contains("linux") || os.contains("mac os")) {
			return "/dev/null";
		} else if (os.contains("windows")) {
			return "NUL:";
		} else {
			return null;
		}
	}

	/**
	 * Test if is running in application server
	 */
	private static boolean inServer() {
		if (System.getProperty("jboss.home.dir") != null || System.getProperty("catalina.home") != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get url base
	 */
	private static String getBase(String url) {
		String ret = "";

		int idx1 = url.lastIndexOf('/');
		if (idx1 > 0)
			ret = url.substring(0, idx1);
		int idx2 = ret.lastIndexOf('/');
		if (idx2 > 0)
			ret = ret.substring(0, idx2);

		return ret;
	}

	//	@PostConstruct
	private static void load() {
		Properties config = new Properties();
		String configFile = CLASS_DIR + "/" + WELLOA_CONFIG;

		// Read config
		try {
			logger.info("** Reading config file " + configFile + " **");
			FileInputStream fis = new FileInputStream(configFile);
			config.load(fis);
			UPLOAD_DIR = config.getProperty(PROPERTY_UPLOAD_DIR, UPLOAD_DIR);
			values.put(PROPERTY_UPLOAD_DIR, UPLOAD_DIR);

			fis.close();
		} catch (FileNotFoundException e) {
			logger.warn("** No " + WELLOA_CONFIG + " file found, set default config **");
		} catch (IOException e) {
			logger.warn("** IOError reading " + WELLOA_CONFIG + ", set default config **");
		} finally {
			logger.info("** Configuration **");
			//			for (Entry<String, String> entry : values.entrySet()) {
			//				log.info("{} => {}", entry.getKey(), entry.getValue());
			//			}
		}
	}

	public static String getValue(String key) {
		//		if (values == null) {
		//			values = new TreeMap<String, String>();
		//			load();
		//		}
		return values.get(key);
	}

}
