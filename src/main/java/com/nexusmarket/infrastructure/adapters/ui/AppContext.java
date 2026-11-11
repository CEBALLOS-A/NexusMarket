package com.nexusmarket.infrastructure.adapters.ui;

import com.nexusmarket.domain.service.AuthService;
import com.nexusmarket.domain.service.BuyerService;
import com.nexusmarket.domain.service.CartItemService;
import com.nexusmarket.domain.service.CartService;
import com.nexusmarket.domain.service.InventoryService;
import com.nexusmarket.domain.service.OrderService;
import com.nexusmarket.domain.service.ProductReturnService;
import com.nexusmarket.domain.service.ProductService;
import com.nexusmarket.domain.service.ProductVariantService;
import com.nexusmarket.domain.service.SellerService;
import com.nexusmarket.domain.service.UserService;
import com.nexusmarket.infrastructure.adapters.persistence.DatabaseService;

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
