package com.example.lab4final.controllers;

import com.example.lab4final.domain.Friendship;
import com.example.lab4final.domain.User;
import com.example.lab4final.service.ServiceFriendship;
import com.example.lab4final.service.ServiceUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UserAccount  implements Initializable {
    ServiceUser serviceUser = ServiceUser.getInstance();
    ServiceFriendship serviceFriendship = ServiceFriendship.getInstance();
    private User userlogged;
    ObservableList<User> model = FXCollections.observableArrayList();

    public void setUserlogged(User userlogged1) {
        this.userlogged = userlogged1;
    }

    @FXML
    TableView<User> tableView;
    @FXML
    TableColumn<User, String> Prenume;
    @FXML
    TableColumn<User, String> Nume;
    @FXML
    TableColumn<User, String> Email;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Prenume.setCellValueFactory(new PropertyValueFactory<User, String>("Prenume"));
        Nume.setCellValueFactory(new PropertyValueFactory<User, String>("Numele"));
        Email.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
        tableView.setItems(model);
    }

    private void initModel(Iterable<User> list){
        List<User> user = StreamSupport.stream(list.spliterator(),false)
                .collect(Collectors.toList());
        model.setAll(user);
     }

     @FXML
     public void showMyFriends(){
            initModel(setFriends());
     }

    public List<User> setFriends(){
        List<User> friends = new ArrayList<>();
        for (Friendship friendship : serviceFriendship.getAll()) {
            if (friendship.getIdUser1() == userlogged.getId() && Objects.equals(friendship.getStatus(), "accepted")) {
                friends.add(serviceUser.getById(friendship.getIdUser2()));
            }
            if(friendship.getIdUser2() == userlogged.getId() && Objects.equals(friendship.getStatus(), "accepted")){
                friends.add(serviceUser.getById(friendship.getIdUser1()));
            }
        }
        return friends;
    }
}
