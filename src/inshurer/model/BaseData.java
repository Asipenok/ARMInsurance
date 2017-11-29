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
    private static final String INSERT_RATE = "INSERT INTO rate (vehicle, territory, quantity, protect, level_driver, rent_taxi, " +
            "condition_franchise, no_condition_franchise, additional_types, bonus, manus, payment, ads, salon, employee, cars, company, rate, optionInsurer, realCoast) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SELECT_PERSON = "SELECT * FROM persondata WHERE PersonalNumber = ?";
    private static final String SELECT_RATE_ERGO = "SELECT * FROM rate ORDER BY id DESC LIMIT 1";
    private static final String SELECT_LAST_NAME = "SELECT * FROM persondata WHERE LastName = ?";
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
                               String company, Double rate, Double optionInsurer, Double realCoast) throws SQLException {

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

            }
            System.out.println("search item ");

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return resRateERGO;
    }


}
