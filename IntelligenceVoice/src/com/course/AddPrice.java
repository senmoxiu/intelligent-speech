package com.course;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.iflytek.MyVoiceListener;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.view.VoiceSpeech;
import cs.service.ScoreService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddPrice extends JFrame {

	private JPanel contentPane;
	private JTextField stuNoField;
	private JTextField courseNoField;
	private JTextField scoreField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPrice frame = new AddPrice();
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
	public AddPrice() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("添加价格");
		//添加成绩
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(260, 13, 99, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("账号");
		//学号
		lblNewLabel_1.setBounds(200, 80, 140, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("商品号");
		//课程号
		lblNewLabel_2.setBounds(200, 130, 140, 30);
		contentPane.add(lblNewLabel_2);
		
		stuNoField = new JTextField();
		stuNoField.setBounds(280, 80, 200, 24);
		contentPane.add(stuNoField);
		stuNoField.setColumns(10);
		
		courseNoField = new JTextField();
		courseNoField.setBounds(280, 130, 200, 24);
		contentPane.add(courseNoField);
		courseNoField.setColumns(10);
		
		JButton btnNewButton = new JButton("添加");
		//添加
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stuNo = stuNoField.getText();
				String courseNo = courseNoField.getText();
				String score = scoreField.getText();
				
				if (courseNo == null || courseNo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请输入课程号");
					return;
				}
				if (score == null || (score.isEmpty())) {
					JOptionPane.showMessageDialog(null, "请输入商品价格");
					return;
				}
				
				if (stuNo == null || stuNo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请输入账号");
					return;
				}
				int mark = Integer.parseInt(score);
				
				Integer rtn2 = ScoreService.service.queryInformation1(stuNo, courseNo);
				
				if(rtn2 == 0) {
					Integer rtn = ScoreService.service.updateScore(stuNo, courseNo, mark);
					if (rtn > 0) {
						JOptionPane.showMessageDialog(null, "购买成功！");
					}else {
						JOptionPane.showMessageDialog(null, "购买失败，该消费者没有购买该商品");
					}
				}else {
					Integer rtn = ScoreService.service.addScore(stuNo, courseNo, mark);
					if (rtn > 0) {
						JOptionPane.showMessageDialog(null, "添加成功！");
					}else {
						JOptionPane.showMessageDialog(null, "添加失败，价格已存在");
					}
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


		//语音识别

		JButton voiceButton = new JButton("语音输入");
		voiceButton.setBounds(441, 280, 130, 30);
		contentPane.add(voiceButton);

		voiceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VoiceHelper();
			}
		});

		JLabel lblNewLabel_3 = new JLabel("价格");
		//成绩
		lblNewLabel_3.setBounds(200, 173, 140, 30);
		contentPane.add(lblNewLabel_3);
		
		scoreField = new JTextField();
		scoreField.setBounds(280, 173, 200, 24);
		contentPane.add(scoreField);
		scoreField.setColumns(10);


	}

	public void VoiceHelper() {
		SpeechUtility.createUtility(SpeechConstant.APPID + "=1a2e1109");
		VoiceSpeech voice = new VoiceSpeech();

		boolean isDo;
		do {
			voice.setMyVoiceListener(new MyVoiceListener() {
				@Override
				public void completion(String text) {
					String stuNo = "账号";
					String courseNo = "商品号";
					String courseScore = "价格";

					if (text.contains(stuNo)) {
						if (!text.isEmpty()) {

							text = text.substring(text.indexOf(stuNo) + 3, text.length() - 1);

							stuNoField.setText(text);

						}
					}
					if (text.contains(courseNo)) {
						if (!text.isEmpty()) {

							text = text.substring(text.indexOf(courseNo) + 4, text.length() - 1);

							courseNoField.setText(text);

						}
					}
					if (text.contains(courseScore)) {
						if (!text.isEmpty()) {

							text = text.substring(text.indexOf(courseScore) + 3, text.length() - 1);

							scoreField.setText(text);

						}
					}
				}
			});
			isDo = false;
		} while (isDo);
	}

}
