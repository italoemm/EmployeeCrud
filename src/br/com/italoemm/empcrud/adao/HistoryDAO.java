package br.com.italoemm.empcrud.adao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.italoemm.empcrud.acore.Employee;
import br.com.italoemm.empcrud.acore.History;
import br.com.italoemm.empcrud.bcrud.ui.EmpCrudFramPrincipal;
import br.com.italoemm.empcrud.blogin.ui.LoginFramPrincipal;
import br.com.italoemm.empcrud.cfunctionally.patterns.singleton.SingletonConnection;
import br.com.italoemm.empcrud.cfunctionally.patterns.strategy.Context;
import br.com.italoemm.empcrud.cfunctionally.patterns.strategy.GenerateIdHistory;
/**
 * @author ${github/italoemm}
 *
 * 
 */
public class HistoryDAO {
	   private  SingletonConnection classCon;
	   private Connection con;
	   private History hist;
	   private Employee empManager;
	   
	public HistoryDAO() throws SQLException{
		// get connection
		   classCon = SingletonConnection.getInstance();
		   con=classCon.getConec();
		// get Manager
		   empManager = LoginFramPrincipal.getEmp();
	}
	
	public List<History> searchHistory (Employee empManager) throws Exception{
		
    	List<History> list = new ArrayList<History>();
    	PreparedStatement mstmt = null;
    	ResultSet rs = null;
        String sql; 
        
    	try{
    	
    		sql = "select * from history where idManager=? order by id DESC ";
    		
    		mstmt = con.prepareStatement(sql);
    		mstmt.setInt(1, empManager.getId());
    		
    		rs = mstmt.executeQuery();
    		
    		while(rs.next()){
    	
    		 int id = rs.getInt("id");
    		 String empName = rs.getString("empName");
    		 String description = rs.getString("description");
    		 String reason = rs.getString("reason");
    		 String date = rs.getString("date");
    		 int idManager = rs.getInt("idManager");
    		 
    		 hist  = new History(id, empName, description,date, reason, idManager);
    		 list.add(hist);
    	
    		}
    	
    	} catch (Exception e) {
			 System.out.println(e);
			 e.printStackTrace();
		}finally{
			classCon.close(mstmt,rs);
		}
    	
    	return list;
    }
	
	public void insertHistory(Employee empNormal, String description, String reason)throws Exception{
	    
 	   PreparedStatement mstmt = null;
 	   ResultSet rs = null;
 	   String sql;
       String date = EmpCrudFramPrincipal.getDateString();
       String NameMoreLastName = empNormal.getName() + " "+ empNormal.getLastName();
       
	       try{		
	    	   sql= "insert into history values(?,?,?,?,?,?)";   	    	   
	    	   mstmt=con.prepareStatement(sql);
	    	   
	    	   mstmt.setInt(1, getId());
	    	   mstmt.setString(2, NameMoreLastName);
	    	   mstmt.setString(3, description);
	    	   mstmt.setString(4, reason);
	    	   mstmt.setString(5, date);
	    	   mstmt.setInt(6, empManager.getId());
	    	   
	    	   mstmt.executeUpdate();
	    	  
 	System.out.println("history insert succefull ");
	       } catch (SQLException d) {
		System.out.println(d + "erro de update" );
		}
	       finally{
	    	  classCon.close(mstmt,rs);
	       }

 }
	// generate ID
	public int getId() throws Exception{
	
		return new Context(new GenerateIdHistory()).executeStrategy();
	}
	
	 
   
}
