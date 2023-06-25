package project.bot.perdunbot.builders;

import com.pengrad.telegrambot.model.request.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KeyboardBuilder {

    public InlineKeyboardMarkup pukButton() {

        InlineKeyboardButton getDocumentButton = new InlineKeyboardButton("ПУК");
        getDocumentButton.callbackData("PUK");

        return new InlineKeyboardMarkup(getDocumentButton);
    }
    public InlineKeyboardMarkup yesNoButtons() {
        InlineKeyboardButton yesButton = new InlineKeyboardButton("Да");
        yesButton.callbackData("Да");
        InlineKeyboardButton noButton = new InlineKeyboardButton("Нет");
        noButton.callbackData("Нет");

        return new InlineKeyboardMarkup(yesButton, noButton);
    }
    public InlineKeyboardMarkup anotherYesNoButtons() {
        InlineKeyboardButton yesButton = new InlineKeyboardButton("Да, все так");
        yesButton.callbackData("Yes");
        InlineKeyboardButton noButton = new InlineKeyboardButton("Нет, мне нужна помощь!");
        noButton.callbackData("No");

        return new InlineKeyboardMarkup(yesButton, noButton);
    }
}