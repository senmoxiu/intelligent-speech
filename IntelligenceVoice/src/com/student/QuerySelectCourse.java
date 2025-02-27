package com.student;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import cs.service.LoginService;
import cs.service.ScoreService;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class QuerySelectCourse extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuerySelectCourse frame = new QuerySelectCourse();
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
	public QuerySelectCourse() {
		setTitle("购买商品");
		//已选课程
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//表格标题
		String [] columNames = {"商品编号","商品名称"};
		//表格数据列
		//Object[][] cellData = {{"101","面向对象程序设计"},{"102","数据库原理及应用"}};
		Object[][] cellData = ScoreService.service.toArrayCourse(ScoreService.service.querystudentCourseBystuNo(LoginService.userName));
		JTable table = new JTable(cellData, columNames);
		table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,null,null,null,null));
		table.setBounds(39, 146, 710, 244);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(14, 55, 498, 145);
		contentPane.add(scrollPane);

		JButton btnNewButton = new JButton("返回");
		//返回
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(290, 213, 113, 27);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("已购买商品");
		//已选课程
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//表格标题
				String [] columNames = {"商品编号","商品名称"};
				//表格数据列
				//Object[][] cellData = {{"101","面向对象程序设计"},{"102","数据库原理及应用"}};
				Object[][] cellData = ScoreService.service.toArrayCourse(ScoreService.service.querystudentCourseBystuNo(LoginService.userName));
				TableModel tableModel = new DefaultTableModel(cellData,columNames);
				table.setModel(tableModel);

//				JTable table = new JTable(cellData, columNames);
//				table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,null,null,null,null));
//				table.setBounds(39, 146, 710, 244);
//
//				JScrollPane scrollPane = new JScrollPane(table);
//				scrollPane.setBounds(14, 55, 498, 145);
//				contentPane.add(scrollPane);
			}
		});
		btnNewButton_1.setBounds(14, 213, 113, 27);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("未选商品");
		//未选课程
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//表格标题
				String [] columNames = {"商品编号","商品名称"};
				//表格数据列
				//Object[][] cellData = {{"101","面向对象程序设计"},{"102","数据库原理及应用"}};
//				Object[][] cellData = ScoreService.service.toArrayCourse(ScoreService.service.querystudentNotCourseBystuNo(LoginService.userName));
				Object[][] cellData = ScoreService.service.toArrayCourse(ScoreService.service.querystudentNotCourseBystuNo(LoginService.userName));
				TableModel tableModel = new DefaultTableModel(cellData,columNames);
				table.setModel(tableModel);
//				JTable table = new JTable(cellData, columNames);
//				table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,null,null,null,null));
//				table.setBounds(39, 146, 710, 244);
//
//				JScrollPane scrollPane = new JScrollPane(table);
//				scrollPane.setBounds(14, 55, 498, 145);
//				contentPane.add(scrollPane);
			}
		});
		btnNewButton_2.setBounds(154, 213, 113, 27);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("退回商品");
		//取消选课
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//deleteScore
				int row = table.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(getParent(), "请选择一条取消的信息");
				}
				else {
					String courseNo=(String) table.getValueAt(row, 0);

					//courseNoField.setText(courseNo);
					//courseNameField.setText(courseName);

//				    String courseName = courseNameField.getText();
//					String courseNo = courseNoField.getText();


					Integer rtn = ScoreService.service.deleteScore(LoginService.userName, courseNo);
					if (rtn > 0) {
						JOptionPane.showMessageDialog(null, "退货成功！");
					}else {
						JOptionPane.showMessageDialog(null, "退货失败");
					}
					//表格更新
					Object[][] cellData = ScoreService.service.toArrayCourse(ScoreService.service.querystudentCourseBystuNo(LoginService.userName));
					TableModel tableModel = new DefaultTableModel(cellData,columNames);
					table.setModel(tableModel);
				}
			}
		});
		btnNewButton_3.setBounds(421, 213, 113, 27);
		contentPane.add(btnNewButton_3);

		JLabel lblNewLabel = new JLabel("查询商品");
		//查询选课
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 30));
		lblNewLabel.setBounds(202, 0, 137, 47);
		contentPane.add(lblNewLabel);
	}
}