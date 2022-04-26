package coach.xfitness.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ParameterAsMapRequestWrapper extends HttpServletRequestWrapper {

    /** 
     * A constructor that takes an HttpServletRequest object and passes it to the super class. 
     */
    public ParameterAsMapRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * It takes a parameter name, finds all parameters that start with that name, and
     * returns a map of the parameter names and values. If the name follows this naming
     * structure:
     * 
     * <pre>
     * paramererName[firstKey][middleKeys][lastKey]
     * </pre>
     * 
     * It will be mapped as such:
     * 
     * <pre>
     * firstKey: {
     *      middleKeys: {
     *          lastKey: value
     *      }
     * }
     * </pre>
     * 
     * @param parameter The parameter name to get the map for.
     * @return A map of the parameters in the request.
     */
    public Map<String, Object> getParameterAsMap(String parameter) {
        Map<String, String[]> parameters = getParameterMapStartingWith(parameter);
        Map<String, Object> parameterAsMap = new HashMap<>();

        parameters.forEach((name, value) -> {
            if (name.contains("[") && hasBalancedNonNestedSquareBrackets(name)) {
                StringTokenizer tokenizedParameter = tokenizeBySquareBrakets(name);
                nestParameterInMap(parameterAsMap, tokenizedParameter, value);
            } else {
                parameterAsMap.put(name, value);
            }
        });

        return parameterAsMap;
    }

    /**
     * It takes a parameter name, and returns a map of all parameters that start with
     * that name
     * 
     * @param string The parameter name to filter by.
     * @return A map of all the parameters that start with the given parameter.
     */
    private Map<String, String[]> getParameterMapStartingWith(String string) {
        return this.getParameterMap().entrySet().stream()
                .filter(e -> e.getKey().startsWith(string))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * It returns true if the string contains balanced non-nested square brackets, and
     * false otherwise
     * 
     * @param string the string to check
     * @return The method returns true if the string has balanced non-nested square
     * brackets.
     */
    private static boolean hasBalancedNonNestedSquareBrackets(String string) {
        Stack<Character> stack = new Stack<>();

        for (Character character : string.toCharArray()) {
            if (character == '[') {
                stack.push(character);

                if (stack.size() > 1)
                    return false; // bracket is nested

                continue;
            }

            if (character == ']') {
                if (stack.isEmpty())
                    return false; // bracked is unmatched

                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    /**
     * It replaces all the closing square brackets with nothing, and then tokenizes the
     * string by the opening square brackets
     * 
     * @param string The string to be tokenized.
     * @return A StringTokenizer object.
     */
    private static StringTokenizer tokenizeBySquareBrakets(String string) {
        return new StringTokenizer(string.replace("]", ""), "[");
    }

    /**
     * It nests the given parameter value in the given map, by mapping it with the
     * given tokens of the name in the following format:
     * 
     * <pre>
     * firstToken: {
     *      middleTokens: {
     *          lastToken: value
     *      }
     * }
     * </pre>
     * 
     * @param map The map that will be returned.
     * @param tokens The tokens of the parameter name.
     * @param value The value of the parameter.
     * @return A map of maps.
     */
    private static Map<String, Object> nestParameterInMap(
            Map<String, Object> map, StringTokenizer tokens, String[] value) {
        String token = tokens.nextToken();

        if (tokens.countTokens() == 0) {
            map.put(token, value);
            return map;
        }

        if (map.containsKey(token)) {
            @SuppressWarnings("unchecked")
            Map<String, Object> existingMap = (Map<String, Object>) map.get(token);
            map.put(token, nestParameterInMap(existingMap, tokens, value));
        } else {
            Map<String, Object> newMap = new HashMap<>();
            map.put(token, nestParameterInMap(newMap, tokens, value));
        }

        return map;
    }
}
