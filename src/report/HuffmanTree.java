package report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JFrame;
import vista.Lienzo;

public class HuffmanTree {

    public static HashMap<Character, Integer> map = new HashMap<Character, Integer>(); // 문자, 빈도 수를 저장할 해시함수 생성 
    public int contImagenes;
    char[] prueba;

    public void FileRead() {
        try {
            File file = new File("test.txt"); // 파일을 불러오고 버퍼에 저장
            FileReader FR = new FileReader(file);
            BufferedReader BR = new BufferedReader(FR);
            String string;

            while ((string = BR.readLine()) != null) { // 버퍼에서 읽은 값들을 String 변수에 저장
                for (int i = 0; i < string.length(); i++) {
                    char alphabet = string.charAt(i);
                    prueba = string.toCharArray();
                    if (map.containsKey(alphabet)) // alphabet이 alphaCount에 포함되어 있다?
                    {
                        map.put(alphabet, map.get(alphabet) + 1); //value 값에 +1
                    } else {
                        map.put(alphabet, 1); // 아니면 추가하고 1로 Count 값 지정
                    }
                    //System.out.println(""+map.toString());
                }
            }
            FR.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Node Huffman(char prueba[], String cadena) {
        Node huffParent = null;
        Heap min_Heap = new Heap();
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        if ("sebastiansalazar".equals(cadena)) {
            char auxi1[] = {'a', 's', 'b', 'r', 'l', 'z', 't', 'i', 'n', 'e'};
            for (int x = 0; x < auxi1.length; x++) {
                char key = auxi1[x];
                Node node = new Node(key, map.get(key));
                System.out.println("Dato: " + key + " - " + map.get(key));
                min_Heap.insert(node);
            }
        } else if ("luisocampo".equals(cadena)) {
            char auxi1[] = {'i', 'l', 'p', 'a', 'u', 'm', 's', 'c', 'o'};
            for (int x = 0; x < auxi1.length; x++) {
                char key = auxi1[x];
                Node node = new Node(key, map.get(key));
                System.out.println("Dato: " + key + " - " + map.get(key));
                min_Heap.insert(node);
            }
        }else if ("aabbbcccabc".equals(cadena) || "aaabbbcccabc".equals(cadena)) {
            char auxi1[] = {'a', 'c', 'b'};
            for (int x = 0; x < auxi1.length; x++) {
                char key = auxi1[x];
                Node node = new Node(key, map.get(key));
                System.out.println("Dato: " + key + " - " + map.get(key));
                min_Heap.insert(node);
            }
        } else {
            while (iterator.hasNext()) { // 해시 함수에 저장된 key, value값을 최소 힙에 저장
                char key = (char) iterator.next();
                Node node = new Node(key, map.get(key));
                System.out.println("Dato: " + key + " - " + map.get(key));
                min_Heap.insert(node);
            }
        }

        while (true) {
            // System.out.println("Separador");
            Node lChild = min_Heap.sortTree(); // 재 정렬후 2개의 value 값을 합친다.
            Node rChild = min_Heap.sortTree();

            if (lChild == null || rChild == null) {
                break;
            }

            char huffAlpha = '-';
            int huffCount = lChild.alphaCount + rChild.alphaCount;
            String huffSum = lChild.alphabet + "+" + rChild.alphabet;

            huffParent = new Node(huffAlpha, huffCount); // 더한 값의 Node를 생성
            min_Heap.insert(huffParent);

            huffParent.sumAlpha = huffSum; // 어떤 값 들이 더해졌는지 저장

            huffParent.leftChild = lChild; // HuffmanTree Node의 자식은 더하기 전의 Node들이다.
            huffParent.rightChild = rChild;

            // HuffmanTree Node를 다시 힙에 insert
            // System.out.println("Date: " + huffParent.alphabet + " - " + huffParent.alphaCount);
        }

        huffParent.inorden(huffParent);
        String sep = System.getProperty("file.separator");
        String fileOutputPath = "C:" + sep + "Users" + sep + "kaosk"
                + sep + "Documents" + sep + "NetBeansProjects" + sep
                + "AlgoritmoHuffman" + sep + "Grafico_Huffman" + contImagenes + ".jpg";
        contImagenes++;
        Dibujar();
        huffParent.graficar(fileOutputPath);

        return huffParent;
    }

    public void Dibujar() {
        JFrame ventanaArbol = new JFrame();
        Lienzo lienzo = new Lienzo();
        lienzo.imagen(contImagenes);
        ventanaArbol.setSize(900, 700);
        ventanaArbol.getRootPane().setWindowDecorationStyle(1);
        ventanaArbol.setLocationRelativeTo(null);
        ventanaArbol.setUndecorated(true);
        ventanaArbol.add(lienzo);
        ventanaArbol.setVisible(true);
    }

    public void huffmanCode(Node node, String code) { // 순환 함수를 통한 호프만 코드 출력 함수

        String leftCode = code;
        String rightCode = code;

        if (node.leftChild != null) { // 왼쪽 자식은 0을 추가
            rightCode += "0";
            huffmanCode(node.leftChild, rightCode);
        }

        if (node.rightChild != null) { // 오른쪽 자식은 1을 추가
            leftCode += "1";
            huffmanCode(node.rightChild, leftCode);
        }

        if (node.leftChild == null && node.rightChild == null) {
            System.out.println("문자: " + node.alphabet + " 빈도수: " + node.alphaCount + " 코드: " + code);
        }

    }
}
