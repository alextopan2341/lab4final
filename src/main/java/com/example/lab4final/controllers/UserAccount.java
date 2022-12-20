package com.example.lab4final.controllers;

import com.example.lab4final.domain.Friendship;
import com.example.lab4final.domain.User;
import com.example.lab4final.service.ServiceFriendship;
import com.example.lab4final.service.ServiceUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
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
    private ObservableList<User> model = FXCollections.observableArrayList();

    public void setUserlogged(User userlogged1) {
        this.userlogged = userlogged1;
    }
    @FXML
    TableView<User> tableView =  new TableView<>();
    @FXML
    TableColumn<User, String> Prenume = new TableColumn<>();
    @FXML
    TableColumn<User, String> Nume = new TableColumn<>();
    @FXML
    TableColumn<User, String> Email = new TableColumn<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Prenume.setCellValueFactory(new PropertyValueFactory<User, String>("Prenume"));
        Nume.setCellValueFactory(new PropertyValueFactory<User, String>("Nume"));
        Email.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
        tableView.setItems(model);
    }

    private void initModel(){
        ArrayList<User> list = setFriends();
        List<User> user = StreamSupport.stream(list.spliterator(),false)
                .collect(Collectors.toList());
        model.setAll(user);
     }

     @FXML
     public void showMyFriends() throws IOException {
         initModel();
     }

     @FXML
     public void showMyUnfriends() throws IOException{
         ArrayList<User> list = unfriends();
         List<User> user = StreamSupport.stream(list.spliterator(),false)
                 .collect(Collectors.toList());
         model.setAll(user);
     }

     @FXML
     public void showRequests() throws IOException{
        ArrayList<User> list = requests();
         List<User> user = StreamSupport.stream(list.spliterator(),false)
                 .collect(Collectors.toList());
         model.setAll(user);
     }

    public ArrayList<User> unfriends(){
        boolean ok;
        ArrayList<User> myUnfriends = new ArrayList<>();
        for(User user: serviceUser.getAll()){
            ok = false;
            for(Friendship friendship: serviceFriendship.getAll()){
                if ((userlogged.getId() == friendship.getIdUser1() && user.getId() == friendship.getIdUser2()) || (user.getId() == friendship.getIdUser1() && userlogged.getId() == friendship.getIdUser2())){
                    ok = true;
                    break;
                }
            }
            if(!ok && !Objects.equals(userlogged.getId(), user.getId())) {
                myUnfriends.add(user);
            }
        }
        return myUnfriends;
    }

    public ArrayList<User> requests(){
        ArrayList<User> myRequests = new ArrayList<>();
        for(Friendship friendship: serviceFriendship.getAll()){
            if(Objects.equals(friendship.getStatus(), "pending")){
                if (friendship.getIdUser1() == userlogged.getId()) {
                    myRequests.add(serviceUser.getById(friendship.getIdUser2()));
                }
                if(friendship.getIdUser2() == userlogged.getId()){
                    myRequests.add(serviceUser.getById(friendship.getIdUser1()));
                }
            }
        }
        return myRequests;
    }

    public ArrayList<User> setFriends(){
        ArrayList<User> friends = new ArrayList<>();
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
