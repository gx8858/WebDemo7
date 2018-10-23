package cn.itcast.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.itcast.utils.MyJdbcUtil;

/**
 * C3P0���ӳؿ�������
 */
public class C3p0Demo1 {
	
	@Test
	public void run2(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// Ĭ��ȥ��ѯָ��·���£�src�����ļ���c3p0-config.xml��
			ComboPooledDataSource dataSource = new ComboPooledDataSource("myoracle");
			// ��ȡ����
			conn = dataSource.getConnection();
			// ��дSQL
			String sql = "update t_account set username = ? where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "����");
			stmt.setInt(2, 4);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			// �ĳ��˹黹�ķ���
			MyJdbcUtil.release(stmt, conn);
		}
	}
	
	@Test
	public void run1(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			// ���ò���
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setJdbcUrl("jdbc:mysql:///day18?useUnicode=true&characterEncoding=UTF-8");
			dataSource.setUser("root");
			dataSource.setPassword("123456");
			
			// ��ȡ����
			conn = dataSource.getConnection();
			
			// ��дSQL
			String sql = "update t_account set username = ? where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "����");
			stmt.setInt(2, 4);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			// �ĳ��˹黹�ķ���
			MyJdbcUtil.release(stmt, conn);
		}
	}

}
