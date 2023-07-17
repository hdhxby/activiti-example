package io.github.hdhxby.example.activiti;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class STest {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private FormService formService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void test() throws IOException {

        identityService.saveGroup(identityService.newGroup("group"));

        identityService.saveUser(identityService.newUser("user"));

        identityService.createMembership("user","group");

        List<Group> groups = identityService.createGroupQuery().list();
        List<User> users = identityService.createUserQuery().list();

        Resource resource = resourceLoader.getResource("classpath:processes/one-task-process.bpmn20.xml");
        repositoryService.createDeployment().addInputStream(resource.getFilename(),resource.getInputStream())
                .deploy();
        List<Deployment> deployments = repositoryService.createDeploymentQuery().list();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("oneTaskProcess");

        List<Task> tasks = taskService.createTaskQuery().list();

        taskService.complete(tasks.get(0).getId());

        tasks = taskService.createTaskQuery().list();

        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery().list();

    }
}
