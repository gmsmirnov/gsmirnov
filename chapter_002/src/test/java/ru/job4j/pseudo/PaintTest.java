package ru.job4j.pseudo;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests Paint.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 17/02/2018
 */
public class PaintTest {
    @Test
    public void whenDrawSquare() {
        // получаем ссылку на стандартный вывод в консоль.
        PrintStream stdout = System.out;
        // Создаем буфур для хранения вывода.
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //Заменяем стандартный вывод на вывод в пямять для тестирования.
        System.setOut(new PrintStream(out));
        // выполняем действия пишушиее в консоль.
        new Paint().draw(new Square(4));
        // проверяем результат вычисления
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("++++").append(System.lineSeparator())
                .append("++++").append(System.lineSeparator())
                .append("++++").append(System.lineSeparator())
                .append("++++").append(System.lineSeparator())
                .toString()));
        // возвращаем обратно стандартный вывод в консоль.
        System.setOut(stdout);
    }
}