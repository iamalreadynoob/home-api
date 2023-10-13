package fileWorks;

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

    public enum BlockTypes
    {
        TYPE_0, TYPE_1, TYPE_2
    }
}
