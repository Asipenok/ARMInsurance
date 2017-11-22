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
    private static final String INSERT_PERSON = "INSERT INTO person (FirstName, LastName, MiddleName, PersonalNumber, Birthday) VALUES (?,?,?,?,?)";
    private static final String INSERT_ADRESS = "INSERT INTO adress (Country, Region, Distric, City, Street, HouseNumber, BuildNumber, ApartmentNumber) VALUES (?,?,?,?,?,?,?,?)";
    private static final String INSERT_DOC = "INSERT INTO document (TypeDoc, SeriyaDoc, NumberDoc, IssuedBy, DateIssued) VALUES (?,?,?,?,?)";
    private static final String SELECT_PERSON = "SELECT * FROM person WHERE PersonalNumber = ?";

    private Connection connection;
    private PreparedStatement preparedStatement;
    PersonController personController = new PersonController();

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

    //метод вставки клиента в БД
    public void insertPerson(String first_name, String last_name, String middle_name, String personal_number, LocalDate birthday) throws SQLException {

        preparedStatement = connection.prepareStatement(INSERT_PERSON);

        try {
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, middle_name);
            preparedStatement.setString(4, personal_number);
            preparedStatement.setString(5, String.valueOf(birthday));

            preparedStatement.execute();

            System.out.println("person add");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //метод вставки клиента в БД
    public void insertAdress(String country, String region, String distric, String city, String street, String house, String build, String apartment) throws SQLException {

        preparedStatement = connection.prepareStatement(INSERT_ADRESS);

        try {
            preparedStatement.setString(1, country);
            preparedStatement.setString(2, region);
            preparedStatement.setString(3, distric);
            preparedStatement.setString(4, city);
            preparedStatement.setString(5, street);
            preparedStatement.setString(6, house);
            preparedStatement.setString(7, build);
            preparedStatement.setString(8, apartment);

            preparedStatement.execute();
            connection.close();
            System.out.println("adress add");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //метод вставки документа в БД
    public void insertDocument(String type_doc, String seriya_doc, String number_doc, String issued_by, LocalDate issued) throws SQLException {

        preparedStatement = connection.prepareStatement(INSERT_DOC);

        try {
            preparedStatement.setString(1, type_doc);
            preparedStatement.setString(2, seriya_doc);
            preparedStatement.setString(3, number_doc);
            preparedStatement.setString(4, issued_by);
            preparedStatement.setString(5, String.valueOf(issued));

            preparedStatement.execute();

            System.out.println("document add");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //метод поиска клиента в БД
    public HashMap<String, String> findPersonByID(String personal_number) throws SQLException {
        HashMap<String, String> resHashMap = new HashMap<String, String>();
        PersonController personController = new PersonController();
//        preparedStatement = connection.prepareStatement(SELECT_PERSON);
//        preparedStatement.setString(1, personal_number);
        try {
            preparedStatement = connection.prepareStatement(SELECT_PERSON);
            preparedStatement.setString(1, personal_number);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                resHashMap.put("id_number", personal_number);
                resHashMap.put("first_name", res.getString("FirstName"));
                resHashMap.put("last_name", res.getString("LastName"));
                resHashMap.put("middle_name", res.getString("MiddleName"));
            }


            System.out.println("search off");
        } catch (
                SQLException e)

        {
            e.printStackTrace();
        }
        return resHashMap;
    }

}
