/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.gabriel.service;

import com.br.gabriel.dao.AuthorDao;
import com.br.gabriel.dao.DimensaoTempoDao;
import com.br.gabriel.dao.ExtractorDao;
import com.br.gabriel.dao.GerenteDao;
import com.br.gabriel.dao.ProjetoDao;
import com.br.gabriel.vo.TransRepoProjVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Owner;
import entity.Project;
import entity.Push;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
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

    private final AuthorDao authorDao = new AuthorDao();
    private final DimensaoTempoDao dimensaoTempoDao = new DimensaoTempoDao();
    private final GerenteDao gerenteDao = new GerenteDao();
    private final ProjetoDao projetoDao = new ProjetoDao();
    private final ExtractorDao extractorDao = new ExtractorDao();
    private final String login_github ="gajfmg";
    private final String nome_projeto ="WEB-APP";
    private final String URL_PADRAO = "https://api.github.com/repos/"+login_github+"/"+nome_projeto;
    private final String URL_COMMITS = "/commits";
    private List<TransRepoProjVO> list = new ArrayList<>();
    private List<Push> lstPush = null;
    private Owner owner = null;
    private Project project = null;
    
    
    private void inicializarObjetos() throws IOException {
        Type collectionType = new TypeToken<List<Push>>(){}.getType();
            Type objectType = new TypeToken<Owner>(){}.getType();
            Type type  = new TypeToken<Project>(){}.getType();          
            Gson gson = new Gson();           
            lstPush = (List<Push>) gson.fromJson(recuperarJsonPorUrl(URL_PADRAO + URL_COMMITS).toString(), collectionType);            
            owner = gson.fromJson(recuperarJsonPorUrl(URL_PADRAO).toString(), objectType);                  
            owner = gson.fromJson(recuperarJsonPorUrl(owner.getOwner().getUrl()).toString(), objectType);            
            project = gson.fromJson(recuperarJsonPorUrl(URL_PADRAO).toString(), type);
    }
    
    private StringBuilder recuperarJsonPorUrl(String url) throws IOException {
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

    private Integer extrairIdAuthorPorNome(Push push) throws SQLException {
        return authorDao.recuperarIdAuthorPorNome(push.getCommit().getAuthor().getName());
    }
    
    private Integer extrairIdGerentePorNome(Owner owner) throws SQLException {
        return gerenteDao.recuperarIdGerentePorNome(owner.getName());
    }
    
     private Integer extrairIdProjetoPorNome(Project project) throws SQLException {
        return projetoDao.recuperarIdProjetoPorNome(project.getName());
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
    
    private void salvarDados() {
        list.forEach(vo -> extractorDao.salvarDados(vo));
    }
    
    public void extrairDados() throws SQLException, IOException {
        inicializarObjetos();
        for(Push push : lstPush){
            if(push != null){                
                if (extrairIdAuthorPorNome(push) != null) {
                    TransRepoProjVO vo = new TransRepoProjVO();
                    vo.setIdDesenvolvedor(extrairIdAuthorPorNome(push));
                    vo.setCommit(push.getCommit().getMessage());
                    vo.setIdDmsaoTempo(extrairIdDmsaoTempo(push));
                    vo.setIdGerenteProj(extrairIdGerentePorNome(owner));
                    vo.setIdRepoProj(extrairIdProjetoPorNome(project));
                    list.add(vo);
                }
            }
        }
        salvarDados();
    }
}
