package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private final String title;
    private final String text;
    private final UUID id;

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.title;
    }

    @Override
    public String getSearchTerm() {
        return this.text;
    }

    public Article(String title, String text, UUID id) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Поле 'title' не может быть пустым.");
        }
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Поле 'text' не может быть пустым.");
        }
        this.title = title;
        this.text = text;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Название статьи:\n" + title +
                "\nТекст статьи: \n" + text;
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getStringRepresentation() {
        return toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }
}
