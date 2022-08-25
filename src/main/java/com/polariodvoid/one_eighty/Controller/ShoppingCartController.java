package com.polariodvoid.one_eighty.Controller;

import com.polariodvoid.one_eighty.ControllerHelper;
import com.polariodvoid.one_eighty.Model.CartItem;
import com.polariodvoid.one_eighty.Model.User;
import com.polariodvoid.one_eighty.Service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Controller
public class ShoppingCartController {
    @Autowired
    private ControllerHelper controllerHelper;
    @Autowired private ShoppingCartService cartService;


    @GetMapping("/cart")
    public String viewCart(Model model, HttpServletRequest request) {
        User user = controllerHelper.getAuthenticatedUser(request);
        List<CartItem> cartItems = cartService.listCartItems(user);

        float estimatedTotal = 0.0F;

        for (CartItem item : cartItems) {
            estimatedTotal += item.getSubtotal();
        }

        return "shopping_cart";
    }
}
