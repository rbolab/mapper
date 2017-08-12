package com.example.demo;

import com.example.demo.domain.Benchmark;
import com.example.demo.domain.BenchmarkType;
import com.example.demo.domain.KeyValue;
import com.example.demo.mapping.FolioMapper;
import com.example.demo.service.FolioDataFinder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MapperTest {

    private List<KeyValue> keyValues;
    private List<List<KeyValue>> items;

    @MockBean
    private FolioDataFinder folioDataFinder;

    @Before
    public void setUp() throws Exception {
        items = new ArrayList<>();

        keyValues = new ArrayList<>();
        keyValues.add(new KeyValue("setId", 25));
        keyValues.add(new KeyValue("setName", "Fund 25"));
        items.add(keyValues);

        keyValues = new ArrayList<>();
        keyValues.add(new KeyValue("setId", 39));
        keyValues.add(new KeyValue("setName", "Fund 39"));
        items.add(keyValues);

        keyValues = new ArrayList<>();
        keyValues.add(new KeyValue("setId", 38));
        keyValues.add(new KeyValue("setName", "Fund 38"));
        items.add(keyValues);

        given(folioDataFinder.findAllTradinfAdvisorInfos()).willReturn(items);

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
        List<Benchmark> benchmarks = mapper.mapList(folioDataFinder.findAllTradinfAdvisorInfos());
        System.out.println(benchmarks.toString());
    }
}
