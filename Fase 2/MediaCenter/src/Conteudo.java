import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Conteudo {
    private String titulo;
    private String artista;
    private File file;


    public Conteudo(){
        this.titulo = "";
        this.artista = "";
        this.file = null;
    }

    public Conteudo(String titulo, String artista, String categoria, File file) {
        this.titulo = titulo;
        this.artista = artista;
        this.file = file;
    }

    public Conteudo (String path) throws FileNotFoundException,FormatoInvalidoException{
        validaExistenciaFicheiro(path);
        validaFormatoConteudo(path);
        this.file = new File(path);
    }

    public Conteudo(Conteudo conteudo){
        this.titulo = conteudo.getTitulo();
        this.artista = conteudo.getArtista();
        this.file = conteudo.getFile();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }


    public void validaFormatoConteudo(String path) throws FormatoInvalidoException{
        Path p = Paths.get(path);
        String fileName =  p.getFileName().toString();
        String[] split = fileName.split("_");

        if(split.length < 1){
            throw new FormatoInvalidoException("Ficheiro Inválido");
        }

        String formato = split[split.length-1].substring(split[split.length - 1].lastIndexOf(".") + 1);
        if(!FormatosConteudo.validaFormato(formato)){
            throw new FormatoInvalidoException("Formato Invalido");
        }

        if(split.length == 1){
            this.titulo = split[0].substring(0,split[0].lastIndexOf("."));
            if(split.length > 1) {
                this.artista = split[1].substring(0, split[1].lastIndexOf("."));
            }
        }
    }


    public void validaExistenciaFicheiro(String path) throws FileNotFoundException{
        Path p = Paths.get(path);
        boolean existe = Files.exists(p);
        /*if(!existe){
            throw new FileNotFoundException("Ficheiro não existe");
        }*/
    }


    public void reproduzirConteudo() throws IOException{
        Desktop.getDesktop().open(this.file);
    }

}