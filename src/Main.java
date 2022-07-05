import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        final int numberEarningsShop1 = 100;
        final int numberEarningsShop2 = 300;
        final int numberEarningsShop3 = 250;

        final int[] earningShop1 = fillArrayRandom(numberEarningsShop1);
        final int[] earningShop2 = fillArrayRandom(numberEarningsShop2);
        final int[] earningShop3 = fillArrayRandom(numberEarningsShop3);

        final ReportForTaxService report = new ReportForTaxService();

        Thread thread1 = new Thread(null, () -> report.compute(earningShop1), "Выручка магазина 1");
        Thread thread2 = new Thread(null, () -> report.compute(earningShop2), "Выручка магазина 2");
        Thread thread3 = new Thread(null, () -> report.compute(earningShop3), "Выручка магазина 3");

        thread1.start();
        thread2.start();
        thread3.start();

        thread3.join();
        thread2.join();
        thread1.join();

        System.out.println("Общая дневная выручка = " + report.getTotalEarning());
    }

    private static int[] fillArrayRandom(int number) {
        final int min = 30;
        final int range = 20_000;

        Random random = new Random();
        int[] array = new int[number];
        for (int i = 0; i < number; i++) {
            array[i] = random.nextInt(range) + min;
        }
        return array;
    }
}
