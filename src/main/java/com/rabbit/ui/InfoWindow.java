package com.rabbit.ui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;

import com.rabbit.entities.Entity;
import com.rabbit.map_container.MapComponent;

class InfoWindow extends JPanel { // TODO extends some swing component
    /**
     *
     */
    private static final long serialVersionUID = -8685737135216575332L;
    private static final int BORDER_WIDTH = 5;
    private final JLabel imgWindow;
    private final JLabel[] labels = new JLabel[10];
    private final int imgSize;
    private Entity currentEntity;

    public InfoWindow(int windowSize) {
        this.imgSize = windowSize - BORDER_WIDTH * 2;
        this.imgWindow = new JLabel();
        imgWindow.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH));
        imgWindow.setPreferredSize(new Dimension(this.imgSize, this.imgSize));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(imgWindow);

        for (int i = 0; i < this.labels.length; i++) {
            this.labels[i] = new JLabel();
            this.add(this.labels[i]);
        }
    }

    public void showInfo(MapComponent comp) {
        if (!(comp instanceof Entity)) {
            throw new ClassCastException("It is only possible to show details of an Entity");
        }
        this.currentEntity = (Entity) comp;
        updateInfo();
    }

    public void updateInfo() {
        if (this.currentEntity == null) {
            return;
        }
        
        this.imgWindow.setIcon(new ImageIcon(this.currentEntity.getScaledImage(this.imgSize)));
        int index = 0;
        for (Map.Entry<String, Number> entry : this.currentEntity.getStats().getStats().entrySet()) {
            this.labels[index].setText(entry.getKey() + ": " + entry.getValue());
            System.out.println(entry.getKey() + ": " + entry.getValue());
            index++;
        }

        this.repaint();
    }
}