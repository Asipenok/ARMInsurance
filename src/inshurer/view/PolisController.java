package inshurer.view;

import inshurer.model.BaseData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public class PolisController {
    @FXML
    TextArea initPerson;
    @FXML
    TextArea initOwner;
    @FXML
    TextArea initCar;
    @FXML
    Button searchPerson;
    @FXML
    Button searchOwner;
    @FXML
    Button searchCar;
    PersonController personController = new PersonController();


    public void ckickSearchPerson() throws SQLException {
       BaseData baseData = new BaseData();
        String vin = initPerson.getText();
        String person;

        HashMap<String, String> values = baseData.findPersonByID(vin);

            person = values.get("last_name") + " " + " " + values.get("first_name") + " " + " " + values.get("middle_name")
                    + " " + " " + values.get("typeDoc") + " " + " " + values.get("seriesDoc") + " " + " " + values.get("numberDoc")
                    + " " + " " + values.get("id_number");

        initPerson.setText(person);
    }


}
