package tools;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Instance
{

    public static boolean isMapStringString(Object data)
    {
        boolean flag = false;

        if (data instanceof Map<?,?>)
        {
            Map<?,?> map = (Map<?, ?>) data;
            if (map.isEmpty() ||
            map.entrySet().stream().allMatch(entry -> entry.getKey() instanceof String && entry.getValue() instanceof String))
                flag = true;
        }

        return flag;
    }

    public static boolean isArrayListString(Object data)
    {
        boolean flag = false;

        if (data instanceof ArrayList<?>)
        {
            ArrayList<?> arr = (ArrayList<?>) data;

            if (arr.isEmpty() || arr.get(0) instanceof String) flag = true;
        }

        return flag;
    }

    public static boolean isMapStringArrayListString(Object data)
    {
        boolean flag = false;

        if (data instanceof Map<?,?>)
        {
            Map<?,?> map = (Map<?, ?>) data;

            if (map.isEmpty()) flag = true;
            else
            {
                Set<?> keys = map.keySet();
                for (Object k: keys)
                {
                    if (k instanceof String && map.get(k) instanceof ArrayList<?>)
                    {
                        ArrayList<?> arr = (ArrayList<?>) map.get(k);
                        if (arr.isEmpty() || arr.get(0) instanceof String)
                        {
                            flag = true;
                            break;
                        }
                    }
                    else break;
                }
            }
        }

        return flag;
    }

    public static boolean isMapStringMapStringString(Object data)
    {
        boolean flag = false;



        return flag;
    }

}
