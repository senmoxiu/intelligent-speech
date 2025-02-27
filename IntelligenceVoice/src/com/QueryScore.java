package com;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.iflytek.MyVoiceListener;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.view.VoiceSpeech;
import cs.service.ScoreService;
import cs.service.StudentService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QueryScore extends JFrame {

    private JPanel contentPane;
    private JTextField courseNoField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QueryScore frame = new QueryScore();
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
    public QueryScore() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 774, 442);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("价格查询");
        //成绩查询
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 30));
        lblNewLabel.setBounds(281, 0, 160, 52);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_2 = new JLabel("商店");
        //班级
        lblNewLabel_2.setBounds(265, 65, 72, 18);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("商品号");
        //课程号
        lblNewLabel_3.setBounds(28, 65, 72, 18);
        contentPane.add(lblNewLabel_3);

        courseNoField = new JTextField();
        courseNoField.setBounds(91, 62, 160, 24);
        contentPane.add(courseNoField);
        courseNoField.setColumns(10);

        String[] selection = {"请选择","一号店铺", "二号店铺"};
        //选择班级
        JComboBox comboBox = new JComboBox(selection);
        comboBox.setBounds(317, 62, 151, 24);
        contentPane.add(comboBox);

        String [] columNames = {"商店", "商品名", "最高价", "最低价", "平均价"};
        //班级，课程名，最高分，最低分，平均分
        Object[][] cellData = ScoreService.service.toArrayVO(ScoreService.service.countScore2(null));
        JTable table = new JTable(cellData, columNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(34, 111, 683, 260);

        contentPane.add(scrollPane);
        JButton btnNewButton = new JButton("查询");
        //查询
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String classInfo = (String) comboBox.getSelectedItem();
                String courseNo = courseNoField.getText();

                if(!classInfo.isEmpty() && classInfo != "请选择") {
                    String [] columNames = {"商店", "商品名", "最高价", "最低价", "平均价"};
                    //选择查询：班级，课程名，最高分，最低分，平均分
                    Object[][] cellData = ScoreService.service.toArrayVO(ScoreService.service.countScore2(classInfo));
                    JTable table = new JTable(cellData, columNames);
                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setBounds(34, 111, 683, 260);
                    contentPane.add(scrollPane);
                }else if(!courseNo.isEmpty()) {
                    String [] columNames = {"商店", "商品", "最高价", "最低价", "平均价"};
                    Object[][] cellData = ScoreService.service.toArrayVO(ScoreService.service.countScore1(courseNo));
                    JTable table = new JTable(cellData, columNames);
                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setBounds(34, 111, 683, 260);
                    contentPane.add(scrollPane);
                }else {
                    JOptionPane.showMessageDialog(getParent(), "请选择输入商品号或选择商店的信息");
                    //选择输入课程号或选择班级信息
                }
            }
        });

        btnNewButton.setBounds(507, 61, 113, 27);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("返回");
        //返回
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });
        btnNewButton_1.setBounds(634, 61, 113, 27);
        contentPane.add(btnNewButton_1);
        String [] columNames1 = {"商店", "商品名", "昂贵", "贵", "中等", "一般", "便宜"};
        //评级：优，良，中，及格，不及格
        Object[][] cellData1 = ScoreService.service.toArrayVO(ScoreService.service.countScore1(null));



        //语音输入

        JButton btnNewButton_2 = new JButton("语音输入");
        btnNewButton_2.setBounds(634, 30, 113, 27);
        contentPane.add(btnNewButton_2);

        btnNewButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VoiceHelper();
            }
        });

    }

    public void VoiceHelper() {
        SpeechUtility.createUtility(SpeechConstant.APPID + "=1a2e1109");
        VoiceSpeech voice = new VoiceSpeech();

        boolean isDo;
        do {
            voice.setMyVoiceListener(new MyVoiceListener() {
                @Override
                public void completion(String text) {
                    String courseNo = "商品号";

                    if (text.contains(courseNo)) {
                        if (!text.isEmpty()) {

                            text = text.substring(text.indexOf(courseNo) + 4, text.length() - 1);

                            courseNoField.setText(text);

                        }
                    }
                }
            });
            isDo = false;
        } while (isDo);
    }

}