import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.SortedSet;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        System.out.println("Лабораторная работа №5. Вариант 3");
        System.out.println("Исходные значения заданы в программе и текстовых файлах.");

        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7_1();
        task7_2();
    }

    private static void task1() {
        System.out.println("\nЗадание 1. КЭШИРУЮЩИЙ PROXY");

        Fraction original = new Fraction(1, 2);
        FractionOperations fraction = new CachedFraction(original);

        System.out.println("Исходная дробь: " + original);
        System.out.println("Первый запрос: " + fraction.getValue());
        System.out.println("Второй запрос: " + fraction.getValue());

        fraction.setNumerator(3);
        System.out.println("После изменения числителя: " + fraction.getValue());

        fraction.setDenominator(4);
        System.out.println("После изменения знаменателя: " + fraction.getValue());

        System.out.println("Дробь с отрицательным знаменателем: " + new Fraction(1, -2));
        System.out.println("Две одинаковые дроби равны: "
                + new Fraction(1, 2).equals(new Fraction(1, 2)));
    }

    private static void task2() {
        System.out.println("\nЗадание 2. ДЕКОРАТОР ДЛЯ ПОДСЧЁТА МЯУКАНИЙ");

        Cat cat = new Cat("Барсик");
        CountingMeowable countedCat = new CountingMeowable(cat);

        List<Meowable> animals = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            animals.add(countedCat);
        }

        MeowService service = new MeowService("Подсчёт мяуканий");
        System.out.println(cat);
        service.makeAllMeow(animals);
        System.out.println(service);
        System.out.println("Кот мяукал раз: " + countedCat.getCount());
    }

    private static void task3() {
        System.out.println("\nЗадание 3. СПИСОК");

        List<Integer> source = Arrays.asList(
                1, 1, 1, 2, 2, 3, 1, 1, 4, 4, 4
        );

        ListProcessor processor = new ListProcessor(source);
        System.out.println(processor);
        System.out.println("После удаления соседних повторов: "
                + processor.removeConsecutiveDuplicates());
    }

    private static void task4() {
        System.out.println("\nЗадание 4. MAP — ЦЕНЫ НА СМЕТАНУ");

        Path file = Paths.get("data", "sour_cream.txt");

        try {
            List<SourCreamOffer> offers = readSourCreamOffers(file);
            SourCreamAnalyzer analyzer = new SourCreamAnalyzer(offers);
            Map<Integer, Integer> result = analyzer.countCheapestStores();

            System.out.println("Количество магазинов с минимальной ценой для 15%, 20% и 25%:");
            System.out.println(result.get(15) + " " + result.get(20) + " " + result.get(25));
        } catch (IOException exception) {
            System.out.println("Не удалось прочитать файл: " + file);
        }
    }

    private static List<SourCreamOffer> readSourCreamOffers(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);

        if (lines.isEmpty()) {
            throw new IOException("Файл пустой: отсутствует количество магазинов.");
        }

        int expectedCount;
        try {
            expectedCount = Integer.parseInt(lines.get(0).trim());
        } catch (NumberFormatException exception) {
            throw new IOException("В первой строке файла должно быть число магазинов N.");
        }

        if (expectedCount < 0 || lines.size() - 1 != expectedCount) {
            throw new IOException("Количество записей не совпадает со значением N.");
        }

        List<SourCreamOffer> offers = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.trim().split("\\s+");

            if (parts.length != 4) {
                System.out.println("Строка " + (i + 1) + " пропущена: неверный формат.");
                continue;
            }

            try {
                int fatness = Integer.parseInt(parts[2]);
                int price = Integer.parseInt(parts[3]);
                offers.add(new SourCreamOffer(parts[0], parts[1], fatness, price));
            } catch (IllegalArgumentException exception) {
                System.out.println("Строка " + (i + 1) + " пропущена: "
                        + exception.getMessage());
            }
        }

        return offers;
    }

    private static void task5() {
        System.out.println("\nЗадание 5. SET — ЦИФРЫ В ТЕКСТЕ");

        DigitFinder finder = new DigitFinder(
                Paths.get("data", "text_digits.txt")
        );

        try {
            SortedSet<Character> digits = finder.findDigits();
            if (digits.isEmpty()) {
                System.out.println("В файле цифры не найдены.");
            } else {
                System.out.println("Цифры, которые встречаются в тексте: " + digits);
            }
        } catch (IOException exception) {
            System.out.println("Не удалось прочитать текстовый файл.");
        }
    }

    private static void task6() {
        System.out.println("\nЗадание 6. ОЧЕРЕДЬ");

        Queue<Integer> queueWithPair = new ArrayDeque<>(
                Arrays.asList(4, 7, 2, 2, 9, 1)
        );

        Queue<Integer> queueWithoutPair = new ArrayDeque<>(
                Arrays.asList(4, 7, 2, 9, 1)
        );

        QueueChecker firstChecker = new QueueChecker(queueWithPair);
        QueueChecker secondChecker = new QueueChecker(queueWithoutPair);

        System.out.println(firstChecker);
        System.out.println("Есть равные соседние элементы по кругу: "
                + firstChecker.hasEqualNeighboursInCircle());

        System.out.println(secondChecker);
        System.out.println("Есть равные соседние элементы по кругу: "
                + secondChecker.hasEqualNeighboursInCircle());
    }

    private static void task7_1() {
        System.out.println("\nЗадание 7.1. STREAM API — ЛОМАНАЯ");

        List<Point> points = Arrays.asList(
                new Point(3, -4),
                new Point(1, 2),
                new Point(2, -5),
                new Point(1, -3),
                new Point(3, -4),
                new Point(4, -1),
                new Point(2, 6)
        );

        Point[] resultPoints = points.stream()
                .distinct()
                .sorted(Comparator.comparingInt(Point::getX))
                .map(point -> new Point(point.getX(), Math.abs(point.getY())))
                .toArray(Point[]::new);

        Polyline polyline = new Polyline(resultPoints);

        System.out.println("Исходные точки: " + points);
        System.out.println("Полученная ломаная: " + polyline);

        Line line = new Line(new Point(0, 0), new Point(3, 4));
        System.out.println(line);
    }

    private static void task7_2() {
        System.out.println("\nЗадание 7.2. STREAM API — ГРУППИРОВКА ИМЁН");

        PersonGrouper grouper = new PersonGrouper(
                Paths.get("data", "people_numbers.txt")
        );

        try {
            System.out.println("Группы людей по номерам: "
                    + grouper.groupPeopleByNumber());
        } catch (IOException exception) {
            System.out.println("Не удалось прочитать файл с именами.");
        }
    }
}
