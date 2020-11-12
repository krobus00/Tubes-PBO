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
abstract class Manusia {
    
    protected String nama;
    protected String status;
    protected String alamat;

    public abstract void updateStatus(String status);
    
}
