import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

public class CallGeneration {
    private final static int CALL_INTERRUPTION = 200;
    private final static int TOTAL_APP = 50;

    private final BlockingQueue<String> call;
    private Random random = new Random();
    private String number;

    public CallGeneration(BlockingQueue<String> call) {
        this.call = call;
    }

    public void generationCall() {
        for (int i = 0; i < TOTAL_APP; i++) {
            number = ("+7 (" + generation(3) + ") " + generation(6));
            try {
                call.put(number);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(CALL_INTERRUPTION);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String generation(int streamSize) {
        return random.ints(streamSize, 0, 9)
                .mapToObj(String::valueOf).collect(Collectors.joining());
    }
}
