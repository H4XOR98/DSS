/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Playlist{
    private List<Conteudo> conteudos;
    
    public Playlist(){
        this.conteudos = new ArrayList<>();
    }
    
    public Playlist(List<Conteudo> conteudos){
        setConteudos(conteudos);
    }
    
    public List<Conteudo> getConteudos(){
        List<Conteudo> aux = new ArrayList<>();
        conteudos.forEach((c) -> {
            aux.add(c.clone());
        });
        return aux;
    }

    public void setConteudos(List<Conteudo> conteudos){
        this.conteudos = new ArrayList<>();
        conteudos.forEach(c -> this.conteudos.add(c));
    }

    public List<String> ListToString(){
        List<String> l = new ArrayList<>();
        this.conteudos.forEach(c->l.add(c.toString()));
        return l;
    }
    
    public int size() {
        return this.conteudos.size();
    }

    public void reproduzir() {
        try{
            for(Conteudo c : this.conteudos){
                c.reproduzirConteudo();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
