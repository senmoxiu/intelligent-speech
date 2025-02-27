package com.course;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.TeacherMain;
import com.iflytek.MyVoiceListener;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.view.VoiceSpeech;
import cs.service.CourseService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCourse extends JFrame {

	private JPanel contentPane;
	private JTextField courseNoField;
	private JTextField courseNameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCourse frame = new AddCourse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddCourse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("商品添加");
		//加课
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(260, 13, 99, 36);;
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("商品编号");
		//课程编号
		lblNewLabel_1.setBounds(200, 80, 140, 30);//改
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("商品名称");
		//课程名称
		lblNewLabel_2.setBounds(200, 150, 72, 18);
		contentPane.add(lblNewLabel_2);

		courseNoField = new JTextField();
		courseNoField.setBounds(280, 80, 200, 24);
		contentPane.add(courseNoField);
		courseNoField.setColumns(10);

		courseNameField = new JTextField();
		courseNameField.setBounds(280, 150, 200, 24);
		contentPane.add(courseNameField);
		courseNameField.setColumns(10);

		JButton btnNewButton = new JButton("添加");
		//添加
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courseNo = courseNoField.getText();
				String courseName = courseNameField.getText();
				if (courseNo == null || courseNo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请输入商品号");
					return;
				}
				if (courseName == null || courseName.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请输入商品名");
					return;
				}
				Integer rtn = CourseService.service.addCourse(courseNo, courseName);
				CourseService.service.deleteCourse1(courseNo);
				if (rtn > 0) {
					JOptionPane.showMessageDialog(null, "购买成功！");
				}else {
					JOptionPane.showMessageDialog(null, "购买失败，商品号已购买");
				}
			}
		});
		btnNewButton.setBounds(53, 250, 130, 30);
		contentPane.add(btnNewButton);


		JButton btnNewButton_1 = new JButton("返回");
		//返回
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherMain.a = true;
				dispose();
			}
		});


		btnNewButton_1.setBounds(247, 250, 130, 30);
		contentPane.add(btnNewButton_1);

		//语音输入

		JButton btnNewButton_2 = new JButton("语音输入");
		btnNewButton_2.setBounds(441, 250, 130, 30);
		contentPane.add(btnNewButton_2);

		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VoiceHelper();
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
