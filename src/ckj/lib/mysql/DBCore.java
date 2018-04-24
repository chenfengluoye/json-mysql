package ckj.lib.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 数据库操作的核心类
 * @author chengkaiju
 *
 */
public class DBCore {
	
	//数据库连接管理对象
	public DBManager manager;
	
	/**
	 * 设置数据库连接管理对象
	 * @param manager
	 */
	public void setDBManaer(DBManager manager){
		this.manager=manager;
	}
	
	/**
	 * 由一个数据库连接管理对象生成数据库操作的核心对象
	 * @param manager
	 */
	public DBCore(DBManager manager){
		this.manager=manager;
	}
	
	/**
	 * 执行操作
	 * @param con 数据库连接
	 * @param sql 欲执行的sql语句
	 * @param sqlargs sql语句的参数
	 * @return true 或者 false
	 * @throws Exception
	 */
	public boolean excute(String sql,Object... sqlargs) throws Exception{
		System.out.println(sql);
		boolean re=false;
		Connection con=manager.getConnection();
		PreparedStatement sta=null;
		try{
			sta =con.prepareStatement(sql);
			for(int i=0;i<sqlargs.length;i++){
				sta.setObject(i,sqlargs[i]);
			}
			re=sta.execute();
			return re;
		}catch(Exception e){
			e.printStackTrace();
			throw(e);
		}finally{
			if(sta!=null){
				sta.close();
			}
			manager.closeConnection(con);
		}
	}
	
	
	/**
	 * 添加一条数据到数据库中
	 * @param con 数据库连接
	 * @param sql 欲执行的sql语句
	 * @param sqlargs sql语句的参数
	 * @return 受影响的行数
	 * @throws Exception
	 */
	public int add(String sql,Object... sqlargs) throws Exception{
		System.out.println(sql);
		int re=-1;
		Connection con=manager.getConnection();
		PreparedStatement sta=null;
		try{
			sta =con.prepareStatement(sql);
			for(int i=0;i<sqlargs.length;i++){
				sta.setObject(i+1,sqlargs[i]);
			}
			re=sta.executeUpdate();
			return re;
		}catch(Exception e){
			e.printStackTrace();
			throw(e);
		}finally{
			if(sta!=null){
				sta.close();
			}
			manager.closeConnection(con);
		}
	}
	
	
	/**
	 * 删除记录
	 * @param con 数据库连接
	 * @param sql 欲执行的sql语句
	 * @param sqlargs sql语句的参数
	 * @return 受影响的行数
	 * @throws Exception
	 */
	public int delelte(String sql,Object... sqlargs)throws Exception{
		System.out.println(sql);
		int re=-1;
		Connection con=manager.getConnection();
		PreparedStatement sta=null;
		try{
			sta =con.prepareStatement(sql);
			for(int i=0;i<sqlargs.length;i++){
				sta.setObject(i+1,sqlargs[i]);
			}
			re=sta.executeUpdate();
			return re;
		}catch(Exception e){
			throw(e);
		}finally{
			if(sta!=null){
				sta.close();
			}
			manager.closeConnection(con);
		}
	}
	
	
	/**
	 * 更新记录
	 * @param con 数据库连接
	 * @param sql 欲执行的sql语句
	 * @param sqlargs sql语句的参数
	 * @return true 或者 false
	 * @throws Exception
	 */
	public int update(String sql,Object... sqlargs)throws Exception{
		System.out.println(sql);
		int re=-1;
		PreparedStatement sta=null;
		Connection con=manager.getConnection();
		try{
			sta =con.prepareStatement(sql);
			for(int i=0;i<sqlargs.length;i++){
				sta.setObject(i+1,sqlargs[i]);
			}
			re=sta.executeUpdate();
			return re;
		}catch(Exception e){
			throw(e);
		}finally{
			if(sta!=null){
				sta.close();
			}
			manager.closeConnection(con);
		}
	}

	/**
	 * 查询唯一记录，并封装成jsonobject对象，若结果有多条，则返回最前面一条
	 * @param con 数据库连接
	 * @param sql 欲执行的sql语句
	 * @param sqlargs sql语句的参数
	 * @return true 或者 false
	 * @throws Exception
	 */
	public JSONObject selectOne(String sql,Object... sqlargs)throws Exception{
		System.out.println(sql);
		PreparedStatement sta=null;
		ResultSet set=null;
		Connection con=manager.getConnection();
		JSONObject re=null;
		try{
			sta =con.prepareStatement(sql);
			for(int i=0;i<sqlargs.length;i++){
				sta.setObject(i+1,sqlargs[i]);
			}
			set=sta.executeQuery();
			re=getJSOFromSet(set);
			return re;
		}catch(Exception e){
			throw(e);
		}finally{
			if(sta!=null){
				sta.close();
			}
			manager.closeConnection(con);
		}
	}
	
	
	/**
	 * 查询多条记录，并封装成JSONArray对象
	 * @param con 数据库连接
	 * @param sql 欲执行的sql语句
	 * @param sqlargs sql语句的参数
	 * @return true 或者 false
	 * @throws Exception
	 */
	public JSONArray selectList(String sql,Object... sqlargs)throws Exception{
		System.out.println(sql);
		PreparedStatement sta=null;
		Connection con=manager.getConnection();
		ResultSet set=null;
		try{
			sta =con.prepareStatement(sql);
			for(int i=0;i<sqlargs.length;i++){
				sta.setObject(i+1,sqlargs[i]);
			}
			set=sta.executeQuery();
			JSONArray array=getJSAFromSet(set);
			return array;
		}catch(Exception e){
			throw(e);
		}finally{
			if(sta!=null){
				sta.close();
			}
			manager.closeConnection(con);
		}
	}

	/**
	 * 修改表
	 * @param con 数据库连接
	 * @param sql 欲执行的sql语句
	 * @param sqlargs sql语句的参数
	 * @return true 或者 false
	 * @throws Exception
	 */
	public boolean alter(String sql,Object... sqlargs)throws Exception{
		System.out.println(sql);
		boolean re=false;
		Connection con=manager.getConnection();
		PreparedStatement sta=null;
		try{
			sta =con.prepareStatement(sql);
			for(int i=0;i<sqlargs.length;i++){
				sta.setObject(i+1,sqlargs[i]);
			}
			re=sta.execute();
			return re;
		}catch(Exception e){
			throw(e);
		}finally{
			if(sta!=null){
				sta.close();
			}
			manager.closeConnection(con);
		}
	}
	
	/**
	 * 将结果集封装成jsonObject对象并返回，同时关闭结果集
	 * @param set ResultSet结果集
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getJSOFromSet(ResultSet set){

		try{
			JSONObject ob=null;
			ResultSetMetaData data=set.getMetaData();
			int cc=data.getColumnCount();
			if(set.next()){
				ob=new JSONObject();
				for(int i=1;i<=cc;i++){
					ob.put(data.getColumnName(i),set.getObject(i));
				}
			}
			set.close();
			return ob;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将结果集封装成JSONArray对象并返回，同时关闭结果集
	 * @param set ResultSet结果集
	 * @return JSONArray
	 * @throws Exception
	 */
	public JSONArray getJSAFromSet(ResultSet set){

		JSONArray array=null;
		try{
			ResultSetMetaData data=set.getMetaData();
			int cc=data.getColumnCount();
			array=new JSONArray();
			while(set.next()){
				JSONObject ob=new JSONObject();
				for(int i=1;i<=cc;i++){
					ob.put(data.getColumnName(i),set.getObject(i));
				}
				array.add(ob);
			}
			set.close();
			return array;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 执行一组sql语句，当成事物处理，并关闭连接
	 * @param con
	 * @param sqls
	 * @return
	 * @throws Exception
	 */
	public boolean excuteSqls(String... sqls) throws Exception{
		PreparedStatement sta=null;
		Connection con=manager.getConnection();
		boolean re=false;
		try{
			con.setAutoCommit(false);
			for(String sql:sqls){
				System.out.println(sql);
				sta=con.prepareStatement(sql);
				sta.execute();
			}
			con.commit();
			con.setAutoCommit(true);
			re=true;
			return re;
		}catch(Exception e){
			e.printStackTrace();
			con.rollback();
		}finally{
			sta.close();
			manager.closeConnection(con);
		}
		return re;
	}	
	
	
	/**
	 * 获取某张表所有的字段
	 * @param table
	 * @return
	 */
	public JSONArray getTableKeys(String table){
		JSONArray re=null;
		ResultSet set=null;
		PreparedStatement pre=null;
		Connection con=manager.getConnection();
		try{
			String sql="show full columns from " + table;
			pre=con.prepareStatement(sql);
			set=pre.executeQuery();
	        re=new JSONArray();
			while(set.next()){
				re.add(set.getString("Field"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(set!=null){
					set.close();
				}if(pre!=null){
					pre.close();
				}
				manager.closeConnection(con);
			}catch(Exception e){
			}
		}
		return re;
	}
	
	/**
	 * 获取某张表的所有字段及注释
	 * @param table
	 * @return
	 */
	public JSONArray getTableKeyAndExplain(String table){
		JSONArray re=null;
		ResultSet set=null;
		PreparedStatement pre=null;
		Connection con=manager.getConnection();
		try{
			String sql="show full columns from " + table;
			pre=con.prepareStatement(sql);
			set=pre.executeQuery();
	        re=new JSONArray();
			while(set.next()){
				JSONObject item=new JSONObject();
				item.put(set.getString("Field"),set.getString("Comment"));
				re.add(item);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(set!=null){
					set.close();
				}if(pre!=null){
					pre.close();
				}
				manager.closeConnection(con);
			}catch(Exception e){
			}
		}
		return re;
	}	

}
