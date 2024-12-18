package org.example;

import com.github.javafaker.Faker;

import java.util.HashSet;
import java.util.Random;

public class CharacterGenerator {
    private final Faker faker = new Faker();
    // Массивы для генерации случайных значений - создаём их один раз для эффективности
    private final String[] races = {"Эльф", "Полурослик", "Полуэльф", "Человек", "Дварф", "Орк", "Гном", "Тифлинг", "Драконорожденный"};
    private final String[] classes = {"Воин", "Разбойник", "Следопыт", "Маг", "Жрец", "Колдун", "Бард", "Рыцарь", "Монах", "Алхимик", "Некромант", "Друид"};
    private final String[] personalities = {"Храбрый и решительный", "Тихий и задумчивый", "Весёлый и оптимистичный", "Серьёзный и сосредоточенный", "Добрый и отзывчивый", "Хитроумный и изобретательный", "Независимый и свободолюбивый", "Мудрый и осторожный", "Страстный и целеустремлённый", "Романтичный и мечтательный", "Прагматичный и реалистичный", "Саркастичный и остроумный", "Скромный и застенчивый", "Агрессивный и амбициозный", "Спокойный и уравновешенный"};
    private final String[] appearances = {"Высокий и мускулистый, с чёрными волосами и карими глазами.", "Низкорослый, с рыжими волосами и веснушками.", "Стройный, с длинными светлыми волосами и голубыми глазами.", "Крепкий, с короткой бородой и серыми глазами.", "Элегантный, с длинными чёрными волосами и загадочным взглядом.", "Спортивный, с загорелой кожей и короткой стрижкой.", "Молодой, с яркими волосами и необычным стилем одежды.", "Старый, с седыми волосами и глубокими морщинами.", "Индийский воин с татуировками и длинными косами.", "Лесной дух с зелёными глазами и листьями в волосах.", "Скромная девушка с очками и неуклюжей походкой.", "Богатырь с огромными мускулами и шрамами на теле."};
    private final String[] roles = {"Главный герой, отправляющийся в опасное путешествие.", "Верный друг, всегда поддерживающий в трудную минуту.", "Загадочный антагонист, преследующий собственные цели.", "Мудрый наставник, обучающий молодых героев.", "Смешной комик, развлекающий и поднимающий настроение.", "Скромный исследователь, открывающий новые горизонты.", "Силовик, защищающий своих друзей от опасностей.", "Шпион, проникающий в ряды врага.", "Лидер восстания, вдохновляющий других на борьбу.", "Маг, способный управлять стихиями.", "Охотник за сокровищами, ищущий древние артефакты.", "Целитель, помогающий раненым и больным."};
    private final String[] phrases = {"Смелость – это не отсутствие страха, а умение с ним справляться.", "Я никогда не сдамся, пока не достигну своей цели!", "Каждый из нас – кузнец своего счастья.", "Ничто не может остановить меня, даже самые трудные испытания.", "Сила не в мускулах, а в духе.", "Я верю в свои силы и способности.", "Мы все можем изменить мир, если будем действовать вместе.", "Секрет успеха – это настойчивость.", "Не бойся мечтать, мечты – это первый шаг к успеху.", "Каждый день – это новый шанс стать лучше.", "Я не идеален, но я стремлюсь к совершенству.", "Судьба улыбается смелым."};


    public Character generateCharacter() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String race = races[(int) (Math.random() * races.length)];
        String characterClass = classes[(int) (Math.random() * classes.length)];
        String personality = personalities[(int) (Math.random() * personalities.length)];
        String appearance = appearances[(int) (Math.random() * appearances.length)];
        String role = roles[(int) (Math.random() * roles.length)];
        int age = faker.number().numberBetween(10, 50);
        int strength = faker.number().numberBetween(1, 20);
        int intelligence = faker.number().numberBetween(1, 20);
        int agility = faker.number().numberBetween(1, 20);
        int constitution = faker.number().numberBetween(1, 20);
        int charisma = faker.number().numberBetween(1, 20);
        String phrases = getRandomPhrases();
        return new Character(firstName, lastName, race, characterClass, personality, appearance, role, age, strength, intelligence, agility, constitution, charisma, phrases);
    }

    private String getRandomPhrases() {
        HashSet<String> selectedPhrases = new HashSet<>();
        Random random = new Random();
        int numberOfPhrases = random.nextInt(3) + 1; // Выбираем от 1 до 3 фраз

        while (selectedPhrases.size() < numberOfPhrases) {
            selectedPhrases.add(phrases[random.nextInt(phrases.length)]);
        }

        return String.join(" | ", selectedPhrases);
    }

    public static class Character {
        String firstName;
        String lastName;
        String race;
        String characterClass;
        String personality;
        String appearance;
        String role;
        int age;
        int strength;
        int intelligence;
        int agility;
        int constitution;
        int charisma;
        String phrases;

        public Character(String firstName, String lastName, String race, String characterClass, String personality, String appearance, String role, int age, int strength, int intelligence, int agility, int constitution, int charisma, String phrases) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.race = race;
            this.characterClass = characterClass;
            this.personality = personality;
            this.appearance = appearance;
            this.role = role;
            this.age = age;
            this.strength = strength;
            this.intelligence = intelligence;
            this.agility = agility;
            this.constitution = constitution;
            this.charisma = charisma;
            this.phrases = phrases;
        }

        @Override
        public String toString() {
            return String.format("Имя: %s %s\nРаса: %s\nКласс: %s\nЛичность: %s\nВнешность: %s\nРоль в сюжете: %s\nВозраст: %d\nСила: %d\nИнтеллект: %d\nЛовкость: %d\nТелосложение: %d\nХаризма: %d\nФразы: %s\n",
                    firstName, lastName, race, characterClass, personality, appearance, role, age, strength, intelligence, agility, constitution, charisma, phrases);
        }
    }
}
