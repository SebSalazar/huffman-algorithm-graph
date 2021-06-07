package report;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Node {

    int alphaCount;
    char alphabet;
    Node leftChild;
    Node rightChild;
    String sumAlpha;
    private static int correlativo = 1;
    private final int id;

    public Node(char alphabet, int alphaCount) {
        this.alphabet = alphabet;
        this.alphaCount = alphaCount;
        leftChild = null;
        rightChild = null;
        this.id = correlativo++;
    }

    public void graficar(String path) {
        FileWriter fichero = null;
        PrintWriter escritor;
        try {
            fichero = new FileWriter("Codigo_Grafico.txt");
            escritor = new PrintWriter(fichero);
            escritor.print(getCodigoGraphviz());
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo Codigo_Grafico.txt");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (IOException e2) {
                System.err.println("Error al cerrar el archivo Codigo_Grafico.txt");
            }
        }
        try {
            String sep = System.getProperty("file.separator");
            String dotPath = "C:" + sep + "Program Files (x86)" + sep
                    + "Graphviz2.38" + sep + "bin" + sep + "dot.exe";
            String fileInputPath = "C:" + sep + "Users" + sep + "kaosk" + sep
                    + "Documents" + sep + "NetBeansProjects" + sep
                    + "AlgoritmoHuffman" + sep + "Codigo_Grafico.txt";

            String tParam = "-Tjpg"; //si se quisiera una imagen png se pondria -Tpng
            String tOParam = "-o"; // este parametro indica la ruta donde se va guardar la imagen con el arbol

            String[] cmd = new String[5];
            cmd[0] = dotPath; //esta ruta es donde se encuentra el dot.exe del Graphviz 
            cmd[1] = tParam; //esta ruta es el parametro -Tjpg para generar un jpg 
            cmd[2] = fileInputPath; // esta es la ruta donde se encuentra el codigo de Graphviz es decir el txt
            cmd[3] = tOParam;
            cmd[4] = path; //esta ruta es donde se guardara la imagen 
            Runtime rt = Runtime.getRuntime();
            //Esta es la ejecucion del script que nos genera el Graphviz 
            rt.exec(cmd[0] + " " + cmd[1] + " " + cmd[2] + " " + cmd[3] + " " + cmd[4]);
            //Esperamos medio segundo para dar tiempo a que la imagen se genere.
            //Para que no sucedan errores en caso de que se decidan graficar varios
            //árboles sucesivamente.
            Thread.sleep(500);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo Codigo_Grafico.txt");
        }
    }

    public void inorden(Node raiz) {
        if (raiz != null) {
            inorden(raiz.leftChild);
            System.out.print(" " + raiz.alphaCount);
            inorden(raiz.rightChild);
        }

    }

    /**
     * Método que retorna el código que grapviz usará para generar la imagen del
     * árbol binario de búsqueda.
     */
    private String getCodigoGraphviz() {
        return "digraph grafica{\n"
                + "rankdir=TB;\n"
                + "node [shape = record, style=filled, fillcolor=seashell2];\n"
                + getCodigoInterno()
                + "}\n";
    }

    /**
     * Genera el código interior de graphviz, este método tiene la
     * particularidad de ser recursivo, esto porque recorrer un árbol de forma
     * recursiva es bastante sencillo y reduce el código considerablemente.
     */
    private String getCodigoInterno() {
        String etiqueta;
        if (leftChild == null && rightChild == null) {
            etiqueta = "nodo" + id + " [ label =\"" + alphabet + "\"];\n";
        } else {
            etiqueta = "nodo" + id + " [ label =\"<C0>|" + alphaCount + "|<C1>\", fillcolor=\"lightblue\"];\n";
        }
        if (leftChild != null) {
            etiqueta = etiqueta + leftChild.getCodigoInterno()
                    + "nodo" + id + ":C0->nodo" + leftChild.id + "\n";
        }
        if (rightChild != null) {
            etiqueta = etiqueta + rightChild.getCodigoInterno()
                    + "nodo" + id + ":C1->nodo" + rightChild.id + "\n";
        }
        return etiqueta;
    }

}
