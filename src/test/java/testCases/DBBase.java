public class DBBase {
    private Connection connection;

    public DBBase() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "user", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
