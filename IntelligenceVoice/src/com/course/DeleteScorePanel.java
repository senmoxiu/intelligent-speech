package com.course;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import cs.service.ScoreService;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeleteScorePanel extends JFrame {
	private JTextField courseNoField;
	private JTextField courseNameField;
	private JTextField scoreField;
	private JTextField stuNoield;
	private JTextField stuNameField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteScorePanel frame = new DeleteScorePanel();
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
	public DeleteScorePanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);//改
		JPanel contentPane = new JPanel();
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLayout(null);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(14, 45, 497, 137);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("商品编号");
		//课程编号
		lblNewLabel.setBounds(100, 13, 75, 18);
		panel.add(lblNewLabel);

		courseNoField = new JTextField();
		courseNoField.setEditable(false);
		courseNoField.setBounds(170, 10, 113, 24);
		panel.add(courseNoField);
		courseNoField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("商品名称");
		//课程名称
		lblNewLabel_1.setBounds(100, 65, 72, 18);
		panel.add(lblNewLabel_1);

		courseNameField = new JTextField();
		courseNameField.setEditable(false);
		courseNameField.setBounds(170, 62, 113, 24);
		panel.add(courseNameField);
		courseNameField.setColumns(10);

		//表格标题
		String [] columNames = {"账号","昵称","商品号","商品名称","价格","商店"};
		//表格数据列
		//	Object[][] cellData = {{"101","2199709","面向对象程序设计","哈哈","99"},{"102","2199809","数据库原理及应用","一样","89"}};
		Object[][] cellData = ScoreService.service.toArray(ScoreService.service.queryAll());
		JTable table = new JTable(cellData, columNames);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String stuName=(String) table.getValueAt(row, 1);
				String stuNo=(String) table.getValueAt(row, 0);
				String courseName=(String) table.getValueAt(row, 3);
				String courseNo=(String) table.getValueAt(row, 2);
				Integer score= (Integer) table.getValueAt(row, 4);

				stuNoield.setText(stuNo);
				stuNameField.setText(stuName);
				courseNameField.setText(courseName);
				courseNoField.setText(courseNo);
				scoreField.setText(Integer.toString(score));
			}
		});

		table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,null,null,null,null));
		table.setBounds(40, 146, 710, 100);

		table.setBounds(14, 161, 450, 96);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(97);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(130, 230, 500, 200);
		add(scrollPane);

		JButton deleteCourseButton = new JButton("删除");
		//删除
		deleteCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(getParent(), "请选择一条要删除的信息");
				}
				else {
					String studentNo=(String) table.getValueAt(row, 0);
					String courseNo=(String) table.getValueAt(row, 2);

					Integer rtn = ScoreService.service.deleteScore(studentNo, courseNo);
					if (rtn > 0) {
						JOptionPane.showMessageDialog(null, "删除成功！");
					}else {
						JOptionPane.showMessageDialog(null, "删除失败");
					}

					//表格更新
					Object[][] cellData = ScoreService.service.toArray(ScoreService.service.queryAll());
					TableModel tableModel = new DefaultTableModel(cellData,columNames);
					table.setModel(tableModel);
					table.setBounds(800, 161, 450, 96);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getColumnModel().getColumn(0).setPreferredWidth(100);
					table.getColumnModel().getColumn(1).setPreferredWidth(97);

				}

			}
		});
		deleteCourseButton.setBounds(500, 150, 113, 27);
		add(deleteCourseButton);

		JLabel lblNewLabel_2 = new JLabel("账号");
		//学号
		lblNewLabel_2.setBounds(420, 13, 72, 18);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("昵称");
		//学生姓名
		lblNewLabel_3.setBounds(420, 65, 72, 18);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("价格");
		//成绩
		lblNewLabel_4.setBounds(100, 110, 72, 18);
		panel.add(lblNewLabel_4);

		scoreField = new JTextField();
		scoreField.setEditable(false);
		scoreField.setBounds(170, 110, 113, 24);
		panel.add(scoreField);
		scoreField.setColumns(10);

		stuNoield = new JTextField();
		stuNoield.setEditable(false);
		stuNoield.setBounds(500, 60, 138, 24);
		add(stuNoield);
		stuNoield.setColumns(10);

		stuNameField = new JTextField();
		stuNameField.setEditable(false);
		stuNameField.setBounds(500, 105, 138, 24);
		add(stuNameField);
		stuNameField.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("价格删除");
		//删除成绩
		lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 24));
		lblNewLabel_5.setBounds(350, 13, 132, 34);
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