package ckj.lib.mysql;

import java.util.Iterator;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 生成占位符的sql类，仅仅生成sql,不生成参数值
 * @author chengkaiju
 *
 */
public class OccupySql {
	
	DBCore dbcore;
	public OccupySql(DBCore dbcore){
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
				sql+=key+"=?";
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
				sql+=key+"=?";
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
				sql+=key+"=?";
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
				sql+=key+"=?";
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
				sql+=key+"=?";
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
				sql+=key+"=?";
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
				sql+=key+"=?";
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
				sql+=key+"=?";
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
				value+="?";
				isback=true;
			}
		}
		sql+=")";
		value+=")";
		sql+="values"+value;
		return sql;
	}
	
	
	
	/**
	 * 生成字符串集合格式的数据，适用于sql语句中的字符串集合类型
	 * @param array
	 * @return
	 */
	public String formStrCollectionForDB(JSONArray array){
		String formStr="(";
		int i;
		int len=array.size();
		if(len==0){
			return "()";
		}
		for(i=0;i<array.size()-1;i++){
			formStr+="'"+array.getString(i)+"',";
		}
		formStr+="'"+array.get(i)+"')";
		return formStr;
	}
	
	/**
	 * 生成数字集合格式的数据，适用于sql语句中的数字集合类型
	 * @param array
	 * @return
	 */
	public String formNumbCollectionForDB(JSONArray array){
		String formStr="(";
		int i;
		int len=array.size();
		if(len==0){
			return "()";
		}
		for(i=0;i<array.size()-1;i++){
			formStr+=array.getString(i)+",";
		}
		formStr+=array.get(i)+")";
		return formStr;
	}
	
}
