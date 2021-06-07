
package codigohuffman;

import java.util.ArrayList;
import java.util.List;
import report.HuffmanTree;
import report.Node;


public class CodigoHuffman {

    int[][] m;
    int tamano;
    final int simbolo = 0;
    final int frecuencia = 1;
    final int padre = 2;
    final int tipo = 3;
    final int izq = 4;
    final int der = 5;
    public static List lista = new ArrayList();
    public static List listaCod = new ArrayList();
    public static String nuevo;
    public static String compara;
    public int contImagenes;
    String entrada;

    public CodigoHuffman(String mensaje) {
        //se inicializa la fila simbolo de la matriz
        //se inicializa la fila frecuencia de la matriz
        //se usa para escribir los numeros de la matriz
        int i, j;
        entrada = mensaje;
        int[] temp = new int[26];
        for (i = 0; i < 26; i++) {
            temp[i] = 0;
        }
        for (i = 0; i < mensaje.length(); i++) {
            temp[mensaje.charAt(i) - 97]++;
        }
        tamano = 0;
        for (i = 0; i < 26; i++) {
            if (temp[i] != 0) {
                tamano++;
            }
        }
        int col = tamano * 2 - 1;
        m = new int[6][col]; //filas y columnas de la matriz

        for (i = 0; i < 6; i++) {
            for (j = 0; j < col; j++) {
                m[i][j] = 0;
            }
        }
        j = 0;

        for (i = 0; i < 26; i++) {
            if (temp[i] != 0) {
                m[simbolo][j] = i; //letra
                System.out.println("Simbolo: " + (char) (i + 97));
                lista.add(String.valueOf((char) (i + 97)));
                m[frecuencia][j] = temp[i]; //frecuencia
                j++;
            }
        }
        System.out.println("Tamano: " + tamano);

    }

    public void crearMatriz() {
        //crea la matriz
        int valor, k, i;
        int[] menor = new int[2];
        int nuevo = tamano;
        for (i = 0; i < (tamano - 1); i++, nuevo++) {
            //System.out.println("i"+i);
            //menor[0]=frecuencia;
            //System.out.println("menor"+menor[1]);
            //System.out.println("m1 "+m);
            valor = MenorFrecuencia(menor);
            m[tipo][menor[0]] = 1; //izquierda
            m[tipo][menor[1]] = 2; //derecha
            m[frecuencia][nuevo] = valor;
            m[padre][menor[0]] = nuevo;
            m[padre][menor[1]] = nuevo;
            m[izq][nuevo] = menor[0];
            m[der][nuevo] = menor[1];
        }
    }

    public String codifica(int ind, char[] en) {
        int lon = 0;
        int j;
        //System.out.println("en "+en[0]);
        while (m[padre][ind] != 0) {
            for (j = lon; j >= 0; j--) {
                en[j + 1] = en[j];
            }
            en[0] = (char) (m[tipo][ind] + 47);

            ind = m[padre][ind];
            lon++;
        }
        //System.out.println(" en "+en[0]+" lon "+lon);
        return new String(en, 0, lon);
    }

    public int NumCaracteres() {
        return tamano;
    }

    public int listarMatriz() {
        int i, j, auxi = 0;
        //lista el numero de columnas de la matriz
        for (j = 0; j < (tamano * 2 - 1); j++) {
            //System.out.println(alinear(j));
        }
        System.out.println(" ");

        //lista el contenido de la matriz
        for (i = 0; i < 6; i++) {
            for (j = 0; j < (tamano * 2 - 1); j++) {
                System.out.println(m[i][j]);
            }
            System.out.println(" ");
        }
        System.out.println("Raiz: " + m[1][j - 1]);
        String prueba[] = new String[lista.size()];
        char prueba2[] = new char[lista.size()];

        for (int z = 0; z < lista.size(); z++) {
            prueba[z] = (String) lista.get(z);
            prueba2[z] = prueba[z].charAt(0);
        }

        String str = "";
        HuffmanTree huf = new HuffmanTree();
        huf.FileRead();
        huf.contImagenes = contImagenes;
        Node root = huf.Huffman(prueba2, entrada);
        System.out.println("---- Huffman Algoritmo ----");
        huf.huffmanCode(root, str);

        return 0;
    }

    public String alinear(int numero) {
        int i;
        String aux = "" + numero;
        for (i = aux.length(); i < 4; i++) {
            aux = aux = "";
        }
        return aux;
    }

    public int MenorFrecuencia(int[] menor) {
        //escoje las columnas donde se almacena las letras de menor frecuencia
        int m1, m2;
        m1 = m2 = 32767;
        int i = 0, tp;
        while (m[frecuencia][i] != 0) {
            if (m[tipo][i] == 0) {
                if (m2 > m[frecuencia][i]) {
                    if (m1 > m2) {
                        m1 = m2;
                        menor[0] = menor[1];
                    }
                    m2 = m[frecuencia][i];
                    menor[1] = i;
                    if (m1 > m2) {
                        tp = m2;
                        m2 = m1;
                        m1 = tp;
                        tp = menor[1];
                        menor[1] = menor[0];
                        menor[0] = tp;
                    }
                }
            }
            i++;
        }
        return m[frecuencia][menor[0]] + m[frecuencia][menor[1]];
    }

    public String deCodifica(String cad) {
        int raiz, i = 0;
        String deco = "";
        //System.out.println(""+tamano);
        //System.out.println("len "+cad);
        while (i < cad.length()) {
            raiz = tamano * 2 - 2;
            while (raiz >= tamano) {
                if (cad.charAt(i) == '0') {
                    raiz = m[izq][raiz];
                } else {
                    raiz = m[der][raiz];
                }

                i++;
            }
            //Adiciona el siguiente caracter a la cadena deco
            String aux = deco;
            //almacena el caracter encontrado en aux1
            // System.out.println(simbolo);
            //System.out.println(raiz);
            char[] aux1 = {(char) (m[simbolo][raiz] + 97)};
            String cadena = new String(aux1);
            aux = aux + cadena;
            deco = aux;

            //System.out.println("caracter "+cadena);
        }

        //System.out.println("deco "+deco);
        return deco;
    }

}
