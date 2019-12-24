package Business;

import java.util.ArrayList;
import java.util.List;

public enum CategoriasVideoEnum {
    Ação(1), Animação(2), Aventura(3), Comédia(4), Documentário(5), Drama(6), Fantasia(7), Ficção(8), Musical(9), Romance(10), Suspense(11), Terror(12);

    private double idCategoria;


    private CategoriasVideoEnum(int idCategoria){
        this.idCategoria = idCategoria;
    }

    public static String getRandom(){
        return (CategoriasVideoEnum.values()[(int)(Math.random() * values().length)]).toString();
    }
    
    public static List<String> toList(){
            List<String> l = new ArrayList<>();
            for (CategoriasVideoEnum value : CategoriasVideoEnum.values()) {
                l.add(value.toString());
            }
            return l;
        }
}
