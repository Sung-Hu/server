package project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ServerFrame extends JFrame {

	private BackgroundPanel backgroundPanel;
	private JPanel mainPanel;
	private JTextArea mainBoard;

	private JTextField inputPort;
	private JButton button;
	private JLabel inputLabel;
	private JPanel portPanel;

	// private JLabel backgroundImage;
	// private BackgroundPanel backgroundPanel;

	public ServerFrame() {
		initData();
		setInitLayout();
	}

	private void initData() {
		// backgroundImage = new JLabel(new ImageIcon("img/back.jpg"));
		// setContentPane(backgroundImage);

		backgroundPanel = new BackgroundPanel();

		mainPanel = new JPanel();
		mainBoard = new JTextArea();

		portPanel = new JPanel();
		inputLabel = new JLabel("Service Number");
		inputPort = new JTextField(10);
		button = new JButton("Click");

	}

	private void setInitLayout() {
		setTitle("서버관리자");
		setLayout(null);

		setSize(400, 601);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setResizable(false);

		backgroundPanel.setSize(400, 600);
		// backgroundPanel.setSize(getWidth(),getHeight());
		// backgroundPanel.setLayout(null);
		backgroundPanel.setLocation(0, 0);
		add(backgroundPanel);

		// JLayeredPane layeredPane = getLayeredPane();

		portPanel.setBounds(50, 30, 300, 50);
		portPanel.setBackground(new Color(0, 0, 0, 0));
		portPanel.add(inputLabel);
		portPanel.add(inputPort);
		portPanel.add(button);

		mainPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5)));
		mainPanel.setBounds(40, 100, 320, 350);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(null);

		mainBoard.setBounds(45, 100, 300, 320);
		mainPanel.add(mainBoard);

		// layeredPane.add(mainPanel, JLayeredPane.PALETTE_LAYER);

		setVisible(true);
	}

	private class BackgroundPanel extends JPanel {

		private Image backgroundImage;
		private JPanel backgroundPanel;

		public BackgroundPanel() {
			backgroundImage = new ImageIcon("img/back.jpg").getImage();
			backgroundPanel = new JPanel();
			add(backgroundPanel);
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(backgroundImage, 0, 0, 400, 600, null);
		}

	}

	public static void main(String[] args) {
		new ServerFrame();
	}
}// end of class
