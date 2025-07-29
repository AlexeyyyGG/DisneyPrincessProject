package repository;

import model.EyeColor;
import model.HairColor;
import model.Princess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrincessRepositoryDB implements PrincessRepository {
    private final Connection connection;
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_AGE = "age";
    private static final String COL_HAIRCOLOR = "hairColor";
    private static final String COL_EYECOLOR = "eyeColor";
    private static final String SQL_INSERT =
            "INSERT INTO PRINCESSES(id, name, age, hairColor, eyeColor) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE PRINCESSES SET name=?, age=?, hairColor=?, eyeColor=? WHERE id=?";
    private static final String SQL_GET =
            "SELECT * FROM PRINCESSES WHERE id=?";
    private static final String SQL_LIST =
            "SELECT * FROM PRINCESSES";
    private static final String SQL_DELETE =
            "DELETE FROM PRINCESSES WHERE id=?";
    private static final String SQL_EXIST =
            "SELECT 1 FROM PRINCESSES WHERE id = ? LIMIT 1";
    private static final String FAILED_TO_ADD_MESSAGE = "Failed to add princess";
    private static final String FAILED_TO_UPDATE_MESSAGE = "Failed to update princess";
    private static final String FAILED_TO_GET_MESSAGE = "Failed to get princess";
    private static final String FAILED_TO_LIST = "Failed to list princesses";
    private static final String FAILED_TO_DELETE_MESSAGE = "Failed to delete princess";
    private static final String FAILED_TO_CHECK_MESSAGE = "Failed to check if princess exists";

    public PrincessRepositoryDB(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addAll(List<Princess> princesses) {
        for (Princess princess : princesses) {
            add(princess);
        }
    }

    @Override
    public void add(Princess princess) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setInt(1, princess.getId());
            statement.setString(2, princess.getName());
            statement.setInt(3, princess.getAge());
            statement.setString(4, princess.getHairColor().toString());
            statement.setString(5, princess.getEyeColor().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(FAILED_TO_ADD_MESSAGE, e);
        }
    }

    @Override
    public void update(Princess princess) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, princess.getName());
            statement.setInt(2, princess.getAge());
            statement.setString(3, princess.getHairColor().toString());
            statement.setString(4, princess.getEyeColor().toString());
            statement.setInt(5, princess.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(FAILED_TO_UPDATE_MESSAGE, e);
        }
    }

    @Override
    public Princess get(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSetToPrincess(resultSet);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(FAILED_TO_GET_MESSAGE, e);
        }
    }

    @Override
    public List<Princess> list() {
        List<Princess> princesses = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_LIST);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                princesses.add(resultSetToPrincess(resultSet));
            }
        return princesses;
        } catch (SQLException e) {
            throw new RuntimeException(FAILED_TO_LIST, e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(FAILED_TO_DELETE_MESSAGE, e);
        }
    }

    @Override
    public boolean exist(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_EXIST)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(FAILED_TO_CHECK_MESSAGE, e);
        }
    }

    private Princess resultSetToPrincess(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(COL_ID);
        String name = resultSet.getString(COL_NAME);
        int age = resultSet.getInt(COL_AGE);
        String hairColor = resultSet.getString(COL_HAIRCOLOR);
        String eyeColor = resultSet.getString(COL_EYECOLOR);
        return new Princess(
                id,
                name,
                age,
                HairColor.fromString(hairColor),
                EyeColor.fromString(eyeColor)
        );
    }
}
