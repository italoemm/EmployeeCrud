package br.com.italoemm.empcrud.bcrud.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.italoemm.empcrud.acore.Employee;
import br.com.italoemm.empcrud.adao.EmployeeDAO;
import br.com.italoemm.empcrud.adao.HistoryDAO;
import br.com.italoemm.empcrud.bhistory.ui.HistoryReasonJframe;
import br.com.italoemm.empcrud.blogin.ui.LoginFramPrincipal;
import br.com.italoemm.empcrud.cfunctionally.*;
/**
 * @author ${github/italoemm}
 *
 * 
 */
public class EmpCrudFramPrincipal extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField lastNameTextField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JFrame frame =null;
	
	

	/*it's going be used  to iteract with BD*/
	private  List<Employee> empList = null;
	
	private  EmployeeModel modelEmp = null;
	private  EmployeeDAO empDAO= null;
	private  Employee empManager=null;
	/*it has to be static, cause I'm going user later to insert in DB from other class*/
	private static String dateString;
	private HistoryDAO histDAO;
	private Thread trDate;
	private Thread trReason;
	/*it has to be static, cause I'm going use only a table*/
	private  static JTable table;
	private EmployeeFunctionally empFunc;
		
	public EmpCrudFramPrincipal() throws Exception {
		//Thread.sleep(100);
		// instantiate of Manager, EmployeeDAO and HistoryDAO
		empManager = LoginFramPrincipal.getEmp();
		empDAO= new EmployeeDAO();
		histDAO = new HistoryDAO();
		empFunc = new EmployeeFunctionally();
		
		frame =new JFrame();
		frame.setVisible(true);
		frame.setTitle("Employee Crud");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 424, 71);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLastName = new JLabel("Enter Last Name");
		lblLastName.setBounds(24, 35, 103, 14);
		panel.add(lblLastName);
		
	

	    scrollPane = new JScrollPane();
	    scrollPane.setBounds(5, 77, 424, 273);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getClickCount()==2){
					try {
						showProfile();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		/*could initialize the table filled but, I decided to fill empty*/
		table.setModel(new EmployeeModel());
		table.setFillsViewportHeight(true);		
		scrollPane.setViewportView(table);
		
   
		

  /* Yes I know, I could take straight from DB, but I didn't see need to 
   * make a fetch on DB each letter that I type, So I tought
   * why not get All employee beloging of Manager from DB a once
   * and after use the same list to fetch employee 
   * 
   */	lastNameTextField = new JTextField();
		lastNameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					List<Employee> listCheck = new ArrayList<Employee>();
					/*if JTextField is empty I'll get all employee of manager*/
					if(lastNameTextField.getText().isEmpty()){
					modelEmp= new EmployeeModel(empList);
					}else{
						
						for(int i=0 ;i<empList.size();i++)  {
							
			        	  if(empList.get(i).getLastName().toLowerCase().startsWith(lastNameTextField.getText().toLowerCase()) ||  (empList.get(i).getLastName().startsWith(lastNameTextField.getText()))){
			        		  listCheck.add(empList.get(i));		        		  
		                                   }        	    
			          }
						modelEmp= new EmployeeModel(listCheck);	
					}
					table.setModel(modelEmp);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				
			    }
			}
		}); 
		
		lastNameTextField.setBounds(137, 32, 126, 20);
		panel.add(lastNameTextField);
		lastNameTextField.setColumns(15);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(293, 31, 76, 23);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					String lastName = lastNameTextField.getText();	
					
					/* if the field is empty I'll get a list of all employee belonging to manager*/
					if(lastName.isEmpty()){
						empList = empDAO.searchEmployee(null,false);		
					}else{
						/*otherwise I'm going get a specific employee*/
						empList = empDAO.searchEmployee(lastName,false);	
					}	
					/* after get the list I'm going send to a Model*/
					modelEmp = new EmployeeModel(empList);
					/*and now I set the table with that model created*/
					table.setModel(modelEmp);
					} catch (Exception e1) {
					e1.printStackTrace();
				}
		
			}
		});
		panel.add(btnSearch);
		
		// I'm going get the name and last name of Manager and put into label;
		String managerName = "Sr. "+empManager.getName()+" "+empManager.getLastName();
		JLabel lblManagerName = new JLabel(managerName);
		lblManagerName.setBounds(24, 7, 160, 14);
		panel.add(lblManagerName);
		
	
		JLabel lblDate = new JLabel ("") ;
		// I'm going get the time and put into label;
	    trDate = new Thread(new Runnable() {
	    	/* pretty much I'm going create a Indepedent path of execution without
	    	 * compromising the program.In otherwords
	    	 * I want that the method be called for each 1 seconds while I doing
	    	 * other things in program. 
	    	 */
		public void run() {
			try {
				while (frame.isEnabled()){
				 dateString=getDate();
				 lblDate.setText(dateString);
				}
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		
	});
	trDate.start();
		
		lblDate.setBounds(293, 7, 121, 14);
		panel.add(lblDate);
	
		
		panel_1 = new JPanel();
		panel_1.setBounds(5, 355, 424, 46);
		contentPane.add(panel_1);
		
		btnInsert = new JButton("   Insert");
		btnInsert.setBounds(22, 12, 80, 23);
		/* whenever I click on button I'm going call method*/
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					callInsertFrame();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		
		panel_1.setLayout(null);
		btnInsert.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(btnInsert);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(133, 12, 80, 23);
		/* whenever I click on button I'm going call method*/
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					callUpdateFrame();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		panel_1.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(244, 12, 80, 23);
		/* whenever I click on button I'm going call method*/
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					callDeleteDialog();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_1.add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("");
		/* here I created some action in a arrow...so if I pass the mouse on image its going change
		 * if I click on image its going change... and go on
		 */
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\arrow\\arrowEntered.png"));
			}
			public void mouseExited(MouseEvent e) {
				lblNewLabel.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\arrow\\Original.png"));
			}
			public void mouseClicked(MouseEvent e) {
				lblNewLabel.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\arrow\\arrowClicked.png"));
			    //but when I click on image I'm going dispose the program and go back to the login screen
				try {
					new LoginFramPrincipal().setVisible(true);;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				frame.dispose();
			}
			public void mousePressed(MouseEvent e) {
				lblNewLabel.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\arrow\\arrowPressed.png"));
			}
		});
		lblNewLabel.setIcon(new ImageIcon("C:\\POO\\workspace\\EmployeeCrud\\login_image\\arrow\\Original.png"));
		lblNewLabel.setBounds(354, 0, 50, 50);
		panel_1.add(lblNewLabel);
		
		
	}
public void callUpdateFrame() throws Exception {
	/*if the row is not select I'm going show a error message*/
	if(table.getSelectedRow()<0){
		JOptionPane.showMessageDialog(null, "Please Select a Employee", "error", JOptionPane.ERROR_MESSAGE);
	}else{
		new EmpCrudFramUpdate();
	}
	}

public void callInsertFrame() throws Exception {
        new EmpCrudFramInsert();
	    
}

public void callDeleteDialog() throws Exception{
	/*if the row is not select I'm going show a error message*/
	if(table.getSelectedRow()<=-1){
		JOptionPane.showMessageDialog(null, "Please Select a Employee", "error", JOptionPane.ERROR_MESSAGE);
	}else{
	
	    

		 int op = JOptionPane.showConfirmDialog(null, "Are you sure", "Confirm",JOptionPane.YES_NO_OPTION);
		 if(op == JOptionPane.YES_OPTION ){
			 
			 /* I'm going get all values of the row selected*/	 
			   Employee temp = empFunc.convertInEmployee(null,table);
			   
			   HistoryReasonJframe rj = new HistoryReasonJframe();
		        new Thread(new Runnable() {
				public void run() {
					  
					/*Indepedent path of execution to keep check if the ReasonJframe
					 * already closed, if it's closed I check if the field "reason"
					 * is empty, if its not... I delete the employee and I insert 
					 * what Manager is doing in DB  */
					try {
					while(!rj.getIsclosed()){
						 System.out.println("inside while");
						if(rj.getIsclosed()){
							
	                               if(!rj.getReason().isEmpty() || rj.getReason() != null ){
							          histDAO.insertHistory(temp, "Deleted",rj.getReason());
							
							          empDAO.deleteEmployee(temp);
							          empFunc.checkAndRefreshTable();	
							      break;
	                              }
	                               System.out.println("inside if");
                                  break;
	                              }
                 
					}
					Thread.sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
		
				}
			}).start();
		 }
		 }
		
	
} 

public String getDate(){
	 DateFormat dateFormat;
	 Date date;
	//get date from API JAVA
	dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	date = new Date();
	String st = dateFormat.format(date);
	return st;
}

public void showProfile() throws Exception{
	if(table.getSelectedRow()<=-1){
		JOptionPane.showMessageDialog(null, "Please Select a Employee", "error", JOptionPane.ERROR_MESSAGE);
	}else{
		/* I'm going get all values of the row selected*/
		
		Employee temp = empFunc.convertInEmployee(null,table);
		
		new EmpCrudFramShowProfle(temp);
	
}
}

// cause I'm going use in other class the same table
public static JTable getTable() {
	return table;
}
//cause I'm going use in other class the same date 
public static String getDateString() {
	return dateString;
}

}

