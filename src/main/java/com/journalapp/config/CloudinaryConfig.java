package com.journalapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {
	@Value("${config.cloudinary.cloud.name}")
	private String cloudname;
	@Value("${config.cloudinary.cloud.key}")
	private String apikey;
	@Value("${config.cloudinary.cloud.secret}")
	private String apisecret;
	@Bean
	public Cloudinary cloudinary()
	{
		return new Cloudinary(
			
	            ObjectUtils.asMap(
			"cloud_name" , cloudname,
			"api_key" , apikey,
			"api_secret" , apisecret
						)
				);
				
	}
}
