import java.util.concurrent.BlockingQueue;

public class Spetsialist {
    private final static int APPLICATION_PROCCESING_TIME = 3000;

    private final BlockingQueue<String> request;

    public Spetsialist(BlockingQueue<String> queue) {
        this.request = queue;
    }

    public void takeAvailableCall() {
        while (!request.isEmpty()) {
            try {
                System.out.println("Обработка заявки от абонента : " + request.take() + " -> " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(APPLICATION_PROCCESING_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void getTotalApp() {
        while (!request.isEmpty()) {
            System.out.println("*** Осталось обработать заявок: " + request.size());
            try {
                Thread.sleep(APPLICATION_PROCCESING_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Все заявки отработаны!");
    }
}
