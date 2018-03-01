package br.com.italoemm.empcrud.adao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import br.com.italoemm.empcrud.acore.Employee;
import br.com.italoemm.empcrud.acore.LoginManager;
import br.com.italoemm.empcrud.cfunctionally.EmployeeFunctionally;
import br.com.italoemm.empcrud.cfunctionally.patterns.singleton.SingletonConnection;
import br.com.italoemm.empcrud.cfunctionally.patterns.strategy.Context;
import br.com.italoemm.empcrud.cfunctionally.patterns.strategy.GenerateIdHistory;
import br.com.italoemm.empcrud.cfunctionally.patterns.strategy.GenerateIdLogin;
/**
 * @author ${github/italoemm}
 *
 * 
 */
public class LoginDAO {

	   private  SingletonConnection classCon;
	   private  Connection con;
	   private  Employee emp;
	   private  LoginManager l;
	   
	   public LoginDAO() throws SQLException{
		   /* get the connection*/
		   classCon = SingletonConnection.getInstance();
		   con=classCon.getConec();
		} // final constructor
	   
	   public void insertLoginManager(LoginManager login) throws Exception{
		   l = login;
		   PreparedStatement mstmt =null;
		   String sql = "insert into login values(?,?,?,?)";
		   
		  
		   try{
		   mstmt = con.prepareStatement(sql);
		   mstmt.setInt(1, getId());
		   mstmt.setString(2, l.getLogin());
		   mstmt.setInt(3, l.getPassword());
		   mstmt.setInt(4, l.getIdEmployee());
		   
		   mstmt.executeUpdate();
		   System.out.println("insert login succesfull");
	   } finally{
		   classCon.close(mstmt, null);
	   }
           
		   
	   } // final method insert

	   //check if login and password are correctly 
	   public Employee checkLoginManager (String userLoginManager,  int userPassword) throws Exception{
		   PreparedStatement mstmt = null;
		   ResultSet rs = null;
		   String sql;
		 
		   
		   try{
			   sql= "select employees.id as id, employees.first_name as first_name, employees.last_name as last_Name,employees.email as email, "
			   		  + " employees.idManager as idManager , employees.dataPic as dataPic"
					  +" from login inner join employees on login.idManager = employees.id "
					  +" where login.login = ? and login.pass = ?;";
					   			   	
			   
			   mstmt = con.prepareStatement(sql);
			   mstmt.setString(1, userLoginManager.trim());
			   mstmt.setInt(2, userPassword);
			   
			   rs = mstmt.executeQuery();
			   
			   if(rs.next()){
			           emp = new EmployeeFunctionally().convertInEmployee(rs,null);
				      
					   System.out.println("user founded: " + emp);
					   return emp;
			   }else{
				     System.out.println("user not founded");
				       return null;
			   }
			   
		   }finally {
			classCon.close(mstmt, null);
		}
		
	   }// final checkLoginManager
	
	   // generate ID
	   public int getId() throws Exception{
			return new Context(new GenerateIdLogin()).executeStrategy();

		
	   }// final generate ID
		   
	   //check if email have @email.com
		   public boolean isValidEmailId(String email) {
				if(!email.equals("example@example.com")){
		        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		        Pattern p = Pattern.compile(emailPattern);
		        Matcher m = p.matcher(email);
		        return m.matches();
				}else{
					return false;
				}
		    }
		   
} // final class
 