package ckj.lib.mysql;

import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 完整的sql生成类，注意使用该类对象生成的sql无法避免sql注入，建议使用OccupySql和OcpSqlAndArgs
 * @author chengkaiju
 *
 */
public class CompleteSql {
	
	//	数据库核心操作对象
	DBCore dbcore;
	
	public CompleteSql(DBCore dbcore){
		this.dbcore=dbcore;
	}
	
	/**
	 * 根据条件查询，生成SQL语句,与关系，占位符的方式
	 * @param table
	 * @param js
	 * @return
	 */
	public String formQueryAndSql(String table,JSONObject js){
		String sql="select * from "+table+" where " ;
		JSONArray keys=dbcore.getTableKeys(table);
		boolean isback=false;
		Iterator<String> it=js.keys();
		while(it.hasNext()){
			String key=it.next();
			if(keys.contains(key)){
				if(isback){
					sql+=" and ";
				}
				sql+=key+"='"+js.optString(key)+"'";
				isback=true;
			}
		}
		return sql;
	}
	
	/**
	 * 根据条件查询，生成SQL语句,与关系
	 * @param table
	 * @param js
	 * @return
	 */
	public String formQueryOrSql(String table,JSONObject js){
		String sql="select * from "+table+" where " ;
		JSONArray keys=dbcore.getTableKeys(table);
		boolean isback=false;
		Iterator<String> it=js.keys();
		while(it.hasNext()){
			String key=it.next();
			if(keys.contains(key)){
				if(isback){
					sql+=" or ";
				}
				sql+=key+"='"+js.optString(key)+"'";
				isback=true;
			}
		}
		return sql;
	}
	
	
	/**
	 * 生成与关系的sql删除语句
	 * @param table
	 * @param js
	 * @return 
	 */
	public String formDeleteAndSql(String table,JSONObject js){
		String sql="delete from "+table+" where " ;
		JSONArray keys=dbcore.getTableKeys(table);
		boolean isback=false;
		Iterator<String> it=js.keys();
		while(it.hasNext()){
			String key=it.next();
			if(keys.contains(key)){
				if(isback){
					sql+=" and ";
				}
				sql+=key+"='"+js.optString(key)+"'";
				isback=true;
			}
		}
		return sql;
	}

	/**
	 * 生成或关系的sql删除语句
	 * @param table
	 * @param js
	 * @return
	 */
	public String formDeleteOrSql(String table,JSONObject js){
		String sql="delete from "+table+" where " ;
		boolean isback=false;
		JSONArray keys=dbcore.getTableKeys(table);
		Iterator<String> it=js.keys();
		while(it.hasNext()){
			String key=it.next();
			if(keys.contains(key)){
				if(isback){
					sql+=" or ";
				}
				sql+=key+"='"+js.optString(key)+"'";
				isback=true;
			}
		}
		return sql;
	}
	
	
	/**
	 * 形成与关系的更新sql语句
	 * @param table
	 * @param conditions
	 * @param newdata
	 * @return
	 */
	public String formUpdateAndSql(String table,JSONObject conditions,JSONObject newdata){
		String sql="update "+table+" set " ;
		JSONArray keys=dbcore.getTableKeys(table);
		boolean isback=false;
		Iterator<String> it=newdata.keys();
		while(it.hasNext()){
			String key=it.next();
			if(keys.contains(key)){
				if(isback){
					sql+=",";
				}
				sql+=key+"='"+newdata.optString(key)+"'";
				isback=true;
			}
		}
		sql+=" where ";
		isback=false;
		Iterator<String> its=conditions.keys();
		while(its.hasNext()){
			String key=its.next();
			if(key.contains(key)){
				if(isback){
					sql+=" and ";
				}
				sql+=key+"='"+conditions.optString(key)+"'";
				isback=true;
			}
		}
		return sql;
	}
	
	/**
	 * 形成或关系的更新sql语句
	 * @param table
	 * @param conditions
	 * @param newdata
	 * @return
	 */
	public String formUpdateOrSql(String table,JSONObject conditions,JSONObject newdata){
		String sql="update "+table+" set " ;
		JSONArray keys=dbcore.getTableKeys(table);
		boolean isback=false;
		Iterator<String> it=newdata.keys();
		while(it.hasNext()){
			String key=it.next();
			if(keys.contains(key)){
				if(isback){
					sql+=",";
				}
				sql+=key+"='"+newdata.optString(key)+"'";
				isback=true;
			}
		}
		sql+=" where ";
		isback=false;
		Iterator<String> its=conditions.keys();
		while(its.hasNext()){
			String key=its.next();
			if(keys.contains(key)){
				if(isback){
					sql+=" or ";
				}
				sql+=key+"='"+conditions.optString(key)+"'";
				isback=true;
			}
		}
		return sql;
	}

	
	/**
	 * 生成添加的sql语句
	 * @param table
	 * @param data
	 * @return
	 */
	public String formAddSql(String table,JSONObject data){
		String sql="insert into "+table+" (";
		JSONArray keys=dbcore.getTableKeys(table);
		String value="(";
		Iterator<String> its=data.keys();
		boolean isback=false;
		while(its.hasNext()){
			String key=its.next();
			if(keys.contains(key)){
				if(isback){
					sql+=",";
					value+=",";
				}
				sql+=key;
				value+="'"+data.optString(key)+"'";
				isback=true;
			}
		}
		sql+=")";
		value+=")";
		sql+="values"+value;
		return sql;
	}
	
}
