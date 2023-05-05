#include <iostream>
#include <random>
#include <iomanip>
#include <fstream>
#include <vector>

using namespace std;
int main(int argc, char *argv[]) {

    if (argc != 2){
        cout << "Error el número de argumentos no es correcto";
        exit(-1);
    }

    int n = atoi(argv[1]);

    ofstream fsalida("./matrizCostes.dat");

    vector<vector<double>> m;
    m.resize(n, vector<double>(n));

    const double MAX = 1000;
    const double MIN = 0;
    random_device rd;
    default_random_engine eng(rd());
    uniform_real_distribution<double> distr(MIN, MAX);

    for (int i = 0; i < n; i++){
        for (int j = i; j < n; j++){
            if(i == j){
                m.at(i).at(i) = -1;
            }else {
                double valor = distr(eng);
                m.at(i).at(j) = valor;
                m.at(j).at(i) = valor;
            }

        }

    }

    fsalida << n << endl;

    for (int i = 0; i < n; i++){
        for (int j = 0; j < n; j++){
            fsalida << fixed;
            fsalida <<  setprecision(3) << m.at(i).at(j)  << " ";

        }
        fsalida << "\n";
    }




    return 0;
}