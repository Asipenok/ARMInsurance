package inshurer.view;


import inshurer.Main;
import inshurer.model.BaseData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public class PersonController {

    private Main main;

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
    private Button nextToCar;
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
    private void clickNextToCar() throws IOException {
        main.showCar();

    }

    //по нажатию кнопки фамилия для поиска сохраняется в переменную search_name
    @FXML
    private String clickSearchByName() throws IOException {
        String search_name = field_first_name.getText();
        return search_name;
    }

    //по нажатию кнопки id для поиска сохраняется в переменную search_id
    @FXML
    private void clickSearchByID() throws IOException, SQLException {
        BaseData baseData = new BaseData();
        String search_id = field_id_number.getText();

        HashMap<String, String> values = baseData.findPersonByID(search_id);
        String first_name = values.get("first_name");
        String last_name = values.get("last_name");
        String middle_name = values.get("middle_name");
        String personal_number = values.get("id_number");

        field_first_name.setText(first_name);
        field_last_name.setText(last_name);
        field_middle_name.setText(middle_name);
        field_id_number.setText(personal_number);
        







        try {
            baseData.findPersonByID(search_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //нажатие на кнопку SAVE Person
    @FXML
    private void clickSavePerson() throws IOException, SQLException {
        BaseData baseData = new BaseData();
        String first_name = getFirstName();
        String last_name = getLastName();
        String middle_name = getMiddleName();
        String id_number = getPersonalNumber();
        LocalDate birthday = getFieldBirthday();
        String type_doc = getTypeDoc();
        String seriya_doc = getSerialDoc();
        String number_doc = getNumberDoc();
        String issued_by = getIssuedlDoc();
        LocalDate issued = getFieldBirthday();

        try {
            baseData.insertPerson(first_name, last_name, middle_name, id_number, birthday);
            baseData.insertDocument(type_doc, seriya_doc, number_doc, issued_by, issued);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    }


}
