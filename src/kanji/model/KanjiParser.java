package kanji.model;

import com.fasterxml.jackson.databind.ObjectMapper;

public class KanjiParser
{
    public KanjiInfo parseKanjiJson(String jsonString) 
    {
        ObjectMapper mapper = new ObjectMapper();

        try 
        {
            // Convert the JSON string into a KanjiInfo object
            KanjiInfo info = mapper.readValue(jsonString, KanjiInfo.class);
            return info;

        } 
        catch (Exception error) 
        {
        	error.printStackTrace();
            return null;
        }
    }
}