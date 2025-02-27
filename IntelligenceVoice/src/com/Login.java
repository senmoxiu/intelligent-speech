package com;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import cs.entity.UserBean;
import cs.service.LoginService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;

public class Login {

    private JFrame frame;
    private JTextField accoutField;
    private JTextField PasswordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login window = new Login();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Login() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("智能语音商品管理系统：请登录");
        //Unicode学生管理系统登录
        frame.setBounds(100, 100, 666, 444);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setForeground(Color.YELLOW);
        panel.setBorder(new TitledBorder(null, "登录", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(20, 13, 586, 344);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("账号");//账号
        lblNewLabel.setBounds(150, 108, 45, 33);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("密码");//密码
        lblNewLabel_1.setBounds(150, 177, 38, 21);
        panel.add(lblNewLabel_1);

        accoutField = new JTextField();
        accoutField.setBounds(220, 112, 189, 24);
        panel.add(accoutField);
        accoutField.setColumns(10);

        PasswordField = new JTextField();
        PasswordField.setBounds(220, 175, 189, 24);
        panel.add(PasswordField);
        PasswordField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("类型");//类型
        lblNewLabel_2.setBounds(106, 236, 85, 41);
        panel.add(lblNewLabel_2);

        JRadioButton teacherRadioButton = new JRadioButton("商家");//教师
        teacherRadioButton.setActionCommand("teacher");
        teacherRadioButton.setBounds(209, 243, 157, 27);
        panel.add(teacherRadioButton);

        JRadioButton studentRadioButton = new JRadioButton("会员");//学生
        studentRadioButton.setActionCommand("student");
        studentRadioButton.setBounds(378, 243, 157, 27);
        panel.add(studentRadioButton);

        //创建按钮组
        ButtonGroup group=new  ButtonGroup();
        group.add(studentRadioButton);
        group.add(teacherRadioButton);

        //初始化方便测试
        accoutField.setText("admin");
        PasswordField.setText("123456");
        teacherRadioButton.setSelected(true);

        JButton loginNewButton = new JButton("登陆");//登录
        loginNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Map<String,String> students = new HashMap<String,String>();

                Map<String,String> teachers = new HashMap<String,String>();


                //获取用户的角色
                String type = group.getSelection().getActionCommand();
                //获取用户输入的账号
                String account = accoutField.getText();

                //获取用户输入的密码
                String password = PasswordField.getText();
                //判断用户角色的类型
                if(type.equals("student")) {
                    LoginService.userName = account;

                    UserBean userBean = LoginService.service.login(account, password, 2);
//					if (!(account.equals("admin") && password.equals("123456"))) {
                    if (userBean == null) {
                        JOptionPane.showMessageDialog(frame,"账号密码错误","错误提示",JOptionPane.INFORMATION_MESSAGE);
                        //账密错误提示
                    }else {
                        StudentMain studentMain = new StudentMain();
                        studentMain.setTitle("商品主页");//学生主页
                        studentMain.setVisible(true);
                        frame.setVisible(false);
                        studentMain.setLocationRelativeTo(null);
                    }

                    //Set<String> keys = students.keySet();
                    //boolean flag = false;
                    //增强for循环遍历key集合
					/*UserBean userBean = LoginService.service.login(account, password, 2);
					if(userBean==null) {
						JOptionPane.showMessageDialog(frame, "账号密码错误","错误提示",JOptionPane.INFORMATION_MESSAGE);
					}else {
						StudentMain studentMain = new StudentMain();
						studentMain.setTitle("学生页面");
						studentMain.setVisible(true);
						frame.setVisible(false);

					}*/

                }else {
                    //	Set<String> keys = teachers.keySet();
                    //	boolean flag = false;

                    //增强for循环遍历key集合
                    UserBean userBean = LoginService.service.login(account, password, 1);
                    if (userBean == null) {
                        JOptionPane.showMessageDialog(frame,"账号密码错误","错误提示",JOptionPane.INFORMATION_MESSAGE);
                        //账密错误提醒
                    }else {
                        TeacherMain teacherMain = new TeacherMain();
                        teacherMain.setTitle("商品主页");//教师主页
                        teacherMain.setVisible(true);
                        frame.setVisible(false);
                        teacherMain.setLocationRelativeTo(null);
                    }

                }

            }
        });
        loginNewButton.setBounds(250, 290, 100, 27);
        panel.add(loginNewButton);

        JLabel lblNewLabel_4 = new JLabel("商品管理系统");
        //成绩查询系统
        lblNewLabel_4.setForeground(Color.BLUE);
        lblNewLabel_4.setFont(new Font("宋体", Font.BOLD, 25));
        lblNewLabel_4.setBounds(230, 13, 261, 47);
        panel.add(lblNewLabel_4);


    }
}