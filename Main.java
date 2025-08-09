package pracseven;
import java.awt.*;
import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Prac");
		JPanel panel = new Plant();
		//JPanel panel = new Target();
		frame.add(panel);
		frame.setLayout(new FlowLayout());
		frame.pack();
        frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
