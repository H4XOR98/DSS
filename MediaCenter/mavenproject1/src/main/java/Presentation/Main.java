/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Business.MediaCenter_Facade;

/**
 *
 * @author lazaropinheiro
 */
public class Main {
    public static void main(String[] args) {
        MediaCenter_Facade facade = new MediaCenter_Facade();
        ReproduzirConteudoCasa reproduzir = new ReproduzirConteudoCasa();
        reproduzir.setVisible(true);
    }
}
