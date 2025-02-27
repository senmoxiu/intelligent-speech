package com;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cs.service.LoginService;
import cs.service.ScoreService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditPassword extends JFrame {

    private JPanel contentPane;
    private JTextField userNameField;
    private JTextField oldPwdField;
    private JTextField newPwdField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EditPassword frame = new EditPassword();
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
    public EditPassword() {
        setTitle("修改密码");
        //改密
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 749, 456);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("修改密码");
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
        lblNewLabel.setBounds(146, 13, 113, 18);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("账号");
        //账号
        lblNewLabel_1.setBounds(33, 51, 72, 18);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("旧密码");
        //旧密码
        lblNewLabel_2.setBounds(33, 106, 72, 18);
        contentPane.add(lblNewLabel_2);

        userNameField = new JTextField();
        userNameField.setEditable(false);
        userNameField.setBounds(119, 51, 187, 24);
        contentPane.add(userNameField);
        userNameField.setColumns(10);

        oldPwdField = new JTextField();
        oldPwdField.setBounds(119, 103, 187, 24);
        contentPane.add(oldPwdField);
        oldPwdField.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("新密码");
        //新密码
        lblNewLabel_3.setBounds(33, 154, 72, 18);
        contentPane.add(lblNewLabel_3);

        newPwdField = new JTextField();
        newPwdField.setBounds(119, 151, 187, 24);
        contentPane.add(newPwdField);
        newPwdField.setColumns(10);

        //创建按钮组
        ButtonGroup group=new  ButtonGroup();

        userNameField.setText(LoginService.userName);
        JButton btnNewButton = new JButton("确定");
        //确认修改
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //获取用户输入的账号
                String account = userNameField.getText();
                //获取用户输入的密码
                String oldPassword = oldPwdField.getText();
                String newPassword = newPwdField.getText();
                System.out.println("666");
                Integer rtn = LoginService.service.updatePwd(account, oldPassword,newPassword);
                if (rtn > 0) {
                    JOptionPane.showMessageDialog(null, "修改成功！");
                }else {
                    JOptionPane.showMessageDialog(null, "旧密码错误，修改失败！");
                }
            }
        });
        btnNewButton.setBounds(64, 248, 113, 27);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("返回");
        //返回
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton_1.setBounds(266, 248, 113, 27);
        contentPane.add(btnNewButton_1);




    }
}