package com.example.spaceagency.service;

import com.example.spaceagency.exception.FileDbNotFoundException;
import com.example.spaceagency.model.FileDb;

public interface FileDbDownloadService {

    FileDb getFileDb(Long id) throws FileDbNotFoundException;

    FileDb getFileDbByUrl(String url) throws FileDbNotFoundException;
}
