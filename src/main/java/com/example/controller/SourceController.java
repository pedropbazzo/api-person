package com.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins= "*")
@Api(value = "Source")
@RestController
@RequestMapping({"/source"})
public class SourceController {

    @ApiOperation(value = "Lista o link do projeto no Github")
    @GetMapping
    public ResponseEntity getLinkSource() {
        return ResponseEntity.ok().body("https://github.com/pedropbazzo/cadastro-pessoa-fisica");
    }
}
