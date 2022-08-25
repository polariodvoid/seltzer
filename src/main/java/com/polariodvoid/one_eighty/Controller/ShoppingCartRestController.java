package com.polariodvoid.one_eighty.Controller;

import com.polariodvoid.one_eighty.Exceptions.UserNotFoundException;
import com.polariodvoid.one_eighty.Model.User;
import com.polariodvoid.one_eighty.Service.ShoppingCartService;
import com.polariodvoid.one_eighty.Service.UserService;
import com.polariodvoid.one_eighty.ShoppingCartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@RestController
public class ShoppingCartRestController {
    @Autowired
    private ShoppingCartService cartService;
    @Autowired private UserService userService;



    @PostMapping("/cart/add/{productId}/{quantity}")
    public String addProductToCart(@PathVariable("productId") Integer productId,
                                   @PathVariable("quantity") Integer quantity, HttpServletRequest request) {

        try {
            User user = getAuthenticatedUser(request);
            Integer updatedQuantity = cartService.addProduct(productId, quantity, user);

            return updatedQuantity + " item(s) of this product were added to your shopping cart.";
        } catch (UserNotFoundException ex) {
            return "You must login to add this product to cart.";
        } catch (ShoppingCartException ex) {
            return ex.getMessage();
        }

    }

    private User getAuthenticatedUser(HttpServletRequest request)
            throws UserNotFoundException {
        //String email = request.getQueryString(); //email
        String email = "abcd@gmail.com";
        return userService.findByEmail(email);
    }

    @PostMapping("/cart/update/{productId}/{quantity}")
    public String updateQuantity(@PathVariable("productId") Integer productId,
                                 @PathVariable("quantity") Integer quantity, HttpServletRequest request) {
        try {
            User customer = getAuthenticatedUser(request);
            float subtotal = cartService.updateQuantity(productId, quantity, customer);

            return String.valueOf(subtotal);
        } catch (UserNotFoundException ex) {
            return "You must login to change quantity of product.";
        }
    }

    @DeleteMapping("/cart/remove/{productId}")
    public String removeProduct(@PathVariable("productId") Integer productId,
                                HttpServletRequest request) {
        try {
            User user = getAuthenticatedUser(request);
            cartService.removeProduct(productId, user);

            return "The product has been removed from your shopping cart.";

        } catch (UserNotFoundException e) {
            return "You must login to remove product.";
        }
    }
}
