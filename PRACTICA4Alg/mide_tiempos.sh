#!/bin/bash 
i=0
while [ "$i" -le 50 ]
do
	j=3
	while [ "$j" -le 12 ]
	do
		./generador $j 
		./bt "matrizCostes.dat"
		./bb "matrizCostes.dat"

		j=$(( $j + 1 ))
	done
	
	i=$(( $i + 1 ))
done
