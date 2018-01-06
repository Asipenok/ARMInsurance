package inshurer.model;

import inshurer.view.RateERGOController;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public class Polis {

    private BaseData baseData = new BaseData();
    private RateERGOController rateERGOController;

    public String getTerritory() throws SQLException {
        initFieldPolis();
        return territory;
    }

    public String getOption() throws SQLException {
        initFieldPolis();
        return option;
    }

    public String getFranchise() throws SQLException {
        initFieldPolis();
        return franchise;
    }

    public String getFranchiseSecond() throws SQLException {
        initFieldPolis();
        return franchise_second;
    }

    public String getPayment() throws SQLException {
        initFieldPolis();
        return payment;
    }

    public String getPaymentReal() throws SQLException {
        initFieldPolis();
        return payment_real;
    }

    public String getRealCoast() throws SQLException {
        initFieldPolis();
        return real_coast;
    }

    public String getCoast_year() throws SQLException {
        initFieldPolis();
        return coast_year;
    }

    public String getCurrency() throws SQLException {
        initFieldPolis();
        return currency;
    }

    public String getRate() throws SQLException {
        initFieldPolis();
        return rate;
    }

    public String getId_rate() throws SQLException {
        return id_rate;
    }


    private String territory;
    private String option;
    private String franchise;
    private String franchise_second;
    private String payment;
    private String payment_real;
    private String real_coast;
    private String coast_year;
    private String currency;
    private String rate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String id_rate;


    public void initFieldPolis() throws SQLException {

        HashMap<String, String> values = baseData.findRate();

        //значение перменной годового взноса
        coast_year = values.get("coast_year");
        //запись в поле стоимость авто
        real_coast = values.get("realCoast");
        //получение валюты
        currency = values.get("currency");
        //получение тарифа
        rate = values.get("rate");
        //получение первой оплаты
        payment_real = values.get("first_pay");
        // получение id рассчета
        id_rate = values.get("id");


        //запись в поле территория действия полиса
        if (values.get("territory").equals("1")) {
            territory = "Все страны мира (за исключением регионов военных действий)";
        } else {
            territory = "Республика Беларусь";
        }

        //запись в поле вариант страхования
        switch (values.get("optionInsurer")) {
            case "1.2":
                option = "Вариант 1 - без учета износа, Б - Стандарт";
                break;
            case "1.4":
                option = "Вариант 1 - без учета износа, В - Премиум";
                break;
            case "1":
                option = "Вариант 2 - с учетом износа";
                break;
        }
        //запись в поле безусловная франшиза
        switch (values.get("condition_franchise")) {
            case "0.95":
                franchise = "Безусловная франшиза 5% на угон, хищение ТС";
                break;
            case "0.9":
                franchise = "Безусловная франшиза 10% на угон, хищение ТС";
                break;
            case "0.85":
                franchise = "Безусловная франшиза 20% на угон, хищение ТС";
                break;
            case "0.8":
                franchise = "Безусловная франшиза 30% на угон, хищение ТС";
                break;
            case "1":
                franchise = "Без франшизы";
                break;
        }
        //запись в поле условная франшиза
        switch (values.get("no_condition_franchise")) {
            case "0.9":
                franchise_second = "Условная франшиза 200$";
                break;
            case "0.85":
                franchise_second = "Условная франшиза 300$";
                break;
            case "0.8":
                franchise_second = "Условная франшиза 400$";
                break;
            case "0.75":
                franchise_second = "Условная франшиза 500$";
                break;
            case "1":
                franchise_second = " ";
                break;
        }
        //запись в поле порядок оплаты
        switch (values.get("payment")) {
            case "0.9":
                payment = "Единовременно";
                break;
            case "1":
                payment = "В два этапа";
                break;
            case "1.05":
                payment = "Ежеквартально";
                break;

        }


    }
}


