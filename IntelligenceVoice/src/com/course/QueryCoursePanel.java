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
import cs.service.CourseService;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QueryCoursePanel extends JFrame {
	private JTextField courseNameField;
	private JTextField courseNoField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QueryCoursePanel frame = new QueryCoursePanel();
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
	public QueryCoursePanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);//改
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

		courseNameField = new JTextField();
		courseNameField.setBounds(150, 75, 187, 24);
		panel.add(courseNameField);
		courseNameField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("商品名称");
		//课程名称
		lblNewLabel_1.setBounds(50, 81, 72, 18);;
		panel.add(lblNewLabel_1);


		//表格标题
		String [] columNames = {"商品编号","商品名称"};
		//表格数据列
		//Object[][] cellData = {{"101","面向对象程序设计"},{"102","数据库原理及应用"}};
		Object[][] cellData = CourseService.service.toArray(CourseService.service.queryAll());
		JTable table = new JTable(cellData, columNames);
		table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,null,null,null,null));
		table.setBounds(39, 146, 710, 96);
		table.setBounds(14, 161, 450, 96);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);


		JButton queryCourseButton = new JButton("查询");
		//查询
		queryCourseButton.setBounds(360, 15, 113, 27);
		queryCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courseName = courseNameField.getText();
				String courseNo = courseNoField.getText();
				String courseClass = courseNoField.getText();
				//表格的标题列
				String[] columnNames =  {"商品编号", "商品名称"};
				Object[][] cellData;
				if (!courseName.isEmpty() ) {
					cellData = CourseService.service.toArray(CourseService.service.queryCourseByCourseName(courseName));
				}
				else if(!courseNo.isEmpty()){
					cellData = CourseService.service.toArray(CourseService.service.queryCourseByCourseNo(courseNo));
				}
				else {
					cellData = CourseService.service.toArray(CourseService.service.queryAll());
				}

				//表格刷新
				TableModel tableModlel = new DefaultTableModel(cellData,columNames);
				table.setModel( tableModlel);

				table.setBounds(14, 161, 450, 100);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getColumnModel().getColumn(0).setPreferredWidth(200);
				table.getColumnModel().getColumn(1).setPreferredWidth(250);
			}
		});
		panel.add(queryCourseButton);

		JLabel lblNewLabel = new JLabel("商品编号");
		//课程编号
		lblNewLabel.setBounds(50, 13, 72, 18);
		panel.add(lblNewLabel);

		courseNoField = new JTextField();
		courseNoField.setBounds(150, 13, 187, 24);
		panel.add(courseNoField);
		courseNoField.setColumns(10);


		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(45, 120, 440, 200);
		panel.add(scrollPane);

		table.setBounds(14, 161, 450, 90);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JLabel lblNewLabel_2 = new JLabel("查询商品");
		//查询课程
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 24));
		lblNewLabel_2.setBounds(307, 13, 134, 35);;
		add(lblNewLabel_2);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(237);

		//语音按钮
		JButton voiceButton = new JButton("语音助手");
		voiceButton.setBounds(360, 75, 113, 27);
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
							String courseName = "商品名称";//课程名称
							String courseNo = "商品编号";//课程编号
							if (text.contains(courseName)) {
								String resultText = text.substring(text.indexOf(courseName) + 4, text.length() - 1);
								courseNameField.setText(resultText);
							} else if (text.contains(courseNo)){
								String resultText = text.substring(text.indexOf(courseName) + 5, text.length() - 1);
								courseNoField.setText(resultText);
							}
						}
					});
					isDo = false;
				}while (isDo);
			}
		});

	}
}