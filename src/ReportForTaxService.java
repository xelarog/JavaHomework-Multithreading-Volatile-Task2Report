import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class ReportForTaxService {
    private LongAdder totalEarning;

    public ReportForTaxService() {
        totalEarning = new LongAdder();
    }

    public void compute(int[] earning) {
        IntStream.of(earning).forEach(i -> totalEarning.add(i));
    }

    public void makeSum() {
        totalEarning.sum();
    }

    public LongAdder getTotalEarning() {
        return totalEarning;
    }

}