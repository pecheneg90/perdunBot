package project.bot.perdunbot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.request.SendVoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.bot.perdunbot.builders.KeyboardBuilder;
import project.bot.perdunbot.builders.MessageBuilder;

import java.io.File;

@Component
@Slf4j
public class CallBackQueryHandler {
    private final TelegramBot bot;
    private final MessageBuilder messageBuilder;
    private final KeyboardBuilder keyboardBuilder;

    @Autowired
    public CallBackQueryHandler(TelegramBot bot, MessageBuilder messageBuilder,
                                KeyboardBuilder keyboardBuilder) {
        this.bot = bot;
        this.messageBuilder = messageBuilder;
        this.keyboardBuilder = keyboardBuilder;
    }

    public void onCallbackQuery(CallbackQuery callbackQuery) {
        String data = callbackQuery.data();
        Long chatId = callbackQuery.message().chat().id();
        if (data.equals("PUK")) {
            // Воспроизведение звука пердежа
            File audioFile = new File("C:\\Users\\pecheneg\\IdeaProjects" +
                    "\\perdunBot\\src\\main\\java\\project\\bot\\perdunbot\\sound\\puk1.mp3");
            SendVoice sendVoiceRequest = new SendVoice(chatId, audioFile);
            bot.execute(sendVoiceRequest);
        }
        if (data.equals("Да")){
            File pic = new File("C:\\Users\\pecheneg\\IdeaProjects\\perdunBot" +
                    "\\src\\main\\java\\project\\bot\\perdunbot\\pics\\pic1.jpg");
            SendPhoto sendPhoto = new SendPhoto(chatId, pic);
            bot.execute(sendPhoto);
            messageBuilder.sendMessage(chatId,"В настройках Telegram все так как на картинке?",
                    keyboardBuilder.anotherYesNoButtons());
        }
        if (data.equals("Yes")){
            messageBuilder.sendMessage(chatId, """
                    Ты готов(а) прослушать важное сообщение!
                    
                    Твои действия следующие:
                    1️⃣ Нажми на кнопку 'ПУК';
                    2️⃣ Поднеси смартфон к уху будто отвечаешь на звонок;
                    3️⃣ Слушай👂 важное сообщение""", keyboardBuilder.pukButton());
        }
        if (data.equals("Нет")){
            messageBuilder.sendMessage(chatId,"Когда запустишь бота со смартфона, " +
                    "тогда отправь команду /start");
        }
        if (data.equals("No")){
            messageBuilder.sendMessage(chatId,"Зайди в настройки Telegram ➡️ Настройки чатов " +
                    "➡️ напротив параметра 'Поднести и слушать' " +
                    "передвинь включатель в правое положение, как на картинке выше!" +
                    "\nКогда всё сделаешь нажимай на кнопку 'Да, все так'");
        }
    }
}
