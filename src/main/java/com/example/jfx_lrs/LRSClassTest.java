package com.example.jfx_lrs;

public class LRSClassTest {

    private static LongRangSensorClass.LRS lrs = new LongRangSensorClass().new LRS("Датчик1", "Тип1", 10.0f);
    public static void TST() {
        testConstructor();
        testSettersAndGetters();
        testSwitchState();
        testToString();
    }

    public static void testConstructor() {


        // Проверка, что конструктор корректно инициализирует поля
        assert lrs.getSensorName().equals("Датчик1");
        assert lrs.getSensorType().equals("Тип1");
        assert lrs.getCoverageRadius() == 10.0f;
    }

    public static void testSettersAndGetters() {
        // Проверка сеттеров и геттеров
        lrs.setSensorName("Новое название");
        assert lrs.getSensorName().equals("Новое название");

        lrs.setSensorType("Новый тип");
        assert lrs.getSensorType().equals("Новый тип");

        lrs.setDescription("Новое описание");
        assert lrs.getDescription().equals("Новое описание");

        lrs.setState(true);
        assert lrs.getState();

        lrs.setCoverageRadius(30.0f);
        assert lrs.getCoverageRadius() == 30.0f;
    }

    public static void testSwitchState() {
        // Проверка метода switchState()
        assert !lrs.getState();
        lrs.switchState();
        assert lrs.getState();
        lrs.switchState();
        assert !lrs.getState();
    }

    public static void testToString() {

        // Проверка метода toString()
        String expected = "Датчик: Новое название\n" +
                "Тип датчика: Новый тип\n" +
                "Описание: null\n" +
                "Статус: Оффлайн\n" +
                "Радиус обзора: 10.0\n";
        assert lrs.toString().equals(expected);
    }
}
