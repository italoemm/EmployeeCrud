package br.com.italoemm.empcrud.cfunctionally;

import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.italoemm.empcrud.acore.Employee;
import br.com.italoemm.empcrud.adao.EmployeeDAO;
import br.com.italoemm.empcrud.bcrud.ui.EmpCrudFramPrincipal;
import br.com.italoemm.empcrud.cfunctionally.*;

public class EmployeeFunctionally {
	/**
	 * @author ${github/italoemm}
	 *
	 * 
	 */
	JTable table;
	/* whenever I instantiate this class I will set again a new Model for the same 
	 * table that is in EmpCrudFramPrincipal
	 */
	public  EmployeeFunctionally( ){

	}
	
    public void checkAndRefreshTable() throws Exception{
	 	/* get the table */
    	 this.table=EmpCrudFramPrincipal.getTable();
    	 
    	/* set a new model though a new list that return from DB*/
         table.setModel(new EmployeeModel(new EmployeeDAO().searchEmployee(null, false)));
    	}
    
    
    
    public byte[] getDataPic() throws Exception{
    	File f = null;
    	JFileChooser fc = null;
        FileFilter filter;
    	FileInputStream input = null;
    	byte[] data = null;
    	
    	Thread.sleep(1000);
    	/*use a default operation of API JAVA to get a file from computer, like whenever you wanna
    	 * open any file*/
    	fc =  new JFileChooser();
    	
    	/*create a filter*/
    	filter = new FileNameExtensionFilter("All Images File", "jpg","jpeg","png","gif","bmp");
    	
    	/* I'm going set this operation with that filter*/
    	fc.addChoosableFileFilter(filter);
    	/*and make that filter avaiable*/
    	fc.setFileFilter(filter);
    	
    	int result = fc.showOpenDialog(null);
    	
    	/* if result is....let me see...if user managed to get his image
    	 * I will set the FILE in image selected*/
    	if(result == fc.APPROVE_OPTION){
    		f = fc.getSelectedFile();
    	}else{
    		/* otherwise I'm going set default image "noimage"*/
    		f= new File("C:\\POO\\workspace\\EmployeeCrud\\employee Pic\\nophoto2.gif");
    	}
    	// set up a size for array of byte
    	data = new byte[(int)f.length()];
    	
    	// open a connection with a file as open device
    	input = new FileInputStream(f);
    	// read in a memory and store into data
    	input.read(data);
    	
    	input.close();
    	return data;
    }
    
    public Employee convertInEmployee(ResultSet rs, JTable table) throws Exception{
    	Employee emp = null;
    	if(rs==null){
    		// get from Table
    		/* I'm going get all values of the row selected*/
    		int id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
    		String name = (String) table.getModel().getValueAt(table.getSelectedRow(), 1);
    	    String lastName = (String) table.getModel().getValueAt(table.getSelectedRow(), 2);
    		String email = (String) table.getModel().getValueAt(table.getSelectedRow(), 3);
    		byte[] dataPic = (byte[]) table.getModel().getValueAt(table.getSelectedRow(), 4);
    		int idManager = (int) table.getModel().getValueAt(table.getSelectedRow(), 5)  ; 
    		/* or 
    		 * String email = modelEmp.getValueAt(table.getSelectedRow(), 3);
    		 */
    		/* ask to user with is what he really want*/
    		 emp = new Employee(id, name, lastName, email, dataPic, idManager);
    		 return emp;
    	}else{
    		// get from DB
   		 int id = rs.getInt("id");
   		 String name = rs.getString("first_name");
   		 String lastName = rs.getString("last_name");
   		 String email = rs.getString("email");
   		 byte [] dataPic = rs.getBytes("dataPic");
   		 int idManager = rs.getInt("idManager");
   		 emp = new Employee(id, name, lastName, email, dataPic, idManager);
   		 return emp; 

      	}
    }
	}

