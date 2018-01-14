package inshurer.view;

import inshurer.Main;
import inshurer.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private TextField field_payment_real;
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
    private Button btn_Next;
    @FXML
    private TextField field_polis_number;

    private Main main;
    private Polis polis = new Polis();
    private RateERGOController rateERGOController;
    private BaseData baseData = new BaseData();
    private PersonController personController;

    //метод записи полиса в БД
    @FXML
    public void clickSavePolis() throws SQLException {

        int number_polis = Integer.parseInt(field_polis_number.getText());
        String insurer_field = initPerson.getText();
        String owner_field = initOwner.getText();
        String territory_field = field_territory.getText();
        LocalDate start_date = startDate.getValue();
        LocalDate end_date = endDate.getValue();
        String car_field = initCar.getText();
        String variant_field = field_option.getText();
        String real_coast = field_real_coast.getText();
        String insurer_coast = field_coast.getText();
        String franshise_one_field = field_franshise.getText();
        String franshise_two_field = field_second_franshise.getText();
        String payment_field = field_coast_year.getText();
        String payment_first_field = field_payment_real.getText();
        String order_payment_field = field_payment.getText();
        String type_payment_field = period_payment.getText();
        LocalDate polis_date = doDate.getValue();
        int id_rate = Integer.parseInt(polis.getId_rate());

        baseData.insertPolisData(number_polis, insurer_field, owner_field, territory_field, start_date, end_date, car_field, variant_field, real_coast, insurer_coast, franshise_one_field, franshise_two_field, payment_field, payment_first_field, order_payment_field, type_payment_field, polis_date, id_rate);

    }

    //метод поиска и вставки полиса
    @FXML
    public void clickSearchPolis() throws SQLException {

        try {
            BaseData baseData = new BaseData();
            int polis_number = Integer.parseInt(field_polis_number.getText());
            String person;

            HashMap<String, String> values = baseData.findPolis(polis_number);

            initPerson.setText(values.get("insurer_field"));
            initOwner.setText(values.get("owner_field"));
            field_territory.setText(values.get("territory_field"));
            startDate.setValue(LocalDate.parse(values.get("start_date")));
            endDate.setValue(LocalDate.parse(values.get("end_date")));
            initCar.setText(values.get("car_field"));
            field_option.setText(values.get("variant_field"));
            field_real_coast.setText(values.get("real_coast"));
            field_coast.setText(values.get("insurer_coast"));
            field_franshise.setText(values.get("franshise_one_field"));
            field_second_franshise.setText(values.get("franshise_two_field"));
            field_coast_year.setText(values.get("payment_field"));
            field_payment_real.setText(values.get("payment_first_field"));
            field_payment.setText(values.get("order_payment_field"));
            period_payment.setText(values.get("type_payment_field"));
            doDate.setValue(LocalDate.parse(values.get("polis_date")));

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Ошибка поиска");
            alert.setContentText("Полис с данным номером не найден");

            alert.showAndWait();

        }

    }

    //метод поиска и вставки страхователя из БД
    @FXML
    public void clickSearchPerson() throws SQLException {

        BaseData baseData = new BaseData();
        String vin = initPerson.getText();
        String person;

        HashMap<String, String> values = baseData.findPersonByID(vin);


        if (values.get("last_name") == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Ошибка поиска");
            alert.setContentText("Страхователь не найден");

            alert.showAndWait();

        } else {
            person = values.get("last_name") + " " + " " + values.get("first_name") + " " + " " + values.get("middle_name")
                    + " " + " " + values.get("typeDoc") + " " + " " + values.get("seriesDoc") + values.get("numberDoc") + " "
                    + "выдан " + " " + values.get("issuedBy") + " " + values.get("issuedDate") + " личный номер " + values.get("id_number");

            initPerson.setText(person);
        }

    }


    //метод поиска и вставки выгодоприобретателя из БД
    @FXML
    public void clickSearchOwner() throws SQLException {

        BaseData baseData = new BaseData();
        String vin = initOwner.getText();
        String person;

        HashMap<String, String> values = baseData.findPersonByID(vin);
        if (values.get("last_name") == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Ошибка поиска");
            alert.setContentText("Выгодоприобретатель не найден");

            alert.showAndWait();

        } else {
            person = values.get("last_name") + " " + " " + values.get("first_name") + " " + " " + values.get("middle_name")
                    + " личный номер " + values.get("id_number");

            initOwner.setText(person);
        }
    }

    //метод поиска и вставки авто из БД
    @FXML
    public void clickSearchCar() throws SQLException {
        BaseData baseData = new BaseData();
        String vin = initCar.getText();
        String car;

        HashMap<String, String> values = baseData.findCarByVIN(vin);
        if (values.get("brand") == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Ошибка поиска");
            alert.setContentText("Транспортное средство не найден");

            alert.showAndWait();

        } else {
            car = values.get("type") + " " + " " + values.get("brand") + " " + " " + values.get("model")
                    + " " + " " + values.get("number") + " " + " " + values.get("year") + " " + "VIN " + values.get("vin");
            initCar.setText(car);
        }
    }

    //метод вызова поля для вставки нового клиента
    @FXML
    public void clickNewPerson() throws IOException, SQLException {
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
        Stage stage = (Stage) btn_Next.getScene().getWindow();
        stage.close();


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
        field_payment_real.setText(polis.getPaymentReal() + " " + propisBelRub(polis.getPaymentReal()));
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

        Double coastYear = Double.valueOf(polis.getCoast_year());
        double first = new BigDecimal(coastYear * 40 / 100).setScale(0, RoundingMode.UP).doubleValue();
        double second = new BigDecimal((coastYear - first) / 3).setScale(0, RoundingMode.HALF_UP).doubleValue();

        double firstTwo = new BigDecimal((coastYear / 2)).setScale(0, RoundingMode.UP).doubleValue();
        double secondTwo = new BigDecimal((coastYear - firstTwo)).setScale(0, RoundingMode.HALF_UP).doubleValue();

        double third = new BigDecimal((coastYear - first) / 3).setScale(0, RoundingMode.HALF_UP).doubleValue();
        double four = new BigDecimal(coastYear - first - second - third).setScale(0, RoundingMode.HALF_UP).doubleValue();

        switch (polis.getPayment()) {
            case "Ежеквартально":
                payOption = String.valueOf(second) + " " + currency + String.valueOf(startDate.getValue().plusMonths(3).minusDays(1)) + " " +
                        String.valueOf(third) + " " + currency + String.valueOf(startDate.getValue().plusMonths(6).minusDays(1)) + " " +
                        String.valueOf(four) + " " + currency + String.valueOf(startDate.getValue().plusMonths(9).minusDays(1));
                period_payment.setText(payOption);
                period_payment.setWrapText(true);
                break;
            case "В два этапа":

                payOption = String.valueOf(secondTwo) + " " + currency + String.valueOf(startDate.getValue().plusMonths(6).minusDays(1));
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
