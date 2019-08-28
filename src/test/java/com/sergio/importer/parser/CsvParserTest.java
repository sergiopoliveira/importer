package com.sergio.importer.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@JsonTest
class CsvParserTest {

    @Autowired
    ObjectMapper mapper;

    @Test
    void testParseCorrectCsv() throws IOException {
        InputStream correctFile = getClass().getResourceAsStream("/static/correctFile.csv");
        Parser csvParser = new CsvParser();
        Iterator<Object> iterator = csvParser.parse(correctFile);

        String json = mapper.writeValueAsString(iterator.next());
        Map<String, String> map = mapper.readValue(json, Map.class);

        assertEquals("fccc13f1-f337-480b-9305-a5bb56bcaa11", map.get("UUID"));
        assertEquals("Samsung Galaxy Mobile", map.get("Name"));
        assertEquals("smart phone", map.get("Description"));
        assertEquals("Samsung Galaxy", map.get("provider"));
        assertEquals("true", map.get("available"));
        assertEquals("PC", map.get("MeasurementUnits"));
    }

    @Test
    void testParseBrokenCsv() throws IOException {
        InputStream brokenFile = getClass().getResourceAsStream("/static/brokenFile.csv");

        Parser csvParser = new CsvParser();
        Iterator<Object> iterator = csvParser.parse(brokenFile);

        assertThrows(RuntimeJsonMappingException.class, iterator::next);
    }

}