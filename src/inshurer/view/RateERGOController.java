package inshurer.view;

import inshurer.Main;
import inshurer.model.BaseData;
import inshurer.model.ERGO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;

import static java.lang.String.format;


public class RateERGOController {

    private Main main;

    private ERGO ergo = new ERGO();

    //заполнение коэффициента по типу ТС
    ObservableList<String> vehicle = FXCollections.observableArrayList
            ("Легковой автомобиль", "Автобусы, грузовые авто", "Тракторы, прицепы");
    //заполнение коэфф по варианту страхования
    ObservableList<String> option = FXCollections.observableArrayList
            ("Вариант 1 - без учета износа, А - Базовый", "Вариант 1 - без учета износа, Б - Стандарт",
                    "Вариант 1 - без учета износа, В - Премиум", "Вариант 2 - с учетом износа");
    //заполнение коэфф по территории действия договора
    ObservableList<String> teritory = FXCollections.observableArrayList
            ("Все страны мира (за исключением регионов военных действий", "Республика Беларусь");
    //заполнение коэфф по количеству застрахованных ТС
    ObservableList<String> quantity = FXCollections.observableArrayList
            ("1 единица", "2 единицы", "3-5 единиц");
    //заполнение коэфф средства защиты ТС
    ObservableList<String> protect = FXCollections.observableArrayList
            (" ", "механическое", "электронное", "оба вида зщиты", "противоугонная маркировка", "спутник");
    //заполнение коэфф стаж
    ObservableList<String> level_driver = FXCollections.observableArrayList
            ("мультидрайв", "стаж более 5 лет", "стаж более 10 лет");
    //заполнение коэфф по условию эксплуатации
    ObservableList<String> rent_taxi = FXCollections.observableArrayList
            (" ", "договор аредны (прокат)", "такси, обучение вождению");
    //заполнение коэфф по безусловная франшиза
    ObservableList<String> condition_franchise = FXCollections.observableArrayList
            ("Без франшизы", "5%", "10%", "20%", "30%");
    //заполнение коэфф по условная франшиза
    ObservableList<String> no_condition_franchise = FXCollections.observableArrayList
            (" ", "100$", "200$", "300$", "400$", "500$");
    //заполнение коэфф по доп полисам
    ObservableList<String> additional_types = FXCollections.observableArrayList
            (" ", "1 вид", "2 вида", "3 вида");
    //заполнение коэфф по скидкам
    ObservableList<String> bonus = FXCollections.observableArrayList
            (" ", "A1", "A2", "A3", "A4", "A5");
    //заполнение коэфф по убыткам
    ObservableList<String> manus = FXCollections.observableArrayList
            (" ", "B1", "B2", "B3", "B4", "B5");
    //заполнение коэфф стоимость авто
    ObservableList<String> price_car = FXCollections.observableArrayList
            (" ", "от 20 000$ до 40 000$", "от 40 000$");
    //заполнение коэфф порядок оплаты
    ObservableList<String> payment = FXCollections.observableArrayList
            ("ежеквартально", "в два срока", "единовременно");
    //заполнение валюты платежа
    ObservableList<String> currency = FXCollections.observableArrayList
            ("USD", "EUR", "RUB", "BYN");


    @FXML
    private ComboBox boxVehicle;
    @FXML
    private ComboBox boxOption;
    @FXML
    private ComboBox boxTeritoty;
    @FXML
    private ComboBox boxQuantity;
    @FXML
    private ComboBox boxProtect;
    @FXML
    private ComboBox boxLevelDriver;
    @FXML
    private ComboBox boxRent_Taxi;
    @FXML
    private ComboBox boxCondition_franchise;
    @FXML
    private ComboBox boxNo_condition_franchise;
    @FXML
    private ComboBox boxAdditional_types;
    @FXML
    private ComboBox boxBonus;
    @FXML
    private ComboBox boxManus;
    @FXML
    private ComboBox boxPrice_Car;
    @FXML
    private ComboBox boxPayment;
    @FXML
    private ComboBox boxCurrency;
    @FXML
    private Button calculate;
    @FXML
    private Button saveAs;
    @FXML
    private Button next;
    @FXML
    private RadioButton adsYes;
    @FXML
    private RadioButton adsNo;
    @FXML
    private ToggleGroup ads;
    @FXML
    private RadioButton salonYes;
    @FXML
    private RadioButton salonNo;
    @FXML
    private RadioButton employeeYes;
    @FXML
    private RadioButton employeeNo;
    @FXML
    private RadioButton dangerCarYes;
    @FXML
    private RadioButton dangerCarNo;
    @FXML
    private TextField rezultCalc;
    @FXML
    private TextField field_currencyValue;
    @FXML
    private TextField field_coastCar;
    @FXML
    private TextField field_payment;
    @FXML
    private TextField field_first_currency;
    @FXML
    private TextField field_second_currency;
    @FXML
    private TextField field_third_currency;
    @FXML
    private TextField field_fourth_currency;
    @FXML
    private TextField field_first_pay;
    @FXML
    private TextField field_second_pay;
    @FXML
    private TextField field_third_pay;
    @FXML
    private TextField field_fourth_pay;

    //инициализация полей списков
    @FXML
    private void initialize() {

        boxVehicle.setValue("Легковой автомобиль");
        boxVehicle.setItems(vehicle);

        boxOption.setValue("Вариант 1 - без учета износа, Б - Стандарт");
        boxOption.setItems(option);

        boxTeritoty.setValue("Все страны мира (за исключением регионов военных действий");
        boxTeritoty.setItems(teritory);

        boxQuantity.setValue("1 единица");
        boxQuantity.setItems(quantity);

        boxProtect.setValue("оба вида защиты");
        boxProtect.setItems(protect);

        boxLevelDriver.setValue("мультидрайв");
        boxLevelDriver.setItems(level_driver);

        boxRent_Taxi.setValue(" ");
        boxRent_Taxi.setItems(rent_taxi);

        boxCondition_franchise.setValue("Без франшизы");
        boxCondition_franchise.setItems(condition_franchise);

        boxNo_condition_franchise.setValue(" ");
        boxNo_condition_franchise.setItems(no_condition_franchise);

        boxAdditional_types.setValue(" ");
        boxAdditional_types.setItems(additional_types);

        boxBonus.setValue(" ");
        boxBonus.setItems(bonus);

        boxManus.setValue(" ");
        boxManus.setItems(manus);

        boxPrice_Car.setValue(" ");
        boxPrice_Car.setItems(price_car);

        boxPayment.setValue("ежеквартально");
        boxPayment.setItems(payment);

        boxCurrency.setValue("USD");
        boxCurrency.setItems(currency);


    }

    //расчет тарифа
    @FXML
    private void onClickCalculate() {

        ergo.calculateRate(String.valueOf(boxVehicle.getValue()),
                String.valueOf(boxTeritoty.getValue()),
                String.valueOf(boxQuantity.getValue()),
                String.valueOf(boxProtect.getValue()),
                String.valueOf(boxLevelDriver.getValue()),
                String.valueOf(boxRent_Taxi.getValue()),
                String.valueOf(boxCondition_franchise.getValue()),
                String.valueOf(boxNo_condition_franchise.getValue()),
                String.valueOf(boxAdditional_types.getValue()),
                String.valueOf(boxBonus.getValue()),
                String.valueOf(boxManus.getValue()),
                String.valueOf(boxPayment.getValue()),
                groupABS(), groupSalon(), groupEmployee(), groupCar()
        );

        String rez = String.valueOf(ergo.getRezCalc());
        rez = String.valueOf(new BigDecimal(rez).setScale(2, RoundingMode.HALF_UP).floatValue());
        rezultCalc.setText(rez);
        paymentOption();
    }

    //получение значания из радиогруппы РЕКЛАМА
    @FXML
    private String groupABS() {
        String adsRez;
        if (adsYes.isSelected()) {
            adsRez = "Yes";
        } else {
            adsRez = "No";
        }
        return adsRez;
    }

    //получение значания из радиогруппы САЛОН
    @FXML
    private String groupSalon() {
        String salonRez;
        if (salonYes.isSelected()) {
            salonRez = "Yes";
        } else {
            salonRez = "No";
        }
        return salonRez;
    }

    //получение значания из радиогруппы СОТРУДНИК
    @FXML
    private String groupEmployee() {
        String employeeRez;
        if (employeeYes.isSelected()) {
            employeeRez = "Yes";
        } else {
            employeeRez = "No";
        }
        return employeeRez;
    }

    //получение значания из радиогруппы CAR
    @FXML
    private String groupCar() {
        String carRez;
        if (dangerCarYes.isSelected()) {
            carRez = "Yes";
        } else {
            carRez = "No";
        }
        return carRez;
    }

    //запись данных по тарифу
    @FXML
    private void onClickSave() {
        BaseData baseData = new BaseData();
        String company = "ERGO";

        try {
            baseData.insertRateData(ergo.getVehicleRate(), ergo.getTerritoryRate(), ergo.getQuantityRate(), ergo.getProtectRate(), ergo.getLevel_driverRate(),
                    ergo.getRent_taxiRate(), ergo.getCondition_franchiseRate(), ergo.getNo_condition_franchiseRate(), ergo.getAdditional_typesRate(),
                    ergo.getBonusRate(), ergo.getManusRate(), ergo.getPaymentRate(), ergo.getAdsRate(), ergo.getSalonRate(), ergo.getEmployeeRate(),
                    ergo.getCarsRate(), company, ergo.getRezCalc());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void onClickNext() throws IOException {
        main.showPerson();
    }

    @FXML
    private void paymentOption() {

        double coast = Double.valueOf(String.valueOf(field_coastCar.getText()));
        double currencyValue = Double.valueOf(String.valueOf(field_currencyValue.getText()));
        double rateRez = Double.valueOf(String.valueOf(rezultCalc.getText()));
        double coastYear = coast * rateRez / 100;
        double coastYearCurrency = coastYear * currencyValue;

        field_payment.setText(String.valueOf(new BigDecimal(coastYear).setScale(2, RoundingMode.HALF_UP).floatValue()));

        switch (String.valueOf(boxPayment.getValue())) {

            case "ежеквартально":
                field_first_currency.setText(String.valueOf(coastYear / 4));
                field_second_currency.setText(String.valueOf(coastYear - coastYear / 4));
                field_third_currency.setText(String.valueOf(coastYear - 2 * coastYear / 4));
                field_fourth_currency.setText(String.valueOf(coastYear - 3 * coastYear / 4));

                field_first_pay.setText(String.valueOf(coastYear / 4 * currencyValue));
                field_second_pay.setText(String.valueOf((coastYear - coastYear / 4) * currencyValue));
                field_third_pay.setText(String.valueOf((coastYear - 2 * coastYear / 4) * currencyValue));
                field_fourth_pay.setText(String.valueOf((coastYear - 3 * coastYear / 4) * currencyValue));


                break;

            case "в два срока":
                field_first_currency.setText(String.valueOf(coastYear / 4 * currencyValue));
                field_second_currency.setText(String.valueOf(coastYear - coastYear / 2));
                field_third_currency.setText("");
                field_fourth_currency.setText("");

                field_first_pay.setText(String.valueOf(coastYear / 2 * currencyValue));
                field_second_pay.setText(String.valueOf((coastYear - coastYear / 2) * currencyValue));
                field_third_pay.setText("");
                field_fourth_pay.setText("");

                break;

            case "единовременно":
                field_first_currency.setText(String.valueOf(coastYear));
                field_second_currency.setText("");
                field_third_currency.setText("");
                field_fourth_currency.setText("");

                field_first_pay.setText(String.valueOf(coastYearCurrency));
                field_second_pay.setText("");
                field_third_pay.setText("");
                field_fourth_pay.setText("");

                break;

        }

    }
}

