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

    public void set(Location location, Object data)
    {
        switch (location)
        {
            case TYPE_0_STORAGE:
                if (data instanceof String) this.value = (String) data;
                break;
            case TYPE_1_STORAGE:
                if (Instance.isMapStringString(data)) this.valueSet = (Map<String, String>) data;
                break;
        }
    }

    public void set(TypeTwoLocations location, Object data)
    {
        switch (location)
        {
            case LIST_VALUES:

        }
    }

    public void append(String value)
    {

    }

    public void append(Map<String, String> valueSet)
    {

    }

    public void append(ArrayList<String> objectNames, Map<String, String> plainValues, Map<String, Map<String, String>> plainTaggedValues, Map<String, Map<String, String>> simpleValues, Map<String, Map<String, ArrayList<String>>> listValues, Map<String, Map<String, Map<String, String>>> taggedValues)
    {

    }

    public void append(Location location, Object data)
    {

    }

    public void append(TypeTwoLocations location, Object data)
    {

    }

    public Object get(What what)
    {

        return null;
    }

    public void delete()
    {

    }


    public enum BlockTypes
    {
        TYPE_0, TYPE_1, TYPE_2
    }

    public enum What
    {

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
