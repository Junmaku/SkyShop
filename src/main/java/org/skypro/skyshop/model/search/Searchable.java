package org.skypro.skyshop.model.search;

import org.springframework.stereotype.Component;

import java.util.UUID;

public interface Searchable extends Comparable<Searchable> {

    String getSearchTerm();

    String getContentType();

    String getName();

    @Override
    default int compareTo(Searchable o) {
        int tempInt = Integer.compare(getName().length(), o.getName().length());
        if (tempInt != 0) {
            return tempInt;
        } else {
            return getName().compareTo(o.getName());
        }
    }

    default String getStringRepresentation() {
        return getSearchTerm() + " - " + getContentType();
    }


    UUID getId();


}
