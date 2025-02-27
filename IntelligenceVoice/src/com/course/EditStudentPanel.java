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

public class EditStudentPanel extends JFrame {
	private JTextField studentNoField;
	private JTextField studentNameField;
	private JTextField stuClassField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditStudentPanel frame = new EditStudentPanel();
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
	public EditStudentPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 444);//改
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLayout(null);

		setLayout(null);

		//表格标题
		String [] columNames = {"账号","昵称","商店"};
		//表格数据列
		//Object[][] cellData = {{"李三","219970906","21软工8班"},{"王五","219970937","21软工9班"}};
		Object[][] cellData = StudentService.service.toArray(StudentService.service.queryAll());
		JTable table = new JTable(cellData, columNames);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String stuName=(String) table.getValueAt(row, 1);
				String stuNo=(String) table.getValueAt(row, 0);
				String stuClass=(String) table.getValueAt(row, 2);

				studentNoField.setText(stuNo);
				studentNameField.setText(stuName);
				stuClassField.setText(stuClass);
			}
		});

		table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,null,null,null,null));
		table.setBounds(39, 146, 710, 244);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(120, 60, 401, 125);
		add(scrollPane);

		table.setBounds(14, 161, 450, 100);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(124);
		table.getColumnModel().getColumn(1).setPreferredWidth(124);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);

		JPanel panel = new JPanel();
		panel.setBounds(40, 170, 462, 200);;
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("账号");
		//学号
		lblNewLabel.setBounds(120, 40, 72, 18);
		panel.add(lblNewLabel);

		studentNoField = new JTextField();
		studentNoField.setBounds(200, 40, 212, 24);
		panel.add(studentNoField);
		studentNoField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("昵称");
		//姓名
		lblNewLabel_1.setBounds(120, 80, 72, 18);
		panel.add(lblNewLabel_1);

		studentNameField = new JTextField();
		studentNameField.setBounds(200, 80, 212, 24);
		panel.add(studentNameField);
		studentNameField.setColumns(10);

		JButton editCourseButton = new JButton("修改");
		//修改
		editCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(getParent(), "请选择一条要修改的信息");
				}
				else {
//					String stuName=(String) table.getValueAt(row, 1);
					String stuNo=(String) table.getValueAt(row, 0);
//					String stuClass=(String) table.getValueAt(row, 2);

//					studentNoField.setText(stuNo);
//					studentNameField.setText(stuName);
//					stuClassField.setText(stuClass);

					String stuName = studentNameField.getText();
//					String stuNo = studentNoField.getText();
					String stuClass = stuClassField.getText();

					Integer rtn = StudentService.service.updateStudentByStudentNo(stuName, stuNo,stuClass);
					if (rtn > 0) {
						JOptionPane.showMessageDialog(null, "修改成功！");
					}else {
						JOptionPane.showMessageDialog(null, "修改失败");
					}
					//表格更新
					Object[][] cellData = StudentService.service.toArray(StudentService.service.queryAll());
					TableModel tableModel = new DefaultTableModel(cellData,columNames);
					table.setModel(tableModel);
					table.setBounds(100, 161, 450, 100);

					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getColumnModel().getColumn(0).setPreferredWidth(124);
					table.getColumnModel().getColumn(1).setPreferredWidth(124);
					table.getColumnModel().getColumn(2).setPreferredWidth(150);
				}
			}
		});
		editCourseButton.setBounds(80, 170, 113, 27);
		panel.add(editCourseButton);

		JLabel lblNewLabel_2 = new JLabel("商店");
		//班级
		lblNewLabel_2.setBounds(120, 125, 72, 18);
		panel.add(lblNewLabel_2);

		stuClassField = new JTextField();
		stuClassField.setBounds(200, 125, 212, 24);
		panel.add(stuClassField);
		stuClassField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("会员信息修改");
		//学生修改
		lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 24));
		lblNewLabel_3.setBounds(260, 0, 153, 43);
		add(lblNewLabel_3);

		//语音按钮
		JButton voiceButton = new JButton("语音助手");
		voiceButton.setBounds(340, 170, 113, 27);
		panel.add(voiceButton);

		JButton exitButton = new JButton("<—");
		exitButton.setBounds(0, 0, 60, 30);
		add(exitButton);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		//语音动作
		voiceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SpeechUtility.createUtility(SpeechConstant.APPID + "=1a2e1109");

				//开始语音输入
				VoiceSpeech voice = new VoiceSpeech();
				boolean isDo;
				do {
					voice.setMyVoiceListener(new MyVoiceListener() {
						@Override
						public void completion(String text) {
							String stuNo = "账号";//学号
							String stuName = "昵称";//姓名
							String stuClass = "商店";//班级
							if (text.contains(stuNo)) {
								String resultText = text.substring(text.indexOf(stuNo) + 2, text.length() - 1);
								studentNoField.setText(resultText);
							} else if (text.contains(stuName)){
								String resultText = text.substring(text.indexOf(stuName) + 2, text.length() - 1);
								studentNameField.setText(resultText);
							} else if (text.contains(stuClass)) {
								String resultText = text.substring(text.indexOf(stuClass) + 2, text.length() - 1);
								stuClassField.setText(resultText);
							}
						}
					});
					isDo = false;
				}while (isDo);

			}
		});
	}
}