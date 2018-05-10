/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.gabriel.dao;

import com.br.gabriel.vo.TransRepoProjVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author gabriel
 */
public class TransRepoProjDao {
    
    Connection connection = null;
    private final String INSERIR = "insert into t_trans_repo_proj(sk_desenv, sk_repo_proj, sk_grnte_proj, id_dmsao_tempo, commit) values (?,?,?,?,?)";
    
    
    public TransRepoProjDao () {
        connection = new ConnectionFactory().getConnection();
    }
    
    public void insert (TransRepoProjVO vo) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERIR);
        ps.setInt(2,vo.getIdDesenvolvedor());
        ps.setInt(3,vo.getIdRepoProj());
        ps.setInt(4,vo.getIdGerenteProj());
        ps.setInt(5,vo.getIdDmsaoTempo());
        ps.setString(6, vo.getCommit());    

        ps.execute();
        ps.close();
        
    }
}
