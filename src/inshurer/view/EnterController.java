package inshurer.view;

import inshurer.Main;
import inshurer.model.BaseData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;


public class EnterController {
    private Main main = new Main();
    @FXML
    private TextField fieldLogin;
    @FXML
    private PasswordField fieldPassword;
    @FXML
    private Button btnEnter;
    private BaseData baseData = new BaseData();

    @FXML
    public void clickEnter() throws IOException, SQLException {


        HashMap<String, String> values = baseData.authorization(fieldLogin.getText());


        if (fieldPassword.getText().equals(values.get("password"))) {
            main.showMainView();
            main.showItemCompany();

        } else {
            System.out.println("Wrong login or password");
        }
    }

}



