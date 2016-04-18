package com.example.vernon.gameapp.Domain;

import java.io.Serializable;

/**
 * Created by VERNON on 2016/04/18.
 */
public class UnderRatedGames implements Serializable {

    private Integer id;
    private String title; //The title of the game being rated
    private String year; //Year game was released
    private String reason; //The reason why the game is under rated by other ratings

    private UnderRatedGames()
    {}

    public UnderRatedGames (Builder builder)
    {
        title = builder.title;
        year = builder.year;
        reason = builder.reason;
        id = builder.id;
    }

    public String getTitle() {return title;}
    public String getYear() {return year;}
    public String getReason() {return reason;}
    public Integer getId() {return id;}

    public static class Builder {

        private String title;
        private String year;
        private String reason;
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

        public Builder reason(String reason){
            this.reason = reason;
            return this;
        }

        public Builder copy(UnderRatedGames value)
        {
            this.title = value.getTitle();
            this.year = value.getYear();
            this.reason = value.getReason();
            this.id = value.getId();
            return this;
        }

        public UnderRatedGames build() {return new UnderRatedGames(this);}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnderRatedGames)) return false;

        UnderRatedGames that = (UnderRatedGames) o;

        if (title != null ? !title.equals(that.title) : that.title != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {return title != null ? title.hashCode() : 0;}

    @Override
    public String toString() {
        return "Game that is under rated{" +
                "is =" + title +
                '}';
    }
}
