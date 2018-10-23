package cn.itcast.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil;

/**
 * 使用JDBC操作事务
 */
public class Demo1 {
	
	/**
	 * 带有设置保存点
	 * Savepoint sp = conn.setSavepoint();
	 */
	@Test
	public void run2(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// 获取链接
			conn = MyJdbcUtil.getConnection();
			// 开启事物 -- 默认不提交
			conn.setAutoCommit(false);
			// 编写SQL语句
			String sql = "update t_account set money = money + ? where username = ? ";
			// 预编译SQL
			stmt = conn.prepareStatement(sql);
			// 设置参数（给聪聪扣除1000元）
			stmt.setDouble(1, -10000);
			stmt.setString(2, "聪聪");
			// 执行
			stmt.executeUpdate();
			
			// 给美美加1000元
			stmt.setDouble(1, 10000);
			stmt.setString(2, "美美");
			// 执行
			stmt.executeUpdate();
			
			
			// 设置保存点
			Savepoint sp = conn.setSavepoint();
			
			
			// 其他的操作
			// 设置参数（给美美扣除100000元）
			stmt.setDouble(1, -100000);
			stmt.setString(2, "美美");
			// 执行
			stmt.executeUpdate();
			
			// 设置参数（给聪聪增加100000元）
			stmt.setDouble(1, 100000);
			stmt.setString(2, "聪聪");
			// 执行
			stmt.executeUpdate();
			
			// 如果查询，发送账户中没有那么多的钱，回滚到保存点的位置
			String sql2 = "select * from t_account where username = ?";
			stmt = conn.prepareStatement(sql2);
			stmt.setString(1, "美美");
			rs = stmt.executeQuery();
			if(rs.next()){
				// 获取美美的钱，如果发现<0
				double money = rs.getDouble("money");
				if(money < 0){
					// 回滚到保存点的位置
					conn.rollback(sp);
					// 回到保存点的位置  聪聪钱是0  美美2万
				}
			}
			
			// 提交
			conn.commit();
			
		} catch (Exception e) {
			// 回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MyJdbcUtil.release(stmt, conn);
		}
	}
	
	/**
	 * 模拟转账的过程
	 * 模拟异常
	 */
	@Test
	public void run1(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 获取链接
			conn = MyJdbcUtil.getConnection();
			
			// 开启事物 -- 默认不提交
			conn.setAutoCommit(false);
			
			// 编写SQL语句
			String sql = "update t_account set money = money + ? where username = ? ";
			// 预编译SQL
			stmt = conn.prepareStatement(sql);
			// 设置参数（给聪聪扣除1000元）
			stmt.setDouble(1, -1000);
			stmt.setString(2, "聪聪");
			// 执行
			stmt.executeUpdate();
			
			// 加点异常走异常回滚操作（如果没有异常正常提交）
//			int a = 10 / 0;
			
			// 给美美加1000元
			stmt.setDouble(1, 1000);
			stmt.setString(2, "美美");
			// 执行
			stmt.executeUpdate();
			
			// 提交
			conn.commit();
			
		} catch (Exception e) {
			// 回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MyJdbcUtil.release(stmt, conn);
		}
	}

}












