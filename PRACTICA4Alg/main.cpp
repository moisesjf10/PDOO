#include <iostream>
#include <vector>
#include <fstream>
#include <limits>
using namespace std;

int global = 0;

class pvcBackTracking{
private:
    vector<vector<double>> costes;
    vector<int> recorrido;
    double coste_solucion;

public:
    pvcBackTracking(char *archivo){
        costes.clear();
        recorrido.clear();

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

        coste_solucion=numeric_limits<double>::max();
    }

    double getCosteSolucion(){
        return coste_solucion;
    }

    vector<int> getRecorrido(){
        return recorrido;
    }

    void pvcbt(){
        vector<int> ciudades_visitadas;
        ciudades_visitadas.push_back(0);
        vector<int> ciudades_sinvisitar;

        int n = costes.size();
        for(int i = 1; i < n; ++i){
            ciudades_sinvisitar.push_back(i);
        }

        pvcbt(ciudades_visitadas,ciudades_sinvisitar,0);
    }

private:
    void pvcbt(const vector<int> &ciudades_visitadas, const vector<int> &ciudades_sinvisitar, double coste){

        if(ciudades_sinvisitar.empty()){
            coste += costes.at(0).at(*(ciudades_visitadas.end()-1));
            if (coste < coste_solucion){
                recorrido = ciudades_visitadas;
                coste_solucion = coste;
            }
        }
        else{
            vector<int> aux_ciudades_visitadas(ciudades_visitadas);
            vector<int>  aux_ciudades_sinvisitar;
            aux_ciudades_visitadas.push_back(*(ciudades_sinvisitar.begin()));
            for (int i = 0; i < (int) ciudades_sinvisitar.size(); i++) {
                aux_ciudades_visitadas.pop_back();
                aux_ciudades_visitadas.push_back(ciudades_sinvisitar.at(i));
                double coste_aux = coste + costes.at(*(aux_ciudades_visitadas.end()-1)).at(*(aux_ciudades_visitadas.end()-2));
                aux_ciudades_sinvisitar = ciudades_sinvisitar;
                aux_ciudades_sinvisitar.erase(aux_ciudades_sinvisitar.begin()+i);
                pvcbt(aux_ciudades_visitadas, aux_ciudades_sinvisitar,coste_aux);
            }
        }
    }

    //Devuelve una cota
    double pvcGreedy(){
        vector<int> r;
        r.push_back(0);

        int n=costes.size();
        vector<int> aux;
        for(int i=1; i<n; ++i) aux.push_back(i);

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

        return c;
    }



};


int main(int argc, char* argv[]){

    pvcBackTracking prueba(argv[1]);

    prueba.pvcbt();

    cout << "Coste total del recorrido: " << prueba.getCosteSolucion() << endl;

    vector<int> solucion = prueba.getRecorrido();
    for(vector<int>::iterator it = solucion.begin(); it != solucion.end(); ++it ){
        cout << *it << endl;
    }



    return 0;
}