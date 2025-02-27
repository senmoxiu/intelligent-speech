package com.course;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.iflytek.MyVoiceListener;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.view.VoiceSpeech;
import cs.service.StudentService;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QueryStudentPanel extends JFrame {
	private JTextField stuNoField;
	private JTextField stuNameField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QueryStudentPanel frame = new QueryStudentPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the panel.
	 */
	public QueryStudentPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 444);//改
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLayout(null);

		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(100, 100, 666, 444);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("???");
		lblNewLabel.setBounds(80, 13, 30, 18);
		panel.add(lblNewLabel);
		
		stuNoField = new JTextField();
		stuNoField.setBounds(160, 10, 178, 24);
		panel.add(stuNoField);
		stuNoField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("???");
		lblNewLabel_1.setBounds(80, 60, 30, 18);
		panel.add(lblNewLabel_1);

		stuNameField = new JTextField();
		stuNameField.setBounds(160, 60, 178, 24);
		panel.add(stuNameField);
		stuNameField.setColumns(10);
		//??????
		String [] columNames = {"???","???","???"};
		//?????????
		//Object[][] cellData = {{"????","219970906","21??8??"},{"????","219970937","21??9??"}};
		Object[][] cellData = StudentService.service.toArray(StudentService.service.queryAll());
		JTable table = new JTable(cellData, columNames);
				
		table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,null,null,null,null));
		table.setBounds(39, 146, 600, 244);
		
		table.setBounds(14, 161, 450, 100);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
				
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String stuName=(String) table.getValueAt(row, 1);
				String stuNo=(String) table.getValueAt(row, 0);
				
				stuNoField.setText(stuNo);
				stuNameField.setText(stuName);
			}
		});
		scrollPane.setBounds(100, 270, 800, 250);
		panel.add(scrollPane);
		
		String[] selection = {"?????","??????","???????"};
		JComboBox comboBox = new JComboBox(selection);
		scrollPane.setBounds(0, 150, 500, 100);
		panel.add(comboBox);
		
		JButton QueryStudentButton = new JButton("???");
		QueryStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stuName = stuNameField.getText();
				String stuNo = stuNoField.getText();
				String stuClass = (String) comboBox.getSelectedItem();
				//?????????
				String [] columNames = {"???","???","???"};
				Object[][] cellData;
				if (!stuName.isEmpty()) {
					cellData = StudentService.service.toArray(StudentService.service.queryStudentByStudentName(stuName));
				}
				else if(!stuNo.isEmpty()){
					cellData = StudentService.service.toArray(StudentService.service.queryStudentByStudentNo(stuNo));
				}else if(!stuClass.isEmpty() && stuClass !=  "?????"){
					cellData = StudentService.service.toArray(StudentService.service.queryStudentByStudentClass(stuClass));
				}else if(stuClass ==  "?????"){
					cellData = StudentService.service.toArray(StudentService.service.queryAll());
				}else {
					cellData = StudentService.service.toArray(StudentService.service.queryAll());
				}
				
				//??????
				TableModel tableModlel = new DefaultTableModel(cellData,columNames);
				table.setModel( tableModlel);	
				
				table.setBounds(14, 161, 300, 100);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getColumnModel().getColumn(0).setPreferredWidth(100);
				table.getColumnModel().getColumn(1).setPreferredWidth(200);
				table.getColumnModel().getColumn(2).setPreferredWidth(200);
			}
		});
		QueryStudentButton.setBounds(280, 102, 80, 30);
		panel.add(QueryStudentButton);

		//????????
		JButton btnNewButton_2 = new JButton("????????");
		btnNewButton_2.setBounds(380, 102, 90, 30);
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VoiceHelper();
			}
		});

		JLabel lblNewLabel_2 = new JLabel("???");
		lblNewLabel_2.setBounds(80, 106, 30, 18);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("?????????");
		lblNewLabel_3.setFont(new Font("????", Font.BOLD, 24));
		lblNewLabel_3.setBounds(277, 13, 99, 36);
		add(lblNewLabel_3);

		JButton exitButton = new JButton("<—");
		exitButton.setBounds(0, 0, 60, 30);
		add(exitButton);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public void VoiceHelper() {
		SpeechUtility.createUtility(SpeechConstant.APPID + "=1a2e1109");
		VoiceSpeech voice = new VoiceSpeech();

		boolean isDo;
		do {
			voice.setMyVoiceListener(new MyVoiceListener() {
				@Override
				public void completion(String text) {
					String stuNo = "???";
					String stuName = "???";

					if (text.contains(stuNo)) {
						if (!text.isEmpty()) {
							text = text.substring(text.indexOf(stuNo) + 3, text.length() - 1);
							stuNoField.setText(text);
						}
					}
					if (text.contains(stuName)) {
						if (!text.isEmpty()) {
							text = text.substring(text.indexOf(stuName) + 3, text.length() - 1);
							stuNameField.setText(text);
						}
					}
				}
			});
			isDo = false;
		} while (isDo);
	}
}
