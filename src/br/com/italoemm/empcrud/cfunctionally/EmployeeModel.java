package br.com.italoemm.empcrud.cfunctionally;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.italoemm.empcrud.acore.Employee;

public class EmployeeModel extends AbstractTableModel {
	
	/**
	 * @author ${github/italoemm}
	 *
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int ID = 0;
	private static final int LAST_NAME_COL = 1;
	private static final int FIRST_NAME_COL = 2;
	private static final int EMAIL_COL = 3;
	private static final int DATAPIC = 4;
	private static final int IDEMP_COL = 5;
	
	/* name of columns*/
    private String [] columns = {"Id","Last Name","Name","Email"};
	
    private List <Employee> tempList;
    
 
    public EmployeeModel(){
    	tempList = new ArrayList<Employee>();
    }
 
    /* get any list*/
	public EmployeeModel(List<Employee> temp){
    	this.tempList=temp;
    }
    
	public int getRowCount() {
		return tempList.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public String getColumnName(int col) {
		return columns[col];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		/*I'll get the employee from the list*/
		Employee tempEmployee = tempList.get(rowIndex);
		
		/*I'll retur the variables from employee*/
		switch (columnIndex) {
		case ID:
			return tempEmployee.getId();
		case LAST_NAME_COL:
			return tempEmployee.getLastName();
		case FIRST_NAME_COL:
			return tempEmployee.getName();
		case EMAIL_COL:
			return tempEmployee.getEmail();
		case DATAPIC:
			   return tempEmployee.getDataPic();
		case IDEMP_COL:
		   return tempEmployee.getIdManager();
		default:
			return "error in moment of get columns";
		}
	}
	
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	   
	   public void setTempList(List<Employee> tempList) {
			this.tempList = tempList;
		}
}
