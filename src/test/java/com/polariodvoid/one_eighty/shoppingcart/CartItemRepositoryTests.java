package com.polariodvoid.one_eighty.shoppingcart;

import com.polariodvoid.one_eighty.CartItemRepository;
import com.polariodvoid.one_eighty.Model.CartItem;
import com.polariodvoid.one_eighty.Model.Product;
import com.polariodvoid.one_eighty.Model.User;
import com.polariodvoid.one_eighty.OneEightyApplication;
import com.polariodvoid.one_eighty.ProductRepository;
import com.polariodvoid.one_eighty.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = OneEightyApplication.class)
public class CartItemRepositoryTests {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // CREATE
    @Test
    public void testSaveItem() {
        Product product = productRepository.save(getProduct());
        User user = userRepository.save(getUser());

        CartItem item = new CartItem();
        item.setUser(user);
        item.setProduct(product);
        item.setQuantity(10);

        CartItem savedItem = cartItemRepository.save(item);
        assertThat(savedItem.getId()).isGreaterThan(0);
    }

    // READ
    @Test
    public void testFindItem() {
        Product product = productRepository.save(getProduct());
        User user = userRepository.save(getUser());
        int quantity = 7;

        CartItem item = new CartItem();
        item.setUser(user);
        item.setProduct(product);
        item.setQuantity(quantity);

        cartItemRepository.save(item);
        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);

        assertThat(cartItem).isNotNull();
        assertThat(cartItem.getQuantity()).isEqualTo(quantity);
    }

    @Test
    public void testUpdateQuantity() {
        Product product = productRepository.save(getProduct());
        User user = userRepository.save(getUser());

        CartItem item = new CartItem();
        item.setUser(user);
        item.setProduct(product);
        item.setQuantity(100);

        CartItem cartItem = cartItemRepository.save(item);
        assertThat(cartItem.getQuantity()).isEqualTo(100);

        cartItem.setQuantity(5);
        cartItem = cartItemRepository.save(item);
        assertThat(cartItem.getQuantity()).isEqualTo(5);
    }

//    @Test
//    public void testDeleteCartItemByUserAndProduct() {
//        Product product = productRepository.save(getProduct());
//        User user = userRepository.save(getUser());
//
//        CartItem item = new CartItem();
//        item.setUser(user);
//        item.setProduct(product);
//        item.setQuantity(10);
//
//        cartItemRepository.save(item);
//        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);
//        assertThat(cartItem).isNotNull();
//
//        cartItemRepository.deleteByUserAndProduct(user.getId(), product.getId());
//        cartItem = cartItemRepository.findByUserAndProduct(user, product);
//        assertThat(cartItem).isNull();
//    }

    @Test
    public void testDeleteCartByUser() {
        Product product = productRepository.save(getProduct());
        User user = userRepository.save(getUser());

        CartItem item = new CartItem();
        item.setUser(user);
        item.setProduct(product);
        item.setQuantity(10);

        cartItemRepository.save(item);
        List<CartItem> cartItemList = cartItemRepository.findByUser(user);
        assertThat(cartItemList.size()).isGreaterThan(0);

        cartItemRepository.deleteByUser(user.getId());
        cartItemList = cartItemRepository.findByUser(user);
        assertThat(cartItemList.size()).isEqualTo(0);
    }

    private Product getProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("Apple iPhone 14");
        product.setAlias("iPhone 14");
        product.setShortDescription("The new apple iphone");
        product.setFullDescription("Bundling with superb functions and next-gen features, you can buy Apple iPhone 14 online. It comes with Apple’s latest A16 Bionic chip that offers the best CPU performance in a smartphone. It is a 6 core CPU that has 2 performance cores that boosts up the phone’s maximum performance potential and 4 efficiency cores that optimize the phone’s overall performance.");
        product.setMainImage("iphone14.jpeg");
        return product;
    }

    private User getUser() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Adam");
        user.setLastName("Afilaka");
        user.setEmail("adam@apple.com");
        user.setPassword("NotSoSecurePassword");
        return user;
    }
}
