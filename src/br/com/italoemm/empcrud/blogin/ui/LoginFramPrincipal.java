package br.com.italoemm.empcrud.blogin.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.italoemm.empcrud.acore.Employee;
import br.com.italoemm.empcrud.acore.LoginManager;
import br.com.italoemm.empcrud.adao.LoginDAO;
import br.com.italoemm.empcrud.bcrud.ui.EmpCrudFramPrincipal;
import br.com.italoemm.empcrud.bhistory.ui.HistoryFramPrincipal;
import javafx.scene.control.Tooltip;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.ToolTipManager;

import java.awt.Label;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Color;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginFramPrincipal extends JFrame {
	/**
	 * @author ${github/italoemm}
	 *
	 * 
	 */
	private JPanel contentPane;
	private JTextField loginInTextField;
	private JTextField passInTextField;
    private LoginDAO daoLogin;
    private EmpCrudFramPrincipal empCrud;
    private StringSelection stringSelection ;
	private Clipboard clpbrd ; 
	private ToolTipManager tt;
	
	private static Employee emp = null;

	public  LoginFramPrincipal() throws Exception {
		daoLogin= new LoginDAO();
		/* set up a delay to message pop up on app
		 * ToolTipManager - it is a global configuration class, 
		 * changes made to the sharedInstance will be applied to every tooltip 
		 */
		tt.sharedInstance().setInitialDelay(50);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		loginInTextField = new JTextField();
		loginInTextField.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(loginInTextField.getText().equals("example@example.com")){
					loginInTextField.setText(null);	
					loginInTextField.setForeground(Color.BLACK);
				
				}
			}
		});
		loginInTextField.setForeground(Color.GRAY);
		loginInTextField.setText("example@example.com");
		loginInTextField.setBounds(139, 220, 255, 20);
		loginInTextField.setBorder(null);
		contentPane.add(loginInTextField);
		loginInTextField.setColumns(10);
		
		JLabel loginLb = new JLabel("");
		loginLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\blank field.png"));
		loginLb.setBounds(129, 217, 295, 26);
		contentPane.add(loginLb);
		
		passInTextField = new JTextField();

		passInTextField.addKeyListener(new KeyAdapter() {
			/* I'm going check if password is equals "12345678", make it null and change the color 
			  to black,  when user start to type*/
		
			@Override
			public void keyPressed(KeyEvent e) {
				if(passInTextField.getText().equals("12345678"))
				   passInTextField.setText(null);	
				   passInTextField.setForeground(Color.BLACK);
			}
		});
		/* initialize the field with in gray color and word 12345678*/
		passInTextField.setForeground(Color.GRAY);
		passInTextField.setText("12345678");
		passInTextField.setBackground(Color.WHITE);
		passInTextField.setBounds(139, 252, 255, 20);
		passInTextField.setBorder(null);
		contentPane.add(passInTextField);
		passInTextField.setColumns(10);
		
		JLabel passwordLb = new JLabel("");
		/* put a image on label from computer*/
		passwordLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\blank field - C\u00F3pia.png"));
		passwordLb.setBounds(129, 248, 295, 26);
		contentPane.add(passwordLb);
		
		JLabel minLb = new JLabel("");
		minLb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				/* put/change the image on label from computer when a pass mouse on Label*/
				minLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\mouse\\minMouseEntered.png"));
			}
			public void mouseExited(MouseEvent e) {
				/* put/change the image on label from computer when I take out the mouse from Label*/
				minLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\min.png"));
			}
		});
		minLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\min.png"));
		minLb.setBounds(390, -1, 30, 22);
		contentPane.add(minLb);
		
		JLabel xLb = new JLabel("");
		xLb.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				/* put/change the image on label from computer when a pass mouse on Label*/
				xLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\mouse\\xLbMouseEntered.png"));
			}
			public void mouseExited(MouseEvent e) {
				/* put/change the image on label from computer when I take out the mouse from Label*/
				xLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\xLb.png"));
			}
			public void mouseClicked(MouseEvent e) {
				/* put/change the image on label from computer when I click on Label*/
				xLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\mouse\\xLbMouseClicked.png"));	
			dispose();
			}
		});
		xLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\xLb.png"));
		xLb.setBounds(445, 1, 56, 19);
		contentPane.add(xLb);
		
		JLabel loginTxt = new JLabel("Login:");
		loginTxt.setBounds(55, 223, 46, 14);
		contentPane.add(loginTxt);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(55, 255, 64, 14);
		contentPane.add(lblPassword);
		
		JLabel cofirmLb = new JLabel("");
		cofirmLb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cofirmLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\mouse\\bottomMouseEntered.png"));
			}
			public void mouseExited(MouseEvent e) {
				cofirmLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\bottom-iloveimg-resized.png"));
			}
			public void mouseClicked(MouseEvent e){
				try {
					// call method when I click 
					checkUser();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		cofirmLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\bottom-iloveimg-resized.png"));
		cofirmLb.setBounds(209, 320, 89, 30);
		contentPane.add(cofirmLb);
		
		JLabel lbRegister = new JLabel("Do you want to register ?");
		lbRegister.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				/* change the color of text and increase its size, when I pass the mouse*/
				lbRegister.setForeground(Color.RED);
				lbRegister.setFont(new Font(null, Font.CENTER_BASELINE, 13));
			}
			public void mouseExited(MouseEvent e) {
				/* change the color of text and decrease its size, when I take out the mouse*/
				lbRegister.setForeground(Color.RED);
				lbRegister.setFont(new Font(null, Font.CENTER_BASELINE, 12));
				
			}
			public void mouseClicked(MouseEvent e) {
				/* change the color of text and increase its size, when I click with the mouse*/
				lbRegister.setForeground(Color.BLACK);
				lbRegister.setFont(new Font(null, Font.CENTER_BASELINE, 13));
				lbRegister.setBounds(244, 285, 180, 15);
				try {
					LoginFramInsert lInsert = new LoginFramInsert();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		lbRegister.setForeground(Color.RED);
		lbRegister.setFont(new Font(null, Font.CENTER_BASELINE, 12));
		lbRegister.setBounds(244, 285, 180, 14);
		contentPane.add(lbRegister);
		
		JLabel faceLb = new JLabel("");
		faceLb.addMouseListener(new MouseAdapter() {
			@Override
		    public void mousePressed (MouseEvent e) {
				faceLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\mouseClicked\\facebookClicked.png"));
			}
			public void mouseEntered(MouseEvent e) {
			   faceLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\mouse\\facebookMousePassed.png"));
			   /* set a pop up to label when user pass the mouse on label
			    */
			   faceLb.setToolTipText("Click to Copy the Adress and Paste in your Browser");
			}
			public void mouseExited(MouseEvent e) {
				faceLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\facebook-icon.png"));
			}
			
			public void mouseClicked(MouseEvent e){
				faceLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\mouse\\facebookMousePassed.png"));
				// whenever user click on label, I'll make that clip automatically copy the Address 
				stringSelection = new StringSelection ("www.facebook.com/italo.morai");
				clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents (stringSelection, null);
			}
		});
		faceLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\facebook-icon.png"));
		faceLb.setBounds(42, 49, 52, 52);
		contentPane.add(faceLb);
		
		JLabel lbLinkedin = new JLabel("");
		lbLinkedin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e ){
				lbLinkedin.setIcon(new javax.swing.ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\mouseClicked\\linkedinClicked.png"));
				}
			public void mouseEntered(MouseEvent e) {
				lbLinkedin.setIcon(new javax.swing.ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\mouse\\LinkedinEntered.png"));
				lbLinkedin.setToolTipText("Click to Copy the Adress and Paste in your Browser");
			}
			public void mouseExited(MouseEvent e) {
				lbLinkedin.setIcon(new javax.swing.ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\linkedin-icon.png"));	
			}
			public void mouseClicked(MouseEvent e){
				lbLinkedin.setIcon(new javax.swing.ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\mouse\\LinkedinEntered.png"));
				stringSelection = new StringSelection ("www.linkedin.com/in/italoEMMorais");
				clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents (stringSelection, null);
			}
		});
		lbLinkedin.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\linkedin-icon.png"));
		lbLinkedin.setBounds(102, 49, 52, 52);
		contentPane.add(lbLinkedin);
		
		JLabel userLb = new JLabel("");
		userLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\user-icon.png"));
		userLb.setBounds(233, 166, 50, 50);
		contentPane.add(userLb);
		
		JLabel gitLb = new JLabel("");
		gitLb.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				gitLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\mouseClicked\\getMouseClicked.png"));
			}
			public void mouseEntered(MouseEvent e) {
				gitLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\mouse\\gitMouseEntered.png"));
				gitLb.setToolTipText("Click to Copy the Adress and Paste in your Browser");	
			}
			public void mouseExited(MouseEvent e) {
				gitLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\github-free-icon-512-iloveimg-resized.png"));
			}
			public void mouseClicked(MouseEvent e){
				gitLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\mouse\\gitMouseEntered.png"));
				stringSelection = new StringSelection ("www.github.com/italoemm");
				clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents (stringSelection, null);
			}
		});
		gitLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\github-free-icon-512-iloveimg-resized.png"));
		gitLb.setBounds(162, 43, 55, 60);
		contentPane.add(gitLb);
		
		JLabel minAndXLb = new JLabel("");
		minAndXLb.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\minAndX1.PNG"));
		minAndXLb.setBounds(390, -1, 104, 22);
		contentPane.add(minAndXLb);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\login_beFunky.png"));
		background.setBounds(0, 0, 504, 415);
		contentPane.add(background);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
	}// finali constructor
	
	public void checkUser() throws Exception{
		
		
		int id ;
   	    String login ;
      	int password ;
		boolean isvalid;
		// check if the fields are empty
		if((!loginInTextField.getText().isEmpty() || ! passInTextField.getText().isEmpty())){	
			 
			 login = loginInTextField.getText();
			 password = Integer.parseInt(passInTextField.getText());
			 isvalid = daoLogin.isValidEmailId((login));
			 

			 if(login.equals("admin") && password == 123){
				new  HistoryFramPrincipal();
			 }else{
			
			 //check if email is valid
			 if(!isvalid){
	        	 JOptionPane.showMessageDialog(null, "Email Invalid","Error",JOptionPane.ERROR_MESSAGE);
	         } else{
	        	 /*if email is valid I'll send to method check if the login and password
	        	  are in DB, if yes ...it's going return a employee and call the class... if's not ...it's going show a error
	        	 */
	        	emp = daoLogin.checkLoginManager(login, password);
	        	if(emp==null){
	    			JOptionPane.showMessageDialog(null, "User not Founded","Error",JOptionPane.ERROR_MESSAGE);
	    		}else{
	    	        dispose();
	    	        new Thread().sleep(250);
	    			empCrud = new EmpCrudFramPrincipal();
	    	         //new Teste();
	   
	    		}
	         }
			 }
		 }else {
			 JOptionPane.showMessageDialog(null,"Please fill the fields \n                Thanks","Confirm",JOptionPane.ERROR_MESSAGE);
        }
		
		
	}

	public static Employee getEmp() {
		return emp;
	}	
	
	
}// final class
