@echo off
set commit_msg=%1 %2 %3 %4 %5 %6


if "%commit_msg%"=="     " (
    echo ::::please input COMMIT INFO::::
    echo     %0 ^<commit message^>
    goto  end
)

git add .
git commit -m "%commit_msg%"
git push

:end
@echo off
echo ...
