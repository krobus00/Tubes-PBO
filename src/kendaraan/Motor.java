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
public class Motor extends kendaraan implements Jenis {

    protected String merk;
    protected String tanggal;

    public Motor(String nomor_kendaraan, String status, String merk, String tanggal) {
        super(nomor_kendaraan, status);
        this.merk = merk;
        this.tanggal = tanggal;
    }

    @Override
    public void tambahKerusakan(String kerusakan, int biaya) {

    }

    @Override
    public void updateKerusakan(String bagian, String status) {

    }

    @Override
    public void daftarKerusakan() {

    }

    @Override
    public void detailKendaraan() {
    }

    @Override
    public void biayaPerbaikan() {

    }
}
