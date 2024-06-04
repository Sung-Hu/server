package mychat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class ServerFrame extends JFrame{
	
	private Server mContext;
	
	// 백그라운드 패널
	private BackgroundPanel backgroundPanel;
	
	// 메인보드
	private JPanel mainPanel;
	private JTextArea mainBoard;
	
	// 포트패널
	private JPanel portPanel;
	private JLabel portLabel;
	private JTextField inputPort;
	private JButton connectBtn;
	
	public Server getmContext() {
		return mContext;
	}
	public void setmContext(Server mContext) {
		this.mContext = mContext;
	}
	public JPanel getMainPanel() {
		return mainPanel;
	}
	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	public JTextArea getMainBoard() {
		return mainBoard;
	}
	public void setMainBoard(JTextArea mainBoard) {
		this.mainBoard = mainBoard;
	}
	public JPanel getPortPanel() {
		return portPanel;
	}
	public void setPortPanel(JPanel portPanel) {
		this.portPanel = portPanel;
	}
	public JLabel getPortLabel() {
		return portLabel;
	}
	public void setPortLabel(JLabel portLabel) {
		this.portLabel = portLabel;
	}
	public JTextField getInputPort() {
		return inputPort;
	}
	public void setInputPort(JTextField inputPort) {
		this.inputPort = inputPort;
	}
	public JButton getConnectBtn() {
		return connectBtn;
	}
	public void setConnectBtn(JButton connectBtn) {
		this.connectBtn = connectBtn;
	}
	
	public ServerFrame(Server mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		addEventListener();
	}
	private void initData() {
		// 백그라운드 패널
		backgroundPanel = new BackgroundPanel();
		
		// 메인 패널
		mainPanel = new JPanel();
		mainBoard = new JTextArea();

		// 포트패널
		portPanel = new JPanel();
		portLabel = new JLabel("PORT NUMBER");
		inputPort = new JTextField(10);
		connectBtn = new JButton("Connect");
		
		inputPort.setText("10001");
	}
	private void setInitLayout() {
		setTitle("서버");
		setSize(400, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // 좌표 기반 설정
		
		//백그라운드 패널
		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		add(backgroundPanel);

		// 포트패널
		portPanel.setBounds(50, 30, 300, 50);
		portPanel.setBackground(new Color(0, 0, 0, 0));
		portPanel.setFont(new Font(null));
		portPanel.add(portLabel);
		portPanel.add(inputPort);
		portPanel.add(connectBtn);
		backgroundPanel.add(portPanel);

		// 메인패널
		mainPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), "Server"));
		mainPanel.setBounds(40, 100, 320, 350);
		mainPanel.setBackground(Color.WHITE);
		backgroundPanel.add(mainPanel);

		setVisible(true);
	}
	private void addEventListener() {
		connectBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mContext.startServer();
			}
		});
	}
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

}