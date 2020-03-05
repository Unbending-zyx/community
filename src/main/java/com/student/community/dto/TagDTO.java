package com.student.community.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagDTO {
    private String categoryName;//一级标签
    private List<String> tags;//二级标签
}
