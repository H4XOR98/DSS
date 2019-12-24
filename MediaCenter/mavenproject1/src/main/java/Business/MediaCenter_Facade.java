package Business;

import Data.ConteudoDAO;
import java.util.Map;

import Exceptions.UtilizadorInexistenteException;
import Data.UtilizadorDAO;
import Exceptions.AdministradorNaoAutenticadoException;
import Exceptions.ConteudoDuplicadoPessoalException;
import Exceptions.FormatoInvalidoException;
import Exceptions.PasswordsIncompativeisException;
import Exceptions.UtilizadorJaExistenteException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class MediaCenter_Facade {
    private Colecao colecaoCasa;
    private Colecao colecaoPessoal;
    private UtilizadorDAO gestorUtilizadores;
    private ConteudoDAO gestorConteudos;
    private Utilizador utilizadorLogado;
    
    public MediaCenter_Facade(){
        this.colecaoCasa = new Colecao();
        this.colecaoPessoal = null;
        this.gestorUtilizadores = new UtilizadorDAO();
        this.gestorConteudos = new ConteudoDAO();
        this.utilizadorLogado = null;
    }

    public void carregarDados(){
        Conteudo.identidade = this.gestorConteudos.maxIdConteudo()+1;
        Utilizador.identificador = this.gestorUtilizadores.maxIdUtilizador()+1;
        List<Conteudo> conteudos = this.gestorConteudos.getConteudos();
        this.colecaoCasa.adicionaListaConteudos(conteudos);
    }
    
    
    public int tipoUtilizadorLogado(){
        if (this.utilizadorLogado instanceof Administrador) return 1;
        if (this.utilizadorLogado instanceof Residente) return 2;
        return 0;
    }
    
    
    /*FUNCIONAL*/
    public boolean validaOperacao(String password) throws PasswordsIncompativeisException {
        if(!utilizadorLogado.comparaPassword(password)){
            throw new PasswordsIncompativeisException("Password Incorreta!");
        }
        return utilizadorLogado.comparaPassword(password);
    }

    /*FUNCIONAL*/
    public void loginUtilizador(String email,String password) throws UtilizadorInexistenteException, PasswordsIncompativeisException {
        if(!gestorUtilizadores.containsKey(email)){
            throw new UtilizadorInexistenteException("Email inserido inválido!");
        }
        Utilizador utilizador = this.gestorUtilizadores.get(email);
        if(!utilizador.comparaPassword(password)){
            throw new PasswordsIncompativeisException("Password inserida incorreta!");
        }
        int idUtilizador = utilizador.getIdUtilizador();
        List<Conteudo> listaConteudos = this.gestorConteudos.getConteudosUtilizador(idUtilizador);
        this.colecaoPessoal = new Colecao();
        this.colecaoPessoal.adicionaListaConteudos(listaConteudos);
        this.utilizadorLogado = utilizador;
    }
    
    /*FUNCIONAL*/
    public void terminarSessao(){
        this.colecaoPessoal.apagarColecao();
        this.colecaoPessoal = null;
        this.utilizadorLogado = null;
    }
    
    /*FUNCIONAL*/
    public void criarUtilizador(String email,String password,String nome,int tipo) throws UtilizadorJaExistenteException, AdministradorNaoAutenticadoException{
        if(!this.utilizadorLogado.getClass().getSimpleName().toString().equals("Administrador")){
            throw new AdministradorNaoAutenticadoException("Nenhum Administrador autenticado!");
        }
        if(this.gestorUtilizadores.containsKey(email)){
            throw new UtilizadorJaExistenteException("Já existe um Utilizador com este email!");
        }
        Utilizador utilizador;
        if(tipo == 1){
            utilizador = new Administrador(email,nome,password);
        }
        else{
            utilizador = new Residente(email,nome,password);
        }
        this.gestorUtilizadores.put(email,utilizador);
    }
    
    /*FUNCIONAL*/
    public void imprimeUtilizadores(){
        for(Utilizador u : this.gestorUtilizadores.values()){
            System.out.println(u.toString());
        }
    }
    
    
    /*FUNCIONAL*/
    public void removerUtilizador(String email,String password) throws AdministradorNaoAutenticadoException, UtilizadorInexistenteException, PasswordsIncompativeisException {
        if(utilizadorLogado.getEmail().equals(email) && utilizadorLogado.getPassword().equals(password)){
            throw new UtilizadorInexistenteException("Operação Inválida!");
        }
        if(!this.utilizadorLogado.getClass().getSimpleName().toString().equals("Administrador")){
            throw new AdministradorNaoAutenticadoException("Nenhum Administrador autenticado!");
        }
        if(!this.gestorUtilizadores.containsKey(email)){
            throw new UtilizadorInexistenteException("Email inválido!");
        }
        Utilizador utilizador = this.gestorUtilizadores.get(email);
        if(!utilizador.comparaPassword(password)){
            throw new PasswordsIncompativeisException("A password inserida incorreta!");
        }
        this.gestorUtilizadores.remove(email);
        int idUtilizador = utilizador.getIdUtilizador();
        this.gestorConteudos.removeConteudosUtilizador(idUtilizador);
        //Atualizar a colecao Casa
        this.colecaoCasa.apagarColecao();
        List<Conteudo> listaConteudos = this.gestorConteudos.getConteudos();
        this.colecaoCasa.adicionaListaConteudos(listaConteudos);
    }
    
    /*FUNCIONAL*/
    public void mudarNome(String novoNome){
        String email = utilizadorLogado.getEmail();
        utilizadorLogado.setNome(novoNome);
        this.gestorUtilizadores.put(email, utilizadorLogado);
    }
    
    /*FUNCIONAL*/ 
    public void mudarPassword(String novaPassword,String novaPasswordC) throws PasswordsIncompativeisException{
        if(!novaPassword.equals(novaPasswordC)){
            throw new PasswordsIncompativeisException("As passwords inseridas são diferentes!");
        }
        String email = this.utilizadorLogado.getEmail();
        utilizadorLogado.setPassword(novaPassword);
        this.gestorUtilizadores.put(email,utilizadorLogado);
    }
    
    
    
    
    public void imprimeColecaoPessoal(){
        this.colecaoPessoal.imprimeConteudo();
    }
    
    public void imprimeColecaoCasa(){
        this.colecaoCasa.imprimeConteudo();
    }
    
    /*
     *
     *
     *Conteudo
     *
     *
     */
    
    public void uploadConteudo(String path) throws ConteudoDuplicadoPessoalException, IOException, FileNotFoundException, FormatoInvalidoException {
        Conteudo conteudo;
        if(Conteudo.validaConteudo(path)==1){
            conteudo = new Musica(path);
        }
        else{
            conteudo = new Video(path);
        }
        if(this.colecaoPessoal.existeConteudo(conteudo)){
            throw new ConteudoDuplicadoPessoalException("Conteúdo já existe na coleção pessoal");
        }
        int idUtilizador = this.utilizadorLogado.getIdUtilizador();
        boolean duplicacao = false;
        if(!this.colecaoCasa.existeConteudo(conteudo)){
            this.colecaoCasa.adicionaConteudo(conteudo);
        }else{
            conteudo = this.colecaoCasa.getConteudoTituloAutor(conteudo.getTitulo(),conteudo.getAutor()).clone();
            duplicacao = true;
        }
        if(!this.colecaoPessoal.existeConteudo(conteudo)){
            this.colecaoPessoal.adicionaConteudo(conteudo);
        }
        this.gestorConteudos.adicionaConteudo(idUtilizador,conteudo,duplicacao);
    }
    
    
    public void removerConteudo(int idConteudo) {
        if(!this.gestorConteudos.conteudoDuplicado(idConteudo)){
            this.colecaoCasa.removerConteudo(idConteudo);
        }
        this.colecaoPessoal.removerConteudo(idConteudo);
        this.gestorConteudos.removerConteudo(idConteudo);
    }
    
    
    
    public void mudarCategoriaNova(int idConteudo,String novaCategoria){
        int idUtilizador = this.utilizadorLogado.getIdUtilizador();
        String categoriaAntiga = this.colecaoPessoal.getConteudo(idConteudo).getCategoria();
        this.colecaoPessoal.atualizaCategoria(idConteudo,novaCategoria);
        this.gestorConteudos.atualizaCategoria(idUtilizador,idConteudo,categoriaAntiga,novaCategoria);
    }
    
    
    public List<String> listaConteudo(){
        List<String> listaString;
        if(this.utilizadorLogado != null){
            listaString = this.colecaoPessoal.getListaConteudo();
        }else{
            listaString = this.colecaoCasa.getListaConteudo();
        }
        return listaString;
    }
    
    
    public List<String> listaAutores(){
        Colecao colecao;
        if(utilizadorLogado == null){
            colecao = this.colecaoCasa;
        }else{
            colecao = this.colecaoPessoal;
        }
        return colecao.getListaAutores();
    }
    
    
    public List<String> listaCategorias(){
        Colecao colecao;
        if(utilizadorLogado == null){
            colecao = this.colecaoCasa;
        }else{
            colecao = this.colecaoPessoal;
        }
        return colecao.getListaCategorias();
    }
    
    
    public List<String> listaCategoriasMusica(){
        return CategoriasMusicaEnum.toList();
    }
    
    
    public List<String> listaCategoriasVideo(){
        return CategoriasVideoEnum.toList();
    }
    
    
    public int tipoConteudo(int idConteudo){
         Conteudo c = this.colecaoCasa.getConteudo(idConteudo);
         if (c instanceof Musica) return 1;
         else return 0;
    }
    
    
    /*
     * Criar Playlist's
     * @param opcoes(aleatorio, por autor, por categoria, especifico)
    */
    public Playlist criarPlaylist(String[] opcoes){
        Playlist playlist = new Playlist();
        Colecao colecao;
        if(opcoes.length > 0){
            List<Conteudo> listaConteudo = new ArrayList<>();
            if(this.utilizadorLogado == null){
                colecao = this.colecaoCasa;
            }else{
                colecao = this.colecaoPessoal;
            }
            switch(opcoes[0]){
                case "aleatório":
                    colecao.playlistAleatoria(listaConteudo);
                    break;
                case "por autor":
                    colecao.playlistAutor(opcoes[1],listaConteudo);
                    break;
                case "por categoria":
                    colecao.playlistCategoria(opcoes[1], listaConteudo);
                    break;
                case "especifico":
                    colecao.playlistEspecifico(Integer.parseInt(opcoes[1]),listaConteudo);
                default:
                    break;
            }
            playlist.setConteudos(listaConteudo);
        }
        return playlist;
    }
    
    
    
    public void reproduzir(Playlist playlist) throws IOException{
        playlist.reproduzir();
    }
    
    public void pararReproducao() throws IOException{
        Runtime rt = Runtime.getRuntime();
        rt.exec("killall VLC");
    }
}