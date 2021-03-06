var initWidth = 100;
var bgWidth;
var bgHeight;
var times;  
var jsonDatas='';
var ymlDatas='';
var scrollHeight = $("#roomBody").height();
var scrollWidth = $("#roomBody").width();
var disFlag = '';
var equipFlag = '';
var selectedEquipments = {};

function roomPGWatch(id,dis,equip){
	var path = '../../../roomIcngph/getImage?id='+id;
	$('#roombg').attr('src', path);
	$("#roombg")[0].onload = function(){
		document.getElementById('roomBody').style.width ='100%';
		var bg = document.getElementById("roombg");
		var image = new Image();
		image.src = bg.src;
		bgWidth = image.width;
		bgHeight = image.height;
		console.log("dis"+dis);
		times = 1;//平面图初始放大倍数
		DCMSUtils.Modal.showLoading('机房平面图加载中...');
		DCMSBusi.Api.invoke('roomIcngph/getJson',{id:id}).then(function(data){
	        if(data.status=='1'){
	           jsonDatas = data.data;
	           if(equip != ''){
	           		equipFlag = equip;//记录机柜管理所要查看的机柜资源编码
	           }
	           EquipmentFloat();
	           
	           
				var roomBody = document.getElementById('roomBody');
				if (roomBody.addEventListener) {   
			    // IE9, Chrome, Safari, Opera   
				    roomBody.addEventListener("mousewheel", MouseWheelHandler, false);   
				    // Firefox   
				    roomBody.addEventListener("DOMMouseScroll", MouseWheelHandler, false);   
				}   
				// IE 6/7/8   
				else roomBody.attachEvent("onmousewheel", MouseWheelHandler);
	        }
	        else{
	            DCMSUtils.Modal.toast('json出错'+data.msg,'forbidden');
	        }
	    },function (error) {
	        // DCMSUtils.Modal.hideLoading();
	        DCMSUtils.Modal.toast('json出错','forbidden');
	    });

		DCMSBusi.Api.invoke('roomIcngph/getYml',{id:id}).then(function(data){
	        if(data.status=='1'){
	           ymlDatas = data.data;
	           var html = '';
	           for(var i in ymlDatas){
	           		html += '<option value="'+i+'">'+i+'</option>';
	           }
	           document.getElementById('chooseDis').innerHTML = html;
	           document.getElementById('chooseDisGroup').innerHTML = html;
	           if(dis != ''){
	           	disFlag = dis;
	           	disFocus(dis);
	           }
	           console.log(ymlDatas);
	        }
	        else{
	            DCMSUtils.Modal.toast('yml出错'+data.msg,'forbidden');
	        }
	    },function (error) {
	        // DCMSUtils.Modal.hideLoading();
	        DCMSUtils.Modal.toast('yml出错','forbidden');
	    });
		DCMSUtils.Modal.hideLoading();
		
	}
}

function districtFocus(){
	var dis = $("#chooseDis").val();
	// DCMSUtils.Modal.toast(dis,'');
	disFlag = '';
	disFocus(dis);
}

function disFocus(dis){
	scrollHeight = $("#roomBody").height();//document.body.scrollHeight;
	scrollWidth = $("#roomBody").width();
	var leftUpY = ymlDatas[dis][0][1]/bgHeight*scrollHeight/2.12;
	var leftUpX = ymlDatas[dis][0][0]/bgWidth*scrollWidth/2.12;
	var rightDownY = ymlDatas[dis][3][1]/bgHeight*scrollHeight/2.12;
	var rightDownX = ymlDatas[dis][3][0]/bgWidth*scrollWidth/2.12;
	// console.log('ly:'+leftUpY+','+ymlDatas[dis][0][1]+','+bgHeight+','+scrollHeight);
	// console.log('lx:'+leftUpX+','+ymlDatas[dis][0][0]+','+bgWidth+','+scrollWidth);
	// console.log('ry:'+rightDownY+','+ymlDatas[dis][3][1]+','+bgHeight+','+scrollHeight);
	// console.log('rx:'+rightDownX+','+ymlDatas[dis][3][0]+','+bgWidth+','+scrollWidth);
	var y = (rightDownY - leftUpY)+'px';
	var x = (rightDownX -leftUpX)+'px';
	// var style = "style='width:"+x+"px;border:2px dotted red;height:"+y+"px;position: absolute;top:"+leftUpY+";left:"+leftUpX+";-webkit-animation:flash 1s 2 ease-in-out;'";
	$("#disAnimation").css({
		'z-index':'0',
		'width':x,
		'border':'5px dotted red',
		'height':y,
		'position':'absolute',
		'top':leftUpY+'px',
		'left':leftUpX+'px',
		'-webkit-animation':'flash 5s 12 ease-in-out'
	});
}

function MouseWheelHandler(e){
	var e = window.event || e; // old IE support   
    var delta = Math.max(-1, Math.min(1, (e.wheelDelta || -e.detail))); 
    initWidth += 5 * delta;
    console.log(delta); 
    // roombg.style.zoom = Math.max(0.5, Math.min(1.5, roombg.width + (0.1 * delta))); 
    var width = Math.max(20, Math.min(150, initWidth));
    // if(width != 150){
    // 	window.scrollTo(0,0);
    // }
    times = width/100;
    roomBody.style.width = width + '%';
    EquipmentFloat();
    if(disFlag){
    	disFocus(disFlag);
    }else{
    	districtFocus();
    }
    
    console.log(width);
    //var widths = width.toString;
    
    return false;   
}

function EquipmentFloat(){
	//var data_id = JSON.parse(window.sessionStorage.getItem('data-id'));
	var eq='';
	var equipmentsExistFlag = 0;
	scrollHeight = $("#roomBody").height();//document.body.scrollHeight;
	scrollWidth = $("#roomBody").width();
	if(document.getElementById('equipments').childNodes.length > 1){
		equipmentsExistFlag = 1;
	}
	//console.log(scrollHeight);
	for(var i in jsonDatas){
		if(i == ''){
			continue;
		}
		var data = jsonDatas[i];
		var eqWidth = ((data[7] - data[1])/bgWidth*times*100);
		var eqHeight = ((data[2] - data[6])/bgHeight*scrollHeight);
		// var eqHeight = (data[2] - data[6])*times;
		var eqLeft = (data[1]/bgWidth*times*100);
		var eqTop = (data[4]/bgHeight*scrollHeight);
		// var eqTop = data[4]*times;
		var color;
		switch(data[0]){
			case 0:
				color = '#6495ed';
				break;
			case 1:
				color = '#A52A2A';
				break;
			case 2:
				color = '#32b16c';
				break;
			case 3:
				color = '#f19ec2';
				break;
			case 4:
				color = 'purple';
				break;
			default:
				color = 'navy';
				break;
		}
		var style = 'style="cursor:pointer;position:absolute;z-index:1;left:'+eqLeft+'%;top:'+eqTop+'px;width:'+eqWidth+'%;height:'+eqHeight+'px;background-color:'+color+';border-left:2px solid #bcbcbc;border-bottom:2px solid #bcbcbc;"'
		// var style = 'style="position:absolute;left:100px;top:60px;width:50px;height:60px;background-color:red;"'; 
		if(equipmentsExistFlag == 1){
			var tmp = document.getElementById(i);
			var rate = eqHeight/$('#'+i+' i').height();
			tmp.style.left = eqLeft+'%';
			tmp.style.top = eqTop+'px';
			tmp.style.width = eqWidth+'%';
			tmp.style.height = eqHeight+'px';
			// if(tmp.firstChild.firstChild.nodeValue == 'A1-10'){
			// 	console.log('A1-10:height'+$('#'+i+' i').height());
			// 	console.log('eqHeight'+eqHeight);
			// }
			
		    // if ($('#'+i+' i').height() > eqHeight) {
		    //     //当容器高度大于最大高度的时候，按比例缩放
		    //     $('#'+i+' i').css({
		    //     	'display':'inline-block',
		    //     	'-webkit-transform':'scale('+rate+')'
		    //     })    
		    // }
		}else{
			var mouseOverOut = 'onmouseover="this.style.backgroundColor=\'#c19288\'" onmouseout="this.style.backgroundColor=\''+color+'\'"';
			var data_content = "机柜编号<br />品牌<br />承载设备数量<br />已用空间大小<br />承重大小<br />用电信息";
			var dataPopOver = 'title="'+i+'" data-trigger="hover" data-html="true" data-container="#roomBody" '+
			'data-toggle="popover" data-placement="top" '+
			'data-content="'+data_content+'"';
			var classType = 'equipment popover-toggle';
			if(i == equipFlag){
				classType += ' selected';
			}
			// if(data_id == i){
			eq += '<span '+dataPopOver+'class="'+classType+'" id="'+i+'" data-color="'+color+'" data-id="'+i+'"'+style+'onclick=make(this)><i>'+i+'</i></span>';
			// }else{
			// 	eq += '<span '+dataPopOver+'class="'+classType+'" id="'+i+'" data-id="'+i+'"'+style+'onclick=make(this)><i>'+i+'</i></span>';
			// }
		}
	}
	if(equipmentsExistFlag == 0){
		document.getElementById('equipments').innerHTML = eq;
		$('span.equipment.selected').css({
			'background-color': '#c19288'
		});
		$(function(){ 
			$("[data-toggle='popover']").popover();
			console.log('popover!!!');
		});
		// var tmp = document.getElementById(i);
		// for (var i = 12; i < 200; i++) {
		//     if (tmp.firstChild.height() > eqHeight) {
		//         //当容器高度大于最大高度的时候，上一个尝试的值就是最佳大小。
		//         tmp.style.fontSize = (i - 2) + 'px';
		//         //结束循环
		//         break;
		//     } else {
		//         //如果小于最大高度，文字大小加1继续尝试
		//         tmp.style.fontSize = i + 'px';
		//     }
		// }
	}
}

function make(e){
	var id = e.getAttribute('data-id');
	var color = e.getAttribute('data-color');

	// var test = JSON.parse(window.sessionStorage.getItem('data-id'));
	if($('#'+id).hasClass('choosed')){
		// window.sessionStorage.removeItem('data-id');
		$('#'+id).removeClass('choosed');
		$('#'+id).css({
			'background-color':color
		});
		delete selectedEquipments[id];
	}else{
		// window.sessionStorage.setItem('data-id',JSON.stringify(id));
		$('#'+id).addClass('choosed');
		$('#'+id).css({
			'background-color':'#c19288'
		});
		selectedEquipments[id] = id;
		for(var k in selectedEquipments){
			console.log(k+' groups '+selectedEquipments[k]);
		}
		// console.log(selectedEquipments+'groups'+selectedEquipments[id]);
	}
}
//关闭机房平面图后，清除背景和机柜位置信息
function clearRoombg(){
	$('#equipments').empty();
	$('#disAnimation').css({
		'border':'0 solid red'
	});
	$('#roombg').attr('src', '');
	times = 1;
}

$('div.popover.fade').css({
	'backgroundColor':'black',
	'opacity':'0.7'
});

$('#opWords').click(function(){

});
$('#opGroupToggle').click(function(){
	$('#operations_group').toggleClass("appear_opGroup");
});

$('#opWordsToggle').click(function(){
	console.log('opwt');
	$('.equipment > i').each(function(){
		$(this).toggleClass("disappear_words");
	});
});
$('#opLocToggle').click(function(){
	$('#operations_locate').toggleClass("appear_opLoc");
});
$('#opSeaToggle').click(function(){
	$('#operations_search').toggleClass("appear_opSea");
});
$('#expandSearch').click(function(){
	$('#operations_search_secondlevel').toggleClass("appear_opSea2");
});

$('#operations_group button').click(function(){
	var districtName = $('#operations_group input').val();
	console.log('districtName'+districtName);
	console.log(selectedEquipments.length+'group'+selectedEquipments);
	var tmpEqs = '';
	for(var k in selectedEquipments){
		tmpEqs += selectedEquipments[k] + ',';
	}
	tmpEqs = tmpEqs.substring(0,tmpEqs.length-1);
	var cabinetResourceCodes = tmpEqs;
	var name = districtName;
	selectedEquipments = {};
	// var name = $('#districtName').val();
	// var roomId = $('#districtRoomId').val();
	// console.log('roomId'+roomId);
	// var remark = $('#districtRemark').val();
	// var cabinetResourceCodes = $('#cabinetResourceCodes').val();
	// var disInfo = {name:name,roomId:roomId,remark:remark,cabinetResourceCodes:cabinetResourceCodes};
	// DCMSBusi.Api.invoke("area/add",disInfo).done((res)=>{
	// 	if(res.status == "1"){
	// 		DCMSUtils.Modal.toast("添加区域"+name+"成功",'');
	// 		dtApi.ajax.reload();
 //        	$("#districtAdd").modal('hide');
 //            districtAddFormReset();//重置表单
	// 		return true;
	// 	}else{
	// 		DCMSUtils.Modal.toast("添加区域失败"+res.msg,'');
	// 		return false;
	// 	}
	// });
});

$('#operations_locate button').click(function(){
	var name = $('#operations_locate input').val();
	EquipmentFloat(times);
	console.log($('#'+name).length);
	if(name == ''){
		// alert("请输入您要查找的设备！");
		DCMSUtils.Modal.toast('请输入您要查找的设备！','');
	}else if($('#'+name).length){
		$('#'+name).css({'background-color':'green'});
	}else{
		DCMSUtils.Modal.toast('您输入的设备不存在！','');
		// alert("您输入的设备不存在！");
	}
	
});
// });

function searchDistrict(){
}
function locateInput(){
}
function locateEquipment(){
}