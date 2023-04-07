import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");

        Student st = new Student("12", "Nhan", 123, "hoangchinhan");
        Student st1 = new Student("13", "Nhan", 123, "dasfsadf");
        Student st2 = new Student("14", "Nhan", 123, "hoangchadsfafinhan");
        StudentsData data = new StudentsData();
        data.add(st.getId(), st);
        data.add(st1.getId(), st1);
        data.add(st2.getId(), st2);

        for (Person p : data.findByName("Nhan")) {
            System.out.println(p.getId());
        }

        String url = "jdbc:sqlite:test.db";
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            String createTableSql = "CREATE TABLE IF NOT EXISTS customers (\n"
                    + "    id INTEGER PRIMARY KEY,\n"
                    + "    name TEXT NOT NULL,\n"
                    + "    email TEXT UNIQUE\n"
                    + ");";

            statement.execute(createTableSql);

            System.out.println("Table created successfully.");

        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }
}
