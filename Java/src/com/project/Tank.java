package com.project;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;


/**
 * 坦克类
 * @author lanmin
 *
 */
public abstract class Tank implements GameObject {
	//坦克的属性（带默认值）
	//坦克的位置
	protected int x = 100;
	protected int y = 100;
	//坦克的大小（高宽）
	private int w = 50;
	private int h = 50;
	//坦克的速度
	protected int speed = 10;
	//坦克的外形图片
	private Image image = Toolkit.getDefaultToolkit().getImage(
			"src/images/tankU.gif");
	//坦克的生命
	protected boolean life = true;
	//坦克的方法
	//构造方法重载（初始化时修改坦克的属性）
	public Tank(int x, int y, int w, int h, int speed, Image image) {
		super();
		this.x = x; //this见本章技术拓展
		this.y = y;
		this.w = w;
		this.h = h;
		this.speed = speed;
		this.image = image;
	}
	//构造方法重载（初始化时修改坦克的位置）
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
	//构造方法重载（不带参数，初始化时不修改属性默认值）
	public Tank() {
	}
	//坦克移动
	public abstract void move(KeyEvent e);
	//坦克的绘制
	@Override
	public abstract void paint(Graphics g);
	//坦克开火
	public abstract void fire();
	
	//得到坦克的碰撞矩形
	public Rectangle getRectangle(){
		return new Rectangle(x,y,w,h);
	}
	//判断坦克之间是否碰撞
	public boolean collideTank(Tank []tanks){
		for(int i=0;i<tanks.length;i++){
			//自己不和自己做碰撞检测 && 己方矩形和其他矩形碰撞
			if(tanks[i]!=null && this != tanks[i] && this.getRectangle().intersects(tanks[i].getRectangle())){
				return true;
			}
		}
		return false;
	}
	//判断坦克是否碰撞墙壁
	public boolean collideWall(Wall []walls){
		for(int i=0;i<walls.length;i++){
			//墙不为空并且坦克和墙壁碰撞时
			if(walls[i]!=null && this.getRectangle().intersects(walls[i].getRectangle())){
				if (walls[i].getType()!=3) {
					return true;
				}
			}
		}
		return false;
	}

}
