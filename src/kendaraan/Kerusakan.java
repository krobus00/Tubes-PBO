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
public class Kerusakan{
    private String bagian;
    private String status;
    private int biaya;

    public Kerusakan(String bagian, int biaya, String status) {
        this.bagian = bagian;
        this.biaya = biaya;
        this.status = status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public String getBagian() {
        return bagian;
    }

    public int getBiaya() {
        return biaya;
    }
         
}
