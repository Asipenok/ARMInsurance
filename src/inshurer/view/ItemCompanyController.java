package inshurer.view;

import inshurer.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemCompanyController {
    private Main main;

    @FXML
    Button btnERGO;
    @FXML
    Button btnTASK;



    @FXML
    private void goERGO() throws IOException{
        main.showRateERGO();
        //закрываем окно
//        Stage stage = (Stage) btnERGO.getScene().getWindow();
//        stage.close();

    }

    @FXML
    private void goTASK() throws IOException{
        main.showRateTASK();
    }
}
