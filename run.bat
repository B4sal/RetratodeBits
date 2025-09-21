@echo off
echo Compilando RetratoDeBits.java...
javac RetratoDeBits.java

if %errorlevel% neq 0 (
    echo Error en la compilacion
    pause
    exit /b 1
)

echo Ejecutando RetratoDeBits...
java RetratoDeBits

echo Limpiando archivos temporales...
del RetratoDeBits.class

echo Proceso completado!
pause