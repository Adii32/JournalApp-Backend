package com.journalapp.controller;
import com.journalapp.Info.ImgInfo;
import  com.journalapp.service.ImageUploadService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RequestMapping("/cloud/upload")
@RestController
public class CloudinaryImageUplodController {
	@Autowired
	private ImageUploadService imageService;
	@PostMapping
	public ResponseEntity<ImgInfo> uplodImage(@RequestParam("image")MultipartFile image) throws IOException
	{
		ImgInfo imgInfo = imageService.upload(image);
		return new ResponseEntity<>(imgInfo,HttpStatus.OK);
	}
}
