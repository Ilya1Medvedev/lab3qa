package com.example.surinklietuva.Controllers;

import com.example.surinklietuva.AllertBox;
import com.example.surinklietuva.BigDataManager;
import com.example.surinklietuva.DataStructures.User;
import com.example.surinklietuva.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LoginView {
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button signUpButton;
    @FXML
    public Button loginButton;

    private List<User> listOfUsers;
    private BigDataManager bigDataManager;

    public void setData(List<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    public void goToMainView(ActionEvent actionEvent) throws IOException {
        List<User> currentUser;
        currentUser = listOfUsers.stream()
                .filter((u -> u.getUsername()
                        .equals(loginField.getText()) && (u.getPassword().equals(passwordField.getText())))).collect(Collectors.toList());
        if (currentUser.size() == 0) {
            AllertBox.display("Klaida", "Duomenų bazėje nėra tokio vartotojo");
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
            Parent root = fxmlLoader.load();
            MainView mainView = fxmlLoader.getController();
            mainView.setData(listOfUsers, currentUser.get(0));

            Scene scene = new Scene(root);
            Stage stage = (Stage) loginField.getScene().getWindow();
            stage.setTitle("Pagrindinis");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void goToSignUpView(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sign-up-view.fxml"));
        Parent root = fxmlLoader.load();
        SignUpView signUpView = fxmlLoader.getController();
        signUpView.setData(listOfUsers);

        Scene scene = new Scene(root);
        Stage stage = (Stage) loginField.getScene().getWindow();
        stage.setTitle("Registracija");
        stage.setScene(scene);
        stage.show();
    }
}