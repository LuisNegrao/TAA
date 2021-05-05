package randomized.SkipList;

import randomized.node.SkipNode;

import java.util.Random;

public class SkipList {

    public SkipNode header;
    public SkipNode tail;

    private int MAX_LEVELS;

    public SkipList(int MAX_LEVELS) {
        this.header = new SkipNode(Integer.MIN_VALUE);
        this.tail = new SkipNode(Integer.MAX_VALUE);
        this.header.next = tail;
        this.tail.prev = header;
        int i = 1;
        this.MAX_LEVELS = MAX_LEVELS;

        SkipNode h = this.header;
        SkipNode t = this.tail;
        while (i < MAX_LEVELS) {
            h.above = new SkipNode(Integer.MIN_VALUE);
            h.above.bellow = h;

            t.above = new SkipNode(Integer.MAX_VALUE);
            t.above.bellow = t;

            t = t.above;
            h = h.above;

            h.next = t;
            t.prev = h;

            i++;
        }
    }

    public void print() {
        SkipNode n = this.header;

        for (int i = 0; i < this.MAX_LEVELS; i++) {
            SkipNode n1 = n;
            while (n1 != null) {
                if(n1.key == Integer.MAX_VALUE) System.out.printf("+inf ");
                else if(n1.key == Integer.MIN_VALUE) System.out.printf("-inf ");
                    else System.out.print(n1.key + " ");
                n1 = n1.next;
            }
            System.out.println();
            n = n.above;
        }
    }

    public Boolean insert(int key) {
        SkipNode[] arr = this.modded_search(key);
        SkipNode n = arr[0];
        int level =0;

        if(this.contains(key))return false;

        //Auxiliar Node
        SkipNode aux = n.next;
        //Create the new Node
        SkipNode new_node = new SkipNode(key);

        //Make the insertion for level 0
        n.next= new_node;
        new_node.prev = n;
        new_node.next = aux;
        aux.prev = new_node;
        level++;

        //Start generating new node levels if true until the MAX_LEVELS
        while(new Random().nextBoolean() && level < this.MAX_LEVELS){
            //create the reference to the new node
            SkipNode new_above_node = new SkipNode(key);
            new_node.above = new_above_node;
            new_above_node.bellow = new_node;

            //Same process as above
            n = arr[level];
            aux = n.next;
            n.next = new_above_node;
            new_above_node.prev= n;
            new_above_node.next = aux;
            aux.prev = new_above_node;

            //Next iteration
            level++;
            new_node = new_above_node;
        }

        return true;
    }

    public void delete(int key) {
        SkipNode n = this.search(key);

        //Node not found return null
        if(n.key != key)return;

        while(n != null){
            //Remove the old node
            SkipNode previous = n.prev;
            SkipNode next = n.next;
            previous.next = next;
            next.prev = previous;
            n = n.above;
        }

    }

    public Boolean contains(int key){
        SkipNode n = this.search(key);
        return n.key ==key;
    }

    public SkipNode[] modded_search(int key) {
        SkipNode[] arr = new SkipNode[this.MAX_LEVELS];
        SkipNode n = this.header;
        int level = 0;
        while (n.above != null) {
            n = n.above;
            level++;

        }

        while (n != null) {
            SkipNode n1 = n;
            while (n1.next != null && key >= n1.next.key) {
                n1 = n1.next;

            }
            arr[level] = n1;
            level--;
            n = n.bellow;
        }
        return arr;
    }

    public SkipNode search(int key) {
        SkipNode n = this.header;
        SkipNode n1 = null;
        //int level =0;
        while (n.above != null) {
           // level++;
            n = n.above;
        }

        while (n != null) {
            n1 = n;
            while (n1.next != null && key >= n1.next.key) {
                n1 = n1.next;
            }
            //System.out.println("At level :"+level+",value :"+n1.key);
            n = n.bellow;
           // level--;
        }
        return n1;
    }


   /* Search(list, searchKey)
    x := list -> header
-- loop invariant: x -> key  level downto 0 do
            while x -> forward[i] -> key  forward[i]
    x := x -> forward[0]
            if x -> key = searchKey then return x -> value
else return failure*/

    /*Delete(list, searchKey)
local update[0..MaxLevel+1]
x := list -> header
for i := list -> level downto 0 do
    while x -> forward[i] -> key  forward[i]
    update[i] := x
x := x -> forward[0]
if x -> key = searchKey then
    for i := 0 to list -> level do
        if update[i] -> forward[i] ≠ x then break
        update[i] -> forward[i] := x -> forward[i]
    free(x)
    while list -> level > 0 and list -> header -> forward[list -> level] = NIL do
        list -> level := list -> level – 1*/
}
/*
* randomLevel()
lvl := 1
//random() that returns a random value in [0...1)
while random() < p and lvl < MaxLevel do
lvl := lvl + 1
return lvl
* */
/*
* Insert(list, searchKey)
local update[0...MaxLevel+1]
x := list -> header
for i := list -> level downto 0 do
    while x -> forward[i] -> key  forward[i]
update[i] := x
x := x -> forward[0]
lvl := randomLevel()
if lvl > list -> level then
for i := list -> level + 1 to lvl do
    update[i] := list -> header
    list -> level := lvl
x := makeNode(lvl, searchKey, value)
for i := 0 to level do
    x -> forward[i] := update[i] -> forward[i]
    update[i] -> forward[i] := x
* */

// C++ code for inserting element in skip list

/*
#include <bits/stdc++.h>
        using namespace std;

// Class to implement node
class Node
{
    public:
    int key;

    // Array to hold pointers to node of different level
    Node **forward;
    Node(int, int);
};

Node::Node(int key, int level)
        {
        this->key = key;

        // Allocate memory to forward
        forward = new Node*[level+1];

        // Fill forward array with 0(NULL)
        memset(forward, 0, sizeof(Node*)*(level+1));
        };

// Class for Skip list
class SkipList
{
    // Maximum level for this skip list
    int MAXLVL;

    // P is the fraction of the nodes with level
    // i pointers also having level i+1 pointers
    float P;

    // current level of skip list
    int level;

    // pointer to header node
    Node *header;
    public:
    SkipList(int, float);
    int randomLevel();
    Node* createNode(int, int);
    void insertElement(int);
    void displayList();
};

SkipList::SkipList(int MAXLVL, float P)
        {
        this->MAXLVL = MAXLVL;
        this->P = P;
        level = 0;

        // create header node and initialize key to -1
        header = new Node(-1, MAXLVL);
        };

// create random level for node
        int SkipList::randomLevel()
        {
        float r = (float)rand()/RAND_MAX;
        int lvl = 0;
        while (r < P && lvl < MAXLVL)
        {
        lvl++;
        r = (float)rand()/RAND_MAX;
        }
        return lvl;
        };

// create new node
        Node* SkipList::createNode(int key, int level)
        {
        Node *n = new Node(key, level);
        return n;
        };

// Insert given key in skip list
        void SkipList::insertElement(int key)
        {
        Node *current = header;

        // create update array and initialize it
        Node *update[MAXLVL+1];
        memset(update, 0, sizeof(Node*)*(MAXLVL+1));

    */
/*    start from highest level of skip list
        move the current pointer forward while key
        is greater than key of node next to current
        Otherwise inserted current in update and
        move one level down and continue search
    *//*

        for (int i = level; i >= 0; i--)
        {
        while (current->forward[i] != NULL &&
        current->forward[i]->key < key)
        current = current->forward[i];
        update[i] = current;
        }

    */
/* reached level 0 and forward pointer to
       right, which is desired position to
       insert key.
    *//*

        current = current->forward[0];

    */
/* if current is NULL that means we have reached
       to end of the level or current's key is not equal
       to key to insert that means we have to insert
       node between update[0] and current node *//*

        if (current == NULL || current->key != key)
        {
        // Generate a random level for node
        int rlevel = randomLevel();

        // If random level is greater than list's current
        // level (node with highest level inserted in
        // list so far), initialize update value with pointer
        // to header for further use
        if (rlevel > level)
        {
        for (int i=level+1;i<rlevel+1;i++)
        update[i] = header;

        // Update the list current level
        level = rlevel;
        }

        // create new node with random level generated
        Node* n = createNode(key, rlevel);

        // insert node by rearranging pointers
        for (int i=0;i<=rlevel;i++)
        {
        n->forward[i] = update[i]->forward[i];
        update[i]->forward[i] = n;
        }
        cout << "Successfully Inserted key " << key << "\n";
        }
        };

// Display skip list level wise
        void SkipList::displayList()
        {
        cout<<"\n*****Skip List*****"<<"\n";
        for (int i=0;i<=level;i++)
        {
        Node *node = header->forward[i];
        cout << "Level " << i << ": ";
        while (node != NULL)
        {
        cout << node->key<<" ";
        node = node->forward[i];
        }
        cout << "\n";
        }
        };

// Driver to test above code
        int main()
        {
        // Seed random number generator
        srand((unsigned)time(0));

        // create SkipList object with MAXLVL and P
        SkipList lst(3, 0.5);

        lst.insertElement(3);
        lst.insertElement(6);
        lst.insertElement(7);
        lst.insertElement(9);
        lst.insertElement(12);
        lst.insertElement(19);
        lst.insertElement(17);
        lst.insertElement(26);
        lst.insertElement(21);
        lst.insertElement(25);
        lst.displayList();
        }*/
