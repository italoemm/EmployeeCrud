package br.com.italoemm.empcrud.blogin.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.italoemm.empcrud.acore.Employee;
import br.com.italoemm.empcrud.acore.LoginManager;
import br.com.italoemm.empcrud.adao.EmployeeDAO;
import br.com.italoemm.empcrud.adao.LoginDAO;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InterfaceAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFramInsert extends JFrame  {
	/**
	 * @author ${github/italoemm}
	 *
	 * 
	 */
	private static JPanel contentPane;
	private JTextField idTextField;
	private JFrame frame = null;
	private JLabel lblLogin;
	private JTextField loginTextField;
	private JTextField passTextField;
	private JTextField nameTextField;
	private JTextField lastNameTextField;
	
	private LoginDAO daoLogin;
	private EmployeeDAO    daoEmp;
	LoginManager l;
	Employee emp;
	
	
	public LoginFramInsert() throws Exception {
		daoLogin = new LoginDAO();
		daoEmp = new EmployeeDAO();
		frame = new JFrame();
		frame.setVisible(true);		
		frame.setResizable(false);
		frame.setTitle("Login Insert");
		
		frame.setBounds(100, 100, 439, 259);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(10, 43, 14, 14);
		contentPane.add(lblId);
		
		idTextField = new JTextField();
		idTextField.setText(String.valueOf(daoLogin.getId()));
		idTextField.setEditable(false);
		idTextField.setBounds(32, 40, 29, 20);
		contentPane.add(idTextField);
		idTextField.setColumns(10);
		
		lblLogin = new JLabel("Login:");
		lblLogin.setBounds(81, 43, 46, 14);
		contentPane.add(lblLogin);
		
		loginTextField = new JTextField();
		loginTextField.addKeyListener(new KeyAdapter() {
			@Override
			/* I'm going check if loginText is equals "example", make it null and change the color 
			  to black, when user start to type*/
		
			public void keyPressed(KeyEvent e) {
				if(loginTextField.getText().equals("example@example.com"))
					loginTextField.setText(null);	
				    loginTextField.setForeground(Color.BLACK);
			}
		});
		/* initialize the field with in gray color and word example@example.com*/
		loginTextField.setForeground(Color.GRAY);
		loginTextField.setText("example@example.com");
		loginTextField.setBounds(151, 40, 203, 20);
		contentPane.add(loginTextField);
		loginTextField.setColumns(10);
		
		JLabel lblPassoword = new JLabel("Password: ");
		lblPassoword.setBounds(81, 74, 71, 14);
		contentPane.add(lblPassoword);
		
		passTextField = new JTextField();
		passTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(passTextField.getText().equals("12345678"))
					passTextField.setText(null);	
				passTextField.setForeground(Color.BLACK);
			}
		});
		passTextField.setForeground(Color.GRAY);
		passTextField.setText("12345678");
		passTextField.setBounds(151, 71, 203, 20);
		contentPane.add(passTextField);
		passTextField.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					registerManager();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		
		});
		btnRegister.setBounds(89, 179, 89, 23);
		contentPane.add(btnRegister);
		
		JButton btnCancel_1 = new JButton("Cancel");
		btnCancel_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
			}
		});
		btnCancel_1.setBounds(229, 179, 89, 23);
		contentPane.add(btnCancel_1);
		
		JLabel lblName = new JLabel("name:");
		lblName.setBounds(81, 102, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(81, 127, 60, 14);
		contentPane.add(lblLastName);
		
		nameTextField = new JTextField();
		nameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(nameTextField.getText().equals("Name"))
					nameTextField.setText(null);	
				     nameTextField.setForeground(Color.BLACK);
			}
		});
		nameTextField.setForeground(Color.GRAY);
		nameTextField.setText("Name");
		nameTextField.setBounds(151, 99, 203, 20);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		lastNameTextField = new JTextField();
		lastNameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(lastNameTextField.getText().equals("Last Name"))
					lastNameTextField.setText(null);	
				    lastNameTextField.setForeground(Color.BLACK);
			}
		});
		lastNameTextField.setForeground(Color.GRAY);
		lastNameTextField.setText("Last Name");
		lastNameTextField.setBounds(150, 124, 204, 20);
		contentPane.add(lastNameTextField);
		lastNameTextField.setColumns(10);
		// I put on here cause I want to show its ID whenever I want insert employee before click on button
		//idTextField.setText(String.valueOf(loginDAO.generateId()));
		
		} // final constructor
	
	
	public void registerManager() throws Exception {
		

		
		int idLogin ;
   	    String loginEmail ;
      	int password ;
		boolean isvalid;
		String name;
		String lastName;
		
		//check if the fields are empty
		 if((!loginTextField.getText().isEmpty() || ! passTextField.getText().isEmpty() 
				 || !nameTextField.getText().isEmpty() || !lastNameTextField.getText().isEmpty())){	
			 
			 idLogin = Integer.parseInt(idTextField.getText());
			 loginEmail = loginTextField.getText();
			 password = Integer.parseInt(passTextField.getText());
			 
			 name = nameTextField.getText();
			 lastName = lastNameTextField.getText();
			 isvalid = daoLogin.isValidEmailId((loginEmail));
			 
			 // check of if email is valid
			 if(!isvalid){
	        	 JOptionPane.showMessageDialog(null, "Email Invalid","Error",JOptionPane.ERROR_MESSAGE);
	         } else{
	
	        	emp = new Employee(name, lastName, loginEmail);
	         	
	            //insert a new login with the id of Manager created in DB
	        	l = new LoginManager(idLogin, loginEmail, password,daoEmp.getId());
	        	// insert the new Manager in DB
	            daoEmp.insertEmployee(emp,true);
	            
	            daoLogin.insertLoginManager(l);
	
	            frame.dispose();
	         }
		 }else {
			 JOptionPane.showMessageDialog(null,"Please fill the fields \n                Thanks","Confirm",JOptionPane.ERROR_MESSAGE);
         }
	}
}//final class;

