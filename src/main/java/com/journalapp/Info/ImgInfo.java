package com.journalapp.Info;

public record ImgInfo(String public_id,String secure_url,String format) {

	public String public_id() {
		return public_id;
	}

	public String secure_url() {
		return secure_url;
	}

	public String format() {
		return format;
	}

	
}
