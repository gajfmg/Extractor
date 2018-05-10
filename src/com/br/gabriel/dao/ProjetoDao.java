/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.gabriel.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Gabriel
 */
public class ProjetoDao {
    Connection connection = null;
    private final String RECUPERAR_ID_PROJETO_POR_NOME = "SELECT sk_repo_proj FROM t_repo_proj WHERE ds_repo_proj = '";
    
    
    public ProjetoDao () {
        connection = new ConnectionFactory().getConnection();
    }
    
    public Integer recuperarIdProjetoPorNome (String nome) throws SQLException {
        Statement ps = connection.createStatement();
        ResultSet rs;
        
        rs = ps.executeQuery(RECUPERAR_ID_PROJETO_POR_NOME + nome + "'");
        
        Integer id = null;
        
        while (rs.next()) {
            id = rs.getInt("sk_repo_proj");
        }
        ps.close();
        return id;
    }
}
