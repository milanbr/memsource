package cz.brich.memsource.api.project;

import cz.brich.memsource.api.project.dto.ListProjectsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ListProjectsResponse listProjects() {
        return projectService.listProjects();
    }
}
