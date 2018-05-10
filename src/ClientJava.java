import com.br.gabriel.service.ExtratorService;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Owner;
import entity.Project;


import entity.Push;
import java.sql.SQLException;

public class ClientJava {

	public static void main(String[] args) throws ClientProtocolException, IOException, SQLException{

            String URL_PADRAO = "https://api.github.com/repos/gajfmg/web-app";
            String URL_COMMITS = "/commits";
            
            ExtratorService service = new ExtratorService();
            
            Type collectionType = new TypeToken<List<Push>>(){}.getType();
            Type objectType = new TypeToken<Owner>(){}.getType();
            Type type  = new TypeToken<Project>(){}.getType();
            
           
            Gson gson = new Gson();
            
            List<Push> lstPush = (List<Push>) gson.fromJson(service.recuperarJsonPorUrl(URL_PADRAO + URL_COMMITS).toString(), collectionType);
            
            Owner owner = gson.fromJson(service.recuperarJsonPorUrl(URL_PADRAO).toString(), objectType);
                  
            owner = gson.fromJson(service.recuperarJsonPorUrl(owner.getOwner().getUrl()).toString(), objectType);
            
            Project project = gson.fromJson(service.recuperarJsonPorUrl(URL_PADRAO).toString(), type);
                  
            project = gson.fromJson(service.recuperarJsonPorUrl(project.getName()).toString(), type);
        
            
            service.extrairDados(lstPush, owner, project);
        }
}
