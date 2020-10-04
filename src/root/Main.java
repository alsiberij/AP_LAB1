package root;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final int MAX = 20;
        Food[] breakfast = new Food[MAX];
        int totalAmount = 0;
        boolean needCalories = false;
        boolean needSort = false;

        for (String arg : args) {
            if (arg.equals("-calories")) {
                needCalories = true;
            } else if (arg.equals("-sort")) {
                needSort = true;
            } else {
                String[] item = arg.split("/");
                if (totalAmount >= 20) {
                    System.out.println("No space for " + item[0] + ".");
                    continue;
                }
                try {
                    Class food = Class.forName("root." + item[0]);
                    Constructor cs;
                    if (item.length == 1) {
                        cs = food.getConstructor();
                        breakfast[totalAmount] = (Food) cs.newInstance();
                    } else if (item.length == 2) {
                        cs = food.getConstructor(String.class);
                        breakfast[totalAmount] = (Food) cs.newInstance(item[1]);
                    } else if (item.length == 3) {
                        cs = food.getConstructor(String.class, String.class);
                        breakfast[totalAmount] = (Food) cs.newInstance(item[1], item[2]);
                    } else {
                        throw new ClassNotFoundException();
                    }
                    totalAmount++;
                } catch (ClassNotFoundException e) {
                    System.out.printf("%s is unknown item or has invalid amount of parameters.\n", item[0]);
                } catch (NoSuchMethodException | IllegalAccessException |
                        InstantiationException | InvocationTargetException ignored) {
                }
            }
        }

        System.out.printf("\nBreakfast consists of %d item(s).\n", totalAmount);

        if (needSort) {
            Arrays.sort(breakfast, new Comparator<Food>() {
                @Override
                public int compare(Food obj1, Food obj2) {
                    if (obj1 == null) {
                        return 1;
                    } else if (obj2 == null) {
                        return -1;
                    } else {
                        int compResult = Integer.compare(obj1.toString().length(), obj2.toString().length());
                        if (compResult != 0) {
                            return compResult;
                        } else {
                            return obj1.toString().compareTo(obj2.toString());
                        }
                    }
                }
            });
        }

        int calories = 0;
        for (int i = 0; i < totalAmount; i++) {
            System.out.printf("%d. ", i + 1);
            breakfast[i].consume();
            calories += breakfast[i].calculateCalories();
        }

        if (needCalories) {
            System.out.println("Calories: " + calories);
        }

        System.out.printf("Please select item to calculate amount of it (1 - %d): ", totalAmount);
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt() - 1;
        if (choice < 0 || choice >= totalAmount) {
            System.out.println("Invalid number.");
        } else {
            int repeatCounter = 0;
            for (int i = 0; i < totalAmount; i++) {
                if (breakfast[i].equals(breakfast[choice])) {
                    repeatCounter++;
                }
            }
            System.out.println("Amount: " + repeatCounter);
        }
    }
}

