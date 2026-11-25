package task7_2;

import java.util.Locale;

public class PersonNumber {

    private final String name;
    private final Integer number;

    public PersonNumber(String name, Integer number) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не должно быть пустым");
        }

        String lowerName = name.trim().toLowerCase(Locale.forLanguageTag("ru"));
        this.name = Character.toUpperCase(lowerName.charAt(0)) + lowerName.substring(1);
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public static PersonNumber fromLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split(":", 2);
        String name = parts[0].trim();

        if (name.isEmpty()) {
            return null;
        }

        Integer number = null;
        if (parts.length == 2 && !parts[1].trim().isEmpty()) {
            try {
                number = Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException exception) {
                number = null;
            }
        }

        return new PersonNumber(name, number);
    }

    @Override
    public String toString() {
        return number == null ? name + ": без номера" : name + ":" + number;
    }
}
