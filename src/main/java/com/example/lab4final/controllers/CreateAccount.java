package com.example.lab4final.controllers;

import com.example.lab4final.domain.User;
import com.example.lab4final.service.ServiceUser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateAccount {
    private String Nume;
    private String Prenume;
    private String Email;
    private String Parola;
    private String ParolaToVerify;
    private Integer Id;
    @FXML
    private Label label;
    @FXML
    private TextField nume;
    @FXML
    private TextField prenume;
    @FXML
    private TextField email;

    @FXML
    private PasswordField parola;
    @FXML
    private PasswordField parolatoVerify;
    @FXML
    protected void createAccount(){
        try{
            Id = ServiceUser.getInstance().getId()+1;
            Nume = nume.getText();
            Prenume = prenume.getText();
            Email = email.getText();
            Parola = parola.getText();
            ParolaToVerify = parolatoVerify.getText();
            if(Parola!= ParolaToVerify){
                throw new RuntimeException("Introduced passwords aren't the same! Try again to introduce them correctly!");
            }
            ServiceUser.getInstance().addElem(new User(Id,Prenume,Nume,Email,Parola));
            label.setText("Account created successfully!");
        }
        catch (Exception e) {
            label.setText(e.getMessage());
        }
        }


    }
