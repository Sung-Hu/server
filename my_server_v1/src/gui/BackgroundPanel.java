package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import lombok.Data;


@Data
public class BackgroundPanel extends JPanel {
	
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