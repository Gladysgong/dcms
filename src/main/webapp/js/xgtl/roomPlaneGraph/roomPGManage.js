/**
 * Created by Ding on 2016/10/27.
 *
 */
/**
 * 页面初始化
 */
var dtApi;
function pageInit() {

	// 初始化菜单树
	var dtTable=$("#roomPGListTable");
	dtApi=dtTable.dataTable({
		/**
		 *
		 * @param data DT 封装的向后台传递的参数
		 * @param callback 回调函数，用户向DT传数据
		 * @param settings 一些设置
		 */
		ajax:function(data, callback, settings){
			//需要把分页参数转为DCMS接口规范的
			console.log(data);
			var pageNum=data.start/data.length+1,pageSize=data.length;
			var params={
				pageNum:pageNum,
				pageSize:pageSize,
				floorName:$("#searchFloorName").val(),
				fileName:$("#searchFileName").val()
			};
			DCMSUtils.Ajax.doPost("serviceRoomIcngph/datagrid",params).then(function (data) {
				if(data.status=='1'){
					//组织DT标准的返回值
					callback({
						data:data.data.records,
						recordsTotal:data.data.count,
						recordsFiltered:data.data.count
					});
				}
			});
		},
		columns: [
			{title: '楼层名称', data: 'floorName'},
			{title: '图片名称', data: 'imageName'},
			{title: 'Yml文件名称', data: 'ymlName'},
			{title: 'Json文件名称', data: 'jsonName'},
			{title: '操作', data: ''}
		],
		columnDefs:[
		{
			targets:1,
			render:function(data,type,row,meta){
				var html='<a onclick="downloadConfirm()" download="'+row.imageName+'" href="../../../serviceRoomIcngph/downloadFile?id='+row.id+'&fileName='+row.imageName+'">'+row.imageName+'</a>';
				return html;
			}
		},
		{
			targets:2,
			render:function(data,type,row,meta){
				var html='<a onclick="downloadConfirm()" download="'+row.ymlName+'" href="../../../serviceRoomIcngph/downloadFile?id='+row.id+'&fileName='+row.ymlName+'">'+row.ymlName+'</a>';
				return html;
			}
		},
		//onclick="download('+row.ymlName+','+row.id+')"
		{
			targets:3,
			render:function(data,type,row,meta){
				var html='<a onclick="downloadConfirm()" download="'+row.jsonName+'" href="../../../serviceRoomIcngph/downloadFile?id='+row.id+'&fileName='+row.jsonName+'">'+row.jsonName+'</a>';
				return html;
			}
		},
		{
			targets:4,
			render:function(data,type,row,meta){
				var html='<i class="glyphicon glyphicon-pencil" title="编辑" role=\"presentation\" data-toggle=\"modal\" data-target=\"#roomPGUpdate\" onclick="roomPGUpdateInit(\'' + row.id +'\',\''+row.floorName+'\')"></i>&nbsp;&nbsp;' +
					'<i class="glyphicon glyphicon-trash"  title="删除" onclick="roomPGDelete(\'' + row.id +'\',\''+row.floorName+'\')"></i>&nbsp;&nbsp;';
				return html;
			}
		}]
	}).api();

	dtTable.on("draw.DT", datatablesMtr.freeTableHeight(dtTable));

	$("#queryBtn").click(function(){
		dtApi.ajax.reload();
	});

	$("#resetBtn").click(function(){
		document.getElementById("queryForm").reset();
	});
}
function downloadConfirm(){

}
// function download(){
// 	var fileName = '1.png';
// 	var id = '531478698ca04c9abaa1bc2baa96c402';
// 	var formData={fileName:fileName,id:id};
// 	console.log("download"+fileName);
// 	DCMSUtils.Ajax.doPost("serviceRoomIcngph/downloadFile",formData).done((res)=>{
// 		if(res.status == "1"){
// 			console.log("添加平面图"+fileName+"成功");
// 			alert("添加平面图"+fileName+"成功");
// 			return true;
// 		}else{
// 			console.log("添加平面图"+fileName+"失败"+res.msg);
// 			alert("添加平面图"+fileName+"失败");
// 			return false;
// 		}
// 	});
// }



