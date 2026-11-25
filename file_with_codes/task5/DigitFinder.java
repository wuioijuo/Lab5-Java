package task5;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class DigitFinder {

    private final Path filePath;

    public DigitFinder(Path filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException("Путь к файлу не должен быть null");
        }
        this.filePath = filePath;
    }

    public SortedSet<Character> findDigits() throws IOException {
        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        SortedSet<Character> digits = new TreeSet<>();

        for (String line : lines) {
            for (char symbol : line.toCharArray()) {
                if (symbol >= '0' && symbol <= '9') {
                    digits.add(symbol);
                }
            }
        }

        return digits;
    }

    @Override
    public String toString() {
        return "Поиск цифр в файле: " + filePath;
    }
}
