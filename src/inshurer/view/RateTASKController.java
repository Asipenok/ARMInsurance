package inshurer.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class RateTASKController {
    //заполнение коэффициента по типу ТС
    ObservableList<String> vehicle = FXCollections.observableArrayList
            ("Легковой автомобиль", "Автобусы, грузовые авто", "Тракторы, прицепы");
    //заполнение коэфф по варианту страхования
    ObservableList<String> option = FXCollections.observableArrayList
            ("Вариант 1 - без учета износа", "Вариант 2 - с учетом износа");
    //заполнение коэфф по территории действия договора
    ObservableList<String> teritory = FXCollections.observableArrayList
            ("Все страны мира", "Республика Беларусь");
    //заполнение коэфф по месту жительства
    ObservableList<String> teritoryLive = FXCollections.observableArrayList
            ("Минск и Минская обл", "кроме Минск и Минская обл");
    //заполнение коэфф по количеству застрахованных ТС
    ObservableList<String> quantity = FXCollections.observableArrayList
            ("1 единица", "2 единицы", "3-5 единиц");
    //заполнение коэфф по условию эксплуатации
    ObservableList<String> rent_taxi = FXCollections.observableArrayList
            (" ", "договор аредны (прокат)", "такси, обучение вождению");
    //заполнение коэфф по безусловная франшиза
    ObservableList<String> condition_franchise = FXCollections.observableArrayList
            ("Без франшизы", "до 1% включительно", "свыше 5% до 10% вкл", "свыше 10% до 15% вкл");
    //заполнение коэфф по условная франшиза
    ObservableList<String> no_condition_franchise = FXCollections.observableArrayList
            (" ", "до 1% включительно", "свыше 5% до 10% вкл", "свыше 10% до 15% вкл");
    //заполнение коэфф по доп полисам
    ObservableList<String> additional_types = FXCollections.observableArrayList
            (" ", "1 и более доп полисов с суммой не менее 20$ в эквиваленте",
                    "1 и более доп полисов с суммой не менее 50$ в эквиваленте", "обязательное страхование");
    //заполнение коэфф по скидкам
    ObservableList<String> bonus = FXCollections.observableArrayList
            (" ", "1 год без убытков", "2 года без убытков", "3 года без убытков", "4 года без убытков", "5 лет и более без убытков");
    //заполнение коэфф по убыткам
    ObservableList<String> manus = FXCollections.observableArrayList
            (" ", "убытки не превышают 50% от страхового взноса", "убытки не превышают 120% от страхового взноса");
    //заполнение коэфф по убыткам
    ObservableList<String> ads = FXCollections.observableArrayList
            (" ", "рекламная акция");
    //заполнение коэфф салон
    ObservableList<String> salon = FXCollections.observableArrayList
            (" ", "салон, кредит, лизинг");
    //заполнение коэфф стоимость авто
    ObservableList<String> price_car = FXCollections.observableArrayList
            (" ", "от 20 000$ до 40 000$", "от 40 000$");
    //заполнение коэфф порядок оплаты
    ObservableList<String> payment = FXCollections.observableArrayList
            ("ежеквартально", "в два срока", "единовременно");
    //заполнение валюты платежа
    ObservableList<String> currency = FXCollections.observableArrayList
            ("USD", "EUR", "RUB", "BYN");




    @FXML
    private ComboBox boxVehicle;
    @FXML
    private ComboBox boxOption;
    @FXML
    private ComboBox boxTeritoty;
    @FXML
    private ComboBox boxTeritotyLive;
    @FXML
    private ComboBox boxQuantity;
    @FXML
    private ComboBox boxRent_Taxi;
    @FXML
    private ComboBox boxCondition_franchise;
    @FXML
    private ComboBox boxNo_condition_franchise;
    @FXML
    private ComboBox boxAdditional_types;
    @FXML
    private ComboBox boxBonus;
    @FXML
    private ComboBox boxManus;
    @FXML
    private ComboBox boxAds;
    @FXML
    private ComboBox boxSalon;
    @FXML
    private ComboBox boxPrice_Car;
    @FXML
    private ComboBox boxPayment;
    @FXML
    private ComboBox boxCurrency;


    @FXML
    private void initialize() {

        boxVehicle.setValue("Легковой автомобиль");
        boxVehicle.setItems(vehicle);

        boxOption.setValue("Вариант 1 - без учета износа");
        boxOption.setItems(option);

        boxTeritoty.setValue("Все страны мира");
        boxTeritoty.setItems(teritory);

        boxTeritotyLive.setValue("Минск и Минская обл");
        boxTeritotyLive.setItems(teritoryLive);

        boxQuantity.setValue("1 единица");
        boxQuantity.setItems(quantity);

        boxRent_Taxi.setValue(" ");
        boxRent_Taxi.setItems(rent_taxi);

        boxCondition_franchise.setValue("Без франшизы");
        boxCondition_franchise.setItems(condition_franchise);

        boxNo_condition_franchise.setValue(" ");
        boxNo_condition_franchise.setItems(no_condition_franchise);

        boxAdditional_types.setValue(" ");
        boxAdditional_types.setItems(additional_types);

        boxBonus.setValue(" ");
        boxBonus.setItems(bonus);

        boxManus.setValue(" ");
        boxManus.setItems(manus);

        boxAds.setValue(" ");
        boxAds.setItems(ads);

        boxSalon.setValue(" ");
        boxSalon.setItems(salon);

        boxPrice_Car.setValue(" ");
        boxPrice_Car.setItems(price_car);

        boxPayment.setValue(" ");
        boxPayment.setItems(payment);

        boxCurrency.setValue("USD");
        boxCurrency.setItems(currency);


    }


}
