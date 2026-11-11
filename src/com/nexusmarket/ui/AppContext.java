package com.nexusmarket.ui;

import com.nexusmarket.service.AuthService;
import com.nexusmarket.service.BuyerService;
import com.nexusmarket.service.CartItemService;
import com.nexusmarket.service.CartService;
import com.nexusmarket.service.InventoryService;
import com.nexusmarket.service.OrderService;
import com.nexusmarket.service.ProductReturnService;
import com.nexusmarket.service.ProductService;
import com.nexusmarket.service.ProductVariantService;
import com.nexusmarket.service.SellerService;
import com.nexusmarket.service.UserService;
import com.nexusmarket.persistence.DatabaseService;

/** Wiring of the services shared by all the windows. */
class AppContext {

    final UserService userService = new UserService();
    final ProductService productService;
    final SellerService sellerService;
    final BuyerService buyerService;
    final AuthService authService;
    final DatabaseService databaseService = new DatabaseService();

    AppContext() {
        productService = new ProductService(new ProductVariantService());
        sellerService = new SellerService(productService, new InventoryService());
        buyerService = new BuyerService(new CartService(new CartItemService()),
                new OrderService(), new ProductReturnService());
        authService = new AuthService(userService, buyerService, sellerService);
    }
}
