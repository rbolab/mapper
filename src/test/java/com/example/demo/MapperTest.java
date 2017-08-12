package com.example.demo;

import com.example.demo.domain.Benchmark;
import com.example.demo.domain.BenchmarkType;
import com.example.demo.domain.KeyValue;
import com.example.demo.mapping.FolioMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by biba on 12/08/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MapperTest {

    private List<KeyValue> keyValues;
    private List<List<KeyValue>> items;

    @Before
    public void setUp() throws Exception {
        items = new ArrayList<>();

        keyValues = new ArrayList<>();
        keyValues.add(new KeyValue("setId", 1));
        keyValues.add(new KeyValue("setName", "WNT Fund"));
        items.add(keyValues);

        keyValues = new ArrayList<>();
        keyValues.add(new KeyValue("setId", 2));
        keyValues.add(new KeyValue("setName", "Bridgewater"));
        items.add(keyValues);
    }

    @Test
    public void testMapper(){
        FolioMapper<Benchmark> mapper = new FolioMapper<>(Benchmark.class);
        Benchmark benchmark = mapper.map(keyValues);
        System.out.println(benchmark.toString());
    }

    @Test
    public void testListMapper(){
        FolioMapper<Benchmark> mapper = new FolioMapper<>(Benchmark.class,
                Collections.singletonList(new KeyValue("setType", BenchmarkType.PROGRAM)));
        List<Benchmark> benchmarks = mapper.mapList(items);
        System.out.println(benchmarks.toString());
    }
}
