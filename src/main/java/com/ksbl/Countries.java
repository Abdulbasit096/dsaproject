package com.ksbl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Countries {



    List<String> countries;

    Countries(){}

    public Object searchCountryByName(String name){
        List<String> matched =  countries.stream().filter((c)->c.toLowerCase().contains(name.toLowerCase())).collect(Collectors.toSet()).stream().toList();
        if (matched.size()==1)return matched.get(0);
        return matched;
    }

    public List<String> getCountries() {
        return new HashSet<>(countries).stream().toList();
    }

}
