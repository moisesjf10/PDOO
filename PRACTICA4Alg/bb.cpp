#include <iostream>
#include <vector>
#include <fstream>
#include <limits>
#include <queue>
using namespace std;

//Struct nodo: representa un nodo del árbol de decisiones
struct nodo{
    vector<int> visitadas;
    vector<int> sin_visitar;
    double coste;
    double estimacion;
};

//Functor usado para la cola con prioridad de la clase pvcBranchBound
class comparador{
public:
    bool operator()(const nodo &n1, const nodo &n2){
        return (n1.estimacion > n2.estimacion);
    }

};

class pvcBranchBound{
private:
    vector<vector<double>> costes;  //matriz de costes
    vector<int> recorrido;          //solucion
    double coste_solucion;
    priority_queue<nodo, vector<nodo>, comparador> nodos_a_expandir; //nodos vivos
    double minimo;
    int nodos_creados;

public:
    //Constructor
    //Recibe el nombre del archivo en el que están los datos
    pvcBranchBound(char *archivo){
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
        minimo=Minimo();
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

    void reseteo(){
        pair<vector<int>,double> greedy = pvcGreedy();
        coste_solucion=greedy.second;
        recorrido=greedy.first;
        nodos_creados=0;
    }

    //Recibe la función de cota a usar
    void pvcbb(double (pvcBranchBound::*cota)(const nodo &)){
        vector<int> ciudades_visitadas;
        ciudades_visitadas.push_back(0);
        vector<int> ciudades_sinvisitar;

        int n = costes.size();
        for(int i = 1; i < n; ++i){
            ciudades_sinvisitar.push_back(i);
        }
        nodo n1={ciudades_visitadas,ciudades_sinvisitar,0,0};
        n1.estimacion=((this->*cota)(n1));
        pvcbb(n1,cota);

    }

    //cota1: encuentra la arista de menor peso y multiplica dicho peso por el número de ciudades sin visitar
    double cota1(const nodo &n){
        return ((n.coste+minimo*n.sin_visitar.size()));
    }

    //cota2: considera el minimo costo de SALIR de cada vértice
    double cota2(const nodo &n1){
        double suma = n1.coste;
        int n = n1.sin_visitar.size();
        int a = n1.visitadas.front();
        int b = n1.visitadas.back();

        double min = costes.at(a).at(b);

        for(int i = 0; i < n; i++){
            if(min > costes.at(b).at(n1.sin_visitar.at(i))){
                min = costes.at(b).at(n1.sin_visitar.at(i));
            }
        }
        suma += min;

        for(int i = 0; i < n; i++){
            min = costes.at(n1.sin_visitar.at(i)).at(a);
            for(int j = 0; j < n; j++){
                if(min > costes.at(n1.sin_visitar.at(j)).at(n1.sin_visitar.at(i)) && costes.at(n1.sin_visitar.at(j)).at(n1.sin_visitar.at(i)) != -1){
                    min = costes.at(n1.sin_visitar.at(j)).at(n1.sin_visitar.at(i));
                }
            }
            suma += min;
        }

        return suma;
    }

    //cota3: considera el inimo costo de SALIR Y ENTRAR en cada vértice
    double cota3(const nodo &n) {
        double suma = n.coste;
        int m=costes.size();

        int a = n.visitadas.front();
        int b = n.visitadas.back();

        double min_a = numeric_limits<double>::max();
        double min_b = numeric_limits<double>::max();

        for(int i = 0; i != m; i++ ){
            if(costes.at(a).at(i) != -1 && costes.at(a).at(i) < min_a) min_a = costes.at(a).at(i);
            if(costes.at(b).at(i) != -1 && costes.at(b).at(i) < min_b) min_b = costes.at(b).at(i);
        }
        suma += (min_a + min_b)/2;

        double min1;
        double min2;
        for(auto it = n.sin_visitar.begin(); it != n.sin_visitar.end(); ++it){
            if(costes.at(*it).at(a) < costes.at(*it).at(b)) {
                min1 = costes.at(*it).at(a);
                min2 = costes.at(*it).at(b);
            }else{
                min1 = costes.at(*it).at(b);
                min2 = costes.at(*it).at(a);
            }
            for(int i = 1; i < m; ++i ){
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
        return suma;
    }

    double cota4(const nodo &n1){
        double suma = n1.coste;

        int a = n1.visitadas.front();
        int b = n1.visitadas.back();

        double min_a = numeric_limits<double>::max();
        double min_b = numeric_limits<double>::max();

        for(auto it = n1.sin_visitar.begin(); it != n1.sin_visitar.end(); ++it){
            if(costes.at(a).at(*it) != -1 && costes.at(a).at(*it) < min_a) min_a = costes.at(a).at(*it);
            if(costes.at(b).at(*it) != -1 && costes.at(b).at(*it) < min_b) min_b = costes.at(b).at(*it);
        }
        suma += (min_a + min_b)/2;

        double min1 ;
        double min2 ;

        for(auto it = n1.sin_visitar.begin(); it != n1.sin_visitar.end(); ++it){
            if(costes.at(*it).at(a) < costes.at(*it).at(b)) {
                min1 = costes.at(*it).at(a);
                min2 = costes.at(*it).at(b);
            }else{
                min1 = costes.at(*it).at(b);
                min2 = costes.at(*it).at(a);
            }

            for(auto j = n1.sin_visitar.begin(); j != n1.sin_visitar.end(); ++j){
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
        return suma;
    }

private:
    //Método privado que se llamará desde pvcbb público
    //Recibe el nodo y la función de cota a usar
    void pvcbb(const nodo &c, double (pvcBranchBound::*cota)(const nodo &)){
        nodos_a_expandir.push(c);

        while( !nodos_a_expandir.empty() && nodos_a_expandir.top().estimacion < coste_solucion){
            nodo nodo_mejor = nodos_a_expandir.top();
            nodos_a_expandir.pop();

            nodo nuevo;
            int k = 0;
            nuevo.sin_visitar = vector<int>(nodo_mejor.sin_visitar.begin() + 1,nodo_mejor.sin_visitar.end());
            nuevo.visitadas = nodo_mejor.visitadas;
            nuevo.visitadas.push_back(nodo_mejor.sin_visitar.at(0));
            for(int i = 0; i < nodo_mejor.sin_visitar.size(); i++){
                nuevo.visitadas.pop_back();
                nuevo.visitadas.push_back(nodo_mejor.sin_visitar.at(i));
                nuevo.coste = nodo_mejor.coste + costes.at(*(nuevo.visitadas.end()-1)).at(*(nuevo.visitadas.end()-2));

                if(nuevo.coste < coste_solucion){
                    if(!nuevo.sin_visitar.empty()){
                        if (i != 0){
                            if (nuevo.sin_visitar.at(i-1) == nodo_mejor.sin_visitar.at(i)) {
                                nuevo.sin_visitar.at(i-1) = nodo_mejor.sin_visitar.at(k);
                                k = i;
                            }
                        }
                        nuevo.estimacion = (this->*cota)(nuevo);
                        if(nuevo.estimacion < coste_solucion){
                            nodos_a_expandir.push(nuevo);
                            nodos_creados++;
                        }
                    }else{
                        nuevo.coste += costes.at(nuevo.visitadas.front()).at(nuevo.visitadas.back());
                        if (nuevo.coste < coste_solucion){
                            coste_solucion = nuevo.coste;
                            recorrido=nuevo.visitadas;
                        }
                    }
                }
            }
        }
    }

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
};

int main(int argc, char **argv){
    if(argc!=2){
        cout << "ERROR: Número de parámetros incorrecto" << endl;
        exit(-1);
    }

    pvcBranchBound prueba(argv[1]);
    vector<int> solucion;

    double (pvcBranchBound::*c1) (const nodo &);
    c1 = &pvcBranchBound::cota1;

    clock_t t_antes = clock();
    prueba.pvcbb(c1);
    clock_t t_despues = clock();

    ofstream salida;
    salida.open("tiemposCOTA1bb.dat",fstream::app);
    salida << prueba.getRecorrido().size() << " " << ((double) ((t_despues - t_antes)) / (CLOCKS_PER_SEC)) << endl;
    salida.close();

    cout << "COTA 1" << endl;
    cout << "Coste total del recorrido: " << prueba.getCosteSolucion() << endl;

    solucion = prueba.getRecorrido();
    for(vector<int>::iterator it = solucion.begin(); it != solucion.end(); ++it ){
        cout << *it << endl;
    }
    ofstream salidaNODOS;
    salidaNODOS.open("nodosCOTA1bb.dat",fstream::app);
    salidaNODOS << prueba.getRecorrido().size() << " " << prueba.getNodoscreados() << endl;
    salidaNODOS.close();
    cout << "Numero de nodos creados: " << prueba.getNodoscreados() << endl;

    prueba.reseteo();

    double (pvcBranchBound::*c2) (const nodo &);
    c2 = &pvcBranchBound::cota2;

    t_antes=clock();
    prueba.pvcbb(c2);
    t_despues=clock();

    ofstream salida2;
    salida2.open("tiemposCOTA2bb.dat",fstream::app);
    salida2 << prueba.getRecorrido().size() << " " << ((double) ((t_despues - t_antes)) / (CLOCKS_PER_SEC)) << endl;
    salida2.close();

    cout << "COTA 2" << endl;
    cout << "Coste total del recorrido: " << prueba.getCosteSolucion() << endl;

    solucion = prueba.getRecorrido();
    for(vector<int>::iterator it = solucion.begin(); it != solucion.end(); ++it ){
        cout << *it << endl;
    }
    ofstream salidaNODOS2;
    salidaNODOS2.open("nodosCOTA2bb.dat",fstream::app);
    salidaNODOS2 << prueba.getRecorrido().size() << " " << prueba.getNodoscreados() << endl;
    salidaNODOS2.close();
    cout << "Numero de nodos creados: " << prueba.getNodoscreados() << endl;

    prueba.reseteo();

    double (pvcBranchBound::*c3) (const nodo &);
    c3 = &pvcBranchBound::cota3;

    t_antes=clock();
    prueba.pvcbb(c3);
    t_despues=clock();

    ofstream salida3;
    salida3.open("tiemposCOTA3bb.dat",fstream::app);
    salida3 << prueba.getRecorrido().size() << " " << ((double) ((t_despues - t_antes)) / (CLOCKS_PER_SEC)) << endl;
    salida3.close();

    cout << "COTA 3" << endl;
    cout << "Coste total del recorrido: " << prueba.getCosteSolucion() << endl;

    solucion = prueba.getRecorrido();
    for(vector<int>::iterator it = solucion.begin(); it != solucion.end(); ++it ){
        cout << *it << endl;
    }

    ofstream salidaNODOS3;
    salidaNODOS3.open("nodosCOTA3bb.dat",fstream::app);
    salidaNODOS3 << prueba.getRecorrido().size() << " " << prueba.getNodoscreados() << endl;
    salidaNODOS3.close();
    cout << "Numero de nodos creados: " << prueba.getNodoscreados() << endl;

    prueba.reseteo();

    double (pvcBranchBound::*c4) (const nodo &);
    c4 = &pvcBranchBound::cota4;

    t_antes=clock();
    prueba.pvcbb(c4);
    t_despues=clock();

    ofstream salida4;
    salida4.open("tiemposCOTA4bb.dat",fstream::app);
    salida4 << prueba.getRecorrido().size() << " " << ((double) ((t_despues - t_antes)) / (CLOCKS_PER_SEC)) << endl;
    salida4.close();

    cout << "COTA 4" << endl;
    cout << "Coste total del recorrido: " << prueba.getCosteSolucion() << endl;

    solucion = prueba.getRecorrido();
    for(vector<int>::iterator it = solucion.begin(); it != solucion.end(); ++it ){
        cout << *it << endl;
    }

    ofstream salidaNODOS4;
    salidaNODOS4.open("nodosCOTA4bb.dat",fstream::app);
    salidaNODOS4 << prueba.getRecorrido().size() << " " << prueba.getNodoscreados() << endl;
    salidaNODOS4.close();
    cout << "Numero de nodos creados: " << prueba.getNodoscreados() << endl;



    return 0;
}