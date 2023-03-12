package datastructures;

public class BST <K extends Comparable, T> implements IBinarySearchTree<K, T> {

    private Node<K, T> root;

    @Override
    public T getRoot() {
        if(root == null){
            return null;
        }
        return this.root.getValue();
    }

    @Override
    public T search(K key) {
        return search(root, key);
    }

    private T search(Node<K, T> current, K goal) {
        if (current == null) {
            //System.out.println("Not found");
            return null;
        } else if (current.getKey().equals(goal)) {
            return current.getValue();
        }
        if (goal.compareTo(current.getKey()) < 0) {
            return search(current.getLeft(), goal);
        } else {
            return search(current.getRight(), goal);
        }
    }


    @Override
    public void insert(K key, T value) {
        if (root == null) {
            root = new Node<K, T>(key, value);
            //System.out.println(root.getKey());
        } else {
            insert(key, value, root);
        }
    }

    private void insert(K key, T value, Node current) {
        if (key.compareTo(current.getKey()) < 0) {
            //Ingresar a la izquierda
            if (current.getLeft() == null) {
                current.setLeft(new Node<K, T>(key, value));
            } else {
                insert(key, value, current.getLeft());
            }
        } else if (key.compareTo(current.getKey()) > 0) {
            //Ingresar a la derecha
            if (current.getRight() == null) {
                current.setRight(new Node<K, T>(key, value));
            } else {
                insert(key, value, current.getRight());
            }
        } else {
            //No hacer nada
        }
    }

    private Node<K, T> getMin(Node<K, T> current){
        if(current.getLeft() == null){
            return current;
        }else {
            return (current.getLeft());
        }
    }

    @Override
    public T delete(K goal) {
        return delete(null, root, goal);
    }

    private T delete(Node<K, T> parent, Node<K, T> current, K goal){
        if(current == null){
            return null;
        }if(goal.equals(current.getKey())){ //Encontramos al nodo
            T value = current.getValue();
            if(current.getRight() == null && current.getLeft() == null){ //Es un nodo hoja
                if(parent == null){
                    root = null;
                }else if(parent.getLeft() == current){ //Es un nodo hoja izquierdo
                    parent.setLeft(null);
                }else if(parent.getRight() == current){ //Es un nodo hoja derecho
                    parent.setRight(null);
                }
            } else if (current.getRight() != null && current.getLeft() == null) { //Nodo a eliminar tiene un hijo derecho
                Node<K, T> child = current.getRight();
                if(parent == null){
                    root = child;
                } else if(parent.getRight() == current){ //El nodo a eliminar tiene un hijo derecho y está a la derecha del padre
                    parent.setRight(current.getRight());
                }else {                           //El nodo a eliminar tiene un hijo derecho y está a la izquierda del padre
                    parent.setLeft(current.getRight());
                }
            } else if (current.getLeft() != null && current.getRight() == null) { //Nodo a eliminar tiene un hijo izquierdo
                if(parent.getLeft() == current){ //El nodo a eliminar tiene un hijo izquierdo y está a la izquierda del padre
                    parent.setLeft(current.getLeft());
                }else {                          //El nodo a eliminar tiene un hijo izquierdo y está a la derecha del padre
                    parent.setRight(current.getLeft());
                }
            } else if (current.getRight() != null && current.getLeft() != null){ //Nodo a eliminar tiene dos hijos
                Node<K, T> successor = getMin(current.getRight());
                //Sobreescribir la key y los valores
                current.setKey(successor.getKey());
                current.setValue(successor.getValue());
                //Si tienen valores hacer current.setValue(sucessor.getValue())
                delete(current, current.getRight(), (K) successor.getKey());
            }
            return value;
        }else if(goal.compareTo(current.getKey()) < 0){
            return delete(current, current.getLeft(), goal);
        } else if (goal.compareTo(current.getKey()) > 0) {
            return delete(current, current.getRight(), goal);
        }
        return null;
    }



    @Override
    public String inOrder() {
        return inOrder(root).trim();
    }

    private String inOrder(Node<K, T> current){
        if(current == null){
            //current.getKey()
            //System.out.println("No hay nada");
            return "";
        }else {
            return inOrder(current.getLeft()) + " " + current.getKey() + inOrder(current.getRight());
        }
    }
}
