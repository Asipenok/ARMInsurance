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
    private TextArea period_payment;
    @FXML
    private Button btn_Save;
    @FXML
    private Button btn_Search;
    @FXML
    private TextField field_polis_number;

    private Main main;
    private Polis polis = new Polis();
    private RateERGOController rateERGOController;

    //метод записи полиса в БД
    @FXML
    public void clickSavePolis() {
        String polis_number = getPolisNumber();
        String polis_person = getPolisPerson();
        String polis_owner = getPolisOwner();
        String polis_territory = getPolisTerritory();
        LocalDate polis_start_date = getPolisStartDate();
        LocalDate polis_end_date = getPolisEndDate();
        String polis_car = getPolisCar();
        String polis_option = getPolisOption();
        String polis_real_coast = getPolisRealCoast();
        String polis_coast = getPolisCoast();
        String polis_franshise = getPolisFranshise();
        String polis_second_franshise = getPolisSecondFranshise();

    }

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
        field_real_coast.setText(polis.getRealCoast() + " " + propisAll(polis.getCurrency(), polis.getRealCoast()));
        field_coast.setText(polis.getRealCoast() + " " + propisAll(polis.getCurrency(), polis.getRealCoast()));
        doDate.setValue(LocalDate.now());
        field_coast_year.setText(polis.getCoast_year() + " " + propisAll(polis.getCurrency(), polis.getCoast_year()));
        startDate.setValue(LocalDate.now());
        payOption();
    }

    //метод прописи цифр, в зависимости от валюты
    public String propisAll(String currency, String val_digital) throws SQLException {
        currency = polis.getCurrency();

        switch (currency) {
            case "USD":
                currency = propisUSD(val_digital);
                break;
            case "EUR":
                currency = propisEUR(val_digital);
                break;
            case "RUB":
                currency = propisBelRub(val_digital);
                break;
        }
        return currency;
    }

    // метод оплаты очередных взносов
    @FXML
    private void payOption() throws SQLException {
        String payOption;
        String currency = polis.getCurrency() + " до ";

        Double coast_year = Double.valueOf(polis.getCoast_year());
        Double first;
        Double second;
        Double third;
        Double four;

        switch (polis.getPayment()) {
            case "Ежеквартально":
                first = coast_year * 40 / 100;
                second = (coast_year - first) / 3;
                third = (coast_year - first - second) / 2;
                four = (coast_year - first - second - third);
                payOption = String.valueOf(second) + " " + currency + String.valueOf(startDate.getValue().plusMonths(3).minusDays(1)) + " " +
                        String.valueOf(third) + " " + currency + String.valueOf(startDate.getValue().plusMonths(6).minusDays(1)) + " " +
                        String.valueOf(four) + " " + currency + String.valueOf(startDate.getValue().plusMonths(9).minusDays(1));
                period_payment.setText(payOption);
                period_payment.setWrapText(true);
                break;
            case "В два этапа":
                first = coast_year / 2;
                second = (coast_year - first);
                payOption = String.valueOf(second) + " " + currency + String.valueOf(startDate.getValue().plusMonths(6).minusDays(1));
                period_payment.setText(payOption);
                period_payment.setWrapText(true);
                break;
            case "Единовременно":
                period_payment.setText("");
                period_payment.setWrapText(true);
                break;

        }

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
        payOption();

    }

    //номер полиса
    @FXML
    public String getPolisNumber() {
        String polisNumber = field_polis_number.getText();
        return polisNumber;
    }

    //страхователь из поиса
    @FXML
    public String getPolisPerson() {
        String polisNumber = field_polis_number.getText();
        return polisNumber;
    }

    //выгодоприобретатель из полиса
    @FXML
    public String getPolisOwner() {
        String polisOwner = initOwner.getText();
        return polisOwner;
    }

    //территория из полиса
    @FXML
    public String getPolisTerritory() {
        String polisTerritory = field_territory.getText();
        return polisTerritory;
    }

    //начало страхования из полиса
    @FXML
    public LocalDate getPolisStartDate() {
        LocalDate polisStartDate = startDate.getValue();
        return polisStartDate;
    }

    //окончание страхования из полиса
    @FXML
    public LocalDate getPolisEndDate() {
        LocalDate polisEndDate = startDate.getValue();
        return polisEndDate;
    }

    //авто из полиса
    @FXML
    public String getPolisCar() {
        String polisCar = initCar.getText();
        return polisCar;
    }

    //вариант страхования из полиса
    @FXML
    public String getPolisOption() {
        String polisOption = field_option.getText();
        return polisOption;
    }

    //действительная стоимость из полиса
    @FXML
    public String getPolisRealCoast() {
        String polisRealCoast = field_real_coast.getText();
        return polisRealCoast;
    }

    //страховая стоимость из полиса
    @FXML
    public String getPolisCoast() {
        String polisCoast = field_coast.getText();
        return polisCoast;
    }

    //франшиза из полиса
    @FXML
    public String getPolisFranshise() {
        String polisFranshise = field_franshise.getText();
        return polisFranshise;
    }

    //вторая франшиза из полиса
    @FXML
    public String getPolisSecondFranshise() {
        String polisSecondFranshise = field_second_franshise.getText();
        return polisSecondFranshise;
    }
}
