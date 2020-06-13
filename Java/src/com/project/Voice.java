package com.project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONObject;

import com.baidu.aip.speech.AipSpeech;

import api.BaiduApi;
import api.TuLing;

public class Voice {
	
	// 设置APPID/AK/SK
	public static final String APP_ID = "20282022";
	public static final String API_KEY = "2svtsoaGuGBB3oKgqf4OIWXg";
	public static final String SECRET_KEY = "oGtigNpLbTWbLOZpO9YjGslCDspyUpAA";
	
	TuLing ling = new TuLing();
	BaiduApi baiduApi = new BaiduApi();
	JLabel jl1;
	JButton captureBtn;
	long startPlay;
	AudioFormat af = null;  
	TargetDataLine td = null;
	boolean flag = true;
	JPanel jp1, jp2, jp3;
	byte audioDataBuffer[] = null;
	// 定义字节数组输入输出流
	ByteArrayInputStream bais = null;
	ByteArrayOutputStream baos = null;
	// 定义停止录音的标志，来控制录音线程的运行
	Boolean stopflag = false;
	// 定义音频输入流
	AudioInputStream ais = null;
	// 定义源数据行,源数据行是可以写入数据的数据行。它充当其混频器的源。应用程序将音频字节写入源数据行，这样可处理字节缓冲并将它们传递给混频器。
	SourceDataLine sd = null;
	
	// 定义音频波形每次显示的字节数
	int intBytes = 0;
	GameWindowRight gameWindowRight;
	
	public void mousePressed(MouseEvent e,JButton captureBtn,JPanel jpa1 ,JPanel jpa2,JPanel jpa3,JLabel jl1) {
		this.captureBtn = captureBtn;
		this.jp1 = jpa1;
		this.jp2 = jpa2;
		this.jp3 = jpa3;
		// TODO Auto-generated method stub
		// 当开始录音按钮被按下时就开始录音
		if (e.getSource().equals(captureBtn)) {
			// 改变按钮上面的字的内容
			captureBtn.setText("松开 结束");
			// 调用录音的方法
			capture();
			// 记录开始录音的时间
			startPlay = System.currentTimeMillis();
		}
		
	}
	
	// 开始录音
	public void capture()  {
		try {
			// af为AudioFormat也就是音频格式
			af = getAudioFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, af);
			td = (TargetDataLine) (AudioSystem.getLine(info));
			// 打开具有指定格式的行，这样可使行获得所有所需的系统资源并变得可操作。
			td.open(af);
			// 允许某一数据行执行数据 I/O
			td.start();
			// 启动显示波形的进程
			RecordWave aw = new RecordWave();
			Thread t2 = new Thread(aw);
			t2.start();
			// 把显示波形的进程标志设为true
			flag = true;

			Record record = new Record();
			Thread t1 = new Thread(record);
			t1.start();
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
	}
	
	//鼠标松开，停止录音
	public void mouseReleased(MouseEvent e,GameWindowRight right) {
		gameWindowRight = right;
		// 当松开录音按钮时停止录音并保存录音的文件
		if (e.getSource().equals(captureBtn)) {
			// 调用停止录音的方法
			stopflag = true;
			// 将画波形的进程终止
			flag = false;
			// 当松开按钮后对显示波形的面板进行清空
			// jp2.repaint();
			// 改变按钮上面的字的内容
			captureBtn.setText("按住说话(3s)");
			jp2.setBackground(Color.black);

			JLabel lbl = new JLabel("正在处理中,请稍后!...");
			lbl.setForeground(Color.RED);
			jp2.add(lbl);

			// 调用保存录音的方法
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					save();
				}
			}).start(); 
		}
	}
	
	// 设置AudioFormat的参数
	public AudioFormat getAudioFormat() {
		// 下面注释部分是另外一种音频格式，两者都可以
		AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
		float rate = 16000f;
		int sampleSize = 16;
		String signedString = "signed";
		boolean bigEndian = true;
		int channels = 1;
		return new AudioFormat(encoding, rate, sampleSize, channels, (sampleSize / 8) * channels, rate, bigEndian);

	}
	
	public void save() {
		af = getAudioFormat();
		byte audioData[] = baos.toByteArray();
		if (audioData.length <= 0)
			return;
		bais = new ByteArrayInputStream(audioData);
		ais = new AudioInputStream(bais, af, audioData.length / af.getFrameSize());
		// 定义最终保存的文件名
		File file = null;
		// 写入文件
		try {
			// 以当前的时间命名录音的名字
			// 将录音的文件存放到F盘下语音文件夹下
			File filePath = new File("D:/AudioFile");

			if (!filePath.exists()) {// 如果文件不存在，则创建该目录
				filePath.mkdirs();
			}
			long time = System.currentTimeMillis();
			file = new File(filePath + "/" + time + ".wav");
			AudioSystem.write(ais, AudioFileFormat.Type.WAVE, file);
			// 将录音产生的wav文件转换为容量较小的mp3格式
			// 定义产生后文件名
			String fullpath = filePath + "/" + time + ".wav"; 
			
			
			// 根据语音生成文字
			// 初始化一个AipSpeech
			AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

			// 可选：设置网络连接参数
			client.setConnectionTimeoutInMillis(2000);
			client.setSocketTimeoutInMillis(60000);
			// 调用接口
			JSONObject res = client.asr(fullpath, "wav", 16000, null);
			String msg = res.get("result").toString().replace("\"", "").replace("[", "").replace("]", "").replace("。",
					"");
			//将生成的文字发送给图录机器人，生成语音，同步呈现消息
			gameWindowRight.getStringtext(msg);

		} catch (Exception e) {
			System.out.println("文件操作异常"+e);
			JOptionPane.showMessageDialog(null, "操作失败");
		} finally {
			// 关闭流
			try {

				if (bais != null) {
					bais.close();
				}
				if (ais != null) {
					ais.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 画波形的类
	// 因为要使用一些主函数中的数据，所以做成内部类
	class RecordWave extends JPanel implements Runnable {
		// 用画笔画出波形
		public void paint(Graphics g) {
			super.paint(g);
			g.fillRect(jp2.getX(), jp2.getY(), 300, 280);
			if (audioDataBuffer != null) {
				g.drawLine(jp2.getWidth() / 256, 700, jp2.getWidth() / 256, 700);

				for (int i = 0; i < audioDataBuffer.length - 1; ++i) {
					g.setColor(Color.RED);
					g.drawLine(i * jp2.getWidth() / 256, (int) audioDataBuffer[i] + 200, (i + 1)

							* jp2.getWidth() / 256, (int) audioDataBuffer[i + 1] + 200);
				}
			}
		}

		public void run() {
			// 刷新波形
			while (true) {
				// System.out.println("ok");
				try {
					synchronized (this) {
						// 隔多长时间获取
						Thread.sleep(100);
					}
				} catch (Exception e) {

					e.printStackTrace();
				}
				this.paint(jp2.getGraphics());
				// 终止线程
				if (flag == false) {
					break;
				}
			}
		}
	}
	
	// 录音类，因为要用到MyRecord类中的变量，所以将其做成内部类
		class Record implements Runnable {
			// 定义存放录音的字节数组,作为缓冲区
			byte bts[] = new byte[10000];

			// 将字节数组包装到流里，最终存入到baos中
			// 重写run函数
			public void run() {
				baos = new ByteArrayOutputStream();
				try {
					stopflag = false;
					while (stopflag != true) {
						// 当停止录音没按下时，该线程一直执行
						// 从数据行的输入缓冲区读取音频数据。
						// 要读取bts.length长度的字节,cnt 是实际读取的字节数
						int cnt = td.read(bts, 0, bts.length);
						if (cnt > 0) {
							baos.write(bts, 0, cnt);
						}

						// 开始从音频流中读取字节数
						byte copyBts[] = bts;
						bais = new ByteArrayInputStream(copyBts);
						ais = new AudioInputStream(bais, af, copyBts.length / af.getFrameSize());
						try {
							DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, af);
							sd = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
							sd.open(af);
							sd.start();

							// 从音频流中读取
							int Buffer_Size = 10000;
							audioDataBuffer = new byte[Buffer_Size];
							int outBytes;

							intBytes = ais.read(audioDataBuffer, 0, audioDataBuffer.length);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						// intBytes = -1;
						// 关闭打开的字节数组流
						if (baos != null) {
							baos.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						// 下面这句td.drain()不能要，这样如果不播放数据就阻塞再次录音会出现其他程序访问错误
						// td.drain();
						td.close();
						// 刷新显示波形的面板
						jp2.repaint();
					}
				}
			}

		}
	
}
