package com.sergio.importer.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public interface Parser {

   <T> Iterator<T> parse(InputStream input) throws IOException;
}
