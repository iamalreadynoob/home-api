package tools;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Instance
{
    private Object data;
    private Object[] dataPile;
    public Instance(Object data)
    {
        this.data = data;
        dataPile = null;
    }
    public Instance(Object... dataPile)
    {
        this.dataPile = dataPile;
        data = null;
    }

    public Instance init()
    {
        Object nested = null;

        if (data instanceof Map<?,?> map)
        {
            Set<?> keys = map.keySet();
            for (Object k: keys)
            {
                if (k instanceof String)
                {
                    nested = map.get(k);
                    break;
                }
            }
        }

        return new Instance(nested);
    }

    public boolean isMapStringString()
    {
        boolean flag = false;

        if (data != null && data instanceof Map<?,?>)
        {
            Map<?,?> map = (Map<?, ?>) data;
            if (map.isEmpty() ||
            map.entrySet().stream().allMatch(entry -> entry.getKey() instanceof String && entry.getValue() instanceof String))
                flag = true;
        }

        return flag;
    }

    public boolean isArrayListString()
    {
        boolean flag = false;

        if (data != null && data instanceof ArrayList<?>)
        {
            ArrayList<?> arr = (ArrayList<?>) data;

            if (arr.isEmpty() || arr.get(0) instanceof String) flag = true;
        }

        return flag;
    }

    public boolean isMapStringArrayListString()
    {
        boolean flag = false;

        if (data != null && data instanceof Map<?,?>)
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

    public boolean isMapStringMapStringString()
    {
        boolean flag = false;

        if (data != null && data instanceof Map<?,?> map)
        {
            if (map.isEmpty()) flag = true;
            else
            {
                Set<?> keys = map.keySet();
                for (Object k: keys)
                {
                    if (k instanceof String && map.get(k) instanceof Map<?,?> nestedMap)
                    {
                        flag = new Instance(nestedMap).isMapStringString();
                        break;
                    }
                }
            }

        }

        return flag;
    }

    public boolean isAllString()
    {
        boolean flag = true;

        for (Object obj: dataPile)
        {
            if (!(obj instanceof String))
            {
                flag = false;
                break;
            }
        }

        return flag;
    }

}
