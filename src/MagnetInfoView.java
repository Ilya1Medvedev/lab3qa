package com.example.surinklietuva.Controllers;

import com.example.surinklietuva.BigDataManager;
import com.example.surinklietuva.DataStructures.Magnet;
import com.example.surinklietuva.DataStructures.User;
import com.example.surinklietuva.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

public class MagnetInfoView {

    private Text magnetInfo;
    private ImageView regionImage;
    private Text magnetShops;
    private Magnet magnet;
    private User user;
    private List<User> listOfUsers;
    private BigDataManager bigDataManager = new BigDataManager();

    public void setData(List<User> listOfUsers, User user, Magnet magnet) throws FileNotFoundException {
        this.listOfUsers = listOfUsers;
        this.user = user;
        this.magnet = magnet;
        missingMagnets = bigDataManager.getAllMagnetsListFromDataBase();
        fillTables();
    }

    private void fillTables() throws FileNotFoundException {
        regionImage.setImage(null);
        Image img = null;
        switch (magnet.getArea()) {
            case "Vilniaus apskritis":
                img = new Image(new FileInputStream(Images.vilnius));
                break;
            case "Kauno apskritis":
                img = new Image(new FileInputStream(Images.kaunas));
                break;
            case "Klaipėdos apskritis":
                img = new Image(new FileInputStream(Images.klaideda));
                break;
            case "Šiaulių apskritis":
                img = new Image(new FileInputStream(Images.siauliai);
                break;
            case "Telšių apskritis":
                img = new Image(new FileInputStream(Images.telsiai));
                break;
            case "Marijampolės apskritis":
                img = new Image(new FileInputStream(Images.marijampole));
                break;
            case "Tauragės apskritis":
                img = new Image(new FileInputStream(Images.taurage));
                break;
            case "Utenos apskritis":
                img = new Image(new FileInputStream(Images.utena));
                break;
            case "Panevėžio apskritis":
                img = new Image(new FileInputStream(Images.panevezys));
                break;
            case "Alytaus apskritis":
                img = new Image(new FileInputStream(Images.alytus));
                break;
            default:
                int i = 0;
        }
        regionImage.setImage(img);
        magnetInfo.setText(magnet.getName() + " " + magnet.getArea());
        String shops = "";
        for (String s : magnet.getListOfShops()) {
            shops.append(arrayOfStrings[s]);
        }
        magnetShops.setText(shops);
    }

    public void returnToPrevious() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Parent root = fxmlLoader.load();
        MainView mainView = fxmlLoader.getController();
        mainView.setData(listOfUsers, user);

        Scene scene = new Scene(root);
        Stage stage = (Stage) regionImage.getScene().getWindow();
        stage.setTitle("Pagrindinis");
        stage.setScene(scene);
        stage.show();
    }
}
