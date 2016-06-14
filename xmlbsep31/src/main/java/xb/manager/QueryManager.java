package xb.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.validation.Schema;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.eval.EvalResult;
import com.marklogic.client.eval.EvalResultIterator;
import com.marklogic.client.eval.ServerEvaluationCall;

import xb.conversion.JaxbXMLConverter;

public class QueryManager<T> {
	
	private DatabaseClient client;
	private ServerEvaluationCall invoker;
	private JaxbXMLConverter<T> converter;
	private Schema schema;
	
	public QueryManager(DatabaseClient client, JaxbXMLConverter<T> converter, Schema schema) {
		this.client = client;
		this.invoker = client.newServerEval();
		this.converter = converter;
		this.schema = schema;
	}
	
	/**
	 * Metoda koja izvrsava zadati upit na MarkLogic serveru.
	 * @param query upit koji zelimo da se izvrsi
	 * @return listu rezultata koji zadovoljavaju prosledjeni upit
	 */
	public ArrayList<T> executeQuery(String query) {
		ArrayList<T> retVal = null;
        try {
            EvalResultIterator response = null;
            // Invoke the query
            invoker.xquery(query);
            response = invoker.eval();
            retVal = new ArrayList<>();
            for (EvalResult result : response) {
                if(converter.writeStringToXML(result.getString())) {
                    T object = converter.unmarshalling("tmp.xml", schema);
                    if (object != null)
                        retVal.add(object);
                }
            }
        } catch (Exception e){
            System.out.println("Unexpected error: " + e.getMessage());
        }
        return retVal;
	}

}
