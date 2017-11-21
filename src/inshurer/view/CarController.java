package inshurer.view;


import inshurer.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class CarController {

    //заполнение валюты платежа
    ObservableList<String> currency = FXCollections.observableArrayList
            ("USD", "EUR", "RUB", "BYN");
    @FXML
    private ComboBox boxCurrencyCar;
    //инициализация полей списков

    @FXML
    private void initialize() {

        boxCurrencyCar.setValue("USD");
        boxCurrencyCar.setItems(currency);


    }


}
