package kanji.controller;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;

import javax.swing.JOptionPane;

import kanji.model.Kanji;
import kanji.model.KanjiInfo;
import kanji.model.KanjiParser;
import kanji.view.KanjiCharacterPanel;
import kanji.view.KanjiFrame;

import com.google.gson.Gson;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Controller class that manages interaction between the view, model,
 * and external APIs for the Kanji lookup application.
 * It handles fetching Kanji data, error reporting, random Kanji selection,
 * and PDF export functionality.
 */
public class Controller 
{

    private final String KanjiURLBase;
    public Kanji currentKanji;
    public List<String> allKanji = new ArrayList<>();
    private final KanjiParser parser = new KanjiParser();
    private final KanjiFrame window;

    /**
     * Constructs a new Controller, sets the API base URL, and initializes the main GUI window.
     */
    public Controller() 
    {
        this.KanjiURLBase = "https://kanjiapi.dev/v1/kanji/";
        this.window = new KanjiFrame(this);
    }

    /**
     * Displays an error dialog with the specified exception.
     *
     * @param error The exception to be shown to the user.
     */
    public void handleError(Exception error) 
    {
        JOptionPane.showMessageDialog(window, error.getMessage(), "There was an error :/", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Starts the application logic (currently unused placeholder).
     */
    public void start() 
    {
        // Placeholder for startup logic
    }

    /**
     * Builds and returns a URL for fetching a given kanji character's API data.
     *
     * @param character The kanji character to encode in the URL.
     * @return A URL object for the API call, or null if malformed.
     */
    public URL getKanjiURL(String character) 
    {
        try 
        {
            return URI.create(KanjiURLBase + encodeKanji(character)).toURL();
        } 
        catch (MalformedURLException error) 
        {
            handleError(error);
            return null;
        }
    }

    /**
     * URL-encodes the provided kanji string using UTF-8.
     *
     * @param kanji The kanji character to encode.
     * @return A UTF-8 encoded string.
     */
    public String encodeKanji(String kanji) 
    {
        try 
        {
            return URLEncoder.encode(kanji, StandardCharsets.UTF_8.toString());
        } 
        catch (Exception error) 
        {
            handleError(error);
            return null;
        }
    }

    /**
     * Fetches raw JSON data for a given kanji character from the API.
     *
     * @param kanji The kanji character to look up.
     * @return A JSON string response, or an empty string on failure.
     */
    public String JsonApiReader(String kanji) 
    {
        String inline = "";
        try 
        {
            URL url = getKanjiURL(kanji);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) 
            {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }

            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNext()) 
            {
                inline += scanner.nextLine();
            }
            scanner.close();

        } 
        catch (IOException error) 
        {
            handleError(error);
        }
        return inline;
    }

    /**
     * Fetches and parses Kanji information from the API for a given character.
     *
     * @param kanji The kanji character to fetch.
     * @return A {@link KanjiInfo} object, or null if an error occurred.
     */
    public KanjiInfo getKanjiInfo(String kanji) 
    {
        if (kanji == null || kanji.trim().isEmpty()) 
        {
            System.out.println("No kanji provided for API call.");
            return null;
        }

        try 
        {
            String apiUrl = KanjiURLBase + encodeKanji(kanji);
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) 
            {
                responseBuilder.append(line);
            }

            reader.close();
            connection.disconnect();

            return parser.parseKanjiJson(responseBuilder.toString());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns the {@link KanjiCharacterPanel} instance from the main application window.
     *
     * @return The KanjiCharacterPanel instance.
     */
    public KanjiCharacterPanel getCharactersPanel() 
    {
        return window.getCharactersPanel();
    }

    /**
     * Exports the specified Kanji information to a formatted PDF file.
     *
     * @param info     The Kanji information to include in the PDF.
     * @param saveFile The file location to save the PDF to.
     */
    public void exportKanjiToPDF(KanjiInfo info, File saveFile) {
        try 
        {
            InputStream fontStream = getClass().getResourceAsStream("/NotoSansJP-VariableFont_wght.ttf");
            byte[] fontBytes = fontStream.readAllBytes();
            BaseFont baseFont = BaseFont.createFont("NotoSansJP.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, true, fontBytes, null);

            Font kanjiFont = new Font(baseFont, 100);
            Font labelFont = new Font(baseFont, 18, Font.BOLD);
            Font textFont = new Font(baseFont, 16);

            Document document = new Document(PageSize.A6, 20, 20, 20, 20);
            PdfWriter.getInstance(document, new FileOutputStream(saveFile));
            document.open();

            // Front: large Kanji character
            Paragraph kanji = new Paragraph(info.getKanji(), kanjiFont);
            kanji.setAlignment(Element.ALIGN_CENTER);
            document.add(kanji);
            document.newPage();

            // Back: readings and meanings
            Paragraph onLabel = new Paragraph("On’yomi:", labelFont);
            Paragraph onReading = new Paragraph(String.join(", ", info.getOnReadings()), textFont);
            Paragraph kunLabel = new Paragraph("Kun’yomi:", labelFont);
            Paragraph kunReading = new Paragraph(String.join(", ", info.getKunReadings()), textFont);
            Paragraph meaningLabel = new Paragraph("Meaning(s):", labelFont);
            Paragraph meanings = new Paragraph(info.getMeanings().toString(), textFont);

            document.add(meaningLabel);
            document.add(meanings);
            document.add(Chunk.NEWLINE);
            document.add(onLabel);
            document.add(onReading);
            document.add(Chunk.NEWLINE);
            document.add(kunLabel);
            document.add(kunReading);

            document.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * Fetches the complete list of Joyo kanji from the Kanji API.
     *
     * @return A list of Joyo kanji characters as Strings.
     */
    public List<String> fetchAllJoyoKanji() 
    {
        List<String> kanjiList = new ArrayList<>();
        try 
        {
            URL url = new URL("https://kanjiapi.dev/v1/kanji/joyo");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) 
            {
                json.append(line);
            }
            reader.close();

            Gson gson = new Gson();
            String[] kanjiArray = gson.fromJson(json.toString(), String[].class);
            kanjiList = Arrays.asList(kanjiArray);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return kanjiList;
    }

    /**
     * Fetches the complete list of Jinmeiyou kanji from the Kanji API.
     *
     * @return A list of Jinmeiyou kanji characters as Strings.
     */
    public List<String> fetchAllJinmeiyouKanji() 
    {
        List<String> kanjiList = new ArrayList<>();
        try 
        {
            URL url = new URL("https://kanjiapi.dev/v1/kanji/jinmeiyou");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) 
            {
                json.append(line);
            }
            reader.close();

            Gson gson = new Gson();
            String[] kanjiArray = gson.fromJson(json.toString(), String[].class);
            kanjiList = Arrays.asList(kanjiArray);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return kanjiList;
    }

    /**
     * Initializes the combined kanji list by fetching both Joyo and Jinmeiyou kanji from the API.
     */
    public void initializeKanjiList() 
    {
        List<String> joyo = fetchAllJoyoKanji();
        List<String> jinmei = fetchAllJinmeiyouKanji();

        allKanji.clear();
        allKanji.addAll(joyo);
        allKanji.addAll(jinmei);
    }

    /**
     * Returns a random kanji character from the combined Joyo and Jinmeiyou list.
     * Initializes the list if it's empty.
     *
     * @return A randomly selected kanji character, or null if list could not be initialized.
     */
    public String getRandomKanji() 
    {
        if (allKanji.isEmpty()) 
        {
            initializeKanjiList();
        }
        if (allKanji.isEmpty()) 
        {
            return null;
        }
        int index = new Random().nextInt(allKanji.size());
        return allKanji.get(index);
    }
}
