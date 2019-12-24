package Business;

import Exceptions.PasswordsIncompativeisException;
import java.util.Objects;

public abstract class Utilizador {
    private final int idUtilizador;
    private String email;
    private String nome;
    private String password;

    public static int identificador;

    public Utilizador(){
        this.idUtilizador = identificador++;
        this.email = "n/a";
        this.nome = "n/a";
        this.password = "n/a";
    }
    public Utilizador(String email,String nome,String password){
        this.idUtilizador = identificador++;
        this.email = email;
        this.nome = nome;
        this.password = password;
    }

    public Utilizador(int idUtilizador, String email, String nome, String password){
        this.idUtilizador = idUtilizador;
        this.email = email;
        this.nome = nome;
        this.password = password;
    }
    
    public Utilizador(Utilizador u){
        this.idUtilizador = u.getIdUtilizador();
        this.email = u.getEmail();
        this.nome = u.getNome();
        this.password = u.getPassword();
    }
    
    
    public int getIdUtilizador(){
        return this.idUtilizador;
    }

    public String getEmail(){
        return this.email;
    }

    public String getNome(){
        return this.nome;
    }

    public String getPassword(){
        return this.password;
    }

    public void setEmail(String e){
        this.email = e;
    }

    public void setNome(String novoNome){
        this.nome = novoNome;
    }

    public void setPassword(String novaPassword){
        this.password = novaPassword;
    }

    public boolean validaUtilizador(String email,String password){
        return(this.email.equals(email) && this.password.equals(password));
    }

    public boolean comparaPassword(String password) {
        return this.password.equals(password);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Utilizador u = (Utilizador) o;
        return (this.idUtilizador == u.getIdUtilizador() || this.email.equals(u.getEmail()));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.idUtilizador;
        hash = 41 * hash + Objects.hashCode(this.email);
        hash = 41 * hash + Objects.hashCode(this.nome);
        return hash;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(this.idUtilizador).append(";\n");
        sb.append("Email: ").append(this.email).append(";\n");
        sb.append("Nome: ").append(this.nome).append(";\n");
        sb.append("Password: ");
        for(int i = 0; i < this.password.length() ;i++){
            sb.append("*");
        }
        sb.append(";\n");
        return sb.toString();
    }
}