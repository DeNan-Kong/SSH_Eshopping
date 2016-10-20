package com.shop.action;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;//程序全局变量，对每个用户每个页面都有效
import org.apache.struts2.interceptor.RequestAware;//request
import org.apache.struts2.interceptor.SessionAware;//session用户全局变量,整个会话期间都有效
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
	
	/**公共action父类
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//封装了图片信息的类  
    protected FileImage fileImage;     
    //上传文件工具类  
    @Resource  
    protected FileUpload fileUpload;
    public FileImage getFileImage() {  
        return fileImage;  
    }  
    public void setFileImage(FileImage fileImage) {  
        this.fileImage = fileImage;  
    }  
    
	//用来装有将要被打包成json格式返回给前台的account数据,要实现get方法  
    protected List<T> jsonList = null;    
    
	public List<T> getJsonList() {
		return jsonList;
	}

	//获取要删除的ids，要有get和set方法   
	protected String ids;
	//流是用来想前台返回数据的，这个数据是让struts获取的，然后通过流的形式传到前台，所以实现get方法即可  
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

	//page和rows和分页有关，pageMap存放查询的数据，然后打包成json格式用的  
    //page和rows实现get和set方法，pageMap只需要实现get方法即可，因为pageMap不是接收前台参数的，是让struts获取的  
    protected Integer page;  
    protected Integer rows;  
    protected Map<String, Object> pageMap = null;//让不同的Action自己去实现     
    
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

	//service对象 
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
	
	//域对象  
	protected Map<String, Object> request;  
    protected Map<String, Object> session;  
    protected Map<String, Object> application;
    //因为有很多不同的model都需要使用ModelDriven，所以这里使用泛型  

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
	
	//ModelDriven,使用ModelDriven接口必须要实现getModel()方法，此方法会把返回的项压到栈顶  
	protected T model;  
	
	
	@SuppressWarnings("unchecked")
	public T getModel() { //这里通过解析传进来的T来new一个对应的instance  
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
