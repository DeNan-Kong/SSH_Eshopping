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
	//set�������Բ��ú�������һ��������ǰ̨������ʱ�Ĳ����ú�set��������ͬ����ǰ̨���Ĳ���ΪfileImage.upload
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
