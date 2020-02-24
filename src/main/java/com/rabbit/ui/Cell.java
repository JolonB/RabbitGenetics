package com.rabbit.ui;

import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.rabbit.map_container.MapComponent;

public class Cell extends JLabel {

    /**
     *
     */
    private static final long serialVersionUID = -4267270471630490457L;

    private MapComponent comp;

    public Cell(MapComponent comp, ImageIcon img, MouseListener ml) {
        super(img);
        this.comp = comp;
        this.addMouseListener(ml);
    }

    @Override
    public String toString() {
        return "[ Cell: { Icon: " + this.getIcon().toString() + ", Component: " + this.comp.toString() + " } ]";
    }
}