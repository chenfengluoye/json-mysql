package ckj.lib.mysql;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 数据库对于json的操作类
 * @author chengkaiju
 *
 */
public class DBJsonCore {
	
	//数据库核心操作对象
	DBCore dbcore;
	
	//占位符sql构造对象
	OcpSqlAndArgs occupySql;

	/**
	 * 构造函数，
	 * @param dbcore 数据库操作核心对象
	 * @param occupySql 生成数据库占位符sql的对象
	 */
	public DBJsonCore(DBCore dbcore,OcpSqlAndArgs occupySql) {
		this.dbcore = dbcore;
		this.occupySql=occupySql;
	}
	
	
	
	/**
	 * 添加记录到数据库中
	 * @param table 表名
	 * @param data 数据
	 * @return
	 * @throws Exception
	 */
	public int add(String table,JSONObject data)throws Exception{
		SqlJSONObject sqljs=occupySql.formAddSql(table, data);
		int re=dbcore.add(sqljs.optString("sql"),sqljs.optArray("args"));
		return re;
	}
	
	
	
	
	/**
	 * 删除数据，将给定的条件以与关系合并
	 * @param table 表名
	 * @param condition 条件
	 * @return
	 * @throws Exception
	 */
	public int delete(String table,JSONObject condition)throws Exception{
		SqlJSONObject sqljs=occupySql.formDeleteAndSql(table, condition);
		int re=dbcore.delelte(sqljs.optString("sql"),sqljs.optArray("args"));
		return re;
	}
	

	/**
	 * 删除数据，将给定的条件以或关系合并
	 * @param table 表名
	 * @param condition 条件
	 * @return
	 * @throws Exception
	 */
	public int deleteOr(String table,JSONObject condition)throws Exception{
		SqlJSONObject sqljs=occupySql.formDeleteOrSql(table, condition);
		int  re=dbcore.delelte(sqljs.optString("sql"),sqljs.optArray("args"));
		return re;
	}
	
	/**
	 * 更新记录，将给定的条件以与关系合并
	 * @param table 表名
	 * @param condition 条件
	 * @param data 新数据
	 * @return
	 * @throws Exception
	 */
	public int update(String table,JSONObject condition,JSONObject data)throws Exception{
		SqlJSONObject sqljs=occupySql.formUpdateAndSql(table, condition, data);
		int re=dbcore.update(sqljs.optString("sql"),sqljs.optArray("args"));
		return re;
	}
	
	/**
	 * 更新记录，将给定的条件以或关系合并
	 * @param table 表名
	 * @param condition 条件
	 * @param data 新数据
	 * @return
	 * @throws Exception
	 */
	public int updateOr(String table,JSONObject condition,JSONObject data)throws Exception{
		SqlJSONObject sqljs=occupySql.formUpdateOrSql(table, condition, data);
		int re=dbcore.update(sqljs.optString("sql"),sqljs.optArray("args"));
		return re;
	}
	
	/**
	 * 查询一条记录，将给定的条件以与关系合并
	 * @param table 表名
	 * @param condition 条件
	 * @return 
	 * @throws Exception
	 */
	public JSONObject selectOne(String table,JSONObject condition)throws Exception{
		SqlJSONObject sqljs=occupySql.formQueryAndSql(table, condition);
		JSONObject re=dbcore.selectOne(sqljs.optString("sql"),sqljs.optArray("args"));
		return re;
	}
	
	
	/**
	 * 查询多条记录，将给定的条件以与关系合并
	 * @param table 表名
	 * @param condition 条件
	 * @return
	 * @throws Exception
	 */
	public JSONArray selectList(String table,JSONObject condition)throws Exception{
		SqlJSONObject sqljs=occupySql.formQueryAndSql(table, condition);
		JSONArray re=dbcore.selectList(sqljs.optString("sql"),sqljs.optArray("args"));
		return re;
	}
	
	/**
	 * 查询一条记录，将给定的条件以或关系合并
	 * @param table 表名
	 * @param condition 条件
	 * @return 
	 * @throws Exception
	 */
	public JSONObject selectOneOr(String table,JSONObject condition)throws Exception{
		SqlJSONObject sqljs=occupySql.formQueryOrSql(table, condition);
		JSONObject re=dbcore.selectOne(sqljs.optString("sql"),sqljs.optArray("args"));
		return re;
	}
	
	
	/**
	 * 查询多条记录，将给定的条件以或关系合并
	 * @param table 表名
	 * @param condition 条件
	 * @return
	 * @throws Exception
	 */
	public JSONArray selectListOr(String table,JSONObject condition)throws Exception{
		SqlJSONObject sqljs=occupySql.formQueryOrSql(table, condition);
		JSONArray re=dbcore.selectList(sqljs.optString("sql"),sqljs.optArray("args"));
		return re;
	}
	
	
	
}
