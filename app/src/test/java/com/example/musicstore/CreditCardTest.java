package com.example.musicstore;


import com.example.musicstore.repository.models.CreditCard;
import static org.junit.Assert.*;
import org.junit.Test;

public class CreditCardTest {

    @Test
    public void creditCard_isEmpty(){
        CreditCard creditCard = new CreditCard(
                "",
                "Aneudy Vargas",
                "01/24",
                123
        );

        assertFalse(creditCard.isValid());
    }

    // expect to be 16 digits
    @Test
    public void creditCardDigits_isIncomplete(){
        CreditCard creditCard = new CreditCard(
                "12341234123412",
                "Aneudy Vargas",
                "01/24",
                123
        );
        assertFalse(creditCard.isValid());
    }

    @Test
    public void creditCardHolder_isEmpty(){
        CreditCard creditCard = new CreditCard(
                "1234123412341234",
                "",
                "01/24",
                123
        );
        assertFalse(creditCard.isValid());
    }

    @Test
    public void creditCardExpiration_Invalid(){
        CreditCard creditCard = new CreditCard(
                "1234123412341234",
                "Aneudy Vargas",
                "01344",
                123
        );
        assertFalse(creditCard.isValid());
    }

    @Test
    public void creditCardCod_isInvalid(){
        CreditCard creditCard = new CreditCard(
                "1234123412341234",
                "Aneudy Vargas",
                "01/24",
                1
        );
        assertFalse(creditCard.isValid());
    }

    @Test
    public void creditCard_IsCorrect(){
        CreditCard creditCard = new CreditCard(
                "1234123412341234",
                "Aneudy Vargas",
                "01/24",
                123
        );
        assertTrue(creditCard.isValid());
    }
}
