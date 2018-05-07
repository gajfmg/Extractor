/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.gabriel.dao;

import com.br.gabriel.vo.TransRepoProjVO;
import entity.Author;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gabriel
 */
public class AuthorDao {
    Connection connection = null;
    private final String RECUPERAR_ID_AUTOR_POR_NOME = "SELECT sk_desenv FROM t_desenv WHERE nm_desenv = '";
    
    
    public AuthorDao () {
        connection = new ConnectionFactory().getConnection();
    }
    
    public Integer recuperarIdAuthorPorNome (String nome) throws SQLException {
        Statement ps = connection.createStatement();
        ResultSet rs;
        
        rs = ps.executeQuery(RECUPERAR_ID_AUTOR_POR_NOME + nome + "'");
        
        Integer id = null;
        
        while (rs.next()) {
            id = rs.getInt("sk_desenv");
        }
        ps.close();
        return id;
    }
}
