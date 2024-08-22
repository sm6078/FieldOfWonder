package org.javaacadmey.wonderfield.player;

public class PlayerAnswer {
    private boolean isWholeWord;
    private String answer;

    public PlayerAnswer(boolean isWholeWord, String answer) {
        this.isWholeWord = isWholeWord;
        this.answer = answer;
    }

    public boolean isWholeWord() {
        return isWholeWord;
    }

    public String getAnswer() {
        return answer;
    }
}
