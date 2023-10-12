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

            if (list.get(0).contains("=")) name = list.get(0).substring(1, list.get(0).indexOf("="));
            else name = list.get(0);

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
                        valueSet.put(s.substring(0, separator), s.substring(separator+1));
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


            }

            blocks.add(bl);
        }

        return blocks;
    }

    public static void write(ArrayList<BlockSMAD> blocks)
    {

    }

}
