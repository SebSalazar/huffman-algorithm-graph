/*
 * Codigo  Desarrollado por Sebastian Salazar 
 * Correo:  Salazars804@gmail.com
 */
package controlador;

import codigohuffman.CodigoHuffman;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import vista.Lienzo;

public class Gestion implements ActionListener {

    String cadena;
    String nuevo, salida = "", salida2 = "", codi;
    CodigoHuffman objeto;
    int contImagenes = 1;

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(Driver.miventana.getInicializar())) {
            cadena = Driver.miventana.getDatos1().getText().trim();
            try {
                FileWriter fw = new FileWriter("test.txt");
                fw.write(cadena);
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Cadena a codificar: " + cadena);
            System.out.println();

            objeto = new CodigoHuffman(cadena);

            objeto.crearMatriz();
            objeto.contImagenes = contImagenes;
            contImagenes++;
            objeto.listarMatriz();

            int k, i, h;
            k = objeto.NumCaracteres();
            String encriptado;

            char[] en = new char[k];

            String result = "";
            String codifi = "";
            System.out.println("El mensaje a encriptar es: " + cadena);
            System.out.println("encriptacion: ");

            //
            int j = 0;
            String[] listar = new String[objeto.lista.size()];

            for (i = 0; i < objeto.lista.size(); i++) {
                listar[i] = (String) objeto.lista.get(i);
            }
            salida += "Sin comprimir: " + cadena.length() + "*8 =" + (cadena.length() * 8) + " bits \n";
            System.out.println("lista  " + listar[0]);
            //

            String[] listarCod = new String[objeto.lista.size()];
            for (i = 0; i < k; i++) {
                encriptado = objeto.codifica(i, en);
                System.out.println("" + objeto.lista.get(i) + " " + encriptado);
                salida += objeto.lista.get(i) + " = " + encriptado + "\n";
                objeto.listaCod.add(encriptado);
            }
            for (i = 0; i < objeto.listaCod.size(); i++) {
                listarCod[i] = (String) objeto.listaCod.get(i);
            }
            System.out.println("encript " + listarCod[0]);

            for (j = 0; j < cadena.length(); j++) {
                for (i = 0; i < k; i++) {
                    if (listar[i].equals(String.valueOf(cadena.charAt(j)))) {
                        nuevo = (String) objeto.listaCod.get(i);
                        result = result + nuevo;
                        codifi = codifi +" "+ nuevo;
                    }
                }
            }
            System.out.println("mensaje codificado " + result);
            codi = result;
            System.out.println(" ");
            System.out.println("Tamano sin comprimir: " + cadena.length() * 8);
            salida += "Comprimido: " + result.length() + "\n";
            System.out.println("tamano comprimido: " + result.length());
            double resto = (double) (result.length() * 100) / (cadena.length() * 8);
            double ahorro = 100 - resto;
            salida += "Codificado: " + String.format("%.3f", resto) + "% de espacio!\n";
            salida += "Ahorro: " + String.format("%.3f", ahorro) + "% de espacio!\n";
            char opci[];
            opci = cadena.toCharArray();
            String xd = "";
            for (int y = 0; y < opci.length; y++) {
                xd += opci[y] + "   ";
            }
            salida += "\nMENSAJE ENCRIPTADO:\n" + codifi + "\n";
            Driver.miventana.getDatos2().setText(salida);
        }

        if (e.getSource().equals(Driver.miventana.getLimpiar())) {
            Driver.miventana.getDatos1().setText("");
            Driver.miventana.getDatos2().setText("");
            Driver.miventana.getDatos3().setText("");
            salida = "";
            salida2 = "";
            codi = "";
        }

        if (e.getSource().equals(Driver.miventana.getDesco())) {
            String deco;
            deco = objeto.deCodifica(codi);
            System.out.println("Mensaje decodificado : " + deco);
            salida2 = deco;
            Driver.miventana.getDatos3().setText(salida2);
        }

    }

    public void Dibujar() {
        JFrame ventanaArbol = new JFrame();
        Lienzo lienzo = new Lienzo();
        lienzo.imagen(contImagenes);
        ventanaArbol.setSize(900, 600);
        ventanaArbol.getRootPane().setWindowDecorationStyle(1);
        ventanaArbol.setLocationRelativeTo(null);
        ventanaArbol.setUndecorated(true);
        ventanaArbol.add(lienzo);
        ventanaArbol.setVisible(true);
    }
}
