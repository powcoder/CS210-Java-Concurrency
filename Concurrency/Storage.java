https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder


import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 
 * @date 
 */
public class Storage {
    private int n;

    private int m;

    private AtomicInteger count;

    private LinkedList<Integer> list = new LinkedList<>();

    public Storage() {
    }

    public Storage(int n, int m, LinkedList<Integer> list) {
        this.n = n;
        this.m = m;
        this.list = list;
        count = new AtomicInteger(0);
    }

    public void produce() {
        while (count.intValue() < n) {
            Random random = new Random();
            synchronized (list) {
                // If the warehouse is full
                while (list.size() == m) {
                    System.out.println("warehouse is full, [producer]:  Temporarily unable to perform production tasks!");
                    try {
                        // Consumption is blocked due to unsatisfied conditions
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // produce product
                count.incrementAndGet();
//                list.add(count.intValue());
                list.add(Math.abs(random.nextInt()));
                System.out.println("[producer]: Produced a product\t [The current storage capacity is] :" + list.size());

                list.notifyAll();
            }
        }
    }

    // Consumer products
    public void consumeEven() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("even-numbers.txt");
            while (!(count.intValue() >= n && isAllnotEven())) {
                synchronized (list) {
                    //If warehouse storage is insufficient
                    while (list.size() == 0) {
                        if(count.intValue() >= n && isAllnotEven()){
                            break;
                        }
                        System.out.println("Warehouse is empty, [consumerEven] : Temporarily unable to perform consumer tasks!");
                        try {
                            // Consumption is blocked due to unsatisfied conditions
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) % 2 == 0) {
                            System.out.println("Produced a product\t [The current storage capacity is] :" + list.size());
                            System.out.println("*********************Even+:" + list.get(i));
                            fw.write(list.get(i).toString() + "\n");
                            list.remove(i);
                        }
                    }

                    list.notifyAll();
                }
            }
            fw.flush();
        } catch (Exception e) {

        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // Consumer products
    public void consumeOdd() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("odd-numbers.txt");
            while (!(count.intValue() >= n && isAllnotOdd())) {
                synchronized (list) {
                    //If warehouse storage is insufficient
                    while (list.size() == 0) {
                        if(count.intValue() >= n && isAllnotOdd()){
                            break;
                        }
                        System.out.println("Warehouse is empty, [consumerOdd]:  Temporarily unable to perform consumer tasks!");
                        try {
                            // Consumption is blocked due to unsatisfied conditions
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) % 2 == 1) {
                            System.out.println("[consumerOdd] : Consumed a product\t [The current storage capacity is] :" + list.size());
                            System.out.println("*********************Odd+:" + list.get(i));
                            fw.write(list.get(i).toString() + "\n");
                            list.remove(i);
                        }
                    }
                    list.notifyAll();
                }
            }
            fw.flush();
        } catch (Exception e) {

        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Consumer products
    public void consumeNum(int methodNum) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("div" + methodNum + "-numbers.txt");
            while (!(count.intValue() >= n && isAllnotNumberDiv(methodNum))) {
                synchronized (list) {
                    //If warehouse storage is insufficient
                    while (list.size() == 0) {
                        if(count.intValue() >= n && isAllnotNumberDiv(methodNum)){
                            break;
                        }
                        System.out.println("Warehouse is empty, [consumeNum] " + methodNum + ":  Temporarily unable to perform consumer tasks!");
                        try {
                            // Consumption is blocked due to unsatisfied conditions
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) % methodNum == 0) {
                            System.out.println(" [consumeNum] " + methodNum + ": Consumed a product\t [The current storage capacity is] :" + list.size());
                            System.out.println("*********************" + methodNum + ":" + list.get(i));
                            fw.write(list.get(i).toString() + "\n");
                            list.remove(i);
                        }
                    }
                    list.notifyAll();
                }
            }
            fw.flush();
        } catch (Exception e) {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private boolean isAllnotEven() {
        synchronized (list) {
            if (list.size() == 0) {
                return true;
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) % 2 == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    private boolean isAllnotOdd() {
        synchronized (list) {
            if (list.size() == 0) {
                return true;
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) % 2 == 1) {
                    return false;
                }
            }
            return true;
        }
    }

    private boolean isAllnotNumberDiv(int methodNum) {
        synchronized (list) {
            if (list.size() == 0) {
                return true;
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) % methodNum == 0) {
                    return false;
                }
            }

            return true;
        }
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public LinkedList<Integer> getList() {
        return list;
    }

    public void setList(LinkedList<Integer> list) {
        this.list = list;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }
}
