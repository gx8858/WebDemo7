package cn.itcast.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import cn.itcast.utils.MyDataSource;
import cn.itcast.utils.MyJdbcUtil;

/**
 * 自定义的链接
 * @author Administrator
 *
 */
public class DataSourceDemo1 {
	
	@Test
	public void run(){
		Connection conn = null;
		PreparedStatement stmt = null;
		MyDataSource dataSource = null;
		try {
			// 先获取连接池
			dataSource = new MyDataSource();
			// 获取链接
			conn = dataSource.getConnection();
			
			// 编写SQL
			String sql = "delete from t_account where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 5);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			/*if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt = null;
			}*/
			
			// 原来是销毁
			MyJdbcUtil.release(stmt, conn);
			
			// 归还conn的链接
			// dataSource.addBack(conn);
		}
		
	}

}





