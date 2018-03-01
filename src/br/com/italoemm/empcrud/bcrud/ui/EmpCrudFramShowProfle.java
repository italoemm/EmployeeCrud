package br.com.italoemm.empcrud.bcrud.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import br.com.italoemm.empcrud.acore.Employee;

import javax.swing.JLabel;
/**
 * @author ${github/italoemm}
 *
 * 
 */
public class EmpCrudFramShowProfle extends JFrame {

	private JPanel contentPane;
	private Employee emp;
	private JFrame frame;

/* this  class is really simple basically I'm going to get a employee and setText with his values*/ 
	public EmpCrudFramShowProfle(Employee emp) {
		this.emp=emp;
	
		frame =  new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Profile");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 438, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel photolbl = new JLabel("");
		photolbl.setBorder(new MatteBorder(1, 1, 1,1, java.awt.Color.BLACK));
		ImageIcon img =  new ImageIcon(emp.getDataPic());
		photolbl.setIcon(new ImageIcon(img.getImage().getScaledInstance(163, 163, Image.SCALE_SMOOTH)));
		photolbl.setBounds(10, 11, 163, 163);
		contentPane.add(photolbl);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(196, 49, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setBounds(196, 74, 79, 14);
		contentPane.add(lblDepartment);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(196, 22, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setBounds(196, 104, 46, 14);
		contentPane.add(lblSalary);
		
		JLabel lblFuntion = new JLabel("Funtion:");
		lblFuntion.setBounds(196, 129, 46, 14);
		contentPane.add(lblFuntion);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(196, 154, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel idEnter = new JLabel("");
		idEnter.setText(String.valueOf(emp.getId()));
		idEnter.setBounds(273, 22, 139, 14);
		contentPane.add(idEnter);
		
		JLabel nameEnter = new JLabel("");
		nameEnter.setText(emp.getName() +" "+ emp.getLastName());
		nameEnter.setBounds(273, 49, 139, 14);
		contentPane.add(nameEnter);
		
		JLabel departmenteEnter = new JLabel("Not Defined");
		departmenteEnter.setBounds(273, 74, 139, 14);
		contentPane.add(departmenteEnter);
		
		JLabel salaryEnter = new JLabel("Not Defined ");
		salaryEnter.setBounds(273, 104, 139, 14);
		contentPane.add(salaryEnter);
		
		JLabel funtionEnter = new JLabel("Not Defined");
		funtionEnter.setBounds(273, 129, 139, 14);
		contentPane.add(funtionEnter);
		
		JLabel emailEnter = new JLabel("");
		emailEnter.setText(emp.getEmail());
		emailEnter.setBounds(273, 154, 139, 14);
		contentPane.add(emailEnter);
	}

}
