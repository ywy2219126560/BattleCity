package com.project;

import com.project.util.AudioUtils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class Bullet implements GameObject{
	//子弹的图片
	private Image img = Toolkit.getDefaultToolkit().getImage("src/images/emU.gif");
	//子弹的位置、速度、运动方向和大小
	private int x;
	private int y;
	private int speed;
	private int w=7;
	private int h=7;
	//0 上 1下 2左 3右
	private int direction;
	//是否是敌方发射的子弹
	private boolean isEnemy;
	//子弹的生命
	private boolean life=true;
	
	public Bullet(int x, int y, int speed, int direction,boolean isEnemy) {
		super();
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.direction = direction;
		this.isEnemy = isEnemy;
	}

	//子弹的绘制
	public void paint(Graphics g) {
		//子弹位置要微调
		g.drawImage(img, x+23, y+23, null);// 2,靠自动重绘（用一个线程来重绘）
		move();
	}
	//子弹的移动
	public void move(){
		switch (this.direction) {//
		case 0:
			y -= speed;
			break;
		case 1:
			y += speed;
			break;
		case 2:
			x -= speed;
			break;
		case 3:
			x += speed;
			break;
		default:
			break;
		}
		hitTanks(GameWindowLeft.tanks);
		hitWalls(GameWindowLeft.walls);
	}
	//得到子弹的碰撞矩形（子弹位置微调了，碰撞矩阵也要微调）
	public Rectangle getRectangle(){
		return new Rectangle(x+23,y+23,w,h);
	}
	//判断该子弹是否打中了敌人
	public boolean hitTanks(Tank []tanks){
		for (int i = 0; i < tanks.length; i++) {
			if(this.isEnemy){//敌人子弹只打我方坦克
				if(tanks[i] instanceof MyTank && this.getRectangle().intersects(tanks[i].getRectangle())){
					System.out.println("被打中了！！！");
					//添加爆炸效果
					GameWindowLeft.addBomb(new Bomb(x,y));
					//坦克和子弹的生命都结束
					tanks[i].life = false;
					this.life = false;
					return true;
				}
			}else{//我方子弹只打敌方坦克
				if(tanks[i] instanceof EnemyTank && this.getRectangle().intersects(tanks[i].getRectangle())){
					System.out.println("打中了！！！");
					AudioUtils.bangAudio();
					//添加爆炸效果
					GameWindowLeft.addBomb(new Bomb(x,y));
					//坦克和子弹的生命都结束
					tanks[i].life = false;
					this.life = false;
					//击中敌方加分
					GameWindowLeft.score.addScore();
					return true;
				}
			}
		}
		return false;
	}
	
	//判断子弹是否击中墙
	public boolean hitWalls(Wall []walls){
		for (int i = 0; i < walls.length; i++) {
			if(walls[i]!=null && this.getRectangle().intersects(walls[i].getRectangle())){
				if (walls[i].getType()!=3){
					System.out.println("打中墙了！！！");
					//添加爆炸效果
					GameWindowLeft.addBomb(new Bomb(x,y));
					//砖墙被击中消失(铁墙即使击中也不消失)
					if(walls[i].getType()==1){
						System.out.println("打中砖墙");
						walls[i].setLife(false);
					}else{
						System.out.println("打中铁墙");
					}
					//子弹的生命结束
					this.life = false;
					return true;
				}
			}
		}
		return false;
	}

	public boolean isLife() {
		return life;
	}

	public void setLife(boolean life) {
		this.life = life;
	}
		
}
