package ckj.lib.test;

import java.util.Arrays;

import ckj.lib.mysql.DBCore;
import ckj.lib.mysql.DefaultDBManager;
import ckj.lib.mysql.OccupySql;
import ckj.lib.mysql.OcpSqlAndArgs;
import ckj.lib.mysql.SqlJSONObject;
import net.sf.json.JSONObject;

public class OcpSqlAndArgsSample {
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
			
			//第三步，创建OccupySql对象
			static OcpSqlAndArgs ocpargsql=new OcpSqlAndArgs(core);
			
			public static void main(String[] args) {
				
				//由json对象形成添加的sql语句
				JSONObject addsql=new JSONObject();
				addsql.put("name", "陈开举");
				addsql.put("pass", "mypass");
				addsql.put("sex", "男");
				addsql.put("age", "15");
				SqlJSONObject sqlobject=ocpargsql.formAddSql("student",addsql);
				String  sql=sqlobject.getSql();
				Object[]sqlargs=sqlobject.getSqlArgs1();
				System.out.println(sql);
				System.out.println(Arrays.toString(sqlargs));
				
				//由json对象形成删除的sql语句
				JSONObject delsql=new JSONObject();
				delsql.put("name", "陈开举");
				sqlobject=ocpargsql.formDeleteAndSql("student",delsql);
				sql=sqlobject.getSql();
				sqlargs=sqlobject.getSqlArgs1();
				System.out.println(sql);
				System.out.println(Arrays.toString(sqlargs));
				
				//由json对象生成修改的sql语句
				JSONObject condition=new JSONObject();
				condition.put("name","李龙");
				JSONObject newdata=new JSONObject();
				newdata.put("sex","女");
				sqlobject=ocpargsql.formUpdateAndSql("student", condition,newdata);
			    sql=sqlobject.getSql();
				sqlargs=sqlobject.getSqlArgs1();
				System.out.println(sql);
				System.out.println(Arrays.toString(sqlargs));
				
				//由json对象生成查询的sql语句
				JSONObject quercondition=new JSONObject();
				quercondition.put("name","李龙");
				sqlobject=ocpargsql.formQueryAndSql("student", condition);
				sql=sqlobject.getSql();
				sqlargs=sqlobject.getSqlArgs1();
				System.out.println(sql);
				System.out.println(Arrays.toString(sqlargs));
			}
}
