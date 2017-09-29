package br.org.rpessoa.vstore.controller;

import br.org.rpessoa.vstore.dao.GenericDAO;
import br.org.rpessoa.vstore.dao.UserDAO;
import br.org.rpessoa.vstore.exception.DatabaseException;
import br.org.rpessoa.vstore.model.User;
import br.org.rpessoa.vstore.model.UserCard;
import br.org.rpessoa.vstore.model.UserCardId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    // BASIC CRUD Resources
    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> add(@RequestBody User user) throws DatabaseException {
        GenericDAO<User> userDAO = new GenericDAO<>();
        userDAO.saveOrUpdate(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Collection<User>> list() {
        UserDAO userDAO = new UserDAO();
        return ResponseEntity.ok(userDAO.listAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("id == authentication.principal.getId() || hasRole('ADMIN')")
    public ResponseEntity<User> get(@PathVariable(value = "id") Integer id) {
        GenericDAO<User> userDAO = new GenericDAO<>();
        return ResponseEntity.ok(userDAO.findById(User.class, id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("id == authentication.principal.getId() || hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody User product) throws DatabaseException {
        GenericDAO<User> userDAO = new GenericDAO<>();
        product.setId(id);
        userDAO.saveOrUpdate(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("id == authentication.principal.getId() || hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) throws DatabaseException {
        GenericDAO<User> userDAO = new GenericDAO<>();
        userDAO.remove(User.class, id);
        return ResponseEntity.noContent().build();
    }

    // CRUD related to Credit Cards

    @PostMapping("/{id}/cards")
    @PreAuthorize("id == authentication.principal.getId() || hasRole('ADMIN')")
    public ResponseEntity<?> addCard(@PathVariable(value = "id") Integer id,
                                     @RequestBody UserCard userCard) throws DatabaseException {
        GenericDAO<User> userDAO = new GenericDAO<>();
        User user = userDAO.findById(User.class, id);
        GenericDAO<UserCard> userCardDAO = new GenericDAO<>();
        userCard.setUser(user);
        userCardDAO.saveOrUpdate(userCard);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}/cards/")
                .buildAndExpand(userCard.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/cards/")
    @PreAuthorize("id == authentication.principal.getId() || hasRole('ADMIN')")
    public ResponseEntity<?> listCards(@PathVariable(value = "id") Integer id) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(User.class, id);
        return ResponseEntity.ok(user.getCards());
    }

    @GetMapping("/{id}/cards/{cardId}")
    @PreAuthorize("id == authentication.principal.getId() || hasRole('ADMIN')")
    public ResponseEntity<?> getCard(@PathVariable(value = "id") Integer id,
                                        @PathVariable(value = "cardId") Integer cardId) {
        GenericDAO<UserCard> userCardDAO = new GenericDAO<>();
        UserCardId userCardId = new UserCardId();
        userCardId.setUserId(id);
        userCardId.setId(cardId);
        UserCard card = userCardDAO.findById(UserCard.class, userCardId);
        return ResponseEntity.ok(card);
    }

    @DeleteMapping("/{id}/cards/{cardId}")
    @PreAuthorize("id == authentication.principal.getId() || hasRole('ADMIN')")
    public ResponseEntity<?> deleteCard(@PathVariable(value = "id") Integer id,
                                        @PathVariable(value = "cardId") Integer cardId) throws DatabaseException {
        GenericDAO<UserCard> userCardDAO = new GenericDAO<>();
        UserCardId userCardId = new UserCardId();
        userCardId.setUserId(id);
        userCardId.setId(cardId);
        userCardDAO.remove(UserCard.class, userCardId);
        return ResponseEntity.noContent().build();
    }
}
