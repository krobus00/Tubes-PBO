/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Manusia.Auth;
import Manusia.Manusia;
import Manusia.Montir;
import Manusia.Pelanggan;
import java.sql.SQLException;


public class ModelManusia {

    private Manusia u;
    private Auth authentikasi;
    private Pelanggan p;
    private Montir m;

    public Boolean checkUsername(String username) throws SQLException {
        return authentikasi.checkUsername(username);
    }

    public void auth(String username, String password) throws SQLException {
        this.u = authentikasi.Login(new Manusia(username, password));
    }

    public void auth(String nama, String username, String password, String alamat) throws SQLException {
        authentikasi.Registrasi(new Manusia(nama, username, password, alamat));
    }

    public void setPelanggan(Manusia u) {
        this.p = new Pelanggan(u);
    }

    public void setMontir(Manusia u) throws SQLException {
        this.m = new Montir(u);
    }

    public Manusia getUser() {
        return u;
    }

    public Pelanggan getPelanggan() {
        return p;
    }

    public Montir getMontir() {
        return m;
    }

}
