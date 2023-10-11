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

        }

        return blocks;
    }

    public static void write(ArrayList<BlockSMAD> blocks)
    {

    }

}
