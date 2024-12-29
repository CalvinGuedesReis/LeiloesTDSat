/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        conn = new conectaDAO().connectDB();
          
          try {
              conn.getTransaction().begin();
              conn.persist(produto);
              conn.getTransaction().commit();
          }catch(Exception e){
              conn.getTransaction().rollback();
              throw e;
          }
          finally{
             conectaDAO();
          }
      }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
    conn = conectaDAO().connectDB(); //Abrir conexão
  List produtos = null;
    try{
        //pesquisa, consulta JPQL
        String textoQuery = "select produto from produtoDTO produto"
                + " WHERE (:descricaoProdutor is null OR d.descricao LIKE :descricaoFiltro)";
        
        Query consulta = conn.createQuery(textoQuery); //implementação da consulta
        
        consulta.setParameter("descricaoFiltro", filtroProdutor.isEmpty() ? null : "%" + filtroProdutor + "%");
        
        listagem = consulta.getResultList(); //executa, consulta e obtem lista
        
    }finally{
             conectaDAO().connectDB(); //finalização
             }    

    return listagem;
    }
    
    
    
        
}

