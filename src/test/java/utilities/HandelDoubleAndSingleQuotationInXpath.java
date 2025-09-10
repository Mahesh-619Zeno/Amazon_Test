package utilities;

public class HandelDoubleAndSingleQuotationInXpath {

    /**
     * Escapes a string to be safely used inside an XPath expression.
     * Handles input containing single quotes, double quotes, or both.
     *
     * @param input the input string to escape
     * @return a properly escaped XPath string
     */
    public String escapeForXPath(String input) {
        if (input == null || input.isEmpty()) {
            return "\"\""; // empty XPath string
        }

        // Case 1: Input contains both single and double quotes
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
        }

        // Case 2: Input contains only double quotes
        if (input.contains("\"")) {
            return "'" + input + "'";
        }

        // Case 3: Input contains only single quotes or no quotes
        return "\"" + input + "\"";
    }
}
