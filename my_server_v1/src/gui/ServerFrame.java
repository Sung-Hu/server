package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import lombok.Getter;
import lombok.Setter;
import socket.Server;

@Getter
@Setter
public class ServerFrame extends JFrame {

	private Server mContext;

	private BackgroundPanel backgroundPanel;
	// 메인보드
	private JPanel mainPanel;
	private JTextArea mainBoard;
	// 포트패널
	private JPanel portPanel;
	private JLabel portLabel;
	private JTextField inputPort;
	private JButton connectBtn;

	public ServerFrame(Server mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
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

		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		add(backgroundPanel);

		// 포트패널 컴포넌트
		portPanel.setBounds(50, 30, 300, 50);
		portPanel.setBackground(new Color(0, 0, 0, 0));
		portPanel.setFont(new Font(null));
		portPanel.add(portLabel);
		portPanel.add(inputPort);
		portPanel.add(connectBtn);
		backgroundPanel.add(portPanel);

		// 메인패널 컴포넌트
		mainPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), "Server"));
		mainPanel.setBounds(40, 100, 320, 350);
		mainPanel.setBackground(Color.WHITE);

		// 백그라운드 패널에 메인 패널 넣기
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

//	// 테스트 코드 
//	public static void main(String[] args) {
//		new ServerFrame();
//	}
//	
}
