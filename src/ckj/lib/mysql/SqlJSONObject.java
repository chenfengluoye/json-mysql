package ckj.lib.mysql;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 占位符sql语句结果类
 * @author chengkaiju
 *
 */
public class SqlJSONObject{
	JSONObject jsonobject;
	
	public SqlJSONObject(){
		jsonobject=new JSONObject();
	}
	
	public Object opt(String key){
		return jsonobject.opt(key);
	}
	
	public String optString(String key){
		return jsonobject.optString(key);
	}
	
	public Object[] optArray(String key){
		JSONArray array=jsonobject.optJSONArray(key);
		int len=array.size();
		Object[] value=new Object[len];
		for(int i=0;i<len;i++){
			value[i]=array.get(i);
		}
		return value;
	}
	
	public String[] optStrArray(String key){
		JSONArray array=jsonobject.optJSONArray(key);
		int len=array.size();
		String[] value=new String[len];
		for(int i=0;i<len;i++){
			value[i]=array.getString(i);
		}
		return value;
	}
	
	public Object put(String key,Object o){
		return jsonobject.put(key, o);
	}
	
	public String getSql(){
		return jsonobject.optString("sql");
	}
	
	public String[] getSqlArgs(){
		return optStrArray("args");
	}
	
	public Object[] getSqlArgs1(){
		return optArray("args");
	}
}
