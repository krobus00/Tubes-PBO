/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kendaraan;

/**
 *
 * @author Krobus
 */
public class Motor extends Kendaraan {

    @Override
    void tambahKerusakan(String kerusakan) {
        System.out.println("ini tambah kerusakan motor");
    }

    @Override
    void daftarKerusakan() {
        System.out.println("ini daftar kerusakan");
    }

    @Override
    void telahDiperbaiki() {
        System.out.println("Kerusakan telah diperbaiki");
    }
}
