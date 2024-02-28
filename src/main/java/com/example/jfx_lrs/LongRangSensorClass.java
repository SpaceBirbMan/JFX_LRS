package com.example.jfx_lrs;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

//author Sosnov K.A.

/**
 * Класс представляет собой датчик дальнего действия в системе мониторинга данных.
 * Он хранит названии датчика, типе датчика, дальности обзора, статусе и описании.
 */
public class LongRangSensorClass {

    /**
     * Класс, представляющий датчик дальнего действия (LRS).
     */
    public class LRS {
        private String sensorName; // Название датчика
        private String sensorType; // Тип датчика
        private float coverageRadius; // Дальность обзора датчика
        private boolean onlineState; // Статус онлайн
        private String description; // Описание датчика

        /**
         * Конструктор для инициализации датчика LRS с заданными параметрами.
         *
         * @param sensor Название датчика.
         * @param type   Тип датчика (оптический, фотоэлектрический, инфракрасный).
         * @param rad    Радиус обзора датчика.
         */
        public LRS(String sensor, String type, float rad) {
            sensorName = sensor;
            sensorType = type;
            setCoverageRadius(rad);
        }

        // Методы для получения и установки полей LRS

        public void setSensorName(String sensor) {
            sensorName = sensor;
        }

        public void setSensorType(String type) {
            sensorType = type;
        }

        public void setDescription(String desc) {
            description = desc;
        }

        public void setState(boolean status) {
            onlineState = status;
        }

        public void setCoverageRadius(float radius) {
            if (radius >= 0) {
                coverageRadius = radius;
            } else {
                throw new IllegalArgumentException("Радиус обзора не может быть отрицательным"); //IAE - класс, поэтому нужен new
            }
        }

        public String getSensorName() {
            return sensorName;
        } // получение названия сенсора

        public String getSensorType() {
            return sensorType;
        } // получение типа сенсора

        public String getDescription() {
            return description;
        } // получение описания датчика

        public boolean getState() {
            return onlineState;
        } // получение статуса

        public float getCoverageRadius() {
            return coverageRadius;
        } // получить радиус покрытия

        /**
         * Метод для переключения онлайн-статуса датчика.
         */
        public void switchState() {
            onlineState = !onlineState;
        }

        /**
         * Метод для преобразования объекта LRS в строковое представление.
         *
         * @return Строковое представление объекта LRS.
         */
        @Override //из-за базового класса object, от которого всякое наследуется в java
        public String toString() {
            String stat = onlineState ? "Онлайн" : "Оффлайн";
            return "Датчик: " + sensorName + '\n' +
                    "Тип датчика: " + sensorType + '\n' +
                    "Описание: " + description + '\n' +
                    "Статус: " + stat + '\n' +
                    "Радиус обзора: " + coverageRadius + '\n';
        }
        /*
        public void Void() {
        }
        */
    }

    /**
     * Сохраняет текст в файл
     * @param stage - базовый контейнер для сцены
     * @param content - данные
     */

    public static void saveToFile(String content, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(content);
            } catch (IOException e) {
                e.printStackTrace(); //написать стек в консоль?
            }
        }
    }

    /**
     * Загружаем текст из файла
     * @param stage - базовый контейнер для сцены
     */
    public static String loadFromFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                return content.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}