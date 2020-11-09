/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sparepart;

/**
 *
 * @author Krobus
 */
public class sparepart {

    protected String nama;
    protected String bagian;
    protected int harga;
    protected int jumlah;

    public sparepart(String nama, String bagian, int harga, int jumlah) {
        this.nama = nama;
        this.bagian = bagian;
        this.harga = harga;
        this.jumlah = jumlah;
    }

}
