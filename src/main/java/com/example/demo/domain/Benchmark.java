package com.example.demo.domain;

/**
 * Created by biba on 12/08/2017.
 */
public class Benchmark {

    private Integer id;
    private String name;
    private BenchmarkType type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BenchmarkType getType() {
        return type;
    }

    public void setType(BenchmarkType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Benchmark{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
