package Business;

import Business.Utilizador;

public class Residente extends Utilizador {
    public Residente(){
        super();
    }
    
    public Residente(String email,String nome,String password){
        super(email,nome,password);
    }
    
    public Residente(int idUtilizador, String email,String nome,String password){
        super(idUtilizador,email,nome,password);
    }
    
    public Residente(Residente residente){
        super(residente);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("------ Residente ------\n");
        sb.append(super.toString());
        return sb.toString();
    }
    
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Residente residente = (Residente)o;
        return super.equals(residente);
    }
    
    public Residente clone(Residente residente){
        return new Residente(residente);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }
}
