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
public class Producer extends Thread{

    private String producer;
    private Storage storage;

    @Override
    public void run() {
        produce(producer);
    }

    public void produce(String producer)
    {
        storage.produce();
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
