package org.hjm.renderer;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.hjm.component.NinePatchImageIcon;
import org.hjm.component.RLabel;
import org.hjm.component.RTextPane;
import org.hjm.entity.IMMessage;

import net.miginfocom.swing.MigLayout;
/**
 * <code>DemoRenderer</code> 
 * 
 * @author Jimmy
 * @since v1.0.0 (Oct 15, 2013)
 */
public class BubbleRenderer implements TableCellRenderer {
	LeftViewHolder mLeftHolder = new LeftViewHolder();
	RightViewHolder mRightHolder = new RightViewHolder();
	Map<String, Integer> mCacheCount = new HashMap<String, Integer>();
	int iCount = 0;
	
	public BubbleRenderer() {
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Holder holder = null;
		IMMessage imMsg = (IMMessage)value;
		String sSender = imMsg.getSender();
		String sMsg = imMsg.getMsg();
		String sTime = imMsg.getTime();
		
		Integer iDisplaySide = mCacheCount.get(sSender);
		if (iDisplaySide == null) {
			iDisplaySide = iCount++;
			mCacheCount.put(sSender, iDisplaySide);
		}
		
		if (iDisplaySide % 2 == 0) {
			holder = mLeftHolder;
		} else {
			holder = mRightHolder;
		}
		holder.mLblUser.setText(sSender);
		holder.mLblTime.setText(sTime);
		holder.mTxtMsg.setText(sMsg);
		int iHeight = holder.mTxtMsg.getPreferredSize().height + 20;
		iHeight = iHeight < 85 ? 85 : iHeight;
		
		System.err.println(iHeight);
		int iH = table.getRowHeight(row);
		if (iH != iHeight) {
			table.setRowHeight(row, iHeight);
		}
		return holder;
	}
	
	private class Holder extends JPanel{
		private static final long serialVersionUID = -2850253575244483981L;
		JLabel mLblUser = new JLabel();
		JLabel mLblTime = new JLabel();
		RLabel mLblHead = new RLabel();
		RTextPane mTxtMsg = new RTextPane();
		public Holder() {
			setBackground(Color.white);
		}
	}
	
	private class LeftViewHolder extends Holder{
		private static final long serialVersionUID = 1L;
		private final Icon mBgIconLeft= new NinePatchImageIcon(this.getClass().getResource(
				"/resource/msg_bg2.9.png"));
		private final Icon mHeadLeft= new ImageIcon(this.getClass().getResource(
				"/resource/tuling.jpg"));
		public LeftViewHolder() {
			this.setLayout(new MigLayout("ins 0"));
			mTxtMsg.setBackgroundIcon(mBgIconLeft);
			mLblHead.setIcon(mHeadLeft);
			this.add(mLblTime, "wmax pref");
			this.add(mLblUser, "pushx,growx, wrap");
			this.add(mLblHead, "spanx 2,split 2,wmax pref");
			this.add(mTxtMsg,"spanx 2,split 2,wmax pref");
		}
	}

	private class RightViewHolder extends Holder{
		private static final long serialVersionUID = 1L;
		private final Icon mBgIconRgh = new NinePatchImageIcon(this.getClass().getResource(
				"/resource/msg_bg1.9.png"));
		private final Icon mHeadRight = new ImageIcon(this.getClass().getResource(
				"/resource/wanjia.png"));
		public RightViewHolder() {
			this.setLayout(new MigLayout("ins 0, rtl"));
			mLblHead.setIcon(mHeadRight);
			mTxtMsg.setBackgroundIcon(mBgIconRgh);
			mLblUser.setHorizontalAlignment(JLabel.RIGHT);
			this.add(mLblTime, "wmax pref");
			this.add(mLblUser, "pushx,growx, wrap");
			this.add(mLblHead, "spanx 2,split 2,wmax pref");
			this.add(mTxtMsg,"spanx 2,split 2,wmax pref");
		}
	}

}