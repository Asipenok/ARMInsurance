package inshurer.model;

import java.sql.Connection;
import java.sql.DriverManager;


public class BaseData {

    private static final String URL = "jdbc:mysql://localhost:3306/insurance";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private Connection connection;

    public BaseData() {

        try {
            // загружаем драйвер
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection (URL, USERNAME, PASSWORD);

            if (!connection.isClosed()) {
                System.out.println("all right");
            }
            connection.close();

        } catch (Exception e) {
            // обработка ошибки
            System.out.println("Load driver Error");
            e.printStackTrace();
        }
    }
}
