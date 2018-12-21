package stonetree.com.meals.core.model;

public class Session {

    private static Session session;

    private Cart cart = new Cart();

    public static Session getInstance() {
        if (session == null)
            session = new Session();

        return session;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void purge() {
        this.cart.getMeal().getCustomIngredients().clear();
        this.cart.setPrice(0);
    }
}
