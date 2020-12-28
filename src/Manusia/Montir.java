/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manusia;

import Admin.Sparepart;
import Kendaraan.Kendaraan;
import Kendaraan.Kerusakan;
import java.sql.SQLException;
import java.util.ArrayList;
import database.Database;


public class Montir extends Database implements InterfaceManusia {

    private ArrayList<Kendaraan> listKendaraan = new ArrayList<>();
    private ArrayList<Kendaraan> listDikerjakan = new ArrayList<>();
    private Manusia u;

    public Montir(Manusia u) throws SQLException {
        this.u = u;
        Montir.this.LoadData();
        LoadData(u.getId());
    }

    @Override
    public void LoadData() throws SQLException {
        connect();
        String query = "SELECT * FROM kendaraan WHERE status !='selesai'";
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

    public void LoadData(int id) throws SQLException {
        connect();
        String query = "SELECT * FROM kendaraan INNER JOIN kerusakan ON kendaraan.id = kerusakan.idkendaraan AND kerusakan.idmontir=";
        query += id;
        query += " GROUP by kendaraan.id";
        rs = stmt.executeQuery(query);
        listDikerjakan = new ArrayList<>();
        while (rs.next()) {
            listDikerjakan.add(new Kendaraan(
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

    public ArrayList<Kendaraan> getListKendaraan() {
        return this.listKendaraan;
    }

    public ArrayList<Kendaraan> getListDikerjakan() {
        return this.listDikerjakan;
    }

    public ArrayList<Sparepart> getListSparepart(String s) throws SQLException {
        ArrayList<Sparepart> listSparepart = new ArrayList<>();
        connect();
        String query = "SELECT * FROM sparepart WHERE nama LIKE '%";
        query += s;
        query += "%'";
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            listSparepart.add(new Sparepart(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("merk"),
                    rs.getString("bagian"),
                    rs.getInt("harga"),
                    rs.getInt("jumlah")
            ));
        }
        disconnect();
        return listSparepart;
    }

    private void kurangiStok(int id) throws SQLException {
        connect();
        String query = "UPDATE sparepart SET jumlah = jumlah - 1 WHERE id=" + id;
        stmt.executeUpdate(query);
        disconnect();
    }

    public void TambahData(Manusia u, int idSparepart, Kerusakan k) throws SQLException {
        kurangiStok(idSparepart);
        connect();
        String query = "INSERT INTO kerusakan VALUES(";
        query += "" + "0" + ",";
        query += k.getIdkendaraan() + ",";
        query += u.getId() + ",";
        query += "'" + k.getBagian() + "',";
        query += "'" + k.getKeterangan() + "',";
        query += "'" + k.getStatus() + "',";
        query += k.getBiaya() + ")";
        stmt.executeUpdate(query);
        disconnect();
    }

    public String getNamaByIDKendaraan(int id) throws SQLException {
        connect();
        String nama = "";
        String query = "SELECT nama FROM user WHERE id=" + id;
        rs = stmt.executeQuery(query);
        if (rs.next()) {
            nama = rs.getString("nama");
        }
        disconnect();
        return nama;
    }

    public void updateStatusKerusakan(int id, String status) throws SQLException {
        connect();
        String query = "UPDATE kerusakan SET status='";
        query += status + "' ";
        query += "WHERE id=" + id;
        stmt.executeUpdate(query);
        disconnect();
    }

    public void updateStatusKendaraan(int id, String status) throws SQLException {
        connect();
        String query = "UPDATE kendaraan SET status='";
        query += status + "' ";
        query += "WHERE id=" + id;
        stmt.executeUpdate(query);
        disconnect();
    }
}
