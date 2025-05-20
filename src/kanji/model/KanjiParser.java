package kanji.model;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A utility class for parsing JSON strings into {@link KanjiInfo} objects.
 * This class uses Jackson's ObjectMapper to convert JSON to Java objects.
 */
public class KanjiParser
{
    /**
     * Parses a JSON-formatted string representing Kanji data into a {@link KanjiInfo} object.
     * 
     * @param jsonString the raw JSON string to parse
     * @return a {@link KanjiInfo} object if parsing is successful, otherwise {@code null}
     */
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
