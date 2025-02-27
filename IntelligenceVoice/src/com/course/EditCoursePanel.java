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

public class EditCoursePanel extends JFrame {
	private JTextField courseNoField;
	private JTextField courseNameField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditCoursePanel frame = new EditCoursePanel();
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
	public EditCoursePanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);//改
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLayout(null);

		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(14, 186, 452, 185);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("商品编号");
		//课程编号
		lblNewLabel.setBounds(200, 70, 72, 18);
		panel.add(lblNewLabel);

		courseNoField = new JTextField();
		courseNoField.setBounds(350, 250, 195, 24);
		add(courseNoField);
		courseNoField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("商品名称");
		//课程名称
		lblNewLabel_1.setBounds(200, 110, 72, 18);
		panel.add(lblNewLabel_1);

		courseNameField = new JTextField();
		courseNameField.setBounds(350, 290, 195, 24);
		add(courseNameField);
		courseNameField.setColumns(10);


		//表格标题
		String [] columNames = {"商品编号","商品名称"};
		//表格数据列
		//Object[][] cellData = {{"101","面向对象程序设计"},{"102","数据库原理及应用"}};
		Object[][] cellData = CourseService.service.toArray(CourseService.service.queryAll());
		JTable table = new JTable(cellData, columNames);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String courseName=(String) table.getValueAt(row, 1);
				String courseNo=(String) table.getValueAt(row, 0);

				courseNoField.setText(courseNo);
				courseNameField.setText(courseName);
			}
		});
		table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,null,null,null,null));
		table.setBounds(150, 146, 710, 244);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(150, 75, 505, 140);
		add(scrollPane);

		table.setBounds(150, 140, 200, 96);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(202);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);

		JButton editCourseButton = new JButton("修改");
		//修改

		/*editCourseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
			       System.out.println(row);
			       //row==-1表示的是用户没有选中表格数据
			       if(row==-1) {
			        JOptionPane.showMessageDialog(getParent(), "请选择一条需要修改的课程");
			       }else {
			        String courseNo = (String) table.getValueAt(row, 0);
			        String courseName = (String) table.getValueAt(row, 1);

			        System.out.println(courseNo+","+courseName);
			        //数据加载到输入框
			        courseNoField.setText(courseNo);
			        courseNameField.setText(courseName);
			       }
			}
		});*/
		editCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(getParent(), "请选择一条要修改的信息");
				}
				else {
					//String courseName=(String) table.getValueAt(row, 1);
					String courseNo=(String) table.getValueAt(row, 0);

					//courseNoField.setText(courseNo);
					//courseNameField.setText(courseName);

					String courseName = courseNameField.getText();
//					String courseNo = courseNoField.getText();

					CourseService.service.updateCourseByCourseNo(courseName, courseNo);
					Integer rtn = CourseService.service.updateCourseByCourseNo(courseName, courseNo);
					if (rtn > 0) {
						JOptionPane.showMessageDialog(null, "修改成功！");
					}else {
						JOptionPane.showMessageDialog(null, "修改失败");
					}
					//表格更新
					Object[][] cellData = CourseService.service.toArray(CourseService.service.queryAll());
					TableModel tableModel = new DefaultTableModel(cellData,columNames);
					table.setModel(tableModel);
					table.setBounds(100, 140, 200, 96);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getColumnModel().getColumn(0).setPreferredWidth(202);
					table.getColumnModel().getColumn(1).setPreferredWidth(300);
				}
			}
		});
		editCourseButton.setBounds(150, 160, 113, 27);
		panel.add(editCourseButton);

		//语音输入
		JButton voiceButton = new JButton("语音输入");
		voiceButton.setBounds(500, 340, 113, 27);
		add(voiceButton);
		voiceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VoiceHelper();
			}
		});

		JLabel lblNewLabel_2 = new JLabel("商品修改");
		//课程修改
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 24));
		lblNewLabel_2.setBounds(340, 13, 108, 38);
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

	public void VoiceHelper() {
		SpeechUtility.createUtility(SpeechConstant.APPID + "=1a2e1109");
		VoiceSpeech voice = new VoiceSpeech();

		boolean isDo;
		do {
			voice.setMyVoiceListener(new MyVoiceListener() {
				@Override
				public void completion(String text) {
					String courseName = "商品名称";
					String courseNo = "商品编号";

					if (text.contains(courseName)) {
						if (!text.isEmpty()) {

							text = text.substring(text.indexOf(courseName) + 5, text.length() - 1);

							courseNameField.setText(text);

						}
					}
					if (text.contains(courseNo)) {
						if (!text.isEmpty()) {

							text = text.substring(text.indexOf(courseNo) + 5, text.length() - 1);

							courseNoField.setText(text);

						}
					}
					System.out.println(text + " 123");
				}
			});
			isDo = false;
		} while (isDo);
	}

}