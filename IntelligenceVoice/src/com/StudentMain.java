package com;

import com.student.QueryInformation;
import com.student.QueryScorePanel;
import com.student.QuerySelectCourse;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import com.student.SelectCoursePanel;

import cs.service.CourseService;
import cs.service.LoginService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentMain extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentMain frame = new StudentMain();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public StudentMain() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 675, 440);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("价格查询");
        menuBar.add(mnNewMenu);

        //表格标题
        String [] columNames = {"商品编号","商品名称"};
        //表格数据列
        //Object[][] cellData = {{"101","面向对象程序设计"},{"102","数据库原理及应用"}};
        Object[][] cellData = CourseService.service.toArray(CourseService.service.queryAll());
        JTable table = new JTable(cellData, columNames);
        table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,null,null,null,null));
        table.setBounds(39, 146, 710, 244);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 32, 446, 144);
        getContentPane().add(scrollPane);

        JMenuItem mntmNewMenuItem = new JMenuItem("查询价格");
        //查成绩
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //删除原界面
                contentPane.removeAll();
                contentPane.repaint();

                //添加界面
                QueryScorePanel selectCoursePanel=new QueryScorePanel();
                selectCoursePanel.setBounds(20, 20, 493, 360);
                contentPane.add(selectCoursePanel);

            }
        });
        mnNewMenu.add(mntmNewMenuItem);

        JMenu mnNewMenu_1 = new JMenu("选商品");
        //选课
        menuBar.add(mnNewMenu_1);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("选商品");
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(null, "你的账号是：" + LoginService.userName);
                //询问学号

                //删除原界面
                contentPane.removeAll();
                contentPane.repaint();

                //添加界面
                SelectCoursePanel selectCoursePanel=new SelectCoursePanel();
                selectCoursePanel.setBounds(20, 20, 493, 360);
                contentPane.add(selectCoursePanel);

            }
        });
        mnNewMenu_1.add(mntmNewMenuItem_1);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("查询已买");
        //查课
        mntmNewMenuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                QuerySelectCourse frame = new QuerySelectCourse();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                //删除原界面
                contentPane.removeAll();
                contentPane.repaint();

//				//添加界面
//				QuerySelectCoursepanel querySelectCoursepanel=new QuerySelectCoursepanel();
//				querySelectCoursepanel.setBounds(20, 20, 493, 360);
//				contentPane.add(querySelectCoursepanel);
            }
        });
        mnNewMenu_1.add(mntmNewMenuItem_2);

        JMenu mnNewMenu_2 = new JMenu("个人信息");
        //个人信息
        menuBar.add(mnNewMenu_2);

        JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("查看信息");
        //查询信息
        mntmNewMenuItem_2_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QueryInformation frame = new QueryInformation();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);

                //删除原界面
                contentPane.removeAll();
                contentPane.repaint();
            }
        });
        mnNewMenu_2.add(mntmNewMenuItem_2_1);

        JMenuItem mntmNewMenuItem_2_1_1 = new JMenuItem("修改密码");
        //改信息
        mntmNewMenuItem_2_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(null, "你的账号是：" + LoginService.userName);
                EditPassword frame = new EditPassword();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);

                //删除原界面
                contentPane.removeAll();
                contentPane.repaint();
            }
        });
        mnNewMenu_2.add(mntmNewMenuItem_2_1_1);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton = new JButton("退出");
        //退出
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton.setBounds(530, 327, 113, 27);
        contentPane.add(btnNewButton);


    }

    private static void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }
            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
}