package cn.edu.modules.test.solr;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;



/**
 * 
* @ClassName: Test
* @Description: SolrJ测试类
* @author chrimer(林江毅)
* @date 2014-10-15 下午11:01:18
* 索引结构中 只有两个字段，一个是 id  ，一个是 name  都是String 类型的
 */

public class Test {

	//8080是web服务器的端口号，需要根据情况进行调整
	private static String url = "http://218.104.141.242:7001/solr/";
	//core0是core的名称
	private static String core = "collection1";
	//private static SolrServer server = null;//
	private static HttpSolrServer server = null;// new CommonsHttpSolrServer(url); 
	public static void main(String[] args) throws SolrServerException, IOException {
	
//		//新增
//		SolrInputDocument doc1 = new SolrInputDocument(); 
//		doc1.addField( "id", "4" ); 
//		doc1.addField( "name", "yaxon" );
//		
//		SolrInputDocument doc2 = new SolrInputDocument(); 
//		doc2.addField( "id", "5" ); 
//		doc2.addField( "name", "fzu" );
//		
//		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
//		docs.add( doc1 );
//		docs.add( doc2 );
//		add(docs);
//		
//		//删除
//		String id="4";
//		delete(id);
//		
		
		//查询
		SolrQuery query = new SolrQuery();
		Map<String,String> map = new HashMap<String,String>(); 
//		map.put("DWMC00", "厦门");
		query.setQuery("DWMC00:工程");
		query.addFilterQuery("DWMC01:工程");
		query.addFilterQuery("age:[25,35]");
		
		
		
		query.setQuery("*:*");
		
		//设置sfrom的取值范围为[0-500]
//		query.addFilterQuery("sfrom:[* TO 500]");
		//结果按sfrom值降序
//		query.addSort("sfrom", ORDER.desc);
		//分页返回结果
        query.setStart(0);//设置起始位置
        query.setRows(3);//设置每页条数
//        query.add();
      
        SolrDocumentList docResult = query(query);
		if(docResult.getNumFound()>0){
			for(int i=0;i<docResult.size();i++){
				System.out.println(docResult.get(i));
				System.out.println(docResult.get(i).get("DWMC00"));
//				System.out.println(docResult.get(i).get("name"));
//				System.out.println(docResult.get(i).get("sfrom"));
//				System.out.println(docResult.get(i).get("sto"));
			}
		}else{
			System.out.println("No results!");
		}
		
		
		//没有更新，如果真的要实现，就先删除在新增
	}

	/**
	 * 
	* @Title: delete
	* @Description: 删除索引，只能通过id来删除
	* @author chrimer(林江毅)
	 * @param id 
	* @param @throws SolrServerException
	* @param @throws IOException 
	* @return void    返回类型
	* @date 2014年10月24日 上午10:52:24
	 */
	private static void delete(String id) throws SolrServerException, IOException {
		//1、建立连接
		init(url,core);
		//2、处理事务
		server.deleteById(id);
		//3、提交事务
		server.commit();	
		//4、关闭连接
		shutdown();
	}

	/**
	 * 
	* @Title: add
	* @Description: 添加索引
	* @author chrimer(林江毅)
	* @param @param docs
	* @param @throws SolrServerException
	* @param @throws IOException 
	* @return void    返回类型
	* @date 2014年10月24日 上午10:55:18
	 */
	private static void add(Collection<SolrInputDocument> docs) throws SolrServerException, IOException {
		//1、建立连接
		init(url,core);
		//2、处理事务
		server.add( docs );	
		//3、提交事务
		server.commit();	
		//4、关闭连接
		shutdown();
	}

	/**
	 * 
	* @Title: query
	* @Description: 查询索引
	* @author chrimer(林江毅)
	* @param @param query
	* @param @return
	* @param @throws SolrServerException 
	* @return SolrDocumentList    返回类型
	* @date 2014年10月24日 上午10:55:28
	 */
	private static SolrDocumentList query(SolrQuery query) throws SolrServerException {
		//1、建立连接
		init(url,core);
		//2、处理事务
		QueryResponse rsp = server.query( query );
		SolrDocumentList docs = rsp.getResults();
		//3、关闭连接
		shutdown();
		return docs;
	}

	/**
	 * 
	* @Title: init
	* @Description: 初始化连接
	* @author chrimer(林江毅)
	* @param @param url 连接的url
	* @param @param core   连接的core
	* @return void    返回类型
	* @date 2014年10月24日 上午10:49:57
	 */
	private static void init(String url, String core) {
		server = new HttpSolrServer( url+core );		
	}

	/**
	 * 
	* @Title: shutdown
	* @Description: 关闭连接
	* @author chrimer(林江毅)
	* @param  
	* @return void    返回类型
	* @date 2014年10月24日 上午10:51:02
	 */
	private static void shutdown(){
		server.shutdown();
	}
}
