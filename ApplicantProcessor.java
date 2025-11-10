package ru.ushakova.number4;

import java.io.*;
import java.util.*;

public class ApplicantProcessor {
    private Map<String, Applicant> applicantsMap;

    public ApplicantProcessor() {
        this.applicantsMap = new HashMap<>();
    }

    public void readFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;
            int totalApplicants = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty()) continue;

                if (lineNumber == 1) {
                    try {
                        totalApplicants = Integer.parseInt(line);
                        if (totalApplicants > 500) {
                            System.out.println("Предупреждение: количество абитуриентов превышает 500");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка в формате количества абитуриентов: " + line);
                        continue;
                    }
                } else {
                    processApplicantLine(line, lineNumber);
                }
            }

            System.out.println("Успешно загружено " + applicantsMap.size() + " абитуриентов из файла");

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    private void processApplicantLine(String line, int lineNumber) {
        try {
            String[] parts = line.split("\\s+");

            if (parts.length < 5) {
                System.out.println("Ошибка в строке " + lineNumber + ": недостаточно данных");
                return;
            }

            StringBuilder lastName = new StringBuilder(parts[0]);
            StringBuilder firstName = new StringBuilder();

            int i = 1;
            while (i < parts.length && !isNumeric(parts[i])) {
                if (firstName.length() > 0) {
                    firstName.append(" ");
                }
                firstName.append(parts[i]);
                i++;
            }

            if (parts.length - i < 3) {
                System.out.println("Ошибка в строке " + lineNumber + ": недостаточно баллов");
                return;
            }

            int score1 = Integer.parseInt(parts[i]);
            int score2 = Integer.parseInt(parts[i + 1]);
            int score3 = Integer.parseInt(parts[i + 2]);

            Applicant applicant = new Applicant(lastName.toString(), firstName.toString(),
                    score1, score2, score3);

            String key = applicant.getFullName();
            if (applicantsMap.containsKey(key)) {
                System.out.println("Предупреждение: дублирующийся абитуриент - " + key);
            }
            applicantsMap.put(key, applicant);

        } catch (NumberFormatException e) {
            System.out.println("Ошибка в строке " + lineNumber + ": неверный формат баллов");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка в строке " + lineNumber + ": " + e.getMessage());
        }
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public List<Applicant> getAdmittedApplicants() {
        List<Applicant> admitted = new ArrayList<>();

        for (Applicant applicant : applicantsMap.values()) {
            if (applicant.isAdmitted()) {
                admitted.add(applicant);
            }
        }

        admitted.sort((a1, a2) -> Integer.compare(a2.getTotalScore(), a1.getTotalScore()));

        return admitted;
    }

    public Collection<Applicant> getAllApplicants() {
        return applicantsMap.values();
    }

    public Applicant findApplicant(String fullName) {
        return applicantsMap.get(fullName);
    }

    public void clear() {
        applicantsMap.clear();
    }
}