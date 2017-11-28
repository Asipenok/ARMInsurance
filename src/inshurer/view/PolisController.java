package inshurer.view;

import inshurer.Main;
import inshurer.model.BaseData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class PolisController {
    @FXML
    TextArea initPerson;
    @FXML
    TextArea initOwner;
    @FXML
    TextArea initCar;
    @FXML
    TextArea field_franshis;
    @FXML
    Button searchPerson;
    @FXML
    Button newPerson;
    @FXML
    Button searchOwner;
    @FXML
    Button newOwner;
    @FXML
    Button searchCar;
    @FXML
    Button newCar;

    private Main main;
    PersonController personController = new PersonController();

    //метод поиска и вставки страхователя из БД
    @FXML
    public void clickSearchPerson() throws SQLException {
        BaseData baseData = new BaseData();
        String vin = initPerson.getText();
        String person;

        HashMap<String, String> values = baseData.findPersonByID(vin);

        person = values.get("last_name") + " " + " " + values.get("first_name") + " " + " " + values.get("middle_name")
                + " " + " " + values.get("typeDoc") + " " + " " + values.get("seriesDoc") + " " + " " + values.get("numberDoc")
                + " " + " " + values.get("id_number");

        initPerson.setText(person);
    }

    //метод поиска и вставки выгодоприобретателя из БД
    @FXML
    public void clickSearchOwner() throws SQLException {
        BaseData baseData = new BaseData();
        String vin = initOwner.getText();
        String person;

        HashMap<String, String> values = baseData.findPersonByID(vin);

        person = values.get("last_name") + " " + " " + values.get("first_name") + " " + " " + values.get("middle_name")
                + " " + " " + values.get("typeDoc") + " " + " " + values.get("seriesDoc") + " " + " " + values.get("numberDoc")
                + " " + " " + values.get("id_number");

        initOwner.setText(person);
    }

    //метод поиска и вставки авто из БД
    @FXML
    public void clickSearchCar() throws SQLException {
        BaseData baseData = new BaseData();
        String vin = initCar.getText();
        String car;

        HashMap<String, String> values = baseData.findCarByVIN(vin);

        car = values.get("type") + " " + " " + values.get("brand") + " " + " " + values.get("model")
                + " " + " " + values.get("number") + " " + " " + values.get("year") + " " + "VIN " + values.get("vin");
        initCar.setText(car);
    }

    //метод вызова поля для вставки нового клиента
    @FXML
    public void clickNewPerson() throws IOException {
        main.showPerson();
    }

    //метод вызова поля для вставки нового авто
    @FXML
    public void clickNewCar() throws IOException {
        main.showCar();
    }

    //метод вызова расчета тарифа
    @FXML
    public void clickShowRate() throws IOException {
        main.showRateERGO();
    }

    //метод получения данных из БД инициализации полей после расчета тарифа


    @FXML
    public void initialize() {
    }
}
