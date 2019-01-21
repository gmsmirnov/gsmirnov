package ru.job4j.condition;

/**
 * Stupid Bot.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 16/01/2018
 * @version 1.0
 */
public class DummyBot {
    public String answer(String question) {
        String rsl = "Это ставит меня в тупик. Спросите другой вопрос.";
        if ("Привет, Бот.".equals(question)) {
            rsl = "Привет, умник.";
        } else if ("Пока.".equals(question)) {
            rsl = "До скорой встречи.";
        }
        return rsl;
    }
}