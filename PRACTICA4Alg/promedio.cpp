#include<iostream>
#include <fstream>
#include <vector>
using namespace std;


int main(int argc, char **argv){
	ifstream fentrada(argv[1]);
	vector<vector<double>> matriz;
	matriz.resize(10);
	for(int i=0; i<=50; i++){
		for(int j=0; j<10; j++){
			int tamaño;
			fentrada >> tamaño;
			double dato;
			fentrada >> dato;
			matriz.at(j).push_back(dato);
		}
	}
	
	
	for(int j=0; j<10; j++){
		double media=0;
		for(int i=0; i<=50; i++){
			media+=matriz.at(j).at(i);
		}
		cout << media/50 << endl;
	}
	


	return 0;
}
