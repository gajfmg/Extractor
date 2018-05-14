/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.gabriel.dao;

import com.br.gabriel.vo.TransRepoProjVO;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class ExtractorDao {
    Connection connection = null;
    private final String INCLUIR_DADOS_EXTRAIDOS = 
            "INSERT INTO t_trans_repo_proj (sk_desenv, sk_repo_proj, sk_grnte_proj, id_dmsao_tempo, commit ) VALUES (?,?,?,?,?)";
    
    
    public ExtractorDao () {
        connection = new ConnectionFactory().getConnection();
    }
    
    public void salvarDados (TransRepoProjVO vo) {
        
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) connection.prepareStatement(INCLUIR_DADOS_EXTRAIDOS);
            ResultSet rs;
            ps.setInt(1, vo.getIdDesenvolvedor());
            ps.setInt(2, vo.getIdRepoProj());
            ps.setInt(3, vo.getIdGerenteProj());
            ps.setInt(4, vo.getIdDmsaoTempo());
            ps.setString(5, vo.getCommit());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExtractorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
