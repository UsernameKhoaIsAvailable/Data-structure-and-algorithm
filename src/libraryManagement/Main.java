package libraryManagement;

import libraryManagement.storage.Database;
import libraryManagement.views.Views;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database.init();
        Views.views();
    }
}
