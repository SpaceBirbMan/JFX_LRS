package com.example.jfx_lrs;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections; // это для ObservableList

import com.example.jfx_lrs.MyApplication;
import javafx.stage.Stage;

public class MainController {

    private boolean tState = false; // переменная статуса
    private double covR = 1;

    @FXML
    private ToggleButton toggle_state;
    @FXML
    private Label state_label;
    @FXML
    private TextField label_slider;
    @FXML
    private ChoiceBox<String> sensor_type_chb;
    @FXML
    private Slider slider;
    @FXML
    private TextField name_text;
    @FXML
    private TextArea description_text;
    @FXML
    private Stage st;

    @FXML
    /**
     * Создание всякого перед запуском
     */
    public void initialize() {
        // Создание списка элементов
        String[] sensorTypes = {"Оптический", "Инфракраснный", "Фотоэлектрический"};

        // Добавление элементов в ChoiceBox
        sensor_type_chb.setItems(FXCollections.observableArrayList(sensorTypes));

        // Выбор элемента по умолчанию
        sensor_type_chb.setValue(sensorTypes[0]);
        covR = 1;
        label_slider.setText(Double.toString(covR));
    }
    @FXML
    private void switchState() {
        tState = !tState;
        toggle_state.setText(Boolean.toString(tState));
    }

    @FXML
    /**
     * Создаёт заготовку под сенсор
     */
    private void createNewSensor() {
        covR = 1;
        label_slider.setText(Double.toString(covR));
        slider.setValue(covR);

        toggle_state.setText(Boolean.toString(false));
        description_text.setText("");
        name_text.setText("");

        state_label.setText("Поля очищены");
    }
    @FXML
    /**
     * Сохраняет в файл
     */
    private void saveIntoFile() {
        LongRangSensorClass outerInstance = new LongRangSensorClass();
        LongRangSensorClass.LRS Sensor = outerInstance.new LRS(name_text.getText(), sensor_type_chb.getValue(), (float)covR); // явное преобразование из double, иначе никак

        Sensor.setState(tState);
        Sensor.setDescription(description_text.getText());


        LongRangSensorClass.saveToFile(Sensor.toString(), st);
        state_label.setText("Файл сохранён");
    }

    @FXML
    /**
     * Загружает из файла
     */
    private void loadFromFile() {
        LongRangSensorClass outerInstance = new LongRangSensorClass();
        String data = LongRangSensorClass.loadFromFile(st);

        state_label.setText("Файл загружен, но данные недоступны"); //todo: доделать
    }
    @FXML
    /**
     * Меняет дальность через слайдер
     */
    private void  sliderDrag(){
        covR = slider.getValue();
        label_slider.setText(Double.toString(covR)); //сделать try

    }

    @FXML
    /**
     * Меняет дальность через текст
     */
    private void keyTyped(){
        covR = Double.parseDouble(label_slider.getText());
        slider.setValue(covR);
    }
}