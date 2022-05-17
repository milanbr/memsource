package cz.brich.memsource.api.project.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListProjectsResponse {

    private Integer totalElements;

    private Integer totalPages;

    private Integer pageSize;

    private Integer pageNumber;

    private Integer numberOfElements;

    private List<ContentResponse> content = new ArrayList<>();
}
