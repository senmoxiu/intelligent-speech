package com.student;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import cs.entity.CourseBean;
import cs.service.CourseService;
import cs.service.DbService;
import cs.service.LoginService;
import cs.service.ScoreService;

import java.awt.datatransfer.StringSelection;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class SelectCoursePanel extends JPanel {
	String sql = "select course_name from course";
	List<CourseBean> list = DbService.dbUtil.genericQuery(sql, null, CourseBean.class);
	private JTextField courseNameField;
	private JTable table;
	private JTextField courseNoField;

	/**
	 * Create the panel.
	 */
	public SelectCoursePanel() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("选商品");
		//选课
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 30));
		lblNewLabel.setBounds(191, 13, 91, 35);
		add(lblNewLabel);

		//表格标题
		String [] columNames = {"课商品编号","商品名称"};
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
		table.setBounds(39, 146, 710, 244);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 54, 465, 144);
		add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBounds(14, 201, 461, 129);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("商品名称");
		//课程名称
		lblNewLabel_1.setBounds(14, 43, 60, 18);
		panel.add(lblNewLabel_1);

		courseNameField = new JTextField();
		courseNameField.setBounds(88, 40, 127, 24);
		panel.add(courseNameField);
		courseNameField.setColumns(10);

		JButton btnNewButton = new JButton("选商品");
		//选课
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(getParent(), "请选择一门商品");
				}
				else {
					String courseName=(String) table.getValueAt(row, 1);
					String courseNo=(String) table.getValueAt(row, 0);

					Integer rtn = ScoreService.service.addCourse(LoginService.userName, courseNo);
					if (rtn > 0) {
						JOptionPane.showMessageDialog(null, "购买商品成功！");
					}else {
						JOptionPane.showMessageDialog(null, "该课商品已购买，购买失败！");
					}
				}
			}
		});
		btnNewButton.setBounds(313, 83, 113, 27);
		panel.add(btnNewButton);

		JLabel lblNewLabel_2 = new JLabel("商品号");
		//课程号
		lblNewLabel_2.setBounds(239, 43, 72, 18);
		panel.add(lblNewLabel_2);

		courseNoField = new JTextField();
		courseNoField.setBounds(303, 40, 129, 24);
		panel.add(courseNoField);
		courseNoField.setColumns(10);

	}
}