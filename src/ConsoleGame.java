import java.util.*;

public class ConsoleGame {
    GameLogic game;

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Начинаем");
        System.out.println("Выберите количество колец для игры");
        int countRing = scanner.nextInt();
        game = new GameLogic(countRing);
        draw(game.stacks);
        while (!game.ifWon()) {
            System.out.println("введите номера кучи 1- № снимаемой, 2- № куда ложить ");

            int from = scanner.nextInt();
            int to = scanner.nextInt();

            Optional<String> moveResult = game.move(from, to);
            if (moveResult.isPresent()) {
                System.out.println(moveResult.get());
            } else {
                draw(game.stacks);
            }

        }

        System.out.println("Отлично! Вы закончили игру за " + game.getCounter() + " шагов.");
        int c = game.getCounter() - (int) game.getOptNumbOfSteps();
        if (c == 0) {
            System.out.println(" Это оптимальное количество шагов ");
        } else {
            System.out.println("Оптимальное количество шагов " + game.getOptNumbOfSteps() + " Вы сделали " + c + " лишних шагов");
        }
    }

    void draw(List<Stack<Integer>> stacks) {
        int n = game.getCountRing();
        int m = n * 4 - 3;
        boolean[][] ar1;
        boolean[][] ar2;
        boolean[][] ar3;
        boolean[][] baseArray;

        ar1 = graf(stacks.get(1), n);
        ar2 = graf(stacks.get(2), n);
        ar3 = graf(stacks.get(3), n);

        baseArray = overArray(ar1, ar2, ar3, n, m);

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m * 3; j++) {
                //   System.out.print(baseArray[i][j] + " ");
                if (!baseArray[i][j]) {
                    System.out.print(" ");
                } else System.out.print("*");
            }
            System.out.println();
        }
        System.out.print("    " + stacks.get(1) + "    ");
        System.out.print(stacks.get(2) + "    ");
        System.out.println(stacks.get(3));
    }

    public static boolean[][] graf(Stack<Integer> stack, int countRing) {
        int x = countRing * 4 - 3;
        boolean[][] array = new boolean[countRing][x];
        for (int i = 0; i < stack.size(); i++) {
            int y = stack.elementAt(i) * 2;
            int a = (x - y) / 2;
            for (int j = 0; j < x; j++) {
                array[i][j] = j > a && j < (a + y + 1);
            }
        }
        return array;
    }

    public static boolean[][] overArray(boolean[][] ar1, boolean[][] ar2, boolean[][] ar3, int n, int m) {

        boolean[][] overArray = new boolean[n][m * 3];

        for (int i = 0; i < n; i++) {
            if (m >= 0) System.arraycopy(ar1[i], 0, overArray[i], 0, m);
            if (m >= 0) System.arraycopy(ar2[i], 0, overArray[i], m, m);
            if (m >= 0) System.arraycopy(ar3[i], 0, overArray[i], 2 * m, m);
        }
        return overArray;
    }
}



