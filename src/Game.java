import java.util.Scanner;
import java.util.Stack;

public class Game {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int storage=0;

        Stack<Integer> originalStack = new Stack<>();
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        Stack<Integer> stack3 = new Stack<>();

        originalStack.push(3);
        originalStack.push(2);
        originalStack.push(1);

        stack1.push(3);
        stack1.push(2);
        stack1.push(1);
        System.out.println("Начинаем");


        while ((!stack2.equals(originalStack) &&(!stack3.equals(originalStack)))) {
            System.out.println("введите номера кучи 1- № снимаемой, 2- № куда ложить ");
            int original = scanner.nextInt();
            int finite = scanner.nextInt();

           storage = takeOffToHeap(stack1, stack2, stack3,original);
            addToHeap(stack1,stack2,stack3,finite,storage);
         //   storage=0;
            System.out.println(stack1);
            System.out.println(stack2);
            System.out.println(stack3);
        }
        System.out.println(" Вы закончили игу");
    }
    public static void addToHeap(Stack<Integer> stack1,Stack<Integer> stack2,Stack<Integer> stack3,int x, int z ){
        switch (x){
            case 1:stack1.push(z);
            break;
            case 2:stack2.push(z);
            break;
            case 3:stack3.push(z);
        }
    }
    public static int takeOffToHeap(Stack<Integer> stack1, Stack<Integer> stack2, Stack<Integer> stack3, int x){
        int storage=0;
        switch (x){
            case 1:
                storage= stack1.pop();
                break;
            case 2:
                storage= stack2.pop();
                break;
            case 3:
                storage = stack3.pop();
        }
        return storage;
    }
}
