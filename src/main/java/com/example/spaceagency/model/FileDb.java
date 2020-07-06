package com.example.spaceagency.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "filedb")
public class FileDb {

    @Id
    @GeneratedValue(generator = "FILEDB_ID_GENERATOR")
    @GenericGenerator(name = "FILEDB_ID_GENERATOR", strategy = "enhanced-sequence",
            parameters = @org.hibernate.annotations.Parameter(name = "sequence_name", value = "filedb_sequence"))
    private Long id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    public FileDb(Long id) { }

    public FileDb(){};

    public FileDb(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
