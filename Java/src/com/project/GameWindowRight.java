package com.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;

import org.hjm.entity.IMMessage;
import org.hjm.model.BubbleModel;
import org.hjm.renderer.BubbleRenderer;

import api.TuLing;
import net.miginfocom.swing.MigLayout;

public class GameWindowRight extends JPanel implements ActionListener {
	

	
	TuLing ling = new TuLing();
	
	Language language = new Language();
	 
	BubbleModel mModel = new BubbleModel();
	final JTextPane txtPnl = new JTextPane();
	
	final GameWindowRight gright = this;
	 
	int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public void startJPanel() {
		
	 
		JPanel contentPane = new JPanel();
		
		contentPane.setLayout(new BorderLayout(0, 0));
		this.add(contentPane);
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane,BorderLayout.CENTER);
		contentPane.setPreferredSize(new Dimension((int)(GameWindow.width*0.2),(int)(height*0.9)));
	 
		
		
		JTable table = new JTable();
		table.setTableHeader(null);
		table.setModel(mModel);
		table.getColumnModel().getColumn(0).setCellRenderer(new BubbleRenderer());
		 
		scrollPane.setViewportView(table);
		table.setBackground(Color.white);
		table.setOpaque(true);
		table.setShowHorizontalLines(false);
		scrollPane.getViewport().setBackground(Color.WHITE);
		
		JButton btnSend = new JButton("发送");
		JButton btnsy = new JButton("语言发送");
		final JTextPane txtPnl = new JTextPane();
		JPanel pnlSend = new JPanel(new MigLayout("ins 4"));
		
		pnlSend.add(btnsy, "growy,pushy");
		
		pnlSend.add(new JScrollPane(txtPnl), "hmin 50px,growx,pushx");
		pnlSend.add(btnSend, "growy,pushy");
		contentPane.add(pnlSend, BorderLayout.SOUTH);
		
		StringBuffer strbf = new StringBuffer();
		strbf.append("欢迎来到智能以行——Java坦克游戏AI项目实战");
		sendMessage("小图",strbf.toString());
		language.change("终于等到您啦"+strbf);
		time();
		
		
		
		//消息发送
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = txtPnl.getText();
				getStringtext(msg);
			}
		});
		
		//语音发送
		btnsy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						GetRecorder recorder = new GetRecorder(gright);
						recorder.setVisible(true);
					}
				}).start();
			}
		}); 
	}
	
	public void getStringtext(String msg) {
		sendMessage("玩家",msg);
		String message = ling.getMessage(msg);
		System.err.println("图灵返回"+message);
		sendMessage("小图", message);
		language.change(message);
	}
	
	public void sendMessage(String sender,String msg) {
		String sMsg = msg;
		if ("".equals(sMsg)) {
			System.err.println("Require not blank.");
			return ;
		}
		String sSend =  sender;
		String sTime =  getTime();
		IMMessage imMsg = new IMMessage();
		imMsg.setSender(sSend);
		imMsg.setTime(sTime);
		imMsg.setMsg(sMsg);
		mModel.addRow(imMsg);
		//clear
		txtPnl.setText("");
	}
	
	static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(new Date());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void time() {
		new Thread(new Runnable() {
			@Override
			public void run() {
                try {
					Thread.sleep(1000*60);
					int currentTimeMillis = (int) (3%(System.currentTimeMillis()*20));
					if (currentTimeMillis==0) {
						String healthy = "[健康提示]小图温馨提示 您！小图智能识别您为12周岁以下未成年儿童。您今日的娱乐时间已经超过1小时。根据青少年保护规则，您将无法再次娱乐游戏。请合理安排游戏时间，劳役结合！谢谢配合！";
						language.change(healthy);
						sendMessage("小图",healthy);
					} else if (currentTimeMillis==1){
						String healthy = "[健康提示]小图温馨提示 您！小图智能识别您为16周岁以下未成年儿童。您今日的娱乐时间已经超过3小时。根据青少年保护规则，您将无法再次娱乐游戏。请合理安排游戏时间，劳役结合！谢谢配合！\"";
						language.change(healthy);
						sendMessage("小图",healthy);
					} else if (currentTimeMillis==2||currentTimeMillis==3) {
						String healthy = "[健康提示]您今日的娱乐时间已经超过30分钟。小图温馨提示 ！您的眼睛该休息了！请眺望远方，活动一下身体。如果连续时间太久，小图将强行停止游戏！";
						language.change(healthy);
						sendMessage("小图",healthy);
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	 
}

