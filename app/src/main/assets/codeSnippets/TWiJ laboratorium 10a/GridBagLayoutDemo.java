import java.awt.*;
import javax.swing.*;
public class GridBagLayoutDemo extends JFrame {
	public GridBagLayoutDemo(String title) {
		setTitle(title);
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		JPanel panel = new JPanel();
		panel.setLayout(gridbag);
		constraints.ipadx = 10;
		constraints.ipady = 10;
		//constraints.insets = new Insets(15, 5, 15, 5);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = GridBagConstraints.REMAINDER;
		JButton b1 = new JButton("Button1");
		gridbag.setConstraints(b1, constraints);
		panel.add(b1);
		constraints.gridx = GridBagConstraints.RELATIVE;
		constraints.gridheight = 1;
		JButton b2 = new JButton("Button2");
		gridbag.setConstraints(b2, constraints);
		panel.add(b2);
		JButton b3 = new JButton("Button3");
		gridbag.setConstraints(b3, constraints);
		panel.add(b3);
		JButton b4 = new JButton("Button4");
		gridbag.setConstraints(b4, constraints);
		panel.add(b4);
		constraints.gridheight = GridBagConstraints.REMAINDER;
		JButton b5 = new JButton("Button5");
		panel.add(b5, constraints);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = GridBagConstraints.RELATIVE;
		constraints.gridheight = 1;
		JButton b6 = new JButton("Button6");
		panel.add(b6, constraints);
		constraints.gridy = GridBagConstraints.RELATIVE;
		JButton b7 = new JButton("Button7");
		panel.add(b7, constraints);
		getContentPane().add(panel, BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		GridBagLayoutDemo demo = new GridBagLayoutDemo("GridBagLayoutDemo");
		demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		demo.pack();
		demo.setVisible(true);
	}
}