package br.org.rpessoa.vstore.controller;

import br.org.rpessoa.vstore.dao.GenericDAO;
import br.org.rpessoa.vstore.dao.UserDAO;
import br.org.rpessoa.vstore.exception.DatabaseException;
import br.org.rpessoa.vstore.model.User;
import br.org.rpessoa.vstore.model.UserPurchase;
import br.org.rpessoa.vstore.model.UserPurchaseId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/purchases")
public class PurchaseController {

    @GetMapping
    @PreAuthorize("userId == authentication.principal.getId() || hasRole('ADMIN')")
    public ResponseEntity<?> list(@PathVariable(name = "userId") Integer userId) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(User.class, userId);
        return ResponseEntity.ok(user.getPurchases());
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("userId == authentication.principal.getId() || hasRole('ADMIN')")
    public ResponseEntity<?> get(@PathVariable(name = "userId") Integer userId,
                                 @PathVariable(name = "id") Integer id) {
        GenericDAO<UserPurchase> purchaseDAO = new GenericDAO<>();
        UserPurchaseId purchaseId = new UserPurchaseId();
        purchaseId.setId(id);
        purchaseId.setUserId(userId);
        UserPurchase purchase = purchaseDAO.findById(UserPurchase.class, purchaseId);
        return ResponseEntity.ok(purchase);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("userId == authentication.principal.getId() || hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable(name = "userId") Integer userId,
                                 @PathVariable(name = "id") Integer id) throws DatabaseException {
        GenericDAO<UserPurchase> purchaseDAO = new GenericDAO<>();
        UserPurchaseId purchaseId = new UserPurchaseId();
        purchaseId.setId(id);
        purchaseId.setUserId(userId);

        purchaseDAO.remove(UserPurchase.class, purchaseId);

        return ResponseEntity.noContent().build();
    }
}
