package com.rabbit.ui;

import javax.swing.JPanel;

import com.rabbit.entities.Entity;
import com.rabbit.map_container.MapComponent;

class InfoWindow extends JPanel { // TODO extends some swing component
    /**
     *
     */
    private static final long serialVersionUID = -8685737135216575332L;

    public InfoWindow() {

    }

    public void showInfo(MapComponent comp) {
        if (!(comp instanceof Entity)) {
            throw new ClassCastException("It is only possible to show details of an Entity");
        }
        System.out.println(((Entity) comp).getStats().getAllStats());
    }
}