package probabilistic.Treaps;

import probabilistic.Treaps.TNode;

import java.util.List;

public class Treap<T extends Comparable<T>> {

    private TNode<T> root;
    private TNode<T> nil = new TNode<>();

    public Treap() {
        this.root = nil;
    }

    //------INSERT----------
    public void insert(T key) {

        root = insert(root, key);
    }

    //Recursive insert
    private TNode<T> insert(TNode<T> cur, T key) {
        //Same process as main insert , but with recursion
        if (cur == nil) {
            return new TNode<>(key, nil, nil);
        }
        if (cur.isGreaterThan(key)) {
            cur.left = insert(cur.left, key);

            if (cur.left.getPriority() >= cur.getPriority()) {
                return rotateRight(cur);
            }
        } else if (cur.isLowerThan(key)) {
            cur.right = insert(cur.right, key);

            if (cur.right.getPriority() >= cur.getPriority()) {
                return rotateLeft(cur);
            }
        }
        return cur;
    }

    //PRINT
    public void print() {
        print(root, 0);
    }

    private void print(TNode<T> node, int depth) {
        if (node != nil) {
            print(node.right, depth + 10);
            System.out.print("\n");
            for (int i = 0; i < depth; i++) {
                System.out.print(" ");
            }
            System.out.print(node.getKey() + "|" + node.getPriority() + "\n");

            print(node.left, depth + 10);
        }
    }

    //-----ROTATIONS------
    private TNode<T> rotateRight(TNode<T> cur) {
        TNode<T> left = cur.left;
        cur.left = left.right;
        left.right = cur;
        return left;

    }

    private TNode<T> rotateLeft(TNode<T> cur) {

        TNode<T> right = cur.right;
        cur.right = right.left;
        right.left = cur;
        return right;
    }

    //------DELETE---------
    public void delete(T key) {
        if(this.search(key)) {
            System.out.println("Node "+key+" exists , let's delete him");
            root = delete(root, key);
        }else System.out.println("Node "+key+" does not exist");
    }
    private TNode<T> delete(TNode<T> cur , T key){
        if(cur == nil) return cur;

        //Continue the regression
        if(cur.isGreaterThan(key)){
            cur.left = delete(cur.left, key);
        }
        else if(cur.isLowerThan(key)){
            cur.right = delete(cur.right,key);
        }
        //Check if either of the sides is null
        else if(cur.right == nil){
            cur = cur.left;
        }
        else if(cur.left == nil){
            cur = cur.right;
        }
        //If both sides exist, check priority
        else if(cur.left.getPriority() < cur.right.getPriority()){
            cur = rotateLeft(cur);
            cur.left = delete(cur.left,key);
        }
        else{
            cur = rotateRight(cur);
            cur.right = delete(cur.right,key);
        }
    return cur;
    }

    //------SEARCH---------
    public boolean search(T key) {
        return search(root,key);
    }
    private boolean search(TNode<T> cur,T key) {
        if(cur == nil)
            return false;

        else if (cur.isEqualTo(key)) {
            return true;
        }

        else if (cur.isLowerThan(key)) {
            return search(cur.right, key);
        }

        return search(cur.left, key);
    }


    //--------SPLIT TREE IN 2 TREES-----
    public List<Treap<T>> split(Treap<T> t, int indexOfSplit) {
        return null;
    }

    //---------MERGE TREES------------
    public void merge(Treap<T> right, Treap<T> left) {
    }

    /*Auxiliar methods*/
    //Empty the tree
    public void dumpTree() {
        root = nil;
    }

    //Check is tree is empty
    public Boolean isEmpty() {
        return root == nil;
    }


    @Override
    public String toString() {
        return "Treap{" +
                "root=" + root.toString() +
                '}';
    }
}
