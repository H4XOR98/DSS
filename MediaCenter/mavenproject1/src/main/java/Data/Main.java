/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Administrador;
import Business.Conteudo;
import Business.Musica;
import Business.Residente;
import Business.Utilizador;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mafal
 */
public class Main{
    private static String email= "teste@gmail.com";
    
    public static void main(String[] args) throws SQLException{
        UtilizadorDAO utilizador= UtilizadorDAO.getInstance();
        ConteudoDAO conteudo = ConteudoDAO.getInstance();
        Utilizador u1 = new Residente(0,"lazaro@gmail.com","lazaro","lazaro");
        Utilizador u2 = new Residente(1,"goncalo@gmail.com","goncalo","goncalo");
        Utilizador u3 = new Residente(2,"ruben@gmail.com","ruben","ruben");
        //utilizador.put("lazaro@gmail.com",u1);
        //utilizador.put("goncalo@gmail.com",u2);
        //utilizador.put("ruben@gmail.com",u3);
        Conteudo c = new Musica(0,"tio natal","tio jel","/arroz doce/","Rap");
        Conteudo c1 = new Musica(1,"pai inverno","ze manel","/arroz doce/","Soul");
        conteudo.clear();
        conteudo.adicionaConteudo(0, c, false);
        conteudo.adicionaConteudo(0, c1, false);
        conteudo.adicionaConteudo(1,c,true);
        conteudo.adicionaConteudo(2, c1, true);
        conteudo.clear();
        /*Conteudo c = new Musica(1,"tio natal","tio jel","/arroz doce/","Rap");
        Conteudo c1 = new Musica(2,"pai inverno","ze manel","/arroz doce/","Soul");
        conteudo.adicionaConteudo(0, c, true);*/
        //conteudo.adicionaConteudo(2, c, true);
        //conteudo.adicionaConteudo(1, c1, false);
        //conteudo.removeConteudosUtilizador(2);
        //conteudo.atualizaCategoria(2, c.getIdConteudo(), "Rap", "EDM");
        /*Utilizador u = new Administrador(0,"lazaro.pinheiro@gmail.com","lazaro","lazaro");
        utilizador.put("lazaro.pinheiro@gmail.com",u);
        System.out.println(u);*/
        //Utilizador ut = new Utilizador(1,"teste@gmail.com", "mafalda", "12345");
        //System.out.println(utilizador.put(ut));
        
        /*Utilizador ut= utilizador.get(2);
        System.out.println(ut.toString());*/
        //System.out.println(utilizador.containsKey(email));
        
        //System.out.println(utilizador.remove(1));
            
    }
}
    
