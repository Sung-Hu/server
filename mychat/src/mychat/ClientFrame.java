package mychat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ClientFrame extends JFrame{

	// 클라이언트
	private Client mContext;
	// 로그인창
	private LoginPanel loginPanel;
	// 메세지창
	private MessagePanel messagePanel;
	
	private JPanel mainPanel;		

	public ClientFrame(Client mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
	}
	private void initData() {
		
		loginPanel = new LoginPanel();
		messagePanel = new MessagePanel();
		mainPanel = new JPanel();

	}
	private void setInitLayout() {
		setTitle("클라이언트");
		setSize(500, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(null);
		setContentPane(mainPanel);
		
		loginPanel.setLayout(null);
		
	}


}
