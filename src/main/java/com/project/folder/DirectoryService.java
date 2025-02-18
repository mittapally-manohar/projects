package com.project.folder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DirectoryService {

    private static final String FILE_PATH = "src/main/resources/directory.json";
    private List<Folder> directoryStructure;

    public DirectoryService() {
        this.directoryStructure = loadDirectoryStructure();
    }

    public List<Folder> loadDirectoryStructure() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(FILE_PATH), new TypeReference<List<Folder>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveDirectoryStructure() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), directoryStructure);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewItem(String parentName, String newItemName, boolean isFile) {
        for (Folder folder : directoryStructure) {
            if (folder.getName().equals(parentName) && !folder.isFile()) {
                folder.getChildren().add(new Folder(newItemName, isFile, new ArrayList<>()));
                saveDirectoryStructure();
                return;
            }
            addNewItemRecursively(folder.getChildren(), parentName, newItemName, isFile);
        }
    }

    private void addNewItemRecursively(List<Folder> children, String parentName, String newItemName, boolean isFile) {
        for (Folder folder : children) {
            if (folder.getName().equals(parentName) && !folder.isFile()) {
                folder.getChildren().add(new Folder(newItemName, isFile, new ArrayList<>()));
                saveDirectoryStructure();
                return;
            }
            addNewItemRecursively(folder.getChildren(), parentName, newItemName, isFile);
        }
    }
}