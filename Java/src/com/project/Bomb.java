package com.project;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class Bomb {
	private int x;
	private int y;
	private int count=0;
	private boolean life=true;
	static Image [] imagesBomb=new Image[43];
	
	public Bomb(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	static {
		for (int i = 0; i < imagesBomb.length; i++) {
			imagesBomb[i]=Toolkit.getDefaultToolkit().getImage("src/images/b"+(i+1)+".gif");
		}
	}
	public void paint(Graphics g) {
		//爆炸位置要微调
		g.drawImage(imagesBomb[count], x, y, null);// 2,靠自动重绘（用一个线程来重绘）
		count++;
		if(count>=42){
			this.life=false;
		}
	}
	public boolean isLife() {
		return life;
	}

	public void setLife(boolean life) {
		this.life = life;
	}
}
