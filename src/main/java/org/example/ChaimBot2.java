package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.UpdatesHandler;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChaimBot2 extends TelegramLongPollingBot implements UpdatesHandler {
    private ActionListener actionListener;

    @Override
    public void onUpdateReceived(Update update) {
        long chatId;
        // התקבלה הודעה חדשה
        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId();
//            CallbackQuery = update.getCallbackQuery();
//            String callbackData = callbackQuery.getData();

            // בדיקת תגובת הכפתור שנלחץ ושליחת התשובה המתאימה
            if (update.getMessage().getText().equals("1")) {
                sendResponseMessage(chatId, "לחצת על כפתור 1");
            } else if (update.getMessage().getText().equals("4")) {
                sendResponseMessage(chatId, "לחצת על כפתור 2");
            }
        }

        // בדיקה שהודעה היא מסוג "פקודה"
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
             chatId = update.getMessage().getChatId();

            if (messageText.equals("/start")) {
                // מקלדת התחלה

//                message.setChatId(chatId);
//                message.setText("מה קורה ");
                // יצירת מקלדת
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                List<KeyboardRow> keyboard = new ArrayList<>();
                // שורות המספרים במקלדת
                KeyboardRow row1 = new KeyboardRow();
                row1.add(new KeyboardButton("1"));
                keyboard.add(row1);

                KeyboardRow row2 = new KeyboardRow();
                row2.add(new KeyboardButton("4"));
                keyboard.add(row2);

                KeyboardRow row3 = new KeyboardRow();
                row3.add(new KeyboardButton("7"));
                keyboard.add(row3);

                keyboardMarkup.setKeyboard(keyboard);
                SendMessage message = new SendMessage();
                // הגדרת המקלדת להודעה
                message.setChatId(chatId); // הכנס את ה-Chat ID שלך
                message.setText("בחר אפשרות:");
                message.setReplyMarkup(keyboardMarkup);


                // שליחת ההודעה עם המקלדת
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }


        }
    }
    private void sendResponseMessage(long chatId, String text) {
        SendMessage response = new SendMessage();
        response.setChatId(chatId);
        response.setText(text);

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "ChaimReminderBot";
    }

    @Override
    public String getBotToken() {
        return "6059845154:AAFKBC5pjBMujfutMgNGj1RSzNtDy7siEnE";
    }


}