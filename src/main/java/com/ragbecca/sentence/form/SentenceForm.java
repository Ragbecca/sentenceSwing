package com.ragbecca.sentence.form;

import javax.validation.constraints.NotNull;

public class SentenceForm {

    @NotNull
    private String sentence;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
