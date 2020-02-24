package com.rabbit.ui;

import com.rabbit.map_container.MapComponent;

class InfoWindow { // TODO extends some swing component
    public InfoWindow() {

    }

    public void showInfo(MapComponent comp) {
        System.out.println(comp.toString());
    }
}