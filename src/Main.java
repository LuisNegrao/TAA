import probabilistic.Treaps.Treap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Treap<Integer> t = new Treap<>();
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            l.add(i);
        }
        while(!l.isEmpty()){
            int index = new Random().nextInt(l.size());
            t.insert(l.get(index));
            l.remove(index);
        }
        t.print();
       search(t,8);
       t.delete(11);
       t.print();
       search(t,8);
    }

public static void search(Treap<Integer> t,int value){
    if (t.search(value)) {
        System.out.println("Value "+ value +" was found!");
    }else System.out.println("Value "+ value +" was not found!");
}
}
