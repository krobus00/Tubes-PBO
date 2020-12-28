/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;


public class Sparepart extends AbstractAdmin{

    protected int id;
    protected String nama;
    protected String merk;
    protected String bagian;
    protected int harga;
    protected int jumlah;

    public Sparepart(String nama, String merk, String bagian, int harga, int jumlah) {
        this.nama = nama;
        this.merk = merk;
        this.bagian = bagian;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    public Sparepart(int id, String nama, String merk, String bagian, int harga, int jumlah) {
        this.id = id;
        this.nama = nama;
        this.merk = merk;
        this.bagian = bagian;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getBagian() {
        return bagian;
    }

    public int getHarga() {
        return harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public String getMerk() {
        return merk;
    }

    @Override
    public Object[] getData() {

        return new Object[]{this.nama, this.merk, this.bagian, this.harga, this.jumlah};
    }

}
