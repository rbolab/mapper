package com.example.demo.domain;

/**
 * Created by biba on 12/08/2017.
 */
public enum BenchmarkType {
    PROGRAM(1),
    INDICE(2);

    private Integer id;

    BenchmarkType(Integer id) {
        this.id = id;
    }
}
