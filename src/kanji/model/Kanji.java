package kanji.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents a Kanji character and its associated data such as JLPT level,
 * readings, and meanings. This class is used for deserializing data
 * from the KanjiAPI.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Kanji(String kanji, String jlpt, String [] kun_readings, String [] on_readings, String [] meanings)
{
	/**
	 * Default constructor that initializes all fields with default or empty values.
	 */
	public Kanji()
	{
		this("", "", null, null, null);
	}
}
