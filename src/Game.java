import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Game {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Stack<Integer>> stacks = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            stacks.add(new Stack<>());
        }

        System.out.println("Начинаем");
        System.out.println("Выберите количество колец для игры");

        int counter = 0;
        int numberOfRings = scanner.nextInt();
        double optNumbOfSteps = Math.pow(2, numberOfRings) - 1;

        for (int i = numberOfRings; i >= 1; i--) {
            stacks.get(0).push(i);
            stacks.get(1).push(i);
        }

        while ((!stacks.get(2).equals(stacks.get(0)) && (!stacks.get(3).equals(stacks.get(0))))) {
            System.out.println("введите номера кучи 1- № снимаемой, 2- № куда ложить ");

            int original = scanner.nextInt();
            int finite = scanner.nextInt();

            if (conditionCheck(original, finite, stacks)) {
                int storage = takeOffToHeap(stacks.get(1), stacks.get(2), stacks.get(3), original);
                addToHeap(stacks.get(1), stacks.get(2), stacks.get(3), finite, storage);
                counter++;

                System.out.println(stacks.get(1));
                System.out.println(stacks.get(2));
                System.out.println(stacks.get(3));
            }
        }

        if (counter == optNumbOfSteps) {
            System.out.println("Отлично! Вы закончили игру за " + counter + " шагов. Это самое оптимальное решение");
        } else {
            System.out.println("Вы закончили игру за " + counter + " шагов и сделали " + (int) (counter - optNumbOfSteps) + " лишних шагов");
        }
    }

    private static boolean conditionCheck(int original, int finite, List<Stack<Integer>> stacks) {
        if ((original < 1) || (finite) < 1 || (original > 3 || (finite) > 3)) {
            System.out.println("Вы ввели не правильные цифры! Числа должны быть от 1 до 3-х");
            return false;
        } else if (original == 1 && stacks.get(1).empty() || original == 2 && stacks.get(2).empty() || original == 3 && stacks.get(3).empty()) {
            System.out.println(finite + " Куча пуста вы не можете оттуда брать ");
            return false;
        } else if (stacks.get(finite).size() != 0 && (stacks.get(original).peek() > stacks.get(finite).peek())) {
            System.out.println("Вы нарушаете правило игры: большое кольцо ложите на малое кольцо");
            return false;
        } else {
            return true;
        }
    }

    public static void addToHeap(Stack<Integer> stack1, Stack<Integer> stack2, Stack<Integer> stack3, int x, int z) {
        switch (x) {
            case 1:
                stack1.push(z);
                break;
            case 2:
                stack2.push(z);
                break;
            case 3:
                stack3.push(z);
        }
    }

    public static int takeOffToHeap(Stack<Integer> stack1, Stack<Integer> stack2, Stack<Integer> stack3, int x) {
        switch (x) {
            case 1:
                return stack1.pop();
            case 2:
                return stack2.pop();
            case 3:
                return stack3.pop();
            default:
                return 0;
        }
    }
}
