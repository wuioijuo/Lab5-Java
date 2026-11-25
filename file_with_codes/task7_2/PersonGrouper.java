package task7_2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonGrouper {

    private final Path filePath;

    public PersonGrouper(Path filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException("Путь к файлу не должен быть null");
        }
        this.filePath = filePath;
    }

    public Map<Integer, List<String>> groupPeopleByNumber() throws IOException {
        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);

        return lines.stream()
                .map(PersonNumber::fromLine)
                .filter(Objects::nonNull)
                .filter(person -> person.getNumber() != null)
                .collect(Collectors.groupingBy(
                        PersonNumber::getNumber,
                        LinkedHashMap::new,
                        Collectors.mapping(
                                PersonNumber::getName,
                                Collectors.toList()
                        )
                ));
    }

    @Override
    public String toString() {
        return "Группировка людей из файла: " + filePath;
    }
}
