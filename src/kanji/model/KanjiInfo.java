package kanji.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents detailed information about a Kanji character, including
 * its readings, meanings, stroke count, JLPT level, and more.
 * This model maps JSON properties from the KanjiAPI to Java fields.
 */
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

	    /**
	     * Gets the Mainichi Shinbun frequency rank.
	     * 
	     * @return frequency rank
	     */
	    public int getFreqMainichiShinbun() 
	    {
	        return frequency;
	    }

	    /**
	     * Sets the Mainichi Shinbun frequency rank.
	     * 
	     * @param freqMainichiShinbun frequency rank
	     */
	    public void setFreqMainichiShinbun(int freqMainichiShinbun) 
	    {
	        this.frequency = freqMainichiShinbun;
	    }

	    /**
	     * Gets the grade level the kanji is taught in.
	     * 
	     * @return grade level
	     */
	    public int getGrade() 
	    {
	        return grade;
	    }

	    /**
	     * Sets the grade level the kanji is taught in.
	     * 
	     * @param grade grade level
	     */
	    public void setGrade(int grade) 
	    {
	        this.grade = grade;
	    }

	    /**
	     * Gets the English keyword from the Heisig method.
	     * 
	     * @return Heisig English keyword
	     */
	    public String getHeisigEn() 
	    {
	        return heisigEn;
	    }

	    /**
	     * Sets the English keyword from the Heisig method.
	     * 
	     * @param heisigEn Heisig English keyword
	     */
	    public void setHeisigEn(String heisigEn) 
	    {
	        this.heisigEn = heisigEn;
	    }

	    /**
	     * Gets the JLPT level of the kanji.
	     * 
	     * @return JLPT level
	     */
	    public int getJlpt() 
	    {
	        return jlpt;
	    }

	    /**
	     * Sets the JLPT level of the kanji.
	     * 
	     * @param jlpt JLPT level
	     */
	    public void setJlpt(int jlpt) 
	    {
	        this.jlpt = jlpt;
	    }

	    /**
	     * Gets the kanji character.
	     * 
	     * @return kanji character
	     */
	    public String getKanji() 
	    {
	        return kanji;
	    }

	    /**
	     * Sets the kanji character.
	     * 
	     * @param kanji kanji character
	     */
	    public void setKanji(String kanji) 
	    {
	        this.kanji = kanji;
	    }

	    /**
	     * Gets the list of kun’yomi (Japanese) readings.
	     * 
	     * @return list of kun readings
	     */
	    public List<String> getKunReadings() 
	    {
	        return kunReadings;
	    }

	    /**
	     * Sets the list of kun’yomi (Japanese) readings.
	     * 
	     * @param kunReadings list of kun readings
	     */
	    public void setKunReadings(List<String> kunReadings) 
	    {
	        this.kunReadings = kunReadings;
	    }

	    /**
	     * Gets the list of meanings (English definitions).
	     * 
	     * @return list of meanings
	     */
	    public List<String> getMeanings() 
	    {
	        return meanings;
	    }

	    /**
	     * Sets the list of meanings (English definitions).
	     * 
	     * @param meanings list of meanings
	     */
	    public void setMeanings(List<String> meanings) 
	    {
	        this.meanings = meanings;
	    }

	    /**
	     * Gets the list of name-specific readings.
	     * 
	     * @return list of name readings
	     */
	    public List<String> getNameReadings() 
	    {
	        return nameReadings;
	    }

	    /**
	     * Sets the list of name-specific readings.
	     * 
	     * @param nameReadings list of name readings
	     */
	    public void setNameReadings(List<String> nameReadings) 
	    {
	        this.nameReadings = nameReadings;
	    }

	    /**
	     * Gets the list of additional notes about the kanji.
	     * 
	     * @return list of notes
	     */
	    public List<String> getNotes() 
	    {
	        return notes;
	    }

	    /**
	     * Sets the list of additional notes about the kanji.
	     * 
	     * @param notes list of notes
	     */
	    public void setNotes(List<String> notes) 
	    {
	        this.notes = notes;
	    }

	    /**
	     * Gets the list of on’yomi (Chinese-origin) readings.
	     * 
	     * @return list of on readings
	     */
	    public List<String> getOnReadings() 
	    {
	        return onReadings;
	    }

	    /**
	     * Sets the list of on’yomi (Chinese-origin) readings.
	     * 
	     * @param onReadings list of on readings
	     */
	    public void setOnReadings(List<String> onReadings) 
	    {
	        this.onReadings = onReadings;
	    }

	    /**
	     * Gets the stroke count for the kanji.
	     * 
	     * @return stroke count
	     */
	    public int getStrokeCount() 
	    {
	        return strokeCount;
	    }

	    /**
	     * Sets the stroke count for the kanji.
	     * 
	     * @param strokeCount stroke count
	     */
	    public void setStrokeCount(int strokeCount) 
	    {
	        this.strokeCount = strokeCount;
	    }

	    /**
	     * Gets the Unicode value of the kanji.
	     * 
	     * @return Unicode string
	     */
	    public String getUnicode() 
	    {
	        return unicode;
	    }

	    /**
	     * Sets the Unicode value of the kanji.
	     * 
	     * @param unicode Unicode string
	     */
	    public void setUnicode(String unicode) 
	    {
	        this.unicode = unicode;
	    }
}
