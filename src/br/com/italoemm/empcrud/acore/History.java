package br.com.italoemm.empcrud.acore;
/**
 * @author ${github/italoemm}
 *
 * 
 */
public class History {
	private int id;
	private String empName;
	private String description;
	private String date;
	private String reason;
	private int idManager;


	public History(int id, String empName, String description, String date, String reason, int idManager) {
		super();
		this.id = id;
		this.empName = empName;
		this.description = description;
		this.date = date;
		this.reason = reason;
		this.idManager = idManager;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getEmpName() {
		return empName;
	}



	public void setEmpName(String empName) {
		this.empName = empName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getReason() {
		return reason;
	}



	public void setReason(String reason) {
		this.reason = reason;
	}



	public int getIdManager() {
		return idManager;
	}



	public void setIdManager(int idManager) {
		this.idManager = idManager;
	}



	@Override
	public String toString() {
		return "History [id=" + id + ", empName=" + empName + ", description=" + description + ", date=" + date
				+ ", idManager=" + idManager + " ]";
	}	
	
	
	
}