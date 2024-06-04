package ch05;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Nateon extends JFrame {

	private JButton button1;
	private JButton button2;
	private JLabel label;
	private JTextField textField;
	private JLabel img;
	private JPanel buttonPanel;
	public Nateon() {
		initData();
		setInitLayout();
	}
	private void initData() {
		setTitle("네이트온");
		setSize(400, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buttonPanel = new JPanel();
		button1 = new JButton("서버 실행");
		button2 = new JButton("서버 종료");
		label = new JLabel();
		textField = new JTextField("아이디 입력", 20);
		
	}
	private void setInitLayout() {
		setLayout(new FlowLayout());
		setVisible(true);
		
		add(button1 , BorderLayout.SOUTH);
		add(button2, BorderLayout.SOUTH);
		add(label, BorderLayout.SOUTH);
		add(textField, BorderLayout.SOUTH);
		add(buttonPanel, BorderLayout.SOUTH);
		
	}

	public JButton getButton1() {
		return button1;
	}
	public void setButton1(JButton button) {
		this.button1 = button;
	}
	public JButton getButton2() {
		return button2;
	}
	public void setButton2(JButton button) {
		this.button2 = button;
	}
	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	public JTextField getTextField() {
		return textField;
	}
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public static void main(String[] args) {
	Nateon componets =	new Nateon(); //객체의 주소 값
	componets.getTextField().setText("PORTNUMBER");
		
	}
}