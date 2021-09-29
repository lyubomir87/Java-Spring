package com.softuni.springdataautomapping.domains.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private User buyerId;
    private List<Game> boughtGames;

    public Order() {
    }

    @ManyToOne
    @JoinColumn(name = "buyer_id",referencedColumnName = "id")
    public User getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(User buyerId) {
        this.buyerId = buyerId;
    }

    @ManyToMany
    @JoinTable(name = "orders_games",
            joinColumns = @JoinColumn(name = "order_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id",referencedColumnName = "id")
    )
    public List<Game> getBoughtGames() {
        return boughtGames;
    }

    public void setBoughtGames(List<Game> boughtGames) {
        this.boughtGames = boughtGames;
    }
}
