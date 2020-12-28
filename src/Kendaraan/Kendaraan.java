/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kendaraan;

import java.util.ArrayList;


public class Kendaraan implements InterfaceKendaraan{

    protected int id;
    protected String nomor_kendaraan;
    protected String nama;
    protected String jenis;
    protected String keterangan;
    protected int idpemilik;
    protected String masuk;
    protected String keluar;
    protected String status;
    protected ArrayList<Kerusakan> kerusakan = new ArrayList<>();

    public Kendaraan(String nomor_kendaraan, String nama, String jenis, String keterangan, int idpemilik) {
        this.nomor_kendaraan = nomor_kendaraan;
        this.nama = nama;
        this.jenis = jenis;
        this.keterangan = keterangan;
        this.idpemilik = idpemilik;
    }

    public Kendaraan(String nomor_kendaraan, String jenis, int idpemilik, String masuk) {
        this.nomor_kendaraan = nomor_kendaraan;
        this.jenis = jenis;
        this.idpemilik = idpemilik;
        this.masuk = masuk;
    }

    public Kendaraan(int id, String nomor_kendaraan, String jenis, int idpemilik, String masuk, String keluar, String status) {
        this.id = id;
        this.nomor_kendaraan = nomor_kendaraan;
        this.jenis = jenis;
        this.idpemilik = idpemilik;
        this.masuk = masuk;
        this.keluar = keluar;
        this.status = status;
    }

    public Kendaraan(int id, String nomor_kendaraan, String nama, String jenis, String keterangan, int idpemilik, String masuk, String keluar, String status) {
        this.id = id;
        this.nomor_kendaraan = nomor_kendaraan;
        this.nama = nama;
        this.jenis = jenis;
        this.keterangan = keterangan;
        this.idpemilik = idpemilik;
        this.masuk = masuk;
        this.keluar = keluar;
        this.status = status;
    }

    @Override
    public Object[] getData() {
        if (this.masuk != null) {
            this.masuk = this.masuk.substring(0, this.masuk.length() - 2);
        }
        if (this.keluar == null || this.keluar.equals("-")) {
            this.keluar = "-";
        } else {
            this.keluar = this.keluar.substring(0, this.keluar.length() - 2);
        }
        return new Object[]{this.nomor_kendaraan, this.jenis, this.masuk, this.keluar, this.status};
    }

    public int getId() {
        return id;
    }

    public String getNomor_kendaraan() {
        return nomor_kendaraan;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis() {
        return jenis;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public int getIdpemilik() {
        return idpemilik;
    }

    public String getMasuk() {
        return masuk;
    }

    public String getKeluar() {
        return keluar;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Kerusakan> daftarKerusakan() {
        return kerusakan;
    }
}
