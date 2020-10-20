/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes.pbo;

/**
 *
 * @author Krobus
 */
public class Yogi {
    private int yogi;
    private int darah;
    private int attack;
    public void heal(int i){
        this.darah += i;
        this.attack-= i;
    }
    public void ultimate(){
        this.attack = 10000;
        this.darah = 100;
    }
}
