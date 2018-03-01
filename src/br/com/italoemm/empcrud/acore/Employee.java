package br.com.italoemm.empcrud.acore;
/**
 * @author ${github/italoemm}
 *
 * 
 */
	public class Employee { 
		private int id;
		private String name;
		private String lastName;
		private String email;
		private int idManager ;
		private byte[] dataPic;
	
		
		
		//update delete Employee
		public Employee(int id, String name, String lastName, String email,byte[] dataPic, int idManager) {
			this.id = id;
			this.name = name;
			this.lastName = lastName;
			this.email = email;
			this.idManager=idManager;
			this.dataPic = dataPic;
				}
		
		// its going be used to insert a normal employee
		public Employee(String name, String lastName, String email,byte[] dataPic, int idManager) {
			this.name = name;
			this.lastName = lastName;
			this.email = email;
			this.idManager=idManager;
			this.dataPic = dataPic;
		}
		
		// its going be used to insert employee Manager
		public Employee( String name, String lastName, String email) {
			this.name = name;
			this.lastName = lastName;
			this.email = email;
			this.idManager=0;
				}
	
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public int getIdManager() {
			return idManager;
		}

		public void setIdManager(int idManager) {
			this.idManager = idManager;
		}

		@Override
		public String toString() {
			return "Employee [id=" + id + ", name=" + name + ", lastName=" + lastName + ", email=" + email + ", idManager="
					+ idManager + "] \n";
		}

		
		public byte[] getDataPic() {
			return dataPic;
		}

		
		public void setDataPic(byte[] dataPic) {
			this.dataPic = dataPic;
		}
		


}
