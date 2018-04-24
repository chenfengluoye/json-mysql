package ckj.lib.mysql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 生成占位符的sql语句的类，还生产参数
 * @author chengkaiju
 *
 */
public class OcpSqlAndArgs {
	
	DBCore dbcore;
	public OcpSqlAndArgs(DBCore dbcore){
		this.dbcore=dbcore;
	}
	
	/**
	 * 根据条件查询，生成SQL语句,与关系，占位符的方式
	 * @param table
	 * @param js
	 * @return
	 */
	public SqlJSONObject formQueryAndSql(String table,JSONObject js){
		SqlJSONObject re=new SqlJSONObject();
		JSONArray args=new JSONArray();
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
				args.add(js.opt(key));
			}
		}
		re.put("sql", sql);
		re.put("args",args);
		return re;
	}
	
	/**
	 * 根据条件查询，生成SQL语句,与关系
	 * @param table
	 * @param js
	 * @return
	 */
	public SqlJSONObject formQueryOrSql(String table,JSONObject js){
		SqlJSONObject re=new SqlJSONObject();
		List args=new ArrayList();
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
				args.add(js.opt(key));
			}
		}
		re.put("sql",sql);
		re.put("args",args.toArray());
		return re;
	}
	
	
	/**
	 * 生成与关系的sql删除语句
	 * @param table
	 * @param js
	 * @return SqlJSONObject对象，格式为{"sql":"","args":{}}
	 */
	public SqlJSONObject formDeleteAndSql(String table,JSONObject js){
		SqlJSONObject re=new SqlJSONObject();
		List args=new ArrayList();
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
				args.add(js.opt(key));
			}
		}
		re.put("sql",sql);
		re.put("args",args.toArray());
		return re;
	}

	/**
	 * 生成或关系的sql删除语句
	 * @param table
	 * @param js
	 * @return
	 */
	public SqlJSONObject formDeleteOrSql(String table,JSONObject js){
		SqlJSONObject re=new SqlJSONObject();
		List args=new ArrayList();
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
				args.add(js.opt(key));
			}
		}
		re.put("sql",sql);
		re.put("args",args.toArray());
		return re;
	}
	
	
	/**
	 * 形成与关系的更新sql语句
	 * @param table
	 * @param conditions
	 * @param newdata
	 * @return
	 */
	public SqlJSONObject formUpdateAndSql(String table,JSONObject conditions,JSONObject newdata){
		SqlJSONObject re=new SqlJSONObject();
		List args=new ArrayList();
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
				args.add(newdata.opt(key));
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
				args.add(conditions.opt(key));
			}
		}
		re.put("sql",sql);
		re.put("args",args.toArray());
		return re;
	}
	
	/**
	 * 形成或关系的更新sql语句
	 * @param table
	 * @param conditions
	 * @param newdata
	 * @return
	 */
	public SqlJSONObject formUpdateOrSql(String table,JSONObject conditions,JSONObject newdata){
		SqlJSONObject re=new SqlJSONObject();
		List args=new ArrayList();
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
				args.add(conditions.opt(key));
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
				args.add(conditions.opt(key));
			}
		}
		re.put("sql",sql);
		re.put("args",args.toArray());
		return re;
	}

	
	/**
	 * 生成添加的sql语句
	 * @param table
	 * @param data
	 * @return
	 */
	public SqlJSONObject formAddSql(String table,JSONObject data){
		SqlJSONObject re=new SqlJSONObject();
		List args=new ArrayList();
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
				args.add(data.opt(key));
			}
		}
		sql+=")";
		value+=")";
		sql+="values"+value;
		re.put("sql",sql);
		re.put("args",args.toArray());
		return re;
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
