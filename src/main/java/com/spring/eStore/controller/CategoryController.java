package com.spring.eStore.controller;

import com.spring.eStore.dto.ApiResponseMessage;
import com.spring.eStore.dto.CategoryDto;
import com.spring.eStore.dto.PageableResponse;
import com.spring.eStore.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    //create
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        CategoryDto categoryDto1 = categoryService.create(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }
    //update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable @Valid String categoryId) {
        CategoryDto categoryDto1 = categoryService.update(categoryDto,categoryId);
        return new ResponseEntity<>(categoryDto1, HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponseMessage> deleteCategory(@PathVariable @Valid String categoryId) {
        categoryService.delete(categoryId);
        ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                                            .message("category deleted")
                                            .success(true)
                                            .status(HttpStatus.OK).build();
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
    //getall
    @GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>> getAllCategory(
            @RequestParam(value="pageNumber", defaultValue = "0", required=false) int pageNumber,
            @RequestParam(value="pageSize", defaultValue = "10", required=false) int pageSize,
            @RequestParam(value="sortBy", defaultValue = "title", required=false)String sortBy,
            @RequestParam(value="sortDir", defaultValue = "asc", required=false) String sortDir
    ) {
        PageableResponse<CategoryDto> response = categoryService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //getSingle
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getsingle(@PathVariable String categoryId) {
        CategoryDto categoryDto = categoryService.get(categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }
}
