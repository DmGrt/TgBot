package org.example;

import org.fluentd.logger.FluentLogger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class Botinok extends TelegramLongPollingBot {
    private static final String LABEL = "Lab7";
    private final FluentLogger fluentLogger = FluentLogger.getLogger(LABEL, "localhost", 8080);
    private final Map<String, Object> data = new HashMap<>();

    @Override
    public String getBotUsername() {
        return "rezynyaBot";
    }

    @Override
    public String getBotToken() {
        return "2067761235:AAGKEYE_MoMzAGfslGwuJhJ-RtVbhd1fqLg";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            String chat_id = String.valueOf(update.getMessage().getChatId());
            SendMessage message = new SendMessage();
            message.setChatId(chat_id);
            if (message_text.equals("Слава Україні!")) {
                message.setText("Героям сала!");
            } else {
                message.setText("Hi Zaya! " + message_text);
                data.put(LABEL, message);
                fluentLogger.log(LABEL, data);
            }
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
