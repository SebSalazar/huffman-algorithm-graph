package report;

import java.util.ArrayList;
import java.util.List;

public class Heap {

    public List<Node> heap;

    public Heap() {

        heap = new ArrayList<Node>();
        heap.add(null); 

    }

    public void insert(Node node) {

        heap.add(node); 

        int child = heap.size() - 1;
        int parent = child / 2;
        //System.out.println("Datos: "+child+ " padre: "+parent);

        while (parent >= 1 && heap.get(child).alphaCount < heap.get(parent).alphaCount) { 

            Node temp = heap.get(child);
            heap.set(child, heap.get(parent));
            heap.set(parent, temp);

            child = parent;
            parent = child / 2;
        }
    }

    public Node sortTree() { 

        if (heap.size() <= 1) {
            return null; 
        }
        Node root = heap.get(1); 
        heap.set(1, heap.get(heap.size() - 1)); 
        heap.remove(heap.size() - 1);

        int parent = 1;
        int leftChild = parent * 2;
        int rightChild = parent * 2 + 1;

        while (leftChild <= heap.size() - 1) {

            int selectChild; 

            if (rightChild > heap.size() - 1) { 

                if (heap.get(leftChild).alphaCount >= heap.get(parent).alphaCount) {
                    break;
                }

                selectChild = leftChild; 

            } else {

                if (heap.get(leftChild).alphaCount >= heap.get(parent).alphaCount
                        && heap.get(rightChild).alphaCount >= heap.get(parent).alphaCount)
                {
                    break;
                }

                if (heap.get(leftChild).alphaCount > heap.get(rightChild).alphaCount)
                {
                    selectChild = rightChild;
                } else 
                {
                    selectChild = leftChild;
                }

            }

            if (heap.get(parent).alphaCount <= heap.get(selectChild).alphaCount) {
                break;
            }

            Node temp = heap.get(parent);
            heap.set(parent, heap.get(selectChild));
            heap.set(selectChild, temp);
            parent = selectChild;

            parent = selectChild;
            leftChild = parent * 2;
            rightChild = parent * 2 + 1;
        }
        return root;
    }

}
