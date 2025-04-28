package kanji.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Kanji(String kanji, String jlpt, String [] kun_readings, String [] on_readings, String [] meanings)
{
	public Kanji()
	{
		this("", "", null, null, null);
	}
}
