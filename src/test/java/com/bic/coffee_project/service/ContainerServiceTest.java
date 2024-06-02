package com.bic.coffee_project.service;

import com.bic.project.model.Container;
import com.bic.project.repository.ContainerRepository;
import com.bic.project.service.ContainerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class ContainerServiceTest {

    @Autowired
    private ContainerService containerService;

    @MockBean
    private ContainerRepository containerRepository;

    @Test
    public void testAddContainer() {
        Container container = new Container();
        container.setNumber("12345");
        container.setPrice(100);

        containerService.add(container);

        verify(containerRepository).save(container);
    }
}