package ui;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import wrapper.NumberWrapper;

public class SliderListener implements ChangeListener {

	NumberWrapper n;

	public SliderListener(NumberWrapper n) {
		this.n = n;
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		n.setValue(Long.valueOf(((JSlider) event.getSource()).getValue()));
	}

}
