package com.example.spaceagency.service.fileDbDownloadServiceImplementation;

import com.example.spaceagency.exception.FileDbNotFoundException;
import com.example.spaceagency.model.FileDb;
import com.example.spaceagency.repository.FileDbRepository;
import com.example.spaceagency.service.FileDbDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileDbDownloadServiceImplementation implements FileDbDownloadService {

    private FileDbRepository fileDbRepository;

    @Autowired
    private FileDbDownloadServiceImplementation(FileDbRepository fileDbRepository) {
        this.fileDbRepository = fileDbRepository;
    }

    public FileDbDownloadServiceImplementation() {
    }

    @Override
    public FileDb getFileDb(Long id) throws FileDbNotFoundException {
        return fileDbRepository.findById(id)
                .orElseThrow(() -> new FileDbNotFoundException(id));
    }

    @Override
    @Transactional
    public FileDb getFileDbByUrl(String url) throws FileDbNotFoundException {
        return fileDbRepository.findByUrl(url)
                .orElseThrow(() -> new FileDbNotFoundException(url));
    }
}
