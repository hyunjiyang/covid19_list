package package_exam5;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.ModuleLayer.Controller;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;

import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.GridLayout;

public class UseJTab extends JFrame implements ActionListener {
	private JTabbedPane jtpTab;
	private JTextArea jtaNote, jtaNote2,jtaNote3, jtaNoteView,jtaNoteView2,jtaNoteView3,jtaNoteView4;
	private JTextField jtfSecret;
	private JComboBox jcombo;
	private JButton jbtSend;
	String Check[]= {"동의","비동의"};
	private JTextField textField;
	private JTable table;
	private JScrollPane scrollPane;
	DefaultTableModel model;
	
	public UseJTab() {
		super("코로나 명부");
		
		jtaNote = new JTextArea();
		jtaNote.setBorder(new TitledBorder("성명"));
		jtaNote.setBounds(0, 0, 270, 100);
		
		jtaNote2 = new JTextArea();
		jtaNote2.setBorder(new TitledBorder("시/군/구(거주지)"));
		jtaNote2.setBounds(0, 100, 270, 100);
		
		jtaNote3 = new JTextArea();
		jtaNote3.setBorder(new TitledBorder("전화번호"));
		jtaNote3.setBounds(0, 200, 270, 100);
		
		jcombo = new JComboBox<String>(Check);
		jcombo.setBounds(90,300,100,30);
		
		
		
		jbtSend = new JButton("저장");
		jbtSend.setBounds(90, 350, 100, 60);
		
		JPanel jpTab1 = new JPanel();
		jpTab1.setLayout(null);
		jpTab1.setBackground(Color.WHITE);
		
		jpTab1.add(jtaNote);
		jpTab1.add(jtaNote2);
		jpTab1.add(jtaNote3);
		jpTab1.add(jcombo);
		jpTab1.add(jbtSend);
		////////////////////////////////////////////////////////////////////////
		
		
		jtaNoteView = new JTextArea();
		jtaNoteView.setBorder(new TitledBorder("성명"));
		jtaNoteView.setBackground(Color.WHITE);
		jtaNoteView.setFont(new Font("나눔고딕", Font.BOLD | Font.ITALIC, 15));
		jtaNoteView.setBounds(0, 0, 270, 100);
		jtaNoteView.setEditable(false);
		
		
		jtaNoteView2 = new JTextArea();
		jtaNoteView2.setBorder(new TitledBorder("시/군/구(거주지)"));
		jtaNoteView2.setBackground(Color.WHITE);
		jtaNoteView2.setFont(new Font("나눔고딕", Font.BOLD | Font.ITALIC, 15));
		jtaNoteView2.setBounds(0, 100, 270, 100);
		jtaNoteView2.setEditable(false);
		
		jtaNoteView3 = new JTextArea();
		jtaNoteView3.setBorder(new TitledBorder("전화번호"));
		jtaNoteView3.setBackground(Color.WHITE);
		jtaNoteView3.setFont(new Font("나눔고딕", Font.BOLD | Font.ITALIC, 15));
		jtaNoteView3.setBounds(0, 200, 270, 100);
		jtaNoteView3.setEditable(false);
		
		jtaNoteView4 = new JTextArea();
		jtaNoteView4.setBorder(new TitledBorder("개인정보 수집 동의여부"));
		jtaNoteView4.setBackground(Color.WHITE);
		jtaNoteView4.setFont(new Font("나눔고딕", Font.BOLD | Font.ITALIC, 15));
		jtaNoteView4.setBounds(0, 300, 270, 100);
		jtaNoteView4.setEditable(false);
		
		
		JPanel jpTab2 = new JPanel();
		jpTab2.setLayout(null);
		
		jpTab2.add(jtaNoteView);	
		jpTab2.add(jtaNoteView2);
		jpTab2.add(jtaNoteView3);
		jpTab2.add(jtaNoteView4);
		
		///////////////////////////////////////////////////////////////////////////
		
		jtfSecret = new JTextField();
		jtfSecret.setBorder(new TitledBorder("관리자용"));
		jtfSecret.setBackground(Color.WHITE);
		jtfSecret.setForeground(Color.RED);
		jtfSecret.setFont(new Font("나눔고딕", Font.BOLD | Font.ITALIC, 15));
		jtfSecret.setBounds(0, 0, 372, 100);
		jtfSecret.setEditable(false);
		
		JPanel jpTab3 = new JPanel();
		jpTab3.setLayout(null);
		
		jpTab3.add(jtfSecret);
		
		jtpTab = new JTabbedPane();
		jtpTab.add("입력", jpTab1);
		jtpTab.add("결과", jpTab2);
		jtpTab.add("관리자용", jpTab3);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 110, 510, 200);
		jpTab3.add(scrollPane);
		
		JTable tb = new JTable();
		tb.setBackground(new Color(255, 255, 255));
		model = new DefaultTableModel();
		Object[] column = {"이름", "거주지", "연락처", "방문시각", "동의여부"};
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		tb.setModel(model);
		scrollPane.setViewportView(tb);
		
		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setBounds(159, 360, 34, 23);
		jpTab3.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(201, 361, 152, 21);
		jpTab3.add(textField);
		textField.setColumns(10);
		
		
		JButton btnNewButton = new JButton("검색");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                //String name = textField.getText();
                

			}
		});		
		btnNewButton.setBounds(159, 392, 91, 23);
		jpTab3.add(btnNewButton);	
		
		
		
		JButton btnNewButton_1 = new JButton("삭제");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tb.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please Select a Row First");
				}
				else {
					model.removeRow(tb.getSelectedRow());
					JOptionPane.showMessageDialog(null, "Deleted Successfully");
				}
				
			}
		});
		btnNewButton_1.setBounds(262, 392, 91, 23);
		jpTab3.add(btnNewButton_1);
		
	
		
		
		getContentPane().add(jtpTab);
		
		jbtSend.addActionListener(this);
		
		UseJTab.TabEvt te = this.new TabEvt();
		
		jtpTab.addMouseListener(te);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(531, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	} 
	
	public class TabEvt extends MouseAdapter {
		
		public void mouseClicked(MouseEvent me) {
			if (jtpTab.getSelectedIndex() == 1) {
				jtaNoteView.setText("");
			} 
			
			if (jtpTab.getSelectedIndex() == 2) {
				jtfSecret.setText("");
				
				String passwd = JOptionPane.showInputDialog("관리자용 탭입니다.\n비밀번호를 입력해주세요.");
				
				if (passwd == null) {
					return;
				} 		
				
				if (passwd.equals("1906")) {
					jtfSecret.setText("관리자용");
				} else {
					JOptionPane.showMessageDialog(jtfSecret, "비밀번호를 다시 확인해주세요.");
				} 
			} 
		} 
	}
	
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jbtSend) {
			String data[] = new String[5];
			
			data[0] = jtaNote.getText();
			data[1]= jtaNote2.getText();
			data[2]= jtaNote3.getText();
			
			LocalDate currentDate = LocalDate.now();
			String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			data[3] = currentTime;
			
			String combobox = (String) jcombo.getSelectedItem();
			data[4] = combobox;
			model.addRow(data);
	
			
			if (!data[0].equals("") || !data[0].isEmpty()) {
							
				
				jtaNoteView.setText(data[0]);
				jtaNoteView2.setText(data[1]);
				jtaNoteView3.setText(data[2]);
				jtaNoteView4.setText((String) jcombo.getSelectedItem());
				
				jtpTab.setSelectedIndex(1);
				
				
				jtaNote.setText("");
				jtaNote2.setText("");
				jtaNote3.setText("");
				
				return;
			} else {
				JOptionPane.showMessageDialog(this, "전송할 데이터가 없습니다.");
			}
		}
		
	}
	public static void main(String[] args) {
		new UseJTab();
		
	} 
} 