package com.course;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import cs.service.ScoreService;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditScorePanel extends JFrame {
	private JTextField courseNoField;
	private JTextField courseNameField;
	private JTextField scoreField;
	private JTextField stuNoField;
	private JTextField stuNameField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditScorePanel frame = new EditScorePanel();
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
	public EditScorePanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 444);//改
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLayout(null);

		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(14, 42, 800, 500);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("商品编号");
		//课程编号
		lblNewLabel.setBounds(100, 30, 75, 18);
		panel.add(lblNewLabel);

		courseNoField = new JTextField();
		courseNoField.setEditable(false);
		courseNoField.setBounds(180, 30, 113, 24);
		panel.add(courseNoField);
		courseNoField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("商品名称");
		//课程名称
		lblNewLabel_1.setBounds(100, 80, 72, 18);
		panel.add(lblNewLabel_1);

		courseNameField = new JTextField();
		courseNameField.setEditable(false);
		courseNameField.setBounds(180, 75, 113, 24);
		panel.add(courseNameField);
		courseNameField.setColumns(10);

		//表格标题
		String [] columNames = {"账号","昵称","商品号","商品名称","价格","商店"};
		//表格数据列
		//	Object[][] cellData = {{"101","2199709","面向对象程序设计","哈哈","99"},{"102","2199809","数据库原理及应用","一样","89"}};
		Object[][] cellData = ScoreService.service.toArray(ScoreService.service.queryAll());
		JTable table = new JTable(cellData, columNames);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String stuName=(String) table.getValueAt(row, 1);
				String stuNo=(String) table.getValueAt(row, 0);
				String courseName=(String) table.getValueAt(row, 3);
				String courseNo=(String) table.getValueAt(row, 2);
				Integer score= (Integer) table.getValueAt(row, 4);

				stuNoField.setText(stuNo);
				stuNameField.setText(stuName);
				courseNameField.setText(courseName);
				courseNoField.setText(courseNo);
				scoreField.setText(Integer.toString(score));
			}
		});
		table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,null,null,null,null));
		table.setBounds(39, 146, 710, 100);


		table.setBounds(14, 161, 450, 96);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(66);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(80, 170, 469, 175);
		 panel.add(scrollPane);

		JButton deleteCourseButton = new JButton("修改");
		//修改
		deleteCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(getParent(), "请选择一条要修改的信息");
				}
				else {
					String studentNo=(String) table.getValueAt(row, 0);
					String courseNo=(String) table.getValueAt(row, 2);
					String score = scoreField.getText();

					int mark = Integer.valueOf(score).intValue();
					Integer rtn = ScoreService.service.updateScore(studentNo, courseNo, mark);
					if (rtn > 0) {
						JOptionPane.showMessageDialog(null, "修改成功！");
					}else {
						JOptionPane.showMessageDialog(null, "修改失败");
					}

					//表格更新
					Object[][] cellData = ScoreService.service.toArray(ScoreService.service.queryAll());
					TableModel tableModel = new DefaultTableModel(cellData,columNames);
					table.setModel(tableModel);
					table.setBounds(14, 161, 450, 96);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getColumnModel().getColumn(0).setPreferredWidth(100);
					table.getColumnModel().getColumn(1).setPreferredWidth(66);

				}

			}
		});
		deleteCourseButton.setBounds(400, 120, 135, 27);
		panel.add(deleteCourseButton);

		JLabel lblNewLabel_2 = new JLabel("账号");
		//学号
		lblNewLabel_2.setBounds(340, 30, 72, 18);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("昵称");
		//学生姓名
		lblNewLabel_3.setBounds(340, 80, 72, 18);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("价格");
		//成绩
		lblNewLabel_4.setBounds(100, 120, 72, 18);
		panel.add(lblNewLabel_4);

		scoreField = new JTextField();
		scoreField.setBounds(180, 120, 113, 24);
		panel.add(scoreField);
		scoreField.setColumns(10);

		stuNoField = new JTextField();
		stuNoField.setEditable(false);
		stuNoField.setBounds(400, 30, 138, 24);
		panel.add(stuNoField);
		stuNoField.setColumns(10);

		stuNameField = new JTextField();
		stuNameField.setEditable(false);
		stuNameField.setBounds(400, 75, 138, 24);
		panel.add(stuNameField);
		stuNameField.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("价格修改");
		//成绩修改
		lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 24));
		lblNewLabel_5.setBounds(280, 13, 115, 30);
		add(lblNewLabel_5);

		JButton exitButton = new JButton("<—");
		exitButton.setBounds(0, 0, 60, 30);
		add(exitButton);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

}