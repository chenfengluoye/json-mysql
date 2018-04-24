package ckj.lib.test;

import java.util.Arrays;

import ckj.lib.mysql.CompleteSql;
import ckj.lib.mysql.DBCore;
import ckj.lib.mysql.DBJsonCore;
import ckj.lib.mysql.DBManager;
import ckj.lib.mysql.DefaultDBManager;
import ckj.lib.mysql.OcpSqlAndArgs;
import ckj.lib.mysql.SqlJSONObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * DBJsonCore对象用法示例，（最好为每个数据库定义为一个全局的变量）
 * @author chengkaiju
 *
 */
public class DBJsonCoreSample {
	
	
	
	//第一步，创建数据库管理器对象DBManager,只要实现DBManager接口的对象都可以，DefaultDBManager为一个默认的实现类
	static DefaultDBManager manager=new DefaultDBManager();
	//	static{
	//		manager.setUrl(url);
	//		manager.setUsername(username);
	//		manager.setPassword(password);
	//	}

	//第一步，	static DBManager manager=new DefaultDBManager(dburl,username,password);
	
	//第二步，创建数据库核心操作对象
	static DBCore core=new DBCore(manager);
	
	//第三步，创建数据库占位符sql构造对象(可选)
	static OcpSqlAndArgs ocpSql=new OcpSqlAndArgs(core);
	
	//第三步，数据库json操作对象(可选)
	static DBJsonCore dbjsoncore=new DBJsonCore(core,ocpSql);

	
	
	
	public static void main(String[] args) {	
		
		try {
			//添加json对象到数据库表student中
			JSONObject addsql=new JSONObject();
			addsql.put("name", "陈开举");
			addsql.put("pass", "mypass");
			addsql.put("sex", "男");
			addsql.put("age", "15");
			int i=dbjsoncore.add("student", addsql);
			System.out.println(i);
			//删除表student中id=201509030203的记录
			JSONObject delsql=new JSONObject();
			delsql.put("id", "201509030203");
			i=dbjsoncore.delete("student", delsql);
			System.out.println(i);
			//修改表student中name='李子龙'的记录，修改age=20,pass=123
			JSONObject condition=new JSONObject();
			condition.put("name","李子龙");
			JSONObject newdata=new JSONObject();
			newdata.put("age",20);
			newdata.put("pass", "123");
			i=dbjsoncore.update("student", condition, newdata);
			System.out.println(i);
			
			
			//查询name='李子龙'所有记录
			JSONObject conditionsql=new JSONObject();
			conditionsql.put("name","李子龙");
			JSONArray array=dbjsoncore.selectList("student", conditionsql);
			System.out.println(array);
			
			//查询id='2015'的唯一的记录
			JSONObject idcondition=new JSONObject();
			idcondition.put("id","2015");
			JSONObject studentitem=dbjsoncore.selectOne("student", idcondition);
			System.out.println(studentitem);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
