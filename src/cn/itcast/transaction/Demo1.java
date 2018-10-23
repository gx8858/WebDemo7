package cn.itcast.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil;

/**
 * ʹ��JDBC��������
 */
public class Demo1 {
	
	/**
	 * �������ñ����
	 * Savepoint sp = conn.setSavepoint();
	 */
	@Test
	public void run2(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// ��ȡ����
			conn = MyJdbcUtil.getConnection();
			// �������� -- Ĭ�ϲ��ύ
			conn.setAutoCommit(false);
			// ��дSQL���
			String sql = "update t_account set money = money + ? where username = ? ";
			// Ԥ����SQL
			stmt = conn.prepareStatement(sql);
			// ���ò��������ϴϿ۳�1000Ԫ��
			stmt.setDouble(1, -10000);
			stmt.setString(2, "�ϴ�");
			// ִ��
			stmt.executeUpdate();
			
			// ��������1000Ԫ
			stmt.setDouble(1, 10000);
			stmt.setString(2, "����");
			// ִ��
			stmt.executeUpdate();
			
			
			// ���ñ����
			Savepoint sp = conn.setSavepoint();
			
			
			// �����Ĳ���
			// ���ò������������۳�100000Ԫ��
			stmt.setDouble(1, -100000);
			stmt.setString(2, "����");
			// ִ��
			stmt.executeUpdate();
			
			// ���ò��������ϴ�����100000Ԫ��
			stmt.setDouble(1, 100000);
			stmt.setString(2, "�ϴ�");
			// ִ��
			stmt.executeUpdate();
			
			// �����ѯ�������˻���û����ô���Ǯ���ع���������λ��
			String sql2 = "select * from t_account where username = ?";
			stmt = conn.prepareStatement(sql2);
			stmt.setString(1, "����");
			rs = stmt.executeQuery();
			if(rs.next()){
				// ��ȡ������Ǯ���������<0
				double money = rs.getDouble("money");
				if(money < 0){
					// �ع���������λ��
					conn.rollback(sp);
					// �ص�������λ��  �ϴ�Ǯ��0  ����2��
				}
			}
			
			// �ύ
			conn.commit();
			
		} catch (Exception e) {
			// �ع�
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
	 * ģ��ת�˵Ĺ���
	 * ģ���쳣
	 */
	@Test
	public void run1(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// ��ȡ����
			conn = MyJdbcUtil.getConnection();
			
			// �������� -- Ĭ�ϲ��ύ
			conn.setAutoCommit(false);
			
			// ��дSQL���
			String sql = "update t_account set money = money + ? where username = ? ";
			// Ԥ����SQL
			stmt = conn.prepareStatement(sql);
			// ���ò��������ϴϿ۳�1000Ԫ��
			stmt.setDouble(1, -1000);
			stmt.setString(2, "�ϴ�");
			// ִ��
			stmt.executeUpdate();
			
			// �ӵ��쳣���쳣�ع����������û���쳣�����ύ��
//			int a = 10 / 0;
			
			// ��������1000Ԫ
			stmt.setDouble(1, 1000);
			stmt.setString(2, "����");
			// ִ��
			stmt.executeUpdate();
			
			// �ύ
			conn.commit();
			
		} catch (Exception e) {
			// �ع�
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












