package com.my.application.black.jack.web.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Developer: Roman Shostak
 * Date: 08-Oct-15.
 */
@RestController
public class TestRestController {

    public TestRestController() {

        System.out.print("\n\n\nTest rest controller has been created.\n\n\n");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String helloWorld() {
        return "helloWorld ";
    }

    @RequestMapping(value = "/testDto", method = RequestMethod.GET)
    public SomeDto testDto(@RequestParam(value = "name") String name) {
        return new SomeDto(1, name);
    }


    public static class SomeDto {
        private long id;
        private String name;

        public SomeDto() {
        }

        public SomeDto(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
