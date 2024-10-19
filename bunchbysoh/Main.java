package bunchbysoh;

public class Main {
    private static final int RATED_CAPACITY = 120; // Rated capacity in Ah

    public static void main(String[] args) {
        // Test data
        int[] presentCapacities = {105, 80, 70, 50, 90, 110, 60, 120};

        // function call to classify batteries
        classifyBatteries(presentCapacities);
    }

    public static void classifyBatteries(int[] presentCapacities) {
        int healthyCount = 0;
        int exchangeCount = 0;
        int failedCount = 0;

        for (int presentCapacity : presentCapacities) {
            double soh = computeSoH(presentCapacity);
            if (soh > 80) {
                healthyCount++;
            } else if (soh >= 62) {
                exchangeCount++;
            } else {
                failedCount++;
            }
        }

        System.out.println("Healthy: " + healthyCount);
        System.out.println("Exchange: " + exchangeCount);
        System.out.println("Failed: " + failedCount);
    }

    public static double computeSoH(int presentCapacity) {
        return 100.0 * presentCapacity / RATED_CAPACITY; // Calculate SoH percentage
    }
}
