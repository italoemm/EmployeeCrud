package br.com.italoemm.empcrud.cfunctionally.patterns.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SingletonConnection {
	/**
	 * @author ${github/italoemm}
	 *
	 * 
	 */
 /* I've created this class in intent to use only one connection, if connection
  * is open I keep the same connection otherwise if I close a connection a get another
  */
    private static SingletonConnection sgInstance;
    private static Connection conec;

	private SingletonConnection  () {	
	}
	
	public static SingletonConnection getInstance() throws SQLException{
		if (sgInstance == null){
		return new SingletonConnection();
		}else{
		return sgInstance;
	}
	}
	
	public void close(PreparedStatement mstmt, ResultSet rs)throws Exception {

		if (rs != null) {
			rs.close();
		}
		if(mstmt!=null){
			mstmt.close();
		}

	}
	
	public Connection getConec() throws SQLException {
		if(conec == null){
		String user = "root"; 
		String password = "";
		String url = "jdbc:mysql://localhost:3306/employeedatabase";
		
		conec = DriverManager.getConnection(url, user, password);
		System.out.println("DB connection successful to: " + conec);
		
		if (conec != null) {

	        System.out.println("STATUS--->Conectado com sucesso!");

	    } else {

	    	  System.out.println("STATUS--->Não foi possivel realizar conexão");
	    }
		return conec;
	}else{
		System.out.println("DB connection still conected in: " + conec);
		return conec;
	}
	}
}

