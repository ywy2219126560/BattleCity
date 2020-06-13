package com.project;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Random;


public class EnemyTank extends Tank {


	//增加方向属性，0-3分别表示方向：0 上 1下 2左 3右
	private int direction;
	//每个方向对应一张坦克图片
	private Image[] enemyImage = null;

	private Image [] whiteEnemy = new Image[]{
			Toolkit.getDefaultToolkit().getImage("src/images/tankU.gif"),
			Toolkit.getDefaultToolkit().getImage("src/images/tankD.gif"),
			Toolkit.getDefaultToolkit().getImage("src/images/tankL.gif"),
			Toolkit.getDefaultToolkit().getImage("src/images/tankR.gif")
	};

	private Image[] greenEnemy = new Image[]{
			Toolkit.getDefaultToolkit().getImage("src/images/tankU_green.png"),
			Toolkit.getDefaultToolkit().getImage("src/images/tankD_green.png"),
			Toolkit.getDefaultToolkit().getImage("src/images/tankL_green.png"),
			Toolkit.getDefaultToolkit().getImage("src/images/tankR_green.png"),
	};

	private Image[] yellowEnemy = new Image[]{
			Toolkit.getDefaultToolkit().getImage("src/images/tankU_yellow.png"),
			Toolkit.getDefaultToolkit().getImage("src/images/tankD_yellow.png"),
			Toolkit.getDefaultToolkit().getImage("src/images/tankL_yellow.png"),
			Toolkit.getDefaultToolkit().getImage("src/images/tankR_yellow.png"),
	};

	private Image[] blueEnemy = new Image[]{
			Toolkit.getDefaultToolkit().getImage("src/images/tankU_blue.png"),
			Toolkit.getDefaultToolkit().getImage("src/images/tankD_blue.png"),
			Toolkit.getDefaultToolkit().getImage("src/images/tankL_blue.png"),
			Toolkit.getDefaultToolkit().getImage("src/images/tankR_blue.png"),
	};



	//使用一个随机对象，随机地改变方向
	private Random random=new Random();
	//记录上一个位置（碰到墙就返回到前一个未碰墙的位置）
	private int oldx;
	private int oldy;

	public EnemyTank(int x, int y, int type) {
		super(x, y);
		if (type==0){
			enemyImage = blueEnemy;
		}else if (type==1){
			enemyImage = yellowEnemy;
		}else if (type==2){
			enemyImage = greenEnemy;
		}else {
			enemyImage = whiteEnemy;
		}
	}

	//方法重写（方法名、参数、返回类型均和父类相同）
		@Override
	    public void paint(Graphics g) {
			g.drawImage(enemyImage[direction], x, y, null);//
			move(null);
		}
	
	//方法重载（参数类型和父类不同，不是方法重写）
	public void move(KeyEvent e) {
		//将当前位置记录下来
		oldx=x;
		oldy=y;
		//随机生成一个0到1的小数
		double d=random.nextDouble();
		//5%的情况下会改变方向
		if (d>0.95) {
			direction=random.nextInt(4);//方向随机生成（0-3）
		}
		//1%的情况下会开火
		if(d>0.95){
			fire();
		}
		//朝着改变后的方向继续前进
		switch (direction) {
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
		}
		//前进后如果碰到边界
		if (x <= -20 || x >= 820 - 50 || y <= -20 || y >= 680 - 50) {
			//碰到边界将位置退回到前一个记录的位置（相当于碰到墙就弹回）
			x=oldx;
			y=oldy;
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
		GameWindowLeft.addBullet(new Bullet(x, y, speed*2,direction,true));
	}

}
