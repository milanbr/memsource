package cz.brich.memsource.api.project;

import cz.brich.memsource.api.project.dto.ListProjectsResponse;
import cz.brich.memsource.client.memsource.MemsourceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    private final MemsourceClient memsourceClient;

    public ProjectServiceImpl(MemsourceClient memsourceClient) {
        this.memsourceClient = memsourceClient;
    }

    @Override
    public ListProjectsResponse listProjects() {
        return memsourceClient.listProjects();
    }
}
