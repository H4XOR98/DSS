/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.ConteudoDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Colecao {
    private Map<Integer,Conteudo> conteudos;
    private Map<String,Set<Integer>> autores;
    private Map<String,Set<Integer>> categorias;
    
    
    public Colecao(){
        this.conteudos = new HashMap<>();
        this.autores = new HashMap<>();
        this.categorias = new HashMap<>();
    }
    
    
    public boolean existeConteudo(Conteudo conteudo) {
        return this.conteudos.containsValue(conteudo);
    }
    
    
    public void adicionaConteudo(Conteudo conteudo) {
        int idConteudo = conteudo.getIdConteudo();
        String categoria = conteudo.getCategoria();
        String autor = conteudo.getAutor();
        this.conteudos.put(idConteudo,conteudo.clone());
        if(!this.categorias.containsKey(categoria)){
            Set<Integer> idsCategoriaAux = new HashSet<>();
            this.categorias.put(categoria, idsCategoriaAux);
        }
        if(!this.autores.containsKey(autor)){
            Set<Integer> idsAutorAux = new HashSet<>();
            this.autores.put(autor, idsAutorAux);
        }
        this.categorias.get(conteudo.getCategoria()).add(idConteudo);
        this.autores.get(conteudo.getAutor()).add(idConteudo);
    }
    
    public void adicionaListaConteudos(List<Conteudo> conteudos){
        for(Conteudo c : conteudos){
            this.adicionaConteudo(c);
        }
    }
    
    public Conteudo getConteudo(int idConteudo) {
        return this.conteudos.get(idConteudo).clone();
    }
    
    public Conteudo getConteudoTituloAutor(String titulo,String autor) {
        Conteudo conteudo = null;
        for(Conteudo c : this.conteudos.values()){
            if(c.getAutor().equals(autor) && c.getTitulo().equals(titulo)){
                conteudo = c.clone();
            }
        }
        return conteudo;
    }
    
    public List<String> getListaConteudo() {
        List<String> listaString = new ArrayList<>();
        this.conteudos.values().forEach(c -> listaString.add(c.toString()));
        return listaString;
    }
    
    
    Comparator<String> comparadorAutores = (a,b) -> {return a.compareTo(b);};
    
    public List<String> getListaAutores(){
        Set<String> autores = new TreeSet<>(comparadorAutores);
        for(String autor : this.autores.keySet()){
            autores.add(autor);
        }
        return new ArrayList<>(autores);
    }
    
    
    Comparator<String> comparadorCategorias = (a,b) -> {return a.compareTo(b);};
    
    public List<String> getListaCategorias(){
        Set<String> categorias = new TreeSet<>(comparadorCategorias);
        for(String categoria : this.categorias.keySet()){
            categorias.add(categoria);
        }
        return new ArrayList<>(categorias);
    }
    
    
    public void atualizaCategoria(int idConteudo, String novaCategoria) {
        Conteudo conteudo = this.conteudos.get(idConteudo);
        String antigaCategoria = conteudo.getCategoria();
        conteudo.setCategoria(novaCategoria);
	this.categorias.get(antigaCategoria).remove(idConteudo);
	if(!this.categorias.containsKey(novaCategoria)){
            Set<Integer> ids = new HashSet<>();
            this.categorias.put(novaCategoria,ids);
        }
        this.categorias.get(novaCategoria).add(idConteudo);
    }
    
    
    public void removerConteudo(int idConteudo) {
        Conteudo conteudo = this.conteudos.get(idConteudo);
        String categoria = conteudo.getCategoria();
        this.categorias.get(categoria).remove(idConteudo);
        if(this.categorias.get(categoria).isEmpty()){
            this.categorias.remove(categoria);
        }
        String autor = conteudo.getAutor();
        this.autores.get(autor).remove(idConteudo);
        if(this.autores.get(autor).isEmpty()){
            this.autores.remove(autor);
        }
        this.conteudos.remove(idConteudo);
    }
    
    /*
     *Criar Playlists
    */
    
    public void playlistAleatoria(List<Conteudo> listaConteudo){
        for(Conteudo c : this.conteudos.values()){
            listaConteudo.add(c.clone());
        }
        //Collections.shuffle(listaConteudo);
    }
    
    public void playlistCategoria(String categoria, List<Conteudo> listaConteudo){
        Set<Integer> idsCategoria = this.categorias.get(categoria);
        for(int idConteudo : idsCategoria){
            listaConteudo.add(this.conteudos.get(idConteudo).clone());
        }
    }
    
    public void playlistAutor(String autor, List<Conteudo> listaConteudo){
        Set<Integer> idsAutor = this.autores.get(autor);
        for(int idConteudo : idsAutor){
            listaConteudo.add(this.conteudos.get(idConteudo).clone());
        }
    }
    
    public void playlistEspecifico(int idConteudo, List<Conteudo> listaConteudo){
        listaConteudo.add(this.conteudos.get(idConteudo).clone());
    }
    
    
    public void apagarColecao(){
        this.conteudos.clear();
        for(Set<Integer> idsAutor : this.autores.values()){
            idsAutor.clear();
        }
        for(Set<Integer> idsCategoria : this.categorias.values()){
            idsCategoria.clear();
        }
    }
    
    public void imprimeConteudo(){
        for(Conteudo c : this.conteudos.values()){
            System.out.println(c.toString());
        }
    }
}
