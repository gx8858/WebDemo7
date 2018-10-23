package cn.itcast.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import cn.itcast.utils.MyDataSource;
import cn.itcast.utils.MyJdbcUtil;

/**
 * �Զ��������
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
			// �Ȼ�ȡ���ӳ�
			dataSource = new MyDataSource();
			// ��ȡ����
			conn = dataSource.getConnection();
			
			// ��дSQL
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
			
			// ԭ��������
			MyJdbcUtil.release(stmt, conn);
			
			// �黹conn������
			// dataSource.addBack(conn);
		}
		
	}

}





