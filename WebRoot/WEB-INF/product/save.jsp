<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf" %>  
    <style type="text/css">  
        form div {  
            margin:5px;  
        }  
    </style>
    <script type="text/javascript">
    	$(function(){
    		//自定义验证方法向validatebox.defaults.rules中注册新函数  
    		$.extend($.fn.validatebox.defaults.rules,{
    			//函数的名称：{函数的实现体(又是一个json对象，里面包括函数的实现，和错误消息的设置)}
    			format:{
    				validator:function(value,param){
    					//获取当前文件的后缀名  
    					var ext = value.substring(value.lastIndexOf(".") + 1);
    					
    					//获取支持文件后缀名
    					var arr = param[0].split(",");
    					for(var i = 0;i < arr.length;i++){
    						if(ext == arr[i]) return true;
    					}
    					return false;
    				},
    				message:"文件后缀必须为:{0}"
    			}   
    		});
    		
    		//对商品类别的下拉列表框进行远程加载  
    		$("#cc").combobox({
    			
                url:"category_query.action",    
                valueField:"id",      
                textField:"type", 
                panelHeight:"auto", //自适应高度
                panelWidth:120,//下拉列表是两个组件组成的  
                width:120, //要同时设置两个宽度才行  
                editable:false, //下拉框不允许编辑
                //combobox继承combo继承validatebox，所以可以直接在这里设置验证  
                required:true,  
                missingMessage:'请选择所属类别'      
    		});
    		
    		$("input[name=name]").validatebox({  
                required:true,  
                missingMessage:'请输入商品名称'  
            });  
  
            $("input[name=price]").numberbox({  
                required:true,  
                missingMessage:'请输入商品价格',  
                min:0,  
                precision:2, //保留两位小数  
                prefix:'$'  
            });  
            $("input[name='fileImage.upload']").validatebox({  
                required:true,  
                missingMessage:'请上传商品图片',  
                //设置自定义方法  
                validType:"format['gif,jpg,jpeg,png']"//中括号里面是参数  
            });  
      
            $("textarea[name=remark]").validatebox({  
                required:true,  
                missingMessage:'请输入商品的简单描述'  
            });  
              
            $("textarea[name=xremark]").validatebox({  
                required:true,  
                missingMessage:'请输入商品的简单描述'  
            });
             
    		$("#ff").form("disableValidation");
    		
    		//注册button的事件
    		$("#submit").click(function(){
    			$("#ff").form("enableValidation");//开启验证  
    			if($("#ff").form("validate")){
    				$("#ff").form("submit",{
    					url:"product_save.action",
    					success:function(){
    						parent.$("#win").window("close");
    						//获取aindex-->iframe-->datagrid,刷新页面
                            parent.$("iframe[title='商品管理']").get(0).contentWindow.$("#dg").datagrid("reload");                         	
    					}
    				});
    			};//end if
    		}); 
    		//注册button的事件  
            $("#reset").click(function(){  
                $("#ff").form("disableValidation");//重置不需要表单验证  
                //重置当前表单数据  
                $("#ff").form("reset");  
            });   
    	});
    </script>  
  </head>
  
  <body>
    <form title="添加商品" id="ff" method="post" enctype="multipart/form-data"><!-- 图片，匿名上载文件的正确编码 -->
    	<div>
    		<label>商品名称:</label><input type="text" name="name"/>
    	</div>
    	<div>  
            <label>商品价格:</label> <input type="text" name="price" />  
        </div>  
        <div>  
            <label>图片上传:</label> <input type="file" name="fileImage.upload" />  
        </div>
        
        <div>  
            <label>所属类别：</label>   
            <input id="cc" name="category.id"/>  
        </div>  
          
        <div>  
            <label>加入推荐:</label>
                                    推荐:<input type="radio" name="commend" checked="checked" value="true" />
                                    不推荐:<input type="radio" name="commend" value="false" />   
        </div>  
        <div>  
            <label>是否有效:</label>  
		            上架:<input type="radio" name="open" checked="checked"value="true" />  
		            下架:<input type="radio" name="open" value="false" />                      
        </div>  
           
        <div>  
            <label>简单描述:</label>  
            <textarea name="remark" cols="40" rows="4"></textarea>  
        </div>  
        <div>  
            <label>详细描述:</label>  
            <textarea name="xremark" cols="40" rows="8"></textarea>  
        </div>  
        <div>  
            <a id="submit" href="#" class="easyui-linkbutton">添 加</a>   
            <a id="reset" href="#" class="easyui-linkbutton">重 置</a>  
        </div>    
    </form>
  </body>
</html>
