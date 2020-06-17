package com.kroyce;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RaceInfoReader {
    private final File start;
    private final File end;
    private final File abbreviations;
    private List<Racer> racers;
    private Map<String, LocalDateTime> startTimes;
    private Map<String, LocalDateTime> endTimes;
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

    public RaceInfoReader(File start, File end, File abbreviations) {
        this.start = start;
        this.end = end;
        this.abbreviations = abbreviations;
        racers = new ArrayList<>();
    }

    public List<Racer> getRacers(){
        parseFiles();
        return racers;
    }

    private void parseFiles(){
        checkFiles();
        parseStart();
        parseEnd();
        parseAbbreviations();
    }

    private void checkFiles(){
        checkIfExists();
        checkIfEmpty();
    }

    private void checkIfExists(){
        if(!start.exists()){
            throw new IllegalArgumentException("Start file not found!");
        }
        if(!end.exists()){
            throw new IllegalArgumentException("End file not found!");
        }
        if(!abbreviations.exists()){
            throw new IllegalArgumentException("Abbreviations file not found!");
        }
    }

    private void checkIfEmpty(){
        if(start.length() == 0){
            throw new IllegalArgumentException("Start file is empty!");
        }
        if(end.length() == 0){
            throw new IllegalArgumentException("End file is empty!");
        }
        if(abbreviations.length() == 0){
            throw new IllegalArgumentException("Abbreviations file is empty!");
        }
    }

    private void parseStart(){
        try (Stream<String> stream = Files.lines(Paths.get(start.getAbsolutePath()))){
            startTimes = stream.collect(Collectors.toMap(line -> line.substring(0, 3),
                    line -> LocalDateTime.parse(line.substring(3), timeFormat)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseEnd(){
        try(Stream<String> stream = Files.lines(Paths.get(end.getAbsolutePath()))){
            endTimes = stream.collect(Collectors.toMap(line -> line.substring(0,3),
                    line -> LocalDateTime.parse(line.substring(3), timeFormat)));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void parseAbbreviations(){
        try(Stream<String> stream = Files.lines(Paths.get(abbreviations.getAbsolutePath()))){
            racers = stream.map(this::addRacer).collect(Collectors.toList());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private Racer addRacer(String line){
        String[] racerInfo = line.split("_");
        String abbreviation = racerInfo[0];
        String name = racerInfo[1];
        String team = racerInfo[2];
        LapTime lapTime =  getLapTime(abbreviation);

        return new Racer(name,team,abbreviation,lapTime);
    }

    private LapTime getLapTime(String abbreviation){
        LocalDateTime startTime = startTimes.get(abbreviation);
        LocalDateTime endTime = endTimes.get(abbreviation);

        return new LapTime(startTime, endTime);
    }
}
