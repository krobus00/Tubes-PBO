/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;


public class Voucher extends AbstractAdmin{

    private int id;
    private String kode;
    private int persen;
    private int jumlah;

    public Voucher(String kode, int persen, int jumlah) {
        this.kode = kode;
        this.persen = persen;
        this.jumlah = jumlah;
    }

    public Voucher(int id, String kode, int persen, int jumlah) {
        this.id = id;
        this.kode = kode;
        this.persen = persen;
        this.jumlah = jumlah;
    }

    public int getId() {
        return id;
    }

    public String getKode() {
        return kode;
    }

    public int getPersen() {
        return persen;
    }

    public int getJumlah() {
        return jumlah;
    }

    @Override
    public Object[] getData() {
        return new Object[]{this.kode, this.persen, this.jumlah};
    }
}
