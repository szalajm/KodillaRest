call gradlew build
if "%ERRORLEVEL%" == "0" goto open
echo.
echo GRADLEW BUILD has errors - breaking work
goto fail

:open
call runcrud.bat
if "%ERRORLEVEL%" == "0" goto browser
echo.
echo Application cannot open runcrud.bat
goto fail

:browser
start opera
goto url

:ulr
opera --remote 'openURL(<http://localhost:8080/crud/v1/task/getTasks>, new-page)'
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished git add