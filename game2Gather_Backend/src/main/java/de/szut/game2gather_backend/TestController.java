package de.szut.game2gather_backend;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    public void saveItem() {
        TestEntity entity = new TestEntity();
        entity.setText("test");
        testService.save(entity);
    }

    @GetMapping()
    public List<TestEntity> getTest() {
        saveItem();
        return testService.readAll();
    }

}
