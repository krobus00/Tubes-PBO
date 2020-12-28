/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import database.Database;
import java.sql.SQLException;
import java.util.ArrayList;
import Kendaraan.Kendaraan;
import Kendaraan.Kerusakan;
import Manusia.Manusia;


public class ModelKendaraan extends Database {

    private ArrayList<Kendaraan> listkendaraan = new ArrayList<>();
    private ArrayList<Kerusakan> listKerusakan = new ArrayList<>();

    public ModelKendaraan(int id) throws SQLException {
        loadKendaraan(id);
    }

    public ModelKendaraan() {
    }

    public void getDaftarKerusakan(int id) throws SQLException {
        connect();
        listKerusakan = new ArrayList<>();
        String query = "SELECT * FROM kerusakan WHERE idkendaraan='" + id + "'";
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            listKerusakan.add(new Kerusakan(
                    rs.getInt("id"),
                    rs.getInt("idkendaraan"),
                    rs.getString("bagian"),
                    rs.getString("keterangan"),
                    rs.getString("status"),
                    rs.getInt("biaya")
            ));
        }
        disconnect();
    }

    public ArrayList<Kerusakan> daftarKerusakan() {
        return listKerusakan;
    }

    public void loadKendaraan(int id) throws SQLException {
        connect();
        String query;
        if (id >= 0) {
            query = "SELECT * FROM kendaraan WHERE idpemilik='" + id + "'";
        } else {
            query = "SELECT * FROM kendaraan";
        }
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            listkendaraan.add(new Kendaraan(
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
        return listkendaraan;
    }

    public void tambahKendaraan(Kendaraan k) throws SQLException {
        connect();
        String query = "INSERT INTO kendaraan VALUES(";
        query += "NULL,";
        query += "'" + k.getNomor_kendaraan() + "',";
        query += "'" + k.getNama() + "',";
        query += "'" + k.getJenis() + "',";
        query += "'" + k.getKeterangan() + "',";
        query += k.getIdpemilik() + ",";
        query += "null" + ",";
        query += "null" + ",";
        query += "'masuk'";
        query += ")";
        stmt.executeUpdate(query);
        disconnect();
    }
}
