package project.bot.perdunbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.MessageEntity;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.bot.perdunbot.builders.KeyboardBuilder;
import project.bot.perdunbot.builders.MessageBuilder;

@Component
@Slf4j
public class MessageHandler {
    private final TelegramBot bot;
    private final MessageBuilder messageBuilder;
    private final KeyboardBuilder keyboardBuilder;

    @Autowired
    public MessageHandler(TelegramBot bot, MessageBuilder messageBuilder, KeyboardBuilder keyboardBuilder) {
        this.bot = bot;
        this.messageBuilder = messageBuilder;
        this.keyboardBuilder = keyboardBuilder;
    }

    public void handle(Message message) throws IllegalStateException {
        Long chatId = message.chat().id();
        String text = message.text();

        if (isCommand(message, "/start")) {
            messageBuilder.sendMessage(chatId, """
                    Привет\uD83D\uDC4B\uD83C\uDFFC
                    У меня для тебя есть важное сообщение, но сначала ответь на пару вопросов!
                    
                    Твое приложение Telegram, запущено на смартфоне?""", keyboardBuilder.yesNoButtons());
        } else {
            bot.execute(new SendMessage(chatId, "Хочешь начать сначала? Отправь команду /start"));
        }

    }

    private boolean isCommand(Message message, String command) {
        MessageEntity[] entities = message.entities();
        if (entities != null) {
            for (MessageEntity entity : entities) {
                if (entity.type() == MessageEntity.Type.bot_command) {
                    String text = message.text();
                    if (text != null && text.startsWith(command)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}