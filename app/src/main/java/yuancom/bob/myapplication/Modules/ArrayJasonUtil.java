package yuancom.bob.myapplication.Modules;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by bob on 08/08/2017.
 */

public class ArrayJasonUtil {



        public static ArrayList<Object> convert(JSONArray jArr)
        {
            ArrayList<Object> list = new ArrayList<>();
            try {
                for (int i=0, l=jArr.length(); i<l; i++){
                    list.add(jArr.get(i));
                }
            } catch (JSONException e) {}

            return list;
        }

        public static JSONArray convert(Collection<Object> list)
        {
            return new JSONArray(list);
        }


}
