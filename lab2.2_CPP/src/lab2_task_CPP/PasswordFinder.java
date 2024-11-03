package lab2_task_CPP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordFinder {

    public static void main(String[] args) {
        String filePath = "D:\\java\\text.txt";

        List<String> passwordCandidates = findPasswordCandidates(filePath);

        if (passwordCandidates.isEmpty()) {
            System.out.println("Не знайдено жодних кандидатів на паролі.");
        } else {
            System.out.println("Можливі кандидати на паролі:");
            for (String candidate : passwordCandidates) {
                System.out.println(candidate);
            }
        }
    }

    public static List<String> findPasswordCandidates(String filePath) {
    	String passwordPattern = "^(?=.*[a-zа-яієґ])(?=.*[A-ZА-ЯІЇЄҐ])(?=.*\\d)(?=.*[!@#$%^&*])([A-Za-zА-Яа-яІіЄєҐґ\\d!@#$%^&*]{8,20})$";
        Pattern pattern = Pattern.compile(passwordPattern);
        List<String> candidates = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Видалити лише коми, крапки і лапки
                line = line.replaceAll("[,.\"']", ""); // Залишаємо лише допустимі символи

                String[] words = line.split("\\s+");

                for (String word : words) {
                    Matcher matcher = pattern.matcher(word);
                    if (matcher.matches()) {
                        candidates.add(word);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }

        return candidates;
    }
}
