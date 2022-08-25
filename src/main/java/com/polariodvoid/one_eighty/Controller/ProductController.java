package com.polariodvoid.one_eighty.Controller;

import com.polariodvoid.one_eighty.ControllerHelper;
import com.polariodvoid.one_eighty.Exceptions.ProductNotFoundException;
import com.polariodvoid.one_eighty.Model.Product;
import com.polariodvoid.one_eighty.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
//    @Autowired private CategoryService categoryService;

    @Autowired private ControllerHelper controllerHelper;



    @GetMapping("/p/{product_alias}")
    public String viewProductDetail(@PathVariable("product_alias") String alias, Model model,
                                    HttpServletRequest request) {

        try {
            Product product = productService.getProduct(alias);

//            model.addAttribute("listCategoryParents", listCategoryParents);
            model.addAttribute("product", product);
//            model.addAttribute("listReviews", listReviews);
            model.addAttribute("pageTitle", product.getShortName());

            return "product/product_detail";
        } catch (ProductNotFoundException e) {
            return "error/404";
        }
    }

    @GetMapping("/search")
    public List<Product> searchProduct(String keyword) {
        return productService.search(keyword);
    }
}
