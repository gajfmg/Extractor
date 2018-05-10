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
    private int idRepoProj;
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

    public int getIdRepoProj() {
        return idRepoProj;
    }

    public void setIdRepoProj(int idRepoProh) {
        this.idRepoProj = idRepoProh;
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
