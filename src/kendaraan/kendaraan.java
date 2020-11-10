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
    protected []kerusakan Kerusakan;

    abstract tambahKerusakan(String bagian, int biaya) {
    
    }
    abstract updateKerusakan(String bagian, String status){
    
    }
    abstract daftarKerusakan(){
    
    }
    abstract biayaPerbaikan(){
    
    }
}
