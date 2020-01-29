package ui;

import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import wrapper.NumberWrapper;

public class ControlPane extends JPanel {
	private static final Logger LOGGER = Logger.getLogger(ControlPane.class.getName());

	private static final long serialVersionUID = 1010021733256440598L;

	public ControlPane(NumberWrapper n) {
		/* Set vertical layout of components */
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JButton b1 = new JButton("1");
		JButton b2 = new JButton("2");
		JButton b3 = new JButton("3");
		JButton b4 = new JButton("4");
		JSlider timeSlider = new JSlider(1, 5000, 2000);
		timeSlider.addChangeListener(new SliderListener(n));

		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);
		this.add(timeSlider);

		this.setSize(this.getWidth(), this.getHeight());
	}

}
