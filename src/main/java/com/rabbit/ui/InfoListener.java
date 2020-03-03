package com.rabbit.ui;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class InfoListener extends MouseInputAdapter {

    private final InfoWindow info;

    public InfoListener(InfoWindow info) {
        this.info = info;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        info.showInfo(((Cell) event.getSource()).getComponent());
    }
}