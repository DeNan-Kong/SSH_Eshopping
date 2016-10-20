package com.shop.model;

import java.io.File;

public class FileImage {
	private File file;
	private String filename;
	private String contentType;
	   
	public File getFile() {
		return file;
	}
	public String getFilename() {
		return filename;
	}
	public String getContentType() {
		return contentType;
	}
	//set方法可以不用和属性名一样，但是前台传进来时的参数得和set方法名相同。即前台传的参数为fileImage.upload
	public void setUpload(File file) {
		this.file = file;
	}
	public void setUploadFileName(String filename) {
		this.filename = filename;
	}	
	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}
}
