var icon = "<i class='fa fa-times-circle'></i> ";
$('#roomPGAddForm').validate({
	rules:{
		floorName:{
			required:true,
			minlength:2,
			maxlength:50
		}
	},
	messages:{
		floorName:icon + "请输入2-50个字符的楼层信息"
	},
	submitHandler:function(form){
		roomPGAdd();
	}
});

function roomPGAdd(){
	// $("#roomPGAddForm").submit(function(event){
		var floorName = $("#floorName").val();
		var obj = document.getElementById("zipFile");
		var zipFile = obj.files[0];
		if(obj.value == "选择zip文件..." || obj.value == ""){
			DCMSUtils.Modal.toast('请选择需要上传的文件!','');
			return false;
		}
		console.log(obj.value);
		var stuff = obj.value.match(/^(.*)(\.)(.{1,8})$/)[3];
		if(stuff != 'zip'){
			DCMSUtils.Modal.toast('请选择zip类型的文件上传!','');
			return false;
		}
		var formData = new FormData($("#roomPGAddForm"));
		formData.append('floorName',floorName);
		formData.append('zipFile',zipFile);

		var dtd=$.Deferred();
		$.ajax({
            url:"../../../roomIcngph/add",
            type:'post',
            data: formData,
            async:true,
            processData: false,  // 告诉jQuery不要去处理发送的数据
            contentType: false   // 告诉jQuery不要去设置Content-Type请求头
          //   success:function(data){
          //   	if(data.status == 1){
	        	// 	DCMSUtils.Modal.toast('上传机房平面图信息成功'+data.msg,'');
	        	// 	dtApi.ajax.reload();
	        	// }else{
	        	// 	DCMSUtils.Modal.toast(data.msg,'');
	        	// }
          //   },
          //   error:function(data){
          //   	DCMSUtils.Modal.hideLoading();
          //   	DCMSUtils.Modal.toast(data.msg,'');
          //   }
            // success:function(data){
            // 	window.location.reload();
            // }
        }).done((jsonData)=>{
        	var data = jsonData;
    	   	if(data.status == 1){
        		DCMSUtils.Modal.toast('上传机房平面图信息成功'+data.msg,'');
        		dtApi.ajax.reload();
        		$("#roomPGAdd").modal('hide');
        	}else{
        		DCMSUtils.Modal.toast(data.msg,'');
        	}
        });
	// });
}