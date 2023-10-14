package tools;

import java.util.Map;

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

}
