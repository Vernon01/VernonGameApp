package com.example.vernon.gameapp.Domain;

import java.io.Serializable;

/**
 * Created by VERNON on 2016/04/18.
 */
public class GamePrices implements Serializable {

    private Integer id;
    private String title; //The title of the game being rated
    private String year; //Year game was released
    private String price; //The price of the game
    private String machine; //Is the the rated on a console like xbox or playstation or a computer

    private GamePrices()
    {}

    public GamePrices (Builder builder)
    {
        title = builder.title;
        year = builder.year;
        price = builder.price;
        machine = builder.machine;
        id = builder.id;
    }

    public String getTitle() {return title;}
    public String getYear() {return year;}
    public String getPrice() {return price;}
    public String getMachine() {return machine;}
    public Integer getId() {return id;}

    public static class Builder {

        private String title;
        private String year;
        private String price;
        private String machine;
        private Integer id;

        public Builder(String title) {
            this.title = title;
        }

        public Builder id(Integer value)
        {
            this.id = value;
            return this;
        }

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Builder year(String year){
            this.year = year;
            return this;
        }

        public Builder price(String price){
            this.price = price;
            return this;
        }

        public Builder machine(String machine){
            this.machine = machine;
            return this;
        }

        public Builder copy(GamePrices value)
        {
            this.title = value.getTitle();
            this.year = value.getYear();
            this.price = value.getPrice();
            this.machine = value.getMachine();
            this.id = value.getId();
            return this;
        }

        public GamePrices build() {return new GamePrices(this);}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GamePrices)) return false;

        GamePrices that = (GamePrices) o;

        if (title != null ? !title.equals(that.title) : that.title != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {return title != null ? title.hashCode() : 0;}

    @Override
    public String toString() {
        return "Game being priced{" +
                "is the following =" + title +
                '}';
    }
}
