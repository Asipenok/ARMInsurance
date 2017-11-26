package inshurer;

import inshurer.model.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;
    private static BorderPane mainLoyout;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ARMInsurer");
        showMainView();
        showItemCompany();

    }

    private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/arminsurer.fxml"));
        mainLoyout = loader.load();
        Scene scene = new Scene(mainLoyout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showItemCompany() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/ItemCompany.fxml"));
        GridPane itemCompany = loader.load();
        mainLoyout.setCenter(itemCompany);

    }

    public static void showRateERGO() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/RateERGO.fxml"));
        AnchorPane itemCompany = loader.load();

        Stage dialogRateERGO = new Stage();
        dialogRateERGO.setTitle("Rate ERGO");
        dialogRateERGO.initModality(Modality.NONE);
        dialogRateERGO.initOwner(primaryStage);
        Scene scene = new Scene(itemCompany);
        dialogRateERGO.setScene(scene);
        dialogRateERGO.showAndWait();

    }

    public static void showRateTASK() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/RateTASK.fxml"));
        AnchorPane itemCompany = loader.load();

        Stage dialogRateTASK = new Stage();
        dialogRateTASK.setTitle("Rate TASK");
        dialogRateTASK.initModality(Modality.NONE);
        dialogRateTASK.initOwner(primaryStage);
        Scene scene = new Scene(itemCompany);
        dialogRateTASK.setScene(scene);
        dialogRateTASK.showAndWait();

    }
    public static void showPerson() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Person.fxml"));
        AnchorPane itemCompany = loader.load();

        Stage dialogRateTASK = new Stage();
        dialogRateTASK.setTitle("Person");
        dialogRateTASK.initModality(Modality.APPLICATION_MODAL);
        dialogRateTASK.initOwner(primaryStage);
        Scene scene = new Scene(itemCompany);
        dialogRateTASK.setScene(scene);
        dialogRateTASK.showAndWait();

    }
    public static void showPolis() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Polis.fxml"));
        AnchorPane polis = loader.load();

        Stage dialogPolis = new Stage();
        dialogPolis.setTitle("Polis");
        dialogPolis.initModality(Modality.APPLICATION_MODAL);
        dialogPolis.initOwner(primaryStage);
        Scene scene = new Scene(polis);
        dialogPolis.setScene(scene);
        dialogPolis.showAndWait();

    }
    public static void showCar() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Car.fxml"));
        AnchorPane itemCompany = loader.load();

        Stage dialogRateTASK = new Stage();
        dialogRateTASK.setTitle("Car");
        dialogRateTASK.initModality(Modality.APPLICATION_MODAL);
        dialogRateTASK.initOwner(primaryStage);
        Scene scene = new Scene(itemCompany);
        dialogRateTASK.setScene(scene);
        dialogRateTASK.showAndWait();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
