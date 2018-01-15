package inshurer.view;


import com.mysql.jdbc.log.NullLogger;
import inshurer.Main;
import inshurer.model.BaseData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public class PersonController {

    private Main main;
    BaseData baseData = new BaseData();


    //заполнение коэффициента по типу документа
    ObservableList<String> typeDoc = FXCollections.observableArrayList
            ("Паспорт",
                    "Вид на жительство",
                    "Дипломатическая карточка");

    //заполнение коэффициента по серии документа
    ObservableList<String> serialDoc = FXCollections.observableArrayList
            ("MP",
                    "AB",
                    "KB",
                    "MC");

    //заполнение коэффициента кем выдан документ
    ObservableList<String> issuedDoc = FXCollections.observableArrayList
            ("Заводским РУВД г.Минска",
                    "Фрунзенским РУВД г.Минска",
                    "Октябрьским РУВД г.Минска",
                    "Ленинским РУВД г.Минска",
                    "Первомайским РУВД г.Минска",
                    "Советским РУВД г.Минска");

    //заполнение коэффициента Страна проживания
    ObservableList<String> country = FXCollections.observableArrayList
            ("Республика Беларусь",
                    "Россия",
                    "Украина");


    @FXML
    private TextField field_first_name;
    @FXML
    private TextField field_last_name;
    @FXML
    private TextField field_middle_name;
    @FXML
    private TextField field_id_number;
    @FXML
    private TextField field_number_doc;
    @FXML
    private Button btn_search_name;
    @FXML
    private Button btn_search_id;
    @FXML
    private Button btn_save_person;
    @FXML
    private Button toPolis;
    @FXML
    private Button btn_NewPerson;
    @FXML
    private ComboBox boxTypeDoc;
    @FXML
    private ComboBox boxSerialDoc;
    @FXML
    private ComboBox boxIssuedDoc;
    @FXML
    private DatePicker fieldBirthday;
    @FXML
    private DatePicker fieldIssued;
    @FXML
    private ComboBox boxCountry;
    @FXML
    private TextField field_region;
    @FXML
    private TextField field_distric;
    @FXML
    private TextField field_city;
    @FXML
    private TextField field_street;
    @FXML
    private TextField field_house;
    @FXML
    private TextField field_build;
    @FXML
    private TextField field_apartment;

    private String person;
    private PolisController polisController = new PolisController();

    @FXML
    public void clickToPolis() throws IOException, SQLException {

    }


    //по нажатию кнопки id для поиска сохраняется в переменную search_id
    @FXML
    public void clickSearchByID() throws IOException, SQLException {

        String search_id = field_id_number.getText();


        HashMap<String, String> values = baseData.findPersonByID(search_id);
        if (search_id.equals(values.get("id_number"))) {
            field_last_name.setText(values.get("last_name"));
            field_first_name.setText(values.get("first_name"));
            field_middle_name.setText(values.get("middle_name"));
            fieldBirthday.setValue(LocalDate.parse(values.get("birthday")));
            boxTypeDoc.setValue(values.get("typeDoc"));
            boxSerialDoc.setValue(values.get("seriesDoc"));
            field_number_doc.setText(values.get("numberDoc"));
            fieldIssued.setValue(LocalDate.parse(values.get("issuedDate")));
            boxIssuedDoc.setValue(values.get("issuedBy"));
            boxCountry.setValue(values.get("country"));
            field_region.setText(values.get("region"));
            field_distric.setText(values.get("distric"));
            field_city.setText(values.get("city"));
            field_street.setText(values.get("street"));
            field_house.setText(values.get("houseNumber"));
            field_build.setText(values.get("buildNumber"));
            field_apartment.setText(values.get("roomNumber"));

            person = values.get("last_name") + " " + " " + values.get("first_name") + " " + " " + values.get("middle_name")
                    + " " + " " + values.get("typeDoc") + " " + " " + values.get("seriesDoc") + " " + " " + values.get("numberDoc")
                    + " " + " " + values.get("id_number");


        } else {
            clickClear();
            field_last_name.setText("No matches");
        }

    }

    public String getPerson() {
        return person;
    }

    //по нажатию кнопки поиск по фамилии
    @FXML
    private void clickSearchByLastName() throws IOException, SQLException {

        String search_last_name = field_last_name.getText();

        HashMap<String, String> values = baseData.findPersonByLastName(search_last_name);
        if (search_last_name.equals(values.get("last_name"))) {
            field_id_number.setText(values.get("id_number"));
            field_first_name.setText(values.get("first_name"));
            field_middle_name.setText(values.get("middle_name"));
            fieldBirthday.setValue(LocalDate.parse(values.get("birthday")));
            boxTypeDoc.setValue(values.get("typeDoc"));
            boxSerialDoc.setValue(values.get("seriesDoc"));
            field_number_doc.setText(values.get("numberDoc"));
            fieldIssued.setValue(LocalDate.parse(values.get("issuedDate")));
            boxIssuedDoc.setValue(values.get("issuedBy"));
            boxCountry.setValue(values.get("country"));
            field_region.setText(values.get("region"));
            field_distric.setText(values.get("distric"));
            field_city.setText(values.get("city"));
            field_street.setText(values.get("street"));
            field_house.setText(values.get("houseNumber"));
            field_build.setText(values.get("buildNumber"));
            field_apartment.setText(values.get("roomNumber"));
        } else {
            clickClear();
            field_id_number.setText("No matches");

        }

    }

    //нажатие на кнопку SAVE Person
    @FXML
    private void clickSavePerson() throws IOException, SQLException {

        String last_name = getLastName();
        String first_name = getFirstName();
        String middle_name = getMiddleName();
        String id_number = getPersonalNumber();
        LocalDate birthday = getFieldBirthday();

        String type_doc = getTypeDoc();
        String seriya_doc = getSerialDoc();
        String number_doc = getNumberDoc();
        String issued_by = getIssuedlDoc();
        LocalDate issued = getFieldIssued();

        String country = getField_Country();
        String region = getField_region();
        String distric = getField_distric();
        String city = getField_city();
        String street = getField_street();
        String house = getField_house();
        String build = getField_build();
        String apartment = getField_apertment();

        try {
            baseData.insertPersonData(last_name, first_name, middle_name, id_number, birthday, type_doc,
                    seriya_doc, number_doc, issued_by, issued, country, region, distric, city, street, house, build, apartment);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Данные по страхователю успешно сохранены в базу данных");


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Не сохранен " +e.getMessage());

            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    private void clickClear() {
        field_last_name.setText("");
        field_first_name.setText("");
        field_middle_name.setText("");
        field_id_number.setText("");
        fieldBirthday.setValue(null);
        boxTypeDoc.setValue("");
        boxSerialDoc.setValue("");
        field_number_doc.setText("");
        fieldIssued.setValue(null);
        boxIssuedDoc.setValue("");
        boxCountry.setValue("");
        field_region.setText("");
        field_distric.setText("");
        field_city.setText("");
        field_street.setText("");
        field_house.setText("");
        field_build.setText("");
        field_apartment.setText("");

    }


    public String getField_Country() {
        String country = (String) boxCountry.getValue();
        return country;
    }

    public String getField_region() {
        String region = field_region.getText();
        return region;
    }

    public String getField_distric() {
        String distric = field_distric.getText();
        return distric;
    }

    public String getField_city() {
        String city = field_city.getText();
        return city;
    }

    public String getField_street() {
        String street = field_street.getText();
        return street;
    }

    public String getField_house() {
        String house = field_house.getText();
        return house;
    }

    public String getField_build() {
        String build = field_build.getText();
        return build;
    }

    public String getField_apertment() {
        String apartment = field_apartment.getText();
        return apartment;
    }

    //получение значия фамилии
    @FXML
    public String getFirstName() {
        String first_name = field_first_name.getText();
        return first_name;
    }

    //получение значия имени
    @FXML
    public String getLastName() {
        String last_name = field_last_name.getText();
        return last_name;
    }

    //получение значения отчества
    @FXML
    public String getMiddleName() {
        String middle_name = field_middle_name.getText();
        return middle_name;
    }

    //получение значения личного номера
    @FXML
    public String getPersonalNumber() {
        String id_number = field_id_number.getText();
        return id_number;
    }

    public LocalDate getFieldBirthday() {
        LocalDate date = fieldBirthday.getValue();
        return date;
    }

    public LocalDate getFieldIssued() {
        LocalDate date_issued = fieldIssued.getValue();
        return date_issued;
    }

    public String getNumberDoc() {
        String numberDoc = field_number_doc.getText();
        return numberDoc;
    }

    public String getTypeDoc() {
        String type_doc = String.valueOf(boxTypeDoc.getValue());
        return type_doc;
    }

    public String getSerialDoc() {
        String seriya_doc = String.valueOf(boxSerialDoc.getValue());
        return seriya_doc;
    }

    public String getIssuedlDoc() {
        String issued = String.valueOf(boxIssuedDoc.getValue());
        return issued;
    }

    //инициализация полей списков
    @FXML
    private void initialize() {

        boxTypeDoc.setValue("Паспорт");
        boxTypeDoc.setItems(typeDoc);

        boxSerialDoc.setValue("МР");
        boxSerialDoc.setItems(serialDoc);

        boxIssuedDoc.setValue("Фрунзенским РУВД г.Минска");
        boxIssuedDoc.setItems(issuedDoc);

        boxCountry.setValue("Республика Беларусь");
        boxCountry.setItems(country);

    }
}
