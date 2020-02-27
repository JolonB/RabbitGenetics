package com.rabbit.ui;

import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.rabbit.entities.Entity;
import com.rabbit.map_container.MapComponent;

public class Cell extends JLabel {

    /**
     *
     */
    private static final long serialVersionUID = -4267270471630490457L;

    private transient final MapComponent comp;

    public Cell(MapComponent comp, ImageIcon img, MouseListener ml) {
        super(img);
        this.comp = comp;
        /* Only add a mouse listener if it is an entity */
        if (comp instanceof Entity) {
            this.addMouseListener(ml);
        }
    }

    public MapComponent getComponent() {
        return this.comp;
    }

    @Override
    public String toString() {
        return "[ Cell: { Icon: " + this.getIcon().toString() + ", Component: " + this.comp.toString() + " } ]";
    }
}