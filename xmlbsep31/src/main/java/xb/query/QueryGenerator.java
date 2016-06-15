package xb.query;

public class QueryGenerator {
	
	public QueryGenerator() {}
	
	public static String getFilesInColl(String collId) {
		StringBuilder query = new StringBuilder();
	    query.append("fn:collection(\"");
	    query.append(collId);
	    query.append("\")");
		return query.toString();
	}

}
