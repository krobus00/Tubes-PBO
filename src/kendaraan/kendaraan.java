/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kendaraan;

/**
 *
 * @author REPUBLIC OF GAMERS
 */
abstract class kendaraan {

    protected String nomor_kendaraan;
    protected String status;
    protected Kerusakan[] Kerusakan;

    public kendaraan(String nomor_kendaraan, String status) {
        this.nomor_kendaraan = nomor_kendaraan;
        this.status = status;
    }

    public abstract void tambahKerusakan(String bagian, int biaya);

    public abstract void updateKerusakan(String bagian, String status);

    public abstract void daftarKerusakan();

    public abstract void biayaPerbaikan();
}
