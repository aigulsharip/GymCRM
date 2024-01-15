package com.example.GymCRM.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class TraineeTest {

    @Test
    public void testTraineeCreation() {
        Trainee trainee = new Trainee();
        assertNotNull(trainee);
    }

    @Test
    public void testGetterAndSetterMethods() {
        Trainee trainee = new Trainee();

        trainee.setId(1L);
        assertEquals(1L, trainee.getId());

        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        trainee.setDateOfBirth(dateOfBirth);
        assertEquals(dateOfBirth, trainee.getDateOfBirth());

        trainee.setAddress("123 Main St");
        assertEquals("123 Main St", trainee.getAddress());

        User user = new User();
        user.setId(1L);
        trainee.setUser(user);
        assertEquals(user, trainee.getUser());
    }

    @Test
    public void testAssociationWithUser() {
        Trainee trainee = new Trainee();

        assertNull(trainee.getUser());

        User user = new User();
        user.setId(1L);
        trainee.setUser(user);

        assertNotNull(trainee.getUser());
        assertEquals(1L, trainee.getUser().getId());
    }
}
