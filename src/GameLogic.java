
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class GameLogic {
    public List<Stack<Integer>> stacks = new ArrayList<>();

    private int counter = 0;
    public double optNumbOfSteps;

    public int countRing;

    public int getCountRing() {
        return countRing;
    }

    GameLogic(int countRings) {
        countRing = countRings;
        for (int i = 0; i <= 4; i++) {
            stacks.add(new Stack<>());
        }
        for (int i = countRings; i >= 1; i--) {
            stacks.get(0).push(i);
            stacks.get(1).push(i);
        }
        optNumbOfSteps = Math.pow(2, countRings) - 1;
    }

    public Optional<String> move(int from, int to) {
        Optional<String> resultOfChecking = conditionCheckError(from, to, stacks);
        if (resultOfChecking.isPresent()) {
            return Optional.of(resultOfChecking.get());
        } else {
            int storage = takeOffToHeap(stacks.get(1), stacks.get(2), stacks.get(3), from);
            addToHeap(stacks.get(1), stacks.get(2), stacks.get(3), to, storage);
            counter++;
            return Optional.empty();
        }
    }

    static Optional<String> conditionCheckError(int from, int to, List<Stack<Integer>> stacks) {
        if (from < 1 || from > 3 || to < 1 || to > 3) {
            return Optional.of("Кучей всего 3 и числа должны быть от 1 до 3-х");
        } else if (from == 1 && stacks.get(1).empty() || from == 2 && stacks.get(2).empty() || from == 3 && stacks.get(3).empty()) {
            return Optional.of(" Куча пуста вы не можете оттуда брать ");
        } else if (stacks.get(to).size() != 0 && (stacks.get(from).peek() > stacks.get(to).peek())) {
            return Optional.of("Вы нарушаете правило игры: большое кольцо ложите на малое кольцо");
        } else {
            return Optional.empty();
        }
    }

    private static void addToHeap(Stack<Integer> stack1, Stack<Integer> stack2, Stack<Integer> stack3, int x, int z) {
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

    private int takeOffToHeap(Stack<Integer> stack1, Stack<Integer> stack2, Stack<Integer> stack3, int x) {
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

    public boolean ifWon() {
        return !((!stacks.get(2).equals(stacks.get(0)) && (!stacks.get(3).equals(stacks.get(0)))));
    }

    public int getCounter() {
        return counter;
    }

    public double getOptNumbOfSteps() {
        return optNumbOfSteps;
    }
}
