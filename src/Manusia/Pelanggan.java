/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manusia;

import Kendaraan.Kendaraan;
import database.Database;
import java.sql.SQLException;
import java.util.ArrayList;


public class Pelanggan extends Database {

    private Manusia u;
    private ArrayList<Kendaraan> listKendaraan = new ArrayList<>();

    public Pelanggan(Manusia u) {
        this.u = u;
    }

    public void TambahKendaraan(Kendaraan k) throws SQLException {
        connect();
        String query = "INSERT INTO kendaraan VALUES(";
        query += "'" + "0" + "',";
        query += "'" + k.getNomor_kendaraan() + "',";
        query += "'" + k.getNama() + "',";
        query += "'" + k.getJenis() + "',";
        query += "'" + k.getKeterangan() + "',";
        query += "'" + u.getId() + "',";
        query += null + ",";
        query += null + ",";
        query += "'Masuk')";
        stmt.executeUpdate(query);
        disconnect();
    }

    public void loadKendaraan() throws SQLException {
        connect();
        String query = "SELECT * FROM kendaraan WHERE idpemilik=";
        query += u.getId();
        rs = stmt.executeQuery(query);
        listKendaraan = new ArrayList<>();
        while (rs.next()) {
            listKendaraan.add(new Kendaraan(
                    rs.getInt("id"),
                    rs.getString("nomor"),
                    rs.getString("nama"),
                    rs.getString("jenis"),
                    rs.getString("keterangan"),
                    rs.getInt("idpemilik"),
                    rs.getString("masuk"),
                    rs.getString("keluar"),
                    rs.getString("status")
            ));
        }
        disconnect();
    }

    public ArrayList<Kendaraan> daftarKendaraan() {
        return listKendaraan;
    }

}
