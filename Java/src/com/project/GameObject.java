package com.project;

import java.awt.Graphics;
/**
 * 统一标准，所有的游戏物体都有paint方法，要被绘制出来
 * @author lanmin
 *
 */
public interface GameObject {
	public void paint(Graphics g);
}
