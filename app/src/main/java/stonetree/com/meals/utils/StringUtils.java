package stonetree.com.meals.utils;

import stonetree.com.meals.constants.Constants;

public class StringUtils {

    public static String getQueryParams(String... params){
        final StringBuilder builder = new StringBuilder();
        for (String s: params) {
            if(s.contains(Constants.QUERY_SELECTOR)) {
                s = s.replace(Constants.QUERY_SELECTOR, Constants.EMPTY_SEQUENCE);
                builder.append("&" + s + "=");
            } else {
                builder.append(s);
            }
        }
        return builder.toString();
    }

}
