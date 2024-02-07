package com.study.springboot.entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Board_attach")
public class FileData {

    @Id
    private String uuid; // uuid
    private String origin; // 원본파일
    private String filePath; // 저장파일
    @Column(name = "board_bno")
    private Long boardBno; // 게시글번호

    @Builder
    public FileData(String uuid, String origin, String filePath, Long boardBno) {
        super();
        this.uuid = uuid;
        this.origin = origin;
        this.filePath = filePath;
        this.boardBno = boardBno;
    }
    
    public String getUuid() {
        return this.uuid;
    }
    
    public byte[] readFileData(FileData fileData) throws IOException {
        String filePath = fileData.getFilePath();
        String uuid = fileData.getUuid(); 

        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }



}



