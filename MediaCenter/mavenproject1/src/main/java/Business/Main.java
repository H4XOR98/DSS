/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Exceptions.AdministradorNaoAutenticadoException;
import Exceptions.ConteudoDuplicadoPessoalException;
import Exceptions.FormatoInvalidoException;
import Exceptions.PasswordsIncompativeisException;
import Exceptions.UtilizadorInexistenteException;
import Exceptions.UtilizadorJaExistenteException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author lazaropinheiro
 */
public class Main {
    public static void main(String[] args) throws IOException {
        MediaCenter_Facade facade = new MediaCenter_Facade();
        try{
            facade.loginUtilizador("ruben@gmail.com", "ruben");
            //List<String> l = facade.colecaoPessoal.getListaConteudo();
   
            facade.imprimeColecaoPessoal();
        }
        catch(UtilizadorInexistenteException e){
            e.printStackTrace();
        }
        catch(PasswordsIncompativeisException e){
            e.printStackTrace();
        }
        
            
            
    }
        
}

    /*public static void main(String[] args) throws IOException {
        MediaCenter_Facade facade = new MediaCenter_Facade();
            facade.loginUtilizador("lazaro@gmail.com","lazaro");
            facade.uploadConteudo("/Users/lazaropinheiro/Downloads/kygo.mp3");
            facade.uploadConteudo("/Users/lazaropinheiro/Downloads/Bocejo_SONY_g.mp4");
            facade.terminarSessao();
            facade.loginUtilizador("lazaro","1234");
            facade.uploadConteudo("/Users/lazaropinheiro/Downloads/Bocejo_SONY_g.mp4");
            facade.uploadConteudo("/Users/lazaropinheiro/Downloads/carlos_t.mp3");
            facade.imprimeColecaoPessoal();
            facade.imprimeColecaoCasa();
            String[] opcoes = {"aleatorio"};
            Playlist p = facade.criarPlaylist(opcoes);
            p.reproduzir();*/
            
            /*facade.terminarSessao();
            facade.loginUtilizador("lazaro","1234");
            facade.uploadConteudo("/Users/lazaropinheiro/Downloads/Bocejo_SONY_g.mp4");
            facade.uploadConteudo("/Users/lazaropinheiro/Downloads/carlos_t.mp3");*/
            //facade.removerConteudo(1);*/
            //facade.mudarCategoriaNova(1,"Caganeira");
            //facade.loginUtilizador("tone","123");
            //facade.removerUtilizador("lazaro", "1234");
            /*facade.imprimeColecoesPessoais();
            facade.imprimeColecaoCasa();
            facade.terminarSessao();*/
            /*String[] opcoes = {"aleatorio"};
            Playlist p = facade.criarPlaylist(opcoes);
            p.reproduzir();*/
            /*Administrador admin = new Administrador("x","x","x");
            facade.utilizadorLogado = admin;
            System.out.println(facade.utilizadorLogado.toString());
            facade.criarUtilizador("lazaro","1234","lazaro","Administrador");
            facade.loginUtilizador("lazaro","1234");
            //facade.removerUtilizador("lazaro","1234");
            //facade.imprimeUtilizadores();
            //facade.loginUtilizador("lazaro","1234");
            //facade.validaOperacao("123");
            //facade.mudarNome("Antonio");
            //facade.imprimeUtilizadores();
            //System.out.println("\n\n\n" + facade.utilizadorLogado.toString());
            //facade.mudarPassword("zezoca","zezoca");
            //facade.imprimeUtilizadores();
            //System.out.println("\n\n\n" + facade.utilizadorLogado.toString());*/
        /*}catch(UtilizadorJaExistenteException e){
            System.out.println(e.getMessage());
        }catch(AdministradorNaoAutenticadoException e){
            System.out.println(e.getMessage());
        }catch(UtilizadorInexistenteException e){
            System.out.println(e.getMessage());
        }catch(ConteudoDuplicadoPessoalException e){
            System.out.println(e.getMessage());
        }catch(FormatoInvalidoException e){
            System.out.println(e.getMessage());
        }*/
        
        /*catch(UtilizadorInexistenteException e){
            System.out.println(e.getMessage());
        }*//*catch(UtilizadorJaExistenteException e){
            System.out.println(e.getMessage());
        }*//*catch(AdministradorNaoAutenticadoException e){
            System.out.println(e.getMessage());
        }*//*catch(PasswordsIncompativeisException e){
            System.out.println(e.getMessage());
        }*/
        
        /*Path p = Paths.get("/Users/lazaropinheiro/Downloads/Bocejo_SONY_g.mp4");
        try {
            Conteudo.validaConteudo(p.toString());
            
            //m.reproduzirConteudo();
        }catch(FormatoInvalidoException | FileNotFoundException e){
            e.printStackTrace();
        }*/

