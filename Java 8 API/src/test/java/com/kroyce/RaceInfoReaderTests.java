package com.kroyce;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RaceInfoReaderTests {
    @Test
    void correctInput(){
        List<Racer> expected = new ArrayList<>();
        expected.add(   new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", "DRR",
                        new LapTime(LocalDateTime.parse("2018-05-24T12:14:12.054", DateTimeFormatter.ISO_DATE_TIME),
                                    LocalDateTime.parse("2018-05-24T12:15:24.067", DateTimeFormatter.ISO_DATE_TIME))));
        expected.add(   new Racer("Sebastian Vettel", "FERRARI", "SVF",
                        new LapTime(LocalDateTime.parse("2018-05-24T12:02:58.917", DateTimeFormatter.ISO_DATE_TIME),
                                    LocalDateTime.parse("2018-05-24T12:04:11.332", DateTimeFormatter.ISO_DATE_TIME))));
        expected.add(   new Racer("Lewis Hamilton", "MERCEDES", "LHM",
                        new LapTime(LocalDateTime.parse("2018-05-24T12:18:20.125", DateTimeFormatter.ISO_DATE_TIME),
                                    LocalDateTime.parse("2018-05-24T12:19:32.585", DateTimeFormatter.ISO_DATE_TIME))));

        RaceInfoReader raceInfoReader = new RaceInfoReader( new File("src/test/resources/start.log"),
                                                            new File("src/test/resources/end.log"),
                                                            new File("src/test/resources/abbreviations.txt"));
        List<Racer> actual = raceInfoReader.getRacers();

        assertEquals(actual,expected);
    }

    @Test
    void missingFile(){
        RaceInfoReader raceInfoReader = new RaceInfoReader( new File("src/test/resources/wergreg.log"),
                                                            new File("src/test/resources/end.log"),
                                                            new File("src/test/resources/abbreviations.txt"));

        assertThrows(IllegalArgumentException.class, raceInfoReader::getRacers);
    }

    @Test
    void emptyFile(){
        RaceInfoReader raceInfoReader = new RaceInfoReader( new File("src/test/resources/empty.txt"),
                                                            new File("src/test/resources/end.log"),
                                                            new File("src/test/resources/abbreviations.txt"));

        assertThrows(IllegalArgumentException.class, raceInfoReader::getRacers);
    }
}
