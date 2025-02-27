package com.student;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import cs.service.LoginService;
import cs.service.ScoreService;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Font;

public class QueryScorePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public QueryScorePanel() {
		setLayout(null);
		
		//表格标题
		String [] columNames = {"商品编号","商品名称"};
		//课程编号及名称
		//表格数据列
		//Object[][] cellData = {{"101","面向对象程序设计"},{"102","数据库原理及应用"}};
		Object[][] cellData =  ScoreService.service.toArrayCourse(ScoreService.service.querystudentCourseBystuNo(LoginService.userName));
		JTable table = new JTable(cellData, columNames);
		table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,null,null,null,null));
		table.setBounds(39, 146, 710, 244);
		table.setBounds(14, 161, 450, 96);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(14, 63, 464, 201);
		add(scrollPane);
		
		
		JButton queryButton = new JButton("查询");
		//查询
		queryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				
				if(row==-1) {
					JOptionPane.showMessageDialog(getParent(), "请选择一条要查询的信息");
				}
				else {
					String courseNo=(String) table.getValueAt(row, 0);
					String courseName=(String) table.getValueAt(row, 1);
					String stuNo=LoginService.userName;
					
					Integer rtn = ScoreService.service.queryInformation(stuNo, courseNo);
					
					if (rtn > 0) {
						JOptionPane.showConfirmDialog(null, courseName + "价格：" + rtn);
					}else {
						JOptionPane.showMessageDialog(null, "无价格！");
					}
			}
			}
		});
		queryButton.setBounds(365, 277, 113, 27);
		add(queryButton);
		
		JLabel lblNewLabel = new JLabel("价格查询");
		//查成绩
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 35));
		lblNewLabel.setBounds(145, 13, 198, 47);
		add(lblNewLabel);
		
		

	}
}
