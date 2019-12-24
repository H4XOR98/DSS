package Business;

import java.io.IOException;


public class Musica extends Conteudo {
    private String categoria;

    public Musica(){
        super();
        this.categoria = CategoriasMusicaEnum.getRandom();
    }

    public Musica(String path) throws IOException{
        super(path);
        this.categoria = CategoriasMusicaEnum.getRandom();
    }

    public Musica(int idConteudo,String titulo, String autor,String path, String categoria) {
        super(idConteudo,titulo,autor,path);
        this.categoria = categoria;
    }
    
    public Musica(String path,String cat) throws IOException{
        super(path);
        this.categoria = cat;
    }
    
    public Musica(Musica m){
        super(m);
        this.categoria = m.getCategoria();
    }

    public String getCategoria(){
        return this.categoria;
    }

    public String setCategoria(String novaCategoria){
        String a = this.categoria;
        this.categoria = novaCategoria;
        return a;
    }

    public static boolean validaFormatoMusica (String formato){
        return FormatosMusicaEnum.validaFormato(formato);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Musica musica = (Musica)o;
        return super.equals(musica);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(this.categoria);
        return sb.toString();
    }
    
    @Override
    public Musica clone(){
        return new Musica(this);
    }
    
    
   
}