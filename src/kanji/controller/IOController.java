package kanji.controller;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.URL;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kanji.model.Kanji;

/**
 * IOController handles input/output operations for reading and writing
 * text and JSON data related to the Kanji application.
 */
public class IOController
{
	/**
	 * Reads the entire contents of a text file at the specified path.
	 *
	 * @param app  Reference to the Controller for error handling.
	 * @param path The path to the text file.
	 * @return The full text content of the file, or an empty string if an error occurs.
	 */
	public static String readTextFromFile(Controller app, String path)
	{
		String content = "";
		
		try(Scanner fileScanner = new Scanner(new File(path)))
		{
			while(fileScanner.hasNextLine())
			{
				content += fileScanner.nextLine() + "\n";
			}
		}
		catch(NullPointerException | FileNotFoundException error)
		{
			app.handleError(error);
		}
		
		return content;
	}
	
	/**
	 * Writes a given string of text to a file at the specified path.
	 *
	 * @param app   Reference to the Controller for error handling.
	 * @param text  The text to write to the file.
	 * @param path  The file path where the text should be saved.
	 */
	public static void writeTextForFile(Controller app, String text, String path)
	{
		try (PrintWriter textWriter = new PrintWriter(new File(path));
				Scanner stringScanner = new Scanner(text))
		{
			while (stringScanner.hasNextLine())
			{
				textWriter.println(stringScanner.nextLine());
			}
		}
		catch (FileNotFoundException error)
		{
			app.handleError(error);
		}
	}

	/**
	 * Reads a single JSON object from a URL and maps it to a Kanji object.
	 * Currently only processes URLs containing the word "cat".
	 *
	 * @param app      Reference to the Controller for error handling.
	 * @param urlBase  The base URL to fetch the JSON from.
	 * @param appended Optional additional path or parameters to append to the URL.
	 * @return A {@link Kanji} object parsed from JSON, or null if an error occurs or if the URL does not match expectations.
	 */
	public static Object readSingleJSON(Controller app, String urlBase, String appended)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		if(urlBase != null)
		{
			if (appended == null)
			{
				appended = "";
			}
			try
			{
				if (urlBase.contains("cat"))
				{
					Kanji exampleKanji = mapper.readValue(new URL(urlBase + appended), Kanji.class);
							
					return exampleKanji;
				}
			}
			catch(IOException | ClassCastException error) 
			{
				app.handleError(error);
			}
		}
		
		return null;
	}
}
