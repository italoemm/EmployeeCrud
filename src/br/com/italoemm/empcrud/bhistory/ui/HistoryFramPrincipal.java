package br.com.italoemm.empcrud.bhistory.ui;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import br.com.italoemm.empcrud.acore.Employee;
import br.com.italoemm.empcrud.acore.History;
import br.com.italoemm.empcrud.adao.EmployeeDAO;
import br.com.italoemm.empcrud.adao.HistoryDAO;
import br.com.italoemm.empcrud.blogin.ui.LoginFramPrincipal;
import br.com.italoemm.empcrud.cfunctionally.EmployeeFunctionally;
import br.com.italoemm.empcrud.cfunctionally.EmployeeModel;
import br.com.italoemm.empcrud.cfunctionally.HistoryModel;

public class HistoryFramPrincipal extends JFrame {

	/**
	 * @author ${github/italoemm}
	 *
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblreasonTXT;
	private JTextArea areTxt = null;
	private JPanel contentPane;
	private JTextField lastNameTextField;
	private static JTable empTable;	
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable historyTable;
	private JFrame frame =null;
	
	private List<Employee> empList = null;
	private EmployeeModel modelEmp = null;
	private EmployeeDAO empDAO= null;
	//private Employee emp = null;
	private List<History> histList = null;
	private HistoryModel histModel = null;
	private HistoryDAO histDAO = null;
	private EmployeeFunctionally empFunc;
	
	
	
	public HistoryFramPrincipal() throws Exception {
		new Thread().sleep(100);
		// instantiate of Manager
		//emp = LoginFramPrincipal.getEmp();
		empDAO= new EmployeeDAO();
		histDAO = new HistoryDAO();
		empFunc= new EmployeeFunctionally();
		
		frame =new JFrame();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setTitle("Adm History");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 441, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 11, 424, 42);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLastName = new JLabel("Enter Last Name");
		lblLastName.setBounds(22, 11, 103, 14);
		panel.add(lblLastName);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(135, 8, 203, 20);
		panel.add(lastNameTextField);
		lastNameTextField.setColumns(15);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(348, 7, 76, 23);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					/*if the field is empty I'll get from DB all employees belogin to Manager
					 * otherwise I'm going get a specific employee*/
					String lastName = lastNameTextField.getText();
					if(lastName.isEmpty()){
					empList = empDAO.searchEmployee(null,true);
					}else{
					empList = empDAO.searchEmployee(lastName,true);
					}
					/* I'll set the model with the list returned from DB*/
					modelEmp = new EmployeeModel(empList);
					/* I'm going set the table with that model*/
					empTable.setModel(modelEmp);
			
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}finally{}
		
			}
		});
		panel.add(btnSearch);
		
	    scrollPane = new JScrollPane();
	    scrollPane.setBounds(5, 53, 424, 224);
		contentPane.add(scrollPane);
		
		empTable = new JTable();
		empTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					/* call method and increase the size of JFrame*/
					showHistory();
					frame.setBounds(100, 100, 441, 450);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		empTable.setModel(new EmployeeModel());
		empTable.setFillsViewportHeight(true);		
		scrollPane.setViewportView(empTable);
		
		JScrollPane historyScrollPane = new JScrollPane();
		historyScrollPane.setBounds(5, 301, 424, 111);
		contentPane.add(historyScrollPane);
		
		historyTable = new JTable();
		historyTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				showReason();
				
			}
		
		});
		historyTable.setModel(new HistoryModel());
		historyTable.setFillsViewportHeight(true);
		historyScrollPane.setViewportView(historyTable);
		
		JLabel lblHistory = new JLabel("History");
		lblHistory.setBounds(15, 283, 67, 14);
		contentPane.add(lblHistory);
		
		JPanel allReasonPanel = new JPanel();
		allReasonPanel.setBounds(439, 11, 195, 401);
		contentPane.add(allReasonPanel);
		allReasonPanel.setLayout(null);
		
		JLabel lblReason = new JLabel("                  Reason");
		/* initialize with a custom font*/
		lblReason.setFont(new Font("Tahoma", Font.BOLD, 13));
		/* initialize with a custom border*/
		lblReason.setBorder(new MatteBorder(1,  1, 1,1, java.awt.Color.BLACK));
		/* initialize with a custom color of background*/
		lblReason.setBackground(SystemColor.controlHighlight );
		lblReason.setBounds(0, 7, 195, 34);
		/* I have to set true in intent to be available the custom modification in backgroud
		 * of label*/
		lblReason.setOpaque(true);
		allReasonPanel.add(lblReason);
	
		
		areTxt = new JTextArea();
		areTxt.setEditable(false);
		areTxt.setBorder(new MatteBorder(0, 1, 1,1, java.awt.Color.BLACK));
		areTxt.setBackground(java.awt.Color.WHITE );
		areTxt.setOpaque(true);
		areTxt.setBounds(0, 42, 195, 359);
		allReasonPanel.add(areTxt);
		

	}

public void showHistory() throws Exception{
	     /* I'm going get all values of the row selected*/
	
		Employee temp = empFunc.convertInEmployee(null,empTable);
		
		/* I'm going get a list of all history from a specific Manager*/
		histList = histDAO.searchHistory(temp);
		/* after get the list I'm going send to a Model*/
		histModel = new HistoryModel(histList);
		/*and now I set the table with that model created*/
		historyTable.setModel(histModel);		
} 
 public void showReason(){
	 /* increase the the size of JFrame*/
	 frame.setBounds(100, 100, 650, 450);
	 /* get the "reason" of specific modification of specific Manager*/
	 String getFromTable = (String) historyTable.getModel().getValueAt(historyTable.getSelectedRow(), 4);
	 /* after get the text from DB I will split the text in two parts if the caracters is bigger than 27*/
	 String convert = splitReason(getFromTable);
	 areTxt.setText(convert);
 }

public static JTable getTable() {
	return empTable;
}

 /*I created this method case whenener I was getting the "reason" straight from bd
and put in Componente JTextArea,the texts that were Larger than JtextArea, they didn't fit in Area
 so I have to split the phrase puttin "\n" */
public String splitReason(String str ) {
	int count = 0;
	String phrase ="";
	
	if ( str == null ) {
	     return null;
	   }

	   int len = str.length();
	   // converting all string in array of char
	   Character[] array = new Character[len];
	   for (int i = 0; i < len ; i++) {
	      array[i] = str.charAt(i);
	   }

	   for(Character c : array){
		   /*gettin again the String converted in char, when count arrive in 27 , I add in string 
		     a split "/n", but I have to split only in "spaces" of pharse,otherwise the 
		     words are goin to mess up*/
			phrase=phrase+String.valueOf(c);
			count++;
			if(count == 27){
				if(c.equals(' ')){
				phrase= phrase + "\n";
				count=0;
			}else{
				count = 26;
			}
			}
		}
	   return phrase;
	}
}
