package org.example;

//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//import java.awt.image.ImageProducer;
//import java.util.*;
//
//public class ChaimBot extends TelegramLongPollingBot {
//
//    private Map<Long,Reminder> reminderMap;
//
//
//    public ChaimBot(){
//        this.reminderMap = new HashMap<>();
//        sendNotifications();
//    }
//
//
////    @Override
//////    public String getBotToken() {
//////        return "6059845154:AAFKBC5pjBMujfutMgNGj1RSzNtDy7siEnE";
//////    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//
//        Long chatId = update.getMessage().getChatId();
//        Reminder reminder = this.reminderMap.get(chatId);
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        if (reminder == null){
//            //fist time
//            sendMessage.setText("what do you wanna remind you ? ");
//            InlineKeyboardButton yesButton = new InlineKeyboardButton();
//            yesButton.setText("yes");
//            yesButton.setCallbackData("no");
//
//            InlineKeyboardButton noButton = new InlineKeyboardButton();
//            noButton.setText("no");
//            noButton.setCallbackData("no");
//
//
//            List<InlineKeyboardButton> topRow = List.of(yesButton,noButton);
//
//            List<List<InlineKeyboardButton>> keyBord = List.of(topRow);
//
//            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//            inlineKeyboardMarkup.setKeyboard(keyBord);
//
//            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
//
//            reminder = new Reminder();
//            this.reminderMap.put(chatId,reminder);
//        }else {
//            if (reminder.getWhat() == null){
//                String text = update.getMessage().getText();
//                reminder.setWhat(text);
//                sendMessage.setText("when? ");
//            }else if (reminder.getWhen() == 0) {
//                String text = update.getMessage().getText();
//                reminder.setWhen(Integer.valueOf(text));
//                reminder.setCompleted(true);
//                sendMessage.setText("got it");
//
//            }
//        }
//
//
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
////        String text = update.getMessage().getText();
////        System.out.println(" was send to the bot ");
////        long chatId = update.getMessage().getChatId();
////        SendMessage sendMessage = new SendMessage();
////        sendMessage.setChatId(chatId);
////        sendMessage.setText("fuck you ....");
////        System.out.println(update.getMessage().getText());
////        try {
////            execute(sendMessage);
////        } catch (TelegramApiException e){
////            throw new RuntimeException(e);
////        }
//    }
//
//    @Override
//    public String getBotUsername() {
//        return "ChaimReminderBot";
//    }
//
//    private void sendNotifications(){
//        new Thread(() ->{
//            while (true){
//                for (long chatId :this.reminderMap.keySet()) {
//                    Reminder reminder = this.reminderMap.get(chatId);
//                    if (reminder.isCompleted() && reminder.getWhen() == 0){
//                       //send
//                        SendMessage sendMessage = new SendMessage();
//                        sendMessage.setChatId(chatId);
//                        sendMessage.setText(reminder.getWhat());
//                        try {
//                            execute(sendMessage);
//                            reminder.setWhen(-1);
//                            reminder.setSent(true);
//                        } catch (TelegramApiException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }else {
//                        if (reminder.getWhen() > 0){
//                            reminder.decrementTime();
//                        }
//                    }
//                }
//               try {
//                   Thread.sleep(1000);
//               } catch (InterruptedException e) {
//                   throw new RuntimeException(e);
//               }
//            }
//        }).start();
//    }
//
//}
