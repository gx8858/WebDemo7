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
 * �Զ�������ݿ����ӳ�
 * @author Administrator
 *
 */
public class MyDataSource implements DataSource{
	
	// ����һ��List
	public List<Connection> connlist = new ArrayList<Connection>();
	
	// ���췽��
	public MyDataSource(){
		// ��̬�����������ӣ����뵽connlist��
		for (int i = 0; i < 5; i++) {
			// ����һ������
			Connection conn = MyJdbcUtil.getConnection();
			// ���뼯����
			connlist.add(conn);
		}
	}
	
	/**
	 * �������Զ��������ӳ��л�ȡ���ӵķ���
	 */
	public Connection getConnection() throws SQLException {
		// �Ӽ����л�ȡ���ӣ����ĸ������أ�
		// �Ƴ��б���ָ��λ�õ�Ԫ�أ���ѡ�������������еĺ���Ԫ�������ƶ������������� 1�������ش��б����Ƴ���Ԫ�ء� 
		
		// ��MySQL��ʵ����
		Connection conn = connlist.remove(0);
		
		// �����Լ���ǿ����
		ConnectionWrapper wrapper = new ConnectionWrapper(conn, connlist);
		
		return wrapper;
	}
	
	
	// ����黹���ӵķ���
	/**
	 * �÷����� ���Զ���ķ����������ɽӿ��ṩ�ġ�
	 * @param conn
	 */
	public void addBack(Connection conn){
		// ��ӵ�������
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
