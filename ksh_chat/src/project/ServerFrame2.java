package project;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ServerFrame2 extends JFrame {

	BackgroundPanel backgroundPanel;

	private void initData() {
		backgroundPanel = new BackgroundPanel();
	}

	private void setInitLayout() {

		setTitle("서버");
		setSize(400, 600);
		setLayout(null); // 좌표 기반 설정

		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		add(backgroundPanel);

	}

	private void addEventListener() {
	}

	public ServerFrame2() {
		initData();
		setInitLayout();
		addEventListener();
	}

	// 테스트
	public static void main(String[] args) {
		System.out.println("11111111111111");
		ServerFrame2 frame2 = new ServerFrame2();
	}

	// inner class
	private class BackgroundPanel extends JPanel {
		private JPanel backgroundPanel;
		private Image backgroundImage;

		public BackgroundPanel() {
			backgroundImage = new ImageIcon("img/back.jpg").getImage();
			backgroundPanel = new JPanel();
			add(backgroundPanel);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
		}
	}

}// end of class
