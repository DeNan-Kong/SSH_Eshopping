package com.shop.utils;

import com.shop.model.FileImage;

public interface FileUpload {
	//ʵ���ļ��ϴ��Ĺ��ܣ������ϴ����µ��ļ�����  
    public abstract String uploadFile(FileImage fileImage);  
    //��ȡ������Чͼ��
    public String[] getBankImage();
}
