package stonetree.com.meals.session;

import stonetree.com.meals.core.model.Cart;

public class Session {

    private static Session session;

    private boolean networkOnline;

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

    public void setNetworkOnline(boolean networkOnline) {
        this.networkOnline = networkOnline;
    }

    public boolean isNetworkOnline() {
        return networkOnline;
    }

    public void purge() {
        this.cart = null;
    }
}
