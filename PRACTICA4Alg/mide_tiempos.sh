#!/bin/bash 
i=0
while [ "$i" -le 100 ]
do
	#j=3
	#while [ "$j" -le 15 ]
	#do
		./generador 11
		#./bt "matrizCostes.dat"
		./bb "matrizCostes.dat"

		#j=$(( $j + 1 ))
	#done
	
	i=$(( $i + 1 ))
done
