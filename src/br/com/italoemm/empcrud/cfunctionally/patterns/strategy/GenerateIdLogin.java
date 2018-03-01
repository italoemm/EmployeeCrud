package br.com.italoemm.empcrud.cfunctionally.patterns.strategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.italoemm.empcrud.cfunctionally.patterns.singleton.SingletonConnection;

public class GenerateIdLogin implements InterfaceGenerateId {
	
	/**
	 * @author ${github/italoemm}
	 *
	 * 
	 */
	   private  SingletonConnection classCon;
	   private  Connection con;

	@Override
	public int generateID() throws Exception {
		//get connection
	    classCon = SingletonConnection.getInstance();
		con=classCon.getConec();
		 
		PreparedStatement mstmt = null;
        ResultSet rs = null;
     
        
        try{
	        String sql = "select id from login where id  = (select max(id) from login)";
	    	mstmt=con.prepareStatement(sql);
		   
	    	rs=mstmt.executeQuery();
	    	
	    	if(rs.next()){
	    		//if already have record on DB, its going return the highest id plus 1 otherwise only 1
	    	 int id = rs.getInt("id");
	    	 return id+1;
	    	}else{
	    		return 1;
	    	}		    	
	 }finally{
    	classCon.close(mstmt,rs);
     }			    
	}

}
