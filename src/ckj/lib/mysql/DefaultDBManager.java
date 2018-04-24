package ckj.lib.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DefaultDBManager implements DBManager{
	
	//数据库url
	public String url=null;
	
	//数据库用户名
	public String username=null;
	
	//数据库密码
	public String password=null;
	
	//数据库驱动
	public String driver="com.mysql.jdbc.Driver";
	
	/**
	 *  构造数据库管理对象
	 */
	public DefaultDBManager(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 构造数据库管理对象
	 * @param url 数据库连接
	 * @param username 数据库用户名
	 * @param password 数据库密码
	 */
	public DefaultDBManager(String url,String username,String password){
		try {
			Class.forName(driver);
			this.url=url;
			this.username=username;
			this.password=password;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取驱动
	 * @return
	 */
	public String getDriver() {
		return driver;
	}
	
	

	/**
	 * 设置数据库驱动
	 * @param driver
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}


	/**
	 * 获取数据库路径
	 * @return
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * 设置数据库路径
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * 获取用户名
	 * @return
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * 设置用户名
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * 获取用户密码
	 * @return
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * 设置数据库密码
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
	
	/**
	 * 获取数据库连接
	 */
	@Override
	public Connection getConnection() {
		Connection con=null;
		try {
			con=DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 关闭数据库连接
	 */
	@Override
	public boolean closeConnection(Connection con) {
		if(con!=null){
			try {
				con.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
