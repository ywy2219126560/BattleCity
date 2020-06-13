package com.project.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.WindowConstants;

import com.project.GameWindow;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;


public class TestWebView {
	
	static WebView webView = null;

    private static void gotoURL(String url) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                webView.getEngine().load(url);
            }
        });
    }

    /**
     * @param args
     *            the command line arguments
     * @throws URISyntaxException
     */
    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
        // TODO code application logic here
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(1100, 800);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        JFXPanel jFXPanel = new JFXPanel();
        frame.add(jFXPanel, "Center");
        
        JPanel controlPanel = new JPanel();
        frame.add(controlPanel, "North");
        JTextField urlField = new JTextField();
        ///////////////////////////////////////////////////////////////////////////////////
        urlField.setText("http://www.baidu.com");
        
        controlPanel.setLayout(new BorderLayout());
        urlField.setPreferredSize(new Dimension(frame.getWidth() - 100, 1));
        controlPanel.add(urlField, BorderLayout.WEST);

        controlPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                controlPanel.setLayout(new BorderLayout());
                urlField.setPreferredSize(new Dimension(frame.getWidth() - 100, 1));
                controlPanel.add(urlField, BorderLayout.WEST);
            }

        });
        frame.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                controlPanel.setLayout(new BorderLayout());
                urlField.setPreferredSize(new Dimension(frame.getWidth() - 100, 1));
                controlPanel.add(urlField, BorderLayout.WEST);
            }
        });
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                webView = new WebView();
                jFXPanel.setScene(new Scene(webView));
                // html位置
                webView.getEngine().load(getClass().getResource("/map/baidu_map.html").toExternalForm());
            }
        });
        
        // 设置窗口不可见
        frame.setVisible(true);
   
        // 截图地图图片
        new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					System.out.println("开始截图");
		        	BufferedImage image = new BufferedImage(1100, 800, BufferedImage.TYPE_INT_RGB);
			        Graphics2D g2 = image.createGraphics();
			        frame.paint(g2);
		        	ImageIO.write(image, "jpeg", new File("src/map/map.jpg"));
		        	// 启动游戏窗口
		        	GameWindow.start();
		        	// 关闭窗口
		        	frame.setVisible(false);
		        } catch (Exception e) {
		        	e.printStackTrace();
		        }
			}
		}).start();
        
        
    }

}
