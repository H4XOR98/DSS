/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Conteudo;
import Business.Musica;
import Business.Video;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author lazaropinheiro
 */
public class ConteudoDAO {
    private static ConteudoDAO inst = null;
    private final static String password = "Pavilion11";
    private final static String username = "root";
    
    
    
    public static ConteudoDAO getInstance() {
        if (inst == null) {
            inst = new ConteudoDAO();
        }
        return inst;
    }
    
    public void clear () {
       try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",username,password)){
            Statement st = conn.createStatement();
            String sql = "SELECT Conteudo_idConteudo, Categoria_idCategoria, Utilizador_idUtilizador FROM Colecao";
            ResultSet rs = st.executeQuery(sql);
            Statement st2  = conn.createStatement();
            while(rs.next()){
                int idConteudo = rs.getInt(1);
                int idCategoria = rs.getInt(2);
                int idUtilizador = rs.getInt(3);
                st2.executeUpdate("DELETE FROM Colecao WHERE Conteudo_idConteudo = " + idConteudo + " AND Categoria_idCategoria = " + idCategoria + " AND  Utilizador_idUtilizador = " + idUtilizador);
            }
            rs = st.executeQuery("SELECT idConteudo FROM Conteudo");
            while(rs.next()){
                int idConteudo = rs.getInt(1);
                st2.executeUpdate("DELETE FROM Conteudo WHERE idConteudo = " + idConteudo);
            }
            rs = st.executeQuery("SELECT idCategoria FROM Categoria");
            while(rs.next()){
                int idCategoria = rs.getInt(1);
                st2.executeUpdate("DELETE FROM Categoria WHERE idCategoria = " + idCategoria);
            }
       }catch (Exception e) {
            throw new NullPointerException("Operação impossível!");
       } 
    }
                
               /* st2.executeUpdate("DELETE FROM Colecao WHERE Conteudo_idConteudo = " + idConteudo + " AND Categoria_idCategoria = " + idCategoria + " AND  Utilizador_idUtilizador = " + idUtilizador);
                rs2 = st2.executeQuery("SELECT * Conteudo WHERE idConteudo = " + idConteudo);
                rs2.next();
                if(rs2.next()){
                    st2.executeUpdate("DELETE FROM Conteudo WHERE idConteudo = " + idConteudo);
                    rs2 = st2.executeQuery("SELECT * Categoria WHERE idCategoria = " + idCategoria);
                     if(rs2.next()){
                        st2.executeUpdate("DELETE FROM Categoria WHERE idCategoria = " + idCategoria);
                    }
                }
                
            }
        }
        catch (Exception e) {
            throw new NullPointerException("Operação impossível!");
        } 
    }*/

    /*Funcional*/
    public List<Conteudo> getConteudosUtilizador(int idUtilizador) {
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",username,password)){
            Statement st = conn.createStatement();
            List<Conteudo> conteudos = new ArrayList<>();
            Statement stm1 = conn.createStatement();
            Statement stm2 = conn.createStatement();
            ResultSet rs = stm1.executeQuery("SELECT Conteudo_idConteudo, Categoria_idCategoria FROM Colecao WHERE Utilizador_idUtilizador = " + idUtilizador);
            Conteudo conteudo;
            while (rs.next()){
                int idConteudo = rs.getInt(1);
                int idCategoria = rs.getInt(2);
                
                ResultSet rs2 = stm2.executeQuery("SELECT * FROM Conteudo WHERE idConteudo =" + idConteudo);
                rs2.next();
                String titulo = rs2.getString(2);
                String autor = rs2.getString(3);
                String path = rs2.getString(4);
                int tipoConteudo = rs2.getInt(5);
                rs2 = stm2.executeQuery("SELECT designacao FROM Categoria WHERE idCategoria =" + idCategoria);
                rs2.next();
                String categoria = rs2.getString(1);
                //verificar se o utilizador é um administrador ou residente  
                if (tipoConteudo == 1){
                    conteudo = new Musica(idConteudo,titulo,autor,path,categoria);
                }
                else{
                    conteudo = new Video(idConteudo,titulo,autor,path,categoria);
                }
                conteudos.add(conteudo); 
            }            
            return conteudos;
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    /*Funcional*/
    public void removeConteudosUtilizador(int idUtilizador) {
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",username,password)){
            Statement st = conn.createStatement();
            String sql = "SELECT Conteudo_idConteudo FROM (Colecao LEFT OUTER JOIN Categoria ON Colecao.Categoria_idCategoria = Categoria.idCategoria) WHERE Colecao.Utilizador_idUtilizador = " + idUtilizador;
            ResultSet rs = st.executeQuery(sql);
            int idConteudo;
            while(rs.next()){
                idConteudo = rs.getInt(1);
                if(this.conteudoDuplicado(idConteudo)){
                   st.executeUpdate("DELETE FROM Colecao WHERE Conteudo_idConteudo = " + idConteudo  + " AND Utilizador_idUtilizador = " + idUtilizador);
                }else{
                    this.removerConteudo(idConteudo);
                }
            }
        }
        catch (Exception e) {
            throw new NullPointerException("Operação impossível!");
        }
    }

    /*Implementado*/
    public List<Conteudo> getConteudos() {
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",username,password)){
            Statement st = conn.createStatement();
            List<Conteudo> conteudos = new ArrayList<>();
            Statement stm1 = conn.createStatement();
            Statement stm2 = conn.createStatement();
            ResultSet rs = stm1.executeQuery("SELECT * FROM Conteudo");
            Conteudo conteudo;
            while (rs.next()){
                int idConteudo = rs.getInt(1);
                String titulo = rs.getString(2);
                String autor = rs.getString(3);
                String path = rs.getString(4);
                int tipoConteudo = rs.getInt(5);
                int idCategoria = rs.getInt(6);
                ResultSet rs2 = stm2.executeQuery("SELECT designacao FROM Categoria WHERE idCategoria =" + idCategoria);
                rs2.next();
                String categoria = rs2.getString(1);
                //verificar se o utilizador é um administrador ou residente  
                if (tipoConteudo == 1){
                    conteudo = new Musica(idConteudo,titulo,autor,path,categoria);
                }
                else{
                    conteudo = new Video(idConteudo,titulo,autor,path,categoria);
                }
                conteudos.add(conteudo); 
            }            
            return conteudos;
        }
        catch (Exception e) {throw new NullPointerException("Impossível obter todo o conteudo");}
    }

    /*Implementado*/
    public void adicionaConteudo(int idUtilizador, Conteudo conteudo, boolean duplicado) {
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",username,password)){
            Statement st = conn.createStatement();
            int tipoConteudo;
            if(conteudo instanceof Musica){
                tipoConteudo = 1;   
            } else {
                tipoConteudo = 2;
            }
            int idCategoria;
            ResultSet rs;
            if(duplicado == false){
                rs = st.executeQuery("SELECT designacao FROM Categoria WHERE designacao = '"+ conteudo.getCategoria() +"'");
                if(rs.next() == false){
                    rs = st.executeQuery("SELECT count(idCategoria) FROM Categoria");
                    rs.next();
                    idCategoria = rs.getInt(1);
                    st.executeUpdate("INSERT INTO Categoria VALUES (" + idCategoria + ",'" + conteudo.getCategoria() + "')");
                }else{
                    rs = st.executeQuery("SELECT idCategoria FROM Categoria WHERE designacao = '" + conteudo.getCategoria() + "'");
                    rs.next();
                    idCategoria = rs.getInt(1);
                }
                st.executeUpdate("INSERT INTO Conteudo VALUES (" + conteudo.getIdConteudo() + ",'" + conteudo.getTitulo() + "','"  + conteudo.getAutor() + "','"+ conteudo.getPath() + "'," + tipoConteudo + "," + idCategoria + ")");
            }
            rs = st.executeQuery("SELECT idCategoria FROM Categoria WHERE designacao = '" + conteudo.getCategoria() + "'");
            rs.next();
            idCategoria = rs.getInt(1);
            rs = st.executeQuery("SELECT idColecao FROM Colecao WHERE Conteudo_idConteudo = " + conteudo.getIdConteudo() + " AND Utilizador_idUtilizador = " + idUtilizador + " AND Categoria_idCategoria = " + idCategoria);
            if(rs.next()){
                st.executeUpdate("DELETE FROM Colecao WHERE Conteudo_idConteudo = " + conteudo.getIdConteudo() + " AND Utilizador_idUtilizador = " + idUtilizador + " AND Categoria_idCategoria = " + idCategoria);   
            }
            st.executeUpdate("INSERT INTO Colecao VALUES (" + (this.maxIdColecao()+1) + "," + conteudo.getIdConteudo() + "," + idUtilizador + "," + idCategoria + ")");
        }catch(Exception e){
             throw new NullPointerException("Impossível carregar conteudo!");
        }
    }

    /*Funcional*/
    public void atualizaCategoria(int idUtilizador, int idConteudo, String categoriaAntiga, String novaCategoria) {
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",username,password)){
            Statement st = conn.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT idCategoria FROM Categoria WHERE designacao = '" + categoriaAntiga + "'");
            rs.next();
            int idCategoria = rs.getInt(1);
            rs = st.executeQuery("SELECT * FROM Colecao WHERE Categoria_idCategoria = " + idCategoria + " AND Conteudo_idConteudo = " + idConteudo + " AND Utilizador_idUtilizador = " + idUtilizador);
            if(rs.next()){
                st.executeUpdate("DELETE FROM Colecao WHERE Categoria_idCategoria = " + idCategoria + " AND Conteudo_idConteudo = " + idConteudo + " AND Utilizador_idUtilizador = " + idUtilizador);
                rs = st.executeQuery("SELECT designacao FROM Categoria WHERE designacao = '"+ novaCategoria +"'");
                if(rs.next() == false){
                    rs = st.executeQuery("SELECT count(idCategoria) FROM Categoria");
                    rs.next();
                    idCategoria = rs.getInt(1);
                    st.executeUpdate("INSERT INTO Categoria VALUES (" + idCategoria + ",'" + novaCategoria + "')");
                }else{
                    rs = st.executeQuery("SELECT idCategoria FROM Categoria WHERE designacao = '" + novaCategoria + "'");
                    rs.next();
                    idCategoria = rs.getInt(1);
                }
            }
            st.executeUpdate("INSERT INTO Colecao VALUES (" + (this.maxIdColecao()+1) + "," + idConteudo + "," + idUtilizador + "," + idCategoria + ")");
        }catch(Exception e){
            throw new NullPointerException("Impossível atualizar categoria!");
        }
    }

    /*Implementado*/
    public boolean conteudoDuplicado(int idConteudo) {
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",username,password)){
            Statement st = conn.createStatement();
            String sql = "SELECT count(Conteudo_idConteudo) FROM Colecao WHERE Conteudo_idConteudo = " + idConteudo;
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            return rs.getInt(1) > 1;
        }
        catch (Exception e) {
            throw new NullPointerException("Operação impossível!");
        }
    }

    /*Funcional*/
    public void removerConteudo(int idConteudo) {
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",username,password)){
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM Colecao WHERE Conteudo_idConteudo = " + idConteudo);
            st.executeUpdate("DELETE FROM Conteudo WHERE idConteudo = " + idConteudo);
        }
        catch (Exception e) {
            throw new NullPointerException("Operação impossível!");
        }
    }
    
    public int maxIdConteudo(){
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",username,password)){
            Statement st = conn.createStatement();
            String sql = "SELECT max(idConteudo) FROM Conteudo";
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        }
        catch (Exception e) {
            throw new NullPointerException("Operação impossível!");
        }
    }
    
    public int maxIdColecao(){
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",username,password)){
            Statement st = conn.createStatement();
            String sql = "SELECT max(idColecao) FROM Colecao";
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        }
        catch (Exception e) {
            throw new NullPointerException("Operação impossível!");
        }
    }
    
}
