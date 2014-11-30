import javax.swing.*;

public class BlockJumper {

	public static void main(String[] args) {
		JFrame w = new JFrame("Block Jumper");
		w.setBounds(100, 100, 1170, 900);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Window window = new Window();
		w.addKeyListener(window);
		w.add(window);
		w.setResizable(true);
		w.setVisible(true);
		window.run();
	}
}