package Business;

import Exceptions.FormatoInvalidoException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public abstract class Conteudo {
    private final int idConteudo;
    private final String titulo;
    private final String autor;
    private String path;

    private final String pathDest = FileSystems.getDefault().getPath("").toAbsolutePath().toString() + "/Ficheiros/";
    public static int identidade;

    public Conteudo(){
        this.idConteudo = identidade;
        this.titulo = "n/a";
        this.autor = "n/a";
        this.path = "n/a";
    }

    public Conteudo(String path) throws IOException{
        this.idConteudo = identidade++;
        Path p = Paths.get(path);
        String fileName =  p.getFileName().toString();
        String[] aux = parsePath(fileName);
        this.titulo = aux[0];
        this.autor = aux[1];
        this.path = pathDest + fileName;
        if(!new File(this.path).exists()){
            Files.copy(Paths.get(path),Paths.get(this.path));
        }
    }
    
    
    public Conteudo(int idConteudo, String titulo,String autor, String path){
        this.idConteudo = idConteudo;
        this.titulo = titulo;
        this.autor = autor;
        this.path = path;
    }
    
    
    public Conteudo(Conteudo conteudo){
        this.idConteudo = conteudo.getIdConteudo();
        this.titulo = conteudo.getTitulo();
        this.autor = conteudo.getAutor();
        this.path = conteudo.getPath();
    }
    
    public int getIdConteudo(){
        return this.idConteudo;
    }
    
     public String getTitulo() {
        return titulo;
    }

    public String getAutor(){
        return this.autor;
    }

    public String getPath(){
        return this.path;
    }
    
    public abstract String getCategoria();

    public abstract String setCategoria(String novaCategoria);
    
    private String[] parsePath(String fileName){
        String[] partes = fileName.split("\\.");
        partes = partes[0].split("_");
        String[] aux = {"n/a","n/a"};
        if(partes.length == 1){
            aux[0] = partes[0];
        }else if(partes.length >= 2){
            aux[0] = partes[0];
            aux[1] = partes[1];
        }

        return aux;
    }

    public static int validaConteudo(String path) throws FileNotFoundException,FormatoInvalidoException{
        Path p = Paths.get(path);

        boolean existe = Files.exists(p);
        if(!existe){
            throw new FileNotFoundException("Ficheiro inexistente");
        }

        String fileName =  p.getFileName().toString();
        String[] partes = fileName.split("_");


        String formato = partes[partes.length-1].substring(partes[partes.length - 1].lastIndexOf(".") + 1);

        if(Musica.validaFormatoMusica(formato)){
            return 1; //Musica
        }else if(Video.validaFormatoVideo(formato)){
            return 2; //Video
        }else{
            throw new FormatoInvalidoException("Formato Inválido");
        }
    }
    
    public void reproduzirConteudo() throws IOException{
        Desktop.getDesktop().open(Paths.get(this.path).toFile());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conteudo conteudo = (Conteudo) o;
        return (titulo.equals(conteudo.titulo) &&
                autor.equals(conteudo.autor));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idConteudo;
        hash = 67 * hash + Objects.hashCode(this.titulo);
        hash = 67 * hash + Objects.hashCode(this.autor);
        return hash;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("" + this.idConteudo + " | " + this.titulo + " | " + this.autor + " | ");
        return sb.toString();
    }
    
    
    @Override
    public abstract Conteudo clone();
    
   
}