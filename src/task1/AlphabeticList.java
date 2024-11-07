package task1;

import java.util.ArrayList;
import java.util.Collections;

public class AlphabeticList extends ArrayList<String> {
    public boolean add(String s) {
        super.add(s);
        Collections.sort(this);
        return true;
    }

}
