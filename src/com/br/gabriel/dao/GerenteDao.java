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
 * @author gabriel
 */
public class GerenteDao {
    Connection connection = null;
    private final String RECUPERAR_ID_GERENTE_POR_NOME = "SELECT sk_grnte_proj FROM t_grnte_proj WHERE nm_grnte_proj = '";
    
    
    public GerenteDao () {
        connection = new ConnectionFactory().getConnection();
    }
    
    public Integer recuperarIdGerentePorNome (String nome) throws SQLException {
        Statement ps = connection.createStatement();
        ResultSet rs;
        
        rs = ps.executeQuery(RECUPERAR_ID_GERENTE_POR_NOME + nome + "'");
        
        Integer id = null;
        
        while (rs.next()) {
            id = rs.getInt("sk_grnte_proj");
        }
        ps.close();
        return id;
    }
}
