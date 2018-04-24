package ckj.lib.mysql;

import java.sql.Connection;


/**
 * 数据库连接管理接口，本jar包提供一个默认的实现类DefaultDBManager
 * @author chengkaiju
 *
 */
public interface DBManager {
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getConnection();
	
	/**
	 * 关闭数据库连接
	 * @param con
	 * @return
	 */
	public boolean closeConnection(Connection con);
	

}
