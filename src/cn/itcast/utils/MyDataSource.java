package cn.itcast.utils;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import cn.itcast.datasource.ConnectionWrapper;

/**
 * 自定义的数据库连接池
 * @author Administrator
 *
 */
public class MyDataSource implements DataSource{
	
	// 定义一个List
	public List<Connection> connlist = new ArrayList<Connection>();
	
	// 构造方法
	public MyDataSource(){
		// 动态创建几个链接，存入到connlist中
		for (int i = 0; i < 5; i++) {
			// 创建一个链接
			Connection conn = MyJdbcUtil.getConnection();
			// 加入集合中
			connlist.add(conn);
		}
	}
	
	/**
	 * 从咱们自定定义连接池中获取链接的方法
	 */
	public Connection getConnection() throws SQLException {
		// 从集合中获取链接，用哪个方法呢？
		// 移除列表中指定位置的元素（可选操作）。将所有的后续元素向左移动（将其索引减 1）。返回从列表中移除的元素。 
		
		// 是MySQL的实现类
		Connection conn = connlist.remove(0);
		
		// 创建自己增强的类
		ConnectionWrapper wrapper = new ConnectionWrapper(conn, connlist);
		
		return wrapper;
	}
	
	
	// 定义归还链接的方法
	/**
	 * 该方法是 我自定义的方法，不是由接口提供的。
	 * @param conn
	 */
	public void addBack(Connection conn){
		// 添加到集合中
		connlist.add(conn);
	}
	
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
