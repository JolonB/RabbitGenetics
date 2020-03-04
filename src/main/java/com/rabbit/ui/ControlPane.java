package com.rabbit.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.rabbit.wrapper.BooleanWrapper;
import com.rabbit.wrapper.NumberWrapper;

public class ControlPane extends JPanel {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ControlPane.class.getName());

	private static final long serialVersionUID = 1010021733256440598L;

	public ControlPane(final InfoWindow info, final NumberWrapper num, final BooleanWrapper running,
			final BooleanWrapper paused, final int windowSize) {
		super();
		/* Set vertical layout of components */
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel controlPanel = new JPanel(new GridLayout(0, 1));
		JButton pauseButton = new JButton("Pause");
		pauseButton.addActionListener(e -> paused.invertValue());
		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(e -> running.setValue(false));
		JButton but3 = new JButton("3");
		JButton but4 = new JButton("4");
		JSlider timeSlider = new JSlider(500, 5000, num.getValueInteger());
		timeSlider.addChangeListener(new SliderListener(num));

		controlPanel.add(pauseButton);
		controlPanel.add(stopButton);
		controlPanel.add(but3);
		controlPanel.add(but4);
		controlPanel.add(timeSlider);

		this.add(info);
		this.add(controlPanel);

		info.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
		controlPanel.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
	}

}
