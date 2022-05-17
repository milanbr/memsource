package cz.brich.memsource.client.memsource;

import cz.brich.memsource.api.project.dto.ListProjectsResponse;
import cz.brich.memsource.config.FeignAuthInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "memsource", url = "${app.services.memsource.url}",
    configuration = FeignAuthInterceptor.class)
public interface MemsourceClient {

    @GetMapping("/api2/v1/projects")
    ListProjectsResponse listProjects();
}
