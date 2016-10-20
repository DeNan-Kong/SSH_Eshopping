package com.shop.action;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;//����ȫ�ֱ�������ÿ���û�ÿ��ҳ�涼��Ч
import org.apache.struts2.interceptor.RequestAware;//request
import org.apache.struts2.interceptor.SessionAware;//session�û�ȫ�ֱ���,�����Ự�ڼ䶼��Ч
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.model.FileImage;
import com.shop.service.AccountService;
import com.shop.service.CategoryService;
import com.shop.service.ForderService;
import com.shop.service.ProductService;
import com.shop.service.SorderService;
import com.shop.service.UserService;
import com.shop.utils.FileUpload;
@Controller("baseAction")
@Scope("prototype")
public class BaseAction<T> extends ActionSupport implements RequestAware,SessionAware,ApplicationAware,ModelDriven<T>{
	
	/**����action����
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//��װ��ͼƬ��Ϣ����  
    protected FileImage fileImage;     
    //�ϴ��ļ�������  
    @Resource  
    protected FileUpload fileUpload;
    public FileImage getFileImage() {  
        return fileImage;  
    }  
    public void setFileImage(FileImage fileImage) {  
        this.fileImage = fileImage;  
    }  
    
	//����װ�н�Ҫ�������json��ʽ���ظ�ǰ̨��account����,Ҫʵ��get����  
    protected List<T> jsonList = null;    
    
	public List<T> getJsonList() {
		return jsonList;
	}

	//��ȡҪɾ����ids��Ҫ��get��set����   
	protected String ids;
	//����������ǰ̨�������ݵģ������������struts��ȡ�ģ�Ȼ��ͨ��������ʽ����ǰ̨������ʵ��get��������  
	protected InputStream inputStream;	
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public InputStream getInputStream() {
		return inputStream;
	}

	//page��rows�ͷ�ҳ�йأ�pageMap��Ų�ѯ�����ݣ�Ȼ������json��ʽ�õ�  
    //page��rowsʵ��get��set������pageMapֻ��Ҫʵ��get�������ɣ���ΪpageMap���ǽ���ǰ̨�����ģ�����struts��ȡ��  
    protected Integer page;  
    protected Integer rows;  
    protected Map<String, Object> pageMap = null;//�ò�ͬ��Action�Լ�ȥʵ��     
    
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Map<String, Object> getPageMap() {
		return pageMap;
	}

	//service���� 
	@Resource
    protected CategoryService categoryService;
	@Resource
    protected AccountService accountService;
	@Resource
	protected ProductService productService;
	@Resource  
    protected ForderService forderService;  
    @Resource  
    protected SorderService sorderService;
    @Resource  
    protected UserService userService;
	
	//�����  
	protected Map<String, Object> request;  
    protected Map<String, Object> session;  
    protected Map<String, Object> application;
    //��Ϊ�кܶ಻ͬ��model����Ҫʹ��ModelDriven����������ʹ�÷���  

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
	
	//ModelDriven,ʹ��ModelDriven�ӿڱ���Ҫʵ��getModel()�������˷�����ѷ��ص���ѹ��ջ��  
	protected T model;  
	
	
	@SuppressWarnings("unchecked")
	public T getModel() { //����ͨ��������������T��newһ����Ӧ��instance  
        ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();  
        
		@SuppressWarnings("rawtypes")
		Class clazz = (Class)type.getActualTypeArguments()[0];  
        try {  
            model = (T)clazz.newInstance();  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }     
        return model;  
    }  
	
}
