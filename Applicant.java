package ru.ushakova.number4;

public class Applicant {
    private String lastName;
    private String firstName;
    private int score1;
    private int score2;
    private int score3;

    public Applicant(String lastName, String firstName, int score1, int score2, int score3) {
        setLastName(lastName);
        setFirstName(firstName);
        setScore1(score1);
        setScore2(score2);
        setScore3(score3);
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty() || lastName.length() > 20) {
            throw new IllegalArgumentException("Фамилия не может быть пустой или длиннее 20 символов");
        }
        this.lastName = lastName.trim();
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty() || firstName.length() > 15) {
            throw new IllegalArgumentException("Имя не может быть пустым или длиннее 15 символов");
        }
        this.firstName = firstName.trim();
    }

    public int getScore1() { return score1; }
    public void setScore1(int score1) {
        if (score1 < 0 || score1 > 100) {
            throw new IllegalArgumentException("Балл по предмету 1 должен быть от 0 до 100");
        }
        this.score1 = score1;
    }

    public int getScore2() { return score2; }
    public void setScore2(int score2) {
        if (score2 < 0 || score2 > 100) {
            throw new IllegalArgumentException("Балл по предмету 2 должен быть от 0 до 100");
        }
        this.score2 = score2;
    }

    public int getScore3() { return score3; }
    public void setScore3(int score3) {
        if (score3 < 0 || score3 > 100) {
            throw new IllegalArgumentException("Балл по предмету 3 должен быть от 0 до 100");
        }
        this.score3 = score3;
    }

    public boolean isAdmitted() {
        return score1 >= 30 && score2 >= 30 && score3 >= 30 &&
                (score1 + score2 + score3) >= 140;
    }

    public int getTotalScore() {
        return score1 + score2 + score3;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + score1 + " " + score2 + " " + score3 +
                " (сумма: " + getTotalScore() + ")";
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }
}