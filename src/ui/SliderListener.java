package ui;

import java.util.logging.Logger;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import wrapper.NumberWrapper;

public class SliderListener implements ChangeListener {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(SliderListener.class.getName());

	NumberWrapper n;

	public SliderListener(NumberWrapper n) {
		this.n = n;
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		n.setValue(Long.valueOf(((JSlider) event.getSource()).getValue()));
	}

}
