package kanji.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Kanji()
{
	public Kanji()
	{
		
	}
}
