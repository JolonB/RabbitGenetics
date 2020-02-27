package com.rabbit.ui;

import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.rabbit.wrapper.NumberWrapper;

public class ControlPane extends JPanel {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ControlPane.class.getName());

	private static final long serialVersionUID = 1010021733256440598L;

	public ControlPane(InfoWindow info, NumberWrapper num) {
		super();
		/* Set vertical layout of components */
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JButton but1 = new JButton("1");
		JButton but2 = new JButton("2");
		JButton but3 = new JButton("3");
		JButton but4 = new JButton("4");
		JSlider timeSlider = new JSlider(1000, 5000, 2000);
		timeSlider.addChangeListener(new SliderListener(num));

		this.add(info);
		this.add(but1);
		this.add(but2);
		this.add(but3);
		this.add(but4);
		this.add(timeSlider);

		this.setSize(this.getWidth(), this.getHeight());
	}

}
