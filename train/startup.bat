title ²ÊÆ±×¥È¡


 @echo off
	setlocal enabledelayedexpansion
	echo %java_home%
	set jre="%java_home%\bin\java"
	set tempclass="classes";"%java_home%\lib\dt.jar";"%java_home%\lib\tools.jar";.
	for %%i in (lib\*.*) do (
		set tempclass=!tempclass!;%%i
		
	)
	echo %tempclass%
	
	start "Æô¶¯......" %jre% -classpath !tempclass! com.lyxmq.lottery.ssq.ReverseTestMain
	echo "";
		echo "";
			echo "";
endlocal  

