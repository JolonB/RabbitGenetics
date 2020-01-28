package ui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ControlPane extends JPanel {

	private static final long serialVersionUID = 1010021733256440598L;

	public ControlPane() {
		/* Set vertical layout of components */
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JButton b1 = new JButton("1");
		JButton b2 = new JButton("2");
		JButton b3 = new JButton("3");
		JButton b4 = new JButton("4");
		JSlider sl = new JSlider(0, 100, 10);

		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);
		this.add(sl);
		
		this.setSize(this.getWidth(), this.getHeight());
	}

}
