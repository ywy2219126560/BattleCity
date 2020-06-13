package com.project;

import com.project.util.AudioUtils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class MyTank extends Tank {

	//增加方向属性，0-3分别表示方向：0 上 1下 2左 3右
	private int direction;	
	//每个方向对应一张坦克图片
	private Image[] images = new Image[] {
		Toolkit.getDefaultToolkit().getImage("src/images/tankU_red.gif"),
		Toolkit.getDefaultToolkit().getImage("src/images/tankD_red.gif"),
		Toolkit.getDefaultToolkit().getImage("src/images/tankL_red.gif"),
		Toolkit.getDefaultToolkit().getImage("src/images/tankR_red.gif")};
	//记录上一个位置（碰到墙就返回到前一个未碰墙的位置）
	private int oldx;
	private int oldy;
	
	public MyTank(int x, int y) {
		super(x, y);
	}
	
	//方法重写（方法名、参数、返回类型均和父类相同）
	@Override
	public void paint(Graphics g) {
		g.drawImage(images[direction], x, y,null);// 2,靠自动重绘（用一个线程来重绘）
	}
	
	//方法重写
	@Override
	public void move(KeyEvent e) {
		//将当前位置记录下来
		oldx = x;
		oldy = y;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			direction = 0;
			y -= speed;
			break;
		case KeyEvent.VK_DOWN:
			direction = 1;
			y += speed;
			break;
		case KeyEvent.VK_LEFT:
			direction = 2;
			x -= speed;
			break;
		case KeyEvent.VK_RIGHT:
			direction = 3;
			x += speed;
			break;
		case KeyEvent.VK_F:
			if(this.life) {
				fire();
			}
		default:
			break;
		}
		//前进后如果碰到边界
		if (x <= -10 || x > 800 - 50 || y <= -10 || y > 650 - 50) {
			//超出边界将位置退回到上一个位置
			x = oldx;
			y = oldy;
		}
		//如果坦克之间发生了碰撞，则返回到前一个被记录的位置
		if(collideTank(GameWindowLeft.tanks)){
			x = oldx;
			y = oldy;
		}
		//如果坦克撞墙，则返回上一个位置
		if(collideWall(GameWindowLeft.walls)){
			x = oldx;
			y = oldy;
		}

	}



	@Override
	public void fire() {
		AudioUtils.fireAudio();
		GameWindowLeft.addBullet(new Bullet(x, y, speed*2,direction,false));
	}

}
       