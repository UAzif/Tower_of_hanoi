import java.util.Scanner;

public class GameStarter {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Начинаем");
        System.out.println("Для запуска в оконном режиме введите Yes иначе игра будет запущена в консоли");
        String var = scanner.nextLine();
        if (var.equals("Yes")) {
            new GUIGame().start();
        } else {
            new ConsoleGame().start();
        }
    }
}


