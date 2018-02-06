package ru.job4j.professions;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Professions test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 04/02/2018
 */
public class ProfessionsTest {
    @Test
    public void whenTeacherTeachesHuman() {
        Teacher teacher = new Teacher("Andrei", 7);
        Human human = new Human();
        for (int i = 0; i < 5; i++) {
            teacher.teacherWorks(human);
        }
        int result = human.getEducationLvl();
        int expected = 45;
        assertThat(result, is(expected));
    }

    @Test
    public void whenTeacherTeachesAndPunishesAdnDoctorHealsHuman() {
        Teacher teacher = new Teacher("Ivan", 2);
        Doctor doctor = new Doctor("Gregory House", 10);
        Human human = new Human("Petroich", 5);
        for (int i = 0; i < 11; i++) {
            teacher.teacherWorks(human);
        }
        doctor.doctorWorks(human);
        int[] expected = new int[] {25, 109};
        int[] result = new int[] {human.getEducationLvl(), human.getHealthLvl()};
        assertThat(result, is(expected));
    }

    @Test
    public void whenEngineerCreatesMechanismMechanismWorksAndEngineerRepairsMechanism() {
        Engineer engineer = new Engineer("Kulibin", 10);
        Mechanism mechanism = engineer.createNewMechanism("Velosiped");
        for (int i = 0; i < 50; i++) {
            mechanism.work();
        }
        for (int i = 0; i < 4; i++) {
            engineer.engineerWorks(mechanism);
        }
        int result = mechanism.condition;
        int expected = 90;
        assertThat(result, is(expected));
    }
}