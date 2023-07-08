package ru.alexandrov.springcourse.models;

import org.springframework.data.domain.Sort;

public enum BookSorting {
    BY_YEAR_ASCENDING(Sort.Direction.ASC, "year"),
    BY_YEAR_DESCENDING(Sort.Direction.DESC, "year"),
    BY_TITLE_ASCENDING(Sort.Direction.ASC, "title"),
    BY_TITLE_DESCENDING(Sort.Direction.DESC, "title"), NONE(null, null);

    BookSorting(Sort.Direction direction, String property) {
        this.direction = direction;
        this.property = property;
    }

    private final Sort.Direction direction;
    private final String property;

    public Sort.Direction getDirection() {
        return direction;
    }

    public String getProperty() {
        return property;
    }
//
//    @Override
//    public String toString() {
//        return "BookSorting{" +
//                "direction=" + direction +
//                ", property='" + property + '\'' +
//                '}';
//    }
}
