package com.workintech.ecommerce.entity;

public enum Enum_Category {
    AYAKKABI(1L),
    TSİRT(2L),
    PANTOLON(3L),
    KAZAK(4L),
    CEKET(5L),
    ELBİSE(6L),
    ETEK(7L),
    GÖMLEK(8L)


    private final Long categoryId;

    Enum_Category(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
