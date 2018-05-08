/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.gabriel.service;

import com.br.gabriel.dao.AuthorDao;
import com.br.gabriel.dao.DimensaoTempoDao;
import com.br.gabriel.dao.GerenteDao;
import com.br.gabriel.vo.TransRepoProjVO;
import entity.Owner;
import entity.Push;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author gabriel
 */
public class ExtratorService {

    private List<TransRepoProjVO> list = new ArrayList<>();
    private final AuthorDao authorDao = new AuthorDao();
    private final DimensaoTempoDao dimensaoTempoDao = new DimensaoTempoDao();
    private final GerenteDao gerenteDao = new GerenteDao();
    
    public StringBuilder recuperarJsonPorUrl(String url) throws IOException {
            HttpClient client;
            client = HttpClientBuilder.create().build();

            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            BufferedReader rd  = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            StringBuilder sb = new StringBuilder();
            while((line = rd.readLine())!= null) {
                    sb.append(line + "\n");
            }
            rd.close();
            
            return sb;
    }
    
    public void extrairDados(List<Push> lstPush, Owner owner) throws SQLException {
        for(Push push : lstPush){
            if(push != null ){                
                if (extrairIdAuthorPorNome(push) != null) {
                    TransRepoProjVO vo = new TransRepoProjVO();
                    vo.setIdDesenvolvedor(extrairIdAuthorPorNome(push));
                    vo.setCommit(push.getCommit().getMessage());
                    vo.setIdDmsaoTempo(extrairIdDmsaoTempo(push));
                    vo.setIdGerenteProj(extrairIdGerentePorNome(owner));
                    list.add(vo);
                }
            }
        }
    }

    private Integer extrairIdAuthorPorNome(Push push) throws SQLException {
        return authorDao.recuperarIdAuthorPorNome(push.getCommit().getAuthor().getName());
    }
    
    private Integer extrairIdGerentePorNome(Owner owner) throws SQLException {
        return gerenteDao.recuperarIdGerentePorNome(owner.getName());
    }

    private Integer extrairIdDmsaoTempo(Push push) throws SQLException {
        Date dataCommit = push.getCommit().getAuthor().getDate();
        Calendar c = Calendar.getInstance();
        c.setTime(dataCommit);
        Integer dia = c.get(Calendar.DAY_OF_MONTH);
        Integer mes = c.get(Calendar.MONTH);
        Integer ano = c.get(Calendar.YEAR);
        
        return dimensaoTempoDao.recuperarIdDataPorDiaMesAno(dia, mes + 1, ano);
    }
    
}
