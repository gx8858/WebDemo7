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
 * DBCP���ӳ�
 */
public class DbcpDemo1 {
	
	/**
	 * ����
	 */
	@Test
	public void run2(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// ProPeties����
			Properties pro = new Properties();
			pro.load(DbcpDemo1.class.getClassLoader().getResourceAsStream("dbcp.properties"));
			
			// BasicDataSourceFactory��
			DataSource dataSource = BasicDataSourceFactory.createDataSource(pro);
			// ��ȡ����
			conn = dataSource.getConnection();
			// ��дSQL
			String sql = "update t_account set money = 25000 where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 4);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			// �ĳ��˹黹�ķ���
			MyJdbcUtil.release(stmt, conn);
		}
	}
	
	
	/**
	 * ����
	 */
	@Test
	public void run(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// ��DBCP���ӳ��л�ȡ����
			BasicDataSource dataSource = new BasicDataSource();
			// �����û������������ӣ�4�������
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			// ��������
			dataSource.setUrl("jdbc:mysql:///day18?useUnicode=true&characterEncoding=UTF-8");
			// �����û���
			dataSource.setUsername("root");
			dataSource.setPassword("123456");
			// ��ȡ����
			conn = dataSource.getConnection();
			
			// ��дSQL
			String sql = "update t_account set money = 15000 where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 4);
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
