package com.ninja_squad.training.lambda;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Le TP Lambda
 * @author JB
 */
public class TP {
    
    public static void main(String[] args) {
        //step1();
        //step2();
        //step3().forEach(System.out::println);
        //step4().forEach(System.out::println);
        //step5().forEach(System.out::println);
        //step6().stream().map(Tweet::getHashTags).forEach(System.out::println);
        //step7().forEach((t) -> System.out.println(t));
        //step8().forEach((t) -> System.out.println(t));
        //step9().forEach((s, t) -> System.out.println(s + " ===> "+ t));

        //CRASHES NETBEANS�! step10().forEach((s, t) -> System.out.println(s + " ===> "+ t));
        //System.out.println(step10().size());
        
        //System.out.println(step11());
        //System.out.println(step12());
        //System.out.println(step13());
        //System.out.println(step14());
        for (int ind = 0; ind < 9; ind++) {
            long start = System.currentTimeMillis();
            int n = 100;
            switch (ind%3) {
                case 0:
                    for (int i = 0; i < n; i++) {
                        step15();
                    }
                    break;
                case 1:
                    for (int i = 0; i < n; i++) {
                        step16();
                    }
                    break;
                case 2:
                    for (int i = 0; i < n; i++) {
                        step17();
                    }
                    break;
            }
            System.out.println((ind%3)+" => "+(System.currentTimeMillis()-start));
        }
    }

    /**
     * Ecrivez les dates des tweets sur la sortie standard
     */
    public static void step1() {
        Tweet.TWEETS.forEach(new Consumer<Tweet>() {

            @Override
            public void accept(Tweet t) {
                System.out.println(t.getDate());
            }
        });
    }

    /**
     * Faites la même chose, sans appeler getDate() ni System.out.println()
     */
    public static void step2() {
        Tweet.TWEETS.stream().map(Tweet::getDate).forEach(System.out::println);
    }

    /**
     * Extrayez une List<String> qui contient les senders des tweets
     */
    public static List<String> step3() {
        return Tweet.TWEETS.stream().map(Tweet::getSender).collect(Collectors.toList());
    }

    /**
     * Extrayez une List<String> qui contient les senders des tweets, sans duplicata
     */
    public static List<String> step4() {
        return Tweet.TWEETS.stream().map(Tweet::getSender).distinct().collect(Collectors.toList());
    }

    /**
     * Extrayez une List<String> qui contient les senders des tweets, sans duplicata, triés par ordre alphabétique
     */
    public static List<String> step5() {
        return Tweet.TWEETS.stream().map(Tweet::getSender).distinct().sorted().collect(Collectors.toList());
    }

    /**
     * Extrayez une List<Tweet> qui contient les tweets contenant le hashtag #lambda
     */
    public static List<Tweet> step6() {
        // Q: better???
        return Tweet.TWEETS.stream().filter(t->t.containsHashTag("#lambda")).collect(Collectors.toList());
    }

    /**
     * Extrayez une List<Tweet> qui contient les tweets contenant le hashtag #lambda, triés par sender puis par date
     */
    public static List<Tweet> step7() {
        return step6().stream().sorted(Comparator.comparing(Tweet::getSender).thenComparing(Tweet::getDate)).collect(Collectors.toList());
    }

    /**
     * Extrayez un Set<String> qui contient l'ensemble des hash tags des tweets
     */
    public static Set<String> step8() {
        return Tweet.TWEETS.stream().flatMap((t) -> t.getHashTags().stream()).collect(Collectors.toSet());
    }

    /**
     * Créez une Map<String, List<Tweet>> qui contient, pour chaque sender, les tweets envoyés par ce sender
     */
    public static Map<String, List<Tweet>> step9() {
        return Tweet.TWEETS.stream().collect(Collectors.groupingBy(Tweet::getSender));
    }

    /**
     * Extrayez deux listes: les tweets qui contiennent le hash tag #lambda, et ceux qui ne les contiennent pas.
     */
    public static Map<Boolean, List<Tweet>> step10() {
        return Tweet.TWEETS.stream().collect(Collectors.groupingBy((t) -> t.containsHashTag("#lambda")));
    }

    /**
     * Calculez le total du nombre de caractères des textes des tweets.
     * Hints:
     *     Utilisez un {@link java.util.stream.IntStream}
     */
    public static int step11() {
        return Tweet.TWEETS.stream().map(Tweet::getText).mapToInt(String::length).sum();
    }

    /**
     * Calculez la moyenne du nombre de caractères des textes des tweets.
     * Hints:
     *     Utilisez un {@link java.util.stream.IntStream}
     */
    public static int step12() {
        return (int) Tweet.TWEETS.stream().map(Tweet::getText).mapToInt(String::length).average().getAsDouble();
    }

    /**
     * Même chose, mais en calculant manuellement la somme.
     * Hints:
     *     Utilisez stream.collect(..., ..., ...) ou stream.map(...).reduce(...)
     */
    public static int step13() {
        int n = Tweet.TWEETS.size();
        return Tweet.TWEETS.stream().map(Tweet::getText).mapToInt(String::length).reduce(0, (a,b) -> a+b)/n;
    }

    /**
     * Faites la même chose, mais de manière parrallèle
     */
    public static int step14() {
        int n = Tweet.TWEETS.size();
        return Tweet.TWEETS.parallelStream().map(Tweet::getText).mapToInt(String::length).reduce(0, (a,b) -> a+b)/n;
    }

    public static int step15() {
        int n = Tweet.HUGE_TWEETS.size();
        return Tweet.HUGE_TWEETS.stream().map(Tweet::getText).mapToInt(String::length).reduce(0, (a,b) -> a+b)/n;
    }
    public static int step16() {
        int n = Tweet.HUGE_TWEETS.size();
        return Tweet.HUGE_TWEETS.parallelStream().map(Tweet::getText).mapToInt(String::length).reduce(0, (a,b) -> a+b)/n;
    }
    public static int step17() {
        int n = Tweet.HUGE_TWEETS.size();
        return Tweet.HUGE_TWEETS.parallelStream().map(Tweet::getText).mapToInt(String::length).sum()/n;
    }
}
