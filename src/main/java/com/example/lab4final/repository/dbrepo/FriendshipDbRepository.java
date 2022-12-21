package com.example.lab4final.repository.dbrepo;

import com.example.lab4final.domain.Friendship;
import com.example.lab4final.domain.User;
import com.example.lab4final.domain.Validator.Validator;
import com.example.lab4final.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FriendshipDbRepository implements Repository<Friendship> {
    private String url = "jdbc:postgresql://localhost:5432/socialnetwork";
    private String username = "postgres";
    private String password = "admin";
    private Validator<Friendship> validator;

    private  static FriendshipDbRepository instance = null;

    private FriendshipDbRepository(){}

    public static FriendshipDbRepository getInstance() {
        if (instance == null) {
            instance = new FriendshipDbRepository();
        }
        return instance;
    }

    @Override
    public Optional<User> findOne(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Optional<Friendship> findOne(Friendship friendship) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findOne(int i) {
        return Optional.empty();
    }

    @Override
    public List<Friendship> findAll() {
        List<Friendship> friendships = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from friendships");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = (int) resultSet.getLong("id");
                int id_User1 = (int) resultSet.getLong("id_User1");
                int id_User2 = (int) resultSet.getLong("id_User2");
                Timestamp dateTime = resultSet.getTimestamp("date");
                String status = resultSet.getString("status");
                Friendship friendship = new Friendship(id,id_User1,id_User2,dateTime,status);
                friendship.setId(id);
                friendships.add(friendship);
            }
            return friendships;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendships;
    }

    @Override
    public void save(Friendship entity) {
        String sql = "insert into friendships(id,id_User1,id_User2,date,status) values (?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, entity.getId());
            ps.setInt(2, entity.getIdUser1());
            ps.setInt(3, entity.getIdUser2());
            ps.setTimestamp(4,entity.getDateTime());
            ps.setString(5, entity.getStatus());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Friendship friendship) {

    }

    @Override
    public void update(Friendship entity, Friendship newEntity) {
        String sql = "UPDATE friendships SET id_user1 = (?), id_user2 = (?), status = (?) WHERE id :: int=  (?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, newEntity.getIdUser1());
            ps.setInt(2, newEntity.getIdUser2());
            ps.setString(3, newEntity.getStatus());
            ps.setInt(4,entity.getId());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
