package com.example.catalogservice.controller;

import com.example.catalogservice.entity.Catalog;
import com.example.catalogservice.service.CatalogService;
import com.example.catalogservice.vo.ResponseCatalog;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog-service")
public class CatalogController {
    private final Environment env;
    private final CatalogService catalogService;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's working in Catalog Service on PORT %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
        Iterable<Catalog> allCatalogs = catalogService.getAllCatalogs();

        List<ResponseCatalog> result = new ArrayList<>();
        allCatalogs.forEach(v -> result.add(new ObjectMapper().convertValue(v, ResponseCatalog.class)));

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
