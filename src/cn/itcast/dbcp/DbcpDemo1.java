package cn.itcast.dbcp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil;

/**
 * DBCP连接池
 */
public class DbcpDemo1 {
	
	/**
	 * 入门
	 */
	@Test
	public void run2(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// ProPeties对象
			Properties pro = new Properties();
			pro.load(DbcpDemo1.class.getClassLoader().getResourceAsStream("dbcp.properties"));
			
			// BasicDataSourceFactory类
			DataSource dataSource = BasicDataSourceFactory.createDataSource(pro);
			// 获取链接
			conn = dataSource.getConnection();
			// 编写SQL
			String sql = "update t_account set money = 25000 where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 4);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			// 改成了归还的方法
			MyJdbcUtil.release(stmt, conn);
		}
	}
	
	
	/**
	 * 入门
	 */
	@Test
	public void run(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 从DBCP连接池中获取链接
			BasicDataSource dataSource = new BasicDataSource();
			// 设置用户名和密码链接（4大参数）
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			// 设置链接
			dataSource.setUrl("jdbc:mysql:///day18?useUnicode=true&characterEncoding=UTF-8");
			// 设置用户名
			dataSource.setUsername("root");
			dataSource.setPassword("123456");
			// 获取链接
			conn = dataSource.getConnection();
			
			// 编写SQL
			String sql = "update t_account set money = 15000 where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 4);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			// 改成了归还的方法
			MyJdbcUtil.release(stmt, conn);
		}
	}

}
