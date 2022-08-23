import java.util.Scanner;
import java.util.Stack;

public class Game {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Stack<Integer> originalStack = new Stack<>();
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        Stack<Integer> stack3 = new Stack<>();

        System.out.println("Начинаем");
        System.out.println("Выберите количество колец для игры");

        int numberOfRings = scanner.nextInt();

        for (int i =numberOfRings ; i>=1; i--){
            originalStack.push(i);
            stack1.push(i);
        }

        while ((!stack2.equals(originalStack) && (!stack3.equals(originalStack)))) {
            System.out.println("введите номера кучи 1- № снимаемой, 2- № куда ложить ");
            int original = scanner.nextInt();
            int finite = scanner.nextInt();
            if ((original < 1) || (finite) < 1 || (original > 3 || (finite) > 3)) {
                System.out.println("Вы ввели не правильные цифры! Числа должны быть от 1 до 3-х");
            } else if ((original == 2) && stack2.empty()||(original == 3) && stack3.empty()) {
                System.out.println(finite + " Куча пуста вы не можете оттуда брать ");
            } else {
                int storage = takeOffToHeap(stack1, stack2, stack3, original);
                addToHeap(stack1, stack2, stack3, finite, storage);

                System.out.println(stack1);
                System.out.println(stack2);
                System.out.println(stack3);
            }
        }
        System.out.println(" Вы закончили игру");
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
