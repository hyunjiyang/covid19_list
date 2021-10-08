
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.ModuleLayer.Controller;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

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
import java.awt.LayoutManager;

import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;
import javax.swing.JTable.*;

public class Covidinfo extends JFrame implements ActionListener {
	public String Check[]= {"동의","비동의"};
	public DefaultTableModel model;
	public JTable tb = new JTable();
	
	private JTabbedPane TabPane;
	private JTextArea InputName, InputPlace,InputNumber, InputView1,InputView2,InputView3,InputView4;
	private JTextField jtfSecret, textField;
	private JComboBox jcombo;
	private JButton SendBt;
	private JTable table;
	private JScrollPane scrollPane;
	
	private boolean aa = true;
	
	private Thread t1;
	
	Font font;
	Color color = (Color.WHITE);
	private JPanel pnlMain;
	private JLabel lblNorth;
	private JLabel lblCenter;
	private JPanel pnlSouth;
	private JPanel pnlInSouth;
	private JLabel lblSouth;
	private JPanel pnlbtn;
	private JButton btnTime;
	private JButton btnDay;

	public Covidinfo() {
		super("코로나 명부");
		
		InputName = new JTextArea();
		InputName.setBorder(new TitledBorder("성명"));
		InputName.setBounds(0, 0, 270, 100);
		
		InputPlace = new JTextArea();
		InputPlace.setBorder(new TitledBorder("시/군/구(거주지)"));
		InputPlace.setBounds(0, 100, 270, 100);
		
		InputNumber = new JTextArea();
		InputNumber.setBorder(new TitledBorder("전화번호"));
		InputNumber.setBounds(0, 200, 270, 100);
		
		jcombo = new JComboBox<String>(Check);
		jcombo.setBounds(90,300,100,30);
		
		
		
		SendBt = new JButton("저장");
		SendBt.setBounds(90, 350, 100, 60);
		
		JPanel Tab1= new JPanel();
		Tab1.setLayout(null);
		Tab1.setBackground(Color.WHITE);
		
		Tab1.add(InputName);
		Tab1.add(InputPlace);
		Tab1.add(InputNumber);
		Tab1.add(jcombo);
		Tab1.add(SendBt);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(271, 45, 227, 239);
		Tab1.add(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pnlMain = new JPanel((LayoutManager) null);
		contentPane.add(pnlMain, BorderLayout.CENTER);
		pnlMain.setLayout(new BorderLayout());
		
		lblNorth = new JLabel("", SwingConstants.CENTER);
		lblNorth.setOpaque(true);
		lblNorth.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lblNorth.setBackground(Color.WHITE);
		pnlMain.add(lblNorth, BorderLayout.NORTH);
		
		lblCenter = new JLabel("", SwingConstants.CENTER);
		lblCenter.setOpaque(true);
		lblCenter.setFont(new Font("맑은 고딕", Font.BOLD, 28));
		lblCenter.setBackground(Color.WHITE);
		pnlMain.add(lblCenter, BorderLayout.CENTER);
		
		pnlSouth = new JPanel((LayoutManager) null);
		pnlSouth.setBackground(Color.WHITE);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);
		pnlSouth.setLayout(new BorderLayout());
		
		pnlInSouth = new JPanel();
		pnlInSouth.setBackground(Color.WHITE);
		pnlSouth.add(pnlInSouth, BorderLayout.NORTH);
		
		lblSouth = new JLabel("버튼을 누르면 현재 시간과 날짜를 확인할 수 있습니다.", SwingConstants.CENTER);
		lblSouth.setFont(new Font("나눔고딕", Font.PLAIN, 9));
		pnlInSouth.add(lblSouth);
		
		pnlbtn = new JPanel();
		pnlbtn.setBackground(Color.WHITE);
		pnlSouth.add(pnlbtn, BorderLayout.SOUTH);
		
		btnTime = new JButton("\uC2DC\uAC04");
		btnTime.setBackground(Color.WHITE);
		pnlbtn.add(btnTime);
		
		btnDay = new JButton("\uB0A0\uC9DC");
		btnDay.setBackground(Color.WHITE);
		pnlbtn.add(btnDay);
		
		font = new Font("맑은 고딕",Font.BOLD,28);
		
		
		ThreadTime();
		
		btnDay.addActionListener((e)->{
			
			t1.interrupt();
			SimpleDateFormat day = new SimpleDateFormat ( "yyyy-MM-dd");
			
			Calendar cal = Calendar.getInstance();
			
			String day_string = day.format(cal.getTime());
		
			lblNorth.setText("연도-월-일");
			lblCenter.setText(day_string);
			
		});
		
		btnTime.addActionListener((e)->{
			
			// 여기서 boolean 값을 true로 바꾸지 않으면
			// 스레드안의 while문이 작동하지 않아서 시계가 안돌아감
			aa = true;
			
			ThreadTime();
			
		});
		////////////////////////////////////////////////////////////////////////
		
		
		InputView1 = new JTextArea();
		InputView1.setBorder(new TitledBorder("성명"));
		InputView1.setBackground(Color.WHITE);
		InputView1.setFont(new Font("나눔고딕", Font.BOLD | Font.ITALIC, 15));
		InputView1.setBounds(0, 0, 510, 100);
		InputView1.setEditable(false);
		
		
		InputView2 = new JTextArea();
		InputView2.setBorder(new TitledBorder("시/군/구(거주지)"));
		InputView2.setBackground(Color.WHITE);
		InputView2.setFont(new Font("나눔고딕", Font.BOLD | Font.ITALIC, 15));
		InputView2.setBounds(0, 100, 510, 100);
		InputView2.setEditable(false);
		
		InputView3 = new JTextArea();
		InputView3.setBorder(new TitledBorder("전화번호"));
		InputView3.setBackground(Color.WHITE);
		InputView3.setFont(new Font("나눔고딕", Font.BOLD | Font.ITALIC, 15));
		InputView3.setBounds(0, 200, 510, 100);
		InputView3.setEditable(false);
		
		InputView4 = new JTextArea();
		InputView4.setBorder(new TitledBorder("개인정보 수집 동의여부"));
		InputView4.setBackground(Color.WHITE);
		InputView4.setFont(new Font("나눔고딕", Font.BOLD | Font.ITALIC, 15));
		InputView4.setBounds(0, 300, 510, 100);
		InputView4.setEditable(false);
		
		
		JPanel Tab2 = new JPanel();
		Tab2.setLayout(null);

		Tab2.add(InputView1);	
		Tab2.add(InputView2);
		Tab2.add(InputView3);
		Tab2.add(InputView4);
		
		
		///////////////////////////////////////////////////////////////////////////
		
		jtfSecret = new JTextField();
		jtfSecret.setFont(new Font("나눔고딕", Font.BOLD | Font.ITALIC, 15));
		
		
		JPanel Tab3 = new JPanel();
		Tab3.setLayout(null);
		
		Tab3.add(jtfSecret);
		
		TabPane = new JTabbedPane();
		TabPane.add("입력", Tab1);
		TabPane.add("결과(최초 1회)", Tab2);
		TabPane.add("관리자용", Tab3);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 510, 350);
		Tab3.add(scrollPane);
		
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
		Tab3.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(201, 361, 152, 21);
		Tab3.add(textField);
		textField.setColumns(10);
		
		JButton btn_search = new JButton("검색");

		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					String name = textField.getText();
					TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
					tb.setRowSorter(sorter);
					sorter.setRowFilter(RowFilter.regexFilter(name));
					//이름 지우고 다시 검색 누르면 표 나옴
			}
		});		
		btn_search.setBounds(175, 392, 91, 23);
		Tab3.add(btn_search);	
		
		
		
		JButton btn_del = new JButton("삭제");
		btn_del.addActionListener(new ActionListener() {
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
		btn_del.setBounds(285, 392, 91, 23);
		Tab3.add(btn_del);
		
		
		
		getContentPane().add(TabPane);
		
		SendBt.addActionListener(this);
		
		TabEvt te = this.new TabEvt();
		
		TabPane.addMouseListener(te);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(531, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	} 
	public void ThreadTime() {
		
		 t1 = new Thread() {
			public void run() {
				
				while(aa) {
					
					SimpleDateFormat time = new SimpleDateFormat ( "HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					
					String time_string = time.format(cal.getTime());
					
					lblNorth.setText("Time");
					lblCenter.setText(time_string);
				
				try {
					
						Thread.sleep(1000);
						
				} catch(InterruptedException ie) {
					
					ie.printStackTrace();
				
					aa = false;
					
				}
			}
				
			}
		};
		t1.start();
	}
	
	public class TabEvt extends MouseAdapter {
		
		public void mouseClicked(MouseEvent me) {
			if (TabPane.getSelectedIndex() == 1) {//결과창 정보 다시 지우기
				InputView1.setText("");
				InputView2.setText("");
				InputView3.setText("");
				InputView4.setText("");
				
				return;
			} 
			
			if (TabPane.getSelectedIndex() == 2) {
				scrollPane.setVisible(false); //비밀번호 입력 전까지는 정보 가리기
				jtfSecret.setText("");
				
				String passwd = JOptionPane.showInputDialog("관리자용 탭입니다.\n비밀번호를 입력해주세요.");
				
				if (passwd == null) {
					return;
				} 		
				
				if (passwd.equals("1906")) {
					scrollPane.setVisible(true); //비밀번호 입력시 정보 공개
					jtfSecret.setText("관리자용");
				} else {
					JOptionPane.showMessageDialog(jtfSecret, "비밀번호를 다시 확인해주세요.");
				} 
			} 
		} 
	}
	
	public void waiting(){ //5초 뒤 확인창이 종료되고 첫번쨰 탭으로 이동.
		Timer ex_timer = new Timer();
		TimerTask ex_task = new TimerTask() {
			public void run() {
				TabPane.setSelectedIndex(0);
			}
		};
		ex_timer.schedule(ex_task, 5000);
	}
		

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == SendBt) {
			String data[] = new String[5];

			data[0] = InputName.getText();
			data[1]= InputPlace.getText();
			data[2]= InputNumber.getText();
			
	
			int j =0;
			while (true)  //-한칸이라도 입력이 되지 않을 시 데이터가 저장되지 않도록 함.
				if (!data[j].equals("") || !data[j].isEmpty()) {
					j++;
					if(j==2) {
						//값 보내기
						data[0] = InputName.getText();
						data[1]= InputPlace.getText();
						data[2]= InputNumber.getText();
						
						LocalDate currentDate = LocalDate.now();
						String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
						data[3] = currentTime;
						
						String combobox = (String) jcombo.getSelectedItem();
						data[4] = combobox;

						model.addRow(data);
						
						//1번탭에 값 표시
						
						InputView1.setText(data[0]);
						InputView2.setText(data[1]);
						InputView3.setText(data[2]);
						InputView4.setText((String) jcombo.getSelectedItem());

						TabPane.setSelectedIndex(1);
						
						InputName.setText("");
						InputPlace.setText("");
						InputNumber.setText("");
						
						waiting();  //5초 뒤 확인창이 종료되고 첫번쨰 탭으로 이동.
						

						break;
					}
				} else { 
					JOptionPane.showMessageDialog(this, "모든 정보를 입력해주세요");
					TabPane.setSelectedIndex(0); 
					break;
				}
			
			
			}
			
	}
}