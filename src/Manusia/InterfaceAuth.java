/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manusia;

import java.sql.SQLException;


public interface InterfaceAuth {

    Boolean checkUsername(String username) throws SQLException;

    void Registrasi(Manusia user) throws SQLException;

    Manusia Login(Manusia user) throws SQLException;
}
