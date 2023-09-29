package fourth.card;

import org.junit.jupiter.api.Test;
import seminars.fourth.card.CreditCard;
import seminars.fourth.card.PaymentForm;

import static org.mockito.Mockito.*;

class PaymentFormTest {

    /**
     * 4.2. Используя библиотеку Mockito, напишите модульные тесты для проверки функциональности формы оплаты на сайте.
     * * Вместо реальной кредитной карты используйте мок-объект.
     * Создайте класс `CreditCard` с методами `getCardNumber()`, `getCardHolder()`, `getExpiryDate()`, `getCvv()`, `charge(double amount)`.
     * Создайте класс `PaymentForm` с методом `pay(double amount)`.
     * В тестовом классе, создайте мок-объект для класса `CreditCard`.
     * Определите поведение мок-объекта с помощью метода `when()`.
     * Создайте объект класса `PaymentForm`, передайте ему мок-объект в качестве аргумента.
     * Вызовите метод `pay()` и убедитесь, что мок-объект вызывает метод `charge()`
     */
    @Test
    void testCharge() {
        CreditCard creditCard = mock(CreditCard.class);
        when(creditCard.getCardHolder()).thenReturn("Mike");
        when(creditCard.getCardNumber()).thenReturn("1234");
        when(creditCard.getExpiryDate()).thenReturn("31.01.2019");
        when(creditCard.getCvv()).thenReturn("000");

        PaymentForm paymentForm = new PaymentForm(creditCard);
        paymentForm.pay(1000);
        verify(creditCard, times(1)).charge(1000);
    }

}