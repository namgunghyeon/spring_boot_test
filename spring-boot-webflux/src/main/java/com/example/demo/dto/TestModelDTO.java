package com.example.demo.dto;

import com.example.demo.model.TestModel;

public class TestModelDTO {
    private String field1;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public static TestModelDTO convert(TestModel testModel) {
        TestModelDTO testModelDTO = new TestModelDTO();
        testModelDTO.setField1(testModel.getField1());

        return testModelDTO;
    }
}
