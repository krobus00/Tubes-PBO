/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Admin.Pembayaran;
import database.Database;
import java.sql.SQLException;
import java.util.ArrayList;
import Kendaraan.Kendaraan;
import Manusia.Manusia;
import Admin.Sparepart;
import Admin.Voucher;


public class ModelAdmin extends Database implements InterfaceAdmin {

    private ModelKendaraan modelKendaraan;
    private ArrayList<Kendaraan> listkendaraan = new ArrayList<>();
    private ArrayList<Manusia> listMontir = new ArrayList<>();
    private ArrayList<Sparepart> listSparepart = new ArrayList<>();
    private ArrayList<Voucher> listVoucher = new ArrayList<>();
    private ArrayList<Kendaraan> listPembayaranKendaraan = new ArrayList<>();

    public ModelAdmin() throws SQLException {
        modelKendaraan = new ModelKendaraan(-1);
        loadData();
    }

    @Override
    public void loadData() throws SQLException {
        connect();
        listkendaraan = new ArrayList<>();
        listMontir = new ArrayList<>();
        listSparepart = new ArrayList<>();
        listVoucher = new ArrayList<>();
        listPembayaranKendaraan = new ArrayList<>();
        String query = "SELECT * FROM kendaraan";
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
        query = "SELECT * FROM kendaraan WHERE status='Selesai'";
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            listPembayaranKendaraan.add(new Kendaraan(
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
        query = "SELECT * FROM user WHERE level='montir'";
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            listMontir.add(new Manusia(rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("alamat"),
                    rs.getString("level")
            ));
        }
        query = "SELECT * FROM sparepart";
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
        query = "SELECT * FROM voucher";
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            listVoucher.add(new Voucher(
                    rs.getInt("id"),
                    rs.getString("kode"),
                    rs.getInt("persen"),
                    rs.getInt("jumlah")
            ));
        }
        disconnect();
    }

    public ArrayList<Kendaraan> daftarKendaraan() {
        return listkendaraan;
    }

    public ArrayList<Kendaraan> daftarPembayaranKendaraan() {
        return listPembayaranKendaraan;
    }

    public ArrayList<Manusia> daftarMontir() {
        return listMontir;
    }

    public ArrayList<Sparepart> daftarSparepart() {
        return listSparepart;
    }

    public ArrayList<Voucher> daftarVoucher() {
        return listVoucher;
    }

    @Override
    public void Tambah(Sparepart s) throws SQLException {
        connect();
        String query = "INSERT INTO sparepart VALUES(";
        query += "NULL,";
        query += "'" + s.getNama() + "',";
        query += "'" + s.getMerk() + "',";
        query += "'" + s.getBagian() + "',";
        query += s.getHarga() + ",";
        query += s.getJumlah();
        query += ")";
        stmt.executeUpdate(query);
        disconnect();
    }

    public void HapusSparepart(int id) throws SQLException {
        connect();
        String query = "DELETE FROM sparepart WHERE id=";
        query += id;
        stmt.executeUpdate(query);
        disconnect();
    }

    @Override
    public void Edit(Sparepart s) throws SQLException {
        connect();
        String query = "UPDATE sparepart SET ";
        query += "nama='" + s.getNama() + "',";
        query += "merk='" + s.getMerk() + "',";
        query += "bagian='" + s.getBagian() + "',";
        query += "harga=" + s.getHarga() + ",";
        query += "jumlah=" + s.getJumlah() + " ";
        query += "WHERE id=" + s.getId();
        stmt.executeUpdate(query);
        disconnect();
    }

    @Override
    public void Tambah(Voucher v) throws SQLException {
        connect();
        String query = "INSERT INTO voucher VALUES( ";
        query += "NULL,";
        query += "'" + v.getKode() + "', ";
        query += v.getPersen() + ", ";
        query += v.getJumlah();
        query += ")";
        stmt.executeUpdate(query);
        disconnect();
    }

    @Override
    public void Edit(Voucher v) throws SQLException {
        connect();
        String query = "UPDATE voucher SET ";
        query += "kode='" + v.getKode() + "',";
        query += "persen=" + v.getPersen() + ",";
        query += "jumlah=" + v.getJumlah() + " ";
        query += "WHERE id=" + v.getId();
        stmt.executeUpdate(query);
        disconnect();
    }

    public void HapusVoucher(int id) throws SQLException {
        connect();
        String query = "DELETE FROM voucher WHERE id=";
        query += id;
        stmt.executeUpdate(query);
        disconnect();
    }

    @Override
    public void Tambah(Manusia u) throws SQLException {
        connect();
        String query = "INSERT INTO user VALUES(";
        query += "'" + "0" + "',";
        query += "'" + u.getNama() + "',";
        query += "'" + u.getUsername() + "',";
        query += "'" + u.getPassword() + "',";
        query += "'" + u.getAlamat() + "',";
        query += "'montir'";
        query += ")";
        stmt.executeUpdate(query);
        disconnect();
    }

    @Override
    public void Edit(Manusia u) throws SQLException {
        connect();
        String query = "UPDATE user SET ";
        query += "nama='" + u.getNama() + "',";
        query += "username='" + u.getUsername() + "',";
        query += "password='" + u.getPassword() + "',";
        query += "alamat='" + u.getAlamat() + "' ";
        query += "WHERE id=" + u.getId();
        stmt.executeUpdate(query);
        disconnect();
    }
    public void HapusMontir(int id) throws SQLException {
        connect();
        String query = "DELETE FROM user WHERE id=";
        query += id;
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

    public void cariKendaraanPembayaran(String k) throws SQLException {
        connect();
        listPembayaranKendaraan = new ArrayList<>();
        String query = "SELECT * FROM kendaraan WHERE nomor LIKE '%";
        query += k;
        query += "%' WHERE status='Selesai'";
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            listPembayaranKendaraan.add(new Kendaraan(
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

    public void cariKendaraan(String k) throws SQLException {
        connect();
        listkendaraan = new ArrayList<>();
        String query = "SELECT * FROM kendaraan WHERE nomor LIKE '%";
        query += k;
        query += "%'";
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

    public void cariMontir(String m) throws SQLException {
        connect();
        listMontir = new ArrayList<>();
        String query = "SELECT * FROM user WHERE nama LIKE '%";
        query += m;
        query += "%' AND level='montir'";
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            listMontir.add(new Manusia(rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("alamat"),
                    rs.getString("level")
            ));
        }
        disconnect();
    }

    public void cariSparepart(String s) throws SQLException {
        connect();
        listSparepart = new ArrayList<>();
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
    }

    public void cariVoucher(String v) throws SQLException {
        connect();
        listVoucher = new ArrayList<>();
        String query = "SELECT * FROM voucher WHERE kode LIKE '%";
        query += v;
        query += "%'";
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            listVoucher.add(new Voucher(
                    rs.getInt("id"),
                    rs.getString("kode"),
                    rs.getInt("persen"),
                    rs.getInt("jumlah")
            ));
        }
        disconnect();
    }

    public ArrayList<Manusia> getMontirByIDKendaraan(int id) throws SQLException {
        connect();
        ArrayList<Manusia> lMontir = new ArrayList<>();
        String query = "SELECT * FROM user INNER JOIN kerusakan ON user.id = kerusakan.idmontir AND kerusakan.idkendaraan=";
        query += id;
        query += " GROUP BY kerusakan.idmontir";
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            lMontir.add(new Manusia(rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("alamat"),
                    rs.getString("level")
            ));
        }
        disconnect();
        return lMontir;
    }

    public void updateStatusKendaraan(int id, String status) throws SQLException {
        connect();
        String query = "UPDATE kendaraan SET status='";
        query += status + "' ";
        query += "WHERE id=" + id;
        stmt.executeUpdate(query);
        disconnect();
    }

    public Voucher checkVoucher(String kode) throws SQLException {
        connect();
        Voucher dataVoucher = null;
        listMontir = new ArrayList<>();
        String query = "SELECT * FROM voucher WHERE kode='" + kode + "'";
        rs = stmt.executeQuery(query);
        if (rs.next()) {
            dataVoucher = new Voucher(
                    rs.getInt("id"),
                    rs.getString("kode"),
                    rs.getInt("persen"),
                    rs.getInt("jumlah")
            );
        }
        disconnect();
        return dataVoucher;
    }

    public void Pembayaran(Pembayaran p) throws SQLException {
        connect();
        String query = "UPDATE kendaraan SET status='Lunas', keluar=CURRENT_TIMESTAMP()";
        query += "WHERE id=" + p.getIdkendaraan();
        stmt.executeUpdate(query);
        disconnect();
    }
}
