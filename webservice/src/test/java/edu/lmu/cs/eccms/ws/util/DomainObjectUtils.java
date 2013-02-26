package edu.lmu.cs.eccms.ws.util;

import java.util.ArrayList;
import java.util.List;

import edu.lmu.cs.eccms.ws.domain.Item;
import edu.lmu.cs.eccms.ws.domain.User;
import edu.lmu.cs.eccms.ws.domain.UserRole;
import edu.lmu.cs.eccms.ws.types.Role;

/**
 * Holder for utility methods of use to multiple unit tests.
 */
public class DomainObjectUtils {

    /**
     * Helper factory method for creating new user objects.
     */
    public static User createUserObject(String login, String email, String challenge, Role... roles) {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setChallengeRequest(challenge);

        List<UserRole> userRoles = new ArrayList<UserRole>();
        for (Role role: roles) {
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRoles.add(userRole);
        }

        user.setRoles(userRoles);
        return user;
    }

    public static Item createItemObject(Boolean active, String name, Integer sku, String description,
            Double price) {
        Item item = new Item();
        item.setActive(active);
        item.setName(name);
        item.setSku(sku);
        item.setDescription(description);
        item.setPrice(price);

        return item;
    }

}
