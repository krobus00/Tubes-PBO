/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;

public class Database {

    protected Connection conn = null;
    protected Statement stmt = null;
    protected ResultSet rs = null;

    public Database() {
    }

    public void connect() throws SQLException {
        String url = "jdbc:mysql://localhost/bengkel";
        String user = "root";
        String pass = "";
        conn = DriverManager.getConnection(url, user, pass);
        stmt = conn.createStatement();
    }

    public void disconnect() throws SQLException {
        conn.close();
        stmt.close();
    }
}
