package kanji.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KanjiInfo
{
	 	public int grade;
	    private int jlpt;
	    public String kanji;
	    
	    @JsonProperty("freq_mainichi_shinbun")
	    private int frequency;
	    
	    @JsonProperty("heisig_en")
	    private String heisigEn;

	    @JsonProperty("kun_readings")
		public List<String> kunReadings;

	    public List<String> meanings;

	    @JsonProperty("name_readings")
	    private List<String> nameReadings;

	    private List<String> notes;

	    @JsonProperty("on_readings")
		public List<String> onReadings;

	    @JsonProperty("stroke_count")
		public int strokeCount;

	    private String unicode;

	    public int getFreqMainichiShinbun() 
	    {
	        return frequency;
	    }

	    public void setFreqMainichiShinbun(int freqMainichiShinbun) 
	    {
	        this.frequency = freqMainichiShinbun;
	    }

	    public int getGrade() 
	    {
	        return grade;
	    }

	    public void setGrade(int grade) 
	    {
	        this.grade = grade;
	    }
	    
	    public String getHeisigEn() {
	        return heisigEn;
	    }

	    public void setHeisigEn(String heisigEn) {
	        this.heisigEn = heisigEn;
	    }


	    public int getJlpt() 
	    {
	        return jlpt;
	    }

	    public void setJlpt(int jlpt) 
	    {
	        this.jlpt = jlpt;
	    }

	    public String getKanji() 
	    {
	        return kanji;
	    }

	    public void setKanji(String kanji) 
	    {
	        this.kanji = kanji;
	    }

	    public List<String> getKunReadings() 
	    {
	        return kunReadings;
	    }

	    public void setKunReadings(List<String> kunReadings) 
	    {
	        this.kunReadings = kunReadings;
	    }

	    public List<String> getMeanings() 
	    {
	        return meanings;
	    }

	    public void setMeanings(List<String> meanings) 
	    {
	        this.meanings = meanings;
	    }

	    public List<String> getNameReadings() 
	    {
	        return nameReadings;
	    }

	    public void setNameReadings(List<String> nameReadings) 
	    {
	        this.nameReadings = nameReadings;
	    }

	    public List<String> getNotes() 
	    {
	        return notes;
	    }

	    public void setNotes(List<String> notes) 
	    {
	        this.notes = notes;
	    }

	    public List<String> getOnReadings() 
	    {
	        return onReadings;
	    }

	    public void setOnReadings(List<String> onReadings) 
	    {
	        this.onReadings = onReadings;
	    }

	    public int getStrokeCount() 
	    {
	        return strokeCount;
	    }

	    public void setStrokeCount(int strokeCount) 
	    {
	        this.strokeCount = strokeCount;
	    }

	    public String getUnicode() 
	    {
	        return unicode;
	    }

	    public void setUnicode(String unicode) 
	    {
	        this.unicode = unicode;
	    }
}
