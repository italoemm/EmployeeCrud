package br.com.italoemm.empcrud.bhistory.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import br.com.italoemm.empcrud.acore.Employee;
import br.com.italoemm.empcrud.adao.HistoryDAO;

import java.awt.Panel;
import java.awt.Button;
import java.awt.Color;

import javax.swing.JTextArea;

public class HistoryReasonJframe extends JFrame {
	/**
	 * @author ${github/italoemm}
	 *
	 * 
	 */
	private JPanel contentPane;
    private JFrame frmReason;
	private JPanel panelBttn;
	
	private String reason;
	private boolean isclosed=false;
	
	public HistoryReasonJframe() {
		
        frmReason = new JFrame();
        frmReason.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frmReason.setResizable(false);
        frmReason.setTitle("Reason");
		frmReason.setVisible(true);
		frmReason.setBounds(100, 100, 338, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmReason.setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		frmReason.addWindowListener(new WindowAdapter() {
		    public void windowClosed(WindowEvent e) {
		    	isclosed=true;
		    }

		});
		
		// when I click in field its going to be empty and words are going be black
		JTextArea txtrWhyAreYou = new JTextArea();
		txtrWhyAreYou.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				txtrWhyAreYou.setForeground(Color.BLACK);
				txtrWhyAreYou.setText(null);
			}
		});
		txtrWhyAreYou.setForeground(Color.GRAY);
		txtrWhyAreYou.setText("Why Are doing it?...");
		txtrWhyAreYou.setBounds(10, 11, 302, 112);
		txtrWhyAreYou.setBorder(new MatteBorder(1, 1, 1,1, java.awt.Color.gray));
		txtrWhyAreYou.setOpaque(true);
		contentPane.add(txtrWhyAreYou);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			/* whenever I click on button I'm going close JFrame set variable with getText
			 * and set true in variable isclosed */
			public void actionPerformed(ActionEvent e) {
				reason = txtrWhyAreYou.getText();
				isclosed = true;
				frmReason.dispose();
			}
		});
		btnOk.setBounds(124, 136, 81, 23);
		contentPane.add(btnOk);
		
}
	
	public  boolean getIsclosed() {
	
		return isclosed;
		}
	
	

		public  String getReason() {
			
			return reason;
	}
	
}
