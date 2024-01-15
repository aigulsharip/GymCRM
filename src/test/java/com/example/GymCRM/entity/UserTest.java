package com.example.GymCRM.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

public class UserTest {

    @Test
    public void testUserCreation() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void testGetterAndSetterMethods() {
        User user = new User();

        user.setId(1L);
        assertEquals(1L, user.getId());

        user.setFirstName("John");
        assertEquals("John", user.getFirstName());

        user.setLastName("Doe");
        assertEquals("Doe", user.getLastName());

        user.setUsername("johndoe");
        assertEquals("johndoe", user.getUsername());

        user.setPassword("password123");
        assertEquals("password123", user.getPassword());

        user.setIsActive(true);
        assertTrue(user.getIsActive());
    }

    @Test
    public void testEqualsMethod() {
        User user1 = new User();
        user1.setId(1L);

        User user2 = new User();
        user2.setId(1L);

        User user3 = new User();
        user3.setId(2L);

        assertEquals(user1, user2);
        assertNotEquals(user1, user3);
    }

    @Test
    public void testHashCodeMethod() {
        User user1 = new User();
        user1.setId(1L);

        User user2 = new User();
        user2.setId(1L);

        User user3 = new User();
        user3.setId(2L);

        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }
}
