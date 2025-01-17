import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("********************");
        System.out.println("Задача 1");
        System.out.println("********************");
        String filePath = "C:\\Users\\User\\Documents\\example.txt";
        try
        {
            FileInputStream fis = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            int wordCount = 0;
            String longestWord = "";
            Pattern wordPattern = Pattern.compile("[\\p{L}0-9'-]+");
            while((line = reader.readLine()) != null)
            {
                Matcher matcher = wordPattern.matcher(line);
                while(matcher.find())
                {
                    String word = matcher.group();
                    wordCount++;
                    if(word.length() > longestWord.length())
                        longestWord = word;
                }
            }
            reader.close();
            System.out.println("Количество слов в файле: " + wordCount);
            System.out.println("Самое длинное слово в файле: " + longestWord);
        }
        catch(IOException e)
        {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        System.out.println("********************");
        System.out.println("Задача 2");
        System.out.println("********************");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите текст для шифрования: ");
        String inputText = scanner.nextLine();
        StringBuilder encrypted = new StringBuilder();
        for(char character : inputText.toCharArray())
        {
            char encryptedChar = (char)(((character >>> 3) | (character << 13)) & 0xFFFF);
            encrypted.append(encryptedChar);
        }
        String encryptedText = encrypted.toString();
        filePath = "C:\\Users\\User\\Documents\\EncryptedText.txt";
        try(FileWriter writer = new FileWriter(filePath, true))
        {
            writer.write(encryptedText + System.lineSeparator());
            System.out.println("Зашифрованный текст записан в файл.");
        }
        catch(IOException e)
        {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}