package com.project.folder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Folder {
    private String name;
    private boolean isFile; // false = Folder, true = File
    private List<Folder> children; // Subfolders or files

    public Folder() {
    }

    public Folder(String name, boolean isFile, List<Folder> children) {
        this.name = name;
        this.isFile = isFile;
        this.children = children;
    }

    public List<Folder> getChildren() {
        return children;
    }

    public void setChildren(List<Folder> children) {
        this.children = children;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}