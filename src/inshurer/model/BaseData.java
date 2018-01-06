package inshurer.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;


public class BaseData {

    private static final String URL = "jdbc:mysql://localhost:3306/insurance";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String INSERT_PERSONDATA = "INSERT INTO persondata (LastName, FirstName, MiddleName, PersonalNumber," +
            " Birthday, TypeDoc, SeriesDoc, NumberDoc,IssuedBy, IssuedDate," +
            " Country, Region, Distric, City, Street, HouseNumber, BuildNumber, RoomNumber)" +
            " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_CAR = "INSERT INTO car (TypeCar, Brand, ModelCar, VIN, NumberCar, YearCar, CoastCar, Currency ) VALUES (?,?,?,?,?,?,?,?)";
    private static final String INSERT_POLIS = "INSERT INTO polis (number_polis, insurer_field, owner_field,territory_field,start_date, " +
            "end_date, car_field, variant_field, real_coast,insurer_coast,  franshise_one_field, franshise_two_field, " +
            "payment_field, payment_first_field, order_payment_field, type_payment_field, polis_date, id_rate) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_RATE = "INSERT INTO rate (vehicle, territory, quantity, protect, level_driver, rent_taxi, " +
            "condition_franchise, no_condition_franchise, additional_types, bonus, manus, payment, ads, salon, employee, cars, company, " +
            "rate, optionInsurer, realCoast, currency, coast_year, firstPay, currencyValue) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SELECT_PERSON = "SELECT * FROM persondata WHERE PersonalNumber = ?";
    private static final String SELECT_RATE_ERGO = "SELECT * FROM rate ORDER BY id DESC LIMIT 1";
    private static final String SELECT_LAST_NAME = "SELECT * FROM persondata WHERE LastName = ?";
    private static final String SELECT_POLIS_NUMBER = "SELECT * FROM polis WHERE number_polis = ?";
    private static final String SELECT_CAR_VIN = "SELECT * FROM car WHERE VIN = ?";
    private static final String SELECT_CAR_NUMBER = "SELECT * FROM car WHERE NumberCar = ?";


    private Connection connection;
    private PreparedStatement preparedStatement;


    public BaseData() {

        try {
            // загружаем драйвер
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("all right");
            }

        } catch (Exception e) {
            // обработка ошибки
            System.out.println("Load driver Error");
            e.printStackTrace();
        }
    }

    //метод поиска клиента в БД по личному номеру
    public HashMap<String, String> findPersonByID(String personal_number) throws SQLException {
        HashMap<String, String> resHashMap = new HashMap<String, String>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_PERSON);
            preparedStatement.setString(1, personal_number);
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {

                resHashMap.put("id_number", personal_number);
                resHashMap.put("last_name", res.getString("LastName"));
                resHashMap.put("first_name", res.getString("FirstName"));
                resHashMap.put("middle_name", res.getString("MiddleName"));
                resHashMap.put("birthday", res.getString("Birthday"));
                resHashMap.put("typeDoc", res.getString("TypeDoc"));
                resHashMap.put("seriesDoc", res.getString("SeriesDoc"));
                resHashMap.put("numberDoc", res.getString("NumberDoc"));
                resHashMap.put("issuedBy", res.getString("IssuedBy"));
                resHashMap.put("issuedDate", res.getString("IssuedDate"));
                resHashMap.put("country", res.getString("Country"));
                resHashMap.put("region", res.getString("Region"));
                resHashMap.put("distric", res.getString("Distric"));
                resHashMap.put("city", res.getString("City"));
                resHashMap.put("street", res.getString("Street"));
                resHashMap.put("houseNumber", res.getString("HouseNumber"));
                resHashMap.put("buildNumber", res.getString("BuildNumber"));
                resHashMap.put("roomNumber", res.getString("RoomNumber"));

            }
            System.out.println("search off");
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return resHashMap;
    }

    //метод поиска клиента в БД по фамилии
    public HashMap<String, String> findPersonByLastName(String last_name) throws SQLException {
        HashMap<String, String> resHashMap = new HashMap<String, String>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_LAST_NAME);
            preparedStatement.setString(1, last_name);
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                resHashMap.put("id", res.getString("id"));
                resHashMap.put("last_name", last_name);
                resHashMap.put("id_number", res.getString("PersonalNumber"));
                resHashMap.put("first_name", res.getString("FirstName"));
                resHashMap.put("middle_name", res.getString("MiddleName"));
                resHashMap.put("birthday", res.getString("Birthday"));
                resHashMap.put("typeDoc", res.getString("TypeDoc"));
                resHashMap.put("seriesDoc", res.getString("SeriesDoc"));
                resHashMap.put("numberDoc", res.getString("NumberDoc"));
                resHashMap.put("issuedBy", res.getString("IssuedBy"));
                resHashMap.put("issuedDate", res.getString("IssuedDate"));
                resHashMap.put("country", res.getString("Country"));
                resHashMap.put("region", res.getString("Region"));
                resHashMap.put("distric", res.getString("Distric"));
                resHashMap.put("city", res.getString("City"));
                resHashMap.put("street", res.getString("Street"));
                resHashMap.put("houseNumber", res.getString("HouseNumber"));
                resHashMap.put("buildNumber", res.getString("BuildNumber"));
                resHashMap.put("roomNumber", res.getString("RoomNumber"));

            }
            System.out.println("search off");
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return resHashMap;
    }

    //метод вставки в БД данные по страхователю
    public void insertPersonData(String firstName, String lastName, String middleName, String personalNumber, LocalDate birthday,
                                 String typeDoc, String seriesDoc, String numberDoc, String issuedBy, LocalDate issuedDate,
                                 String country, String region, String distric, String city, String street, String houseNumber,
                                 String buildNumber, String roomNumber) throws SQLException {

        preparedStatement = connection.prepareStatement(INSERT_PERSONDATA);

        try {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, middleName);
            preparedStatement.setString(4, personalNumber);
            preparedStatement.setString(5, String.valueOf(birthday));
            preparedStatement.setString(6, typeDoc);
            preparedStatement.setString(7, seriesDoc);
            preparedStatement.setString(8, numberDoc);
            preparedStatement.setString(9, issuedBy);
            preparedStatement.setString(10, String.valueOf(issuedDate));
            preparedStatement.setString(11, country);
            preparedStatement.setString(12, region);
            preparedStatement.setString(13, distric);
            preparedStatement.setString(14, city);
            preparedStatement.setString(15, street);
            preparedStatement.setString(16, houseNumber);
            preparedStatement.setString(17, buildNumber);
            preparedStatement.setString(18, roomNumber);

            preparedStatement.execute();
            connection.close();
            System.out.println("personData add");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //метод вставки данных по авто
    public void insertCar(String typeCar, String brandCar, String modelCar, String vin, String numberCar, LocalDate yearCar,
                          String coastCar, String currencyCar) throws SQLException {
        preparedStatement = connection.prepareStatement(INSERT_CAR);

        try {
            preparedStatement.setString(1, typeCar);
            preparedStatement.setString(2, brandCar);
            preparedStatement.setString(3, modelCar);
            preparedStatement.setString(4, vin);
            preparedStatement.setString(5, numberCar);
            preparedStatement.setString(6, String.valueOf(yearCar));
            preparedStatement.setString(7, coastCar);
            preparedStatement.setString(8, currencyCar);

            preparedStatement.execute();

            System.out.println("car add");
        } catch (SQLException e) {
            System.out.println("Вставь уникальный ВИН");
            e.printStackTrace();
        }
    }

    //метод поиска клиента в БД по vin
    public HashMap<String, String> findCarByVIN(String vin) throws SQLException {

        HashMap<String, String> resCarHashMap = new HashMap<String, String>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_CAR_VIN);
            preparedStatement.setString(1, vin);
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                resCarHashMap.put("id", res.getString("id"));
                resCarHashMap.put("vin", res.getString("VIN"));
                resCarHashMap.put("brand", res.getString("Brand"));
                resCarHashMap.put("model", res.getString("ModelCar"));
                resCarHashMap.put("number", res.getString("NumberCar"));
                resCarHashMap.put("year", res.getString("YearCar"));
                resCarHashMap.put("coast", res.getString("CoastCar"));
                resCarHashMap.put("currency", res.getString("Currency"));
                resCarHashMap.put("type", res.getString("TypeCar"));

            }
            System.out.println("search car by vin off");

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return resCarHashMap;
    }

    //метод поиска клиента в БД по номеру авто
    public HashMap<String, String> findCarByNumber(String search_number) throws SQLException {

        HashMap<String, String> resCarHashMap = new HashMap<String, String>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_CAR_NUMBER);
            preparedStatement.setString(1, search_number);
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                resCarHashMap.put("id", res.getString("id"));
                resCarHashMap.put("vin", res.getString("VIN"));
                resCarHashMap.put("brand", res.getString("Brand"));
                resCarHashMap.put("model", res.getString("ModelCar"));
                resCarHashMap.put("number", res.getString("NumberCar"));
                resCarHashMap.put("year", res.getString("YearCar"));
                resCarHashMap.put("coast", res.getString("CoastCar"));
                resCarHashMap.put("currency", res.getString("Currency"));
                resCarHashMap.put("type", res.getString("TypeCar"));
            }
            System.out.println("search car by number off");

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return resCarHashMap;
    }


    //метод вставки в БД данные по тарифу
    public void insertRateData(Double vehicle, Double territory, Double quantity, Double protect, Double level_driver,
                               Double rent_taxi, Double condition_franchise, Double no_condition_franchise, Double additional_types,
                               Double bonus, Double manus, Double payment, Double ads, Double salon, Double employee, Double cars,
                               String company, Double rate, Double optionInsurer, Double realCoast, String currency, Double coast_year,
                               Double first_pay, Double currencyValue) throws SQLException {

        preparedStatement = connection.prepareStatement(INSERT_RATE);

        try {
            preparedStatement.setDouble(1, vehicle);
            preparedStatement.setDouble(2, territory);
            preparedStatement.setDouble(3, quantity);
            preparedStatement.setDouble(4, protect);
            preparedStatement.setDouble(5, level_driver);
            preparedStatement.setDouble(6, rent_taxi);
            preparedStatement.setDouble(7, condition_franchise);
            preparedStatement.setDouble(8, no_condition_franchise);
            preparedStatement.setDouble(9, additional_types);
            preparedStatement.setDouble(10, bonus);
            preparedStatement.setDouble(11, manus);
            preparedStatement.setDouble(12, payment);
            preparedStatement.setDouble(13, ads);
            preparedStatement.setDouble(14, salon);
            preparedStatement.setDouble(15, employee);
            preparedStatement.setDouble(16, cars);
            preparedStatement.setString(17, company);
            preparedStatement.setDouble(18, rate);
            preparedStatement.setDouble(19, optionInsurer);
            preparedStatement.setDouble(20, realCoast);
            preparedStatement.setString(21, currency);
            preparedStatement.setDouble(22, coast_year);
            preparedStatement.setDouble(23, first_pay);
            preparedStatement.setDouble(24, currencyValue);

            preparedStatement.execute();
            connection.close();
            System.out.println("rate add");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //метод выборки данных по тарифу из БД
    public HashMap<String, String> findRate() throws SQLException {

        HashMap<String, String> resRateERGO = new HashMap<String, String>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_RATE_ERGO);
            // preparedStatement.setString(1, String.valueOf(id));
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                resRateERGO.put("id", res.getString("id"));
                resRateERGO.put("vehicle", res.getString("vehicle"));
                resRateERGO.put("territory", res.getString("territory"));
                resRateERGO.put("quantity", res.getString("quantity"));
                resRateERGO.put("protect", res.getString("protect"));
                resRateERGO.put("level_driver", res.getString("level_driver"));
                resRateERGO.put("rent_taxi", res.getString("rent_taxi"));
                resRateERGO.put("condition_franchise", res.getString("condition_franchise"));
                resRateERGO.put("no_condition_franchise", res.getString("no_condition_franchise"));
                resRateERGO.put("additional_types", res.getString("additional_types"));
                resRateERGO.put("bonus", res.getString("bonus"));
                resRateERGO.put("manus", res.getString("manus"));
                resRateERGO.put("payment", res.getString("payment"));
                resRateERGO.put("ads", res.getString("ads"));
                resRateERGO.put("salon", res.getString("salon"));
                resRateERGO.put("employee", res.getString("employee"));
                resRateERGO.put("cars", res.getString("cars"));
                resRateERGO.put("company", res.getString("company"));
                resRateERGO.put("optionInsurer", res.getString("optionInsurer"));
                resRateERGO.put("realCoast", res.getString("realCoast"));
                resRateERGO.put("currency", res.getString("currency"));
                resRateERGO.put("coast_year", res.getString("coast_year"));
                resRateERGO.put("rate", res.getString("rate"));
                resRateERGO.put("first_pay", res.getString("firstPay"));
                resRateERGO.put("currencyValue", res.getString("currencyValue"));


            }
            //   System.out.println("search item ");

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return resRateERGO;
    }


    //метод вставки в БД данные по полису
    public void insertPolisData(int number_polis, String insurer_field, String owner_field, String territory_field,
                                LocalDate start_date, LocalDate end_date, String car_field, String variant_field, String real_coast,
                                String insurer_coast, String franshise_one_field, String franshise_two_field,
                                String payment_field, String payment_first_field, String order_payment_field,
                                String type_payment_field, LocalDate polis_date, int id_rate) throws SQLException {

        preparedStatement = connection.prepareStatement(INSERT_POLIS);

        try {
            preparedStatement.setInt(1, number_polis);
            preparedStatement.setString(2, insurer_field);
            preparedStatement.setString(3, owner_field);
            preparedStatement.setString(4, territory_field);
            preparedStatement.setString(5, String.valueOf(start_date));
            preparedStatement.setString(6, String.valueOf(end_date));
            preparedStatement.setString(7, car_field);
            preparedStatement.setString(8, variant_field);
            preparedStatement.setString(9, real_coast);
            preparedStatement.setString(10, insurer_coast);
            preparedStatement.setString(11, franshise_one_field);
            preparedStatement.setString(12, franshise_two_field);
            preparedStatement.setString(13, payment_field);
            preparedStatement.setString(14, payment_first_field);
            preparedStatement.setString(15, order_payment_field);
            preparedStatement.setString(16, type_payment_field);
            preparedStatement.setString(17, String.valueOf(polis_date));
            preparedStatement.setInt(18, id_rate);

            preparedStatement.execute();
            connection.close();
            System.out.println("polis add");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //метод выборки данных по номеру полиса
    public HashMap<String, String> findPolis(int number_polis) throws SQLException {

        HashMap<String, String> resPolisNumber = new HashMap<String, String>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_POLIS_NUMBER);
            preparedStatement.setInt(1, number_polis);
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                resPolisNumber.put("insurer_field", res.getString("insurer_field"));
                resPolisNumber.put("owner_field", res.getString("owner_field"));
                resPolisNumber.put("territory_field", res.getString("territory_field"));
                resPolisNumber.put("start_date", res.getString("start_date"));
                resPolisNumber.put("end_date", res.getString("end_date"));
                resPolisNumber.put("car_field", res.getString("car_field"));
                resPolisNumber.put("variant_field", res.getString("variant_field"));
                resPolisNumber.put("real_coast", res.getString("real_coast"));
                resPolisNumber.put("insurer_coast", res.getString("insurer_coast"));
                resPolisNumber.put("franshise_one_field", res.getString("franshise_one_field"));
                resPolisNumber.put("franshise_two_field", res.getString("franshise_two_field"));
                resPolisNumber.put("payment_field", res.getString("payment_field"));
                resPolisNumber.put("payment_first_field", res.getString("payment_first_field"));
                resPolisNumber.put("order_payment_field", res.getString("order_payment_field"));
                resPolisNumber.put("type_payment_field", res.getString("type_payment_field"));
                resPolisNumber.put("polis_date", res.getString("polis_date"));
                           }
            //   System.out.println("search item ");

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return resPolisNumber;
    }

}
