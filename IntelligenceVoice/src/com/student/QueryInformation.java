package com.student;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cs.entity.ScoreVO;
import cs.service.LoginService;
import cs.service.ScoreService;
import cs.service.StudentService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class QueryInformation extends JFrame {

	private JPanel contentPane;
	private JTextField stuNoField;
	private JTextField stuNameField;
	private JTextField stuClassField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QueryInformation frame = new QueryInformation();
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
	public QueryInformation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("个人信息");
		//个人信息
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(143, 13, 92, 35);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("账号");
		//学号
		lblNewLabel_1.setBounds(37, 65, 72, 18);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("昵称");
		//姓名
		lblNewLabel_2.setBounds(37, 108, 72, 18);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("商店");
		//年级
		lblNewLabel_3.setBounds(37, 152, 72, 18);
		contentPane.add(lblNewLabel_3);

		stuNoField = new JTextField();
		stuNoField.setEditable(false); //可更改自己的班级信息和姓名 禁止更改自己的学号
		stuNoField.setBounds(96, 61, 183, 24);
		contentPane.add(stuNoField);
		stuNoField.setColumns(10);

		stuNameField = new JTextField();
		stuNameField.setBounds(96, 105, 183, 24);
		contentPane.add(stuNameField);
		stuNameField.setColumns(10);

		stuClassField = new JTextField();
		stuClassField.setBounds(96, 149, 183, 24);
		contentPane.add(stuClassField);
		stuClassField.setColumns(10);

		JButton btnNewButton = new JButton("修改");
		//修改
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stuName = stuNameField.getText();
				String stuNo = stuNoField.getText();
				String stuClass = stuClassField.getText();

				Integer rtn = StudentService.service.updateStudentByStudentNo(stuName, stuNo,stuClass);
				if (rtn > 0) {
					JOptionPane.showMessageDialog(null, "修改成功！");
				}else {
					JOptionPane.showMessageDialog(null, "修改失败");
				}
			}
		});
		btnNewButton.setBounds(37, 213, 113, 27);
		contentPane.add(btnNewButton);

		List<ScoreVO> list =ScoreService.service.queryInformationStuNO(LoginService.userName);
		for(ScoreVO scoreVO: list) {
			System.out.println(scoreVO.getStudentNo() + ", " + scoreVO.getStudentName() + ", "
					+ scoreVO.getClassInfo());
			String stuNo = scoreVO.getStudentNo();
			String stuName = scoreVO.getStudentName();
			String stuClass = scoreVO.getClassInfo();

			stuNoField.setText(stuNo);
			stuNameField.setText(stuName);
			stuClassField.setText(stuClass);
		}
		JButton btnNewButton_1 = new JButton("返回");
		//返回
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(263, 213, 113, 27);
		contentPane.add(btnNewButton_1);
	}
}