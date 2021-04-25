package br.com.devspring.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static List<Long> decodeLongList(String s){
        /*String[] vet = s.split(","); //split quebra a string
        List<Integer> list = new ArrayList<>(); //instancia do Array
        for (int i=0; i <= vet.length; i++){ //percore o vetor.
            list.add(Integer.parseInt(vet[i])); //adiciona os numeros a lista
        }
        return list;*/

        //Mesmo código acima.
        return Arrays.asList(s.split(",")).stream().map(x -> Long.parseLong(x)).collect(Collectors.toList());
    }

    public static String decodeParam(String s){
        try {
            return URLDecoder.decode(s,"UTF-8");
        }catch (UnsupportedEncodingException e){
            return "";
        }
    }

}
