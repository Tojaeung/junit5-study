package me.tojaeung.testing;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {
    @Test
    void create() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create");
    }

    @Test
    @Disabled   // 이 테스트는 동작 안하게하는 어노테이션
    void create1() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create1");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before All");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after All");   
    }

    @BeforeEach
     void beforeEach() {
        System.out.println("before Each");
    }
    
    @AfterEach
     void afterEach() {
        System.out.println("after Each");
    }
}
