https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder


/**
 * @author 
 * @date
 */
public class Consumer extends Thread{
    private String consumer;
    private Storage storage;
    private int methodNum;

    @Override
    public void run() {
        consumerMethod(methodNum);
    }

    private void consumerMethod(int methodNum){
        if(methodNum == 1){
            storage.consumeOdd();
        }else if(methodNum == 2){
            storage.consumeEven();
        }else if(methodNum >= 3){
            storage.consumeNum(methodNum);
        }
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public int getMethodNum() {
        return methodNum;
    }

    public void setMethodNum(int methodNum) {
        this.methodNum = methodNum;
    }
}
