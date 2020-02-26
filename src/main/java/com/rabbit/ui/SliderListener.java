package com.rabbit.ui;

import java.util.logging.Logger;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.rabbit.wrapper.NumberWrapper;

public class SliderListener implements ChangeListener {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(SliderListener.class.getName());

	private transient final NumberWrapper num;

	public SliderListener(NumberWrapper num) {
		this.num = num;
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		num.setValue(Long.valueOf(((JSlider) event.getSource()).getValue()));
	}

}
