package com;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.course.AddCourse;
import com.course.AddPrice;
import com.course.AddMember;
import com.course.DeleteCoursePanel;
import com.course.DeleteScorePanel;
import com.course.DeleteStudentPanel;
import com.course.EditCoursePanel;
import com.course.EditScorePanel;
import com.course.EditStudentPanel;
import com.course.QueryCoursePanel;
import com.course.QueryStudentPanel;
import com.iflytek.MyVoiceListener;
import com.iflytek.VoiceTest;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.view.VoiceSpeech;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeacherMain extends JFrame {

	public static boolean a = true;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TeacherMain frame = new TeacherMain();
					frame.setLocationRelativeTo(null);
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
	public TeacherMain() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(630, 320, 735, 443);


				JMenuBar menuBar = new JMenuBar();
				menuBar.setToolTipText("");
				menuBar.setMargin(new Insets(0, 0, 2, 0));
				setJMenuBar(menuBar);

				JMenu mnNewMenu = new JMenu("商品管理");
				//课程管理
				menuBar.add(mnNewMenu);

				JMenuItem mntmNewMenuItem = new JMenuItem("商品新增");
				//加课
				mntmNewMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						AddCourse frame = new AddCourse();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
/*
				//删除原界面
 			    contentPane.removeAll();
				contentPane.repaint();

				//添加界面
				AddCoursePanel addCoursePanel=new AddCoursePanel();
				addCoursePanel.setBounds(20, 20, 493, 360);
				contentPane.add(addCoursePanel);
	*/
					}
				});
				mnNewMenu.add(mntmNewMenuItem);

				JMenuItem mntmNewMenuItem_1 = new JMenuItem("商品查询");
				//查课
				mntmNewMenuItem_1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						//删除原界面
						contentPane.removeAll();
						contentPane.repaint();

						//添加界面
						QueryCoursePanel frame = new QueryCoursePanel();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
					}
				});
				mnNewMenu.add(mntmNewMenuItem_1);

				JMenuItem mntmNewMenuItem_6 = new JMenuItem("商品删除");
				//删课
				mntmNewMenuItem_6.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//删除原界面
						contentPane.removeAll();
						contentPane.repaint();

						//添加界面
						DeleteCoursePanel frame = new DeleteCoursePanel();
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
					}
				});
				mnNewMenu.add(mntmNewMenuItem_6);

				JMenuItem mntmNewMenuItem_8 = new JMenuItem("商品修改");
				//改课
				mntmNewMenuItem_8.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//删除原界面
						contentPane.removeAll();
						contentPane.repaint();

						//添加界面
						EditCoursePanel frame = new EditCoursePanel();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
					}
				});
				mnNewMenu.add(mntmNewMenuItem_8);

				JMenu mnNewMenu_1 = new JMenu("会员管理");
				//学生管理
				menuBar.add(mnNewMenu_1);

				JMenuItem mntmNewMenuItem_2 = new JMenuItem("会员新增");
				//加学生
				mntmNewMenuItem_2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						AddMember frame = new AddMember();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);

						//删除原界面
						contentPane.removeAll();
						contentPane.repaint();
/*
				//添加界面
				AddStudentPanel addStudentCoursePanel=new AddStudentPanel();
				addStudentCoursePanel.setBounds(0, 0, 493, 460);
				contentPane.add(addStudentCoursePanel);
*/
					}
				});
				mnNewMenu_1.add(mntmNewMenuItem_2);

				JMenuItem mntmNewMenuItem_3 = new JMenuItem("会员查询");
				//查学生
				mntmNewMenuItem_3.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//删除原界面
						contentPane.removeAll();
						contentPane.repaint();

						//添加界面
						QueryStudentPanel frame = new QueryStudentPanel();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
					}
				});
				mnNewMenu_1.add(mntmNewMenuItem_3);

				JMenuItem mntmNewMenuItem_7 = new JMenuItem("会员删除");
				//删学生
				mntmNewMenuItem_7.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//删除原界面
						contentPane.removeAll();
						contentPane.repaint();

						//添加界面
						DeleteStudentPanel frame = new DeleteStudentPanel();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
					}
				});
				mnNewMenu_1.add(mntmNewMenuItem_7);

				JMenuItem mntmNewMenuItem_9 = new JMenuItem("会员修改");
				//改学生
				mntmNewMenuItem_9.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//删除原界面
						contentPane.removeAll();
						contentPane.repaint();

						//添加界面
						EditStudentPanel frame = new EditStudentPanel();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
					}
				});
				mnNewMenu_1.add(mntmNewMenuItem_9);

				JMenu mnNewMenu_2 = new JMenu("价格管理");
				//成绩管理
				menuBar.add(mnNewMenu_2);

				JMenuItem mntmNewMenuItem_4 = new JMenuItem("价格新增");
				//加成绩
				mntmNewMenuItem_4.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						AddPrice frame = new AddPrice();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
						//删除原界面
						contentPane.removeAll();
						contentPane.repaint();
/*
				//添加界面
				AddScorePanel addScorePanel=new AddScorePanel();
				addScorePanel.setBounds(0, 0, 493, 460);
				contentPane.add(addScorePanel);
				*/
					}
				});
				mnNewMenu_2.add(mntmNewMenuItem_4);

				JMenuItem mntmNewMenuItem_10 = new JMenuItem("价格修改");
				//改成绩
				mntmNewMenuItem_10.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//删除原界面
						contentPane.removeAll();
						contentPane.repaint();

						//添加界面
						EditScorePanel frame = new EditScorePanel();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
					}
				});
				mnNewMenu_2.add(mntmNewMenuItem_10);

				JMenuItem mntmNewMenuItem_11 = new JMenuItem("价格删除");
				//删成绩
				mntmNewMenuItem_11.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//删除原界面
						contentPane.removeAll();
						contentPane.repaint();

						//添加界面
						DeleteScorePanel frame = new DeleteScorePanel();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
					}
				});
				mnNewMenu_2.add(mntmNewMenuItem_11);

				JMenuItem mntmNewMenuItem_10_1 = new JMenuItem("价格查询");
				//查成绩
				mntmNewMenuItem_10_1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						QueryScore frame = new QueryScore();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
					}
				});
				mnNewMenu_2.add(mntmNewMenuItem_10_1);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);

				JButton btnNewButton = new JButton("退出");
				//退出
				btnNewButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnNewButton.setBounds(548, 319, 113, 27);
				contentPane.add(btnNewButton);

			}


		});
		// 使用SwingWorker类来在后台线程中执行语音识别的任务
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			//boolean a = true;

			@Override
			protected Void doInBackground() throws Exception {
				// 创建一个SpeechUtility对象，用于语音识别的初始化
				// 使用try-with-resources语句来自动关闭资源
				while (a) {
					SpeechUtility speechUtility = SpeechUtility.createUtility(SpeechConstant.APPID + "=1a2e1109");
					// 创建一个VoiceTest对象，用于语音识别的测试
					VoiceTest mvoice = new VoiceTest();
					mvoice.speech();
					// 设置一个MyVoiceListener对象，用于监听语音识别的结果
					mvoice.setMyVoiceListener(new MyVoiceListener() {
						@Override
						public void completion(String text) {
							String gjzName = "\u5c0f\u5764"; //小坤
							if (text.contains(gjzName)) {
								a = false;

								//开始语音输入
								VoiceSpeech voice = new VoiceSpeech();
								boolean isDo;
								do {
									voice.setMyVoiceListener(new MyVoiceListener() {
										@Override
										public void completion(String text) {
											String AddCourseName = "\u5546\u54c1\u65b0\u589e";
											String QueryCoursePanelName = "商品查询";
											String DeleteCoursePanelName = "商品删除";
											String EditCoursePanelName = "商品修改";
											String AddStudentName = "会员新增";
											String QueryStudentPanelName = "会员查询";
											String DeleteStudentPanelName = "会员删除";
											String EditStudentPanelName = "会员修改";

											if (text.contains(AddCourseName)) {
												voice.dispose();
												AddCourse frame = new AddCourse();
												frame.setVisible(true);
												frame.setLocationRelativeTo(null);
											} else if (text.contains(QueryCoursePanelName)) {
												voice.dispose();
												QueryCoursePanel frame = new QueryCoursePanel();
												frame.setVisible(true);
												frame.setLocationRelativeTo(null);
											} else if (text.contains(DeleteCoursePanelName)) {
												voice.dispose();
												DeleteCoursePanel frame = new DeleteCoursePanel();
												frame.setVisible(true);
												frame.setLocationRelativeTo(null);
											} else if (text.contains(EditCoursePanelName)) {
												voice.dispose();
												EditCoursePanel frame = new EditCoursePanel();
												frame.setVisible(true);
												frame.setLocationRelativeTo(null);
											} else if (text.contains(AddStudentName)) {
												voice.dispose();
												AddMember frame = new AddMember();
												frame.setVisible(true);
												frame.setLocationRelativeTo(null);
											} else if (text.contains(QueryStudentPanelName)) {
												voice.dispose();
												QueryStudentPanel frame = new QueryStudentPanel();
												frame.setVisible(true);
												frame.setLocationRelativeTo(null);
											} else if (text.contains(DeleteStudentPanelName)) {
												voice.dispose();
												DeleteStudentPanel frame = new DeleteStudentPanel();
												frame.setVisible(true);
												frame.setLocationRelativeTo(null);
											} else if (text.contains(EditStudentPanelName)) {
												voice.dispose();
												EditStudentPanel frame = new EditStudentPanel();
												frame.setVisible(true);
												frame.setLocationRelativeTo(null);
											} else if (text.contains("\u4ef7\u683c\u65b0\u589e")) {//跳转价格新增
												voice.dispose();
												AddPrice frame = new AddPrice();
												frame.setVisible(true);
												frame.setLocationRelativeTo(null);
												//删除原界面
												contentPane.removeAll();
												contentPane.repaint();
											} else if (text.contains("\u4ef7\u683c\u65b0\u589e")) {//跳转价格修改
												voice.dispose();
												//删除原界面
												contentPane.removeAll();
												contentPane.repaint();

												//添加界面
												EditScorePanel frame = new EditScorePanel();
												frame.setVisible(true);
												frame.setLocationRelativeTo(null);
											} else if (text.contains("\u4ef7\u683c\u5220\u9664")) {//跳转价格删除
												voice.dispose();
												//删除原界面
												contentPane.removeAll();
												contentPane.repaint();

												//添加界面
												DeleteScorePanel frame = new DeleteScorePanel();
												frame.setVisible(true);
												frame.setLocationRelativeTo(null);
											} else if (text.contains("\u4ef7\u683c\u67e5\u8be2")) {//跳转价格查询
												voice.dispose();
												QueryScore frame = new QueryScore();
												frame.setVisible(true);
												frame.setLocationRelativeTo(null);
											}
										}
									});
									isDo = false;
								} while (isDo);
							}
						}
					});
					// 使用一个while循环，不断地进行语音识别，直到识别到""\u5c0f\u5764" （小坤）这个关键词，然后退出循环

					try {
						Thread.sleep(3000);
					} catch (InterruptedException ex) {
						throw new RuntimeException(ex);
					}
				}

				return null;
			}
		};
		// 启动后台任务
		worker.execute();
	}
}
