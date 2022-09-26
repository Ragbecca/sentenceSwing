package com.ragbecca.sentence.controller;

import com.ragbecca.sentence.form.SentenceForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.Arrays;
import java.util.Map;

@Controller
public class WebControllerSentence implements WebMvcConfigurer {

    @RequestMapping(value="/results", method = RequestMethod.GET)
    public String showResults(HttpServletRequest request, Model model) {

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap instanceof Null || flashMap.isEmpty()) {
            return "redirect:/sentence";
        }

        String sentence = (String) flashMap.get("sentence");

        String trimmedSentence = sentence.trim();

        int charactersAmount = trimmedSentence.length();

        int wordsAmount = trimmedSentence.split(" ").length;

        int vowelsAmount = vowelCalculator(trimmedSentence);

        boolean checkPalindroom = palindroomCheck(trimmedSentence);

        model.addAttribute("charactersAmount", charactersAmount);
        model.addAttribute("wordsAmount", wordsAmount);
        model.addAttribute("vowelsAmount", vowelsAmount);
        model.addAttribute("checkPalindroom", checkPalindroom);

        addLetterModel(model, trimmedSentence);

        return "/results";
    }

    private void addLetterModel(Model model, String sentence) {
        model.addAttribute("amountA", amountOfSpecifiedLetter('a', sentence));
        model.addAttribute("amountB", amountOfSpecifiedLetter('b', sentence));
        model.addAttribute("amountC", amountOfSpecifiedLetter('c', sentence));
        model.addAttribute("amountD", amountOfSpecifiedLetter('d', sentence));
        model.addAttribute("amountE", amountOfSpecifiedLetter('e', sentence));
        model.addAttribute("amountF", amountOfSpecifiedLetter('f', sentence));
        model.addAttribute("amountG", amountOfSpecifiedLetter('g', sentence));
        model.addAttribute("amountH", amountOfSpecifiedLetter('h', sentence));
        model.addAttribute("amountI", amountOfSpecifiedLetter('i', sentence));
        model.addAttribute("amountJ", amountOfSpecifiedLetter('j', sentence));
        model.addAttribute("amountK", amountOfSpecifiedLetter('k', sentence));
        model.addAttribute("amountL", amountOfSpecifiedLetter('l', sentence));
        model.addAttribute("amountM", amountOfSpecifiedLetter('m', sentence));
        model.addAttribute("amountN", amountOfSpecifiedLetter('n', sentence));
        model.addAttribute("amountO", amountOfSpecifiedLetter('o', sentence));
        model.addAttribute("amountP", amountOfSpecifiedLetter('p', sentence));
        model.addAttribute("amountQ", amountOfSpecifiedLetter('q', sentence));
        model.addAttribute("amountR", amountOfSpecifiedLetter('r', sentence));
        model.addAttribute("amountS", amountOfSpecifiedLetter('s', sentence));
        model.addAttribute("amountT", amountOfSpecifiedLetter('t', sentence));
        model.addAttribute("amountU", amountOfSpecifiedLetter('u', sentence));
        model.addAttribute("amountV", amountOfSpecifiedLetter('v', sentence));
        model.addAttribute("amountW", amountOfSpecifiedLetter('w', sentence));
        model.addAttribute("amountX", amountOfSpecifiedLetter('x', sentence));
        model.addAttribute("amountY", amountOfSpecifiedLetter('y', sentence));
        model.addAttribute("amountZ", amountOfSpecifiedLetter('z', sentence));
    }

    private int amountOfSpecifiedLetter(char letter, String sentence) {
        char[] chars = sentence.toLowerCase().toCharArray();

        int amountOfLetter = 0;

        for (char inCharArray : chars) {
            if (inCharArray ==  letter) {
                amountOfLetter += 1;
            }
        }
        return amountOfLetter;
    }

    private boolean palindroomCheck(String sentence) {
        char[] charArray = sentence.toCharArray();
        char[] reversedArray = new char[charArray.length];

        int charBoundAdded = 0;

        for (int i = charArray.length-1; i >= 0; i--) {
            reversedArray[charBoundAdded] = charArray[i];
            charBoundAdded += 1;
        }
        return Arrays.equals(reversedArray, charArray);
    }

    private int vowelCalculator(String sentence) {
        char[] charArray = sentence.toCharArray();
        int amountOfVowels = 0;
        for (char charInArray : charArray) {
            switch (charInArray) {
                case 'a', 'e', 'i', 'o', 'u', 'y' -> amountOfVowels += 1;
                default -> {
                }
            }
        }
        return amountOfVowels;
    }

    @GetMapping("/sentence")
    public String showForm(SentenceForm sentenceForm) {
        return "form";
    }

    @PostMapping("/sentence")
    public String checkSentence(@Valid SentenceForm sentenceForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        redirectAttributes.addFlashAttribute("sentence", sentenceForm.getSentence());

        return "redirect:/results";
    }
}
