package com.journalapp.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.journalapp.Info.ImgInfo;
import com.journalapp.config.AppConstants;

@Service
public class ImageUploadService {
@Autowired
private Cloudinary cloudinary;

public ImgInfo upload(MultipartFile file) throws IOException{
Map result = cloudinary.uploader().upload(file.getBytes(),ObjectUtils.emptyMap());
ImgInfo imgInfo = new ImgInfo(
		result.get("public_id").toString(),
		result.get("secure_url").toString(),
		result.get("format").toString()
		);
return imgInfo;
}
public String generateUrl(String publicId) {
	return cloudinary.url().generate(publicId);
}
public String generateTransformUrl(String publicId) {
	return cloudinary.url().transformation(new Transformation<>().width(AppConstants.CLOUDINARY_IMAGE_WIDTH).height(AppConstants.CLOUDINARY_IMAGE_HEIGHT).crop(AppConstants.CLOUDINARY_IMAGE_CROP)).generate(publicId);
}
	
}
