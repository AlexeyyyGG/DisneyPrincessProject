package disneyprincess.repository;

import disneyprincess.model.EyeColor;
import disneyprincess.model.HairColor;
import disneyprincess.model.Princess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrincessRepositoryDB implements PrincessRepository {
    private final Connection connection;
    private static final String FAILED_TO_ADD = "Failed to add princess";
    private static final String PRINCESS_NOT_FOUND = "No princess found with id:  %d";
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

    public PrincessRepositoryDB(Connection connection) {
        this.connection = connection;
    }

    private Princess ResultSetToPrincess(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
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
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException(FAILED_TO_ADD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException(String.format(PRINCESS_NOT_FOUND, princess.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Princess get(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return ResultSetToPrincess(resultSet);
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
        try (PreparedStatement statement = connection.prepareStatement(SQL_LIST);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                princesses.add(ResultSetToPrincess(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return princesses;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException(String.format(PRINCESS_NOT_FOUND, id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
            return false;
        }
    }
}
