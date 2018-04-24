package ckj.lib.test;

import ckj.lib.mysql.CompleteSql;
import ckj.lib.mysql.DBCore;
import ckj.lib.mysql.DBJsonCore;
import ckj.lib.mysql.DefaultDBManager;
import ckj.lib.mysql.OcpSqlAndArgs;
import net.sf.json.JSONObject;

/**
 * 完整sql生成对象使用示例，该对象采用了直接拼接字符串的方式，谨慎使用
 * @author chengkaiju
 *
 */
public class CompleteSqlSample {
	//第一步，创建数据库管理器对象DBManager,只要实现DBManager接口的对象都可以，DefaultDBManager为一个默认的实现类
		static DefaultDBManager manager=new DefaultDBManager();
//		static{
//			manager.setUrl(url);
//			manager.setUsername(username);
//			manager.setPassword(password);
//		}

		//第一步，	static DBManager manager=new DefaultDBManager(dburl,username,password);
		
		//第二步，创建数据库核心操作对象
		static DBCore core=new DBCore(manager);
		
		//第三步，创建数据库占位符sql构造对象(可选)
		static CompleteSql cpltSql=new CompleteSql(core);
		
		public static void main(String[] args) {
			//由json对象形成添加的sql语句
			JSONObject addsql=new JSONObject();
			addsql.put("name", "陈开举");
			addsql.put("pass", "mypass");
			addsql.put("sex", "男");
			addsql.put("age", "15");
			String sql=cpltSql.formAddSql("student",addsql);
			System.out.println(sql);
			//由json对象形成删除的sql语句
			JSONObject delsql=new JSONObject();
			delsql.put("name", "陈开举");
			sql=cpltSql.formDeleteAndSql("student",delsql);
			System.out.println(sql);
			
			//由json对象生成修改的sql语句
			JSONObject condition=new JSONObject();
			condition.put("name","李龙");
			JSONObject newdata=new JSONObject();
			newdata.put("sex","女");
			sql=cpltSql.formUpdateAndSql("student", condition,newdata);
			System.out.println(sql);
			
			//由json对象生成查询的sql语句
			JSONObject quercondition=new JSONObject();
			quercondition.put("name","李龙");
			sql=cpltSql.formQueryAndSql("student", condition);
			System.out.println(sql);
		}
		
		
}
