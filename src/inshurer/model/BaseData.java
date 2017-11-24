package inshurer.model;

import inshurer.view.PersonController;
import javafx.scene.control.DatePicker;

import java.io.IOException;
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
    private static final String INSERT_CAR = "INSERT INTO car (Brand, ModelCar, VIN, NumberCar, YearCar, CoastCar, Currency ) VALUES (?,?,?,?,?,?,?)";
    private static final String SELECT_PERSON = "SELECT * FROM persondata WHERE PersonalNumber = ?";
    private static final String SELECT_LAST_NAME = "SELECT * FROM persondata WHERE LastName = ?";
    private static final String SELECT_CAR_VIN = "SELECT * FROM car WHERE VIN = ?";

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
    public void insertCar(String brandCar, String modelCar, String vin, String numberCar, LocalDate yearCar, String coastCar, String currencyCar) throws SQLException {
        preparedStatement = connection.prepareStatement(INSERT_CAR);

        try {
            preparedStatement.setString(1, brandCar);
            preparedStatement.setString(2, modelCar);
            preparedStatement.setString(3, vin);
            preparedStatement.setString(4, numberCar);
            preparedStatement.setString(5, String.valueOf(yearCar));
            preparedStatement.setString(6, coastCar);
            preparedStatement.setString(7, currencyCar);

            preparedStatement.execute();
            connection.close();
            System.out.println("car add");
        } catch (SQLException e) {
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
                resCarHashMap.put("vin", res.getString("VIN"));
                resCarHashMap.put("brand", res.getString("Brand"));
                resCarHashMap.put("model", res.getString("ModelCar"));
                resCarHashMap.put("number", res.getString("NumberCar"));
                resCarHashMap.put("year", res.getString("YearCar"));
                resCarHashMap.put("coast", res.getString("CoastCar"));
                resCarHashMap.put("currency", res.getString("Currency"));
            }
          System.out.println("search car by vin off");

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return resCarHashMap;
    }
}
