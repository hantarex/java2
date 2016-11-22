package ru.sportdepo.ashikov.lesson4;


import android.util.Log;

class Calculate {
    private String act=null;
    private String spaceA=null;
    private String spaceB=null;
    private MainActivity mainActivity;
    Calculate(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }

    void compute() {
        double result;
        if(act==null || act.equals("")) {showError("Не выбрано действие");return;}
        if(spaceA==null || !spaceA.matches("\\d+(?:\\.\\d+)?")) {showError("В поле А нет числа");return;}
        if(spaceB==null || !spaceB.matches("\\d+(?:\\.\\d+)?")) {showError("В поле B нет числа");return;}

        switch (act){
            case "+":
                result=Integer.parseInt(spaceA)+Integer.parseInt(spaceB);
                break;
            case "-":
                result=Integer.parseInt(spaceA)-Integer.parseInt(spaceB);
                break;
            case "*":
                result=Integer.parseInt(spaceA)*Integer.parseInt(spaceB);
                break;
            case "/":
                result=(double)Integer.parseInt(spaceA)/Integer.parseInt(spaceB);
                break;
            default:
                showError("Ошибка!");
                return;
        }
        showResult(result);
    }

    private void showResult(double result) {
        mainActivity.showResult(String.valueOf(result));
    }

    private void showError(String s) {
        mainActivity.showError(s);
    }

    void setAct(String act) {
        this.act = act;
    }

    void setSpaceA(String spaceA) {
        this.spaceA = spaceA;
    }

    void setSpaceB(String spaceB) {
        this.spaceB = spaceB;
    }

    String getAct() {
        return act;
    }

    String getSpaceA() {
        return spaceA;
    }

    String getSpaceB() {
        return spaceB;
    }
}
