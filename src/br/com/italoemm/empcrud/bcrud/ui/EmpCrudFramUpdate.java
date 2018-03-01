package br.com.italoemm.empcrud.bcrud.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.italoemm.empcrud.acore.Employee;
import br.com.italoemm.empcrud.adao.EmployeeDAO;
import br.com.italoemm.empcrud.adao.HistoryDAO;
import br.com.italoemm.empcrud.adao.LoginDAO;
import br.com.italoemm.empcrud.blogin.ui.LoginFramPrincipal;
import br.com.italoemm.empcrud.cfunctionally.*;

import javax.swing.ImageIcon;
public class EmpCrudFramUpdate extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private JTextField idTextField;
	private JTextField lastNameTextField;
	private JTextField nameTextField;
	private JTextField emailTextField;
	private JFrame frame = null;
	private JFrame frmUpdate;
	private JButton btnCancel=null;
	private JTable table;
	private JTextArea textArea;
	private JLabel photolbl;
	private JButton choosePhotobttn;
	
	private EmployeeDAO empDAO;
	private Employee emp; 
	private HistoryDAO histDAO;
	private byte [] dataPic = null;
	private LoginDAO daoLogin;
	private EmployeeFunctionally empFunc;
	
	/**
	 * @author ${github/italoemm}
	 *
	 * 
	 */
	public EmpCrudFramUpdate() throws Exception {
		/*I'm going get the same table that was in class EmpCrudFramPrincipal, that's why 
		 I've make it static
		 */
		this.table = EmpCrudFramPrincipal.getTable();
		this.histDAO = new HistoryDAO();
		this.empDAO= new EmployeeDAO();
		this.daoLogin = new LoginDAO();
		/* get the Manager*/
		this.emp = LoginFramPrincipal.getEmp();
		this.empFunc = new EmployeeFunctionally();
		
		frmUpdate = new JFrame();
		frmUpdate.setVisible(true);		
		frmUpdate.setResizable(false);
		frmUpdate.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		frmUpdate.setTitle("Update");	
		frmUpdate.setBounds(100, 100, 596, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmUpdate.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(198, 37, 14, 14);
		contentPane.add(lblId);
		
		idTextField = new JTextField();
		idTextField.setEditable(false);
		idTextField.setBounds(222, 34, 29, 20);
		contentPane.add(idTextField);
		idTextField.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(272, 37, 76, 14);
		contentPane.add(lblLastName);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(347, 34, 228, 20);
		contentPane.add(lastNameTextField);
		lastNameTextField.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(198, 65, 42, 14);
		contentPane.add(lblName);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(250, 62, 135, 20);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(401, 65, 50, 14);
		contentPane.add(lblEmail);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(448, 62, 128, 20);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			// increase JFrame size after click on button
			public void actionPerformed(ActionEvent e) {
				frmUpdate.setBounds(100, 100, 820, 173);
				
			}
		});
		btnUpdate.setBounds(272, 104, 89, 23);
		contentPane.add(btnUpdate);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close window
				frmUpdate.dispose();
				
			}
		});
		btnCancel.setBounds(401, 104, 89, 23);
		contentPane.add(btnCancel);
		
		/*I set the field with the values of employee that I selected from table
		 */
		idTextField.setText(String.valueOf((int) table.getModel().getValueAt(table.getSelectedRow(), 0)));
		nameTextField.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 2));
		lastNameTextField.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 1));
		emailTextField.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 3));
		
		textArea = new JTextArea();
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				/* after clicked on field , its going be empty and words are going be black*/
				textArea.setText(null);
				textArea.setForeground(Color.BLACK);
			}
		});
		/* initialize the words in gray color*/
		textArea.setForeground(Color.GRAY);
		textArea.setText("Why Are doing it?...");
		/*set the size and position of textArea */
		textArea.setBounds(607, 34, 197, 52);
		/* put border*/
		textArea.setBorder(new MatteBorder(1, 1, 1,1, Color.gray));
		contentPane.add(textArea);
		
		JLabel lblReason = new JLabel("Reason");
		lblReason.setBounds(607, 9, 46, 14);
		contentPane.add(lblReason);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					/* go back to size of JFrame initial  and call method*/
					frmUpdate.setBounds(100, 100, 595, 173);
					updateEmployeeframe();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnConfirm.setBounds(657, 97, 89, 23);
		contentPane.add(btnConfirm);
		
		photolbl = new JLabel("");
		
		dataPic =  (byte[]) table.getModel().getValueAt(table.getSelectedRow(), 4);
		ImageIcon img =  new ImageIcon(dataPic);
		photolbl.setIcon(new ImageIcon(img.getImage().getScaledInstance(114, 114, Image.SCALE_SMOOTH)));
		photolbl.setBounds(28, 13, 114, 114);
		
		photolbl.setBorder(new MatteBorder(1, 1, 1,1, java.awt.Color.BLACK));
		contentPane.add(photolbl);
		
		choosePhotobttn = new JButton("...");
		choosePhotobttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					/* I'm going get a flow of data that represent a image*/
					dataPic = new EmployeeFunctionally().getDataPic();
					/* set a ImageIcon with that data*/
					ImageIcon pic = new ImageIcon(dataPic);
					/*I'm going get that same image but I'll resize its size for the label's size*/
                    photolbl.setIcon( new ImageIcon(pic.getImage().getScaledInstance(114, 114, Image.SCALE_SMOOTH)));
				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		choosePhotobttn.setBounds(142, 9, 20, 23);
		contentPane.add(choosePhotobttn);
		
	}
	
    public void updateEmployeeframe() throws Exception{
 
    	 int id = Integer.parseInt(idTextField.getText());
         String name = nameTextField.getText();
       	 String lastName = lastNameTextField.getText();
    	 String email= emailTextField.getText();
  
    	 /* I will check if the email is valid*/
    	 boolean isvalid = daoLogin.isValidEmailId((email));
    	 
    	 
        if(textArea.getText().isEmpty() || !isvalid){
        	JOptionPane.showMessageDialog(null, "Please Fill the fields Correctly", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
        	
    	 int op = JOptionPane.showConfirmDialog(null, "Are you sure", "Confirm",JOptionPane.YES_NO_OPTION);
 		 if(op == JOptionPane.YES_OPTION ){
 		 Employee temp = new Employee(id, name, lastName, email,dataPic, emp.getId());
         /* I'm going insert into DB the Manager that make a update and reason why he did it*/
		 histDAO.insertHistory(temp, "Updated",textArea.getText());
 
		 /* make a update*/
 		 empDAO.updateEmployee(temp);
		
		 empFunc.checkAndRefreshTable();
 		 }
 		
 		} 
 
    }
   
}	