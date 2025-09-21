#!/bin/bash
echo "Compilando RetratoDeBits.java..."
javac RetratoDeBits.java

if [ $? -ne 0 ]; then
    echo "Error en la compilación"
    exit 1
fi

echo "Ejecutando RetratoDeBits..."
java RetratoDeBits

echo "Limpiando archivos temporales..."
rm -f RetratoDeBits.class

echo "Proceso completado!"