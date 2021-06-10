package com.ebay.normalization.javatestservice;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("catalogmapping")
public class CatalogCategoryController {

    private ClassLoader classLoader;

    public CatalogCategoryController() {
        this.classLoader = getClass().getClassLoader();
    }

    @GetMapping(value = "getcatalogmapping/json/{catalogId}/{siteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCatalogMapping(@PathVariable Integer catalogId, @PathVariable Integer siteId) throws IOException, InterruptedException {
        System.out.println(String.format("Incoming request for catalog: %d in site %d", catalogId, siteId));
        InputStream resourceAsStream = classLoader.getResourceAsStream(siteId + "_" + catalogId);
        if (resourceAsStream == null) {
            throw new BadRequestException(String.format("No mapping for catalog %d in site %d", catalogId, siteId));
        }
        Thread.sleep(1000L);
        return IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8.name());
    }

}
