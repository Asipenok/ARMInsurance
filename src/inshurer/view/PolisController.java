package inshurer.view;

import inshurer.Main;
import inshurer.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public class PolisController {
    @FXML
    private TextArea initPerson;
    @FXML
    private TextArea initOwner;
    @FXML
    private TextArea initCar;
    @FXML
    private TextField field_franshise;
    @FXML
    private TextField field_second_franshise;
    @FXML
    private TextField field_territory;
    @FXML
    private TextField field_payment;
    @FXML
    private TextField field_option;
    @FXML
    private TextField field_coast;
    @FXML
    private TextField field_real_coast;
    @FXML
    private TextField field_coast_year;
    @FXML
    private Button searchPerson;
    @FXML
    private Button newPerson;
    @FXML
    private Button searchOwner;
    @FXML
    private Button newOwner;
    @FXML
    private Button searchCar;
    @FXML
    private Button newCar;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private DatePicker doDate;
    @FXML
    private TextField period_payment;

    private Main main;
    private Polis polis = new Polis();
    private RateERGOController rateERGOController;


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


    @FXML
    private void initialize() throws SQLException {

        field_territory.setText(polis.getTerritory());
        field_option.setText(polis.getOption());
        field_franshise.setText(polis.getFranchise());
        field_second_franshise.setText(polis.getFranchiseSecond());
        field_payment.setText(polis.getPayment());
        field_real_coast.setText(polis.getRealCoast() + " " + propisEUR(polis.getRealCoast()));
        field_coast.setText(polis.getRealCoast() + " " + propisUSD(polis.getRealCoast()));
        doDate.setValue(LocalDate.now());
        field_coast_year.setText(polis.getCoast_year() + " " + propisBelRub(polis.getCoast_year()));
    }

    //метод цифры - доллары прописью
    @FXML
    public String propisUSD(String coast) {
        MoneyUSD mo = new MoneyUSD(coast);
        return mo.num2str();
    }

    //метод цифры - евро прописью
    @FXML
    public String propisEUR(String coast) {
        MoneyEUR mo = new MoneyEUR(coast);
        return mo.num2str();
    }

    //метод цифры - бел руб прописью
    @FXML
    public String propisBelRub(String coast) {
        Money mo = new Money(coast);
        return mo.num2str();
    }


    @FXML
    public void setEndDate() throws SQLException {
        endDate.setValue(startDate.getValue().plusMonths(12).minusDays(1));
    }
}
