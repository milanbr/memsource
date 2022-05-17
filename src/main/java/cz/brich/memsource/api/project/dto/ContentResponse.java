package cz.brich.memsource.api.project.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContentResponse {

    private String name;

    private String sourceLang;

    private List<String> targetLangs = new ArrayList<>();

    private String status;
}
