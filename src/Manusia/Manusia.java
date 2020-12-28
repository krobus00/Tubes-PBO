/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manusia;


public class Manusia extends AbstractManusia {

    private int id;
    private String nama;
    private String username;
    private String password;
    private String alamat;
    private String level;

    public Manusia(int id, String nama) {
        this.nama = nama;
        this.id = id;
    }

    public Manusia(int id, String nama, String username, String password, String alamat, String level) {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.alamat = alamat;
        this.level = level;
    }

    public Manusia(String nama, String username, String password, String alamat) {
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.alamat = alamat;
    }

    public Manusia(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getLevel() {
        return level;
    }

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public Object[] getData() {
        return new Object[]{this.nama, this.username, this.alamat};
    }

}
