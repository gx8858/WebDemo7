package cn.itcast.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import cn.itcast.utils.MyJdbcUtil;

public class TomcatServlet extends HttpServlet {
	
	private static final long serialVersionUID = 4157957750971256229L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// �Ȼ�ȡ���ӳض���
			Context initCtx = new InitialContext(); 
			Context envCtx = (Context) initCtx.lookup("java:comp/env"); 
			// SUN���ӳض���(���β�ѯ)
			DataSource ds = (DataSource) envCtx.lookup("jdbc/EmployeeDB");
			// ��ȡ����
			// ��ȡ����
			conn = ds.getConnection();
			// ��дSQL
			String sql = "delete from t_account where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 4);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil.release(stmt, conn);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
