package adminservlet;

public class Question {
    private int questionId;
    private int courseId;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;

    // Default constructor
    public Question() {
    }

    // Parameterized constructor (optional)
    public Question(int questionId, int courseId, String questionText, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.questionId = questionId;
        this.courseId = courseId;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    // Getters for each property
    public int getQuestionId() {
        return questionId;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    // Setters for each property
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
