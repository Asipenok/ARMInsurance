package inshurer.view;

import inshurer.Main;
import javafx.fxml.FXML;

import java.io.IOException;

public class ItemCompanyController {
    private Main main;

    @FXML
    private void goERGO() throws IOException{
        main.showRateERGO();
    }

    @FXML
    private void goTASK() throws IOException{
        main.showRateTASK();
    }
}
