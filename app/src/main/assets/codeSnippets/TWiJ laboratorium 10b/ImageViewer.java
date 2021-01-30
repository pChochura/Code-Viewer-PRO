import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ImageViewer extends JFrame implements ActionListener {
	int currentImage = 0;
	String images[] = {"image1.jpg", "image2.jpg", "image3.jpg"};
	JLabel title;
	JLabel display;
	JButton buttonNext;
	JButton buttonBack;
	public ImageViewer() {
		super("Java Slide Show");
		setSize(800, 600);
		title = new JLabel("", JLabel.CENTER);
		add(title, BorderLayout.NORTH);
		display = new JLabel();
		//display.setBounds(0, 0, getWidth(), getHeight());
		showCurrentImage(0);
		add(display, BorderLayout.CENTER);
		buttonNext = new JButton(">>");
		buttonNext.addActionListener(this);
		buttonBack = new JButton("<<");
		buttonBack.addActionListener(this);
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(buttonBack);
		panel.add(buttonNext);
		add(panel, BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		//showCurrentImage(0);
	}
	public void showCurrentImage(int i) {
		try {
			ImageIcon icon = new ImageIcon(images[i]);
			Image img = icon.getImage();
			Image newImg = img.getScaledInstance(display.getWidth(),
					display.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon newImc = new ImageIcon(newImg);
			display.setIcon(newImc);
			title.setText("Image: " + (i + 1) + " / " + images.length);
		}
		catch (Exception e) {
		}
	}
	public void showNextImage() {
		currentImage += 1;
		if (currentImage >= images.length) {
			currentImage = 0;
		}
		showCurrentImage(currentImage);
	}
	public void showPreviousImage() {
		currentImage -= 1;
		if (currentImage < 0) {
			currentImage = images.length - 1;
		}
		showCurrentImage(currentImage);
	}
	public void actionPerforned(ActionEvent e) {
		if (e.getSource() == buttonNext) {
			showNextImage();
		}
		else if (e.getSource() == buttonBack) {
			showPreviousImage();
		}
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ImageViewer();
			}
		});
	}
}