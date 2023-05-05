#include <iostream>
#include <vector>
#include <fstream>
#include <limits>
#include <ctime>
using namespace std;

class pvcBackTracking{
private:
    vector<vector<double>> costes; //matriz de costes
    vector<int> recorrido;         //solucion
    double coste_solucion;
    double minimo; //arista minima del grafo
    //Consideramos nodos creados, a todos aquellos a los que se les calcula el coste.
    int nodos_creados;
    //Consideramos llamadas a cota, todos aquellos nodos a los que se les aplica la función de cota.
    int llamadas_a_cota;
    


public:
    //Constructor
    //Recibe el nombre del archivo en el que están los datos
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
        minimo = Minimo();
        reseteo();
    }

    double getCosteSolucion(){
        return coste_solucion;
    }

    vector<int> getRecorrido(){
        return recorrido;
    }

    int getNodoscreados(){
        return nodos_creados;
    }
    
    int getLlamadasCota(){
    	return llamadas_a_cota;
    }

    double valor(int i, int j){
        return costes.at(i).at(j);
    }

    void reseteo(){
        pair<vector<int>,double> greedy = pvcGreedy();
        coste_solucion=greedy.second;
        recorrido=greedy.first;
        nodos_creados=0;
        llamadas_a_cota=0;
    }

    //recibe la función de cota a usar
    void pvcbt(bool (pvcBackTracking::*cota)(const vector<int> &,const vector<int> &,double)){
        vector<int> ciudades_visitadas;
        ciudades_visitadas.push_back(0);
        vector<int> ciudades_sinvisitar;

        int n = costes.size();
        for(int i = 1; i < n; ++i){
            ciudades_sinvisitar.push_back(i);
        }
        pvcbt(ciudades_visitadas,ciudades_sinvisitar,0, cota);
    }

    //cota0: no acota
    bool cota0(const vector<int> &ciudades_visitadas, const vector<int> &ciudades_sinvisitar, double coste){
        return true;
    }

    //cota1: encuentra la arista de menor peso y multiplica dicho peso por el número de ciudades sin visitar
    bool cota1(const vector<int> &ciudades_visitadas, const vector<int> &ciudades_sinvisitar, double coste){
        return ((coste+minimo*ciudades_sinvisitar.size())<coste_solucion);
    }

    //cota2: considera el minimo costo de SALIR de cada vértice
    bool cota2(const vector<int> &ciudades_visitadas, const vector<int> &ciudades_sinvisitar, double coste){
        double suma = coste;
        int n = ciudades_sinvisitar.size();
        int a = ciudades_visitadas.front();
        int b = ciudades_visitadas.back();

        double min = costes.at(a).at(b);

        for(int i = 0; i < n; i++){
            if(min > costes.at(b).at(ciudades_sinvisitar.at(i))){
                min = costes.at(b).at(ciudades_sinvisitar.at(i));
            }
        }
        suma += min;

        for(int i = 0; i < n; i++){
            min = costes.at(ciudades_sinvisitar.at(i)).at(a);
            for(int j = 0; j < n; j++){
                if(min > costes.at(ciudades_sinvisitar.at(j)).at(ciudades_sinvisitar.at(i)) && costes.at(ciudades_sinvisitar.at(j)).at(ciudades_sinvisitar.at(i)) != -1){
                    min = costes.at(ciudades_sinvisitar.at(j)).at(ciudades_sinvisitar.at(i));
                }
            }
            suma += min;
        }

        return (suma < coste_solucion);

    }

    //cota3: considera el inimo costo de SALIR Y ENTRAR en cada vértice
    bool cota3(const vector<int> &ciudades_visitadas, const vector<int> &ciudades_sinvisitar, double coste){
        double suma = coste;
        int n=costes.size();

        int a = ciudades_visitadas.front();
        int b = ciudades_visitadas.back();

        double min_a = numeric_limits<double>::max();
        double min_b = numeric_limits<double>::max();

        for(int i = 0; i != n; i++ ){
            if(costes.at(a).at(i) != -1 && costes.at(a).at(i) < min_a) min_a = costes.at(a).at(i);
            if(costes.at(b).at(i) != -1 && costes.at(b).at(i) < min_b) min_b = costes.at(b).at(i);
        }
        suma += (min_a + min_b)/2;

        double min1;
        double min2;
        for(auto it = ciudades_sinvisitar.begin(); it != ciudades_sinvisitar.end(); ++it){
            if(costes.at(*it).at(a) < costes.at(*it).at(b)) {
                min1 = costes.at(*it).at(a);
                min2 = costes.at(*it).at(b);
            }else{
                min1 = costes.at(*it).at(b);
                min2 = costes.at(*it).at(a);
            }
            for(int i = 1; i < n; ++i ){
                if(costes.at(*it).at(i) < min2){
                    if(costes.at(*it).at(i) < min1){
                        min2 = min1;
                        min1 = costes.at(*it).at(i);

                    }else{
                        min2 = costes.at(*it).at(i);
                    }
                }
            }
            suma += (min1+min2)/2;
        }

        return (suma <= coste_solucion);
    }


    bool cota4(const vector<int> &ciudades_visitadas, const vector<int> &ciudades_sinvisitar, double coste){
        double suma = coste;

        int a = ciudades_visitadas.front();
        int b = ciudades_visitadas.back();

        double min_a = numeric_limits<double>::max();
        double min_b = numeric_limits<double>::max();

        for(auto it = ciudades_sinvisitar.begin(); it != ciudades_sinvisitar.end(); ++it){
            if(costes.at(a).at(*it) != -1 && costes.at(a).at(*it) < min_a) min_a = costes.at(a).at(*it);
            if(costes.at(b).at(*it) != -1 && costes.at(b).at(*it) < min_b) min_b = costes.at(b).at(*it);
        }
        suma += (min_a + min_b)/2;

        double min1 ;
        double min2 ;

        for(auto it = ciudades_sinvisitar.begin(); it != ciudades_sinvisitar.end(); ++it){
            if(costes.at(*it).at(a) < costes.at(*it).at(b)) {
                min1 = costes.at(*it).at(a);
                min2 = costes.at(*it).at(b);
            }else{
                min1 = costes.at(*it).at(b);
                min2 = costes.at(*it).at(a);
            }

            for(auto j = ciudades_sinvisitar.begin(); j != ciudades_sinvisitar.end(); ++j){
                if((costes.at(*it).at(*j) != -1) && (costes.at(*it).at(*j) < min2)){
                    if(costes.at(*it).at(*j) < min1){
                        min2 = min1;
                        min1 = costes.at(*it).at(*j);

                    }else{
                        min2 = costes.at(*it).at(*j);
                    }
                }
            }
            suma += (min1+min2)/2;
        }
        return (suma <= coste_solucion);
    }

private:
    //Método privado que se llamará en pvcbt público
    //Recibe ciudades visitadas, ciudades sin visitar, el coste parcial y la función de cota a usar
    void pvcbt(const vector<int> &ciudades_visitadas, const vector<int> &ciudades_sinvisitar, double coste , bool (pvcBackTracking::*cota)(const vector<int> &,const vector<int> &,double)){

        if(ciudades_sinvisitar.empty()){
            coste += costes.at(0).at(*(ciudades_visitadas.end()-1));
            if (coste < coste_solucion){
                recorrido = ciudades_visitadas;
                coste_solucion = coste;
            }
        }
        else{
            vector<int> aux_ciudades_visitadas(ciudades_visitadas);
            aux_ciudades_visitadas.push_back(*(ciudades_sinvisitar.begin()));

            vector<int>  aux_ciudades_sinvisitar(ciudades_sinvisitar.begin() + 1,ciudades_sinvisitar.end());

            int k = 0;

            for (int i = 0; i < (int) ciudades_sinvisitar.size(); ++i) {

                //Ciudades visitadas
                aux_ciudades_visitadas.pop_back();
                aux_ciudades_visitadas.push_back(ciudades_sinvisitar.at(i));
                double coste_aux = coste + costes.at(*(aux_ciudades_visitadas.end()-1)).at(*(aux_ciudades_visitadas.end()-2));

                //Ciudades sin visitar

				nodos_creados++;
                if(coste_aux < coste_solucion){
                    if(!aux_ciudades_sinvisitar.empty()){
                        if(i != 0) {
                            if (aux_ciudades_sinvisitar.at(i - 1) == ciudades_sinvisitar.at(i)) {
                                aux_ciudades_sinvisitar.at(i - 1) = ciudades_sinvisitar.at(k);
                                k = i;
                            }
                        }
                    }else{
                        pvcbt(aux_ciudades_visitadas, aux_ciudades_sinvisitar,coste_aux,cota);
                    }
                    llamadas_a_cota++;
                    if((this->*cota)(aux_ciudades_visitadas,aux_ciudades_sinvisitar, coste_aux)){
                        pvcbt(aux_ciudades_visitadas, aux_ciudades_sinvisitar,coste_aux,cota);
                    }
                }
            }
        }
    }

    //Da una aproximación por un algoritmo greedy
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


};

int main(int argc, char **argv){
    if(argc!=2){
        cout << "ERROR: Número de parámetros incorrecto" << endl;
        exit(-1);
    }

    pvcBackTracking prueba(argv[1]);
    vector<int> solucion;

    bool (pvcBackTracking::*c1) (const vector<int> &,const vector<int> &,double);
    c1 = &pvcBackTracking::cota1;

    clock_t t_antes = clock();
    prueba.pvcbt(c1);
    clock_t t_despues = clock();

    ofstream salida;
    salida.open("tiemposCOTA1.dat",fstream::app);
    salida << prueba.getRecorrido().size() << " " << ((double) ((t_despues - t_antes)) / (CLOCKS_PER_SEC)) << endl;
    salida.close();

    cout << "COTA 1" << endl;
    cout << "Coste total del recorrido: " << prueba.getCosteSolucion() << endl;

    solucion = prueba.getRecorrido();
    for(vector<int>::iterator it = solucion.begin(); it != solucion.end(); ++it ){
        cout << *it << endl;
    }
    ofstream salidaNODOS;
    salidaNODOS.open("nodosCOTA1.dat",fstream::app);
    salidaNODOS << prueba.getRecorrido().size() << " " << prueba.getNodoscreados() << endl;
    salidaNODOS.close();
    cout << "Numero de nodos creados: " << prueba.getNodoscreados() << " " << prueba.getLlamadasCota() << endl;
    cout << "Numero de llamadas a cota: " << prueba.getLlamadasCota() << endl;

    prueba.reseteo();

    bool (pvcBackTracking::*c2) (const vector<int> &,const vector<int> &,double);
    c2 = &pvcBackTracking::cota2;

    t_antes=clock();
    prueba.pvcbt(c2);
    t_despues=clock();

    ofstream salida2;
    salida2.open("tiemposCOTA2.dat",fstream::app);
    salida2 << prueba.getRecorrido().size() << " " << ((double) ((t_despues - t_antes)) / (CLOCKS_PER_SEC)) << endl;
    salida2.close();

    cout << "COTA 2" << endl;
    cout << "Coste total del recorrido: " << prueba.getCosteSolucion() << endl;

    solucion = prueba.getRecorrido();
    for(vector<int>::iterator it = solucion.begin(); it != solucion.end(); ++it ){
        cout << *it << endl;
    }
    ofstream salidaNODOS2;
    salidaNODOS2.open("nodosCOTA2.dat",fstream::app);
    salidaNODOS2 << prueba.getRecorrido().size() << " " << prueba.getNodoscreados() << " " << prueba.getLlamadasCota() << endl;
    salidaNODOS2.close();
    cout << "Numero de nodos creados: " << prueba.getNodoscreados() << endl;
    cout << "Numero de llamadas a cota: " << prueba.getLlamadasCota() << endl;

    prueba.reseteo();

    bool (pvcBackTracking::*c3) (const vector<int> &,const vector<int> &,double);
    c3 = &pvcBackTracking::cota3;

    t_antes=clock();
    prueba.pvcbt(c3);
    t_despues=clock();

    ofstream salida3;
    salida3.open("tiemposCOTA3.dat",fstream::app);
    salida3 << prueba.getRecorrido().size() << " " << ((double) ((t_despues - t_antes)) / (CLOCKS_PER_SEC)) << endl;
    salida3.close();

    cout << "COTA 3" << endl;
    cout << "Coste total del recorrido: " << prueba.getCosteSolucion() << endl;

    solucion = prueba.getRecorrido();
    for(vector<int>::iterator it = solucion.begin(); it != solucion.end(); ++it ){
        cout << *it << endl;
    }

    ofstream salidaNODOS3;
    salidaNODOS3.open("nodosCOTA3.dat",fstream::app);
    salidaNODOS3 << prueba.getRecorrido().size() << " " << prueba.getNodoscreados() << " " << prueba.getLlamadasCota() << endl;
    salidaNODOS3.close();
    cout << "Numero de nodos creados: " << prueba.getNodoscreados() << endl;
    cout << "Numero de llamadas a cota: " << prueba.getLlamadasCota() << endl;

    prueba.reseteo();

    bool (pvcBackTracking::*c4) (const vector<int> &,const vector<int> &,double);
    c4 = &pvcBackTracking::cota4;

    t_antes=clock();
    prueba.pvcbt(c4);
    t_despues=clock();

    ofstream salida4;
    salida4.open("tiemposCOTA4.dat",fstream::app);
    salida4 << prueba.getRecorrido().size() << " " << ((double) ((t_despues - t_antes)) / (CLOCKS_PER_SEC)) << endl;
    salida4.close();

    cout << "COTA 4" << endl;
    cout << "Coste total del recorrido: " << prueba.getCosteSolucion() << endl;

    solucion = prueba.getRecorrido();
    for(vector<int>::iterator it = solucion.begin(); it != solucion.end(); ++it ){
        cout << *it << endl;
    }

    ofstream salidaNODOS4;
    salidaNODOS4.open("nodosCOTA4.dat",fstream::app);
    salidaNODOS4 << prueba.getRecorrido().size() << " " << prueba.getNodoscreados() << " " << prueba.getLlamadasCota() << endl;
    salidaNODOS4.close();
    cout << "Numero de nodos creados: " << prueba.getNodoscreados() << endl;
    cout << "Numero de llamadas a cota: " << prueba.getLlamadasCota() << endl;



    return 0;
}
