package ru.sportdepo.ashikov.lesson2;

/**
 * Created by ashikov on 16.11.2016.
 */
public class Question {
    protected String question;
    protected boolean answer;
    protected String user_answer=null;

    public Question(String question,boolean answer){
        this.answer=answer;
        this.question=question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public void setUser_answer(boolean user_answer) {
        this.user_answer = (user_answer==true?"1":"0");
    }

    public String getQuestion() {

        return question;
    }

    public boolean getAnswer() {
        return answer;
    }

    public String isUser_answer() {
        return user_answer;
    }
}
