package utilities;

public class HandelDoubleAndSingleQuotationInXpath {
	
	public String escapeForXPath(String input) {
	    if (input.contains("\"") && input.contains("'")) {
	       
	        String[] parts = input.split("\"");
	        StringBuilder xpath = new StringBuilder("concat(");
	        for (int i = 0; i < parts.length; i++) {
	            xpath.append("\"").append(parts[i]).append("\"");
	            if (i != parts.length - 1) {
	                xpath.append(", '\"', ");
	            }
	        }
	        xpath.append(")");
	        return xpath.toString();
	    } else if (input.contains("\"")) {
	        return "'" + input + "'";
	    } else {
	        return "\"" + input + "\"";
	    }
	}

	
	
}
