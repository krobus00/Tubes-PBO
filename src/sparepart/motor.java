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
public class motor extends sparepart implements jenis {

    public motor(String nama, String bagian, int harga, int jumlah) {
        super(nama, bagian, harga, jumlah);
    }

    public int getHarga() {
        return harga;
    }
    public int getHarga(int diskon) {
        // untuk mendapatkan harga jika didiskon
        return harga - (harga*diskon/100);
    }
    public int getJumlah() {
        return jumlah;
    }

    @Override
    public void updateJumlah(int i) {
        // mengubah jumlah sparepart
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void updateJumlah(int i, int harga) {
        // mengubah jumlah dan sparepart.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void detail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void diskon(int persen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
