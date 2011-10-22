package Experiments;

import java.awt.Button;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MigLayoutDemo {
	public static void main(String[] args) {
		JFrame frame = new JFrame("HelloWorldSwing");		
		JPanel panel = new JPanel(new MigLayout());
		panel.add(new TextArea("hello world!!!"));
		frame.add(panel);
		frame.setVisible(true);
	}
}