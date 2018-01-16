package inshurer.view;


import inshurer.Main;
import inshurer.model.BaseData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public class CarController {
    private Main main;
    private BaseData baseData = new BaseData();

    //заполнение валюты платежа
    ObservableList<String> currency = FXCollections.observableArrayList
            ("USD", "EUR", "RUB", "BYN");
    //заполнение тип ТС
    ObservableList<String> type = FXCollections.observableArrayList
            ("Легковой автомобиль", "Грузовой автомобиль", "Прочее");

    public String getBoxCurrencyCar() {
        String currencyCar = (String) boxCurrencyCar.getValue();
        return currencyCar;
    }

    public String getFieldModelCar() {
        String modelCar = fieldModelCar.getText();
        return modelCar;
    }

    public String getFieldVIN() {
        String vin = fieldVIN.getText();
        return vin;
    }

    public String getFieldNumberCar() {
        String numberCar = fieldNumberCar.getText();
        return numberCar;
    }

    public LocalDate getYearCar() {
        LocalDate year_Car = yearCar.getValue();
        return year_Car;
    }

    public String getFieldCoastCar() {
        String coastCar = fieldCoastCar.getText();
        return coastCar;
    }

    @FXML
    private ComboBox boxCurrencyCar;
    @FXML
    private TextField fieldBrandCar;
    @FXML
    private TextField fieldModelCar;
    @FXML
    private TextField fieldVIN;
    @FXML
    private TextField fieldNumberCar;
    @FXML
    private DatePicker yearCar;
    @FXML
    private TextField fieldCoastCar;
    @FXML
    private Button btn_searchCarID;
    @FXML
    private Button btn_searchCarNumber;
    @FXML
    private Button btn_saveCar;
    @FXML
    private Button btn_Clear;
    @FXML
    private Button btn_Edit;
    @FXML
    private ComboBox boxTypeCar;
    @FXML
    private Label car;


    @FXML
    private void clickSaveCar() throws IOException, SQLException {

        String typeCar = getboxTypeCar();
        String brandCar = getfieldBrandCar();
        String modelCar = getFieldModelCar();
        String vin = getFieldVIN();
        String numberCar = getFieldNumberCar();

        String coastCar = getFieldCoastCar();
        String currencyCar = getBoxCurrencyCar();

        if (typeCar.equals("") || brandCar.equals("") || modelCar.equals("") || yearCar.getValue().equals("") || coastCar.equals("") || currencyCar.equals("")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Не сохранен. Не все данные внесены");

            alert.showAndWait();
        } else {
            LocalDate yearCar = getYearCar();
            baseData.insertCar(typeCar, brandCar, modelCar, vin, numberCar, yearCar, coastCar, currencyCar);

        }
    }

    //по нажатию кнопки поиск по VIN
    @FXML
    private void clickSearchByVIN() throws IOException, SQLException {

        String search_vin = fieldVIN.getText();

        HashMap<String, String> values = baseData.findCarByVIN(search_vin);

        if (search_vin.equals(values.get("vin"))) {
            boxTypeCar.setValue(values.get("type"));
            fieldVIN.setText(search_vin);
            fieldBrandCar.setText(values.get("brand"));
            fieldModelCar.setText(values.get("model"));
            fieldNumberCar.setText(values.get("number"));
            boxCurrencyCar.setValue(values.get("currency"));
            yearCar.setValue(LocalDate.parse(values.get("year")));
            fieldCoastCar.setText(values.get("coast"));

        } else {
            clickClear();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Транспортное средство не найдено");

            alert.showAndWait();
        }
    }

    //по нажатию кнопки поиск по номеру машины
    @FXML
    private void clickSearchByNumber() throws IOException, SQLException {

        String search_number = fieldNumberCar.getText();

        HashMap<String, String> values = baseData.findCarByNumber(search_number);

        if (search_number.equals(values.get("number"))) {

            boxTypeCar.setValue(values.get("type"));
            fieldNumberCar.setText(search_number);
            fieldBrandCar.setText(values.get("brand"));
            fieldModelCar.setText(values.get("model"));
            fieldVIN.setText(values.get("vin"));
            boxCurrencyCar.setValue(values.get("currency"));
            yearCar.setValue(LocalDate.parse(values.get("year")));
            fieldCoastCar.setText(values.get("coast"));

        } else {
            clickClear();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Транспортное средство не найдено");

            alert.showAndWait();
        }
    }

    @FXML
    private void clickClear() {
        fieldVIN.setText("");
        fieldBrandCar.setText("");
        fieldModelCar.setText("");
        fieldNumberCar.setText("");
        boxCurrencyCar.setValue("");
        yearCar.setValue(null);
        fieldCoastCar.setText("");
    }

    //инициализация полей списков
    @FXML
    private void initialize() {

        boxCurrencyCar.setValue("USD");
        boxCurrencyCar.setItems(currency);

        boxTypeCar.setValue("Легковой автомобиль");
        boxTypeCar.setItems(type);

        yearCar.setValue(LocalDate.now());

    }

    public String getfieldBrandCar() {
        String brand = fieldBrandCar.getText();
        return brand;
    }

    public String getboxTypeCar() {
        String type = (String) boxTypeCar.getValue();

        return type;
    }

    @FXML
    private void onClickEdit() throws IOException, SQLException {
    }
}
