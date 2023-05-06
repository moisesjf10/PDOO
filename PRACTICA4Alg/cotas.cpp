#include <iostream>
#include <vector>
#include <fstream>
#include <limits>
#include <ctime>

using namespace std;
class cota {
private:
    vector<vector<double>> costes;
    double coste_solucion;
    double minimo;
public:
    cota(char *archivo){
        costes.clear();
        ifstream entrada(archivo);
        if (entrada.is_open()) {
            int n;
            entrada >> n;
            for(int i=0; i<n; i++){
                vector<double> aux;
                for(int j=0; j<n; j++){
                    double c;
                    entrada >> c ;
                    aux.push_back(c);
                }
                costes.push_back(aux);
            }
            entrada.close();
        }else {
            cout << "ERROR: Fichero no encontrado" << endl;
            exit(-1);
        }
        minimo = Minimo();
    }
    bool cota0(const vector<int> &ciudades_visitadas, const vector<int> &ciudades_sinvisitar, double coste) {
        return true;
    }

    //cota1: encuentra la arista de menor peso y multiplica dicho peso por el número de ciudades sin visitar
    bool cota1(const vector<int> &ciudades_visitadas, const vector<int> &ciudades_sinvisitar, double coste) {
        return ((coste + minimo * ciudades_sinvisitar.size()) < coste_solucion);
    }

    //cota2: considera el minimo costo de SALIR de cada vértice
    bool cota2(const vector<int> &ciudades_visitadas, const vector<int> &ciudades_sinvisitar, double coste) {
        double suma = coste;
        int n = ciudades_sinvisitar.size();
        int a = ciudades_visitadas.front();
        int b = ciudades_visitadas.back();

        double min = costes.at(a).at(b);

        for (int i = 0; i < n; i++) {
            if (min > costes.at(b).at(ciudades_sinvisitar.at(i))) {
                min = costes.at(b).at(ciudades_sinvisitar.at(i));
            }
        }
        suma += min;

        for (int i = 0; i < n; i++) {
            min = costes.at(ciudades_sinvisitar.at(i)).at(a);
            for (int j = 0; j < n; j++) {
                if (min > costes.at(ciudades_sinvisitar.at(j)).at(ciudades_sinvisitar.at(i)) &&
                    costes.at(ciudades_sinvisitar.at(j)).at(ciudades_sinvisitar.at(i)) != -1) {
                    min = costes.at(ciudades_sinvisitar.at(j)).at(ciudades_sinvisitar.at(i));
                }
            }
            suma += min;
        }

        return (suma < coste_solucion);

    }

    //cota3: considera el inimo costo de SALIR Y ENTRAR en cada vértice
    bool cota3(const vector<int> &ciudades_visitadas, const vector<int> &ciudades_sinvisitar, double coste) {
        double suma = coste;
        int n = costes.size();

        int a = ciudades_visitadas.front();
        int b = ciudades_visitadas.back();

        double min_a = numeric_limits<double>::max();
        double min_b = numeric_limits<double>::max();

        for (int i = 0; i != n; i++) {
            if (costes.at(a).at(i) != -1 && costes.at(a).at(i) < min_a) min_a = costes.at(a).at(i);
            if (costes.at(b).at(i) != -1 && costes.at(b).at(i) < min_b) min_b = costes.at(b).at(i);
        }
        suma += (min_a + min_b) / 2;

        double min1;
        double min2;
        for (auto it = ciudades_sinvisitar.begin(); it != ciudades_sinvisitar.end(); ++it) {
            if (costes.at(*it).at(a) < costes.at(*it).at(b)) {
                min1 = costes.at(*it).at(a);
                min2 = costes.at(*it).at(b);
            } else {
                min1 = costes.at(*it).at(b);
                min2 = costes.at(*it).at(a);
            }
            for (int i = 1; i < n; ++i) {
                if (costes.at(*it).at(i) < min2) {
                    if (costes.at(*it).at(i) < min1) {
                        min2 = min1;
                        min1 = costes.at(*it).at(i);

                    } else {
                        min2 = costes.at(*it).at(i);
                    }
                }
            }
            suma += (min1 + min2) / 2;
        }

        return (suma <= coste_solucion);
    }


    bool cota4(const vector<int> &ciudades_visitadas, const vector<int> &ciudades_sinvisitar, double coste) {
        double suma = coste;

        int a = ciudades_visitadas.front();
        int b = ciudades_visitadas.back();

        double min_a = numeric_limits<double>::max();
        double min_b = numeric_limits<double>::max();

        for (auto it = ciudades_sinvisitar.begin(); it != ciudades_sinvisitar.end(); ++it) {
            if (costes.at(a).at(*it) != -1 && costes.at(a).at(*it) < min_a) min_a = costes.at(a).at(*it);
            if (costes.at(b).at(*it) != -1 && costes.at(b).at(*it) < min_b) min_b = costes.at(b).at(*it);
        }
        suma += (min_a + min_b) / 2;

        double min1;
        double min2;

        for (auto it = ciudades_sinvisitar.begin(); it != ciudades_sinvisitar.end(); ++it) {
            if (costes.at(*it).at(a) < costes.at(*it).at(b)) {
                min1 = costes.at(*it).at(a);
                min2 = costes.at(*it).at(b);
            } else {
                min1 = costes.at(*it).at(b);
                min2 = costes.at(*it).at(a);
            }

            for (auto j = ciudades_sinvisitar.begin(); j != ciudades_sinvisitar.end(); ++j) {
                if ((costes.at(*it).at(*j) != -1) && (costes.at(*it).at(*j) < min2)) {
                    if (costes.at(*it).at(*j) < min1) {
                        min2 = min1;
                        min1 = costes.at(*it).at(*j);

                    } else {
                        min2 = costes.at(*it).at(*j);
                    }
                }
            }
            suma += (min1 + min2) / 2;
        }
        return (suma <= coste_solucion);
    }
    pair<vector<int>,double> pvcGreedy(const vector<int> &visitadas, const vector<int> &sin_visitar){
        vector<int> r(visitadas);

        vector<int> aux(sin_visitar);

        vector<int>::iterator it = aux.begin();
        vector<int>::iterator it_min;

        double c=0;
        while(!aux.empty()) {
            double min = costes.at(*it).at(*(r.end() - 1));
            it_min = it;
            for (it = aux.begin() + 1; it != aux.end(); ++it) {
                double otro = costes.at(*it).at(*(r.end() - 1));
                if (min > otro) {
                    min = otro;
                    it_min = it;
                }
            }
            c += min;
            r.push_back(*it_min);
            aux.erase(it_min);
            it = aux.begin();
        }
        c+=costes.at(0).at(*(r.end() - 1));

        return {r,c};
    }

    //Algoritmo greedy empezando por la ciudad 0
    pair<vector<int>,double> pvcGreedy(){
        vector<int> visitadas;
        visitadas.push_back(0);
        vector<int> sin_visitar;
        int n=costes.size();
        for(int i=1; i<n ; i++) sin_visitar.push_back(i);

        return pvcGreedy(visitadas,sin_visitar);
    }

    //Encuentra el mínimo de la matriz de costes
    double Minimo(){
        int min=costes.at(0).at(1);
        int n=costes.size();
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(costes.at(i).at(j)<min) min=costes.at(i).at(j);
            }
        }
        return min;
    }

    int getTamCostes(){
        return costes.size();
    }
};

int main(int argc, char **argv){
    int NUM_REPS=50;
    cota prueba(argv[1]);

    int n=prueba.getTamCostes();

    vector<int> visitados;
    visitados.push_back(0);
    vector<int> sin_visitar;
    for(int i=1; i<n; ++i) sin_visitar.push_back(i);

    clock_t t_antes = clock();
    for(int i=0; i<NUM_REPS; i++) {
        prueba.cota1(visitados, sin_visitar, 0);
    }
    clock_t t_despues = clock();

    ofstream salida;
    salida.open("Tcota1.dat",fstream::app);
    salida << n << " " << ((double) ((t_despues - t_antes)) / (CLOCKS_PER_SEC*NUM_REPS)) << endl;
    salida.close();

    t_antes = clock();
    for(int i=0; i<NUM_REPS; i++) {
        prueba.cota2(visitados, sin_visitar, 0);
    }
    t_despues = clock();

    ofstream salida2;
    salida2.open("Tcota2.dat",fstream::app);
    salida2 << n << " " << ((double) ((t_despues - t_antes)) / (CLOCKS_PER_SEC*NUM_REPS)) << endl;
    salida.close();

    t_antes = clock();
    for(int i=0; i<NUM_REPS; i++) {
        prueba.cota3(visitados, sin_visitar, 0);
    }
    t_despues = clock();

    ofstream salida3;
    salida3.open("Tcota3.dat",fstream::app);
    salida3 << n << " " << ((double) ((t_despues - t_antes)) / (CLOCKS_PER_SEC*NUM_REPS)) << endl;
    salida.close();

    t_antes = clock();
    for(int i=0; i<NUM_REPS; i++) {
        prueba.cota4(visitados, sin_visitar, 0);
    }
    t_despues = clock();

    ofstream salida4;
    salida4.open("Tcota4.dat",fstream::app);
    salida4 << n << " " << ((double) ((t_despues - t_antes)) / (CLOCKS_PER_SEC*NUM_REPS)) << endl;
    salida.close();




	return 0;
}
