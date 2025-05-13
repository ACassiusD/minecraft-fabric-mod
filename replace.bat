@echo off
if "%~1"=="" (
  echo Usage: %~nx0 path-to-java-file
  exit /b 1
)

set "file=%~1"
powershell -NoProfile -ExecutionPolicy Bypass -Command ^
  "(Get-Content -Raw -LiteralPath '%file%') -replace 'pivot','origin' | Set-Content -LiteralPath '%file%'"

echo Done replacing 'pivot' with 'origin' in "%file%"