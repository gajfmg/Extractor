/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.gabriel.dao;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gabriel
 */
public class DimensaoTempoDao {
    Connection connection = null;
    private final String RECUPERAR_ID_DATA_POR_DIA_MES_ANO 
            = "SELECT id_dmsao_tempo FROM t_dmsao_tempo "
                + "WHERE nu_dia_mvmt_fluxo_caixa = ? "
            + "and nu_mes_mvmt_fluxo_caixa = ? and nu_ano_mvmt_fluxo_caixa = ?";
    
    
    public DimensaoTempoDao () {
        connection = new ConnectionFactory().getConnection();
    }
    
    public Integer recuperarIdDataPorDiaMesAno (int dia, int mes, int ano) throws SQLException {
        PreparedStatement ps = (PreparedStatement) connection.prepareStatement(RECUPERAR_ID_DATA_POR_DIA_MES_ANO);
        ResultSet rs;
        ps.setInt(1, dia);
        ps.setInt(2, mes);
        ps.setInt(3, ano);
        rs = ps.executeQuery();
        
        Integer id = null;
        
        while (rs.next()) {
            id = rs.getInt("id_dmsao_tempo");
        }
        ps.close();
        return id;
    }
}
