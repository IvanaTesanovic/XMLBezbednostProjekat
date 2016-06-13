package xb.database;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;

public class DatabaseConnection
{
	
	public static String USERS_DOC_ID = "/Korisnici.xml";
	public static String USERS_COL_ID = "/ColKorisnici";
	
	public static String AKT_DOC_ID = "/Akt";
	public static String AKT_COL_ID = "/ColAkati";
	
	public static String USV_AKT_COL_ID = "/ColUsvojeniAkati";
	
	public static String AMD_DOC_ID = "/Amandman";
	public static String AMD_COL_ID = "/ColAmandmani";
	
	public static String ID_SUFFIX = ".xml";

    private static DatabaseClient client = null;

    public static DatabaseClient getDbClient() {
        if (client == null){
            client = initializeClient();
        }

        return client;
    }

    /**
     * Inicijalizacija konekcije kroz parametre.
     * @return
     */
    @SuppressWarnings("finally")
	private static DatabaseClient initializeClient(){
        Util.ConnectionProperties props = null;
        DatabaseClient client = null;
        try {
            props = Util.loadProperties();
            client = DatabaseClientFactory.newClient(props.host, props.port, props.database, props.user, props.password, props.authType);
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            return client;
        }

    }

}
