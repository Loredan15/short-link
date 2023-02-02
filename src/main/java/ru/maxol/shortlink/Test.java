package ru.maxol.shortlink;

import ru.maxol.shortlink.service.ConvertService;

public class Test {
    public static void main(String[] args) {
        ConvertService convertService = new ConvertService();
        convertService.encode(1L);
    }
}
