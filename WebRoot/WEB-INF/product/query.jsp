<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@ include file="/public/head.jspf" %>
	<style type="text/css">  
        body {  
            margin: 1px;  
        }  
                .searchbox {  
                        margin: -3;  
                }  
    </style>  
    <script type="text/javascript">
    	$(function(){
	    	$("#dg").datagrid({
	    		url:"product_queryJoinCategory.action",
	    		loadMsg:"Login......",
	    		queryParams:{name:""},//参数
	    		fitColumns:true,//水平自动展开，如果设置此属性，则不会有水平滚动条，演示冻结列时，该参数不要设置  
                //显示斑马线  
                striped:true,  
                //当数据多的时候不换行  
                nowrap:true,
                //如果为真，只允许单行显示，全显功能失效  
                singleSelect:false, 
                //设置分页
                pagination:true,
                //pageSize:5,//设置每页显示的记录数，默认是10个
                //pageList:[5,10,15,20],//设置可选的每页记录数，供用户选择，默认是10,20,30,40... 
                idField:"id",//指定id为标识字段，在删除，更新的时候有用，如果配置此字段，在翻页时，换页不会影响选中的项 
              /*rowStyler:function(index,row){
                	console.info("index" + index + "," + row);
                	if(index % 2 == 0){
                		return "background-color:#fff;";
                	}else{
                		return "background-color:#CCCCFF;";
                	}
                },*/
                //同列属性，但是这些列将会冻结在左侧,大小不会改变，当宽度大于250时，会显示滚动条，但是冻结的列不在滚动条内  
                frozenColumns:[[  
                    {field:"checkbox",checkbox:true},  
                    {field:"id",title:"商品编号",width:100}                   
                ]],  
                //配置datagrid的列字段   
                //field：列字段的名称，与json的key捆绑  
                //title：列标题，是显示给人看的
             
                columns:[[                   
                	{field:"name",title:"商品名称",width:100},
                	{field:"price",title:"商品价格",width:100},
                	{field:"remark",title:"简单描述",width:100},
                	{field:"xremark",title:"详细描述",width:100},
                	{field:"date",title:"上架时间",width:100},      
	                {field:"commend",title:"推荐商品",width:100,  
	                	formatter: function(value,row,index){  
	                    //设置当前单元格的样式，返回的字符串直接交给 style属性  
	                    //console.info("val:" + value + ",row:" + row + ",index:" + index)  
		                    if (value){  
		                        return "<input type='checkbox' checked='checked' disabled='true'";  
	                    	}else{
	                    		return "<input type='checkbox' disable='true'"; //不勾选 
	                    	}  
	                	}     
	                },
	                {field:"open",title:"有效商品",width:100,    
                        formatter: function(value,row,index){  
                            if(value) {  
                                return "<input type='checkbox' checked='checked' disabled='true'";  
                            } else {  
                                return "<input type='checkbox' disabled='true'";  
                            }  
                        }  
                     },  
	                {field:"category.type",title:"所属商品类别",width:200, 
                        formatter: function(value,row,index){  
                        if(row.category != null && row.category.type != null) {  
                            return row.category.type; 
                        } else {  
                            return "此商品暂时未分类";  
                        }  
                    	}     
                    }    
                ]],
                
                toolbar: [{  
                    iconCls: 'icon-add',  
                    text:'添加类别',  
                    handler: function(){  
                        parent.$("#win").window({ //因为<div>放在了aindex.jsp中，所以这里创建窗口要先调用parent  
			            title:"添加类别",  
			            width:350,  
			            height:150,  
			            content:'<iframe src="send_product_save.action" frameborder="0" width="100%" height="100%"/>'  
			        	});
                    }  
                },'-',{  
                    iconCls: 'icon-edit',  
                    text:'更新类别',  
                    handler: function(){                    	  
                        //判断是否有选中行记录，使用getSelections获取选中的所有行  
				        var rows = $("#dg").datagrid("getSelections");  
				        if(rows.length == 0) {  
				            //弹出提示信息  
				            $.messager.show({ //语法类似于java中的静态方法，直接对象调用  
				                title:'错误提示',  
				                msg:'至少要选择一条记录',  
				                timeout:2000,  
				                showType:'slide',  
				            });  
				        }else if(rows.length != 1) {  
				            //弹出提示信息  
				            $.messager.show({ //语法类似于java中的静态方法，直接对象调用  
				                title:'错误提示',  
				                msg:'每次只能更新一条记录',  
				                timeout:2000,  
				                showType:'slide',  
				            });  
				        } else{  
				            //1. 弹出更新的页面  
				            parent.$("#win").window({  
				                title:"更新类别",  
				                width:350,  
				                height:250,  
				                content:'<iframe src="send_product_update.action" frameborder="0" width="100%" height="100%"/>'  
				            }); 
				    	}//end if 
                    }//end function  
                },'-',{  
                    iconCls: 'icon-remove',  
                    text:'删除类别',  
                    handler: function(){  
                        //判断是否有选中行记录，使用getSelections获取选中的所有行  
                        var rows = $("#dg").datagrid("getSelections");  
                        //返回被选中的行，如果没有任何行被选中，则返回空数组  
                        if(rows.length == 0) {  
                            //弹出提示信息  
                            $.messager.show({ //语法类似于java中的静态方法，直接对象调用  
                                title:'错误提示',  
                                msg:'至少要选择一条记录',  
                                timeout:2000,  
                                showType:'slide',  
                            });  
                        } else {  
                            //提示是否确认删除，如果确认则执行删除的逻辑  
                            $.messager.confirm('删除的确认对话框', '您确定要删除此项吗？', function(r){  
                                if (r){  
				                    //1. 从获取的记录中获取相应的的id,拼接id的值，然后发送后台1,2,3,4  
				                    var ids = "";  
				                    for(var i = 0; i < rows.length; i ++) {  
				                        ids += rows[i].id + ",";  
				                    }  
				                    ids = ids.substr(0, ids.lastIndexOf(",")); //substr截取从ids[0]开始，到lasIndexOf返回最后","的位置，的长度
				                    //2. 发送ajax请求  
				                    $.post("product_deleteByIds.action",{ids:ids},function(result){  
				                        if(result == "true") {  
	                                        //将刚刚选中的记录删除，要不然会影响后面更新的操作  
	                                        $("#dg").datagrid("uncheckAll");  
				                            //刷新当前页，查询的时候我们用的是load，刷新第一页，reload是刷新当前页  
				                            $("#dg").datagrid("reload");//不带参数默认为上面的queryParams  
				                        } else {  
				                            $.messager.show({   
				                                title:'删除异常',  
				                                msg:'删除失败，请检查操作',  
				                                timeout:2000,  
				                                showType:'slide',  
				                            });  
				                        }  
				                    });//end post 
				                }//end if(r) 
                            });//end $   
                        }//end else                         
                    }//end function 
                 },'-',{ //查询按钮不是LinkButton，它有语法，但是也支持解析HTML标签  
                     text:"<input id='ss' name='serach' />"  
                 }]
                                     
	    	});//2
	    	//把普通的文本框转化为查询搜索文本框  
            $('#ss').searchbox({   
                //触发查询事件  
                searcher:function(value,name){ //value表示输入的值  
                	 //添加触发代码  
                
                	$('#dg').datagrid('load',{  
			            name: value  
			        });
			        //if(!load){alert("没有匹配项");};      
                },   
                prompt:'请输入搜索关键字' //默认的显示
                  
            });
		     
          
    	});//1
    </script>
    	
  </head>
  
  <body>
    <table id="dg"></table> 
	
  </body>
</html>
