package com.example.lab4final.repository.dbrepo;

import com.example.lab4final.domain.User;
import com.example.lab4final.domain.Validator.Validator;
import com.example.lab4final.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDbRepository implements Repository<User> {
    private String url = "jdbc:postgresql://localhost:5432/socialnetwork";
    private String username = "postgres";
    private String password = "admin";
    private Validator<User> validator;

    private static UserDbRepository instance = null;

    private UserDbRepository(){}

        public static UserDbRepository getInstance() {
            if (instance == null) {
                instance = new UserDbRepository();
            }
            return instance;

        }

    @Override
    public Optional<User> findOne(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findOne(User user) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findOne(int i) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from users");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = (int) resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String parola = resultSet.getString("parola");
                User utilizator = new User(id,firstName, lastName,email,parola);
                utilizator.setId(id);
                users.add(utilizator);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<User> save(User entity) {
        String sql = "insert into users (id,first_name, last_name, email, parola) values (?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getPrenume());
            ps.setString(3, entity.getNume());
            ps.setString(4, entity.getEmail());
            ps.setString(5, entity.getParola());

            ps.executeUpdate();
        } catch (SQLException e) {
            return Optional.ofNullable(entity);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> delete(User e) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User entity) {
        return Optional.empty();
    }
}
