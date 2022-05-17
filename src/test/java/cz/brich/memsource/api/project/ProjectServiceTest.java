package cz.brich.memsource.api.project;

import cz.brich.memsource.api.project.dto.ContentResponse;
import cz.brich.memsource.api.project.dto.ListProjectsResponse;
import cz.brich.memsource.client.memsource.MemsourceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ProjectServiceTest {

    @Mock
    private MemsourceClient memsourceClient;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.openMocks(this);
    }

    private ListProjectsResponse prepareListProjectsResponse(String name) {
        ListProjectsResponse mockedListProjectsResponse = new ListProjectsResponse();
        mockedListProjectsResponse.setPageNumber(0);
        mockedListProjectsResponse.setNumberOfElements(1);

        ContentResponse contentResponse = new ContentResponse();

        contentResponse.setName(name);
        contentResponse.setStatus("NEW");
        contentResponse.setSourceLang("es");
        contentResponse.setTargetLangs(List.of("en"));
        mockedListProjectsResponse.setContent(List.of(contentResponse));

        return mockedListProjectsResponse;
    }

    @Test
    void listProjects() {
        String name = "Translation test";
        ListProjectsResponse mockedListProjectsResponse = prepareListProjectsResponse(name);
        when(memsourceClient.listProjects()).thenReturn(mockedListProjectsResponse);

        ListProjectsResponse listProjectsResponse = projectService.listProjects();
        assertThat(listProjectsResponse.getContent().size()).isEqualTo(1);
        assertThat(listProjectsResponse.getContent().get(0).getName()).isEqualTo(name);
    }
}
