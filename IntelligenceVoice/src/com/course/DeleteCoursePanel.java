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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeleteCoursePanel extends JFrame {
	private JTextField courseNameField;
	private JTextField courseNoField;

	/**
	 * Create the panel.
	 */
	public DeleteCoursePanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);//改
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(8, 8, 8, 8));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLayout(null);

		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(100, 100, 800, 444);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("商品名称");
		//课程名称
		lblNewLabel_1.setBounds(20, 15, 72, 18);
		panel.add(lblNewLabel_1);

		courseNameField = new JTextField();
		courseNameField.setBounds(129, 15, 161, 24);
		panel.add(courseNameField);
		courseNameField.setColumns(10);


		//表格标题
		String [] columNames = {"商品编号","商品名称"};
		//表格数据列

		Object[][] cellData = CourseService.service.toArray(CourseService.service.queryAll());
		JTable table = new JTable(cellData, columNames);
		table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,null,null,null,null));
		table.setBounds(39, 146, 710, 244);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(5, 150, 600, 200);
		panel.add(scrollPane);

		table.setBounds(14, 161, 450, 125);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(250);
		table.getColumnModel().getColumn(1).setPreferredWidth(347);

		JButton deleteCourseButton = new JButton("删除");
		//删除
		deleteCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(getParent(), "请选择一条要删除的信息");
				}
				else {
					String courseNo = (String) table.getValueAt(row, 0);
					Integer rtn = CourseService.service.deleteCourse(courseNo);
					if (rtn > 0) {
						JOptionPane.showMessageDialog(null, "删除成功");
					}else {
						JOptionPane.showMessageDialog(null, "删除失败");
					}

					//表格的标题列
					String[] columnNames =  {"商品编号", "商品名称"};
					//表格的数据列
					Object[][] cellData = CourseService.service.toArray(CourseService.service.queryAll());
					//表格刷新
					TableModel tableModlel = new DefaultTableModel(cellData,columnNames);
					table.setModel( tableModlel);
					table.setBounds(14, 161, 450, 125);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getColumnModel().getColumn(0).setPreferredWidth(250);
					table.getColumnModel().getColumn(1).setPreferredWidth(347);
				}

			}
		});
		deleteCourseButton.setBounds(400, 91, 113, 27);
		panel.add(deleteCourseButton);

		JButton queryButton = new JButton("查询");
		//查询
		queryButton.addActionListener(new ActionListener() {
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
				TableModel tableModlel = new DefaultTableModel(cellData,columnNames);
				table.setModel( tableModlel);
				table.setBounds(14, 161, 450, 125);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getColumnModel().getColumn(0).setPreferredWidth(200);
				table.getColumnModel().getColumn(1).setPreferredWidth(397);
			}
		});
		queryButton.setBounds(400, 15, 113, 27);
		panel.add(queryButton);

		JLabel lblNewLabel = new JLabel("商品编号");
		//课程编号
		lblNewLabel.setBounds(20, 70, 72, 18);
		panel.add(lblNewLabel);

		courseNoField = new JTextField();
		courseNoField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String courseName=(String) table.getValueAt(row, 1);
				String courseNo=(String) table.getValueAt(row, 0);

				courseNoField.setText(courseNo);
				courseNameField.setText(courseName);
			}
		});
		courseNoField.setText("");
		courseNoField.setBounds(129, 70, 161, 24);
		panel.add(courseNoField);
		courseNoField.setColumns(10);

		//语音
		JButton voiceButton = new JButton("语音助手");
		voiceButton.setBounds(130, 100, 160, 27);
		panel.add(voiceButton);
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

		JLabel lblNewLabel_2 = new JLabel("商品删除");
		//删除课程
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 24));
		lblNewLabel_2.setBounds(350, 13, 134, 35);
		add(lblNewLabel_2);

		JButton exitButton = new JButton("<—");
		exitButton.setBounds(0, 0, 60, 30);
		add(exitButton);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteCoursePanel frame = new DeleteCoursePanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
