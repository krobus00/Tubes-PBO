/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manusia;

import database.Database;
import java.sql.SQLException;


public class Auth extends Database implements InterfaceAuth{

    @Override
    public Boolean checkUsername(String username) throws SQLException {
        boolean cek = false;
        connect();
        String query = "SELECT * FROM user WHERE username='" + username + "'";
        rs = stmt.executeQuery(query);
        if (rs.next()) {
            cek = true;
        }
        disconnect();
        return cek;
    }

    @Override
    public void Registrasi(Manusia user) throws SQLException {
        connect();
        String query = "INSERT INTO user VALUES(";
        query += "'" + "0" + "',";
        query += "'" + user.getNama() + "',";
        query += "'" + user.getUsername() + "',";
        query += "'" + user.getPassword() + "',";
        query += "'" + user.getAlamat() + "',";
        query += "'pelanggan'";
        query += ")";
        stmt.executeUpdate(query);
        disconnect();
    }

    @Override
    public Manusia Login(Manusia user) throws SQLException {
        connect();
        String query = "SELECT * FROM user where username='" + user.getUsername() + "' AND password='" + user.getPassword() + "'";
        rs = stmt.executeQuery(query);
        if (rs.next()) {
            user = new Manusia(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("alamat"),
                    rs.getString("level"));
        }
        disconnect();
        return user;
    }
}
