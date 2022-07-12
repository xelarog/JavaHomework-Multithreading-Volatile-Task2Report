import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        final int[] numberEarningsShop = {100, 300, 250};

        final int[][] earningShops = fillArrayRandom(numberEarningsShop);

        final ReportForTaxService report = new ReportForTaxService();

        String[] threadNames = new String[numberEarningsShop.length];
        for (int i = 0; i < threadNames.length; i++) {
            threadNames[i] = "Выручка магазина " + (i + 1);
        }

        Thread[] threads = new Thread[numberEarningsShop.length];
        for (int i = 0; i < threads.length; i++) {
            int finalI = i;
            threads[i] = new Thread(null, () -> report.compute(earningShops[finalI]), threadNames[i]);
            threads[i].start();
        }

        for (Thread thread : threads)
            thread.join();

        report.makeSum();
        System.out.println("Общая дневная выручка = " + report.getTotalEarning());
    }

    private static int[][] fillArrayRandom(int[] number) {
        final int min = 30;
        final int range = 20_000;

        Random random = new Random();
        int[][] result = new int[number.length][];

        for (int i = 0; i < number.length; i++) {
            result[i] = new int[number[i]];
            for (int j = 0; j < result[i].length; j++)
                result[i][j] = random.nextInt(range) + min;
        }
        return result;
    }
}
