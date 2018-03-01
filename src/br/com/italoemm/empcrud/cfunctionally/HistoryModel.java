package br.com.italoemm.empcrud.cfunctionally;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.italoemm.empcrud.acore.Employee;
import br.com.italoemm.empcrud.acore.History;

public class HistoryModel extends AbstractTableModel {
	
	/**
	 * @author ${github/italoemm}
	 *
	 * 
	 */
    private static final int ID = 0;
    private static final int EMPNAME = 1;
	private static final int DESCRICAO = 2;
	private static final int DATE = 3;
	private static final int REASON = 4;
	
	 String [] columns = {"Id","Employee name","Description","Date"};
	 private List<History> hisList;
	   	
    public  HistoryModel (List<History> hisList){
    	this.hisList = hisList;
    }
    
    public  HistoryModel (){
    	this.hisList = new ArrayList<History>();
    }
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return hisList.size();
	}

	public String getColumnName(int col) {
		return columns[col];
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
    
		History hist = hisList.get(rowIndex);
		
		switch (columnIndex) {
		case ID:
			return hist.getId();
		case EMPNAME:
			return hist.getEmpName();
		case DESCRICAO:
			return hist.getDescription();
		case DATE:
			return hist.getDate();
		case REASON:
			return hist.getReason();
			default:
			return "error in moment of get columns";
		}
	}
	

}
