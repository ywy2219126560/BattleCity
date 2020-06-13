package com.project;

/**
 * 关卡类，里面提供三个关卡
 * @author 22191
 *
 */
public class CustomsPass {
	
	/**
	 * 第一关的坦克，和敌人坦克
	 */
	 MyTank myTank1_0 = new MyTank(400, 550);
	// 使用构造方法创建坦克对象
	 EnemyTank enemyTank1_1 = new EnemyTank(0, 0,0);
	 EnemyTank enemyTank1_2 = new EnemyTank(300, 200,1);
	 EnemyTank enemyTank1_3 = new EnemyTank(750, 50,2);
	 EnemyTank enemyTank1_4 = new EnemyTank(750, 200,2);
	 EnemyTank enemyTank1_5 = new EnemyTank(500, 100,1);
	 EnemyTank enemyTank1_6 = new EnemyTank(300, 50,1);
	 EnemyTank enemyTank1_7 = new EnemyTank(200, 50,0);
	 Tank[] tanks01 = new Tank[] { myTank1_0, enemyTank1_2, enemyTank1_3, enemyTank1_4, enemyTank1_5, enemyTank1_6, enemyTank1_7, myTank1_0 };


	/**
	 * 第二关的坦克，和敌人坦克
	 */
	 MyTank myTank2_0 = new MyTank(350, 600);
	// 使用构造方法创建坦克对象
	 EnemyTank enemyTank2_1 = new EnemyTank(0, 0,1);
	 EnemyTank enemyTank2_2 = new EnemyTank(300, 50,2);
	 EnemyTank enemyTank2_3 = new EnemyTank(400, 50,1);
	 EnemyTank enemyTank2_4 = new EnemyTank(600, 50,0);
	 EnemyTank enemyTank2_5 = new EnemyTank(0, 350,2);
	 EnemyTank enemyTank2_6 = new EnemyTank(375, 300,1);
	 EnemyTank enemyTank2_7 = new EnemyTank(600, 300,0);
	 EnemyTank enemyTank2_8 = new EnemyTank(0, 600,1);
	 EnemyTank enemyTank2_9 = new EnemyTank(750, 300,0);
	 EnemyTank enemyTank2_10 = new EnemyTank(700, 50,1);
	 Tank[] tanks02 = new Tank[] { enemyTank2_1, enemyTank2_2, enemyTank2_3, 
			 enemyTank2_4, enemyTank2_5, enemyTank2_6, enemyTank2_7, enemyTank2_8, enemyTank2_9, enemyTank2_10, myTank2_0 };
	 
	 /**
	 * 第三关的坦克，和敌人坦克
	 */
	MyTank myTank3_0 = new MyTank(500, 500);
	// 使用构造方法创建坦克对象
	EnemyTank enemyTank3_1 = new EnemyTank(20, 20,1);
	EnemyTank enemyTank3_2 = new EnemyTank(20, 120,0);
	EnemyTank enemyTank3_3 = new EnemyTank(40, 60,2);
	EnemyTank enemyTank3_4 = new EnemyTank(520, 180,1);
	EnemyTank enemyTank3_5 = new EnemyTank(560, 120,0);
	EnemyTank enemyTank3_6 = new EnemyTank(560, 240,1);
	Tank[] tanks03 = new Tank[] { enemyTank3_1, enemyTank3_2, enemyTank3_3, enemyTank3_4, enemyTank3_5, enemyTank3_6, myTank3_0 };
	

	
	
	/**
	 * 第一关
	 * 1土墙
	 * 2铁墙
	 */
	 Wall[] walls01 = new Wall[] { 
			 
			new Wall(0, 400, 50, 50, true, 2), 
			 
			// 1
			new Wall(50, 50, 50, 50, true, 1),
			new Wall(50, 100, 50, 50, true, 1),
			new Wall(100, 100, 50, 50, true, 2),
			new Wall(50, 150, 50, 50, true, 1),
			new Wall(50, 200, 50, 50, true, 1),
				
			// 2
			new Wall(50, 400, 50, 50, true, 1),
			new Wall(50, 450, 50, 50, true, 1),
			new Wall(50, 500, 50, 50, true, 1),
			new Wall(50, 550, 50, 50, true, 1),
			
			// 3
			new Wall(150, 50, 50, 50, true, 1),
			new Wall(150, 100, 50, 50, true, 1),
			new Wall(150, 150, 50, 50, true, 1),
			new Wall(150, 200, 50, 50, true, 1),
			
			// 4
			new Wall(150, 400, 50, 50, true, 1),
			new Wall(150, 450, 50, 50, true, 1),
			new Wall(150, 500, 50, 50, true, 1),
			new Wall(150, 550, 50, 50, true, 1),
			
			// 5
			new Wall(250, 50, 50, 50, true, 1),
			new Wall(250, 100, 50, 50, true, 1),
			new Wall(250, 150, 50, 50, true, 1),
			
			// 6
			new Wall(250, 250, 50, 50, true, 1),
			new Wall(300, 250, 50, 50, true, 2),
			new Wall(350, 250, 50, 50, true, 1),
			
			// 7
			new Wall(250, 350, 50, 50, true, 1),
			new Wall(250, 400, 50, 50, true, 1),
			new Wall(250, 450, 50, 50, true, 1),
			
			// 8
			new Wall(350, 50, 50, 50, true, 1),
			new Wall(350, 100, 50, 50, true, 1),
			new Wall(350, 150, 50, 50, true, 1),
			
			// 9
			new Wall(350, 350, 50, 50, true, 1),
			new Wall(350, 400, 50, 50, true, 1),
			new Wall(400, 400, 50, 50, true, 1),
			new Wall(450, 400, 50, 50, true, 1),
			new Wall(350, 450, 50, 50, true, 1),
			new Wall(400, 350, 50, 50, true, 1),
			new Wall(450, 350, 50, 50, true, 1),
			new Wall(500, 350, 50, 50, true, 1),
			
			// 10
			new Wall(500, 50, 50, 50, true, 1),
			new Wall(500, 100, 50, 50, true, 1),
			new Wall(500, 150, 50, 50, true, 1),
			new Wall(500, 200, 50, 50, true, 1),
			
			// 11
			new Wall(500, 400, 50, 50, true, 1),
			new Wall(500, 450, 50, 50, true, 1),
			new Wall(500, 500, 50, 50, true, 1),
			new Wall(500, 550, 50, 50, true, 1),
			
			// 12
			new Wall(600, 50, 50, 50, true, 1),
			new Wall(600, 100, 50, 50, true, 1),
			new Wall(600, 150, 50, 50, true, 1),
			new Wall(600, 200, 50, 50, true, 1),
			
			// 13
			new Wall(600, 400, 50, 50, true, 1),
			new Wall(600, 450, 50, 50, true, 1),
			new Wall(600, 500, 50, 50, true, 1),
			new Wall(600, 550, 50, 50, true, 1),
			
			// 14
			new Wall(750, 300, 50, 50, true, 2),
			new Wall(700, 300, 50, 50, true, 1),
			new Wall(650, 300, 50, 50, true, 2),
			new Wall(600, 300, 50, 50, true, 2),

			//grass
			new Wall(100,500,50,50,true,3),
			new Wall(600,300,50,50,true,3),
			new Wall(100,300,50,50,true,3),
			new Wall(200,200,50,50,true,3),
			new Wall(300,500,50,50,true,3),
			new Wall(100,200,50,50,true,3),
			new Wall(500,350,50,50,true,3),
			new Wall(400,250,50,50,true,3),
			new Wall(100,300,50,50,true,3),
			new Wall(200,400,50,50,true,3),

			
	};



	
	/**
	 * 第二关
	 */
	 Wall[] walls02 = new Wall[] { 
			new Wall(50, 50, 50, 50, true, 1),
			new Wall(50, 100, 50, 50, true, 1),
			new Wall(0, 400, 50, 50, true, 2),
			new Wall(50, 400, 50, 50, true, 1),
			new Wall(50, 450, 50, 50, true, 1),
			new Wall(50, 500, 50, 50, true, 1),
			new Wall(50, 550, 50, 50, true, 1),
			new Wall(50, 600, 50, 50, true, 1),
			
			new Wall(100, 300, 50, 50, true, 1),
			new Wall(150, 300, 50, 50, true, 1),
			new Wall(200, 300, 50, 50, true, 1),
			new Wall(200, 350, 50, 50, true, 2),
			new Wall(200, 400, 50, 50, true, 2),
			new Wall(200, 450, 50, 50, true, 1),
			new Wall(200, 500, 50, 50, true, 1),
			new Wall(200, 600, 50, 50, true, 1),
			
			new Wall(200, 0, 50, 50, true, 2),
			new Wall(200, 50, 50, 50, true, 2),
			new Wall(200, 150, 50, 50, true, 1),
			new Wall(200, 200, 50, 50, true, 1),
			
			new Wall(300, 250, 50, 50, true, 2),
			new Wall(350, 200, 50, 50, true, 1),
			new Wall(400, 150, 50, 50, true, 2),
			new Wall(300, 350, 50, 50, true, 1),
			new Wall(300, 400, 50, 50, true, 1),
			new Wall(300, 450, 50, 50, true, 1),
			new Wall(300, 500, 50, 50, true, 1),
			new Wall(350, 500, 50, 50, true, 1),
			new Wall(400, 500, 50, 50, true, 1),
			new Wall(350, 450, 50, 50, true, 1),
			new Wall(400, 450, 50, 50, true, 1),
			new Wall(450, 450, 50, 50, true, 1),
			new Wall(450, 500, 50, 50, true, 1),
			new Wall(450, 400, 50, 50, true, 1),
			new Wall(450, 350, 50, 50, true, 1),
			new Wall(500, 300, 50, 50, true, 2),
			new Wall(550, 250, 50, 50, true, 1),
			new Wall(550, 200, 50, 50, true, 2),
			
			new Wall(350, 200, 50, 50, true, 1),
			new Wall(550, 550, 50, 50, true, 1),
			new Wall(550, 600, 50, 50, true, 1),
			new Wall(600, 600, 50, 50, true, 1),
			new Wall(650, 600, 50, 50, true, 1),
			new Wall(650, 550, 50, 50, true, 1),
			
			
			new Wall(550, 350, 50, 50, true, 1),
			new Wall(550, 450, 50, 50, true, 1),
			new Wall(600, 450, 50, 50, true, 2),
			new Wall(650, 450, 50, 50, true, 1),
			new Wall(700, 400, 50, 50, true, 1),
			new Wall(750, 400, 50, 50, true, 1),
			new Wall(650, 400, 50, 50, true, 1),
			new Wall(650, 350, 50, 50, true, 1),
			
			new Wall(650, 250, 50, 50, true, 1),
			new Wall(700, 250, 50, 50, true, 2),
			
			new Wall(600, 100, 50, 50, true, 2),
			new Wall(550, 100, 50, 50, true, 1),
			new Wall(550, 50, 50, 50, true, 1),
			new Wall(650, 100, 50, 50, true, 1),
			new Wall(650, 50, 50, 50, true, 1),
			
	};
	
	/**
	 * 第三关
	 */
	 Wall[] walls03 = new Wall[] { new Wall(150, 300, 50, 50, true, 2), // 2铁墙
			new Wall(200, 300, 50, 50, true, 2), // 1砖墙
			new Wall(250, 300, 50, 50, true, 2), // 1砖墙
			new Wall(300, 300, 50, 50, true, 2), // 1砖墙
			new Wall(350, 300, 50, 50, true, 2), // 1砖墙
			new Wall(400, 300, 50, 50, true, 2), // 1砖墙
			new Wall(450, 300, 50, 50, true, 2), // 1砖墙
			new Wall(500, 300, 50, 50, true, 2), // 2铁墙
			new Wall(550, 300, 50, 50, true, 2), // 2铁墙
			new Wall(600, 300, 50, 50, true, 2), // 2铁墙
			new Wall(650, 300, 50, 50, true, 2), // 1砖墙
			new Wall(700, 300, 50, 50, true, 2), // 1砖墙

			new Wall(400, 50, 50, 50, true, 1), // 1砖墙
			new Wall(400, 100, 50, 50, true, 1), // 1砖墙
			new Wall(400, 150, 50, 50, true, 1), // 1砖墙
			new Wall(400, 200, 50, 50, true, 1), // 1砖墙
			new Wall(400, 250, 50, 50, true, 1), // 1砖墙
			new Wall(400, 350, 50, 50, true, 1), // 1砖墙
			new Wall(400, 400, 50, 50, true, 1), // 1砖墙
			new Wall(350, 400, 50, 50, true, 1), // 2铁墙
			new Wall(450, 400, 50, 50, true, 1),// 2铁墙
			
			
	};

	 
	 
}
