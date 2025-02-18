package com.project.folder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectoryController {

    private final DirectoryService directoryService;

    public DirectoryController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @PostMapping("/addNewItem")
    public ResponseEntity<Void> addNewItem(@RequestBody NewItemRequest request) {
        directoryService.addNewItem(request.getParentName(), request.getNewItemName(), request.isFile());
        return ResponseEntity.ok().build();
    }
}

