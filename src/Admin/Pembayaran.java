/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;


public class Pembayaran {

    private int idkendaraan;
    private int harga = 0;
    private int potongan = 0;
    private int hargaAkhir = 0;

    public Pembayaran(int idkendaraan, int harga) {
        this.idkendaraan = idkendaraan;
        this.harga = harga;
        this.hargaAkhir = harga;
    }

    public void setDiskon(int potongan) {
        this.potongan = potongan;
    }

    public void hitungDiskon() {
        this.hargaAkhir = harga - (harga * potongan / 100);
    }

    public int getHargaAkhir() {
        return hargaAkhir;
    }

    public int getIdkendaraan() {
        return idkendaraan;
    }

    public int getTotalPotongan() {
        return (harga * potongan / 100);
    }

}
