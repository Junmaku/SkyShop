package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SearchService {
    private final StorageService storageService;

    @Autowired
    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String str) {
        List<SearchResult> tempList = new ArrayList<>();
        storageService.getAllSearchable()
                .stream()
                .filter(Objects::nonNull)
                .filter(s -> s.getSearchTerm().contains(str))
                .forEach(s -> tempList.add(SearchResult.fromSearchable(s)));
        return tempList;
    }

}
