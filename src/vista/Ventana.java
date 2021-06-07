/*
 * Codigo  Desarrollado por Sebastian Salazar
 * Correo:  Salazars804@gmail.com
 */
package vista;

import controlador.Driver;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public final class Ventana extends JFrame {

    JPanel panelIzquierdo, panelDerecho;
    private JButton inicializar, limpiar, desco;
    private JTextArea datos1, datos2, datos3;
    JLabel texto, texto1, autor;

    Font fuente1, fuente2;
    Border bordesitosxd, bordes, bordes1, bordes2;
    Cursor mano;

    public Ventana() {
        setSize(700, 410);
        setTitle("Codigo de Huffman");
        getRootPane().setWindowDecorationStyle(1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);

        Panel1();
        Panel2();
        Componentes();
    }

    public void Panel1() {
        // Creacion del JPanel Izquierda
        panelIzquierdo = new JPanel();
        panelIzquierdo.setBounds(0, 0, getWidth() / 2, getHeight());
        panelIzquierdo.setBackground(new Color(32, 50, 72));
        panelIzquierdo.setLayout(null);
        this.add(panelIzquierdo);
        panelIzquierdo.updateUI();
    }

    public void Panel2() {
        // Creacion del JPanel Derecho
        panelDerecho = new JPanel();
        panelDerecho.setBounds(getWidth() / 2, 0, getWidth(), getHeight());
        panelDerecho.setBackground(new Color(235, 196, 56));
        panelDerecho.setLayout(null);
        this.add(panelDerecho);
        panelDerecho.updateUI();
    }

    public void Componentes() {
        /* 
            Variables graficas (bordes y fuentes)
         */
        bordesitosxd = BorderFactory.createLineBorder(new Color(235, 197, 54), 2);
        bordes = BorderFactory.createLineBorder(new Color(180, 180, 180), 2, false);
        bordes1 = BorderFactory.createLineBorder(new Color(32, 50, 72), 2);
        bordes2 = BorderFactory.createLineBorder(new Color(71, 161, 188), 2);
        //------------------------
        fuente1 = new Font("Comic Sans MS", Font.BOLD, 11);
        fuente2 = new Font("Comic Sans MS", Font.BOLD, 12);

        mano = new Cursor(HAND_CURSOR);

        /* 
            Textos
         */
        texto = new JLabel("Entrada: ");
        texto.setFont(fuente2);
        texto.setBounds(10, 10, 70, 20);
        texto.setForeground(new Color(235, 196, 56));
        panelIzquierdo.add(texto);

        texto1 = new JLabel("Salida (Codificado): ");
        texto1.setFont(fuente2);
        texto1.setForeground(new Color(32, 50, 72));
        texto1.setBounds(getWidth() / 2 + 10, 10, 410, 20);
        panelDerecho.add(texto1);
        texto1.updateUI();

        inicializar = new JButton("Realizar codificacion ");
        inicializar.setBounds(10, 310, 320, 25);
        inicializar.setBorder(bordes1);
        inicializar.setCursor(mano);
        inicializar.setFont(fuente2);
        inicializar.setBackground(new Color(235, 196, 56));
        inicializar.setForeground(new Color(32, 50, 72));
        inicializar.addActionListener(Driver.ges);
        panelIzquierdo.add(inicializar);
        inicializar.updateUI();

        limpiar = new JButton("Limpiar");
        limpiar.setBounds(71, 10, 80, 25);
        limpiar.setBorder(bordes1);
        limpiar.setCursor(mano);
        limpiar.setFont(fuente2);
        limpiar.setBackground(new Color(235, 196, 56));
        limpiar.setForeground(new Color(32, 50, 72));
        limpiar.addActionListener(Driver.ges);
        panelIzquierdo.add(limpiar);
        limpiar.updateUI();

        desco = new JButton("Descodificar");
        desco.setBounds(getWidth() / 2 + 10, 250, 150, 25);
        desco.setBorder(bordes1);
        desco.setCursor(mano);
        desco.setFont(fuente2);
        desco.setBackground(new Color(235, 196, 56));
        desco.setForeground(new Color(32, 50, 72));
        desco.addActionListener(Driver.ges);
        panelDerecho.add(desco);
        desco.updateUI();


        /* 
            TextAreapara recibir las cadenas a analizar
         */
        datos1 = new JTextArea();
        datos1.setBorder(bordes);
        datos1.setBounds(10, 35, 320, 270);
        datos1.updateUI();

        JScrollPane scro1 = new JScrollPane(datos1);
        scro1.setBounds(10, 35, 320, 270);
        panelIzquierdo.add(scro1);
        scro1.updateUI();

        datos2 = new JTextArea();
        datos2.setBorder(bordes2);
        datos2.setBounds(getWidth() / 2 + 10, 35, 320, 210);
        panelDerecho.add(datos2);
        datos2.updateUI();

        JScrollPane scro2 = new JScrollPane(datos2);
        scro2.setBounds(getWidth() / 2 + 10, 35, 320, 210);
        panelDerecho.add(scro2);
        scro2.updateUI();

        datos3 = new JTextArea();
        datos3.setBorder(bordes2);
        datos3.setBounds(getWidth() / 2 + 10, 280, 320, 90);
        panelDerecho.add(datos3);
        datos3.updateUI();

        JScrollPane scro3 = new JScrollPane(datos3);
        scro3.setBounds(getWidth() / 2 + 10, 280, 320, 90);
        panelDerecho.add(scro3);
        scro3.updateUI();

        /* 
            Autor - Sebastian Salazar
         */
        autor = new JLabel(" Presentado por: Seb Salazar ");
        autor.setBounds(new Rectangle(10, 340, 295, 30));
        autor.setFont(fuente1);
        autor.setBorder(bordesitosxd);
        autor.setForeground(new Color(235, 197, 54));
        autor.setHorizontalAlignment(JLabel.CENTER);
        panelIzquierdo.add(autor);
        autor.updateUI();
    }

    /*
        Metodos de encapsulamiento
     */
    public JButton getInicializar() {
        return inicializar;
    }

    public JButton getLimpiar() {
        return limpiar;
    }

    public JTextArea getDatos1() {
        return datos1;
    }

    public JTextArea getDatos2() {
        return datos2;
    }

    public JButton getDesco() {
        return desco;
    }

    public JTextArea getDatos3() {
        return datos3;
    }

}
