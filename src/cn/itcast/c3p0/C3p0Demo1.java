package cn.itcast.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.itcast.utils.MyJdbcUtil;

/**
 * C3P0连接池快速入门
 */
public class C3p0Demo1 {
	
	@Test
	public void run2(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 默认去查询指定路径下（src）的文件（c3p0-config.xml）
			ComboPooledDataSource dataSource = new ComboPooledDataSource("myoracle");
			// 获取链接
			conn = dataSource.getConnection();
			// 编写SQL
			String sql = "update t_account set username = ? where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "赵四");
			stmt.setInt(2, 4);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			// 改成了归还的方法
			MyJdbcUtil.release(stmt, conn);
		}
	}
	
	@Test
	public void run1(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			// 设置参数
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setJdbcUrl("jdbc:mysql:///day18?useUnicode=true&characterEncoding=UTF-8");
			dataSource.setUser("root");
			dataSource.setPassword("123456");
			
			// 获取链接
			conn = dataSource.getConnection();
			
			// 编写SQL
			String sql = "update t_account set username = ? where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "赵四");
			stmt.setInt(2, 4);
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
