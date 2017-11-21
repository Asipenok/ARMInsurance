package inshurer.model;

import inshurer.view.PersonController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BaseData {

    private static final String URL = "jdbc:mysql://localhost:3306/insurance";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String INSERT_PERSON = "INSERT INTO person VALUES (?,?,?,?,?)";
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
            //  connection.close();

        } catch (Exception e) {
            // обработка ошибки
            System.out.println("Load driver Error");
            e.printStackTrace();
        }
    }

    //метод вставки клиента в БД
    public void insertPerson(String first_name, String last_name, String middle_name, String personal_number) throws SQLException {

        preparedStatement = connection.prepareStatement(INSERT_PERSON);
        try {

            preparedStatement.setString(2, first_name);
            preparedStatement.setString(3, last_name);
            preparedStatement.setString(4, middle_name);
            preparedStatement.setString(4, personal_number);

            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
