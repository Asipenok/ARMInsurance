package inshurer.view;

import inshurer.Main;
import javafx.fxml.FXML;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class MainViewController {
    private Main main;

    @FXML
    private ImageView imgMain;

    @FXML
    private void goHome() throws IOException {
        main.showItemCompany();
    }
    @FXML
    private void setImag(){

    }


}
