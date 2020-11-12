/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manusia;

/**
 *
 * @author iqbalsaviola
 */
public class Pelanggan extends Manusia implements jenis{
 
    protected Kendaraan kedaraan;
    protected String nama;
    protected String alamat;
    
    public Pelanggan(String nama, String alamat, Kendaraan kendaraan){
        
        this.nama = nama;
        this.alamat = alamat;
        this.kendaraan = kendaraan;
        
    }   
    
    public void updateStatus(String status){
    
    }
    
    public void detail(){
    
    }
    
    public void pembayaran(){
    
    }
    
    public void cetakBuktiPembayaran(){
    
    }

    
}
