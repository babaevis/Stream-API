package com.kroyce;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LapResultsPrinter {
    private final List<Racer> racers;
    private int longestName;
    private int longestTeam;

    public LapResultsPrinter(List<Racer> racers) {
        this.racers = racers;
    }

    public void printLapResults(){
        List<String> result = format();
        result.forEach(System.out::println);
    }

    private List<String> format (){
        if(racers == null){
            throw new IllegalArgumentException("List with racers data is null!");
        }
        if(racers.isEmpty()){
            throw new IllegalArgumentException("List with racers data is empty!");
        }

        this.longestName = findLongestName();
        this.longestTeam = findLongestTeam();
        sortRacersByTime();
        List<String> result = new ArrayList<>();
        StringBuilder racerInfo = new StringBuilder();

        for(int i = 0; i < racers.size(); i++){
            racerInfo.append(String.format("%2d. ", i+1));
            racerInfo.append(formatName(racers.get(i))).append(" | ");
            racerInfo.append(formatTeam(racers.get(i))).append(" | ");
            racerInfo.append(formatLapTime(racers.get(i)));
            result.add(racerInfo.toString());

            if(i == 14){
                result.add(addDelimiter(racerInfo.length()));
            }

            racerInfo.setLength(0);
        }

        return result;
    }

    private void sortRacersByTime(){
        racers.sort(Comparator.comparing(racer -> racer.getLapTime().getDuration()));
    }

    private String formatName(Racer racer){
        String name = racer.getName();
        String nameFormat = "%" + (longestName * -1) + "s";

        return String.format(nameFormat, name);
    }

    private String formatTeam(Racer racer){
        String team = racer.getTeam();
        String teamFormat = "%" + (longestTeam * -1) + "s";

        return String.format(teamFormat, team);
    }

    private String formatLapTime(Racer racer){
        Duration lapTime = racer.getLapTime().getDuration();
        StringBuilder sb = new StringBuilder(lapTime.toString());

        String result = sb.substring(2).replace('M', ':').substring(0, sb.length() - 3);

        while(result.length() < 8) {
            result = result + "0";
        }

        return result;
    }

    private int findLongestTeam(){
        Racer racer = racers.stream().max(Comparator.comparing(r -> r.getTeam().length())).get();

        return racer.getTeam().length();
    }

    private int findLongestName(){
        Racer racer = racers.stream().max(Comparator.comparing( r -> r.getName().length())).get();

        return racer.getName().length();
    }

    private String addDelimiter(int length){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < length; i++){
            result.append("-");
        }

        return result.toString();

    }
}
