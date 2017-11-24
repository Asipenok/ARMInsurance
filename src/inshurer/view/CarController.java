package inshurer.view;


import inshurer.Main;
import inshurer.model.BaseData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public class CarController {
    private Main main;
    //заполнение валюты платежа
    ObservableList<String> currency = FXCollections.observableArrayList
            ("USD", "EUR", "RUB", "BYN");

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
    private void clickSaveCar() throws IOException, SQLException {
        BaseData baseData = new BaseData();

        String brandCar = getfieldBrandCar();
        String modelCar = getFieldModelCar();
        String vin = getFieldVIN();
        String numberCar = getFieldNumberCar();
        LocalDate yearCar = getYearCar();
        String coastCar = getFieldCoastCar();
        String currencyCar = getBoxCurrencyCar();

        try {
            baseData.insertCar(brandCar, modelCar, vin, numberCar, yearCar, coastCar, currencyCar);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //по нажатию кнопки поиск по VIN
    @FXML
    private void clickSearchByVIN() throws IOException, SQLException {
        BaseData baseData = new BaseData();
        String search_vin = fieldVIN.getText();

        HashMap<String, String> values = baseData.findCarByVIN(search_vin);

        if (search_vin.equals(values.get("vin"))) {
            fieldVIN.setText(search_vin);
            fieldBrandCar.setText(values.get("brand"));
            fieldModelCar.setText(values.get("model"));
            fieldNumberCar.setText(values.get("number"));
            boxCurrencyCar.setValue(values.get("currency"));
            yearCar.setValue(LocalDate.parse(values.get("year")));
            fieldCoastCar.setText(values.get("coast"));

        } else {
            clickClear();
            fieldVIN.setText(search_vin);
            fieldBrandCar.setText("No mutches");
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

    }

    public String getfieldBrandCar() {
        String brand = fieldBrandCar.getText();
        return brand;
    }
}
