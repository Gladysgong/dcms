/**
 * Created by Charles on 2016/11/15.
 */
/**
 * Created by Charles on 2016/11/12.
 */
var globlePageNum = 1;//页码
var globlePageSize = 10;//行数

function pageInit(){
    getClassList(globlePageNum,globlePageSize);
}

function getClassList(pageNum,pageSize){
    DCMSUtils.Modal.showLoading('分类加载中...');
    DCMSBusi.Api.invoke('product/category/datagrid', {pageNum: pageNum, pageSize: pageSize})
        .then(function (data) {
            $("#treeBody").empty();
            DCMSUtils.Modal.hideLoading();
            console.log(data);
            if (data.status === 1) {
                data=data.data;
                initTreeGird($("#treeBody"),data.records, 0);
                $(".tree").treegrid({
                    initialState:'collapsed'
                });
                //分页
                $("#treePage").jqPaginator({
                    totalPages: data.pageCount,
                    visiblePages: 5,
                    currentPage: pageNum,
                    first:'<li class="first"><a href="javascript:;">首页</a></li>',
                    last:'<li class="last"><a href="javascript:;">末页</a></li>',
                    prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
                    next: '<li class="next"><a href="javascript:;">下一页</a></li>',
                    page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
                    onPageChange: function (num, type) {
                        if(type=='change'){
                            globlePageNum=num;
                            getClassList(globlePageNum,globlePageSize);
                        }
                    }
                });
            } else {
                DCMSUtils.Modal.toast(data.msg, 'forbidden');
            }
        }, function (error) {
            DCMSUtils.Modal.hideLoading();
            DCMSUtils.Modal.toast('获取分类异常', 'forbidden');
        });
}

var index = 0;

function initTreeGird(container, dataList, parentIndex) {
    for (var i = 0; i < dataList.length; i++) {
        index++;
        var type = dataList[i];

        var trHtml = '<tr class="treegrid-' + index + '';
        if(parentIndex){
            trHtml+=' treegrid-parent-'+parentIndex;
        }
        trHtml += '">';
        trHtml += '<td>' + type.name + '</td>';
        trHtml += '<td>' + type.coding + '</td>';
        trHtml += '<td>' + (type.description?type.description:'') + '</td>';
        trHtml += '<td>' +
            '<i class="glyphicon glyphicon-plus"   title="新增分类"     onclick="newUpdate(\''+type.id+'\',\'new\')"></i>&nbsp;&nbsp;' +
            '<i class="glyphicon glyphicon-pencil" title="编辑分类"     onclick="newUpdate(\''+type.id+'\',\'update\')"></i>&nbsp;&nbsp;'+
            '<i class="glyphicon glyphicon-trash"  title="删除分类"     onclick="deleteFun(\''+type.id+'\')"></i>&nbsp;&nbsp;' +
            '</td>';
        trHtml += '</tr>';

        container.append((trHtml));

        //保存dataList数据，方便编辑等
        var dataMap=DCMSUtils.SessionStorage.get("TYPE_DATA_MAP");
        if(!dataMap){
            dataMap={};
        }
        dataMap[type.id]=type;
        DCMSUtils.SessionStorage.set("TYPE_DATA_MAP",dataMap);

        //子节点初始化
        var children=type.children;
        if(children && children.length>0){
            var pIndex=index;
            initTreeGird(container,children,pIndex);
        }
    }
}
$("#modal").draggable();
function newUpdate(id,type) {
    if('new'==type){
        $("#modalTitle").text('新增分类');
        $("#typeId").val('');
        document.getElementById('newUpdateForm').reset();
        if(id){
            var type=DCMSUtils.SessionStorage.get("TYPE_DATA_MAP")[id];
            $("#typePId").val(type.id);
            $("#typePName").html(type.name);
            $(".typePDiv").css('display','block');
        }else{
            $("#typePId").val('');
            $("#typePName").html('');
            $(".typePDiv").css('display','none');
        }
    }else{
        $("#modalTitle").text('编辑分类');
        document.getElementById('newUpdateForm').reset();

        var type=DCMSUtils.SessionStorage.get("TYPE_DATA_MAP")[id];
        $("#typeId").val(type.id);
        $("#typeName").val(type.name);
        $("#typeCoding").val(type.coding);
        $("#typeDesc").val(type.description);

        var pType=DCMSUtils.SessionStorage.get("TYPE_DATA_MAP")[type.parentId];
        if(pType){
            $("#typePId").val(pType.id);
            $("#typePName").html(pType.name);
            $(".typePDiv").css('display','block');
        }
    }
    $("#modal").modal();
}
var icon = "<i class='fa fa-times-circle'></i> ";
$("#newUpdateForm").validate({
    rules:{
        typeName:{
            required:true,
            minlength:2,
            maxlength:50
        },
        typeCoding: {
            minlength: 2,
            maxlength: 50
        },
        typeDesc:{
            minlength:2,
            maxlength:120
        }
    },
    messages:{
        typeName:icon + "请输入2-50个字符的分类名称",
        typeCoding:icon + "请输入2-50个字符的分类编码",
        typeDesc:icon + "请输入2-120个字符的分类描述"
    },
    submitHandler:function(form){
        var type={
            id:$("#typeId").val(),
            name:$("#typeName").val(),
            coding:$("#typeCoding").val(),
            parentId:$("#typePId").val(),
            description:$("#typeDesc").val()
        }

        var url='product/category/add';
        if(type.id){
            url='product/category/update';
        }
        $("#modal").modal('hide');
        DCMSUtils.Modal.showLoading();
        DCMSBusi.Api.invoke(url,type).then(function(data){
            DCMSUtils.Modal.hideLoading();
            if(data.status=='1'){
                //保存成功清空form
                document.getElementById("newUpdateForm").reset();
                getClassList(globlePageNum, globlePageSize);
                DCMSUtils.Modal.toast('保存分类成功','');
            }else{
                DCMSUtils.Modal.toast('保存分类出错'+data.msg,'forbidden');
            }
        },function(error){
            DCMSUtils.Modal.hideLoading();
            DCMSUtils.Modal.toast('保存分类异常','forbidden');
        });
    }
});

function deleteFun(id){
    var type=DCMSUtils.SessionStorage.get("TYPE_DATA_MAP")[id];

    DCMSUtils.Modal.confirm('确定删除分类['+type.name+']吗？','',function () {
        DCMSUtils.Modal.showLoading('分类删除中...');
        DCMSBusi.Api.invoke('product/category/delete',{id:id}).then(function(data){
            DCMSUtils.Modal.hideLoading();
            if(data.status=='1'){
                getClassList(globlePageNum, globlePageSize);
                DCMSUtils.Modal.toast('删除分类成功','');
            }else{
                DCMSUtils.Modal.toast('删除分类出错'+data.msg,'forbidden');
            }
        },function (error) {
            DCMSUtils.Modal.hideLoading();
            DCMSUtils.Modal.toast('删除分类异常','forbidden');
        });
    })
}