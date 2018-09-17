/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author plalex
 */
public class PanelCarta extends JPanel implements MouseListener
{
    Image img;
    int clave;
    boolean sw = false;
    boolean bloqueado = false;
    Main p;
 
    public PanelCarta(Main prin, Image im, int c){
        p = prin;
        img = im;
        clave = c;
        addMouseListener(this);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        if(sw || bloqueado){
            g.drawImage(img, 32, 0,130,100, this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(bloqueado == false){
            if(!sw){
                sw = true;
                p.seleccionados.add(this);
                p.verificar();
            }
        }
        
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
