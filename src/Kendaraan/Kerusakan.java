/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kendaraan;


public class Kerusakan {

    private int id;
    final private int idkendaraan;
    final private String bagian;
    final private String keterangan;
    final private String status;
    final private int biaya;

    public Kerusakan(int id, int idkendaraan, String bagian, String keterangan, String status, int biaya) {
        this.id = id;
        this.idkendaraan = idkendaraan;
        this.bagian = bagian;
        this.keterangan = keterangan;
        this.status = status;
        this.biaya = biaya;
    }

    public Kerusakan(int idkendaraan, String bagian, String keterangan, String status, int biaya) {
        this.idkendaraan = idkendaraan;
        this.bagian = bagian;
        this.keterangan = keterangan;
        this.status = status;
        this.biaya = biaya;
    }

    public int getId() {
        return id;
    }

    public int getIdkendaraan() {
        return idkendaraan;
    }

    public String getBagian() {
        return bagian;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getStatus() {
        return status;
    }

    public int getBiaya() {
        return biaya;
    }

    @Override
    public String toString() {
        String s = "Bagian : " + bagian + "\n"
                + "Keterangan : " + keterangan + "\n"
                + "Status : " + status + "\n"
                + "Biaya : " + biaya;

        return s;
    }
}
