package br.com.italoemm.empcrud.acore;
/**
 * @author ${github/italoemm}
 *
 * 
 */
public class LoginManager {
	
 private int id;
 private String login;
 private int password;
 private int idEmployee;
 
 // add login
public LoginManager(int id, String login, int password, int idEmployee) {
	super();
	this.id = id;
	this.login = login;
	this.password = password;
	this.idEmployee=idEmployee;
}


public LoginManager() {

}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public int getPassword() {
	return password;
}
public void setPassword(int password) {
	this.password = password;
}
public int getIdEmployee() {
	return idEmployee;
}
public void setIdEmployee(int idEmployee) {
	this.idEmployee = idEmployee;
}

@Override
public String toString() {
	return "Login [id=" + id + ", login=" + login + ", password=" + password + ", idEmployee=" + idEmployee + "]";
}

}
