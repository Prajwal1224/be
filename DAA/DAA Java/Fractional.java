import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Fractional {

    static class Item {
        double value;
        double weight;

        Item(double value, double weight) {
            this.value = value;
            this.weight = weight;
        }

        double ratio() {
            return value / weight;
        }
    }

    // Comparator to sort by value/weight ratio in descending order
    static class RatioComparator implements Comparator<Item> {
        @Override
        public int compare(Item a, Item b) {
            return Double.compare(b.ratio(), a.ratio()); // descending
        }
    }

    // Fractional knapsack implementation
    static double fractionalKnapsack(int n, double W, List<Item> items) {
        Collections.sort(items, new RatioComparator());
        double totalValue = 0.0;

        for (int i = 0; i < n; i++) {
            Item it = items.get(i);
            if (it.weight <= W) {
                W -= it.weight;
                totalValue += it.value;
            } else {
                // take fractional part
                totalValue += it.value * (W / it.weight);
                break;
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        List<Item> items = new ArrayList<>(n);
        System.out.println("Enter value and weight of each item:");
        for (int i = 0; i < n; i++) {
            double value = sc.nextDouble();
            double weight = sc.nextDouble();
            items.add(new Item(value, weight));
        }

        System.out.print("Enter capacity of knapsack: ");
        double W = sc.nextDouble();

        double maxValue = fractionalKnapsack(n, W, items);
        System.out.printf("Maximum value in the knapsack = %.2f%n", maxValue);

        sc.close();
    }
}
