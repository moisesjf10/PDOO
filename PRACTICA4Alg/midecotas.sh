#!/bin/bash 
i=3
while [ "$i" -le 30 ]
do
	./generador $i
	./cotas "matrizCostes.dat"

	i=$(( $i + 1 ))
done
