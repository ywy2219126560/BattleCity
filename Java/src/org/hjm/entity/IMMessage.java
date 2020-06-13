package org.hjm.entity;
/**
 * <code>IMMessage</code> 
 * 
 * @author Jimmy
 * @since v1.0.0 (Oct 15, 2013)
 */
public class IMMessage {
	private String sender;
	private String time;
	private String msg;
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
