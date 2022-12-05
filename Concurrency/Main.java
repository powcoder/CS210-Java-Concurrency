https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author 
 * @date 
 */
public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        int m = 4;
        int n = 400;
        int k = 3;
        Storage storage = new Storage(n, m, linkedList);
        Producer producer = new Producer();
        producer.setStorage(storage);
        producer.start();

        List<Consumer> consumerList = new ArrayList<>(k);
        for(int i=1; i<=k; i++){
            Consumer consumer = new Consumer();
            consumer.setMethodNum(i);
            consumer.setStorage(storage);
            consumerList.add(consumer);
        }

        consumerList.forEach(c->c.start());
    }
}
