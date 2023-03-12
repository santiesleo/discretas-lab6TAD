package datastructures;

public class Node <K extends Comparable, T> {

    private K key;
    private T value;

    //
    private Node right;
    private Node left;

    public Node(K key, T value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<K, T> getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node<K, T> getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }
}
