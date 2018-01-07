package inshurer.view;

import inshurer.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;



public class EnterController {
    private Main main = new Main();
    @FXML
    private TextField fieldLogin;
    @FXML
    private PasswordField fieldPassword;
    @FXML
    private Button btnEnter;


    @FXML
    public void clickEnter() throws IOException {
       main.showMainView();
       main.showItemCompany();
    }


}
