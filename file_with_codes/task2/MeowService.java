package task2;

import java.util.List;

public class MeowService {

    private final String serviceName;

    public MeowService(String serviceName) {
        if (serviceName == null || serviceName.trim().isEmpty()) {
            throw new IllegalArgumentException("Название сервиса не должно быть пустым");
        }
        this.serviceName = serviceName;
    }

    public void makeAllMeow(List<? extends Meowable> animals) {
        if (animals == null) {
            throw new IllegalArgumentException("Список объектов не должен быть null");
        }

        for (Meowable animal : animals) {
            animal.meow();
        }
    }

    @Override
    public String toString() {
        return "Сервис мяуканья: " + serviceName;
    }
}
