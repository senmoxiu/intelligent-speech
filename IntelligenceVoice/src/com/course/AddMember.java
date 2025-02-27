package com.course;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.iflytek.MyVoiceListener;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.view.VoiceSpeech;
import cs.service.StudentService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddMember extends JFrame {

	private JPanel contentPane;
	private JTextField stuNoField;
	private JTextField stuNameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMember frame = new AddMember();
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
	public AddMember() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("添加用户");
		//添加学生
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(260, 10, 99, 36);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("账号");
		//学号
		lblNewLabel_1.setBounds(200, 80, 140, 30);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("昵称");
		//姓名
		lblNewLabel_2.setBounds(200, 140, 72, 18);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("商店");
		//班级
		lblNewLabel_3.setBounds(200, 200, 160, 18);
		contentPane.add(lblNewLabel_3);

		stuNoField = new JTextField();
		stuNoField.setBounds(280, 80, 200, 24);//改
		contentPane.add(stuNoField);
		stuNoField.setColumns(10);

		stuNameField = new JTextField();
		stuNameField.setBounds(280, 140, 200, 24);
		contentPane.add(stuNameField);
		stuNameField.setColumns(10);

		String[] selection = {"请选择",  "一号店铺", "二号店铺", };
		JComboBox comboBox = new JComboBox(selection);
		comboBox.setBounds(280, 200, 200, 24);
		contentPane.add(comboBox);

		JButton btnNewButton = new JButton("添加");
		//添加
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clazz = (String) comboBox.getSelectedItem();
				String stuNo = stuNoField.getText();
				String stuName = stuNameField.getText();
				if (stuNo == null || stuNo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请输入账号");
					return;
				}
				if (stuName == null || stuName.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请输入昵称");
					return;
				}
				if (clazz == null || clazz.isEmpty() || clazz == "请选择") {
					JOptionPane.showMessageDialog(null, "请选择消费者消费的商店");
					return;
				}

				Integer rtn = StudentService.service.addStudent(stuNo, stuName,clazz);
				Integer rtn1 = StudentService.service.addStudent1(stuNo);
				if (rtn > 0 ) {
					JOptionPane.showMessageDialog(null, "添加成功！");
				}else {
					JOptionPane.showMessageDialog(null, "添加失败，账号已存在");
				}
			}
		});
		btnNewButton.setBounds(53, 280, 130, 30);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("返回");
		//返回
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(247, 280, 130, 30);
		contentPane.add(btnNewButton_1);

		//语音按钮
		JButton voiceButton = new JButton("语音输入");
		voiceButton.setBounds(441, 280, 130, 30);
		contentPane.add(voiceButton);
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
							if (text.contains(stuNo)) {
								String resultText = text.substring(text.indexOf(stuNo) + 2, text.length() - 1);
								stuNoField.setText(resultText);
							} else if (text.contains(stuName)){
								String resultText = text.substring(text.indexOf(stuName) + 2, text.length() - 1);
								stuNameField.setText(resultText);
							}
						}
					});
					isDo = false;
				}while (isDo);

			}
		});
	}
}
