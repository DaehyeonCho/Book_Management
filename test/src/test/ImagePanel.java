package test;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

class ImagePanel extends JPanel{
    private Image img; // JPanel의 함수를 사용하기위해 전역 설정

    public ImagePanel(Image img) {
        this.img = img; //사이즈만 정함
        setSize(new Dimension(img.getWidth(null),img.getHeight(null)));
        setPreferredSize(new Dimension(img.getWidth(null),img.getHeight(null)));
        setLayout(null);
    
    }

    public int getWidth() {
        return img.getWidth(null);
    }

    public int getHeight() {
        return img.getHeight(null);
    }

    public void paintComponent(Graphics g) { //자동으로 백그라운드 이미지 설정
        g.drawImage(img, 0, 0, null);
    }
}