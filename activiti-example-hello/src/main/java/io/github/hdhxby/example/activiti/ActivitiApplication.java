package io.github.hdhxby.example.activiti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * 权限中心
 *
 * @author lixiaobin
 * @version 2.0, 03/06/21
 * @since 2.0
 */
@ServletComponentScan
@ComponentScan(basePackages = { "org.activiti.rest.service.api",
        "org.activiti.rest.editor.main",
        "org.activiti.rest.editor.model",
        "org.activiti.rest.diagram.services" })
@SpringBootApplication
public class ActivitiApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ActivitiApplication.class);
        springApplication.setHeadless(false);
        springApplication.run(args);
    }

}
