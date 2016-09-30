package mphantom.com.passwardmanager.ForegroundParse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wushaorong on 16-9-23.
 */
public class ForegroundParse {
    public static List<String> packageName = new ArrayList<>();
    public static List<String> className = new ArrayList<>();

    public static void parse(String pack, String cla) {
        if (packageName.contains(pack)) {
            return;
        }
        if (className.contains(cla)) {
            return;
        }
        // TODO: 16-9-23  
    }
}
