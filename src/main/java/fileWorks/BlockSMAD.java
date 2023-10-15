package fileWorks;

import tools.Instance;

import java.util.ArrayList;
import java.util.Map;

public class BlockSMAD
{

    BlockTypes type;

    //for type 0
    private String value;

    //for type 1
    private Map<String, String> valueSet;

    //for type 2
    private ArrayList<String> objectNames;
    private Map<String, String> plainValues;
    private Map<String, Map<String, String>> plainTaggedValues;
    private Map<String, Map<String, String>> simpleValues;
    private Map<String, Map<String, ArrayList<String>>> listValues;
    private Map<String, Map<String, Map<String, String>>> taggedValues;

    public BlockSMAD(String value)
    {
        type = BlockTypes.TYPE_0;
        this.value = value;
    }

    public BlockSMAD(Map<String, String> valueSet)
    {
        type = BlockTypes.TYPE_1;
        this.valueSet = valueSet;
    }

    public BlockSMAD(ArrayList<String> objectNames, Map<String, String> plainValues, Map<String, Map<String, String>> plainTaggedValues, Map<String, Map<String, String>> simpleValues, Map<String, Map<String, ArrayList<String>>> listValues, Map<String, Map<String, Map<String, String>>> taggedValues)
    {
        type = BlockTypes.TYPE_2;
        this.objectNames = objectNames;
        this.plainValues = plainValues;
        this.plainTaggedValues = plainTaggedValues;
        this.simpleValues = simpleValues;
        this.listValues = listValues;
        this.taggedValues = taggedValues;
    }

    public BlockTypes getType() {return type;}

    //append, set, get, delete
    public void set(String value)
    {
        if (type == BlockTypes.TYPE_0) this.value = value;
    }

    public void set(Map<String, String> valueSet)
    {
        if (type == BlockTypes.TYPE_1) this.valueSet = valueSet;
    }

    public void set(ArrayList<String> objectNames, Map<String, String> plainValues, Map<String, Map<String, String>> plainTaggedValues, Map<String, Map<String, String>> simpleValues, Map<String, Map<String, ArrayList<String>>> listValues, Map<String, Map<String, Map<String, String>>> taggedValues)
    {
        if (type == BlockTypes.TYPE_2)
        {
            this.objectNames = objectNames;
            this.plainValues = plainValues;
            this.plainTaggedValues = plainTaggedValues;
            this.simpleValues = simpleValues;
            this.listValues = listValues;
            this.taggedValues = taggedValues;
        }
    }

    public void set(TypeTwoLocations location, Object data)
    {

        if (type == BlockTypes.TYPE_2)
        {
            switch (location)
            {
                case LIST_VALUES:
                    if (new Instance(data).init().isMapStringArrayListString()) listValues = (Map<String, Map<String, ArrayList<String>>>) data;
                    break;
                case OBJECT_NAMES:
                    if (new Instance(data).init().isArrayListString()) objectNames = (ArrayList<String>) data;
                    break;
                case PLAIN_VALUES:
                    if (new Instance(data).isMapStringString()) plainValues = (Map<String, String>) data;
                    break;
                case SIMPLE_VALUES:
                    if (new Instance(data).init().isMapStringString()) simpleValues = (Map<String, Map<String, String>>) data;
                    break;
                case TAGGED_VALUES:
                    if (new Instance(data).init().isMapStringMapStringString()) taggedValues = (Map<String, Map<String, Map<String, String>>>) data;
                    break;
                case PLAIN_TAGGED_VALUES:
                    if (new Instance(data).init().isMapStringString()) plainTaggedValues = (Map<String, Map<String, String>>) data;
                    break;
            }
        }

    }

    public void append(Add where, Object... data)
    {
        switch (where)
        {
            case VALUE_SET:
                if (type == BlockTypes.TYPE_1 && data.length == 2 && new Instance(data).isAllString())
                {
                    String[] temp = (String[]) data;
                    valueSet.put(temp[0], temp[1]);
                }
                break;
            case PLAIN_TAGGED_VALUE:
                if (type == BlockTypes.TYPE_2 && !plainTaggedValues.isEmpty() && data.length == 3 &&
                new Instance(data).isAllString())
                {
                    String[] temp = (String[]) data;
                    if (objectNames.contains(temp[0]))
                        plainTaggedValues.get(temp[0]).put(temp[1], temp[2]);
                }
                break;
            case SIMPLE_VALUE:
                if (type == BlockTypes.TYPE_2 && plainValues.isEmpty() && plainTaggedValues.isEmpty() &&
                data.length == 3 && new Instance(data).isAllString())
                {
                    String[] temp = (String[]) data;
                    if (objectNames.contains(temp[0]))
                        simpleValues.get(temp[0]).put(temp[1], temp[2]);
                }
                break;
            case LIST_VALUE:
                if (type == BlockTypes.TYPE_2 && plainValues.isEmpty() && plainTaggedValues.isEmpty() &&
                data.length == 3 && data[0] instanceof String && data[1] instanceof String &&
                new Instance(data[2]).isArrayListString() && objectNames.contains((String) data[0]))
                    listValues.get((String) data[0]).put((String) data[1], (ArrayList<String>) data[2]);
                break;

            case LIST_VALUE_INSIDE_VALUE:
                if (type == BlockTypes.TYPE_2 && plainValues.isEmpty() && plainTaggedValues.isEmpty() &&
                data.length == 3 && new Instance(data).isAllString())
                {
                    String[] temp = (String[]) data;
                    if (objectNames.contains(temp[0]) && listValues.get(temp[0]).containsKey(temp[1]))
                        listValues.get(temp[0]).get(temp[1]).add(temp[2]);
                }
                break;

            case TAGGED_VALUE:
                if (type == BlockTypes.TYPE_2 && plainValues.isEmpty() && plainTaggedValues.isEmpty() &&
                data.length == 3 && data[0] instanceof String && data[1] instanceof String &&
                new Instance(data[2]).isMapStringString() && objectNames.contains((String) data[0]))
                    taggedValues.get((String) data[0]).put((String) data[1], (Map<String, String>) data[2]);
                break;

            case TAGGED_VALUE_INSIDE_VALUE:
                if (type == BlockTypes.TYPE_2 && plainValues.isEmpty() && plainTaggedValues.isEmpty() &&
                data.length == 4 && new Instance(data).isAllString())
                {
                    String[] temp = (String[]) data;
                    //COMBINE ADD AND APPEND???
                    //if (objectNames.contains(temp[0]) && taggedValues.containsKey())
                }
        }
    }

    public void addObject(String objectName, String plainValue, Map<String, String> plainTaggedValue, Map<String, String> simpleValue, Map<String, ArrayList<String>> listValue, Map<String, Map<String, String>> taggedValue)
    {
        if (type == BlockTypes.TYPE_2 && !objectNames.contains(objectName))
        {
            objectNames.add(objectName);
            if (plainValue != null) plainValues.put(objectName, plainValue);
            else if (plainTaggedValue != null) plainTaggedValues.put(objectName, plainTaggedValue);
            else
            {
                if (simpleValue != null) simpleValues.put(objectName, simpleValue);
                if (listValue != null) listValues.put(objectName, listValue);
                if (taggedValue != null) taggedValues.put(objectName, taggedValue);
            }
        }
    }

    public Object get(Location location)
    {
        if (type == BlockTypes.TYPE_0 || type == BlockTypes.TYPE_1)
        {
            if (location == Location.TYPE_0_STORAGE) return value;
            else if (location == Location.TYPE_1_STORAGE) return valueSet;
            else return null;
        }
        else return null;
    }

    public Object get(TypeTwoLocations location)
    {
        if (type == BlockTypes.TYPE_2)
        {
            if (location == TypeTwoLocations.LIST_VALUES) return listValues;
            else if (location == TypeTwoLocations.PLAIN_TAGGED_VALUES) return plainTaggedValues;
            else if (location == TypeTwoLocations.OBJECT_NAMES) return objectNames;
            else if (location == TypeTwoLocations.PLAIN_VALUES) return plainValues;
            else if (location == TypeTwoLocations.SIMPLE_VALUES) return simpleValues;
            else if (location == TypeTwoLocations.TAGGED_VALUES) return taggedValues;
            else return null;
        }
        else return null;
    }

    public void delete(String objectName)
    {
        if (type == BlockTypes.TYPE_2 && objectNames.contains(objectName))
        {
            objectNames.remove(objectName);

            if (!plainValues.isEmpty()) plainValues.clear();
            else if (!plainTaggedValues.isEmpty()) plainTaggedValues.clear();
            else
            {
                if (simpleValues.containsKey(objectName)) simpleValues.remove(objectName);
                if (listValues.containsKey(objectName)) listValues.remove(objectName);
                if (taggedValues.containsKey(objectName)) taggedValues.remove(objectName);
            }
        }
    }

    public void delete(String objectName, String paramName)
    {
        if (type == BlockTypes.TYPE_2 && objectNames.contains(objectName) &&
                plainValues.isEmpty() && plainTaggedValues.isEmpty())
        {
            if (simpleValues.containsKey(objectName) &&
            simpleValues.get(objectName).containsKey(paramName))
                simpleValues.get(objectName).remove(paramName);

            if (listValues.containsKey(objectName) &&
            listValues.get(objectName).containsKey(paramName))
                listValues.get(objectName).remove(paramName);

            if (taggedValues.containsKey(objectName) &&
            taggedValues.get(objectName).containsKey(paramName))
                taggedValues.get(objectName).remove(paramName);
        }
    }

    public void delete()
    {
        switch (type)
        {
            case TYPE_0:
                value = null;
                break;
            case TYPE_1:
                valueSet.clear();
                break;
            case TYPE_2:
                objectNames.clear();
                plainValues.clear();
                plainTaggedValues.clear();
                simpleValues.clear();
                listValues.clear();
                taggedValues.clear();
                break;
        }
    }


    public enum BlockTypes
    {
        TYPE_0, TYPE_1, TYPE_2
    }

    public enum Add
    {
        VALUE_SET, PLAIN_TAGGED_VALUE, SIMPLE_VALUE, LIST_VALUE, LIST_VALUE_INSIDE_VALUE, TAGGED_VALUE, TAGGED_VALUE_INSIDE_VALUE
    }

    public enum Location
    {
        TYPE_0_STORAGE, TYPE_1_STORAGE,
    }

    public enum TypeTwoLocations
    {
        OBJECT_NAMES, PLAIN_VALUES, PLAIN_TAGGED_VALUES, SIMPLE_VALUES, LIST_VALUES, TAGGED_VALUES
    }

}
