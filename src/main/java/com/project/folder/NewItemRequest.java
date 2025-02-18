package com.project.folder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewItemRequest {
    private String parentName;
    private String newItemName;
    private boolean isFile;

    // Getters and setters
}