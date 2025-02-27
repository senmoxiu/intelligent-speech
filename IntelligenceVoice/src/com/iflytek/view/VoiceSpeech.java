package com.iflytek.view;

import com.iflytek.MyVoiceListener;
import com.iflytek.cloud.speech.*;
import com.iflytek.util.DebugLog;
import com.iflytek.util.JsonParser;
import com.iflytek.util.Version;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 森修
 */
public class VoiceSpeech extends Frame implements ActionListener {


	Button startBtn;

	Button stopBtn;

	Button exitBtn;

	TextArea textArea;
	String realText;

// ??????д????

	SpeechRecognizer speechRecognize;

	private static final String DEF_FONT_NAME = "语音输入";

	private static final int DEF_FONT_STYLE = Font.BOLD;

	private static final int DEF_FONT_SIZE = 20;

	private static final int TEXT_COUNT = 100;

	private MyVoiceListener mMyVoiceListener;

	public MyVoiceListener getMyVoiceListener() {
		return this.mMyVoiceListener;
	}

	public void setMyVoiceListener(MyVoiceListener mMyVoiceListener) {
		this.mMyVoiceListener = mMyVoiceListener;
	}

	public VoiceSpeech() {

// ???????д????

		speechRecognize = SpeechRecognizer.createRecognizer();

// ???????

		startBtn = new Button("开始");

		stopBtn = new Button("停止");

		exitBtn = new Button("退出");
		startBtn.setBounds(10, 1, 30, 10);

		textArea = new TextArea();

		Panel btnPanel = new Panel();

		Panel textPanel = new Panel();

//????????

		startBtn.addActionListener(this);

		stopBtn.addActionListener(this);

		exitBtn.addActionListener(this);

		btnPanel.add(startBtn);

		btnPanel.add(stopBtn);

		btnPanel.add(exitBtn);

		textPanel.add(textArea);

		add(btnPanel);

		add(textPanel);

// ???????

		setLayout(new GridLayout(2, 1));

		setSize(500, 300);

		setTitle("小坤助手");

		setLocation(200, 200);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == startBtn) {

//            try {textArea.setText("三秒后开始识别");
//                Thread.sleep(3000);
//
//            } catch (InterruptedException ex) {
//                throw new RuntimeException(ex);
//            }

			textArea.setText("小坤助手为您服务，您说的是:");

			if (!speechRecognize.isListening()) {
				speechRecognize.startListening(recognizerListener);
			} else {
				speechRecognize.stopListening();
			}

		} else if (e.getSource() == stopBtn) {

			speechRecognize.stopListening();

		} else if (e.getSource() == exitBtn) {
			dispose();
		}

	}

	/**
	 * ??д??????
	 */

	private RecognizerListener recognizerListener = new RecognizerListener() {

		@Override
		public void onBeginOfSpeech() {

// DebugLog.Log( "onBeginOfSpeech enter" );

// ((JLabel) jbtnRecognizer.getComponent(0)).setText("??д??...");

// jbtnRecognizer.setEnabled(false);

		}

		@Override
		public void onEndOfSpeech() {

			DebugLog.Log("onEndOfSpeech enter");

		}

		/**
		 * ?????д???. ???RecognizerResult???????????????????????????????Area??

		 */

		@Override
		public void onResult(RecognizerResult results, boolean islast) {

			DebugLog.Log("onResult enter");

// ????????json????????????????? com.iflytek.util.JsonParser??

			String text = JsonParser.parseIatResult(results.getResultString());
			mMyVoiceListener.completion(text);
//  String text = results.getResultString();

//  JsonParser json = new JsonParser();

//      String newTest = json.parseIatResult(text);

//      textArea.setText(newTest);

			textArea.append(text);

			text = textArea.getText();

			System.out.println(text);


			if (null != text) {

				int n = text.length() / TEXT_COUNT + 1;

				int fontSize = Math.max(10, DEF_FONT_SIZE - 2 * n);

				DebugLog.Log("onResult new font size=" + fontSize);

				int style = n > 1 ? Font.PLAIN : DEF_FONT_SIZE;

				Font newFont = new Font(DEF_FONT_NAME, style, fontSize);

				textArea.setFont(newFont);

			}

			if (islast) {
				realText = text;
				iatSpeechInitUI();

			}

		}

		@Override
		public void onVolumeChanged(int volume) {

			DebugLog.Log("onVolumeChanged enter");

			if (volume == 0) {
				volume = 1;
			} else if (volume >= 6) {
				volume = 6;
			}

// labelWav.setIcon(new ImageIcon("res/mic_0" + volume + ".png"));

		}

		@Override
		public void onError(SpeechError error) {

			DebugLog.Log("onError enter");

			if (null != error) {

				DebugLog.Log("onError Code??" + error.getErrorCode());

				textArea.setText(error.getErrorDescription(true));

				iatSpeechInitUI();

			}

		}

		@Override
		public void onEvent(int eventType, int arg1, int agr2, String msg) {

			DebugLog.Log("onEvent enter");

		}

	};

	/**
	 * ??д??????????????
	 */

	public void iatSpeechInitUI() {

// labelWav.setIcon(new ImageIcon("res/mic_01.png"));

// jbtnRecognizer.setEnabled(true);

// ((JLabel) jbtnRecognizer.getComponent(0)).setText("?????д");

	}

	public String getText() {
		return realText;
	}

	public static void main(String[] args) {

// ?????

		StringBuffer param = new StringBuffer();

		param.append("appid=" + Version.getAppid());

//  param.append( ","+SpeechConstant.LIB_NAME_32+"=myMscName" );

		SpeechUtility.createUtility(param.toString());

		VoiceSpeech t = new VoiceSpeech();

	}

}



