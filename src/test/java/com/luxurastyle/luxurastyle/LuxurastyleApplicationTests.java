package com.luxurastyle.luxurastyle;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LuxurastyleApplicationTests {

  static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  static final PrintStream original = System.out;

  @Test
  void testHelloWorld() {
    System.setOut(new PrintStream(outputStream));
    LuxurastyleApplication.HelloWorld();
    System.setOut(original);
    try {
      outputStream.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals("Hello World!\n", outputStream.toString());
  }
}
