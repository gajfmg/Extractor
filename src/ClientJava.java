import com.br.gabriel.dao.AuthorDao;
import com.br.gabriel.dao.ConnectionFactory;
import com.br.gabriel.vo.TransRepoProjVO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import entity.Push;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientJava {

	public static void main(String[] args) throws ClientProtocolException, IOException, SQLException{
            HttpClient client;
            client = HttpClientBuilder.create().build();
            
            AuthorDao dao = new AuthorDao();

            HttpGet request = new HttpGet("https://api.github.com/repos/gajfmg/web-app/commits");
            HttpResponse response = client.execute(request);

            BufferedReader rd  = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            StringBuilder sb = new StringBuilder();
            while((line = rd.readLine())!= null) {
                    sb.append(line + "\n");
            }
            rd.close();

            Type collectionType = new TypeToken<List<Push>>(){}.getType();
            Gson gson = new Gson();
            @SuppressWarnings("unchecked")
            List<Push> lstPush = (List<Push>) gson.fromJson(sb.toString(), collectionType);

            for(Push push : lstPush){
                if(push != null ){
                    System.out.println("Nome:" + push.getCommit().getaAuthor().getName());
                    System.out.println("Data do commit:" +push.getCommit().getaAuthor().getDate());
                    System.out.println("Comentário:" +push.getCommit().getMessage());
                    System.out.println("\n");
                }
            }
            
            List<TransRepoProjVO> list = new ArrayList<>();
            
            for(Push push : lstPush){
                if(push != null ){
                    if (dao.recuperarIdAuthorPorNome(push.getCommit().getaAuthor().getName()) != null) {
                        TransRepoProjVO vo = new TransRepoProjVO();
                        vo.setIdDesenvolvedor(dao.recuperarIdAuthorPorNome(push.getCommit().getaAuthor().getName()));
                        
                        list.add(vo);
                    }
                }
            }
        }
}
