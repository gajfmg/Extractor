import com.br.gabriel.service.ExtratorService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import entity.Push;
import java.sql.SQLException;

public class ClientJava {

	public static void main(String[] args) throws ClientProtocolException, IOException, SQLException{

            String URL_PADRAO = "https://api.github.com/repos/gajfmg/web-app";
            String URL_COMMITS = "/commits";
            
            ExtratorService service = new ExtratorService();
            
            Type collectionType = new TypeToken<List<Push>>(){}.getType();
            Gson gson = new Gson();
            
            List<Push> lstPush = (List<Push>) gson.fromJson(service.recuperarJsonPorUrl(URL_PADRAO + URL_COMMITS).toString(), collectionType);

            
            
            service.extrairDados(lstPush);
            
//            for(Push push : lstPush){
//                if(push != null ){
//                    System.out.println("Nome:" + push.getCommit().getaAuthor().getName());
//                    System.out.println("Data do commit:" +push.getCommit().getaAuthor().getDate());
//                    System.out.println("Comentário:" +push.getCommit().getMessage());
//                    System.out.println("\n");
//                }
//            }
        }
}
