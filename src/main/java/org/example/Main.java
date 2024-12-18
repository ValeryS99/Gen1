package org.example;

import com.github.javafaker.Faker;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try {
            // Создаем экземпляр TelegramBotsApi с использованием DefaultBotSession
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            // Регистрируем бота
            botsApi.registerBot(new MyTelegramBot());
            System.out.println("Бот запущен!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("Ошибка при запуске бота.");
        }
    }
}
