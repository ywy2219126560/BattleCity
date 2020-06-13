package com.project;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginJpanel extends JPanel{

    private JTextField usernameField = new JTextField(25);
    private JPasswordField passwordField = new JPasswordField(25);
    private static JButton jButton = new JButton("登录");
    
    public static JButton getBtn() {
    	return jButton;
	}
	
	public void startJPanel() {
		this.setBounds(0,0,400,700);
		this.setOpaque(true);
		this.setBackground(new Color(251,247,241));

        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        this.setLayout(gridBagLayout);


        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(0,5,105,5);
        JLabel title = new JLabel("paper");
        title.setIcon(new ImageIcon("src/icon/icon.png"));
        title.setFont(new Font("黑体", Font.PLAIN,50));
        title.setForeground(new Color(175, 174, 174));

        gridBagLayout.setConstraints(title,constraints);
        this.add(title);


        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5,5,5,5);
        JLabel jLabel = new JLabel("欢迎!");
        jLabel.setFont(new Font("黑体", Font.PLAIN,30));
        jLabel.setForeground(new Color(125,122,119));
        gridBagLayout.setConstraints(jLabel,constraints);
        this.add(jLabel);


        constraints.gridwidth = GridBagConstraints.REMAINDER;
        JLabel jLabel1 = new JLabel("欢迎回来现在有很多东西等着你");
        jLabel1.setFont(new Font("黑体", Font.PLAIN,18));
        jLabel1.setForeground(new Color(175, 174, 174));
        gridBagLayout.setConstraints(jLabel1,constraints);
        this.add(jLabel1);


        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(10,5,10,5);
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.setBackground(new Color(249,247,210));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        usernameField.setFont(new Font("黑体", Font.PLAIN,18));
        usernameField.setForeground(new Color(125,122,119));
        usernameField.setBackground(new Color(249,247,210));
        usernameField.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        panel.add(new JLabel(new ImageIcon("src/icon/emailIcon.png")));
        panel.add(usernameField);
        gridBagLayout.setConstraints(panel,constraints);
        this.add(panel);


        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(10,5,10,5);
        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel1.setBackground(new Color(249,247,210));
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        passwordField.setForeground(new Color(125,122,119));
        passwordField.setBackground(new Color(249,247,210));
        passwordField.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        panel1.add(new JLabel(new ImageIcon("src/icon/passwordIcon.png")));
        panel1.add(passwordField);
        gridBagLayout.setConstraints(panel1,constraints);
        this.add(panel1);



        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(10,5,5,5);
        jButton.setBorder(BorderFactory.createEmptyBorder(13,10,13,10));
        jButton.setFont(new Font("黑体", Font.PLAIN,18));
        jButton.setBackground(new Color(58,175,215));
        jButton.setForeground(new Color(226,229,230));
        gridBagLayout.setConstraints(jButton,constraints);
        this.add(jButton);


        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(0,5,200,5);
        JLabel jLabel2 = new JLabel("<HTML>没有账户?点击<font color='#66bfde'>注册</font></HTML>");
        jLabel2.setFont(new Font("黑体", Font.PLAIN,15));
        jLabel2.setForeground(new Color(175, 174, 174));
        gridBagLayout.setConstraints(jLabel2,constraints);
        this.add(jLabel2);
	}
	
}
