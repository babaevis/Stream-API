package com.kroyce;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LapResultsPrinterTests {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public static class PrintTest{
        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        private final PrintStream originalOut = System.out;


        @BeforeAll
        public void setUpStreams() {
            System.setOut(new PrintStream(outContent));
        }

        @AfterAll
        public void restoreStreams() {
            System.setOut(originalOut);

        }

        @Test
        public void out() {
            String expected =                   " 1. Daniel Ricciardo | RED BULL RACING TAG HEUER | 1:12.013\n" +
                                                " 2. Sebastian Vettel | FERRARI                   | 1:12.415\n" +
                                                " 3. Lewis Hamilton   | MERCEDES                  | 1:12.460\n";

            RaceInfoReader raceInfoReader = new RaceInfoReader( new File("src/test/resources/start.log"),
                                                                new File("src/test/resources/end.log"),
                                                                new File("src/test/resources/abbreviations.txt"));
            List<Racer> racers = raceInfoReader.getRacers();
            LapResultsPrinter lapResultsPrinter = new LapResultsPrinter(racers);
            lapResultsPrinter.printLapResults();
            assertEquals(expected, outContent.toString());
        }

    }

    @Test
    void nullInput(){
        LapResultsPrinter lapResultsPrinter = new LapResultsPrinter(null);

        assertThrows(IllegalArgumentException.class, lapResultsPrinter::printLapResults);
    }

    @Test
    void emptyInput(){
        List<Racer> list = new ArrayList<>();
        LapResultsPrinter lapResultsPrinter = new LapResultsPrinter(list);

        assertThrows(IllegalArgumentException.class, lapResultsPrinter::printLapResults);
    }
}
