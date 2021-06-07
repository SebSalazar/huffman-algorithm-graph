/*
 * Codigo  Desarrollado por Sebastian Salazar
 * Correo:  Salazars804@gmail.com
 */
package vista;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class Lienzo extends JPanel{
    
    int contImagenes;
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Toolkit t = Toolkit.getDefaultToolkit ();
        Image imagen = t.getImage ("Grafico_Huffman"+(contImagenes-1)+".jpg");
        g.drawImage (imagen, 10, 30, this);
    }
    
    public void imagen (int con){
        this.contImagenes = con;
    }

}
