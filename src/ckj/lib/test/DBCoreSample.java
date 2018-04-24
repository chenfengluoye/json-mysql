package ckj.lib.test;

import ckj.lib.mysql.DBCore;
import ckj.lib.mysql.DBManager;
import ckj.lib.mysql.DefaultDBManager;

/**
 * DBCore对象用法示例，（最好为每个数据库定义为一个全局的该类对象）
 * @author chengkaiju
 *
 */
public class DBCoreSample {

	//第一步，创建数据库管理器对象DBManager,只要实现DBManager接口的对象都可以，DefaultDBManager为一个默认的实现类
		static DefaultDBManager manager=new DefaultDBManager();
//		static{
//			manager.setUrl(url);
//			manager.setUsername(username);
//			manager.setPassword(password);
//	    }

		//第一步，	static DBManager manager=new DefaultDBManager(dburl,username,password);
		
		//第二步，创建数据库核心操作对象
		static DBCore core=new DBCore(manager);
		
		public static void main(String[] args) throws Exception {
			
			//添加记录到数据库中,core.add方法只有第一个为必传参数，后面的都可以不要
			String sql="insert into student (name,age,sex,pass) values(?,?,?,?)";
			int i=core.add(sql,"王军",25,"男","wjssa");
			System.out.println(i);
			
			//添加记录到数据库中
		    sql="insert into student (name,age,sex,pass) values('柳中',25,'男','lyss')";
			i=core.add(sql);
			System.out.println(i);
			
			//删除示例
			sql="delete from student where id='2015'";
			i=core.delelte(sql);
			System.out.println(i);
		}
}
