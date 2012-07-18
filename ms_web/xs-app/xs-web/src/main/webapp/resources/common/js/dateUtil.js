/**
 * 生成日期
 * @param ystart 开始日期
 * @param yend 结束日期
 * @return
 */
function dateCreate(ystart,yend)
{
	 
	 for (var i = yend; i >= ystart; i--) //以今年为准，前30年，后30年
		 $("#birthdayYear").append("<option value='"+i+"'>"+i+"</option>");
	 for (var i = 1; i < 13; i++)
		 $("#birthdayMonth").append("<option value='"+i+"'>"+i+"</option>");
	
}
/**
 * 选择年，月时调用
 * @return
 */
function changeDate(){
	var maxMonthDate = [0,31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

	$("#birthdayDate option").remove();
	$("#birthdayDate").append("<option value='1'>日</option>");
	if($("#birthdayYear").val() != 0 && $("#birthdayMonth").val() != 0){
		
		if ((($("#birthdayYear").val() % 4) == 0) && (($("#birthdayYear").val() % 100) != 0) || (($("#birthdayYear").val() % 400) == 0))
		{
			maxMonthDate = [0,31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
		}
		
		for(var i=1;i<=maxMonthDate[$("#birthdayMonth").val()] ;i++){
			$("#birthdayDate").append("<option value='"+i+"'>"+i+"</option>");			
		}
	}
}