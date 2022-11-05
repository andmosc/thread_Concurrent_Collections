import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    private final static int CALL_WAITING = 1000;
    private final static int CAPACITY_APP = 600;

    public static void main(String[] args) {

        BlockingQueue<String> atc = new ArrayBlockingQueue<>(CAPACITY_APP);

        CallGeneration cal = new CallGeneration(atc);

        Spetsialist sp1 = new Spetsialist(atc);
        Spetsialist sp2 = new Spetsialist(atc);
        Spetsialist sp3 = new Spetsialist(atc);


        new Thread(cal::generationCall).start();
        try {
            Thread.sleep(CALL_WAITING);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(sp1::takeAvailableCall,"operator1").start();
        new Thread(sp2::takeAvailableCall,"operator2").start();
        new Thread(sp3::takeAvailableCall,"operator3").start();

        new Thread(sp1::getTotalApp).start();
    }
}
