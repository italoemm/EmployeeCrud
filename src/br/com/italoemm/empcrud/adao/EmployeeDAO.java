package br.com.italoemm.empcrud.adao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import br.com.italoemm.empcrud.acore.Employee;
import br.com.italoemm.empcrud.blogin.ui.LoginFramPrincipal;
import br.com.italoemm.empcrud.cfunctionally.EmployeeFunctionally;
import br.com.italoemm.empcrud.cfunctionally.patterns.singleton.SingletonConnection;
import br.com.italoemm.empcrud.cfunctionally.patterns.strategy.Context;
import br.com.italoemm.empcrud.cfunctionally.patterns.strategy.GenerateIdEmployee;
/**
 * @author ${github/italoemm}
 *
 * 
 */
public class EmployeeDAO {
	
	   private  SingletonConnection classCon;
	   private  Connection con;
	   private  Employee empManager;
	   
	   public EmployeeDAO() throws SQLException{
		   // I'm going get the Manager that make sign into program.
			   empManager = LoginFramPrincipal.getEmp();
			   /* get a connection of DB*/
			   classCon = SingletonConnection.getInstance();
			   con=classCon.getConec();
		}
       	    
		//insert Normal and Manager Employee
	    public void insertEmployee(Employee e, boolean isManager)throws Exception{
	    
	    	   PreparedStatement mstmt = null;
	    	   ResultSet rs = null;
	    	   String sql;
	    	
	   	       try{		
	   	    	   if(!isManager){
	   	    	   sql= "insert into Employees values(?,?,?,?,?,?)";   	    	   
	   	    	   mstmt=con.prepareStatement(sql);
	   	    	   
	   	    	   mstmt.setInt(1, getId());
	   	    	   mstmt.setString(2, e.getName());
	   	    	   mstmt.setString(3, e.getLastName());
	   	    	   mstmt.setString(4, e.getEmail());
	   	    	   mstmt.setBytes(5, e.getDataPic());
	   	    	   mstmt.setInt(6, empManager.getId());
	   	    	   
	   	    	   mstmt.executeUpdate();
	   	    	   }else{
	   	    		  sql= "insert into Employees values(?,?,?,?,NULL,NULL)";   	    	   
		   	    	   mstmt=con.prepareStatement(sql);
		   	    	   
		   	    	   mstmt.setInt(1, getId());
		   	    	   mstmt.setString(2, e.getName());
		   	    	   mstmt.setString(3, e.getLastName());
		   	    	   mstmt.setString(4, e.getEmail());
		   	    	   
		   	    	   mstmt.executeUpdate(); 
	   	    	   }
	    	System.out.println("employee insert succefull ");
	   	       } catch (SQLException d) {
			System.out.println(d + "erro de update" );
			}
	   	       finally{
	   	    	  classCon.close(mstmt,rs);
	   	       }
	  
	    }
	    
	   	public void updateEmployee (Employee e ) throws Exception {

	   		 PreparedStatement mstmt = null;
	  	   
	         String sql = "update employees set last_name =? , first_name =? , email =? , dataPic = ? where id =? ";
	 	       try{
	 	    	   mstmt=con.prepareStatement(sql);
	 	    	  
	 	    	   mstmt.setString(1, e.getLastName());
	 	    	   mstmt.setString(2, e.getName());
	 	    	   mstmt.setString(3, e.getEmail());
	 	    	   mstmt.setBytes(4, e.getDataPic());
	 	    	   mstmt.setInt(5, e.getId());
	 	    	   
	 	    	   mstmt.executeUpdate();
	 	    	 // JOptionPane.showMessageDialog(null, "Are you sure ? ", "Option", JOptionPane.YES_OPTION);  
	  	System.out.println("sucessful updated");
	 	       } catch (SQLException d) {
			System.out.println(d + "erro de update" );
			}
	 	       finally{
	 	    	  classCon.close(mstmt,null);
	 	       }
	   	 } 
	 	    
	   	 public void deleteEmployee(Employee e ) throws SQLException {
	 	   		 
	   		 PreparedStatement mstmt = null;
	 	  	   
	 	         String sql = "delete from employees where id = ?";
	 	 	      
	 	         try{
	 	 	    	   mstmt=con.prepareStatement(sql);
	 	 	    	   
	 	 	    	   mstmt.setInt(1, e.getId());
	 	 	    	   
	 	 	    	   mstmt.executeUpdate();
	 	 	    	   
	 	  	           System.out.println("sucessful deleted");
	 	 
	 	 	       }finally{
	 	 	    	   try {
						classCon.close(mstmt, null);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	 	 	       }
	   	 }
	   	 
	   	 
	   	 public int getId() throws Exception{
	   		 return new Context(new GenerateIdEmployee()).executeStrategy();
	   	 }
	   	 
public List<Employee> searchEmployee(String fetch, boolean isManager) throws Exception{
	    	
	    	List<Employee> list = new ArrayList<Employee>();
	    	CallableStatement mstmt = null;
	    	ResultSet rs = null;
	        String sql; 
	
	    	try{
	    		if(fetch==null){
	    	if(!isManager){ 
	    		sql = "{call searchAllEmployeeOfManager(?)} ";
	    		
	    		mstmt = con.prepareCall(sql);
	    		mstmt.setInt(1, empManager.getId());
	    		mstmt.execute();	
	    		rs = mstmt.getResultSet();
	    		}else{
	    			sql = "{call searchAllManager()} ";
		    		mstmt = con.prepareCall(sql);
		    		mstmt.execute();	
		    		rs = mstmt.getResultSet();
		    		}
	    		}else{
	    			String search= fetch+'%';
	    			sql = "{call searchLastName(?)} ";
		    		mstmt = con.prepareCall(sql);
		    		mstmt.setString(1, search);
		    		mstmt.execute();	
		    		rs = mstmt.getResultSet();
	    		}
	    	
	    		while(rs.next()){
	 
	    		 Employee temp = new EmployeeFunctionally().convertInEmployee(rs,null);

	    		 list.add(temp);
	    		 	    		 
	    		}
	    	
	    	} catch (Exception e) {
				 System.out.println(e);
				 e.printStackTrace();
			}finally{
				classCon.close(mstmt,rs);
			}
	    	
	    	return list;
	    }

	}

		

