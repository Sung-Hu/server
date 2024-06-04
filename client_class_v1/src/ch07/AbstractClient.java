package ch07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class AbstractClient {
	private String name;
	private Socket socket;
	private PrintWriter socketWriter;
	private BufferedReader socketReader;
	private BufferedReader keyboardReader;

	public AbstractClient(String name) {
		this.name = name;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public final void fun() {
		try {
			connectToServer();
			setupStreams();
			startService(); // join 상태
		} catch (IOException e) {
			System.out.println("접속종료");
		} finally {
			cleanup();
		}
	}

	protected abstract void connectToServer() throws IOException;

	private void setupStreams() throws IOException {
		socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		socketWriter = new PrintWriter(socket.getOutputStream(), true);
		keyboardReader = new BufferedReader(new InputStreamReader(System.in));
	}

	private void startService() throws IOException {
		Thread readThread = createReadThread();
		Thread writeThread = createWriteThread();

		// 스레드 시작
		readThread.start();
		writeThread.start();

		// 메인 스레드 대기 처리
		try {
			readThread.join();
			writeThread.join();
		} catch (InterruptedException e) {
		}
	}

	private Thread createWriteThread() {
		return new Thread(() -> {
			try {
				String msg;
				while ((msg = keyboardReader.readLine()) != null) {
					socketWriter.println("[" + name + "] : " + msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private Thread createReadThread() {
		return new Thread(() -> {
			try {
				String msg;
				while ((msg = keyboardReader.readLine()) != null)
					;
				System.out.println("방송 옴 : " + msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private void cleanup() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}
}// end of class