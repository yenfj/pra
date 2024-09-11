package com.example.practice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice: 包含了@ControllerAdvice和@ResponseBody
// @ControllerAdvice: 使用於class的註釋，用來定義「全域」的例外處理策略
// @ResponseBody: 這是一個告訴Spring每次response都需要將返回的物件序列化為JSON或XML格式，然後放入HTTP回應的內容中
@RestControllerAdvice // 用來表示這是一個全域的REST例外處理器
public class GlobalExceptionHandler {
	// @Valid配合Spring會抛出MethodArgumentNotValidException異常；所以要針對此Exception處理
	// @ExceptionHandler: 使用於method中的註釋，提供了在controller處理例外的功能
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	// ResponseEntity: 可以做為controller的返回值
	public ResponseEntity<Map<String, Object>> paramExceptionHandler(MethodArgumentNotValidException e) {
		// 因為在controller中的res返回的是code: 數值 和 message: 字串；所以map的value用Object接
		Map<String, Object> errorMap = new HashMap<>();
		// 將req中的參數檢查統一歸類為bad_request，代碼為400
		errorMap.put("code", HttpStatus.BAD_REQUEST.value());
		// 即使有多個錯誤參數，但還是一個個處理，所以會取第一個錯誤訊息
		errorMap.put("message", e.getAllErrors().get(0).getDefaultMessage());
		// 返回的Http Status Code為badRequest(400)
		return ResponseEntity.badRequest().body(errorMap);
	}
}
