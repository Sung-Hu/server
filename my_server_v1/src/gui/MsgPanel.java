package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgPanel extends JPanel {
	private JTextArea messageArea;
	private JTextField inputField;
	private JButton sendButton;
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;

	public MsgPanel(ClientFrame clientFrame) {
		initData();
		setLayout();
		addEventListeners();
		startMsg();
	}

	private void initData() {
		messageArea = new JTextArea();
		messageArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(messageArea);
		scrollPane.setBounds(20, 20, 300, 150);

		inputField = new JTextField();
		inputField.setBounds(20, 180, 200, 30);

		sendButton = new JButton("Send");
		sendButton.setBounds(230, 180, 90, 30);

		add(scrollPane);
		add(inputField);
		add(sendButton);
	}

	private void setLayout() {

		setLayout(null);
	}

	private void addEventListeners() {
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
	}

	private void sendMessage() {
		String message = inputField.getText();
		try {
			writer.write(message + "\n");
			writer.flush();
			messageArea.append("사용자: " + message + "\n");
			inputField.setText("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void startMsg() {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					String message;
					while ((message = reader.readLine()) != null) {
						messageArea.append("Server: " + message + "\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
}
