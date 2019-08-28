package com.sergio.importer.parser;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

@Component
public class CsvParser implements Parser {

    @Override
    public <T> Iterator<T> parse(InputStream input) throws IOException {

        CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
        CsvMapper csvMapper = new CsvMapper();

        return csvMapper.readerFor(Map.class).with(csvSchema).readValues(input);
    }
}
