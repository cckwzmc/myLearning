// JavaScript Document
var xmlHttp=null;   
var isIe = (document.all) ? true : false;   
  
/** 传入一个层的名字就可以了 * */  
function showWindow(divName) {   
       
 document.getElementById(divName).style.display = 'block';   
 document.getElementById(divName).style.top=document.documentElement.scrollTop+(document.documentElement.clientHeight/3)-50+"px"; 
 var bWidth = parseInt(document.documentElement.scrollWidth);   
 var bHeight = parseInt(document.documentElement.scrollHeight);   
 var back = document.createElement("div");   
 if (isIe) {   
  setSelectState(divName,'hidden');   
 }   
 var back = document.createElement("div");   
 back.id = "back";   
 var styleStr = "top:0px;left:0px;position:absolute;background:#666;width:"  
   + bWidth + "px;height:" + bHeight + "px;";   
 styleStr += (isIe) ? "filter:alpha(opacity=0);" : "opacity:0;";   
 back.style.cssText = styleStr;   
 document.body.appendChild(back);   
 showBackground(back, 50);   
}   
  
// 关闭窗口   
function closeWindow(divName) {   
 document.getElementById(divName).style.display = 'none';   
 if (document.getElementById('back') != null) {   
  document.getElementById('back').parentNode.removeChild(document   
    .getElementById('back'));   
 }   
 if (isIe) {   
  setSelectState(divName,'');   
 }   
}   
  
// 让背景渐渐变暗   
function showBackground(obj, endInt) {   
 if (isIe) {   
  obj.filters.alpha.opacity += 5;   
  if (obj.filters.alpha.opacity < endInt) {   
   setTimeout( function() {   
    showBackground(obj, endInt)   
   }, 5);   
  }   
 } else {   
  var al = parseFloat(obj.style.opacity);   
  al += 0.01;   
  obj.style.opacity = al;   
  if (al < (endInt / 100)) {   
   setTimeout( function() {   
    showBackground(obj, endInt)   
   }, 5);   
  }   
 }   
}   
  
// 设置select的可见状态   
function setSelectState(divName,state) {   
 var objl = document.getElementsByTagName('select');   
 var objd = document.getElementById(divName).getElementsByTagName('select');   
 for ( var i = 0; i < objl.length; i++) {   
  objl[i].style.visibility = state;   
 }   
 for ( var i = 0; i < objd.length; i++) {   
  objd[i].style.visibility = '';   
 }   
}