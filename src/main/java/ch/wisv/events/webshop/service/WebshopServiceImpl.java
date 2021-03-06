package ch.wisv.events.webshop.service;

import ch.wisv.events.core.model.event.Event;
import ch.wisv.events.core.model.product.Product;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * WebshopServiceImpl class.
 */
@Service
public class WebshopServiceImpl implements WebshopService {

    /**
     * Filter the products in a Event which are not sold now.
     *
     * @param event of type Event
     *
     * @return Event
     */
    @Override
    public Event filterEventProductNotSalable(Event event) {
        List<Product> salableProducts = event.getProducts().stream()
                .filter(this.filterProductBySellInterval())
                .filter(this.filterProductSoldOut())
                .collect(Collectors.toList());
        event.setProducts(salableProducts);

        return event;
    }

    /**
     * Filter the products by events if they can be sold or not.
     *
     * @param events of type List
     *
     * @return List
     */
    @Override
    public List<Event> filterEventProductNotSalable(List<Event> events) {
        return events.stream().map(this::filterEventProductNotSalable)
                .filter(event -> event.getProducts().size() > 0)
                .collect(Collectors.toList());
    }

    /**
     * Check if Product is in sell interval.
     *
     * @return Predicate
     */
    private Predicate<Product> filterProductBySellInterval() {
        return product -> LocalDateTime.now().isAfter(product.getSellStart()) && LocalDateTime.now().isBefore(product.getSellEnd());
    }

    /**
     * Check if Product is not sold out.
     *
     * @return Predicate
     */
    private Predicate<Product> filterProductSoldOut() {
        return product -> product.getMaxSold() == null || product.getSold() != product.getMaxSold();
    }
}
