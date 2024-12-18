package org.example;

import com.github.javafaker.Faker;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.ArrayList;
import java.util.List;

public class MyTelegramBot extends TelegramLongPollingBot {
    private final CharacterGenerator characterGenerator = new CharacterGenerator();
    private final String BOT_USERNAME; // Имя пользователя бота
    private final String BOT_TOKEN;     // Токен бота

    // Конструктор, который получает токен из переменных окружения
    public MyTelegramBot() {
        this.BOT_USERNAME = System.getenv("BOT_USERNAME");
        this.BOT_TOKEN = System.getenv("BOT_TOKEN");
        System.out.println("Bot has started");
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (text.equals("/start")) {
                sendResponse(chatId, "Привет, меня зовут Пушистик! Я стану твоим помощником для создания персонажей фэнтези-игр. И мне уже не терпится начать! Итак, сколько персонажей ты хочешь создать? Используй /generate [число], чтобы создать персонажей.");
            } else if (text.startsWith("/generate")) {
                String[] parts = text.split(" ");
                if (parts.length > 1) {
                    try {
                        int numCharacters = Integer.parseInt(parts[1]);
                        if (numCharacters > 10) {
                            sendResponse(chatId, "Пожалуйста, запрашивай не более 10 персонажей за раз.");
                            return;
                        }
                        List<CharacterGenerator.Character> characters = generateCharacters(numCharacters);
                        StringBuilder response = new StringBuilder("Сгенерированные персонажи:\n");
                        for (CharacterGenerator.Character character : characters) {
                            response.append(character).append("\n\n");
                        }
                        sendResponse(chatId, response.toString());
                    } catch (NumberFormatException e) {
                        sendResponse(chatId, "Пожалуйста, введи корректное число.");
                    }
                } else {
                    sendResponse(chatId, "Укажи количество персонажей после команды /generate.");
                }
            }
        }
    }


    private List<CharacterGenerator.Character> generateCharacters(int numCharacters) {
        List<CharacterGenerator.Character> characters = new ArrayList<>();
        for (int i = 0; i < numCharacters; i++) {
            characters.add(characterGenerator.generateCharacter());
        }
        return characters;
    }

    private void sendResponse(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.err.println("Ошибка отправки сообщения: " + e.getMessage());
        }
    }
}
