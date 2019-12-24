package Business;

import Business.Utilizador;

public class Administrador extends Utilizador {
    public Administrador(){
        super();
    }
    
    public Administrador(int idUtilizador, String email,String nome,String password){
        super(idUtilizador,email,nome,password);
    }
    
    public Administrador(String email,String nome,String password){
        super(email,nome,password);
    }
    
    public Administrador(Administrador administrador){
        super(administrador);
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("------ Administrador ------\n");
        sb.append(super.toString());
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Administrador admin = (Administrador)o;
        return super.equals(admin);
    }
    
    public Administrador clone(Administrador admin){
        return new Administrador(admin);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
}
