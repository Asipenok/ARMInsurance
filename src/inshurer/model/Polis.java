package inshurer.model;

import java.sql.SQLException;
import java.util.HashMap;

public class Polis {

    private BaseData baseData = new BaseData();


    public String getTerritory() throws SQLException {
        initFieldPolis();
        return territory;
    }

    public String getOption() throws SQLException {
        initFieldPolis();
        return option;
    }

    private String territory;
    private String option;


    public void initFieldPolis() throws SQLException {

        HashMap<String, String> values = baseData.findRate();

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
    }
}


