package com.project;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class Wall implements GameObject{
	//墙的位置
	private int x;
	private int y;
	//墙的高宽
	private int w=50;
	private int h=50;
	//是否存在
	private boolean life=true;
	//墙的类型(typ为1砖墙2铁墙)
	private int type; 
	public boolean isLife() {
		return life;
	}
	public void setLife(boolean life) {
		this.life = life;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	private Image img;
	private Image brickWallImg = Toolkit.getDefaultToolkit().getImage("src/images/brickWall.jpg");
	private Image ironWallImg  = Toolkit.getDefaultToolkit().getImage("src/images/ironWall.jpg");
	private Image grassImg = Toolkit.getDefaultToolkit().getImage("src/images/grass.png");

	public Wall(int x, int y, int w, int h, boolean life, int type) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.life = life;
		this.type = type;
		//墙的图片随墙的类型变化
		if (type == 1) {
			this.img = brickWallImg;
		}else if (type == 2) {
			this.img = ironWallImg;
		}else if (type ==3 ){
			this.img = grassImg;
		}
	}
	//墙壁的绘制
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, x, y, null);// 2,靠自动重绘（用一个线程来重绘）
	}
	
	//得到子弹的碰撞矩形
	public Rectangle getRectangle(){
		return new Rectangle(x,y,w,h);
	}
}
