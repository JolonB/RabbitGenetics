package com.rabbit.ui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rabbit.entities.Entity;
import com.rabbit.entities.Null;
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
        this.imgWindow.setIcon(new ImageIcon(new Null().getScaledImage(this.imgSize)));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(imgWindow);

        for (int i = 0; i < this.labels.length; i++) {
            this.labels[i] = new JLabel();
            this.labels[i].setPreferredSize(new Dimension(this.imgSize, 30));
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
        if (this.currentEntity == null || !this.currentEntity.getAlive()) {
            reset();
            return;
        }

        this.imgWindow.setIcon(new ImageIcon(this.currentEntity.getScaledImage(this.imgSize)));
        Map<String, Number> statsMap = this.currentEntity.getStats().getStats();
        List<String> keys = new ArrayList<>(statsMap.keySet());
        List<Number> values = new ArrayList<>(statsMap.values());
        for (int i = 0; i < this.labels.length; i++) {
            this.labels[i].setText(i < keys.size() ? keys.get(i) + ": " + values.get(i) : "");
        }

        this.repaint();
    }

    private void reset() {
        this.imgWindow.setIcon(new ImageIcon(new Null().getScaledImage(this.imgSize)));
        for (int i = 0; i < this.labels.length; i++) {
            this.labels[i].setText("");
        }
    }
}