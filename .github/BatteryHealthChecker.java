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
        
        if (result.healthy == 3) {
            System.out.println("Test passed: Expected 3 healthy batteries");
        } else {
            System.out.println("Test failed: Expected 3 healthy batteries, but got " + result.healthy);
        }

        if (result.exchange == 1) {
            System.out.println("Test passed: Expected 1 exchange battery");
        } else {
            System.out.println("Test failed: Expected 1 exchange battery, but got " + result.exchange);
        }

        if (result.failed == 2) {
            System.out.println("Test passed: Expected 2 failed batteries");
        } else {
            System.out.println("Test failed: Expected 2 failed batteries, but got " + result.failed);
        }

        List<Integer> edgeTestCapacities = List.of(96, 85, 81, 61, 61, 0);
        BatteryHealthCount edgeResult = countBatteryHealth(edgeTestCapacities);
        
        if (edgeResult.healthy == 3) {
            System.out.println("Edge test passed: Expected 3 healthy batteries");
        } else {
            System.out.println("Edge test failed: Expected 3 healthy batteries, but got " + edgeResult.healthy);
        }

        if (edgeResult.exchange == 1) {
            System.out.println("Edge test passed: Expected 1 exchange battery");
        } else {
            System.out.println("Edge test failed: Expected 1 exchange battery, but got " + edgeResult.exchange);
        }

        if (edgeResult.failed == 2) {
            System.out.println("Edge test passed: Expected 2 failed batteries");
        } else {
            System.out.println("Edge test failed: Expected 2 failed batteries, but got " + edgeResult.failed);
        }
    }
}
