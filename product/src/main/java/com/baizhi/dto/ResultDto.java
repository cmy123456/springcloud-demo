package com.baizhi.dto;

import com.baizhi.entity.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto implements Serializable {
    private Integer code;
    private String msg;
    private List<ProductCategoryDto> data=new ArrayList<>();
}
