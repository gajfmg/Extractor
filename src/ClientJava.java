import com.br.gabriel.service.ExtratorService;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import java.sql.SQLException;

public class ClientJava {

	public static void main(String[] args) throws ClientProtocolException, IOException, SQLException{
            ExtratorService service = new ExtratorService();            
            service.extrairDados();
        }
}
