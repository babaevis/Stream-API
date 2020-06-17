package com.kroyce;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String startPath =          "src/main/resources/start.log";
        String endPath =            "src/main/resources/end.log";
        String abbreviationsPath =  "src/main/resources/abbreviations.txt";

        File start = new File(startPath);
        File end = new File(endPath);
        File abbreviations = new File(abbreviationsPath);

        RaceInfoReader raceInfoReader = new RaceInfoReader(start, end, abbreviations);
        List<Racer> racers = raceInfoReader.getRacers();
        LapResultsPrinter printer = new LapResultsPrinter(racers);

        printer.printLapResults();
    }
}
