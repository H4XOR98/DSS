/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Administrador;
import Business.Residente;
import Business.Utilizador;
import Exceptions.UtilizadorInexistenteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author lazaropinheiro
 */
public class UtilizadorDAO implements Map<String,Utilizador>{
    private static UtilizadorDAO inst = null;
    private final static String password = "Pavilion11";
    private final static String username = "root";
    
    //Estabelecer ligação
    
    public static UtilizadorDAO getInstance() {
        if (inst == null) {
            inst = new UtilizadorDAO();
        }
        return inst;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsKey(Object key) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",username,password)) {
            Statement stm = conn.createStatement();
            String sql = "SELECT email FROM Utilizador WHERE email ='"+(String)key+"'";
            ResultSet rs = stm.executeQuery(sql);
            return rs.next();
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Utilizador get(Object key) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",username,password)){
            Utilizador ut = null;
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM Utilizador WHERE email ='"+ (String)key +"'";
            ResultSet rs = st.executeQuery(sql);
        
            if(rs.next()){ 
                //vai buscar todos as instâncias da tabela pela sua ordem
                int idUtilizador= rs.getInt(1);
                String email = (String)key;
                String nome= rs.getString(4);
                String password= rs.getString(3);
                int tipoUtilizador= rs.getInt(5);
                
                //verificar se o utilizador é um administrador ou residente  ´
                if (tipoUtilizador == 1){
                    ut= new Administrador(idUtilizador,email,nome,password);
                }
                else if(tipoUtilizador == 2){
                    ut= new Residente(idUtilizador,email,nome,password);
                } 
            }
            return ut;
        }
        catch(Exception e) {
            throw new NullPointerException("Não existe nenhum Utilizador com o email " + (String)key + "");
        }
    }

    @Override
    public Utilizador put(String key, Utilizador value) {
        Utilizador ut = null;
        int tipoUtilizador = 0;
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",username,password)){
            Statement st = conn.createStatement();
            if(value instanceof Administrador){
                tipoUtilizador = 1;   
            } else {
                tipoUtilizador = 2;
            }
            if(this.containsKey(key)){
                this.replace(key,value);
            }else{
                String sql="INSERT INTO Utilizador VALUES ("+value.getIdUtilizador()+",'"+value.getEmail()+"','"+value.getPassword()+"','"+value.getNome()+"',"+tipoUtilizador+")";
                st.executeUpdate(sql);
            }
            if(tipoUtilizador == 1){
                ut = new Residente(value.getIdUtilizador(),value.getEmail(),value.getNome(),value.getPassword());
            }else{
                ut = new Administrador(value.getIdUtilizador(),value.getEmail(),value.getNome(),value.getPassword());
            }
            return ut;
        }catch(Exception e){
             throw new NullPointerException("Impossivel inserir Utilizador com email " + key);
        }
    }

    
    public Utilizador replace(String key, Utilizador value){
        Utilizador ut = null;
        int tipoUtilizador = 0;
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",username,password)){
            Statement st = conn.createStatement();
            if(value instanceof Administrador){
                tipoUtilizador = 1;   
            } else {
                tipoUtilizador = 2;
            }
            if(this.containsKey(key)){
                String sql="REPLACE INTO Utilizador VALUES ("+value.getIdUtilizador()+",'"+value.getEmail()+"','"+value.getPassword()+"','"+value.getNome()+"',"+tipoUtilizador+")";
                st.executeUpdate(sql);
            }
            if(tipoUtilizador == 1){
                ut = new Residente(value.getIdUtilizador(),value.getEmail(),value.getNome(),value.getPassword());
            }else{
                ut = new Administrador(value.getIdUtilizador(),value.getEmail(),value.getNome(),value.getPassword());
            }
            return ut;
        }catch(Exception e){
             throw new NullPointerException("Impossivel inserir Utilizador com email " + key);
        }
    }
    
    
    @Override
    public Utilizador remove(Object key) {
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",username,password)){
            Utilizador ut=null;
            Statement st = conn.createStatement();
            String sql= "DELETE FROM Utilizador WHERE email = '"+ (String)key +"'";
            st.executeUpdate(sql);
            return ut;
        }catch(Exception e){
           throw new NullPointerException("Impossivel remover Utilizador."); 
        }
    }

    @Override
    public void putAll(Map<? extends String, ? extends Utilizador> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Utilizador> values() {            
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",username,password)){
            //conn= this.connect();
            String sql = "SELECT * FROM Utilizador";
            //criar query
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Collection<Utilizador> lista= new HashSet<>();
            while (rs.next()){ 
                //vai buscar todos as instâncias da tabela pela sua ordem
                Utilizador ut;
                int idUtilizador= rs.getInt(1);
                String email= rs.getString(2);
                String password= rs.getString(3);
                String nome= rs.getString(4);
                int tipoUtilizador= rs.getInt(5);
                
                //verificar se o utilizador é um administrador ou residente  
                if (tipoUtilizador == 1){
                    ut= new Administrador(idUtilizador,email,nome,password);
                }
                else{
                    ut= new Residente(idUtilizador,email,nome,password);
                }
                
                lista.add(ut);  
            }
            return lista;
        }
        catch (Exception e) { 
            throw new NullPointerException("Impossivel obter todos os utilizadores.");
        }
    }

    @Override
    public Set<Entry<String, Utilizador>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int maxIdUtilizador(){
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",username,password)){
            Statement st = conn.createStatement();
            String sql = "SELECT max(idUtilizador) FROM Utilizador";
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        }
        catch (Exception e) {
            throw new NullPointerException("Operação impossível!");
        }
    }
    
}
