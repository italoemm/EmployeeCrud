package br.com.italoemm.empcrud.bcrud.ui;
/**
 * @author ${github/italoemm}
 *
 * 
 */
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import br.com.italoemm.empcrud.acore.Employee;
import br.com.italoemm.empcrud.adao.EmployeeDAO;
import br.com.italoemm.empcrud.adao.HistoryDAO;
import br.com.italoemm.empcrud.adao.LoginDAO;
import br.com.italoemm.empcrud.blogin.ui.LoginFramPrincipal;
import br.com.italoemm.empcrud.cfunctionally.*;public class EmpCrudFramInsert extends JFrame{

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private JTextField idTextField;
	private JTextField lastNameTextField;
	private JTextField nameTextField;
	private JTextField emailTextField;
	private JButton btnCancel=null;
	private JFrame frame = null;
	private JTextArea textArea;
	
	
	private HistoryDAO histDAO;
	private EmployeeDAO empDAO ;
	private Employee empManager ;
	private EmployeeFunctionally empFunc;
	private LoginDAO daoLogin;
	private byte[] dataPic=null;
	
	public EmpCrudFramInsert() throws Exception {
        empDAO= new EmployeeDAO();
        // I'm going get the Manager that make sign into program.
		empManager = LoginFramPrincipal.getEmp();
		histDAO = new HistoryDAO();
		empFunc = new EmployeeFunctionally();
		daoLogin = new LoginDAO();
		
		frame = new JFrame();
		frame.setVisible(true);		
		frame.setResizable(false);
		frame.setTitle("Insert");
		
		frame.setBounds(100, 100, 612, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(173, 38, 14, 14);
		contentPane.add(lblId);
		
		idTextField = new JTextField();
		idTextField.setEditable(false);
		idTextField.setBounds(226, 35, 29, 20);
		contentPane.add(idTextField);
		idTextField.setColumns(10);
		// I put on here cause I want to show its ID whenever I want insert employee before click on button
		idTextField.setText(String.valueOf(empDAO.getId()));
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(274, 38, 76, 14);
		contentPane.add(lblLastName);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(360, 35, 228, 20);
		contentPane.add(lastNameTextField);
		lastNameTextField.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(173, 66, 42, 14);
		contentPane.add(lblName);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(226, 63, 135, 20);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(400, 66, 50, 14);
		contentPane.add(lblEmail);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(460, 63, 128, 20);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		
		JLabel lblReason = new JLabel("Reason");
		lblReason.setBounds(626, 11, 46, 14);
		contentPane.add(lblReason);
		
		// when I click in field its going to be empty and words are going be black
		textArea = new JTextArea();
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textArea.setText(null);
				textArea.setForeground(Color.BLACK);
			}
		});
		/* I initialze the program with the words inside of text area in a gray color*/
		textArea.setForeground(Color.GRAY);
		textArea.setText("Why Are doing it?...");
		textArea.setBounds(626, 34, 197, 52);
		textArea.setBorder(new MatteBorder(1, 1, 1,1, Color.gray));
		textArea.setOpaque(true);
		contentPane.add(textArea);
		/* whenever I click in button insert it's going to open the frame*/
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frame.setBounds(100, 100, 839, 173);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		btnInsert.setBounds(263, 105, 89, 23);
		contentPane.add(btnInsert);
		
		/*close fram when I click Cancel*/
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(417, 105, 89, 23);
		contentPane.add(btnCancel);
		
		
		
		
		/* whenever I click in button Confirm it's going go back the size original and a methods insert*/
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frame.setBounds(100, 100, 612, 173);
					insertAction();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnConfirm.setBounds(685, 105, 89, 23);
		contentPane.add(btnConfirm);
		
		/* create a JLabel , set border for it , get a image from computer with its Address, after
		 * I'm going get that same image but I'll resize its size for the label's size
		 */
		JLabel photolbl = new JLabel("");
		photolbl.setBorder(new MatteBorder(1, 1, 1,1, java.awt.Color.BLACK));
		ImageIcon img =  new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\employee Pic\\nophoto2.gif");
		photolbl.setIcon(new ImageIcon(img.getImage().getScaledInstance(114, 114, Image.SCALE_SMOOTH)));
		photolbl.setBounds(27, 13, 115, 115);
		contentPane.add(photolbl);
		
		JButton choosePhotobttn = new JButton("...");
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
		choosePhotobttn.setBounds(142, 11, 22, 23);
		contentPane.add(choosePhotobttn);
	
	}
	

	public void insertAction() throws Exception {
		 Employee temp=null;
		 
		//int id = Integer.parseInt(idTextField.getText());
		String lastName = lastNameTextField.getText();
		String name = nameTextField.getText();
		String email = emailTextField.getText();
		String reason = textArea.getText();
		boolean isvalid = daoLogin.isValidEmailId((email));
		
		/*check if the fields are empty*/
		if(lastName.isEmpty() || name.isEmpty() || email.isEmpty() || reason.isEmpty() || !isvalid ){
			 JOptionPane.showMessageDialog(null,"Please Fill the fields Correctly \n                Thanks","Confirm",JOptionPane.ERROR_MESSAGE);
		}else{
			/*otherwise I'll ask for user if its what he really want*/
			int i = JOptionPane.showConfirmDialog(null, "Are you sure?","Confirm", JOptionPane.YES_OPTION);
			if (i == JOptionPane.YES_OPTION){ 
				
				/* if "yes" I'll assign to a temp employee all variables and id of Manager */
				temp = new Employee(name, lastName, email,dataPic,empManager.getId());
				/* I'll to insert into DB employee and what the Manager are doing */
								 histDAO.insertHistory(temp, "Inserted",reason);
								 empDAO.insertEmployee(temp,false);
			
							     empFunc.checkAndRefreshTable();;            				
 	
                 
			}
			/* And I'm going make the fields empty after insert */
			 idTextField.setText(String.valueOf(empDAO.getId()));
		     lastNameTextField.setText(null);
			 nameTextField.setText(null);
			 emailTextField.setText(null);
			
		}
		
	}
	
	
}//final class


