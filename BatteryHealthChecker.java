import java.util.List;

public class BatteryHealthChecker {

    public static void main(String[] args) {
        // Example battery capacities
        List<Integer> batteryCapacities = List.of(120, 100, 80, 70, 50, 0);
        
        BatteryHealthCount count = countBatteryHealth(batteryCapacities);
        
        System.out.println("Healthy: " + count.healthy);
        System.out.println("Exchange: " + count.exchange);
        System.out.println("Failed: " + count.failed);
        
        // Test function
        testCountBatteryHealth();
    }

    public static class BatteryHealthCount {
        public int healthy;
        public int exchange;
        public int failed;

        public BatteryHealthCount(int healthy, int exchange, int failed) {
            this.healthy = healthy;
            this.exchange = exchange;
            this.failed = failed;
        }
    }

    public static BatteryHealthCount countBatteryHealth(List<Integer> capacities) {
        int healthyCount = 0;
        int exchangeCount = 0;
        int failedCount = 0;
        final int ratedCapacity = 120;

        for (int capacity : capacities) {
            double soh = (double) capacity / ratedCapacity * 100;

            if (soh > 80) {
                healthyCount++;
            } else if (soh >= 62) {
                exchangeCount++;
            } else {
                failedCount++;
            }
        }

        return new BatteryHealthCount(healthyCount, exchangeCount, failedCount);
    }

    public static void testCountBatteryHealth() {
        List<Integer> testCapacities = List.of(120, 100, 80, 70, 50, 0);
        BatteryHealthCount result = countBatteryHealth(testCapacities);
        
        assert result.healthy == 3 : "Expected 3 healthy batteries, but got " + result.healthy;
        assert result.exchange == 1 : "Expected 1 exchange battery, but got " + result.exchange;
        assert result.failed == 2 : "Expected 2 failed batteries, but got " + result.failed;

        List<Integer> edgeTestCapacities = List.of(96, 85, 81, 61, 61, 0);
        BatteryHealthCount edgeResult = countBatteryHealth(edgeTestCapacities);
        
        assert edgeResult.healthy == 3 : "Expected 3 healthy batteries, but got " + edgeResult.healthy;
        assert edgeResult.exchange == 1 : "Expected 1 exchange battery, but got " + edgeResult.exchange;
        assert edgeResult.failed == 2 : "Expected 2 failed batteries, but got " + edgeResult.failed;

        System.out.println("All tests passed!");
    }
}
