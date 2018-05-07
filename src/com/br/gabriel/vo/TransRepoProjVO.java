/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.gabriel.vo;

/**
 *
 * @author gabriel
 */
public class TransRepoProjVO {
    
    private int id;
    private int idDesenvolvedor;
    private int idRepoProh;
    private int idGerenteProj;
    private int idDmsaoTempo;
    private String commit;

    /**
     * Este atributo é auto incremento.
     * @return 
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Este atributo é recuperado da tabela TAL.
     * @return 
     */
    public int getIdDesenvolvedor() {
        return idDesenvolvedor;
    }

    public void setIdDesenvolvedor(int idDesenvolvedor) {
        this.idDesenvolvedor = idDesenvolvedor;
    }

    public int getIdRepoProh() {
        return idRepoProh;
    }

    public void setIdRepoProh(int idRepoProh) {
        this.idRepoProh = idRepoProh;
    }

    public int getIdGerenteProj() {
        return idGerenteProj;
    }

    public void setIdGerenteProj(int idGerenteProj) {
        this.idGerenteProj = idGerenteProj;
    }

    public int getIdDmsaoTempo() {
        return idDmsaoTempo;
    }

    public void setIdDmsaoTempo(int idDmsaoTempo) {
        this.idDmsaoTempo = idDmsaoTempo;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }
    
    
}
