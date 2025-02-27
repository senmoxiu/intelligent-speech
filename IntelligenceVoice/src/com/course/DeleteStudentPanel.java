package com.course;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.iflytek.MyVoiceListener;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.view.VoiceSpeech;
import cs.service.StudentService;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeleteStudentPanel extends JFrame {
    private JTextField stuNoField;
    private JTextField stuNameField;
    private JTextField stuClassField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DeleteStudentPanel frame = new DeleteStudentPanel();
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
    public DeleteStudentPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);//改
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLayout(null);

        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(24, 48, 403, 144);
        add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("账号");
        //学号
        lblNewLabel.setBounds(170, 13, 30, 18);
        panel.add(lblNewLabel);

        stuNoField = new JTextField();
        stuNoField.setBounds(250, 10, 178, 24);
        panel.add(stuNoField);
        stuNoField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("昵称");
        //姓名
        lblNewLabel_1.setBounds(170, 65, 72, 18);
        panel.add(lblNewLabel_1);

        stuNameField = new JTextField();
        stuNameField.setBounds(250, 62, 178, 24);
        panel.add(stuNameField);
        stuNameField.setColumns(10);

        String[] selection = {"请选择","一号店铺", "二号店铺"};
        JComboBox comboBox = new JComboBox(selection);
        comboBox.setBounds(250, 108, 140, 24);
        panel.add(comboBox);

        //表格标题
        String[] columNames = {"账号", "昵称", "商店"};
        //表格数据列
        //Object[][] cellData = {{"李三","219970906","21软工8班"},{"王五","219970937","21软工9班"}};
        Object[][] cellData = StudentService.service.toArray(StudentService.service.queryAll());
        JTable table = new JTable(cellData, columNames);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.getSelectedRow();
                        String stuName = (String) table.getValueAt(row, 1);
                        String stuNo = (String) table.getValueAt(row, 0);

                        stuNoField.setText(stuNo);
                        stuNameField.setText(stuName);
                    }
                });
            }
        });

        table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        table.setBounds(39, 146, 710, 244);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                String stuName = (String) table.getValueAt(row, 1);
                String stuNo = (String) table.getValueAt(row, 0);
                String stuClass = (String) table.getValueAt(row, 2);

                stuNoField.setText(stuNo);
                stuNameField.setText(stuName);
                stuClassField.setText(stuClass);
            }
        });
        scrollPane.setBounds(40, 212, 700, 200);
        add(scrollPane);

        table.setBounds(14, 161, 450, 125);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(232);
        table.getColumnModel().getColumn(1).setPreferredWidth(232);
        table.getColumnModel().getColumn(2).setPreferredWidth(232);

        JButton deleteStudentButton = new JButton("删除");
        //删除
        deleteStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(getParent(), "请选择一条要删除的信息");
                } else {
                    String stuNo = (String) table.getValueAt(row, 0);
                    Integer rtn = StudentService.service.deleteStudent(stuNo);
                    if (rtn > 0) {
                        JOptionPane.showMessageDialog(null, "删除成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "删除失败");
                    }

                    //表格的标题列
                    String[] columNames = {"账号", "昵称", "商店"};
                    //表格的数据列
                    Object[][] cellData = StudentService.service.toArray(StudentService.service.queryAll());
                    //表格刷新
                    TableModel tableModlel = new DefaultTableModel(cellData, columNames);
                    table.setModel(tableModlel);
                    table.setBounds(14, 161, 450, 125);
                    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    table.getColumnModel().getColumn(0).setPreferredWidth(232);
                    table.getColumnModel().getColumn(1).setPreferredWidth(232);
                    table.getColumnModel().getColumn(2).setPreferredWidth(232);
                }

            }
        });
        deleteStudentButton.setBounds(500, 100, 90, 27);
        add(deleteStudentButton);

        JButton queryButton = new JButton("查询");
        //查询
        queryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String stuName = stuNameField.getText();
                String stuNo = stuNoField.getText();
                String stuClass = (String) comboBox.getSelectedItem();
                //表格的标题列
                String[] columNames = {"账号", "昵称", "商店"};
                Object[][] cellData;
                if (!stuName.isEmpty()) {
                    cellData = StudentService.service.toArray(StudentService.service.queryStudentByStudentName(stuName));
                } else if (!stuNo.isEmpty()) {
                    cellData = StudentService.service.toArray(StudentService.service.queryStudentByStudentNo(stuNo));
                } else if (!stuClass.isEmpty()) {
                    cellData = StudentService.service.toArray(StudentService.service.queryStudentByStudentClass(stuClass));
                } else {
                    cellData = StudentService.service.toArray(StudentService.service.queryAll());
                }

                //表格刷新
                TableModel tableModlel = new DefaultTableModel(cellData, columNames);
                table.setModel(tableModlel);

                table.setBounds(14, 161, 450, 125);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                table.getColumnModel().getColumn(0).setPreferredWidth(232);
                table.getColumnModel().getColumn(1).setPreferredWidth(232);
                table.getColumnModel().getColumn(2).setPreferredWidth(232);
            }
        });
        queryButton.setBounds(500, 50, 90, 27);
        add(queryButton);

        JLabel lblNewLabel_2 = new JLabel("商店");
        //班级
        lblNewLabel_2.setBounds(170, 111, 30, 18);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("会员删除");
        //学生删除
        lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 24));
        lblNewLabel_3.setBounds(350, 13, 139, 33);
        add(lblNewLabel_3);


        //语音按钮
        JButton voiceButton = new JButton("语音输入");
        voiceButton.setBounds(500, 150, 90, 27);
        add(voiceButton);
        //语音动作
        voiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpeechUtility.createUtility(SpeechConstant.APPID + "=1a2e1109");

                //开始语音输入
                VoiceSpeech voice = new VoiceSpeech();
                boolean isDo;
                do {
                    voice.setMyVoiceListener(new MyVoiceListener() {
                        @Override
                        public void completion(String text) {
                            String stuNo = "账号";//学号
                            String stuName = "昵称";//姓名
                            if (text.contains(stuNo)) {
                                String resultText = text.substring(text.indexOf(stuNo) + 2, text.length() - 1);
                                stuNoField.setText(resultText);
                            } else if (text.contains(stuName)) {
                                String resultText = text.substring(text.indexOf(stuName) + 2, text.length() - 1);
                                stuNameField.setText(resultText);
                            }
                        }
                    });
                    isDo = false;
                } while (isDo);

            }
        });
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