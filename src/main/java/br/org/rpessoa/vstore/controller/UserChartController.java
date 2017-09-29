package br.org.rpessoa.vstore.controller;

import br.org.rpessoa.vstore.dao.GenericDAO;
import br.org.rpessoa.vstore.dao.ProductDAO;
import br.org.rpessoa.vstore.dao.UserDAO;
import br.org.rpessoa.vstore.exception.BadRequestException;
import br.org.rpessoa.vstore.exception.DatabaseException;
import br.org.rpessoa.vstore.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

@RestController
@RequestMapping("/users/{id}/chart")
public class UserChartController {

    private static final Integer PAYMENT_BY_CARD = 0;
    private static final Integer PAYMENT_BY_BILL = 1;

    @PutMapping("/products/{productId}")
    @PreAuthorize("userId == authentication.principal.id || hasRole('ADMIN')")
    public ResponseEntity<?> add(@PathVariable(name = "id") Integer userId,
                                 @PathVariable(name = "productId") Integer productId,
                                 @RequestParam(value = "items", defaultValue = "1") Integer items) throws DatabaseException {
        GenericDAO<UserCart> usarCartDAO = new GenericDAO<>();
        UserCart userCart = usarCartDAO.findById(UserCart.class, userId);

        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findById(Product.class, productId);

        GenericDAO<UserCartProduct> userCartProductDAO = new GenericDAO<>();

        UserCartProduct productCart = new UserCartProduct();

        productCart.setItems(items);
        productCart.setProduct(product);
        productCart.setProductId(productId);
        productCart.setUserCart(userCart);
        productCart.setUserId(userId);
        userCartProductDAO.saveOrUpdate(productCart);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/products/")
    @PreAuthorize("userId == authentication.principal.id || hasRole('ADMIN')")
    public ResponseEntity<?> list(@PathVariable(name = "id") Integer userId) {
        GenericDAO<UserCart> usarCartDAO = new GenericDAO<>();
        UserCart userCart = usarCartDAO.findById(UserCart.class, userId);
        return ResponseEntity.ok(userCart.getProducts());
    }

    @DeleteMapping("/products/{productId}")
    @PreAuthorize("userId == authentication.principal.id || hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer userId) throws DatabaseException {
        GenericDAO<UserCart> usarCartDAO = new GenericDAO<>();
        usarCartDAO.remove(UserCart.class, userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/buy")
    @PreAuthorize("userId == authentication.principal.id || hasRole('ADMIN')")
    public ResponseEntity<?> buy(@PathVariable(value = "id") Integer userId,
                                 @RequestParam(name = "payment_method", defaultValue = "0")
                                         Integer paymentMethod,
                                 @RequestParam(name = "card_id", required = false) Integer cardId)
            throws BadRequestException, DatabaseException {

        if (paymentMethod == PAYMENT_BY_CARD && cardId == null)
            return ResponseEntity.badRequest().body("Card not set");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(User.class, userId);

        UserPayment payment = new UserPayment();
        payment.setType(paymentMethod);
        payment.setUser(user);

        if (paymentMethod == PAYMENT_BY_CARD) {
            UserCard userCard = user.getCards()
                    .stream()
                    .filter(x -> cardId.equals(x.getId())).findFirst()
                    .orElseThrow(() -> new BadRequestException("The card id is not valid"));
            payment.setUserCard(userCard);
        } else payment.setBillNumber(0);

        GenericDAO<UserPurchase> userPurchaseDAO = new GenericDAO<>();
        UserPurchase purchase = copyUserCart(user);
        userPurchaseDAO.saveOrUpdate(purchase);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("users/{userId}/purchases/{id}")
                .buildAndExpand(user.getId(), purchase.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    private UserPurchase copyUserCart(User user) {
        GenericDAO<UserCart> userCartDAO = new GenericDAO<>();
        UserCart userCart = userCartDAO.findById(UserCart.class, user.getId());

        UserPurchase purchase = new UserPurchase();
        purchase.setDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        purchase.setUser(user);
        purchase.setStatus(0);

        ProductDAO productDAO = new ProductDAO();
        for (UserCartProduct cartProduct : userCart.getProducts()) {
            UserPurchaseProduct purchaseProduct = new UserPurchaseProduct();
            purchaseProduct.setItems(cartProduct.getItems());

            Product p = productDAO.findById(Product.class, cartProduct.getProductId());
            purchaseProduct.setProduct(p);

            ProductValue value = productDAO.getCurrentValue(cartProduct.getProductId());
            purchaseProduct.setProductValue(value);

            purchase.addProduct(purchaseProduct);
        }

        return purchase;
    }
}
