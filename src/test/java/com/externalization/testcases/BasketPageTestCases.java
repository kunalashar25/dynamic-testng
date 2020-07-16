package com.externalization.testcases;

import org.testng.annotations.Test;

public class BasketPageTestCases {

    @Test
    public void checkItemPresent() {
        System.out.println("item present to basket");
    }

    @Test
    public void updateItemQuantity() {
        System.out.println("updating item quantity");
    }

    @Test
    public void updateItemPrice() {
        System.out.println("updating item price");
    }

    @Test
    public void checkoutBasket() {
        System.out.println("basket checkedOut");
    }
}
