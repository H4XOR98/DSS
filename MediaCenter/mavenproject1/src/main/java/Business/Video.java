package Business;

import java.io.IOException;
import java.util.Objects;

public class Video extends Conteudo {
    private String categoria;

    public Video(){
        super();
        this.categoria = CategoriasMusicaEnum.getRandom();
    }

    public Video(String path) throws IOException {
        super(path);
        this.categoria = CategoriasVideoEnum.getRandom();
    }

    public Video(String path,String categoria) throws IOException{
        super(path);
        this.categoria = categoria;
    }
    
    public Video(int idConteudo,String titulo, String autor,String path, String categoria) {
        super(idConteudo,titulo,autor,path);
        this.categoria = categoria;
    }
    
    public Video(Video v){
        super(v);
        this.categoria = v.getCategoria();
    }

    public String getCategoria(){
        return this.categoria;
    }

    @Override
    public String setCategoria(String novaCategoria){
        String a = this.categoria;
        this.categoria = novaCategoria;
        return a;
    }

    public static boolean validaFormatoVideo (String formato) {
        return FormatosVideoEnum.validaFormato(formato);
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video video = (Video)o;
        return super.equals(video);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.categoria);
        return hash;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(this.categoria);
        return sb.toString();
    }
    
    
    /**
     *
     * @return
     */
    @Override
    public Video clone(){
        return new Video(this);
    }
    
    
   
}
