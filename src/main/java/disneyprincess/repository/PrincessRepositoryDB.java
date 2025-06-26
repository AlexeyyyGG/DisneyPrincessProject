package disneyprincess.repository;

import disneyprincess.model.EyeColor;
import disneyprincess.model.HairColor;
import disneyprincess.model.Princess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrincessRepositoryDB implements Repository {
    String url = "jdbc:mysql://localhost:3306/princesses_database";
    String user = "user";
    String password = "user_password";
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void add(Princess princess) {
        String sql =
                "INSERT INTO PRINCESSES(id, name, age, hairColor, eyeColor) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, princess.getId());
            statement.setString(2, princess.getName());
            statement.setInt(3, princess.getAge());
            statement.setString(4, princess.getHairColor().toString());
            statement.setString(5, princess.getEyeColor().toString());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Failed to add princess");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Princess princess) {
        String sql = "UPDATE PRINCESSES SET name=?, age=?, hairColor=?, eyeColor=? WHERE id=?";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, princess.getName());
            statement.setInt(2, princess.getAge());
            statement.setString(3, princess.getHairColor().toString());
            statement.setString(4, princess.getEyeColor().toString());
            statement.setInt(5, princess.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("No princess found with id: " + princess.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Princess get(int id) {
        String sql = "SELECT * FROM PRINCESSES WHERE id = ?";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String hairColor = resultSet.getString("hairColor");
                    String eyeColor = resultSet.getString("eyeColor");
                    return new Princess(
                            id,
                            name,
                            age,
                            HairColor.fromString(hairColor),
                            EyeColor.fromString(eyeColor)
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Princess> list() {
        List<Princess> princesses = new ArrayList<>();
        String sql = "SELECT * FROM PRINCESSES";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String hairColor = resultSet.getString("hairColor");
                String eyeColor = resultSet.getString("eyeColor");
                Princess princess = new Princess(
                        id,
                        name,
                        age,
                        HairColor.fromString(hairColor),
                        EyeColor.fromString(eyeColor)
                );
                princesses.add(princess);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return princesses;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM PRINCESSES WHERE id = ?";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("No princess found with id: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean exist(int id) {
        String sql = "SELECT 1 FROM PRINCESSES WHERE id = ? LIMIT 1";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
