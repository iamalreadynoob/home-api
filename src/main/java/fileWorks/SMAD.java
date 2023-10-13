package fileWorks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SMAD
{

    public static ArrayList<BlockSMAD> read(String path)
    {
        ArrayList<BlockSMAD> blocks = new ArrayList<>();

        ArrayList<String> lines = TextCommunication.read(path);

        //split lines into raw blocks
        ArrayList<ArrayList<String>> rawBlocks = new ArrayList<>();

        int loc = 0;
        while (loc < lines.size())
        {
            if (lines.get(loc).startsWith("!"))
            {
                ArrayList<String> block = new ArrayList<>();

                while (loc < lines.size() && !lines.get(loc).startsWith("!"))
                {
                    if (!lines.get(loc).isEmpty() && !lines.get(loc).isBlank())
                        block.add(lines.get(loc));

                    loc++;
                }

                rawBlocks.add(block);
            }
            else loc++;
        }

        //map these blocks
        Map<String, ArrayList<String>> setBlocks = new HashMap<>();

        for (ArrayList<String> list: rawBlocks)
        {
            String name;

            if (list.get(0).contains("=")) name = list.get(0).substring(1, list.get(0).indexOf("=")).trim();
            else name = list.get(0).trim();

            setBlocks.put(name, list);
        }

        //create SMAD blocks
        Set<String> blockNames = setBlocks.keySet();

        for (String blName: blockNames)
        {
            ArrayList<String> setBlock = setBlocks.get(blName);
            BlockSMAD bl = null;

            if (setBlock.get(0).contains("="))
            {
                int index = setBlock.get(0).indexOf("=");
                boolean flag = true;

                if (setBlock.get(0).charAt(index+1) == '{') flag = false;

                if (flag) bl = new BlockSMAD(setBlock.get(0).substring(index+1));
                else
                {
                    Map<String, String> valueSet = new HashMap<>();

                    int openBracketsIndex = setBlock.get(0).indexOf("{");
                    int closeBracketsIndex = setBlock.get(0).lastIndexOf("}");

                    String rawTagged = setBlock.get(0).substring(openBracketsIndex+1, closeBracketsIndex);
                    String[] splittedRawTagged = rawTagged.split(",");
                    for (String s: splittedRawTagged)
                    {
                        int separator = s.indexOf(":");
                        valueSet.put(s.substring(0, separator).trim(), s.substring(separator+1));
                    }

                    bl = new BlockSMAD(valueSet);
                }
            }
            else
            {
                int bLoc = 0;

                ArrayList<ArrayList<String>> objects = new ArrayList<>();

                while (bLoc < setBlock.size())
                {
                    if (setBlock.get(bLoc).startsWith("@"))
                    {
                        ArrayList<String> obj = new ArrayList<>();

                        while (bLoc < setBlock.size() && !setBlock.get(bLoc).startsWith("@"))
                        {
                            if (!setBlock.get(bLoc).isBlank() && !setBlock.get(bLoc).isEmpty())
                                obj.add(setBlock.get(bLoc));

                            bLoc++;
                        }

                        objects.add(obj);
                    }
                    else bLoc++;
                }

                ArrayList<String> objectNames = new ArrayList<>();
                Map<String, String> plainValues = new HashMap<>();
                Map<String, Map<String, String>> plainTaggedValues = new HashMap<>();
                Map<String, Map<String, String>> simpleValues = new HashMap<>();
                Map<String, Map<String, ArrayList<String>>> listValues = new HashMap<>();
                Map<String, Map<String, Map<String, String>>> taggedValues = new HashMap<>();

                for (ArrayList<String> obj: objects)
                {
                    if (obj.get(0).contains("="))
                    {
                        int eqInd = obj.get(0).indexOf("=");

                        if (eqInd+1 < obj.get(0).length() && obj.get(0).charAt(eqInd+1) == '{')
                        {
                            String objName = obj.get(0).substring(1, eqInd);

                            Map<String, String> objTags = new HashMap<>();
                            String rawTagged = obj.get(0).substring(eqInd+2, obj.get(0).lastIndexOf("}"));
                            String[] connections = rawTagged.split(",");
                            for (String c: connections)
                            {
                                int separator = c.indexOf(":");
                                objTags.put(c.substring(0, separator).trim(), c.substring(separator+1));
                            }

                            objectNames.add(objName);
                            plainTaggedValues.put(objName, objTags);
                        }
                        else
                        {
                            objectNames.add(obj.get(0).substring(1, eqInd).trim());
                            plainValues.put(obj.get(0).substring(1, eqInd).trim(), obj.get(0).substring(eqInd+1));
                        }
                    }
                    else
                    {
                        String objName = obj.get(0).substring(1);

                        Map<String, String> tempSimpleValues = new HashMap<>();
                        Map<String, ArrayList<String>> tempListValues = new HashMap<>();
                        Map<String, Map<String, String>> tempTaggedValues = new HashMap<>();

                        for (int i = 1; i < obj.size(); i++)
                        {
                            int eqInd = obj.get(i).indexOf('=');

                            if (obj.get(i).charAt(0) == '?')
                            {
                                String param = obj.get(i).substring(1, eqInd).trim();

                                String rawList = obj.get(i).substring(eqInd+1);
                                String[] elements = rawList.split(",");
                                ArrayList<String> arr = new ArrayList<>();
                                for (String e: elements) arr.add(e);

                                tempListValues.put(param, arr);
                            }
                            else
                            {
                                if (obj.get(i).charAt(eqInd+1) == '{')
                                {
                                    String rawTagged = obj.get(i).substring(eqInd+1, obj.get(i).lastIndexOf('}'));
                                    String[] connections = rawTagged.split(",");
                                    Map<String, String> tempTaggedBlock = new HashMap<>();
                                    for (String c: connections)
                                    {
                                        int separatorInd = c.indexOf(":");
                                        tempTaggedBlock.put(c.substring(0, separatorInd).trim(), c.substring(separatorInd+1));
                                    }
                                    tempTaggedValues.put(obj.get(i).substring(0, eqInd).trim(), tempTaggedBlock);
                                }
                                else tempSimpleValues.put(obj.get(i).substring(0, eqInd).trim(), obj.get(i).substring(eqInd+1));
                            }
                        }

                        objectNames.add(objName);
                        if (!tempSimpleValues.isEmpty()) simpleValues.put(objName, tempSimpleValues);
                        if (!tempListValues.isEmpty()) listValues.put(objName, tempListValues);
                        if (!tempTaggedValues.isEmpty()) taggedValues.put(objName, tempTaggedValues);
                    }
                }
            }

            blocks.add(bl);
        }

        return blocks;
    }

    public static void write(ArrayList<BlockSMAD> blocks)
    {

    }

}
