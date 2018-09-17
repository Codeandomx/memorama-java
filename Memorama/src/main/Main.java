/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author plalex
 */
public class Main extends JFrame
{
    // Propiedades
    ArrayList<PanelCarta> todos, todos2 ,seleccionados;
    int contadorperdedor;
    int contadorganador;
    JPanel panelPrincipal;
 
    // Constructor
    public Main()
    {
        // Inicializamos los contadores
        contadorperdedor = 0;
        contadorganador = 0;
        
        // Inicializamos los componentes
        initComponents();
    }
    
    // Inicializamos los componentes
    public void initComponents()
    {
        // Creamos la vista principal del juego
        setLayout(new BorderLayout());
        panelPrincipal=new JPanel();
        panelPrincipal.setLayout(new GridLayout(5,4,5,5));
        
        // Creamos el menu del juego
        JMenuBar menu=new JMenuBar();
        JMenu archivo=new JMenu("Archivo");
        
        // Creamos la opciones del menu
        JMenuItem nuevo=new JMenuItem("Nuevo");
        JMenuItem salir=new JMenuItem("Salir");
        
        // Añadimos elementos al panel
        archivo.add(nuevo);
        archivo.add(salir);
        menu.add(archivo);
        setJMenuBar(menu);
        add(panelPrincipal);
        
        // Creamos los arrays contenedores de las cartas
        todos = new ArrayList<PanelCarta>();
        todos2 = new ArrayList<PanelCarta>();
        seleccionados = new ArrayList<PanelCarta>();
        
        // Creamos y añadimos los paneles
        agregarPaneles();
        
        // Eventos
        
        // Reseteamos el panel y agregamos elementos
        nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Inicializamos los contadores
                contadorperdedor = 0;
                contadorganador = 0;
                // Removemos los elementos
                panelPrincipal.removeAll();
                // Añadimos los elementos nuevos
                agregarPaneles();
            }
        });
        
        // Salimos del juego
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // Agregamos los paneles (cartas)
    public void agregarPaneles(){
        // Limpiamos los arrays de carta
        todos.clear();
        todos2.clear();
        seleccionados.clear();
        
        // 
        for(int i = 0; i < 10; i++){
            // Cargamos la imagen
            ImageIcon img = new ImageIcon("resources/imagenes/img"+(i+1)+".png");
            // Añadimos la imagen a un panel
            PanelCarta pc = new PanelCarta(this, img.getImage(), i+1);
            // Creamos la copia para el panel 2
            PanelCarta pc2 = new PanelCarta(this, img.getImage(), i+1);
            
            // Añadimos los paneles de cartas a los arras
            todos.add(pc);
            todos.add(pc2);
            todos2.add(pc);
            todos2.add(pc2);
        }
        
        // Verificamos que el array donde almacenamos los paneles no este vacio
        while(!todos.isEmpty()){
            // Inicializamos un numero random
            Random r = new Random();
            // Generamos un numero randon entre 0 y el total de paneles
            int x = (int)(r.nextDouble() * todos.size());
            // Añadimos la carta al panel principal
            panelPrincipal.add(todos.get(x));
            // Eliminamos la carta del array todos
            todos.remove(x);
        }
        
        // Actualizamos la interfaz del panel
        panelPrincipal.updateUI();
        // Repintamos
        repaint();
    }


    // Vaidamos si el usuario gano o perdio
    public void verificar() {
        if(seleccionados.size() == 3) {
            if (seleccionados.get(0).clave == seleccionados.get(1).clave) {
                seleccionados.get(0).bloqueado = true;
                seleccionados.get(1).bloqueado = true;
                seleccionados.clear();
                ocultar();
                contadorganador++;
            } else {
                seleccionados.clear();
                ocultar();
                contadorperdedor++;
                if (contadorperdedor == 20) {
                    JOptionPane.showMessageDialog(null, "Perdiste, Trata nuevamente.");
                    contadorperdedor = 0;
                    contadorperdedor = 0;
                    panelPrincipal.removeAll();
                    agregarPaneles();
                }
            }
        } else {
            if (contadorganador == 9 && seleccionados.size() == 2) {
                JOptionPane.showMessageDialog(null, "Ganaste!!!!");
                contadorperdedor = 0;
                contadorperdedor = 0;
            }            
        }
    }

    // Ocultamos la carta
    public void ocultar() {
        for (int i = 0; i < todos2.size(); i++) {
            todos2.get(i).sw = false;
            todos2.get(i).repaint();
            repaint();
        }
    }

    // Inicializamos el juego
    public static void main(String arg[]) {
        Main p = new Main();
        p.setVisible(true);
        p.setBounds(0, 0, 800, 600);
        p.setLocationRelativeTo(null);
        p.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
